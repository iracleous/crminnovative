package gr.codehub.crminnovative.viewcontroller;

import gr.codehub.crminnovative.model.Product;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/v1")
public class AppController {
    @GetMapping("/app")
    public String getFirstPage(){
        return "index";
    }
}
