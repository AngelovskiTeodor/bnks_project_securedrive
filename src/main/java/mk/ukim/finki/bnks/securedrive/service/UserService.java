package mk.ukim.finki.bnks.securedrive.service;

import mk.ukim.finki.bnks.securedrive.model.User;

public interface UserService {
    public User findById(String userId) throws Exception;
    User registerUser(User user) throws Exception;
}
