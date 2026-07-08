package main;

import database.KoneksiDB;
import gui.LoginForm;

import javax.swing.SwingUtilities;

public class Main {

    public static void main(String[] args) {

        // Test koneksi database
        KoneksiDB.getConnection();

        // Menjalankan GUI
        SwingUtilities.invokeLater(() -> {

            new LoginForm().setVisible(true);

        });

    }

}