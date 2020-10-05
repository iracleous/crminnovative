package gr.codehub.crminnovative.viewcontroller;

import gr.codehub.crminnovative.exception.ProductNotFoundException;
import gr.codehub.crminnovative.model.Customer;
import gr.codehub.crminnovative.model.Product;
import gr.codehub.crminnovative.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.Base64;

@Controller
public class AppController {

    private ProductService productService;

    @Autowired
    private AppController(ProductService productService){
        this.productService = productService;
    }

    @GetMapping("/createproduct")
    public String crtproduct(@ModelAttribute Product product){
        return "createProductPage";
    }

    @GetMapping("/login")
    public String loginAuth(){
        return "loginPage";
    }

    @PostMapping("loginAuth")
    public String loginAuthorization(@ModelAttribute Customer customer){
        return (customer.getEmail().equals("giannis")?"index":"errorPage");
    }

    @GetMapping("/viewProduct/{id}")
    public String fileUploader(@PathVariable int id, Model model) throws ProductNotFoundException {
        Product product = productService.getProduct(id);
        model.addAttribute(product);
        return "viewProduct";
    }

    @PostMapping("/updateProduct")
    public String updateProduct(
            @RequestParam int id,
            @RequestParam String name,
            @RequestParam double price,
            @RequestParam int quantity,
            @RequestParam MultipartFile photo,
            RedirectAttributes redir) throws IOException, ProductNotFoundException {
        String imageString = Base64.getEncoder().encodeToString(photo.getBytes());
        Product product = new Product();
        product.setName(name);
        product.setPrice(price);
        product.setInventoryQuantity(quantity);
        product.setProfilePictureAsDataURL(imageString);
        productService.updateProduct(product, id);
        redir.addFlashAttribute("product", product);
        return "redirect:/viewProduct/" + id;
    }
}
