package is.hi.hbv501g2021supportsession.Persistence.Repositories;

import is.hi.hbv501g2021supportsession.Persistence.Entities.MealPlan;
import is.hi.hbv501g2021supportsession.Persistence.Entities.Recipe;
import is.hi.hbv501g2021supportsession.Persistence.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MealPlanRepository /*extends JpaRepository<Recipe, Long> */{
    /*List<MealPlan> findAll(User user);
    MealPlan save(int numberOfDays,MealPlan mealPlan);
    Recipe findByCategory(int recipeCategory);
    Recipe findByTitle(String recipeTitle);*/
}
