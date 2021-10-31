package is.hi.hbv501g2021supportsession.Controllers;

import is.hi.hbv501g2021supportsession.Persistence.Entities.Ingredient;
import is.hi.hbv501g2021supportsession.Persistence.Entities.Recipe;
import is.hi.hbv501g2021supportsession.Services.IngredientService;
import is.hi.hbv501g2021supportsession.Services.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
/**
 * A controller class for adding, finding and saving recipes
 */
@Controller
public class RecipeController {


    private RecipeService recipeService;
    private IngredientService ingredientService;


    @Autowired
    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    /**
     * Adds all recipes in db to html
     * @param model
     * @return recipes.html
     */
   @RequestMapping("/recipes")
    public String viewRecipes(Model model) {
        model.addAttribute("recipes", recipeService.findAll());
       return "recipes";
   }

    /**
     * admin page - to add recipes into db
     * @param recipe
     * @return admin.html
     */
   @RequestMapping(value = "/admin")
    public String adminPage(Recipe recipe){
        return "admin";
    }

    /**
     * Saves ingredients, when successfully saved to db redirect to recipes.html
     * @param recipe
     * @param ingredient
     * @param result
     * @param model
     * @return
     */
    @RequestMapping(value= "/admin", params ={"save"})
    public String adminSave(Recipe recipe, Ingredient ingredient, BindingResult result, Model model){
        if(result.hasErrors()){
            return "admin";
        }
        recipeService.save(recipe);
        return "redirect:/recipes";
    }

    /**
     * Adds new row and adds ingredient to database
     * @param recipe
     * @param ingredient
     * @param bindingResult
     * @return admin.html
     */
    @RequestMapping(value="/admin", params={"addRow"})
    public String addRow(Recipe recipe, Ingredient ingredient,  BindingResult bindingResult) {
        recipe.getIngredients().add(new Ingredient());
        return "admin";
    }

    //TODO ingredients hasnt successfully connected to db. needs fixing

}
