package dao;

import database.KoneksiDB;
import model.Mahasiswa;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MahasiswaDAO {

    public boolean insertMahasiswa(Mahasiswa m) {

        Connection conn = null;

        try {

            conn = KoneksiDB.getConnection();

            conn.setAutoCommit(false);

            String username = m.getNim();
            String password = m.getNim();

            // Insert ke tabel users
            String sqlUser =
                    "INSERT INTO users(username,password,role) VALUES(?,?,?)";

            PreparedStatement psUser =
                    conn.prepareStatement(sqlUser);

            psUser.setString(1, username);
            psUser.setString(2, password);
            psUser.setString(3, "MAHASISWA");

            psUser.executeUpdate();

            // Insert ke tabel mahasiswa
            String sqlMahasiswa =
                    "INSERT INTO tbl_mahasiswa(nim,nama,prodi,semester,username) VALUES(?,?,?,?,?)";

            PreparedStatement psMahasiswa =
                    conn.prepareStatement(sqlMahasiswa);

            psMahasiswa.setString(1, m.getNim());
            psMahasiswa.setString(2, m.getNama());
            psMahasiswa.setString(3, m.getProdi());
            psMahasiswa.setInt(4, m.getSemester());
            psMahasiswa.setString(5, username);

            psMahasiswa.executeUpdate();

            conn.commit();

            return true;

        } catch (Exception e) {

            try {

                if (conn != null) {

                    conn.rollback();

                }

            } catch (Exception ex) {

                ex.printStackTrace();

            }

            e.printStackTrace();

            return false;

        }

    }

    public boolean updateMahasiswa(Mahasiswa m) {

        try {

            Connection conn = KoneksiDB.getConnection();

            String sql =
                    "UPDATE tbl_mahasiswa SET nama=?,prodi=?,semester=? WHERE nim=?";

            PreparedStatement ps =
                    conn.prepareStatement(sql);

            ps.setString(1, m.getNama());
            ps.setString(2, m.getProdi());
            ps.setInt(3, m.getSemester());
            ps.setString(4, m.getNim());

            ps.executeUpdate();

            return true;

        } catch (Exception e) {

            e.printStackTrace();

            return false;

        }

    }

    public boolean deleteMahasiswa(String nim) {

        Connection conn = null;

        try {

            conn = KoneksiDB.getConnection();

            conn.setAutoCommit(false);

            String username = "";

            String cari =
                    "SELECT username FROM tbl_mahasiswa WHERE nim=?";

            PreparedStatement psCari =
                    conn.prepareStatement(cari);

            psCari.setString(1, nim);

            ResultSet rs = psCari.executeQuery();

            if (rs.next()) {

                username = rs.getString("username");

            }

            String sql1 =
                    "DELETE FROM tbl_mahasiswa WHERE nim=?";

            PreparedStatement ps1 =
                    conn.prepareStatement(sql1);

            ps1.setString(1, nim);

            ps1.executeUpdate();

            String sql2 =
                    "DELETE FROM users WHERE username=?";

            PreparedStatement ps2 =
                    conn.prepareStatement(sql2);

            ps2.setString(1, username);

            ps2.executeUpdate();

            conn.commit();

            return true;

        } catch (Exception e) {

            try {

                if (conn != null) {

                    conn.rollback();

                }

            } catch (Exception ex) {

                ex.printStackTrace();

            }

            e.printStackTrace();

            return false;

        }

    }

    public List<Mahasiswa> getAllMahasiswa() {

        List<Mahasiswa> list =
                new ArrayList<>();

        try {

            Connection conn =
                    KoneksiDB.getConnection();

            Statement st =
                    conn.createStatement();

            ResultSet rs =
                    st.executeQuery(
                            "SELECT * FROM tbl_mahasiswa");

            while (rs.next()) {

                list.add(

                        new Mahasiswa(

                                rs.getString("nim"),

                                rs.getString("nama"),

                                rs.getString("prodi"),

                                rs.getInt("semester"),

                                rs.getString("username")

                        )

                );

            }

        } catch (Exception e) {

            e.printStackTrace();

        }

        return list;

    }

    public List<Mahasiswa> searchMahasiswa(String keyword) {

        List<Mahasiswa> list =
                new ArrayList<>();

        try {

            Connection conn =
                    KoneksiDB.getConnection();

            String sql =
                    """
                    SELECT *
                    FROM tbl_mahasiswa
                    WHERE nim LIKE ?
                    OR nama LIKE ?
                    """;

            PreparedStatement ps =
                    conn.prepareStatement(sql);

            ps.setString(1, "%" + keyword + "%");
            ps.setString(2, "%" + keyword + "%");

            ResultSet rs =
                    ps.executeQuery();

            while (rs.next()) {

                list.add(

                        new Mahasiswa(

                                rs.getString("nim"),

                                rs.getString("nama"),

                                rs.getString("prodi"),

                                rs.getInt("semester"),

                                rs.getString("username")

                        )

                );

            }

        } catch (Exception e) {

            e.printStackTrace();

        }

        return list;

    }

}