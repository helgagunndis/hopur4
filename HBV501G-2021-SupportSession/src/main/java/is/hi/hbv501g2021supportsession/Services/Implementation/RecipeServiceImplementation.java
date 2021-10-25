package is.hi.hbv501g2021supportsession.Services.Implementation;

import is.hi.hbv501g2021supportsession.Persistence.Entities.Ingredients;
import is.hi.hbv501g2021supportsession.Persistence.Entities.Recipe;
import is.hi.hbv501g2021supportsession.Persistence.Repositories.RecipeRepository;
import is.hi.hbv501g2021supportsession.Services.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
    public Ingredients save(Ingredients ingredients){
        return recipeRepository.save(ingredients);
    }
}
