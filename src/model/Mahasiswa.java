package model;

public class Mahasiswa {

    private String nim;
    private String nama;
    private String prodi;
    private int semester;

    public Mahasiswa() {
    }

    public Mahasiswa(String nim, String nama, String prodi, int semester) {
        this.nim = nim;
        this.nama = nama;
        this.prodi = prodi;
        this.semester = semester;
    }

    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
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

    public void lihatProfil() {
        System.out.println("Menampilkan profil mahasiswa");
    }

    public void isiKRS() {
        System.out.println("Mengambil mata kuliah");
    }

    public void lihatJadwal() {
        System.out.println("Menampilkan jadwal");
    }

    public void lihatNilai() {
        System.out.println("Menampilkan nilai");
    }

    public void lihatKHS() {
        System.out.println("Menampilkan KHS");
    }

    public void lihatTranskrip() {
        System.out.println("Menampilkan transkrip");
    }

    public void logout() {
        System.out.println("Logout berhasil");
    }
}