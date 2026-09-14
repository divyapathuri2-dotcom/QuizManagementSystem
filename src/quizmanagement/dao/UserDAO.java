package quizmanagement.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import quizmanagement.DBConnection;

public class UserDAO {

    // Login method
    public static ResultSet login(
            String username,
            String password) {

        String sql =
                "SELECT * FROM users " +
                "WHERE username = ? AND password = ?";

        try {

            Connection con =
                    DBConnection.getConnection();

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setString(1, username);
            ps.setString(2, password);

            return ps.executeQuery();

        } catch (Exception e) {

            e.printStackTrace();
        }

        return null;
    }


    // Change password method
    public static boolean changePassword(
            int studentId,
            String oldPassword,
            String newPassword) {

        String sql =
                "UPDATE users " +
                "SET password = ? " +
                "WHERE id = ? AND password = ?";

        try (
                Connection con =
                        DBConnection.getConnection();

                PreparedStatement ps =
                        con.prepareStatement(sql)
        ) {

            ps.setString(
                    1,
                    newPassword
            );

            ps.setInt(
                    2,
                    studentId
            );

            ps.setString(
                    3,
                    oldPassword
            );

            return ps.executeUpdate() > 0;

        } catch (Exception e) {

            e.printStackTrace();

            return false;
        }
    }
}