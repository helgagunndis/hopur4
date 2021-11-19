package is.hi.hbv501g2021supportsession.Services;

import is.hi.hbv501g2021supportsession.Persistence.Entities.MealPlan;
import is.hi.hbv501g2021supportsession.Persistence.Entities.Recipe;
import is.hi.hbv501g2021supportsession.Persistence.Entities.User;

import java.util.List;
import java.util.Optional;

public interface MealPlanService {
    MealPlan save(MealPlan mealPlan);
    MealPlan findByMealPlanID(Long ID);

}
