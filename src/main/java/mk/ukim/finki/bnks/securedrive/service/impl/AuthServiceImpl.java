package mk.ukim.finki.bnks.securedrive.service.impl;

import mk.ukim.finki.bnks.securedrive.model.Role;
import mk.ukim.finki.bnks.securedrive.model.User;
import mk.ukim.finki.bnks.securedrive.repository.RoleRepository;
import mk.ukim.finki.bnks.securedrive.repository.UserRepository;
import mk.ukim.finki.bnks.securedrive.service.AuthService;
import mk.ukim.finki.bnks.securedrive.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Collections;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Override
    public User getCurrentUser() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    @Override
    public String getCurrentUsername() {
        return this.getCurrentUser().getUsername();
    }

    @Override
    public User registerUser(String username, String password, String repeatedPassword) throws Exception {
        if (!password.equals(repeatedPassword)) {
            throw new Exception("Passwords do not match!");
        }

        User newUser = new User();
        newUser.setUsername(username);
        newUser.setPassword(passwordEncoder.encode(password));
        Role userRole = this.roleRepository.findByName("ROLE_USER");
        newUser.setRoles(Collections.singletonList(userRole));
        return this.userService.registerUser(newUser);
    }

//    @PostConstruct
//    public void init() {
//        if (!this.userRepository.existsById("admin")) {
//            User superAdmin = new User();
//            superAdmin.setUsername("admin");
//            superAdmin.setPassword(this.passwordEncoder.encode("admin"));
//            superAdmin.setRoles(this.roleRepository.findAll());
//            this.userRepository.save(superAdmin);
//        }
//    }

}
