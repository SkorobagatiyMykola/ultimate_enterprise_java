package ua.skorobahatyi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/hello")
public class HelloController {
    @GetMapping
    public String hello(){
        System.out.println("TEST  !!!!");
        return "morning";
    }

    @GetMapping("/model")
    public String helloModel(Model model){
        model.addAttribute("message","How are you?");

        return "morning2";
    }
}
