package com.example.Exceptions;

public class InvalidRole extends Exception{
    private Object role;

    public InvalidRole(Object role){
        super("Invalid Role entered: " + role);
        this.role = role;
    }

    public Object getRole() { return role; }
}
