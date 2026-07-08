package gui;

import service.Session;

import javax.swing.*;
import java.awt.*;

public class DashboardAdmin extends JFrame {

    private JButton btnMahasiswa;
    private JButton btnDosen;
    private JButton btnMatkul;
    private JButton btnLogout;

    public DashboardAdmin() {

        setTitle("Dashboard Admin");
        setSize(600,400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        initComponents();

    }

    private void initComponents(){

        JLabel lblTitle = new JLabel(
                "DASHBOARD ADMIN",
                SwingConstants.CENTER);

        lblTitle.setFont(new Font("Arial",Font.BOLD,22));

        JLabel lblUser = new JLabel(
                "Login sebagai : "
                        + Session.getCurrentUser().getNama(),
                SwingConstants.CENTER);

        btnMahasiswa = new JButton("Data Mahasiswa");

        btnDosen = new JButton("Data Dosen");

        btnMatkul = new JButton("Data Mata Kuliah");

        btnLogout = new JButton("Logout");

        btnMahasiswa.addActionListener(e->{

            new MahasiswaForm().setVisible(true);

        });

        btnDosen.addActionListener(e->{

            new DosenForm().setVisible(true);

        });

        btnMatkul.addActionListener(e->{

            new MataKuliahForm().setVisible(true);

        });

        btnLogout.addActionListener(e->{

            Session.logout();

            dispose();

            new LoginForm().setVisible(true);

        });

        JPanel panel = new JPanel(new GridLayout(6,1,10,10));

        panel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));

        panel.add(lblTitle);

        panel.add(lblUser);

        panel.add(btnMahasiswa);

        panel.add(btnDosen);

        panel.add(btnMatkul);

        panel.add(btnLogout);

        add(panel);

    }

}