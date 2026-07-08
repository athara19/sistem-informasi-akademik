package gui;

import dao.DosenDAO;
import model.Dosen;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class DosenForm extends JFrame {

    private JTextField txtNidn, txtNama, txtEmail, txtCari;
    private JButton btnTambah, btnEdit, btnHapus, btnCari, btnRefresh;
    private JTable table;
    private DefaultTableModel tableModel;
    private DosenDAO dosenDAO;

    public DosenForm() {

        dosenDAO = new DosenDAO();

        setTitle("Kelola Data Dosen");
        setSize(700,500);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10,10));
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JPanel panelForm = new JPanel(new GridLayout(3,2,5,5));
        panelForm.setBorder(BorderFactory.createTitledBorder("Input Data Dosen"));

        panelForm.add(new JLabel("NIDN :"));
        txtNidn = new JTextField();
        panelForm.add(txtNidn);

        panelForm.add(new JLabel("Nama :"));
        txtNama = new JTextField();
        panelForm.add(txtNama);

        panelForm.add(new JLabel("Email :"));
        txtEmail = new JTextField();
        panelForm.add(txtEmail);

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

        panelCari.add(new JLabel("Cari Nama / NIDN :"));
        panelCari.add(txtCari);
        panelCari.add(btnCari);
        panelCari.add(btnRefresh);

        JPanel panelAtas = new JPanel(new BorderLayout());

        panelAtas.add(panelForm, BorderLayout.NORTH);
        panelAtas.add(panelAksi, BorderLayout.CENTER);
        panelAtas.add(panelCari, BorderLayout.SOUTH);

        add(panelAtas, BorderLayout.NORTH);

        tableModel = new DefaultTableModel(
                new String[]{
                        "NIDN",
                        "Nama",
                        "Email",
                        "Username"
                },0);

        table = new JTable(tableModel);

        add(new JScrollPane(table), BorderLayout.CENTER);

        btnTambah.addActionListener(e -> tambahData());
        btnEdit.addActionListener(e -> editData());
        btnHapus.addActionListener(e -> hapusData());
        btnCari.addActionListener(e -> cariData());
        btnRefresh.addActionListener(e -> {
            loadData();
            kosongkanForm();
        });

        table.getSelectionModel().addListSelectionListener(
                e -> isiFormDariTabel()
        );

        loadData();

    }
    private void tambahData() {

        if (!validasiInput()) return;

        Dosen dosen = new Dosen(
                txtNidn.getText(),
                txtNama.getText(),
                txtEmail.getText(),
                txtNidn.getText()
        );

        if (dosenDAO.insertDosen(dosen)) {

            JOptionPane.showMessageDialog(this,
                    "Data berhasil ditambah!\n\n" +
                            "Username : " + txtNidn.getText() +
                            "\nPassword : " + txtNidn.getText());

            kosongkanForm();

            loadData();

        } else {

            JOptionPane.showMessageDialog(this,
                    "Data gagal ditambah!");

        }

    }

    private void editData() {

        if (!validasiInput()) return;

        Dosen dosen = new Dosen(
                txtNidn.getText(),
                txtNama.getText(),
                txtEmail.getText(),
                txtNidn.getText()
        );

        if (dosenDAO.updateDosen(dosen)) {

            JOptionPane.showMessageDialog(this,
                    "Data berhasil diubah!");

            kosongkanForm();

            loadData();

        } else {

            JOptionPane.showMessageDialog(this,
                    "Data gagal diubah!");

        }

    }

    private void hapusData() {

        String nidn = txtNidn.getText();

        if (nidn.isEmpty()) {

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

            if (dosenDAO.deleteDosen(nidn)) {

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

        List<Dosen> list = dosenDAO.getAllDosen();

        for (Dosen d : list) {

            tableModel.addRow(new Object[]{
                    d.getNidn(),
                    d.getNama(),
                    d.getEmail(),
                    d.getUsername()
            });

        }

    }

    private void cariData() {

        tableModel.setRowCount(0);

        List<Dosen> list =
                dosenDAO.searchDosen(txtCari.getText());

        for (Dosen d : list) {

            tableModel.addRow(new Object[]{
                    d.getNidn(),
                    d.getNama(),
                    d.getEmail(),
                    d.getUsername()
            });

        }

    }

    private void isiFormDariTabel() {

        int baris = table.getSelectedRow();

        if (baris >= 0) {

            txtNidn.setText(tableModel.getValueAt(baris,0).toString());

            txtNama.setText(tableModel.getValueAt(baris,1).toString());

            txtEmail.setText(tableModel.getValueAt(baris,2).toString());

            txtNidn.setEnabled(false);

        }

    }

    private void kosongkanForm() {

        txtNidn.setText("");
        txtNama.setText("");
        txtEmail.setText("");
        txtCari.setText("");

        txtNidn.setEnabled(true);

    }

    private boolean validasiInput() {

        if (txtNidn.getText().isEmpty()
                || txtNama.getText().isEmpty()
                || txtEmail.getText().isEmpty()) {

            JOptionPane.showMessageDialog(this,
                    "Semua kolom wajib diisi!",
                    "Peringatan",
                    JOptionPane.WARNING_MESSAGE);

            return false;

        }

        return true;

    }

}