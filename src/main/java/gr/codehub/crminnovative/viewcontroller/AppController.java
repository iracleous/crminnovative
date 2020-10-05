package gr.codehub.crminnovative.viewcontroller;

import gr.codehub.crminnovative.model.Customer;
import gr.codehub.crminnovative.model.Product;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AppController {
    @GetMapping("/admin/createproduct")
    public String crtproduct(@ModelAttribute Product product){
        return "createProductPage";
    }

    @GetMapping("/test/createproduct")
    public String crtproduct2(@ModelAttribute Product product){
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
}
