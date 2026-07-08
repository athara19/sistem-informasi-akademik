package dao;

import database.KoneksiDB;
import model.KRS;
import model.MataKuliah;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class KRSDAO {

    // Ambil semua mata kuliah
    public List<MataKuliah> getAllMatkul() {

        List<MataKuliah> list = new ArrayList<>();

        try {

            Connection conn = KoneksiDB.getConnection();

            Statement st = conn.createStatement();

            ResultSet rs = st.executeQuery(
                    "SELECT * FROM tbl_matakuliah");

            while (rs.next()) {

                list.add(

                        new MataKuliah(

                                rs.getString("kode_mk"),
                                rs.getString("nama_mk"),
                                rs.getInt("sks"),
                                rs.getString("nidn")

                        )

                );

            }

        } catch (Exception e) {

            e.printStackTrace();

        }

        return list;

    }

    // Simpan KRS
    public boolean insertKRS(KRS krs) {

        try {

            Connection conn = KoneksiDB.getConnection();

            String sql =
                    "INSERT INTO tbl_krs(nim,kode_mk) VALUES(?,?)";

            PreparedStatement ps =
                    conn.prepareStatement(sql);

            ps.setString(1, krs.getNim());
            ps.setString(2, krs.getKodeMk());

            ps.executeUpdate();

            return true;

        } catch (Exception e) {

            e.printStackTrace();

            return false;

        }

    }

    // Hapus KRS
    public boolean deleteKRS(int idKrs) {

        try {

            Connection conn = KoneksiDB.getConnection();

            PreparedStatement ps =
                    conn.prepareStatement(
                            "DELETE FROM tbl_krs WHERE id=?");

            ps.setInt(1, idKrs);

            ps.executeUpdate();

            return true;

        } catch (Exception e) {

            e.printStackTrace();

            return false;

        }

    }

    // KRS mahasiswa
    public List<KRS> getKRSMahasiswa(String nim){

        List<KRS> list = new ArrayList<>();

        try{

            Connection conn = KoneksiDB.getConnection();

            String sql = """
            SELECT
            k.id,
            k.nim,
            m.kode_mk,
            m.nama_mk,
            m.sks,
            m.nidn
            FROM tbl_krs k
            JOIN tbl_matakuliah m
            ON k.kode_mk = m.kode_mk
            WHERE k.nim=?
            """;

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, nim);

            System.out.println("NIM = " + nim);

            ResultSet rs = ps.executeQuery();

            while(rs.next()){

                System.out.println("KETEMU : " + rs.getString("kode_mk"));

                KRS k = new KRS();

                k.setIdKrs(rs.getInt("id"));
                k.setNim(rs.getString("nim"));
                k.setKodeMk(rs.getString("kode_mk"));
                k.setNamaMk(rs.getString("nama_mk"));
                k.setSks(rs.getInt("sks"));
                k.setNidn(rs.getString("nidn"));

                list.add(k);

            }

            System.out.println("TOTAL = " + list.size());

        }catch(Exception e){

            e.printStackTrace();

        }

        return list;

    }

}