package org.example.spring.contollers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class HomeController {

    @RequestMapping("/")
    public String showForm() {
        return "helloworld-form";
    }

    @RequestMapping("/processForm")
    public String processFormVersionThree(@RequestParam("studentName") String name, Model model) {

        // convert the data to all caps
        name = name.toUpperCase();


        model.addAttribute("message",name );

        return "welcome";
    }

}