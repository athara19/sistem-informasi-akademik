package model;

public class MataKuliah {
    private String kodeMK;
    private String namaMK;
    private int sks;
    private Dosen dosenPengampu; // Relasi ke objek Dosen

    // Constructor
    public MataKuliah(String kodeMK, String namaMK, int sks, Dosen dosenPengampu) {
        this.kodeMK = kodeMK;
        this.namaMK = namaMK;
        this.sks = sks;
        this.dosenPengampu = dosenPengampu;
    }

    // Getter dan Setter
    public String getKodeMK() { return kodeMK; }
    public void setKodeMK(String kodeMK) { this.kodeMK = kodeMK; }

    public String getNamaMK() { return namaMK; }
    public void setNamaMK(String namaMK) { this.namaMK = namaMK; }

    public int getSks() { return sks; }
    public void setSks(int sks) { this.sks = sks; }

    public Dosen getDosenPengampu() { return dosenPengampu; }
    public void setDosenPengampu(Dosen dosenPengampu) { this.dosenPengampu = dosenPengampu; }
}