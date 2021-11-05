package is.hi.hbv501g2021supportsession.Controllers;

import is.hi.hbv501g2021supportsession.Persistence.Entities.Ingredient;
import is.hi.hbv501g2021supportsession.Persistence.Entities.IngredientInfo;
import is.hi.hbv501g2021supportsession.Persistence.Entities.Recipe;
import is.hi.hbv501g2021supportsession.Services.IngredientInfoService;
import is.hi.hbv501g2021supportsession.Services.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * A controller class for adding, finding and saving recipes
 */
@Controller
public class RecipeController {


    private RecipeService recipeService;
    private IngredientInfoService ingredientService;


    @Autowired
    public RecipeController(RecipeService recipeService,IngredientInfoService ingredientService) {
        this.recipeService = recipeService;
        this.ingredientService = ingredientService;
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
       List<IngredientInfo> allIngredients = ingredientService.findAll();
       model.addAttribute("allIngredients", allIngredients);
       System.out.println("hér");
       return "admin";
    }

    /**
     * Saves recipes, when successfully saved to db redirect to recipes.html
     * @param recipe
     * @param result
     * @param model
     * @return
     */
    @RequestMapping(value= "/admin", params = {"save"}) //, method = RequestMethod.POST)
    public String adminSave(Recipe recipe, IngredientInfo ingredientInfo, BindingResult result, Model model){
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
   @RequestMapping(value="/admin", params={"addRow"})// method = RequestMethod.GET)
    public String addRow(Recipe recipe, Ingredient ingredient,  BindingResult bindingResult, Model model) {
       // List<IngredientInfo> allIngredients = ingredientService.findAll();
       // model.addAttribute("allIngredients", allIngredients);
        System.out.println("ingredients");
        recipe.getIngredients().add(new Ingredient());

        return "admin";
    }



}
