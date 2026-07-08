package gui;

import dao.KHSDAO;
import model.KHS;
import model.Mahasiswa;
import service.Session;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class TranskripForm extends JFrame {

    private JTable table;
    private DefaultTableModel model;

    private JLabel lblSKS;
    private JLabel lblIPK;

    private KHSDAO dao;

    public TranskripForm(){

        dao = new KHSDAO();

        setTitle("Transkrip Nilai");

        setSize(850,550);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        setLayout(new BorderLayout());

        JLabel title = new JLabel(
                "TRANSKRIP NILAI",
                SwingConstants.CENTER);

        title.setFont(new Font("Arial",Font.BOLD,20));

        add(title,BorderLayout.NORTH);

        model = new DefaultTableModel(

                new String[]{
                        "Kode MK",
                        "Mata Kuliah",
                        "SKS",
                        "Nilai"
                },0);

        table = new JTable(model);

        add(new JScrollPane(table),BorderLayout.CENTER);

        JPanel bawah = new JPanel(new GridLayout(2,1));

        lblSKS = new JLabel("Total SKS : 0");

        lblIPK = new JLabel("IPK : 0.00");

        bawah.add(lblSKS);

        bawah.add(lblIPK);

        add(bawah,BorderLayout.SOUTH);

        loadData();

    }

    private void loadData(){

        model.setRowCount(0);

        Mahasiswa m =
                (Mahasiswa) Session.getCurrentUser();

        List<KHS> list =
                dao.getKHS(m.getNim());

        int totalSKS = 0;

        double totalBobot = 0;

        for(KHS k:list){

            model.addRow(new Object[]{

                    k.getKodeMk(),

                    k.getNamaMk(),

                    k.getSks(),

                    k.getNilai()

            });

            totalSKS += k.getSks();

            totalBobot +=
                    konversiBobot(k.getNilai()) * k.getSks();

        }

        lblSKS.setText(
                "Total SKS : " + totalSKS);

        if(totalSKS>0){

            lblIPK.setText(

                    String.format(
                            "IPK : %.2f",
                            totalBobot/totalSKS)

            );

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