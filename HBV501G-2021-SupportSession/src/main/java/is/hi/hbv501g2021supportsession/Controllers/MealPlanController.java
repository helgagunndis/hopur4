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

    //Taka út þessar breytur
    private Recipe Monday;
    private Recipe Tuesday;
    private Recipe Wednesday;
    private Recipe Thursday;
    private Recipe Friday;
    private Recipe Saturday;
    private Recipe Sunday;

    private boolean firstRun=false;

   private ArrayList<Recipe> weekdays; //Monday, Tuesday ...
   /*private List weekdaysCheckbox; // 1 for on 0 for off*/


    @Autowired
    public MealPlanController(MealPlanService mealPlanService , RecipeService recipeService){
        this.mealPlanService = mealPlanService;
        this.recipeService = recipeService;
        this.userService = userService;
    }


    @RequestMapping(value = "/" , method = RequestMethod.GET)
    public String mealplan(Model model, HttpSession session) {
        // Finnum hvaða category við erum að vinna með.
        List recipeCategory = recipeService.findByRecipeCategoryLessThanEqual(Category);
        model.addAttribute("categoryRecipe", recipeCategory);

        // Spurning um að setja þetta inn implementation ?
        Set<Integer> randomNumbers = new HashSet<>();
        while(randomNumbers.size() != 8){
            randomNumbers.add(1 + (int) (Math.random() * recipeCategory.size()));
        }

        List<Integer> randomNumberList = new ArrayList<Integer>(randomNumbers);

        // þarf að skilgreina betur hvenær á að keyra þetta!
        if(!firstRun){
            weekdays =new ArrayList<Recipe>();
            weekdays.add(recipeService.findRecipe(Category, randomNumberList.get(0)));
            weekdays.add(recipeService.findRecipe(Category, randomNumberList.get(1)));
            weekdays.add(recipeService.findRecipe(Category, randomNumberList.get(2)));
            weekdays.add(recipeService.findRecipe(Category, randomNumberList.get(3)));
            weekdays.add(recipeService.findRecipe(Category, randomNumberList.get(4)));
            weekdays.add(recipeService.findRecipe(Category, randomNumberList.get(5)));
            weekdays.add(recipeService.findRecipe(Category, randomNumberList.get(6)));
            firstRun=true;
        }

        model.addAttribute("mondayRecipe", weekdays.get(0));
        model.addAttribute("tuesdayRecipe", weekdays.get(1));
        model.addAttribute("wednesdayRecipe", weekdays.get(2));
        model.addAttribute("thursdayRecipe", weekdays.get(3));
        model.addAttribute("fridayRecipe", weekdays.get(4));
        model.addAttribute("saturdayRecipe", weekdays.get(5));
        model.addAttribute("sundayRecipe", weekdays.get(6));
        return "mealplan";
    }


    @RequestMapping(value = "/category" , method = RequestMethod.GET)
    public String recipeListGET(Model model, Recipe recipe){
        Category = recipe.getRecipeCategory();
        return "redirect:/";
    }
    //Þarf að breyta þessum þannig að hann geti valið númer hvað dagurinn er
    @RequestMapping(value = "/mondayChooseRecipe",method = RequestMethod.GET)
    public String mondayChooseRecipeGET(Model model, Recipe recipe){
        Monday =recipeService.findByRecipeID(recipe.getRecipeID());
        weekdays.set(0,Monday);
        return "redirect:/";
    }

    // þarf að breyta nafninu á numberofDays
    @RequestMapping(value = "/tryagain",method = RequestMethod.GET)
    public String tryagain(Model model, Recipe recipe, MealPlan mealPlan){
        int weekday=mealPlan.getNumberOfDays();
        Recipe newRecipe =recipeService.findRandomRecipe(Category);

        // fær nýja uppskrift og setur inn í listan á réttan stað miðað við takkann
        weekdays.set(weekday,newRecipe);
        return "redirect:/";
    }


    //Tuesday
    @RequestMapping(value = "/tuesdayChooseRecipe",method = RequestMethod.GET)
    public String tuesdayChooseRecipeGET(Model model, Recipe recipe){
        Tuesday =recipeService.findByRecipeID(recipe.getRecipeID());
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

    //Thursday
    @RequestMapping(value = "/thursdayChooseRecipe",method = RequestMethod.GET)
    public String thursdayChooseRecipeGET(Model model, Recipe recipe){
        Thursday =recipeService.findByRecipeID(recipe.getRecipeID());
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

    //Saturday
    @RequestMapping(value = "/saturdayChooseRecipe",method = RequestMethod.GET)
    public String saturdayChooseRecipeGET(Model model, Recipe recipe){
        Saturday =recipeService.findByRecipeID(recipe.getRecipeID());
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
}
