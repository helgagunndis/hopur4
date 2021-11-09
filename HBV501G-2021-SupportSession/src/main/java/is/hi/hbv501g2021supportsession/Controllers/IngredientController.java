package is.hi.hbv501g2021supportsession.Controllers;

import is.hi.hbv501g2021supportsession.Persistence.Entities.IngredientInfo;
import is.hi.hbv501g2021supportsession.Persistence.Entities.Recipe;
import is.hi.hbv501g2021supportsession.Services.IngredientInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IngredientController {
    private IngredientInfoService ingredientService;

    @Autowired
    public IngredientController(IngredientInfoService ingredientService) {
        this.ingredientService = ingredientService;
    }

   /* @RequestMapping("/adminIngredients")
    public String viewIngredients(Model model) {
        model.addAttribute("ingredientName", ingredientService.findAll());
        return "adminIngredients";
    }*/

    //TODO ingredients hasn't successfully connected to db. needs fixing

    @RequestMapping(value = "/adminIngredients")
    public String adminIngredientsPage(IngredientInfo ingredientInfo) {
        return "adminIngredients";
    }

    /**
     * Saves ingredients, when successfully saved to db redirect to recipes.html
     * @param ingredientInfo
     * @param result
     * @param model
     * @return
     */
    @RequestMapping(value = "/adminIngredients", params = {"saveIngred"})
    public String adminSaveIngredients(IngredientInfo ingredientInfo, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "adminIngredients";
        }
        ingredientService.save(ingredientInfo);
        return "redirect:/admin";
    }
}
