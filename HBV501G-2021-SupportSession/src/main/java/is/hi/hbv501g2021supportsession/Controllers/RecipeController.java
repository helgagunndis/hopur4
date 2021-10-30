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
     *
     * @param model
     * @return recipe page
     */
   @RequestMapping("/recipes")
    public String viewRecipes(Model model) {
        model.addAttribute("recipes", recipeService.findAll());
       return "recipes";
   }

    /**
     *
     * @param recipe
     * @return admin page where admin can add recipes to recipe page
     */
   /* Admin aðgangur til að setja inn uppskriftir. Er að tékka að gagnagrunnurinn sé rétt tengdur*/
   @RequestMapping(value = "/admin")
    public String adminPage(Recipe recipe){
        return "admin";
        }

    /**
     *
     * @param recipe
     * @param ingredient
     * @param result
     * @param model
     * @return admin can save recipes to recipe page
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
     *
     * @param recipe
     * @param ingredient
     * @param bindingResult
     * @return admin can add rows for ingredients
     */
    @RequestMapping(value="/admin", params={"addRow"})
    public String addRow(Recipe recipe, Ingredient ingredient,  BindingResult bindingResult) {
        recipe.getIngredients().add(new Ingredient());
        return "admin";
    }

}
