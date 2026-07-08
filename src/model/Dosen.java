package model;

public class Dosen extends Person {

    private String nidn;
    private String email;

    public Dosen() {
    }

    // Constructor untuk DAO
    public Dosen(String nidn,
                 String nama,
                 String email,
                 String username) {

        super(nidn, nama, username, "");

        this.nidn = nidn;
        this.email = email;
    }

    // Constructor untuk Login
    public Dosen(String nidn,
                 String nama,
                 String email,
                 String username,
                 String password) {

        super(nidn, nama, username, password);

        this.nidn = nidn;
        this.email = email;
    }

    public String getNidn() {
        return nidn;
    }

    public void setNidn(String nidn) {
        this.nidn = nidn;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String getRole() {
        return "DOSEN";
    }
}