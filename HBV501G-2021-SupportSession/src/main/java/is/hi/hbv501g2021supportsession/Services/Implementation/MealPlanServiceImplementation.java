package is.hi.hbv501g2021supportsession.Services.Implementation;

import is.hi.hbv501g2021supportsession.Persistence.Entities.MealPlan;
import is.hi.hbv501g2021supportsession.Persistence.Entities.Recipe;
import is.hi.hbv501g2021supportsession.Persistence.Entities.User;
import is.hi.hbv501g2021supportsession.Persistence.Repositories.MealPlanRepository;
import is.hi.hbv501g2021supportsession.Services.MealPlanService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class MealPlanServiceImplementation implements MealPlanService {
   /* private MealPlanRepository mealPlanRepository;

    @Override
    public List<MealPlan> findAll(User user) {
        return null;
    }

    @Override
    public MealPlan save(int numberOfDays, MealPlan mealPlan) {
        return null;
    }

    @Override
    public Recipe findByCategory(int recipeCategory) {
        return null;
    }

    @Override
    public Recipe findByTitle(String recipeTitle) {
        return null;
    }

    /**@Autowired
    public MealPlanServiceImplementation(MealPlanRepository mealPlanRepository){
        this.mealPlanRepository = mealPlanRepository;
    }

    @Override
    public List<MealPlan> findAll(User user) {
        return mealPlanRepository.findAll(user);
    }

    @Override
    public MealPlan save(int numberOfDays, MealPlan mealPlan) {
        return mealPlanRepository.save(numberOfDays,mealPlan);
    }

    @Override
    public Recipe findByCategory(int findByCategory) {
        return mealPlanRepository.findByCategory(findByCategory);
    }
    @Override
    public Recipe findByTitle(String recipeTitle) {
        return mealPlanRepository.findByTitle(recipeTitle);
    }
    */
}
