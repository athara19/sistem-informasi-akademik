package model;

public abstract class Person {

    // =========================
    // ATRIBUT
    // =========================
    protected String id;
    protected String nama;
    protected String username;
    protected String password;

    // =========================
    // CONSTRUCTOR KOSONG
    // =========================
    public Person() {
    }

    // =========================
    // CONSTRUCTOR BERPARAMETER
    // =========================
    public Person(String id, String nama, String username, String password) {
        this.id = id;
        this.nama = nama;
        this.username = username;
        this.password = password;
    }

    // =========================
    // GETTER & SETTER
    // =========================
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // =========================
    // ABSTRACT METHOD
    // =========================
    public abstract void showDashboard();
}