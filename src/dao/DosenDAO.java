package dao;

import database.KoneksiDB;
import model.Dosen;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DosenDAO {

    public boolean insertDosen(Dosen dosen) {

        Connection conn = null;

        try {

            conn = KoneksiDB.getConnection();

            conn.setAutoCommit(false);

            String username = dosen.getNidn();
            String password = dosen.getNidn();

            String sqlUser =
                    "INSERT INTO users(username,password,role) VALUES(?,?,?)";

            PreparedStatement psUser =
                    conn.prepareStatement(sqlUser);

            psUser.setString(1, username);
            psUser.setString(2, password);
            psUser.setString(3, "DOSEN");

            psUser.executeUpdate();

            String sqlDosen =
                    "INSERT INTO tbl_dosen(nidn,nama,email,username) VALUES(?,?,?,?)";

            PreparedStatement psDosen =
                    conn.prepareStatement(sqlDosen);

            psDosen.setString(1, dosen.getNidn());
            psDosen.setString(2, dosen.getNama());
            psDosen.setString(3, dosen.getEmail());
            psDosen.setString(4, username);

            psDosen.executeUpdate();

            conn.commit();

            return true;

        } catch (Exception e) {

            try {

                if (conn != null) conn.rollback();

            } catch (Exception ex) {
                ex.printStackTrace();
            }

            e.printStackTrace();

            return false;

        }

    }

    public boolean updateDosen(Dosen dosen) {

        try {

            Connection conn = KoneksiDB.getConnection();

            String sql =
                    "UPDATE tbl_dosen SET nama=?,email=? WHERE nidn=?";

            PreparedStatement ps =
                    conn.prepareStatement(sql);

            ps.setString(1, dosen.getNama());
            ps.setString(2, dosen.getEmail());
            ps.setString(3, dosen.getNidn());

            ps.executeUpdate();

            return true;

        } catch (Exception e) {

            e.printStackTrace();

            return false;

        }

    }

    public boolean deleteDosen(String nidn) {

        Connection conn = null;

        try {

            conn = KoneksiDB.getConnection();

            conn.setAutoCommit(false);

            String username = "";

            PreparedStatement psCari =
                    conn.prepareStatement(
                            "SELECT username FROM tbl_dosen WHERE nidn=?");

            psCari.setString(1, nidn);

            ResultSet rs = psCari.executeQuery();

            if (rs.next()) {

                username = rs.getString("username");

            }

            PreparedStatement ps1 =
                    conn.prepareStatement(
                            "DELETE FROM tbl_dosen WHERE nidn=?");

            ps1.setString(1, nidn);

            ps1.executeUpdate();

            PreparedStatement ps2 =
                    conn.prepareStatement(
                            "DELETE FROM users WHERE username=?");

            ps2.setString(1, username);

            ps2.executeUpdate();

            conn.commit();

            return true;

        } catch (Exception e) {

            try {

                if (conn != null) conn.rollback();

            } catch (Exception ex) {
                ex.printStackTrace();
            }

            e.printStackTrace();

            return false;

        }

    }

    public List<Dosen> getAllDosen() {

        List<Dosen> list = new ArrayList<>();

        try {

            Connection conn = KoneksiDB.getConnection();

            Statement stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT * FROM tbl_dosen");

            while (rs.next()) {

                list.add(new Dosen(
                        rs.getString("nidn"),
                        rs.getString("nama"),
                        rs.getString("email"),
                        rs.getString("username")
                ));

            }

        } catch (Exception e) {

            e.printStackTrace();

        }

        return list;

    }

    public List<Dosen> searchDosen(String keyword) {

        List<Dosen> list = new ArrayList<>();

        try {

            Connection conn = KoneksiDB.getConnection();

            String sql =
                    "SELECT * FROM tbl_dosen WHERE nama LIKE ? OR nidn LIKE ?";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, "%" + keyword + "%");
            ps.setString(2, "%" + keyword + "%");

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                list.add(new Dosen(
                        rs.getString("nidn"),
                        rs.getString("nama"),
                        rs.getString("email"),
                        rs.getString("username")
                ));

            }

        } catch (Exception e) {

            e.printStackTrace();

        }

        return list;

    }
}