package gui;

import javax.swing.*;

public class DashboardForm extends JFrame {

    public DashboardForm() {
        setTitle("Dashboard");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel label = new JLabel("Dashboard Sistem Akademik");
        add(label);
    }
}