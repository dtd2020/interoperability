package mz.gov.inage.esircev.v2.controllers;

import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Hidden
@Controller
public class HomeController {

    @RequestMapping("/")
    public String index(Model model){
        model.addAttribute("name", "Wontas");
        return "index";
    }
}
