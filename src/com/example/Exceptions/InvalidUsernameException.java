package com.example.Exceptions;
public class InvalidUsernameException extends Exception {
    private String username;

    public InvalidUsernameException(String username){
        super("Invalid username, no empty usernames are allowed");
        this.username = username;
    }

    public String getUsername(){
        return username;
    }


}
