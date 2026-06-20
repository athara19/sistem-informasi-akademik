package gui;

import javax.swing.*;

public class LoginForm extends JFrame {

    public LoginForm() {
        setTitle("Login");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel lblUser = new JLabel("Username");
        JTextField txtUser = new JTextField();

        JLabel lblPass = new JLabel("Password");
        JPasswordField txtPass = new JPasswordField();

        JButton btnLogin = new JButton("Login");

        setLayout(null);

        lblUser.setBounds(50, 40, 100, 25);
        txtUser.setBounds(150, 40, 150, 25);

        lblPass.setBounds(50, 80, 100, 25);
        txtPass.setBounds(150, 80, 150, 25);

        btnLogin.setBounds(150, 130, 100, 30);

        add(lblUser);
        add(txtUser);
        add(lblPass);
        add(txtPass);
        add(btnLogin);
    }
}