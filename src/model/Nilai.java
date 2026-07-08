package model;

public class Nilai {

    private int id;
    private String nim;
    private String kodeMk;
    private double nilai;

    public Nilai() {
    }

    public Nilai(String nim,
                 String kodeMk,
                 double nilai) {

        this.nim = nim;
        this.kodeMk = kodeMk;
        this.nilai = nilai;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public String getKodeMk() {
        return kodeMk;
    }

    public void setKodeMk(String kodeMk) {
        this.kodeMk = kodeMk;
    }

    public double getNilai() {
        return nilai;
    }

    public void setNilai(double nilai) {
        this.nilai = nilai;
    }

}