package model;

public class Dosen {
    private String nidn;
    private String nama;
    private String email;

    // Constructor
    public Dosen(String nidn, String nama, String email) {
        this.nidn = nidn;
        this.nama = nama;
        this.email = email;
    }

    // Getter dan Setter
    public String getNidn() { return nidn; }
    public void setNidn(String nidn) { this.nidn = nidn; }

    public String getNama() { return nama; }
    public void setNama(String nama) { this.nama = nama; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    // Override toString agar nama dosen yang muncul di JComboBox (Dropdown)
    @Override
    public String toString() {
        return this.nama;
    }
}