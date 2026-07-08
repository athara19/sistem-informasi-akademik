package dao;

import database.KoneksiDB;
import model.Admin;
import model.Dosen;
import model.Mahasiswa;
import model.Person;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginDAO {

    public Person login(String username, String password) {

        try {

            Connection conn = KoneksiDB.getConnection();

            String sql = "SELECT * FROM users WHERE username=? AND password=?";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, username);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (!rs.next()) {
                return null;
            }

            String role = rs.getString("role");

            switch (role) {

                case "ADMIN":

                    return new Admin(
                            "ADM001",
                            "Administrator",
                            username,
                            password
                    );

                case "DOSEN":

                    return getDosen(conn, username, password);

                case "MAHASISWA":

                    return getMahasiswa(conn, username, password);

                default:

                    return null;

            }

        } catch (Exception e) {

            e.printStackTrace();

            return null;

        }

    }

    private Dosen getDosen(Connection conn,
                           String username,
                           String password) throws Exception {

        String sql = "SELECT * FROM tbl_dosen WHERE username=?";

        PreparedStatement ps = conn.prepareStatement(sql);

        ps.setString(1, username);

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {

            return new Dosen(

                    rs.getString("nidn"),

                    rs.getString("nama"),

                    rs.getString("email"),

                    username,

                    password

            );

        }

        return null;

    }

    private Mahasiswa getMahasiswa(Connection conn,
                                   String username,
                                   String password) throws Exception {

        String sql = "SELECT * FROM tbl_mahasiswa WHERE username=?";

        PreparedStatement ps = conn.prepareStatement(sql);

        ps.setString(1, username);

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {

            return new Mahasiswa(

                    rs.getString("nim"),

                    rs.getString("nama"),

                    rs.getString("prodi"),

                    rs.getInt("semester"),

                    username,

                    password

            );

        }

        return null;

    }

}