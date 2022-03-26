package is.hi.hbv501g2021supportsession.Controllers;

import is.hi.hbv501g2021supportsession.Persistence.Entities.MealPlan;
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

    @RequestMapping(value = "/rest/login",method = RequestMethod.POST, consumes = "application/json")
    public Object userLogin(@RequestBody User user, BindingResult result) {
        if (result.hasErrors()) {
            return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
        }
        User exists = userService.login(user);

        if(exists != null){
            List<MealPlan> mealPlanList = userService.ViewArchived(exists);
            user.setMealPlan(mealPlanList);
            return ResponseEntity.ok(user);
        }
        return new ResponseEntity<String>("this user dosen't have account",HttpStatus.BAD_REQUEST);
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
    


   /* @RequestMapping(value = "/loggedin", method = RequestMethod.GET)
    public String loggedinGET(HttpSession session, Model model){
        User sessionUser = (User) session.getAttribute("LoggedInUser");
        if(sessionUser  != null){
            model.addAttribute("LoggedInUser", sessionUser);
            List<MealPlan> mealPlanList = userService.ViewArchived(sessionUser);
            model.addAttribute("mealPlanList", mealPlanList);
            return "/LoggedInUser";
        }
        return "redirect:/login";
    }


    @RequestMapping(value = "/logout",method = RequestMethod.GET)
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return "redirect:/";
    }*/

}
