package Exceptions;

public class InvalidPaymentException extends Exception{
private double total, balance;
public InvalidPaymentException(double total, double balance){
    super("Your balance: " + balance + " is less than the total amount due which is " + total);
    this.total = total;
    this.balance = balance;
}

    //Getters
    public double getTotal() {return total;}
    public double getBalance() {return balance;}
}
