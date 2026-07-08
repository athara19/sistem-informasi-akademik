package model;

public class KRS {

    private int idKrs;
    private String nim;
    private String kodeMk;

    // tambahan
    private String namaMk;
    private int sks;
    private String nidn;

    public KRS(){}

    public KRS(String nim,String kodeMk){

        this.nim=nim;
        this.kodeMk=kodeMk;

    }

    public int getIdKrs() {
        return idKrs;
    }

    public void setIdKrs(int idKrs) {
        this.idKrs = idKrs;
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

    public String getNamaMk() {
        return namaMk;
    }

    public void setNamaMk(String namaMk) {
        this.namaMk = namaMk;
    }

    public int getSks() {
        return sks;
    }

    public void setSks(int sks) {
        this.sks = sks;
    }

    public String getNidn() {
        return nidn;
    }

    public void setNidn(String nidn) {
        this.nidn = nidn;
    }

}