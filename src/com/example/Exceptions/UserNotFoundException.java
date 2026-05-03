package com.example.Exceptions;
public class UserNotFoundException extends Exception {
    private String username;
    public UserNotFoundException(String username){
        super("User data was not found with this username: " + username +
                ", please make sure you entered the right credentials.");
        this.username = username;
    }

    public String getUsername(){
        return username;
    }
}
