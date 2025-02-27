package logistics_api.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/auth/login")
    public String login() {
        return "login";
    }


    @GetMapping("/home")
    public String home() {
        return "home";
    }


}
