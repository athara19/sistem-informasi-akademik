package model;

public class Admin extends Person {

    public Admin() {
    }

    public Admin(String id, String nama, String username, String password) {
        super(id, nama, username, password);
    }

    @Override
    public String getRole() {
        return "ADMIN";
    }

}