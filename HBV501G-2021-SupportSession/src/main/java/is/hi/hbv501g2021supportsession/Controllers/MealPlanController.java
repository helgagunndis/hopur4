package is.hi.hbv501g2021supportsession.Controllers;


import is.hi.hbv501g2021supportsession.Persistence.Entities.MealPlan;
import is.hi.hbv501g2021supportsession.Persistence.Entities.Recipe;
import is.hi.hbv501g2021supportsession.Persistence.Entities.User;
import is.hi.hbv501g2021supportsession.Services.MealPlanService;
import is.hi.hbv501g2021supportsession.Services.RecipeService;
import is.hi.hbv501g2021supportsession.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * A controller class for meal plan
 */
@Controller
public class MealPlanController {

    RecipeService recipeService;
    MealPlanService mealPlanService;
    UserService userService;

    private int Category=4;
    private int Numbdays=7;
    private Recipe Monday;
    private Recipe Tuesday;
    private Recipe Wednesday;
    private Recipe Thursday;
    private Recipe Friday;
    private Recipe Saturday;
    private Recipe Sunday;

   /* private List<Recipe> weekdays; //Monday, Tuesday ...
    private List weekdaysCheckbox; // 1 for on 0 for off*/


    @Autowired
    public MealPlanController(MealPlanService mealPlanService , RecipeService recipeService){
        this.mealPlanService = mealPlanService;
        this.recipeService = recipeService;
        this.userService = userService;
    }


    @RequestMapping(value = "/" , method = RequestMethod.GET)
    public String mealplan(Model model, HttpSession session) {
        // Athuga hvaða category er verið að vinna með
        List recipeCategory = recipeService.findByRecipeCategoryLessThanEqual(Category);
        model.addAttribute("categoryRecipe", recipeCategory);

        if(Monday==null|| Monday.getRecipeCategory() > Category){
            Monday = recipeService.findRandomRecipe(Category);

        }
        model.addAttribute("mondayRecipe", Monday);
        return "mealplan";
    }


    @RequestMapping(value = "/category" , method = RequestMethod.GET)
    public String recipeListGET(Model model, Recipe recipe, MealPlan mealPlan){
        Category = recipe.getRecipeCategory();
        return "redirect:/";
    }

    @RequestMapping(value = "/chooseRecipe",method = RequestMethod.GET)
    public String chooseRecipeGET(Model model, Recipe recipe){
        Monday =recipeService.findByRecipeID(recipe.getRecipeID());
        return "redirect:/";
    }

    @RequestMapping(value = "/tryagain",method = RequestMethod.GET)
    public String tryagain(Model model, Recipe recipe, MealPlan mealPlan){
        Monday = recipeService.findRandomRecipe(Category);
        model.addAttribute("mondayRecipe", Monday);
        return "redirect:/";
    }

}
