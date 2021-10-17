package is.hi.hbv501g2021supportsession.Services.Implementation;

import is.hi.hbv501g2021supportsession.Persistence.Entities.Recipe;
import is.hi.hbv501g2021supportsession.Services.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RecipeServiceImplementation implements RecipeService {
    private List<Recipe> recipeRepository = new ArrayList<>();
    private int id_counter = 0;

    @Autowired
    public RecipeServiceImplementation(){
        //dummy repo - to be removed later
        recipeRepository.add(new Recipe("Title", 1, "recipe method", "recipe time", 4));
        recipeRepository.add(new Recipe("Title", 1, "recipe method", "recipe time", 4));
        for(Recipe r: recipeRepository){
            r.setRecipeID(id_counter);
            id_counter++;
        }
    }

    @Override
    public List<Recipe> viewAll(){
        return recipeRepository;
    }

}
