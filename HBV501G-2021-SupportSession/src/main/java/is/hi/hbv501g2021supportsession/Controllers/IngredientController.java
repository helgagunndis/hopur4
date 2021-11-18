package is.hi.hbv501g2021supportsession.Controllers;

import is.hi.hbv501g2021supportsession.Persistence.Entities.IngredientInfo;
import is.hi.hbv501g2021supportsession.Services.IngredientInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IngredientController {
    private IngredientInfoService infoIngredientService;

    @Autowired
    public IngredientController(IngredientInfoService infoIngredientService) {
        this.infoIngredientService = infoIngredientService;
    }

    @RequestMapping(value = "/adminIngredients")
    public String adminIngredientsPage(IngredientInfo ingredientInfo) {
       // model.addAttribute("ingredients", infoIngredientService.findAll());
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
            return "redirect:/error";
        }
        infoIngredientService.save(ingredientInfo);
        return "redirect:/admin";
    }

}
