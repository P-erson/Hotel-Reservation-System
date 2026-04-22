package com.example.Exceptions;
public class InvalidBalanceException extends Exception {
    private double balance;

    public InvalidBalanceException(double balance){
        super("Invalid Balance " + balance);
        this.balance = balance;
    }

    public double getBalance(){
        return balance;
    }
}
