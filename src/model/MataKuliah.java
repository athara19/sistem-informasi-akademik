package model;

public class MataKuliah {
    private String kodeMk;
    private String namaMk;
    private int sks;
    private String nidn;

    public MataKuliah(String kodeMk, String namaMk, int sks, String nidn) {
        this.kodeMk = kodeMk;
        this.namaMk = namaMk;
        this.sks = sks;
        this.nidn = nidn;
    }

    public String getKodeMk() { return kodeMk; }
    public void setKodeMk(String kodeMk) { this.kodeMk = kodeMk; }

    public String getNamaMk() { return namaMk; }
    public void setNamaMk(String namaMk) { this.namaMk = namaMk; }

    public int getSks() { return sks; }
    public void setSks(int sks) { this.sks = sks; }

    public String getNidn() { return nidn; }
    public void setNidn(String nidn) { this.nidn = nidn; }
}