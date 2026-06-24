package gui;

import model.Mahasiswa;

import javax.swing.*;
import java.awt.*;

public class MahasiswaForm extends JFrame {

    private Mahasiswa mahasiswa;

    public MahasiswaForm() {

        mahasiswa = new Mahasiswa(
                "231001",
                "Budi Santoso",
                "Informatika",
                4
        );

        setTitle("Dashboard Mahasiswa");
        setSize(600, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setLayout(new BorderLayout());

        // Header
        JLabel lblTitle = new JLabel(
                "SISTEM INFORMASI AKADEMIK MAHASISWA",
                SwingConstants.CENTER
        );

        lblTitle.setFont(new Font("Arial", Font.BOLD, 18));
        lblTitle.setBorder(BorderFactory.createEmptyBorder(20,10,20,10));

        add(lblTitle, BorderLayout.NORTH);

        // Menu
        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new GridLayout(7,1,10,10));
        menuPanel.setBorder(BorderFactory.createEmptyBorder(20,50,20,50));

        JButton btnProfil = new JButton("Lihat Profil");
        JButton btnKRS = new JButton("Ambil Mata Kuliah (KRS)");
        JButton btnJadwal = new JButton("Lihat Jadwal");
        JButton btnNilai = new JButton("Lihat Nilai");
        JButton btnKHS = new JButton("Lihat KHS");
        JButton btnTranskrip = new JButton("Lihat Transkrip");
        JButton btnLogout = new JButton("Logout");

        menuPanel.add(btnProfil);
        menuPanel.add(btnKRS);
        menuPanel.add(btnJadwal);
        menuPanel.add(btnNilai);
        menuPanel.add(btnKHS);
        menuPanel.add(btnTranskrip);
        menuPanel.add(btnLogout);

        add(menuPanel, BorderLayout.CENTER);

        // Event
        btnProfil.addActionListener(e -> {

            JOptionPane.showMessageDialog(this,
                    "NIM : " + mahasiswa.getNim() +
                            "\nNama : " + mahasiswa.getNama() +
                            "\nProdi : " + mahasiswa.getProdi() +
                            "\nSemester : " + mahasiswa.getSemester()
            );

        });

        btnKRS.addActionListener(e ->
                JOptionPane.showMessageDialog(this,
                        "Menu Pengambilan KRS")
        );

        btnJadwal.addActionListener(e ->
                JOptionPane.showMessageDialog(this,
                        "Menu Jadwal Kuliah")
        );

        btnNilai.addActionListener(e ->
                JOptionPane.showMessageDialog(this,
                        "Menu Nilai")
        );

        btnKHS.addActionListener(e ->
                JOptionPane.showMessageDialog(this,
                        "Menu KHS")
        );

        btnTranskrip.addActionListener(e ->
                JOptionPane.showMessageDialog(this,
                        "Menu Transkrip Nilai")
        );

        btnLogout.addActionListener(e -> {

            int pilih = JOptionPane.showConfirmDialog(
                    this,
                    "Yakin ingin logout?",
                    "Logout",
                    JOptionPane.YES_NO_OPTION
            );

            if (pilih == JOptionPane.YES_OPTION) {

                new LoginForm().setVisible(true);
                dispose();

            }

        });
    }
}