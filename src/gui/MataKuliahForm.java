package gui;

import model.Dosen;       // Mengimpor class Dosen
import model.MataKuliah;  // Mengimpor class MataKuliah
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class MataKuliahForm extends JFrame {
    // Penyimpanan data sementara (static) yang nantinya akan diambil oleh Orang 4 (KRS)
    public static java.util.ArrayList<MataKuliah> listMK = new java.util.ArrayList<>();

    private JTextField txtKode, txtNama, txtSks;
    private JComboBox<Dosen> cbDosen; // Dropdown menu bertipe objek Dosen
    private JTable tableMK;
    private DefaultTableModel tableModel;

    public MataKuliahForm() {
        setTitle("Manajemen Data Mata Kuliah");
        setSize(700, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // Panel Input Data (Kiri)
        JPanel panelInput = new JPanel(new GridLayout(5, 2, 5, 5));
        panelInput.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        panelInput.add(new JLabel("Kode MK:"));
        txtKode = new JTextField();
        panelInput.add(txtKode);

        panelInput.add(new JLabel("Nama MK:"));
        txtNama = new JTextField();
        panelInput.add(txtNama);

        panelInput.add(new JLabel("SKS:"));
        txtSks = new JTextField();
        panelInput.add(txtSks);

        panelInput.add(new JLabel("Dosen Pengampu:"));
        cbDosen = new JComboBox<>();

        // Mengambil data real-time dari listDosen static milik DosenForm
        for (Dosen d : DosenForm.listDosen) {
            cbDosen.addItem(d);
        }
        panelInput.add(cbDosen);

        JButton btnSimpan = new JButton("Simpan");
        panelInput.add(btnSimpan);
        add(panelInput, BorderLayout.WEST);

        // Panel Tabel Tampilan Data (Kanan)
        String[] kolom = {"Kode MK", "Nama MK", "SKS", "Dosen Pengampu"};
        tableModel = new DefaultTableModel(kolom, 0);
        tableMK = new JTable(tableModel);
        add(new JScrollPane(tableMK), BorderLayout.CENTER);

        // Aksi ketika tombol Simpan ditekan
        btnSimpan.addActionListener(e -> {
            String kode = txtKode.getText().trim();
            String nama = txtNama.getText().trim();
            String sksStr = txtSks.getText().trim();
            Dosen dosenTerpilih = (Dosen) cbDosen.getSelectedItem();

            if (!kode.isEmpty() && !nama.isEmpty() && !sksStr.isEmpty() && dosenTerpilih != null) {
                try {
                    int sks = Integer.parseInt(sksStr);
                    MataKuliah mkBaru = new MataKuliah(kode, nama, sks, dosenTerpilih);
                    listMK.add(mkBaru); // Masuk ke storage static

                    // Tambahkan baris baru ke tabel UI
                    tableModel.addRow(new Object[]{
                            mkBaru.getKodeMK(),
                            mkBaru.getNamaMK(),
                            mkBaru.getSks(),
                            mkBaru.getDosenPengampu().getNama()
                    });

                    // Reset form input
                    txtKode.setText("");
                    txtNama.setText("");
                    txtSks.setText("");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Format SKS harus berupa angka angka!");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Semua data wajib diisi dan pastikan data Dosen sudah ada!");
            }
        });
    }

    // Method main lokal untuk melakukan uji coba (Testing) mandiri
    public static void main(String[] args) {
        // Simulasi data dosen dummy agar dropdown tidak kosong saat ditest terpisah
        DosenForm.listDosen.add(new Dosen("0429038801", "Ruth Mariana Bunga Wadu, S.Kom.,MMSi", "ruthbungawadu@upnvj.ac.id"));
        DosenForm.listDosen.add(new Dosen("0419107003", "Dr. Tjahjanto, S.Kom., M.M.", "tjahjanto@upnvj.ac.id"));

        SwingUtilities.invokeLater(() -> new MataKuliahForm().setVisible(true));
    }
}