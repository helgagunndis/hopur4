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

@Controller
public class RecipeController {
    private RecipeService recipeService;
    private IngredientService ingredientService;

    @Autowired
    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }


   @RequestMapping("/recipes")
    public String viewRecipes(Model model) {
        model.addAttribute("recipes", recipeService.findAll());
       return "recipes";
   }

   /* Admin aðgangur til að setja inn uppskriftir. Er að tékka að gagnagrunnurinn sé rétt tengdur*/
   @RequestMapping(value = "/admin")
    public String adminPage(Recipe recipe){
        return "admin";
        }

    @RequestMapping(value= "/admin", params ={"save"})
    public String adminSave(Recipe recipe, Ingredient ingredient, BindingResult result, Model model){
        if(result.hasErrors()){
            return "admin";
        }
        recipeService.save(recipe);
        return "redirect:/recipes";
    }

    @RequestMapping(value="/admin", params={"addRow"})
    public String addRow(Recipe recipe, Ingredient ingredient,  BindingResult bindingResult) {
        recipe.getIngredients().add(new Ingredient());
        return "admin";
    }

}
