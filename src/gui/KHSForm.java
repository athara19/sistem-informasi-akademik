package gui;

import dao.KHSDAO;
import model.KHS;
import model.Mahasiswa;
import service.Session;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class KHSForm extends JFrame {

    private JTable table;
    private DefaultTableModel tableModel;

    private JLabel lblIP;
    private JLabel lblTotalSKS;

    private KHSDAO dao;

    public KHSForm() {

        dao = new KHSDAO();

        setTitle("Kartu Hasil Studi");
        setSize(800,500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JLabel title = new JLabel(
                "KARTU HASIL STUDI",
                SwingConstants.CENTER);

        title.setFont(new Font("Arial",Font.BOLD,20));

        add(title,BorderLayout.NORTH);

        tableModel = new DefaultTableModel(
                new String[]{
                        "Kode MK",
                        "Mata Kuliah",
                        "SKS",
                        "Nilai"
                },0);

        table = new JTable(tableModel);

        add(new JScrollPane(table),BorderLayout.CENTER);

        JPanel panelBawah = new JPanel(new GridLayout(2,1));

        lblTotalSKS = new JLabel("Total SKS : 0");

        lblIP = new JLabel("IP : 0.00");

        panelBawah.add(lblTotalSKS);
        panelBawah.add(lblIP);

        add(panelBawah,BorderLayout.SOUTH);

        loadData();

    }

    private void loadData(){

        tableModel.setRowCount(0);

        Mahasiswa m =
                (Mahasiswa) Session.getCurrentUser();

        List<KHS> list =
                dao.getKHS(m.getNim());

        int totalSKS = 0;
        double totalBobot = 0;

        for(KHS k : list){

            tableModel.addRow(new Object[]{
                    k.getKodeMk(),
                    k.getNamaMk(),
                    k.getSks(),
                    k.getNilai()
            });

            totalSKS += k.getSks();

            totalBobot += konversiBobot(k.getNilai()) * k.getSks();

        }

        lblTotalSKS.setText(
                "Total SKS : " + totalSKS);

        if(totalSKS>0){

            double ip = totalBobot / totalSKS;

            lblIP.setText(
                    String.format("IP : %.2f",ip));

        }

    }

    private double konversiBobot(double nilai){

        if(nilai>=85) return 4.0;
        if(nilai>=80) return 3.7;
        if(nilai>=75) return 3.3;
        if(nilai>=70) return 3.0;
        if(nilai>=65) return 2.7;
        if(nilai>=60) return 2.3;
        if(nilai>=55) return 2.0;
        if(nilai>=40) return 1.0;

        return 0;

    }

}