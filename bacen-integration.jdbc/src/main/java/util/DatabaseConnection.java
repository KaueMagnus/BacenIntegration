package util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {

    private static Connection connection;

    public static void CreateConnection() {
        try {
            String driver = "org.postgresql.Driver";
            String url = "jdbc:postgresql://localhost:5433/bacenintegration_db";
            String username = "postgres";
            String password = "magnus";

            Class.forName(driver).newInstance();
            connection = DriverManager.getConnection(url, username, password);

            System.out.println("Database connected!");

        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public static Connection getConnection() {
        return connection;
    }

}
