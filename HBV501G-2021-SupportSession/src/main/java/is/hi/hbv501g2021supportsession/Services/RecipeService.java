package is.hi.hbv501g2021supportsession.Services;

import is.hi.hbv501g2021supportsession.Persistence.Entities.Ingredient;
import is.hi.hbv501g2021supportsession.Persistence.Entities.Recipe;

import java.util.List;
import java.util.Optional;

public interface RecipeService {
    List<Recipe> findAll();
    Recipe save(Recipe recipe);
   // void add(Ingredient ingredient);
    Recipe findByRecipeID(Long id);
    List<Recipe> findByRecipeCategory(int category);

}
