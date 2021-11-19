package is.hi.hbv501g2021supportsession.Services;

import is.hi.hbv501g2021supportsession.Persistence.Entities.Recipe;

import java.util.ArrayList;
import java.util.List;

public interface RecipeService {
    List<Recipe> findAll();
    Recipe save(Recipe recipe);
   // void add(Ingredient ingredient);
    Recipe findByRecipeID(Long id);
    Recipe findRandomRecipe(int category);
    Recipe findRecipe(int category, int randomNumber);
    ArrayList<Recipe> findListOfRecipe(int category);
    List<Recipe> findByRecipeCategoryLessThanEqual(int category);


}
