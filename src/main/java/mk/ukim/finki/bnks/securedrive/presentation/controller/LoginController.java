package mk.ukim.finki.bnks.securedrive.presentation.controller;

import mk.ukim.finki.bnks.securedrive.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/login")
public class LoginController {
    private final UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String getLoginPage() {
        return "login";
    }

    @PostMapping
    public String loginUser(HttpServletRequest req) throws Exception {
        String username = req.getParameter("username");
        if (username != null) {
            try {
                this.userService.findById(username);
            }
            catch (Exception ex) {
                throw new Exception("Invalid user: "+username);
            }
            req.getSession().setAttribute("username", username);
            return "redirect:/files/list";
        } else {
            return "redirect:/login";
        }
    }
}