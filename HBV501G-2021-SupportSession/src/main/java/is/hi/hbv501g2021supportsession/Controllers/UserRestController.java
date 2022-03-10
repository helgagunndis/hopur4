package is.hi.hbv501g2021supportsession.Controllers;

import is.hi.hbv501g2021supportsession.Persistence.Entities.MealPlan;
import is.hi.hbv501g2021supportsession.Persistence.Entities.User;
import is.hi.hbv501g2021supportsession.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @RequestMapping(value = "/login",method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public User userLogin(@RequestBody User user) {
        User exists = userService.login(user);
        if(exists != null){
            return user;
        }
        return null;
    }

    @RequestMapping(value = "/signup",method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public User userSignup(@RequestBody User user) {
        User usernameExists = userService.findByUsername(user.getUsername());
        if(usernameExists != null){
            return null;
        }
        userService.save(user);
        return user;
    }


    /*@RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginGET(User user, HttpSession session, Model model){
        User sessionUser = (User) session.getAttribute("LoggedInUser");
        if(sessionUser  != null){
            model.addAttribute("LoggedInUser", sessionUser);
            return "LoggedInUser";
        }
        return "/login";
    }*/


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
