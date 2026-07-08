package gui;

import dao.NilaiDAO;
import model.NilaiView;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class NilaiForm extends JFrame {

    private JTable table;

    private DefaultTableModel tableModel;

    private JButton btnSimpan;

    private NilaiDAO dao;

    public NilaiForm(){

        dao = new NilaiDAO();

        setTitle("Input Nilai Mahasiswa");

        setSize(1000,600);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        setLayout(new BorderLayout());

        JLabel title = new JLabel(
                "INPUT NILAI MAHASISWA",
                SwingConstants.CENTER);

        title.setFont(new Font("Arial",Font.BOLD,20));

        add(title,BorderLayout.NORTH);

        tableModel = new DefaultTableModel(

                new String[]{

                        "NIM",

                        "Nama",

                        "Kode MK",

                        "Mata Kuliah",

                        "Nilai"

                },0){

            @Override
            public boolean isCellEditable(int row,int column){

                return column==4;

            }

        };

        table = new JTable(tableModel);

        add(new JScrollPane(table),BorderLayout.CENTER);

        btnSimpan = new JButton("Simpan Nilai");

        add(btnSimpan,BorderLayout.SOUTH);

        btnSimpan.addActionListener(e -> simpanNilai());

        loadData();

    }
    private void loadData() {

        tableModel.setRowCount(0);

        List<NilaiView> list = dao.getDataNilai();

        for (NilaiView n : list) {

            tableModel.addRow(new Object[]{

                    n.getNim(),

                    n.getNama(),

                    n.getKodeMk(),

                    n.getNamaMk(),

                    n.getNilai()

            });

        }

    }
    private void simpanNilai() {

        if (table.isEditing()) {
            table.getCellEditor().stopCellEditing();
        }

        for (int i = 0; i < tableModel.getRowCount(); i++) {

            try {

                String nim = tableModel.getValueAt(i, 0).toString();

                String kodeMk = tableModel.getValueAt(i, 2).toString();

                double nilai = Double.parseDouble(
                        tableModel.getValueAt(i, 4).toString());

                System.out.println(
                        "NIM = " + nim +
                                " | MK = " + kodeMk +
                                " | Nilai = " + nilai);

                dao.simpanNilai(nim, kodeMk, nilai);

            } catch (Exception e) {

                JOptionPane.showMessageDialog(
                        this,
                        "Nilai pada baris " + (i + 1) + " tidak valid!"
                );

                return;
            }
        }

        JOptionPane.showMessageDialog(this, "Nilai berhasil disimpan!");

        loadData();
    }

//    private void simpanNilai() {
//
//        for (int i = 0; i < tableModel.getRowCount(); i++) {
//
//            try {
//
//                int idKrs = Integer.parseInt(
//                        tableModel.getValueAt(i, 0).toString());
//
//                double nilai = Double.parseDouble(
//                        tableModel.getValueAt(i, 5).toString());
//
//                dao.simpanNilai(idKrs, nilai);
//
//                tableModel.setValueAt(
//                        service.KonversiNilai.getHuruf(nilai),
//                        i,
//                        6
//                );
//
//            } catch (Exception e) {
//
//                JOptionPane.showMessageDialog(
//                        this,
//                        "Nilai pada baris " + (i + 1) + " tidak valid!"
//                );
//
//                return;
//
//            }
//
//        }
//
//        JOptionPane.showMessageDialog(
//                this,
//                "Semua nilai berhasil disimpan.");
//
//        loadData();
//
//    }

}