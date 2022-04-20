package is.hi.hbv501g2021supportsession.Controllers;

import is.hi.hbv501g2021supportsession.Persistence.Entities.MPList;
import is.hi.hbv501g2021supportsession.Persistence.Entities.MealPlan;
import is.hi.hbv501g2021supportsession.Persistence.Entities.Recipe;
import is.hi.hbv501g2021supportsession.Persistence.Entities.User;
import is.hi.hbv501g2021supportsession.Services.MPListService;
import is.hi.hbv501g2021supportsession.Services.MealPlanService;
import is.hi.hbv501g2021supportsession.Services.RecipeService;
import is.hi.hbv501g2021supportsession.Services.UserService;
import javassist.expr.NewArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A controller class for meal plan
 */
@RestController
public class MealPlanRestController {
    RecipeService recipeService;
    MealPlanService mealPlanService;
    UserService userService;
    MPListService mpListService;

    @Autowired
    public MealPlanRestController(MealPlanService mealPlanService , RecipeService recipeService, MPListService mpListService, UserService userService){
        this.mealPlanService = mealPlanService;
        this.recipeService = recipeService;
        this.userService =userService;
        this.mpListService = mpListService;
    }

    @GetMapping("/rest/mealplan")
    public List<Recipe> getMealPlan(@RequestParam(value = "recipeCategory") int recipeCategory, @RequestParam(value = "numberOfWeekDay") int numberOfWeekDay) {
        List<Recipe> recipesList = recipeService.findByRecipeCategoryLessThanEqual(recipeCategory);
        List<Integer> list = new ArrayList<Integer>();
        List<Recipe>  weekdays = new ArrayList<Recipe>();

        for (int i=1; i < recipesList.size(); i++) {
            list.add(i);
        }
        Collections.shuffle(list);
        for (int i=0; i< numberOfWeekDay; i++) {
            weekdays.add(recipeService.findRecipe(recipeCategory, list.get(i)));
        }

        return weekdays;
    }

    /**
     * Skíta mix til þess að fá þetta til að virka, var ekki hægt að senda inn list Recipe
     * með því að breyta því í string og svo aftur í list recipe þegar tekið er við requesti.
     *
     * @param username
     * @param recipe0
     * @param recipe1
     * @param recipe2
     * @param recipe3
     * @param recipe4
     * @param recipe5
     * @param recipe6
     * @return
     */
    @GetMapping("/rest/mealplan/confirm")
    public MealPlan saveMealPlan(
            @RequestParam(value = "username") String username,
            @RequestParam(value = "recipe0") long recipe0,
            @RequestParam(value = "recipe1") long recipe1,
            @RequestParam(value = "recipe2") long recipe2,
            @RequestParam(value = "recipe3") long recipe3,
            @RequestParam(value = "recipe4") long recipe4,
            @RequestParam(value = "recipe5") long recipe5,
            @RequestParam(value = "recipe6") long recipe6,
            @RequestParam(value = "recipe7") long recipe7
    ) {
        System.out.println("Fyrsta línan"+ recipe6);
        MealPlan mealPlan = new MealPlan();
        User user= userService.findByUsername(username);

        // setja user á mealPlan
        mealPlan.setUser(user);
        mealPlan.setRecipeCategory(Integer.parseInt(user.getUserCategory()));
        mealPlanService.save(mealPlan);

        List<Recipe> listRecipe = new ArrayList<Recipe>();
        listRecipe.add(recipeService.findByRecipeID(recipe0));
        listRecipe.add(recipeService.findByRecipeID(recipe1));
        listRecipe.add(recipeService.findByRecipeID(recipe2));
        listRecipe.add(recipeService.findByRecipeID(recipe3));
        listRecipe.add(recipeService.findByRecipeID(recipe4));
        listRecipe.add(recipeService.findByRecipeID(recipe5));
        listRecipe.add(recipeService.findByRecipeID(recipe6));
        listRecipe.add(recipeService.findByRecipeID(recipe7));

        List<MPList> mpLists=new ArrayList<MPList>();
        for (int i = 0; i <7; i++) {
            System.out.println(listRecipe.get(i));
            if(listRecipe.get(i)==null){
                MPList list = new MPList(null, mealPlan);
                mpLists.add(list);
            }
            else {
                MPList list = new MPList(listRecipe.get(i), mealPlan);
                mpListService.save(list);
                mpLists.add(list);
            }
        }

        mealPlan.setMpLists(mpLists);

        return mealPlan;
    }

}
