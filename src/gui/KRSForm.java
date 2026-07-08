package gui;

import dao.KRSDAO;
import model.KRS;
import model.MataKuliah;
import service.Session;
import model.Mahasiswa;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class KRSForm extends JFrame {

    private JTable tableMatkul;
    private JTable tableKRS;

    private DefaultTableModel modelMatkul;
    private DefaultTableModel modelKRS;

    private JButton btnAmbil;
    private JButton btnHapus;

    private KRSDAO dao;

    public KRSForm() {

        dao = new KRSDAO();

        setTitle("Kartu Rencana Studi");

        setSize(850,600);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        setLayout(new BorderLayout(10,10));

        JLabel title = new JLabel(
                "KARTU RENCANA STUDI",
                SwingConstants.CENTER);

        title.setFont(new Font("Arial",Font.BOLD,20));

        add(title,BorderLayout.NORTH);

        JPanel center = new JPanel(new GridLayout(2,1));

        //------------------------
        // Tabel Mata Kuliah
        //------------------------

        modelMatkul = new DefaultTableModel(

                new String[]{
                        "Kode",
                        "Nama",
                        "SKS",
                        "NIDN"
                },0

        );

        tableMatkul = new JTable(modelMatkul);

        JPanel p1 = new JPanel(new BorderLayout());

        p1.setBorder(
                BorderFactory.createTitledBorder(
                        "Daftar Mata Kuliah"));

        p1.add(new JScrollPane(tableMatkul));

        btnAmbil = new JButton("Ambil Mata Kuliah");

        p1.add(btnAmbil,BorderLayout.SOUTH);

        //------------------------
        // Tabel KRS
        //------------------------

        modelKRS = new DefaultTableModel(

                new String[]{
                        "ID",
                        "Kode",
                        "Nama Mata Kuliah",
                        "SKS",
                        "Dosen"
                },0

        );

        tableKRS = new JTable(modelKRS);

        JPanel p2 = new JPanel(new BorderLayout());

        p2.setBorder(
                BorderFactory.createTitledBorder(
                        "KRS Saya"));

        p2.add(new JScrollPane(tableKRS));

        btnHapus = new JButton("Hapus");

        p2.add(btnHapus,BorderLayout.SOUTH);

        center.add(p1);

        center.add(p2);

        add(center,BorderLayout.CENTER);

        btnAmbil.addActionListener(e->ambilMK());

        btnHapus.addActionListener(e->hapusMK());

        loadMatkul();

        loadKRS();

    }
    private void loadMatkul() {

        modelMatkul.setRowCount(0);

        List<MataKuliah> list = dao.getAllMatkul();

        for (MataKuliah mk : list) {

            modelMatkul.addRow(new Object[]{
                    mk.getKodeMk(),
                    mk.getNamaMk(),
                    mk.getSks(),
                    mk.getNidn()
            });

        }

    }

    private void loadKRS(){

        System.out.println("LOAD KRS DIPANGGIL");

        modelKRS.setRowCount(0);

        Mahasiswa m=(Mahasiswa)Session.getCurrentUser();

        List<KRS> list=
                dao.getKRSMahasiswa(m.getNim());

        for(KRS k:list){

            modelKRS.addRow(new Object[]{

                    k.getIdKrs(),

                    k.getKodeMk(),

                    k.getNamaMk(),

                    k.getSks(),

                    k.getNidn()

            });

        }

    }

    private void ambilMK() {

        int row = tableMatkul.getSelectedRow();

        if (row == -1) {

            JOptionPane.showMessageDialog(this,
                    "Pilih Mata Kuliah!");

            return;

        }

        Mahasiswa m = (Mahasiswa) Session.getCurrentUser();
        JOptionPane.showMessageDialog(
                this,
                "NIM Login = " + m.getNim()
        );
        System.out.println("LOGIN NIM = " + m.getNim());

        String kode =
                modelMatkul.getValueAt(row,0).toString();

        KRS krs = new KRS(
                m.getNim(),
                kode
        );

        if (dao.insertKRS(krs)) {

            JOptionPane.showMessageDialog(this,
                    "Mata Kuliah berhasil diambil.");

            loadKRS();

        } else {

            JOptionPane.showMessageDialog(this,
                    "Gagal mengambil Mata Kuliah.");

        }

    }

    private void hapusMK() {

        int row = tableKRS.getSelectedRow();

        if (row == -1) {

            JOptionPane.showMessageDialog(this,
                    "Pilih KRS yang akan dihapus!");

            return;

        }

        int id = Integer.parseInt(
                modelKRS.getValueAt(row,0).toString()
        );

        if (dao.deleteKRS(id)) {

            JOptionPane.showMessageDialog(this,
                    "KRS berhasil dihapus.");

            loadKRS();

        } else {

            JOptionPane.showMessageDialog(this,
                    "Gagal menghapus KRS.");

        }

    }

}