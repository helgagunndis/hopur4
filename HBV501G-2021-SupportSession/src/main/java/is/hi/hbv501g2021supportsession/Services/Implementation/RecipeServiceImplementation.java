package is.hi.hbv501g2021supportsession.Services.Implementation;

import is.hi.hbv501g2021supportsession.Persistence.Entities.Recipe;
import is.hi.hbv501g2021supportsession.Persistence.Repositories.RecipeRepository;
import is.hi.hbv501g2021supportsession.Services.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RecipeServiceImplementation implements RecipeService {
    private RecipeRepository recipeRepository;

    @Autowired
    public RecipeServiceImplementation(RecipeRepository recipeRepository){
        this.recipeRepository = recipeRepository;
    }

    @Override
    public List<Recipe> findAll() {return recipeRepository.findAll();}

    @Override
    public Recipe save(Recipe recipe){
        return recipeRepository.save(recipe);
    }

    @Override
    public Recipe findByRecipeID(Long id) {
        long badRequest = (long) -1;
        if (id == badRequest){
            return null;
        }
        return recipeRepository.findByRecipeID(id);
    }

    /**
     * From chosen category find one random recipe.
     * @param category
     * @return Recipe recipe
     */
    @Override
    public Recipe findRandomRecipe(int category) {
        List<Recipe> recipeCategory = recipeRepository.findByRecipeCategoryLessThanEqual(category);
        int random =(int) (Math.random() * (recipeCategory.size()));
        return recipeCategory.get(random);
    }

    /**
     * From chosen category find recipe that has the ID randomNumber
     * @param category
     * @param randomNumber
     * @return Recipe recipe
     */
    @Override
    public Recipe findRecipe(int category, int randomNumber) {
        List<Recipe> recipeCategory = recipeRepository.findByRecipeCategoryLessThanEqual(category);
        return recipeCategory.get(randomNumber);
    }

    @Override
    public List<Recipe> findByRecipeCategoryLessThanEqual(int category){
        return recipeRepository.findByRecipeCategoryLessThanEqual(category);
    }


    /**
     * Makes a list of recipes that are generated with unique recipe ID
     * @param category
     * @return List<Recipe> weekdays
     */
    @Override
    public List<Recipe> findListOfRecipe(int category){
        List<Integer> list = new ArrayList<Integer>();
        List<Recipe>  weekdays = new ArrayList<Recipe>();
        List recipeCategory = findByRecipeCategoryLessThanEqual(category);

        for (int i=1; i < recipeCategory.size(); i++) {
            list.add(i);
        }
        Collections.shuffle(list);
        for (int i=0; i<7; i++) {
            weekdays.add(findRecipe(category, list.get(i)));
        }
        return weekdays;
    }
}
