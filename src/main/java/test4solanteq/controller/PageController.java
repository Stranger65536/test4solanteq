package test4solanteq.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@SuppressWarnings("unused")
@Controller
public class PageController {
    @SuppressWarnings({"HardcodedFileSeparator", "SameReturnValue"})
    @RequestMapping("/")
    public String home() {
        return "redirect:/index.html";
    }
}
