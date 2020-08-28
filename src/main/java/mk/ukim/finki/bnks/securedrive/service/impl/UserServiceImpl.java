package mk.ukim.finki.bnks.securedrive.service.impl;

import mk.ukim.finki.bnks.securedrive.model.User;
import mk.ukim.finki.bnks.securedrive.repository.UserRepository;
import mk.ukim.finki.bnks.securedrive.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User findById(String userId) throws Exception {
        return this.userRepository.findById(userId)
                .orElseThrow(() -> new Exception("User not found: "+userId));
    }

    @Override
    public User registerUser(User user) throws Exception {
        if (this.userRepository.existsById(user.getUsername())) {
            throw new Exception("User " + user.getUsername() + "already exists!");
        }
        return this.userRepository.save(user);
    }


}
