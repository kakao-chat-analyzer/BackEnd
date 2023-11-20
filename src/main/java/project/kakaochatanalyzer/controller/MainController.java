package project.kakaochatanalyzer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @GetMapping("/main")
    public String mainPage() {
        return "main"; // Refers to a Thymeleaf template named main.html
    }

    @GetMapping("/nextPage")
    public String nextPage() {
        return "next"; // Refers to a Thymeleaf template named next.html
    }
}
