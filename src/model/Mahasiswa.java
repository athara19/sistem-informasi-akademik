package model;

public class Mahasiswa extends Person {

    private String nim;
    private String prodi;
    private int semester;

    public Mahasiswa(String text, String txtNamaText, String txtProdiText, int i) {
    }

    // Constructor untuk DAO (tanpa password)
    public Mahasiswa(String nim,
                     String nama,
                     String prodi,
                     int semester,
                     String username) {

        super(nim, nama, username, "");

        this.nim = nim;
        this.prodi = prodi;
        this.semester = semester;
    }

    // Constructor untuk Login / Session (dengan password)
    public Mahasiswa(String nim,
                     String nama,
                     String prodi,
                     int semester,
                     String username,
                     String password) {

        super(nim, nama, username, password);

        this.nim = nim;
        this.prodi = prodi;
        this.semester = semester;
    }

    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public String getProdi() {
        return prodi;
    }

    public void setProdi(String prodi) {
        this.prodi = prodi;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    @Override
    public String getRole() {
        return "MAHASISWA";
    }
}