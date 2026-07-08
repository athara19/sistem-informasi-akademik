package gui;

import dao.MahasiswaDAO;
import model.Mahasiswa;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class MahasiswaForm extends JFrame {

    private JTextField txtNim, txtNama, txtProdi, txtSemester, txtCari;
    private JButton btnTambah, btnEdit, btnHapus, btnCari, btnRefresh;
    private JTable table;
    private DefaultTableModel tableModel;
    private MahasiswaDAO mahasiswaDAO;

    public MahasiswaForm() {

        mahasiswaDAO = new MahasiswaDAO();

        setTitle("Kelola Data Mahasiswa");
        setSize(700,500);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10,10));

        JPanel panelForm = new JPanel(new GridLayout(4,2,5,5));
        panelForm.setBorder(BorderFactory.createTitledBorder("Input Data Mahasiswa"));

        panelForm.add(new JLabel("NIM :"));
        txtNim = new JTextField();
        panelForm.add(txtNim);

        panelForm.add(new JLabel("Nama :"));
        txtNama = new JTextField();
        panelForm.add(txtNama);

        panelForm.add(new JLabel("Prodi :"));
        txtProdi = new JTextField();
        panelForm.add(txtProdi);

        panelForm.add(new JLabel("Semester :"));
        txtSemester = new JTextField();
        panelForm.add(txtSemester);

        JPanel panelAksi = new JPanel(new FlowLayout());

        btnTambah = new JButton("Tambah");
        btnEdit = new JButton("Edit");
        btnHapus = new JButton("Hapus");

        panelAksi.add(btnTambah);
        panelAksi.add(btnEdit);
        panelAksi.add(btnHapus);

        JPanel panelCari = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        txtCari = new JTextField(15);

        btnCari = new JButton("Cari");

        btnRefresh = new JButton("Refresh");

        panelCari.add(new JLabel("Cari Nama / NIM :"));

        panelCari.add(txtCari);

        panelCari.add(btnCari);

        panelCari.add(btnRefresh);

        JPanel panelAtas = new JPanel(new BorderLayout());

        panelAtas.add(panelForm,BorderLayout.NORTH);

        panelAtas.add(panelAksi,BorderLayout.CENTER);

        panelAtas.add(panelCari,BorderLayout.SOUTH);

        add(panelAtas,BorderLayout.NORTH);

        tableModel = new DefaultTableModel(

                new String[]{
                        "NIM",
                        "Nama",
                        "Prodi",
                        "Semester",
                        "Username"
                },0

        );

        table = new JTable(tableModel);

        add(new JScrollPane(table),BorderLayout.CENTER);

        btnTambah.addActionListener(e->tambahData());
        btnEdit.addActionListener(e->editData());
        btnHapus.addActionListener(e->hapusData());
        btnRefresh.addActionListener(e->loadData());
        btnCari.addActionListener(e->cariData());

        table.getSelectionModel().addListSelectionListener(
                e->isiFormDariTabel());

        loadData();

    }
    private void tambahData() {

        if (!validasiInput()) return;

        try {

            Mahasiswa mahasiswa = new Mahasiswa(
                    txtNim.getText(),
                    txtNama.getText(),
                    txtProdi.getText(),
                    Integer.parseInt(txtSemester.getText()),
                    txtNim.getText()
            );

            boolean berhasil = mahasiswaDAO.insertMahasiswa(mahasiswa);

            if (berhasil) {

                JOptionPane.showMessageDialog(this,
                        "Data berhasil ditambah!\n\n" +
                                "Username : " + txtNim.getText() +
                                "\nPassword : " + txtNim.getText());

                kosongkanForm();
                loadData();

            } else {

                JOptionPane.showMessageDialog(this,
                        "Data gagal ditambah!");

            }

        } catch (Exception ex) {

            JOptionPane.showMessageDialog(this,
                    "Semester harus berupa angka!");

        }

    }

    private void editData() {

        if (!validasiInput()) return;

        try {

            Mahasiswa mahasiswa = new Mahasiswa(
                    txtNim.getText(),
                    txtNama.getText(),
                    txtProdi.getText(),
                    Integer.parseInt(txtSemester.getText()),
                    txtNim.getText()
            );

            if (mahasiswaDAO.updateMahasiswa(mahasiswa)) {

                JOptionPane.showMessageDialog(this,
                        "Data berhasil diubah!");

                kosongkanForm();
                loadData();

            } else {

                JOptionPane.showMessageDialog(this,
                        "Data gagal diubah!");

            }

        } catch (Exception ex) {

            JOptionPane.showMessageDialog(this,
                    ex.getMessage());

        }

    }

    private void hapusData() {

        String nim = txtNim.getText();

        if (nim.isEmpty()) {

            JOptionPane.showMessageDialog(this,
                    "Pilih data terlebih dahulu!");

            return;

        }

        int confirm = JOptionPane.showConfirmDialog(
                this,
                "Yakin hapus data?",
                "Konfirmasi",
                JOptionPane.YES_NO_OPTION
        );

        if (confirm == JOptionPane.YES_OPTION) {

            if (mahasiswaDAO.deleteMahasiswa(nim)) {

                JOptionPane.showMessageDialog(this,
                        "Data berhasil dihapus!");

                kosongkanForm();
                loadData();

            } else {

                JOptionPane.showMessageDialog(this,
                        "Data gagal dihapus!");

            }

        }

    }

    private void loadData() {

        tableModel.setRowCount(0);

        List<Mahasiswa> list = mahasiswaDAO.getAllMahasiswa();

        for (Mahasiswa m : list) {

            tableModel.addRow(new Object[]{
                    m.getNim(),
                    m.getNama(),
                    m.getProdi(),
                    m.getSemester(),
                    m.getUsername()
            });

        }

    }

    private void cariData() {

        tableModel.setRowCount(0);

        List<Mahasiswa> list =
                mahasiswaDAO.searchMahasiswa(txtCari.getText());

        for (Mahasiswa m : list) {

            tableModel.addRow(new Object[]{
                    m.getNim(),
                    m.getNama(),
                    m.getProdi(),
                    m.getSemester(),
                    m.getUsername()
            });

        }

    }

    private void isiFormDariTabel() {

        int baris = table.getSelectedRow();

        if (baris >= 0) {

            txtNim.setText(tableModel.getValueAt(baris,0).toString());
            txtNama.setText(tableModel.getValueAt(baris,1).toString());
            txtProdi.setText(tableModel.getValueAt(baris,2).toString());
            txtSemester.setText(tableModel.getValueAt(baris,3).toString());

            txtNim.setEnabled(false);

        }

    }

    private void kosongkanForm() {

        txtNim.setText("");
        txtNama.setText("");
        txtProdi.setText("");
        txtSemester.setText("");
        txtCari.setText("");

        txtNim.setEnabled(true);

    }

    private boolean validasiInput() {

        if (txtNim.getText().isEmpty()
                || txtNama.getText().isEmpty()
                || txtProdi.getText().isEmpty()
                || txtSemester.getText().isEmpty()) {

            JOptionPane.showMessageDialog(this,
                    "Semua kolom wajib diisi!",
                    "Peringatan",
                    JOptionPane.WARNING_MESSAGE);

            return false;

        }

        return true;

    }

}