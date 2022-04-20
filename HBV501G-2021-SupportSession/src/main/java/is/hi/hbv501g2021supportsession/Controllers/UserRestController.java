package is.hi.hbv501g2021supportsession.Controllers;

import com.fasterxml.jackson.databind.SerializationFeature;
import is.hi.hbv501g2021supportsession.Persistence.Entities.MealPlan;
import is.hi.hbv501g2021supportsession.Persistence.Entities.Recipe;
import is.hi.hbv501g2021supportsession.Persistence.Entities.User;
import is.hi.hbv501g2021supportsession.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * A controller class used for login and sign-up of the user.
 */
@RestController
public class UserRestController {
    UserService userService;

    @Autowired
    public UserRestController(UserService userService){
        this.userService=userService;
    }


    @RequestMapping(value = "/rest/signup", method = RequestMethod.POST, consumes = "application/json")
    public Object signupUser(@RequestBody User user , BindingResult result) {
        if (result.hasErrors()) {
            return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
        }
        User usernameExists = userService.findByUsername(user.getUsername());
        if(usernameExists != null){
            return new ResponseEntity<String>("this username is not available", HttpStatus.BAD_REQUEST);
        }
        userService.save(user);

        return ResponseEntity.ok(user);
    }
    @RequestMapping(value = "/rest/login",method = RequestMethod.POST, consumes = "application/json")
    public User userLogin(@RequestBody User user) {
        User exists = userService.login(user);
        if(exists != null){
            return user;
        }
        return null;
    }

    @GetMapping("/rest/user/mealplan")
    public List<MealPlan> userMealplan(@RequestParam(value = "username") String username) {
        User user= userService.findByUsername(username);
        List<MealPlan> mealPlanList = userService.ViewArchived(user);
        if(mealPlanList!=null){
            return mealPlanList;
        }
        MealPlan emptyMealPlan= new MealPlan();
        List<MealPlan> emptyMealPlanList = null;
        emptyMealPlanList.add(emptyMealPlan);
        return emptyMealPlanList;
    }
    @GetMapping("/rest/user/category")
    public User userCategory(@RequestParam(value = "username") String username, @RequestParam(value = "category") String category) {
        User user= userService.findByUsername(username);
        user.setUserCategory(category);
        userService.save(user);
        return user;
    }

    @GetMapping("/rest/user/user")
    public User findUser(@RequestParam(value = "username") String username) {
        User user= userService.findByUsername(username);
        return user;
    }

    


}
