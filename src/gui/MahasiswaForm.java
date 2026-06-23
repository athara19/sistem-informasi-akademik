package gui;

import model.Mahasiswa;

import javax.swing.*;
import java.awt.*;

public class MahasiswaForm extends JFrame {

    public MahasiswaForm() {

        setTitle("Profil Mahasiswa");
        setSize(500, 350);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // ================= DATA =================
        Mahasiswa mhs = new Mahasiswa(
                "M001",
                "Budi Santoso",
                "Informatika",
                3
        );

        // ================= HEADER =================
        JPanel header = new JPanel();
        header.setBackground(new Color(41, 128, 185));
        header.setPreferredSize(new Dimension(500, 50));

        JLabel title = new JLabel("PROFIL MAHASISWA");
        title.setForeground(Color.WHITE);
        title.setFont(new Font("SansSerif", Font.BOLD, 18));

        header.add(title);

        // ================= BODY (GRID RAPI) =================
        JPanel body = new JPanel(new GridBagLayout());
        body.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        Font labelFont = new Font("SansSerif", Font.BOLD, 13);

        // ID
        gbc.gridx = 0;
        gbc.gridy = 0;
        JLabel l1 = new JLabel("ID");
        l1.setFont(labelFont);
        body.add(l1, gbc);

        gbc.gridx = 1;
        body.add(new JLabel(mhs.getId()), gbc);

        // Nama
        gbc.gridx = 0;
        gbc.gridy = 1;
        JLabel l2 = new JLabel("Nama");
        l2.setFont(labelFont);
        body.add(l2, gbc);

        gbc.gridx = 1;
        body.add(new JLabel(mhs.getNama()), gbc);

        // Prodi
        gbc.gridx = 0;
        gbc.gridy = 2;
        JLabel l3 = new JLabel("Prodi");
        l3.setFont(labelFont);
        body.add(l3, gbc);

        gbc.gridx = 1;
        body.add(new JLabel(mhs.getProdi()), gbc);

        // Semester
        gbc.gridx = 0;
        gbc.gridy = 3;
        JLabel l4 = new JLabel("Semester");
        l4.setFont(labelFont);
        body.add(l4, gbc);

        gbc.gridx = 1;
        body.add(new JLabel(String.valueOf(mhs.getSemester())), gbc);

        // ================= FOOTER =================
        JPanel footer = new JPanel();
        footer.setPreferredSize(new Dimension(500, 60));

        JButton btnBack = new JButton("Kembali");

        btnBack.setBackground(new Color(231, 76, 60));
        btnBack.setForeground(Color.WHITE);
        btnBack.setFocusPainted(false);

        footer.add(btnBack);

        btnBack.addActionListener(e -> {
            new DashboardForm().setVisible(true);
            dispose();
        });

        // ================= ADD =================
        add(header, BorderLayout.NORTH);
        add(body, BorderLayout.CENTER);
        add(footer, BorderLayout.SOUTH);
    }
}