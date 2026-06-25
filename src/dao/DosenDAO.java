package dao;

import database.KoneksiDB;
import model.Dosen;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DosenDAO {

    public void insertDosen(Dosen dosen) throws SQLException {
        String sql = "INSERT INTO tbl_dosen (nidn, nama, email, username) VALUES (?, ?, ?, ?)";
        try (Connection conn = KoneksiDB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, dosen.getNidn());
            pstmt.setString(2, dosen.getNama());
            pstmt.setString(3, dosen.getEmail());
            pstmt.setString(4, dosen.getUsername());
            pstmt.executeUpdate();
        } catch (SQLIntegrityConstraintViolationException e) {
            throw new SQLException("Gagal! NIDN atau Username sudah terdaftar.");
        }
    }

    public void updateDosen(Dosen dosen) throws SQLException {
        String sql = "UPDATE tbl_dosen SET nama=?, email=?, username=? WHERE nidn=?";
        try (Connection conn = KoneksiDB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, dosen.getNama());
            pstmt.setString(2, dosen.getEmail());
            pstmt.setString(3, dosen.getUsername());
            pstmt.setString(4, dosen.getNidn());
            pstmt.executeUpdate();
        }
    }

    public void deleteDosen(String nidn) throws SQLException {
        String sql = "DELETE FROM tbl_dosen WHERE nidn=?";
        try (Connection conn = KoneksiDB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, nidn);
            pstmt.executeUpdate();
        } catch (SQLIntegrityConstraintViolationException e) {
            throw new SQLException("Tidak bisa dihapus! Dosen ini sedang mengajar mata kuliah.");
        }
    }

    public List<Dosen> getAllDosen() throws SQLException {
        List<Dosen> list = new ArrayList<>();
        String sql = "SELECT * FROM tbl_dosen";
        try (Connection conn = KoneksiDB.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                list.add(new Dosen(rs.getString("nidn"), rs.getString("nama"), rs.getString("email"), rs.getString("username")));
            }
        }
        return list;
    }

    public List<Dosen> searchDosen(String keyword) throws SQLException {
        List<Dosen> list = new ArrayList<>();
        String sql = "SELECT * FROM tbl_dosen WHERE nama LIKE ? OR nidn LIKE ?";
        try (Connection conn = KoneksiDB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, "%" + keyword + "%");
            pstmt.setString(2, "%" + keyword + "%");
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    list.add(new Dosen(rs.getString("nidn"), rs.getString("nama"), rs.getString("email"), rs.getString("username")));
                }
            }
        }
        return list;
    }
}