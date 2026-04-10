import java.time.LocalDate
import java.util.ArrayList

class Invoice{
     public enum PaymentMethod { CASH, CREDIT_CARD, ONLINE };
private double totalAmount;
private LocalDate paymentDate;
private List<PaymentMethod> methods;

public void addPayment(PaymentMethod method) {
    methods.add(method);
}

public void getSummary() {
    System.out.println("Total Amount: " + totalAmount);
    System.out.println("Payment Date: " + paymentDate);
    System.out.println("Payment Methods: " + methods);
}
public Invoice(double totalAmount, LocalDate paymentDate) {
    this.totalAmount = totalAmount;
    this.paymentDate = paymentDate;
    this.methods = new ArrayList<>();
}

}
