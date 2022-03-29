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

}
