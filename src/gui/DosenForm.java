package gui;

import model.Dosen; // Mengimpor class Dosen dari paket model
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class DosenForm extends JFrame {
    // Penyimpanan data sementara (static) agar bisa dibaca oleh MataKuliahForm
    public static java.util.ArrayList<Dosen> listDosen = new java.util.ArrayList<>();

    private JTextField txtNidn, txtNama, txtEmail;
    private JTable tableDosen;
    private DefaultTableModel tableModel;

    public DosenForm() {
        setTitle("Manajemen Data Dosen");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // Panel Input Data (Kiri)
        JPanel panelInput = new JPanel(new GridLayout(4, 2, 5, 5));
        panelInput.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        panelInput.add(new JLabel("NIDN:"));
        txtNidn = new JTextField();
        panelInput.add(txtNidn);

        panelInput.add(new JLabel("Nama Dosen:"));
        txtNama = new JTextField();
        panelInput.add(txtNama);

        panelInput.add(new JLabel("Email:"));
        txtEmail = new JTextField();
        panelInput.add(txtEmail);

        JButton btnSimpan = new JButton("Simpan");
        panelInput.add(btnSimpan);
        add(panelInput, BorderLayout.WEST);

        // Panel Tabel Tampilan Data (Kanan)
        String[] kolom = {"NIDN", "Nama", "Email"};
        tableModel = new DefaultTableModel(kolom, 0);
        tableDosen = new JTable(tableModel);
        add(new JScrollPane(tableDosen), BorderLayout.CENTER);

        // Aksi ketika tombol Simpan ditekan
        btnSimpan.addActionListener(e -> {
            String nidn = txtNidn.getText().trim();
            String nama = txtNama.getText().trim();
            String email = txtEmail.getText().trim();

            if (!nidn.isEmpty() && !nama.isEmpty() && !email.isEmpty()) {
                Dosen dosenBaru = new Dosen(nidn, nama, email);
                listDosen.add(dosenBaru); // Masuk ke storage static

                // Tambahkan baris baru ke tabel UI
                tableModel.addRow(new Object[]{dosenBaru.getNidn(), dosenBaru.getNama(), dosenBaru.getEmail()});

                // Reset form input
                txtNidn.setText("");
                txtNama.setText("");
                txtEmail.setText("");
            } else {
                JOptionPane.showMessageDialog(this, "Semua kolom input wajib diisi!");
            }
        });
    }

    // Method main lokal untuk melakukan uji coba (Testing) mandiri
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new DosenForm().setVisible(true));
    }
}