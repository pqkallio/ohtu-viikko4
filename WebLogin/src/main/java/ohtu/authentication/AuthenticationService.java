package ohtu.authentication;

import ohtu.data_access.UserDao;
import ohtu.domain.User;
import ohtu.util.CreationStatus;

public class AuthenticationService {
    private static final int MIN_ALFA_I = 10;
    private static final int MAX_ALFA_I = 35;
    private UserDao userDao;

    public AuthenticationService(UserDao userDao) {
        this.userDao = userDao;
    }

    public boolean logIn(String username, String password) {
        for (User user : userDao.listAll()) {
            if (user.getUsername().equals(username)
                    && user.getPassword().equals(password)) {
                return true;
            }
        }

        return false;
    }

    public CreationStatus createUser(String username, String password, String passwordConfirmation) {
        CreationStatus status = new CreationStatus();
        
        if (userDao.findByName(username) != null) {
            status.addError("username is already taken");
        }

        if (username.length()<3 ) {
            status.addError("username should have at least 3 characters");
        }
        
        if (password.length()<8) {
            status.addError("password should have at least 8 characters");
        }
        
        if (containsOnlyLetters(password)) {
            status.addError("password can not contain only letters");
        }
        
        if (!passwordConfirmation.equals(password)) {
            status.addError("password and password confirmation do not match");
        }

        if (status.isOk()) {
            userDao.add(new User(username, password));
        }
        
        return status;
    }

    private boolean containsOnlyLetters(String password) {
        for (int i = 0; i < password.length(); i++) {
            int c = Character.getNumericValue(password.charAt(i));
            
            if (c < MIN_ALFA_I || c > MAX_ALFA_I) {
                return false;
            }
        }
        return true;
    }

}
