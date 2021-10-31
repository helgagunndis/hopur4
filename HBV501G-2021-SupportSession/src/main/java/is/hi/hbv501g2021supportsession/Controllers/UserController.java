package is.hi.hbv501g2021supportsession.Controllers;

import is.hi.hbv501g2021supportsession.Persistence.Entities.User;
import is.hi.hbv501g2021supportsession.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * A controller class used for login and sign-in of the user.
 */
@Controller
public class UserController {


    UserService userService;
    private Object model;

    @Autowired
    public UserController(UserService userService){
        this.userService=userService;
    }

    //TODO
    //End points to add
    // signup (GET, POST)
    // login (GET, POST)
    // loggedin (GET)

    /**
     *
     * @param user
     * @return signup page where user can sign up for account
     */
    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public String signupGET(User user){
        return "signup";
    }

    /**
     *
     * @param user
     * @param result
     * @param model
     * @return
     */
    @RequestMapping(value = "/signup", method = RequestMethod.POST)

    public String signupPOST(User user, BindingResult result, Model model){
        if(result.hasErrors()){
            return "redirect:/signup";
        }
        User usernameExists = userService.findByUsername(user.getUsername());
        if(usernameExists != null){
            // When user is already in database
            model.addAttribute("usernameExists",usernameExists);
            return "/signup";
        }
        //TODO What to do when user is already in the database?
        userService.save(user);
        // If it's able to make new user
        return "redirect:/";
    }

    /**
     *
     * @param user
     * @param session
     * @param model
     * @return if user is logged in, then return the users homepage
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginGET(User user, HttpSession session, Model model){
        User sessionUser = (User) session.getAttribute("LoggedInUser");
        if(sessionUser  != null){
            model.addAttribute("LoggedInUser", sessionUser);
            // If user is logged in then go to my website.
            return "LoggedInUser";
        }
        return "/login";
    }

    /**
     *
     * @param user
     * @param result
     * @param model
     * @param session
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String loginPOST(User user, BindingResult result, Model model, HttpSession session){
        if(result.hasErrors()){
            return "login";
        }
        User exists = userService.login(user);
        if(exists != null){
            session.setAttribute("LoggedInUser", exists);
            model.addAttribute("LoggedInUser", exists);
            return "LoggedInUser";
        }
        return "redirect:/";
    }

    /**
     *
     * @param session
     * @param model
     * @return
     */
    @RequestMapping(value = "/loggedin", method = RequestMethod.GET)
    public String loggedinGET(HttpSession session, Model model){
        User sessionUser = (User) session.getAttribute("LoggedInUser");
        if(sessionUser  != null){
            model.addAttribute("LoggedInUser", sessionUser);
            return "LoggedInUser";
        }
        return "redirect:/login";
    }

    /**
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return "redirect:/";
    }
}
