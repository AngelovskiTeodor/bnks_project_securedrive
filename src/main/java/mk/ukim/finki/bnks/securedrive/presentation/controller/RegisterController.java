package mk.ukim.finki.bnks.securedrive.presentation.controller;

import mk.ukim.finki.bnks.securedrive.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/register")
public class RegisterController {

    public final AuthService authService;

    public RegisterController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping
    public String getRegisterPage() {
        return "register";
    }

    @PostMapping
    public String registerUser(
            @RequestParam String username,
            @RequestParam String password,
            @RequestParam String repeatedPassword
    ){
        try {
            this.authService.registerUser(username,password,repeatedPassword);
            return "redirect:/login";
        } catch (Exception ex) {
            System.out.println("Exception in RegisterController: " + ex.getLocalizedMessage());
            return "redirect:/register?";
        }
    }
}
