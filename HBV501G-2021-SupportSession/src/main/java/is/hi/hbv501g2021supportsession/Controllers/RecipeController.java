package is.hi.hbv501g2021supportsession.Controllers;

import is.hi.hbv501g2021supportsession.Persistence.Entities.Recipe;
import is.hi.hbv501g2021supportsession.Services.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class RecipeController {
    private RecipeService recipeService;

    @Autowired
    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

   @RequestMapping("/recipes")
    public String viewRecipes(Model model) {
        model.addAttribute("recipes", recipeService.findAll());
       return "recipes";
   }
   //TODO tegja ingredients við recipes


   /* Admin aðgangur til að setja inn uppskriftir. Er að tékka að gagnagrunnurinn sé rétt tengdur*/
   @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String adminPage(Recipe recipe){
        return "admin";
        }

    @RequestMapping(value= "/admin", method = RequestMethod.POST)
    public String adminSave(Recipe recipe, BindingResult result, Model model){
        if(result.hasErrors()){
            return "admin";
        }
        recipeService.save(recipe);
        return "redirect:/recipes";
    }





}
