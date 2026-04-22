package com.example;

import java.time.LocalDate;
import java.util.ArrayList;

public class Invoice{
    public enum PaymentMethod { CASH, CREDIT_CARD, ONLINE };
    private double totalAmount;
    private LocalDate paymentDate;
    private ArrayList<PaymentMethod> methods;
    private Reservation reservation;

    public void addPayment(PaymentMethod method) {
    if (method == null) {
        throw new IllegalArgumentException("Payment Method cannot be a null value.");
    }
    methods.add(method);
}

    public void getSummary() {
        System.out.println("Total Amount: " + totalAmount);
        System.out.println("Payment Date: " + paymentDate);
        System.out.println("Payment Methods: " + methods);
    }
    public Invoice(double totalAmount, LocalDate paymentDate) {
        setTotalAmount(totalAmount);
    setPaymentDate(paymentDate);
        this.methods = new ArrayList<>();
    }
      public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
    if (totalAmount < 0) {
        throw new IllegalArgumentException("Amount can't be negative.");
    }
    this.totalAmount = totalAmount;
}

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDate paymentDate) {
    if (paymentDate == null || paymentDate.isBefore(LocalDate.now())) {
        throw new IllegalArgumentException("Payment date cannot be null or in the past.");
    }
    this.paymentDate = paymentDate;
}

    public ArrayList<PaymentMethod> getMethods() {
        return methods;
    }

    public void setMethods(ArrayList<PaymentMethod> methods) {
        this.methods = methods;
    }
      public Reservation getReservation() {
        return reservation;
    }

}
