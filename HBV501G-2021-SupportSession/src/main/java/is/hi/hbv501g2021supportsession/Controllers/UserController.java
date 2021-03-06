package is.hi.hbv501g2021supportsession.Controllers;

import is.hi.hbv501g2021supportsession.Persistence.Entities.MealPlan;
import is.hi.hbv501g2021supportsession.Persistence.Entities.User;
import is.hi.hbv501g2021supportsession.Services.MealPlanService;
import is.hi.hbv501g2021supportsession.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * A controller class used for login and sign-up of the user.
 */
@Controller
public class UserController {
    UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService=userService;
    }


    /**
     * Get info about user signing up
     * @param user
     * @return signup.html
     */
    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public String signupGET(User user){ return "/signup";}

    /**
     * Checks if errors in signup, is already in DB or new user
     * @param user
     * @param result
     * @param model
     * @return signup.html
     */
    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public String signupPOST(User user, BindingResult result, Model model){
        if(result.hasErrors()){
            return "redirect:/error";
        }
        User usernameExists = userService.findByUsername(user.getUsername());
        if(usernameExists != null){
            model.addAttribute("usernameExists",usernameExists);
            return "/signup";
        }
        userService.save(user);
        return "redirect:/login";
    }

    /**
     * Get login only if the user isn't login
     * @param user
     * @param session
     * @param model
     * @return login.html
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginGET(User user, HttpSession session, Model model){
        User sessionUser = (User) session.getAttribute("LoggedInUser");
        if(sessionUser  != null){
            model.addAttribute("LoggedInUser", sessionUser);
            return "LoggedInUser";
        }
        return "/login";
    }

    /**
     * Finds errors in login and checks if user exists in db
     * and add all mealplans that he has.
     * @param user
     * @param result
     * @param model
     * @param session
     * @return LoggedInUser.html
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String loginPOST(User user, BindingResult result, Model model, HttpSession session){
        if(result.hasErrors()){
            return "redirect:/error";
        }
        User exists = userService.login(user);
        if(exists != null){
            session.setAttribute("LoggedInUser", exists);
            model.addAttribute("LoggedInUser", exists);
            List<MealPlan> mealPlanList = userService.ViewArchived(exists);
            model.addAttribute("mealPlanList", mealPlanList);
            return "/LoggedInUser";
        }
        return "login";
    }

    /**
     * Check if user is logged in and add all mealsplans that this
     * user has.
     * @param model
     * @param session
     * @return LoggedInUser.html
     */
    @RequestMapping(value = "/loggedin", method = RequestMethod.GET)
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

    /**
     * user log out
     * @param request
     * @return mealplan.html
     */
    @RequestMapping(value = "/logout",method = RequestMethod.GET)
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return "redirect:/";
    }

    /**
     * frequently asked questions
     * @return fag.html
     */
    @RequestMapping(value = "/faq",method = RequestMethod.GET)
    public String fag() {
        return "/faq";
    }

}
