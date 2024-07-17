package repository;

import model.Registration;
import util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class RegistrationRepository {
    private Connection connection;

    public RegistrationRepository() {
        connection = DatabaseConnection.getConnection();
    }

    public void save (Registration registration) {
        try {
            String sql = "INSERT INTO public.registrations (code, name, gender, min_income, restriction) VALUES(?, ?, ?, ?, ?);";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, registration.code);
            statement.setString(2, registration.name);
            statement.setString(3, registration.gender);
            statement.setDouble(4, registration.minIncome);
            statement.setBoolean(5, registration.restriction);

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Registration successful!");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }


}
