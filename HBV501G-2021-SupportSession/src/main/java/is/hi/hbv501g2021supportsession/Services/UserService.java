package is.hi.hbv501g2021supportsession.Services;
import is.hi.hbv501g2021supportsession.Persistence.Entities.MealPlan;
import is.hi.hbv501g2021supportsession.Persistence.Entities.User;

import is.hi.hbv501g2021supportsession.Persistence.Entities.User;

import java.util.List;

public interface UserService {
    User save (User user);
    void delete(User user);
    List<User> findAll();
    User findByUsername(String username);
    User login(User user);
    //void saveMealPlan(MealPlan mealPlan, User user);
    List<MealPlan> ViewArchived(User user);
}

