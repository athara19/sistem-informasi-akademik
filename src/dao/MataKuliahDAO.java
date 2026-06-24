package dao;

import database.KoneksiDB;
import model.MataKuliah;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MataKuliahDAO {

    public void insertMatkul(MataKuliah mk) throws SQLException {
        String sql = "INSERT INTO tbl_matakuliah (kode_mk, nama_mk, sks, nidn) VALUES (?, ?, ?, ?)";
        try (Connection conn = KoneksiDB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, mk.getKodeMk());
            pstmt.setString(2, mk.getNamaMk());
            pstmt.setInt(3, mk.getSks());
            pstmt.setString(4, mk.getNidn());
            pstmt.executeUpdate();
        } catch (SQLIntegrityConstraintViolationException e) {
            throw new SQLException("Gagal! Kode Mata Kuliah sudah ada atau NIDN tidak ditemukan.");
        }
    }

    public void updateMatkul(MataKuliah mk) throws SQLException {
        String sql = "UPDATE tbl_matakuliah SET nama_mk=?, sks=?, nidn=? WHERE kode_mk=?";
        try (Connection conn = KoneksiDB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, mk.getNamaMk());
            pstmt.setInt(2, mk.getSks());
            pstmt.setString(3, mk.getNidn());
            pstmt.setString(4, mk.getKodeMk());
            pstmt.executeUpdate();
        } catch (SQLIntegrityConstraintViolationException e) {
            throw new SQLException("Gagal! Pastikan NIDN Dosen valid.");
        }
    }

    public void deleteMatkul(String kodeMk) throws SQLException {
        String sql = "DELETE FROM tbl_matakuliah WHERE kode_mk=?";
        try (Connection conn = KoneksiDB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, kodeMk);
            pstmt.executeUpdate();
        } catch (SQLIntegrityConstraintViolationException e) {
            throw new SQLException("Tidak bisa dihapus! Mata kuliah ini sedang diambil oleh mahasiswa (KRS).");
        }
    }

    public List<MataKuliah> getAllMatkul() throws SQLException {
        List<MataKuliah> list = new ArrayList<>();
        String sql = "SELECT * FROM tbl_matakuliah";
        try (Connection conn = KoneksiDB.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                list.add(new MataKuliah(rs.getString("kode_mk"), rs.getString("nama_mk"), rs.getInt("sks"), rs.getString("nidn")));
            }
        }
        return list;
    }

    public List<MataKuliah> searchMatkul(String keyword) throws SQLException {
        List<MataKuliah> list = new ArrayList<>();
        String sql = "SELECT * FROM tbl_matakuliah WHERE nama_mk LIKE ? OR kode_mk LIKE ?";
        try (Connection conn = KoneksiDB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, "%" + keyword + "%");
            pstmt.setString(2, "%" + keyword + "%");
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    list.add(new MataKuliah(rs.getString("kode_mk"), rs.getString("nama_mk"), rs.getInt("sks"), rs.getString("nidn")));
                }
            }
        }
        return list;
    }
}