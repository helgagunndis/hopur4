package is.hi.hbv501g2021supportsession.Services.Implementation;

import is.hi.hbv501g2021supportsession.Persistence.Entities.MealPlan;
import is.hi.hbv501g2021supportsession.Persistence.Entities.Recipe;
import is.hi.hbv501g2021supportsession.Persistence.Entities.User;
import is.hi.hbv501g2021supportsession.Persistence.Repositories.MealPlanRepository;
import is.hi.hbv501g2021supportsession.Persistence.Repositories.UserRepository;
import is.hi.hbv501g2021supportsession.Services.MealPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MealPlanServiceImplementation implements MealPlanService {
   private MealPlanRepository mealPlanRepository;


    @Autowired
    public MealPlanServiceImplementation(MealPlanRepository mealPlanRepository){
        this.mealPlanRepository = mealPlanRepository;
    }



    @Override
   public MealPlan save(MealPlan mealPlan) {
      return mealPlanRepository.save(mealPlan);
   }

   @Override
   public MealPlan findByMealPlanID(Long ID) {
      return mealPlanRepository.findByMealPlanID(ID);
   }

    @Override
    public List<MealPlan> findAll() {
        return mealPlanRepository.findAll();
    }

}
