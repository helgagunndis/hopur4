package is.hi.hbv501g2021supportsession.Services.Implementation;

import is.hi.hbv501g2021supportsession.Persistence.Entities.MealPlan;
import is.hi.hbv501g2021supportsession.Persistence.Entities.User;
import is.hi.hbv501g2021supportsession.Persistence.Repositories.MealPlanRepository;
import is.hi.hbv501g2021supportsession.Persistence.Repositories.UserRepository;
import is.hi.hbv501g2021supportsession.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImplementation implements UserService {
    UserRepository userRepository;
    MealPlanRepository mealPlanRepository;
    
    @Autowired
    private UserServiceImplementation (UserRepository userRepository,MealPlanRepository mealPlanRepository){
        this.userRepository=userRepository;
        this.mealPlanRepository=mealPlanRepository;
    }
    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public void delete(User user) {userRepository.delete(user);}

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User login(User user) {
        User doesExist = findByUsername(user.getUsername());
        if(doesExist != null){
            if(doesExist.getUserPassword().equals(user.getUserPassword())){
                return doesExist;
            }
        }
        return null;
    }

    @Override
    public List<MealPlan> ViewArchived(User user) {
        List<MealPlan> list = mealPlanRepository.findMealPlanByUserID(user.getID());
        return list;
    }

    /*@Override
    public void saveMealPlan(MealPlan mealPlan, User user) {
        user.addToMealPlan(mealPlan);
        return;
    }*/

}
