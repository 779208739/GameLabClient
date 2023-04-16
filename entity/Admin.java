package entity;

public class Admin extends User{
    public Admin(){
    }

    public Admin(int userID, String userName, String password, String email, String phone) {
        super(userID, userName, password, email, phone);
    }

}
