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
import org.thymeleaf.expression.Lists;

import javax.servlet.http.HttpSession;
import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * A controller class for meal plan
 */
@Controller
public class MealPlanController {
    RecipeService recipeService;
    MealPlanService mealPlanService;
    UserService userService;
    MPListService mpListService;

    private int Category = 4;
    private List<Recipe> weekdays; //Monday, Tuesday ...
    private List<String> weekdaysName = new ArrayList<>(List.of("Mánudagur", "Þriðjudagur", "Miðvikudagur","Fimmtudagur","Föstudagur","Laugardagur","Sunnudagur"));
    private List <Boolean> weekdaysCheckbox= new ArrayList<>(List.of(true,true,true,true,true,true,true));

    @Autowired
    public MealPlanController(MealPlanService mealPlanService , RecipeService recipeService,MPListService mpListService, UserService userService){
        this.mealPlanService = mealPlanService;
        this.recipeService = recipeService;
        this.userService =userService;
        this.mpListService = mpListService;
    }

    // notar valið category til þess að finna hvaða uppskriftir eru mögulegar
    @RequestMapping(value = "/" , method = RequestMethod.GET)
    public String mealplan(Model model, HttpSession session) {
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

    // Velur category
    @RequestMapping(value = "/category" , method = RequestMethod.GET)
    public String recipeListGET(Recipe recipe){
        Category = recipe.getRecipeCategory();
        weekdays = null;
        return "redirect:/";
    }

    // random recipe
    @RequestMapping(value = "/manualRecipes",method = RequestMethod.GET)
    public String manualRecipes(Recipe recipe, MealPlan mealplan){
        int weekday = mealplan.getNumberOfWeekDay();
        Recipe newRecipe = recipeService.findByRecipeID(recipe.getRecipeID());
        weekdays.set(weekday,newRecipe);
        return "redirect:/";
    }

    // fær nýja uppskrift og setur inn í listan á réttan stað miðað við valdan dag
    @RequestMapping(value = "/generateOneMeal",method = RequestMethod.GET)
    public String generateOneMeal(MealPlan mealPlan){
        int weekday = mealPlan.getNumberOfWeekDay();
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
    public String getCheckboxinfo(MealPlan mealPlan){
        int weekday = mealPlan.getNumberOfWeekDay();
        boolean isture =weekdaysCheckbox.get(weekday);
        if(isture){
            weekdaysCheckbox.set(weekday,false);
        }
        else {
            weekdaysCheckbox.set(weekday,true);
        }

        return "redirect:/";
    }


    //confirm page
    @RequestMapping(value = "/confirm",method = RequestMethod.GET)
    public String createMealPlan(Model model,HttpSession session, MealPlan mealPlan) {
        User sessionUser = (User) session.getAttribute("LoggedInUser");
        model.addAttribute("LoggedInUser", sessionUser);
        if (sessionUser != null) {
            mealPlan.setUser(sessionUser);
        }
        mealPlan.setRecipeCategory(Category);

        //vistar mealplan en ekki recipes
        mealPlanService.save(mealPlan);
        List<MPList> mpLists=new ArrayList<MPList>();

        for (int i = 0; i <7; i++) {
            //vistar hverja uppskrift saman við mealplan í MPList gagnagrunninn
            //villa hér? vistast rétt í gagnagrunni en null gildi þegar debuggað

            if(weekdaysCheckbox.get(i)==false){
                MPList list = new MPList(null, mealPlan);
                mpLists.add(list);
            }
            else {
                MPList list = new MPList(weekdays.get(i), mealPlan);
                mpListService.save(list);
                mpLists.add(list);
            }
        }
        mealPlan.setMpLists(mpLists);

        List recipesList =mealPlanService.findByMealPlanID(mealPlan.getMealPlanID()).getMpLists();
        // Sækir allar uppskriftirnar sem eru í mealplan
        model.addAttribute("recipesList", recipesList);
        model.addAttribute("weekdaysName",weekdaysName);
        return "/confirm";
    }

}
