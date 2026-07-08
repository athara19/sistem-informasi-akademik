package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class KoneksiDB {

    private static final String URL = "jdbc:mysql://localhost:3306/db_akademik";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    private static Connection conn;

    public static Connection getConnection() {

        try {

            if (conn == null || conn.isClosed()) {

                conn = DriverManager.getConnection(URL, USER, PASSWORD);

                System.out.println("Database Connected");

            }

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return conn;

    }

}