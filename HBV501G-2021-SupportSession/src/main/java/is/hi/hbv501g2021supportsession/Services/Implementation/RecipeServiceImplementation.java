package is.hi.hbv501g2021supportsession.Services.Implementation;

import is.hi.hbv501g2021supportsession.Persistence.Entities.Ingredient;
import is.hi.hbv501g2021supportsession.Persistence.Entities.Recipe;
import is.hi.hbv501g2021supportsession.Persistence.Repositories.RecipeRepository;
import is.hi.hbv501g2021supportsession.Services.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RecipeServiceImplementation implements RecipeService {
    private RecipeRepository recipeRepository;

    @Autowired
    public RecipeServiceImplementation(RecipeRepository recipeRepository){
        this.recipeRepository = recipeRepository;
    }


    @Override
    public List<Recipe> findAll() {
        return recipeRepository.findAll();

    }
    @Override
        public Recipe save(Recipe recipe){
        return recipeRepository.save(recipe);
    }

    @Override
    public Optional<Recipe> findByID(Recipe recipe) {
        return recipeRepository.findById(recipe.getRecipeID());
    }

}
