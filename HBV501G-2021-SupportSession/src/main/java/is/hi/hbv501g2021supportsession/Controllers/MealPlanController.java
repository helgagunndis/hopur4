package is.hi.hbv501g2021supportsession.Controllers;

import is.hi.hbv501g2021supportsession.Persistence.Entities.Recipe;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MealPlanController {
    @RequestMapping("/")
    public String HomeController() {
        //Business Logic
        // Call a method in service class
        // Add some data to the Model
        return "mealplan";
    }

    @RequestMapping(value = "/recipes", method = RequestMethod.GET)
    public String viewAllRecipes(Recipe recipe){
        return "recipes";
    }
}
