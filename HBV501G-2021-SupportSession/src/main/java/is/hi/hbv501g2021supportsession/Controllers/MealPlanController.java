package is.hi.hbv501g2021supportsession.Controllers;

import is.hi.hbv501g2021supportsession.Persistence.Entities.MPList;
import is.hi.hbv501g2021supportsession.Persistence.Entities.MealPlan;
import is.hi.hbv501g2021supportsession.Persistence.Entities.Recipe;
import is.hi.hbv501g2021supportsession.Persistence.Entities.User;
import is.hi.hbv501g2021supportsession.Services.MPListService;
import is.hi.hbv501g2021supportsession.Services.MealPlanService;
import is.hi.hbv501g2021supportsession.Services.RecipeService;
import is.hi.hbv501g2021supportsession.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * A controller class for meal plan
 */
@Controller
public class MealPlanController {
    RecipeService recipeService;
    MealPlanService mealPlanService;
    UserService userService;
    MPListService mpListService;

    private int Category=4;

   private List<Recipe> weekdays; //Monday, Tuesday ...
   /*private List weekdaysCheckbox; // 1 for on 0 for off*/

    @Autowired
    public MealPlanController(MealPlanService mealPlanService , RecipeService recipeService,MPListService mpListService, UserService userService){
        this.mealPlanService = mealPlanService;
        this.recipeService = recipeService;
        this.userService =userService;
        this.mpListService = mpListService;
    }

    @RequestMapping(value = "/" , method = RequestMethod.GET)
    public String mealplan(Model model, HttpSession session) {
        List recipeCategory = recipeService.findByRecipeCategoryLessThanEqual(Category);
        model.addAttribute("categoryRecipe", recipeCategory);

        if(weekdays==null){
            //weekdays = new ArrayList<Recipe>();
            weekdays = recipeService.findListOfRecipe(Category);
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

    // Velur category
    @RequestMapping(value = "/category" , method = RequestMethod.GET)
    public String recipeListGET(Recipe recipe){
        Category = recipe.getRecipeCategory();
        return "redirect:/";
    }

    // random recipe
    @RequestMapping(value = "/manualRecipes",method = RequestMethod.GET)
    public String manualRecipes(Recipe recipe, MealPlan mealplan){
        int weekday=mealplan.getNumberOfWeekDay();
        Recipe newRecipe =recipeService.findByRecipeID(recipe.getRecipeID());
        weekdays.set(weekday,newRecipe);
        return "redirect:/";
    }

    // fær nýja uppskrift og setur inn í listan á réttan stað miðað við valdan dag
    @RequestMapping(value = "/generateOneMeal",method = RequestMethod.GET)
    public String generateOneMeal(MealPlan mealPlan){
        int weekday = mealPlan.getNumberOfWeekDay();
        Recipe newRecipe =recipeService.findRandomRecipe(Category);
        weekdays.set(weekday,newRecipe);
        return "redirect:/";
    }

    @RequestMapping(value = "/generateWholeWeek",method = RequestMethod.GET)
    public String generateWholeWeek(){
        weekdays= null;
        /*ArrayList<Recipe> newWeekdays = recipeService.findListOfRecipe(Category);
        for (int i=0; i<7; i++) {
            weekdays.set(i,newWeekdays.get(i));
        }*/
        return "redirect:/";
    }


    //confirm page
    @RequestMapping(value = "/confirm",method = RequestMethod.GET)
    public String createMealPlan(Model model,HttpSession session, MealPlan mealPlan) {
        //TODO all ingredients added to a shopping list

        User sessionUser = (User) session.getAttribute("LoggedInUser");
        model.addAttribute("LoggedInUser", sessionUser);
        if (sessionUser != null) {
            mealPlan.setUser(sessionUser);
        }

        /*
        mealPlan.setNumberOfWeekDay(7);
        mealPlan.setRecipeCategory(Category);
        mealPlanService.save(mealPlan);
        //mealPlan.setRecipes(weekdays);*/

        List<MPList> mpL = mealPlan.getMpLists();

        mealPlanService.save(mealPlan);

        for (MPList mpList : mpL) {
           // MPList newList = MPListService.save(new MPList(mpList.getRecipe(), mealPlan));
        }

        long mpID = mealPlan.getMealPlanID();
        model.addAttribute("mealplan", mealPlanService.findByMealPlanID(mpID));

        // sama breyta og weekdays
        //model.addAttribute("recipesList", mealPlanService.findByMealPlanID(mpID).getRecipes());



        return "/confirm";
    }

}
