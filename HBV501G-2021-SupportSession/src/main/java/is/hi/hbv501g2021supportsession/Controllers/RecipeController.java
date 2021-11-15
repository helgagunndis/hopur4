package is.hi.hbv501g2021supportsession.Controllers;

import is.hi.hbv501g2021supportsession.Persistence.Entities.Ingredient;
import is.hi.hbv501g2021supportsession.Persistence.Entities.IngredientInfo;
import is.hi.hbv501g2021supportsession.Persistence.Entities.Recipe;
import is.hi.hbv501g2021supportsession.Services.IngredientInfoService;
import is.hi.hbv501g2021supportsession.Services.IngredientService;
import is.hi.hbv501g2021supportsession.Services.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * A controller class for adding, finding and saving recipes
 */
@Controller
public class RecipeController {


    private RecipeService recipeService;
    private IngredientInfoService infoIngredientService;
    private IngredientService ingredientService;

    @Autowired
    public RecipeController(RecipeService recipeService,IngredientInfoService infoIngredientService, IngredientService ingredientService) {
        this.recipeService = recipeService;
        this.infoIngredientService = infoIngredientService;
        this.ingredientService = ingredientService;
    }

    @ModelAttribute("allIngredients")
    public List<IngredientInfo> populateIngredients() {
        return this.infoIngredientService.findAll();
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
   @RequestMapping(value = "/admin") //, method = RequestMethod.GET)
    public String adminPage( Recipe recipe, Model model){
       /*List<IngredientInfo> allIngredients = ingredientService.findAll();
       model.addAttribute("allIngredients", allIngredients);*/

       return "admin";
    }

   /* @RequestMapping(value="/admin", method=RequestMethod.POST)
    public String adminSave(Recipe recipe, IngredientInfo ingredientInfo, BindingResult result, Model model) {
        if(result.hasErrors()){
            return "admin";
        }
        model.addAttribute("recipes", recipeService.findAll());

        recipeService.save(recipe);
        return "redirect:/recipes";
    }*/

    @RequestMapping(value= "/admin", params ={"save"})
    public String adminSave(Recipe recipe, Ingredient ingredients, BindingResult result, Model model){
        if(result.hasErrors()){
            return "redirect:/error";
        }
        ingredientService.save(ingredients);

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
    public String addRow(Recipe recipe, Ingredient ingredient,  BindingResult bindingResult, Model model) {
        recipe.getIngredients().add(new Ingredient());

        return "admin";
    }


}
