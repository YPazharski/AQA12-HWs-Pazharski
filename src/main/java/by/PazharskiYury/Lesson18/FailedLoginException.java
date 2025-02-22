package by.PazharskiYury.Lesson18;

public class FailedLoginException extends RuntimeException {

    public FailedLoginException(String email, String password) {
        super(String.format("Login failed with email %s and password %s.", email, password));
    }

}