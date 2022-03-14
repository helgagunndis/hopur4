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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * A controller class for meal plan
 */
@Controller
public class MealPlanRestController {
    RecipeService recipeService;
    MealPlanService mealPlanService;
    UserService userService;
    MPListService mpListService;

    private int Category = 4;
    private List<Recipe> weekdays; //Monday, Tuesday ...
    private List<String> weekdaysName = new ArrayList<>(List.of("Mánudagur", "Þriðjudagur", "Miðvikudagur","Fimmtudagur","Föstudagur","Laugardagur","Sunnudagur"));
    private List <Boolean> weekdaysCheckbox= new ArrayList<>(List.of(true,true,true,true,true,true,true));

    @Autowired
    public MealPlanRestController(MealPlanService mealPlanService , RecipeService recipeService, MPListService mpListService, UserService userService){
        this.mealPlanService = mealPlanService;
        this.recipeService = recipeService;
        this.userService =userService;
        this.mpListService = mpListService;
    }


    @GetMapping("/mealplan")
    public List mealplan() {
        List recipeCategory = recipeService.findByRecipeCategoryLessThanEqual(Category);
        //List<Recipe> weekdays = recipeService.findListOfRecipe(Category);
        return recipeCategory;
    }

    /* @RequestMapping(value = "/" , method = RequestMethod.GET)
    public String mealplan(Model model) {
        List recipeCategory = recipeService.findByRecipeCategoryLessThanEqual(Category);
        model.addAttribute("categoryRecipe", recipeCategory);

        if(weekdays==null){
            weekdays = new ArrayList<Recipe>();
            weekdays = recipeService.findListOfRecipe(Category);
        }

        for (int i = 0; i <7; i++) {
            if(weekdaysCheckbox.get(i)==false){
                weekdays.set(i,null);
            }
        }
        model.addAttribute("weekdaysRecipes",weekdays);
        model.addAttribute("weekdaysName",weekdaysName);
        model.addAttribute("weekdaysCheckbox",weekdaysCheckbox);

        return "mealplan";
    }



    @RequestMapping(value = "/category" , method = RequestMethod.GET)
    public String recipeListGET(Recipe recipe){
        Category = recipe.getRecipeCategory();
        weekdays = null;
        return "redirect:/";
    }



    @RequestMapping(value = "/manualRecipes",method = RequestMethod.GET)
    public String manualRecipes(Recipe recipe, MealPlan mealplan){
        int weekday = mealplan.getNumberOfWeekDay();
        Recipe newRecipe = recipeService.findByRecipeID(recipe.getRecipeID());
        weekdays.set(weekday,newRecipe);
        return "redirect:/";
    }


    @RequestMapping(value = "/generateOneMeal",method = RequestMethod.GET)
    public String generateOneMeal(MealPlan mealplan){
        int weekday = mealplan.getNumberOfWeekDay();
        Recipe newRecipe = recipeService.findRandomRecipe(Category);
        weekdays.set(weekday,newRecipe);
        return "redirect:/";
    }


    @RequestMapping(value = "/generateWholeWeek",method = RequestMethod.GET)
    public String generateWholeWeek(){
        weekdays= null;
        return "redirect:/";
    }


    @RequestMapping(value = "/getCheckboxinfo",method = RequestMethod.GET)
    public String getCheckboxinfo(MealPlan mealplan){
        int weekday = mealplan.getNumberOfWeekDay();
        boolean isture =weekdaysCheckbox.get(weekday);
        if(isture){
            weekdaysCheckbox.set(weekday,false);
        }
        else {
            weekdaysCheckbox.set(weekday,true);
        }
        return "redirect:/";
    }



    @RequestMapping(value = "/confirm",method = RequestMethod.GET)
    public String createMealPlan(Model model,HttpSession session, MealPlan mealplan) {
        User sessionUser = (User) session.getAttribute("LoggedInUser");
        model.addAttribute("LoggedInUser", sessionUser);
        if (sessionUser != null) {
            mealplan.setUser(sessionUser);
        }
        mealplan.setRecipeCategory(Category);
        mealPlanService.save(mealplan);


        List<MPList> mpLists=new ArrayList<MPList>();
        for (int i = 0; i <7; i++) {
            if(weekdaysCheckbox.get(i)==false){
                MPList list = new MPList(null, mealplan);
                mpLists.add(list);
            }
            else {
                MPList list = new MPList(weekdays.get(i), mealplan);
                mpListService.save(list);
                mpLists.add(list);
            }
        }
        mealplan.setMpLists(mpLists);

        List recipesList =mealPlanService.findByMealPlanID(mealplan.getMealPlanID()).getMpLists();
        model.addAttribute("recipesList", recipesList);
        model.addAttribute("weekdaysName",weekdaysName);
        return "/confirm";
    } */

}
