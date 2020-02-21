package com.company.jobnow.activity.firstTime;

public class SecurityService {
    private static final SecurityService ourInstance = new SecurityService();

    public static SecurityService getInstance() {
        return ourInstance;
    }

    private SecurityService() { }

    public boolean isTokenValid(String token) {
        // Check if token is valid
        return true;
    }

    public boolean registerUser(String fullName, String email, String password) {

        // hash the password
        // return true if registration was success
        return true;
    }

    public String logInUser(String email, String password) {

        // hash the password
        // return null if login failed
        return null;
    }

    public void logOutUser(String tokem) {

        // delete token from database
        // preferences will be cleared in mainActivity
    }
}
