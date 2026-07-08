package gui;

import service.Session;

import javax.swing.*;
import java.awt.*;

public class DashboardMahasiswa extends JFrame{

    public DashboardMahasiswa(){

        setTitle("Dashboard Mahasiswa");

        setSize(500,400);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JLabel title = new JLabel(
                "Dashboard Mahasiswa",
                SwingConstants.CENTER);

        title.setFont(new Font("Arial",Font.BOLD,20));

        JButton btnKRS = new JButton("Isi KRS");

        JButton btnNilai = new JButton("Lihat Nilai");

        JButton btnKHS = new JButton("KHS");

        JButton btnTranskrip = new JButton("Transkrip");

        JButton btnLogout = new JButton("Logout");

        btnKRS.addActionListener(e->{

            new KRSForm().setVisible(true);

        });

        btnNilai.addActionListener(e -> {

            new NilaiForm().setVisible(true);

        });

        btnKHS.addActionListener(e -> {

            new KHSForm().setVisible(true);

        });

        btnTranskrip.addActionListener(e -> {

            new TranskripForm().setVisible(true);

        });

        btnLogout.addActionListener(e->{

            Session.logout();

            dispose();

            new LoginForm().setVisible(true);

        });

        JPanel panel = new JPanel(new GridLayout(7,1,10,10));

        panel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));

        panel.add(title);

        panel.add(new JLabel(
                Session.getCurrentUser().getNama(),
                SwingConstants.CENTER));

        panel.add(btnKRS);

        panel.add(btnNilai);

        panel.add(btnKHS);

        panel.add(btnTranskrip);

        panel.add(btnLogout);


        add(panel);

    }

}