package is.hi.hbv501g2021supportsession.Persistence.Repositories;

import is.hi.hbv501g2021supportsession.Persistence.Entities.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {
    List<Recipe> findAll();
    Recipe save(Recipe recipe);
    void delete(Recipe recipe);
    Recipe findByRecipeID(Long id);
    List<Recipe> findByRecipeCategoryLessThanEqual(int category);

}
