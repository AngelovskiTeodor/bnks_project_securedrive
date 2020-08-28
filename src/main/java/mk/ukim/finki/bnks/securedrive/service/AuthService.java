package mk.ukim.finki.bnks.securedrive.service;

import mk.ukim.finki.bnks.securedrive.model.User;

public interface AuthService {
    User getCurrentUser();
    String getCurrentUsername();
    User registerUser(String username, String password, String repeatedPassword) throws Exception;

}
