package gui;

import service.Session;

import javax.swing.*;
import java.awt.*;

public class DashboardDosen extends JFrame {

    public DashboardDosen(){

        setTitle("Dashboard Dosen");

        setSize(500,300);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JLabel title = new JLabel(
                "Dashboard Dosen",
                SwingConstants.CENTER);

        title.setFont(new Font("Arial",Font.BOLD,20));

        JButton btnNilai = new JButton("Input Nilai");

        JButton btnLogout = new JButton("Logout");

        btnNilai.addActionListener(e->{

            new NilaiForm().setVisible(true);

        });

        btnLogout.addActionListener(e->{

            Session.logout();

            dispose();

            new LoginForm().setVisible(true);

        });

        JPanel panel = new JPanel(new GridLayout(4,1,10,10));

        panel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));

        panel.add(title);

        panel.add(new JLabel(
                "Halo "
                        + Session.getCurrentUser().getNama(),
                SwingConstants.CENTER));

        panel.add(btnNilai);

        panel.add(btnLogout);

        add(panel);

    }

}