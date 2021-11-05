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

    @Autowired
    public MealPlanController(MealPlanService mealPlanService , RecipeService recipeService){
        this.mealPlanService = mealPlanService;
        this.recipeService = recipeService;
        this.userService = userService;
    }


    @RequestMapping(value = "/" , method = RequestMethod.GET)
    public String mealplan(Model model, MealPlan mealPlan, User user, HttpSession session){
        // er userinn logaður inni ?
        User sessionUser = (User) session.getAttribute("LoggedInUser");
        if(sessionUser != null){
            System.out.println("þú er loggaður inni");
            User mealPlanE =mealPlan.getUser();
            System.out.println(mealPlanE);
            if(mealPlanE==null){
                mealPlanService.save(mealPlan);
                mealPlan.setUser(sessionUser);
                User n =mealPlan.getUser();
                System.out.println(n.getUsername());
            }
            else {
                System.out.println("þú átt nú þegar mealplan");
                model.addAttribute("mealPlanExists",mealPlanE);
            }
        }
        //ef mealplanið er ekki með ákveðið category þá er 4 default
        int category=mealPlan.getRecipeCategory();
        if(category==0){
            mealPlan.setRecipeCategory(4);
            List recipeCategory = recipeService.findAll();
            model.addAttribute("categoryRecipe", recipeCategory);
        }

        return "mealplan";
    }

    @RequestMapping(value = "/gategory" , method = RequestMethod.GET)
    public String recipeListGET(Model model, Recipe recipe, MealPlan mealPlan){
        int category =recipe.getRecipeCategory();
        mealPlan.setRecipeCategory(category);
        List recipeCategory;
        switch(category) {
            case 3:
                recipeCategory = recipeService.findByRecipeCategory(3);
                recipeCategory.addAll(recipeService.findByRecipeCategory(2));
                recipeCategory.addAll(recipeService.findByRecipeCategory(1));
                break;
            case 2:
                recipeCategory = recipeService.findByRecipeCategory(2);
                recipeCategory.addAll(recipeService.findByRecipeCategory(1));
                break;
            case 1:
                recipeCategory = recipeService.findByRecipeCategory(1);
                break;
            default:
                recipeCategory = recipeService.findAll();
        }
        model.addAttribute("categoryRecipe", recipeCategory);
        return "mealplan";
    }
    @RequestMapping(value = "/chooseRecipe",method = RequestMethod.GET)
    public String chooseRecipeGET(Model model, Recipe recipe){
        Recipe thatRecipe=recipeService.findByRecipeID(recipe.getRecipeID());
        System.out.println(thatRecipe.getRecipeID());
        return "redirect:/";
    }



    //@RequestMapping(value = "/loggedin")


}
