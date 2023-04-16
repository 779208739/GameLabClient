package entity;

public class Gamer extends User {

    private String PaymentMethod;

    public Gamer(){
    }

    public Gamer(int userID, String userName, String password, String email, String phone, String paymentMethod) {
        super(userID, userName, password, email, phone);
        PaymentMethod = paymentMethod;
    }

    public String getPaymentMethod() {
        return PaymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        PaymentMethod = paymentMethod;
    }
}
