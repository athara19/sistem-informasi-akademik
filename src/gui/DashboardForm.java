package gui;

import javax.swing.*;

public class DashboardForm extends JFrame {

    public DashboardForm() {

        setTitle("Dashboard Sistem Akademik");
        setSize(500,400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JButton btnMahasiswa = new JButton("Data Mahasiswa");
        JButton btnDosen = new JButton("Data Dosen");
        JButton btnMatkul = new JButton("Data Mata Kuliah");
        JButton btnKRS = new JButton("KRS");
        JButton btnNilai = new JButton("Nilai");
        JButton btnKHS = new JButton("KHS");

        setLayout(null);

        btnMahasiswa.setBounds(150,40,200,30);
        btnDosen.setBounds(150,90,200,30);
        btnMatkul.setBounds(150,140,200,30);
        btnKRS.setBounds(150,190,200,30);
        btnNilai.setBounds(150,240,200,30);
        btnKHS.setBounds(150,290,200,30);

        add(btnMahasiswa);
        add(btnDosen);
        add(btnMatkul);
        add(btnKRS);
        add(btnNilai);
        add(btnKHS);
    }
}