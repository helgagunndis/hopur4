package is.hi.hbv501g2021supportsession.Controllers;


import is.hi.hbv501g2021supportsession.Services.MealPlanService;
import is.hi.hbv501g2021supportsession.Services.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * A controller class for meal plan
 */
@Controller
public class MealPlanController {

    RecipeService recipeService;
    MealPlanService mealPlanService;


    //@Autowired
    //public MealPlanController(MealPlanService mealPlanService ){
    //    this.mealPlanService = mealPlanService;
    //}


    @RequestMapping("/")
    public String home(Model model) {
        //Business Logic
        // Call a method in service class
        // Add some data to the Model
        return "mealplan";
    }

    //algengar spurningar síða
    @RequestMapping("/faq")
    public String faqPage() {
        return "faq";
    }

}
