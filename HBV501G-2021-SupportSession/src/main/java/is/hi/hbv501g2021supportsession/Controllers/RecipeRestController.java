package is.hi.hbv501g2021supportsession.Controllers;

import is.hi.hbv501g2021supportsession.Persistence.Entities.Ingredient;
import is.hi.hbv501g2021supportsession.Persistence.Entities.IngredientInfo;
import is.hi.hbv501g2021supportsession.Persistence.Entities.Recipe;
import is.hi.hbv501g2021supportsession.Persistence.Entities.User;
import is.hi.hbv501g2021supportsession.Services.IngredientInfoService;
import is.hi.hbv501g2021supportsession.Services.IngredientService;
import is.hi.hbv501g2021supportsession.Services.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * A controller class for adding, finding and saving recipes
 */
@RestController
public class RecipeRestController {
    private RecipeService recipeService;
    private IngredientInfoService infoIngredientService;
    private IngredientService ingredientService;

    @Autowired
    public RecipeRestController(RecipeService recipeService, IngredientInfoService infoIngredientService, IngredientService ingredientService) {
        this.recipeService = recipeService;
        this.infoIngredientService = infoIngredientService;
        this.ingredientService = ingredientService;
    }


   /* @RequestMapping(value = "/recipes",method = RequestMethod.GET, consumes = "application/json")
    @ResponseBody
    public List getRecipes() {
        List recipes = recipeService.findAll();
        return recipes;
    } */

    @GetMapping("/recipes")
    public List getAllReports() {
        List recipes = recipeService.findAll();
        return recipes;
    }


    /* @RequestMapping(value = "/admin") //, method = RequestMethod.GET)
    public String adminPage(Recipe recipe, Model model) {
        return "admin";
    }

    @RequestMapping(value = "/admin", params = {"save"})
    public String adminSave(Recipe recipe, Ingredient ingredients, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "redirect:/error";
        }
        recipeService.save(recipe);

        List<Ingredient> ingredientList = recipe.getIngredients();
        for (Ingredient ingredient : ingredientList) {
            Ingredient newIngredient = ingredientService.save(new Ingredient(ingredient.getAmount(), ingredient.getIngredientInfo(), recipe));
        }

        return "redirect:/recipes";
    }


    @RequestMapping(value = "/admin", params = {"addRow"})
    public String addRow(Recipe recipe, Ingredient ingredient, BindingResult bindingResult, Model model) {
        recipe.getIngredients().add(new Ingredient());
        return "admin";
    }*/

}
