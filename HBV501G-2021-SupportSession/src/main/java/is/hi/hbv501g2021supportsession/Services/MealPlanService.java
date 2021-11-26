package is.hi.hbv501g2021supportsession.Services;

import is.hi.hbv501g2021supportsession.Persistence.Entities.MealPlan;
import java.util.List;

public interface MealPlanService {
    MealPlan save(MealPlan mealPlan);
    MealPlan findByMealPlanID(Long ID);
    List<MealPlan> findAll();
}
