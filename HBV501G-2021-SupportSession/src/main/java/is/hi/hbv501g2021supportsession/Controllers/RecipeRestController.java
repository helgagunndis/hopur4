package is.hi.hbv501g2021supportsession.Controllers;

import is.hi.hbv501g2021supportsession.Persistence.Entities.*;
import is.hi.hbv501g2021supportsession.Services.IngredientInfoService;
import is.hi.hbv501g2021supportsession.Services.IngredientService;
import is.hi.hbv501g2021supportsession.Services.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collections;
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

    @GetMapping("/rest/recipes")
    public List<Recipe> getAllReports() {
        List<Recipe> list= recipeService.findAll();

        return list;
    }


}
