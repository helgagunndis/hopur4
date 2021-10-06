package is.hi.hbv501g2021supportsession.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
    @RequestMapping("/")
    public String HomeController() {
        //Business Logic
        // Call a method in service class
        // Add some data to the Model
        return "home";
    }
}
