package gui;

import model.Person;
import service.LoginService;
import service.Session;

import javax.swing.*;
import java.awt.*;

public class LoginForm extends JFrame {

    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton btnLogin;

    public LoginForm() {

        setTitle("Sistem Informasi Akademik");
        setSize(400,250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        initComponents();

    }

    private void initComponents(){

        JLabel lblTitle = new JLabel("LOGIN SISTEM AKADEMIK");
        lblTitle.setFont(new Font("Arial",Font.BOLD,18));

        JLabel lblUsername = new JLabel("Username");

        JLabel lblPassword = new JLabel("Password");

        txtUsername = new JTextField();

        txtPassword = new JPasswordField();

        btnLogin = new JButton("LOGIN");

        btnLogin.addActionListener(e->login());

        JPanel panel = new JPanel(new GridLayout(0,1,10,10));

        panel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));

        panel.add(lblTitle);

        panel.add(lblUsername);

        panel.add(txtUsername);

        panel.add(lblPassword);

        panel.add(txtPassword);

        panel.add(btnLogin);

        add(panel);

    }

    private void login(){

        String username = txtUsername.getText();

        String password = String.valueOf(txtPassword.getPassword());

        LoginService service = new LoginService();

        Person person = service.login(username,password);

        if(person==null){

            JOptionPane.showMessageDialog(this,
                    "Username atau Password Salah!");

            return;

        }

        Session.login(person);

        dispose();

        switch (person.getRole()){

            case "ADMIN":

                new DashboardAdmin().setVisible(true);

                break;

            case "DOSEN":

                new DashboardDosen().setVisible(true);

                break;

            case "MAHASISWA":

                new DashboardMahasiswa().setVisible(true);

                break;

        }

    }

}