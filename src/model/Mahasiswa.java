package model;

public class Mahasiswa extends Person {

    private String prodi;
    private int semester;

    public Mahasiswa(String id, String nama, String prodi, int semester) {
        super(id, nama);
        this.prodi = prodi;
        this.semester = semester;
    }

    public String getProdi() {
        return prodi;
    }

    public int getSemester() {
        return semester;
    }

    public String getInfoLengkap() {
        return "ID: " + id +
                "\nNama: " + nama +
                "\nProdi: " + prodi +
                "\nSemester: " + semester;
    }
}