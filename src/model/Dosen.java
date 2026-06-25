package model;

public class Dosen {
    private String nidn;
    private String nama;
    private String email;
    private String username;

    public Dosen(String nidn, String nama, String email, String username) {
        this.nidn = nidn;
        this.nama = nama;
        this.email = email;
        this.username = username;
    }

    public String getNidn() { return nidn; }
    public void setNidn(String nidn) { this.nidn = nidn; }

    public String getNama() { return nama; }
    public void setNama(String nama) { this.nama = nama; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
}