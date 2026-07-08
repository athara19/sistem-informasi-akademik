package dao;

import database.KoneksiDB;
import model.MataKuliah;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MataKuliahDAO {

    public boolean insertMatkul(MataKuliah mk) {

        try {

            String sql =
                    "INSERT INTO tbl_matakuliah(kode_mk,nama_mk,sks,nidn) VALUES(?,?,?,?)";

            Connection conn = KoneksiDB.getConnection();

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, mk.getKodeMk());
            ps.setString(2, mk.getNamaMk());
            ps.setInt(3, mk.getSks());
            ps.setString(4, mk.getNidn());

            int hasil = ps.executeUpdate();

            System.out.println("HASIL INSERT = " + hasil);
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT DATABASE()");
            if(rs.next()){
                System.out.println("DATABASE JAVA = " + rs.getString(1));
            }

            ResultSet rs2 = st.executeQuery("SELECT COUNT(*) FROM tbl_matakuliah");
            if(rs2.next()){
                System.out.println("COUNT JAVA = " + rs2.getInt(1));
            }

            return hasil > 0;

        } catch (Exception e) {

            e.printStackTrace();

            JOptionPane.showMessageDialog(null, e.toString());

            return false;

        }

    }

    public boolean updateMatkul(MataKuliah mk) {

        try {

            String sql =
                    "UPDATE tbl_matakuliah SET nama_mk=?,sks=?,nidn=? WHERE kode_mk=?";

            Connection conn = KoneksiDB.getConnection();

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, mk.getNamaMk());
            ps.setInt(2, mk.getSks());
            ps.setString(3, mk.getNidn());
            ps.setString(4, mk.getKodeMk());

            ps.executeUpdate();

            return true;

        } catch (Exception e) {

            e.printStackTrace();

            return false;

        }

    }

    public boolean deleteMatkul(String kodeMk) {

        try {

            String sql =
                    "DELETE FROM tbl_matakuliah WHERE kode_mk=?";

            Connection conn = KoneksiDB.getConnection();

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, kodeMk);

            ps.executeUpdate();

            return true;

        } catch (Exception e) {

            e.printStackTrace();

            return false;

        }

    }

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

    public List<MataKuliah> searchMatkul(String keyword) {

        List<MataKuliah> list = new ArrayList<>();

        try {

            Connection conn = KoneksiDB.getConnection();

            String sql =
                    """
                    SELECT *
                    FROM tbl_matakuliah
                    WHERE kode_mk LIKE ?
                    OR nama_mk LIKE ?
                    """;

            PreparedStatement ps =
                    conn.prepareStatement(sql);

            ps.setString(1,"%"+keyword+"%");
            ps.setString(2,"%"+keyword+"%");

            ResultSet rs = ps.executeQuery();

            while(rs.next()){

                list.add(

                        new MataKuliah(

                                rs.getString("kode_mk"),

                                rs.getString("nama_mk"),

                                rs.getInt("sks"),

                                rs.getString("nidn")

                        )

                );

            }

        } catch (Exception e){

            e.printStackTrace();

        }

        return list;

    }

}