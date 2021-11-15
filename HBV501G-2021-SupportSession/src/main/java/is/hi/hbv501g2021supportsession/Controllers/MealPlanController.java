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
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

   /*
   private List<Recipe> weekdays; //Monday, Tuesday ...
   private List weekdaysCheckbox; // 1 for on 0 for off
   */


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

        Set<Integer> randomNumbers = new HashSet<>();
        while(randomNumbers.size() != 8){
            randomNumbers.add(1 + (int) (Math.random() * recipeCategory.size()));
        }

        List<Integer> randomNumberList = new ArrayList<Integer>(randomNumbers);

        if(Monday==null|| Monday.getRecipeCategory() > Category){
            Monday = recipeService.findRecipe(Category, randomNumberList.get(0));
            Tuesday = recipeService.findRecipe(Category, randomNumberList.get(1));
            Wednesday = recipeService.findRecipe(Category, randomNumberList.get(2));
            Thursday = recipeService.findRecipe(Category, randomNumberList.get(3));
            Friday = recipeService.findRecipe(Category, randomNumberList.get(4));
            Saturday = recipeService.findRecipe(Category, randomNumberList.get(5));
            Sunday = recipeService.findRecipe(Category, randomNumberList.get(6));
        }

        model.addAttribute("mondayRecipe", Monday);
        model.addAttribute("tuesdayRecipe", Tuesday);
        model.addAttribute("wednesdayRecipe", Wednesday);
        model.addAttribute("thursdayRecipe", Thursday);
        model.addAttribute("fridayRecipe", Friday);
        model.addAttribute("saturdayRecipe", Saturday);
        model.addAttribute("sundayRecipe", Sunday);
        return "mealplan";
    }


    @RequestMapping(value = "/category" , method = RequestMethod.GET)
    public String recipeListGET(Model model, Recipe recipe, MealPlan mealPlan){
        Category = recipe.getRecipeCategory();
        return "redirect:/";
    }
    //Monday
    @RequestMapping(value = "/mondayChooseRecipe",method = RequestMethod.GET)
    public String mondayChooseRecipeGET(Model model, Recipe recipe){
        Monday =recipeService.findByRecipeID(recipe.getRecipeID());
        model.addAttribute("mondayRecipe", Monday);
        return "redirect:/";
    }

    @RequestMapping(value = "/mondaytryagain",method = RequestMethod.GET)
    public String mondaytryagain(Model model, Recipe recipe, MealPlan mealPlan){
        Monday = recipeService.findRandomRecipe(Category);
        model.addAttribute("mondayRecipe", Monday);
        return "redirect:/";
    }

    //Tuesday
    @RequestMapping(value = "/tuesdayChooseRecipe",method = RequestMethod.GET)
    public String tuesdayChooseRecipeGET(Model model, Recipe recipe){
        Tuesday =recipeService.findByRecipeID(recipe.getRecipeID());
        model.addAttribute("tuesdayRecipe", Tuesday);
        return "redirect:/";
    }

    @RequestMapping(value = "/tuesdaytryagain",method = RequestMethod.GET)
    public String tuesdaytryagain(Model model, Recipe recipe, MealPlan mealPlan){
        Tuesday = recipeService.findRandomRecipe(Category);
        model.addAttribute("tuesdayRecipe", Tuesday);
        return "redirect:/";
    }

    //Wednesday
    @RequestMapping(value = "/wednesdayChooseRecipe",method = RequestMethod.GET)
    public String wednesdayChooseRecipeGET(Model model, Recipe recipe){
        Wednesday =recipeService.findByRecipeID(recipe.getRecipeID());
        model.addAttribute("wednesdayRecipe", Wednesday);
        return "redirect:/";
    }

    @RequestMapping(value = "/wednesdaytryagain",method = RequestMethod.GET)
    public String wednesdaytryagain(Model model, Recipe recipe, MealPlan mealPlan){
        Wednesday = recipeService.findRandomRecipe(Category);
        model.addAttribute("wednesdayRecipe", Wednesday);
        return "redirect:/";
    }

    //Thursday
    @RequestMapping(value = "/thursdayChooseRecipe",method = RequestMethod.GET)
    public String thursdayChooseRecipeGET(Model model, Recipe recipe){
        Thursday =recipeService.findByRecipeID(recipe.getRecipeID());
        model.addAttribute("thursdayRecipe", Thursday);
        return "redirect:/";
    }

    @RequestMapping(value = "/thursdaytryagain",method = RequestMethod.GET)
    public String thursdaytryagain(Model model, Recipe recipe, MealPlan mealPlan){
        Thursday = recipeService.findRandomRecipe(Category);
        model.addAttribute("thursdayRecipe", Thursday);
        return "redirect:/";
    }

    //Friday
    @RequestMapping(value = "/fridayChooseRecipe",method = RequestMethod.GET)
    public String fridayChooseRecipeGET(Model model, Recipe recipe){
        Friday =recipeService.findByRecipeID(recipe.getRecipeID());
        model.addAttribute("fridayRecipe", Friday);
        return "redirect:/";
    }

    @RequestMapping(value = "/fridaytryagain",method = RequestMethod.GET)
    public String fridaytryagain(Model model, Recipe recipe, MealPlan mealPlan){
        Friday = recipeService.findRandomRecipe(Category);
        model.addAttribute("fridayRecipe", Friday);
        return "redirect:/";
    }

    //Saturday
    @RequestMapping(value = "/saturdayChooseRecipe",method = RequestMethod.GET)
    public String saturdayChooseRecipeGET(Model model, Recipe recipe){
        Saturday =recipeService.findByRecipeID(recipe.getRecipeID());
        model.addAttribute("saturdayRecipe", Saturday);
        return "redirect:/";
    }

    @RequestMapping(value = "/saturdaytryagain",method = RequestMethod.GET)
    public String saturdaytryagain(Model model, Recipe recipe, MealPlan mealPlan){
        Saturday = recipeService.findRandomRecipe(Category);
        model.addAttribute("saturdayRecipe", Saturday);
        return "redirect:/";
    }

    //Sunday
    @RequestMapping(value = "/sundayChooseRecipe",method = RequestMethod.GET)
    public String sundayChooseRecipeGET(Model model, Recipe recipe){
        Sunday =recipeService.findByRecipeID(recipe.getRecipeID());
        model.addAttribute("sundayRecipe", Sunday);
        return "redirect:/";
    }

    @RequestMapping(value = "/sundaytryagain",method = RequestMethod.GET)
    public String sundaytryagain(Model model, Recipe recipe, MealPlan mealPlan){
        Sunday = recipeService.findRandomRecipe(Category);
        model.addAttribute("sundayRecipe", Sunday);
        return "redirect:/";
    }
}
