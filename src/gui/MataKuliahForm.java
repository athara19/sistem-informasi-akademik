package gui;

import dao.MataKuliahDAO;
import model.MataKuliah;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;

public class MataKuliahForm extends JFrame {
    private JTextField txtKode, txtNama, txtSks, txtNidn, txtCari;
    private JButton btnTambah, btnEdit, btnHapus, btnCari, btnRefresh;
    private JTable table;
    private DefaultTableModel tableModel;
    private MataKuliahDAO mkDAO;

    public MataKuliahForm() {
        mkDAO = new MataKuliahDAO();
        setTitle("Kelola Data Mata Kuliah");
        setSize(700, 500);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // Form Panel
        JPanel panelForm = new JPanel(new GridLayout(4, 2, 5, 5));
        panelForm.setBorder(BorderFactory.createTitledBorder("Input Data Mata Kuliah"));
        panelForm.add(new JLabel("Kode MK:"));
        txtKode = new JTextField();
        panelForm.add(txtKode);
        panelForm.add(new JLabel("Nama MK:"));
        txtNama = new JTextField();
        panelForm.add(txtNama);
        panelForm.add(new JLabel("SKS:"));
        txtSks = new JTextField();
        panelForm.add(txtSks);
        panelForm.add(new JLabel("NIDN Dosen Pengampu:"));
        txtNidn = new JTextField();
        panelForm.add(txtNidn);

        // Button Panel
        JPanel panelAksi = new JPanel(new FlowLayout());
        btnTambah = new JButton("Tambah");
        btnEdit = new JButton("Edit");
        btnHapus = new JButton("Hapus");
        panelAksi.add(btnTambah);
        panelAksi.add(btnEdit);
        panelAksi.add(btnHapus);

        // Search Panel
        JPanel panelCari = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        txtCari = new JTextField(15);
        btnCari = new JButton("Cari");
        btnRefresh = new JButton("Refresh");
        panelCari.add(new JLabel("Cari Kode/Nama:"));
        panelCari.add(txtCari);
        panelCari.add(btnCari);
        panelCari.add(btnRefresh);

        // Top Panel Combine
        JPanel panelAtas = new JPanel(new BorderLayout());
        panelAtas.add(panelForm, BorderLayout.NORTH);
        panelAtas.add(panelAksi, BorderLayout.CENTER);
        panelAtas.add(panelCari, BorderLayout.SOUTH);
        add(panelAtas, BorderLayout.NORTH);

        // Table
        tableModel = new DefaultTableModel(new String[]{"Kode MK", "Nama MK", "SKS", "NIDN Dosen"}, 0);
        table = new JTable(tableModel);
        add(new JScrollPane(table), BorderLayout.CENTER);

        // Event Listeners
        btnTambah.addActionListener(e -> tambahData());
        btnEdit.addActionListener(e -> editData());
        btnHapus.addActionListener(e -> hapusData());
        btnRefresh.addActionListener(e -> loadData());
        btnCari.addActionListener(e -> cariData());

        table.getSelectionModel().addListSelectionListener(e -> isiFormDariTabel());

        loadData();
    }

    private void tambahData() {
        if (!validasiInput()) return;
        try {
            int sks = Integer.parseInt(txtSks.getText());
            MataKuliah mk = new MataKuliah(txtKode.getText(), txtNama.getText(), sks, txtNidn.getText());
            mkDAO.insertMatkul(mk);
            JOptionPane.showMessageDialog(this, "Data berhasil ditambah!");
            kosongkanForm();
            loadData();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "SKS harus berupa angka!", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void editData() {
        if (!validasiInput()) return;
        try {
            int sks = Integer.parseInt(txtSks.getText());
            MataKuliah mk = new MataKuliah(txtKode.getText(), txtNama.getText(), sks, txtNidn.getText());
            mkDAO.updateMatkul(mk);
            JOptionPane.showMessageDialog(this, "Data berhasil diubah!");
            kosongkanForm();
            loadData();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "SKS harus berupa angka!", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void hapusData() {
        String kode = txtKode.getText();
        if (kode.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Pilih data dari tabel terlebih dahulu!");
            return;
        }
        int confirm = JOptionPane.showConfirmDialog(this, "Yakin hapus data?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            try {
                mkDAO.deleteMatkul(kode);
                JOptionPane.showMessageDialog(this, "Data berhasil dihapus!");
                kosongkanForm();
                loadData();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void loadData() {
        tableModel.setRowCount(0);
        try {
            List<MataKuliah> list = mkDAO.getAllMatkul();
            for (MataKuliah mk : list) {
                tableModel.addRow(new Object[]{mk.getKodeMk(), mk.getNamaMk(), mk.getSks(), mk.getNidn()});
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Gagal memuat data: " + ex.getMessage());
        }
    }

    private void cariData() {
        String keyword = txtCari.getText();
        tableModel.setRowCount(0);
        try {
            List<MataKuliah> list = mkDAO.searchMatkul(keyword);
            for (MataKuliah mk : list) {
                tableModel.addRow(new Object[]{mk.getKodeMk(), mk.getNamaMk(), mk.getSks(), mk.getNidn()});
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Gagal mencari data: " + ex.getMessage());
        }
    }

    private void isiFormDariTabel() {
        int baris = table.getSelectedRow();
        if (baris >= 0) {
            txtKode.setText(tableModel.getValueAt(baris, 0).toString());
            txtNama.setText(tableModel.getValueAt(baris, 1).toString());
            txtSks.setText(tableModel.getValueAt(baris, 2).toString());
            txtNidn.setText(tableModel.getValueAt(baris, 3).toString());
            txtKode.setEnabled(false); // Kode MK tidak boleh diedit
        }
    }

    private void kosongkanForm() {
        txtKode.setText("");
        txtNama.setText("");
        txtSks.setText("");
        txtNidn.setText("");
        txtCari.setText("");
        txtKode.setEnabled(true);
    }

    private boolean validasiInput() {
        if (txtKode.getText().isEmpty() || txtNama.getText().isEmpty() || txtSks.getText().isEmpty() || txtNidn.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Semua kolom wajib diisi!", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        return true;
    }
}