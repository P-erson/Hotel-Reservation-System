package com.example.Exceptions;
public class InvalidPasswordException extends Exception{
    private String password;
    public InvalidPasswordException(String password){
        super("Invalid password, please try again");
        this.password = password;
    }

    public String getPassword(){
        return password;
    }
}
