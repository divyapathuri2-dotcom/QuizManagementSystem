package quizmanagement.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedHashMap;
import java.util.Map;

import quizmanagement.DBConnection;

public class SubjectDAO {

    // =========================================================
    // GET ALL SUBJECTS
    // =========================================================

    public static Map<Integer, String> getAllSubjects() {

        Map<Integer, String> subjects =
                new LinkedHashMap<>();

        String sql =
                "SELECT id, subject_name " +
                "FROM subjects " +
                "ORDER BY id";

        try (
                Connection con =
                        DBConnection.getConnection();

                PreparedStatement ps =
                        con.prepareStatement(sql);

                ResultSet rs =
                        ps.executeQuery()
        ) {

            while (rs.next()) {

                subjects.put(
                        rs.getInt("id"),
                        rs.getString("subject_name")
                );
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return subjects;
    }


    // =========================================================
    // ADD SUBJECT
    // =========================================================

    public static boolean addSubject(
            String subjectName) {

        String sql =
                "INSERT INTO subjects " +
                "(subject_name) VALUES (?)";

        try (
                Connection con =
                        DBConnection.getConnection();

                PreparedStatement ps =
                        con.prepareStatement(sql)
        ) {

            ps.setString(
                    1,
                    subjectName
            );

            return ps.executeUpdate() > 0;

        } catch (Exception e) {

            e.printStackTrace();

            return false;
        }
    }


    // =========================================================
    // DELETE SUBJECT
    // =========================================================

    public static boolean deleteSubject(
            int subjectId) {

        String sql =
                "DELETE FROM subjects " +
                "WHERE id = ?";

        try (
                Connection con =
                        DBConnection.getConnection();

                PreparedStatement ps =
                        con.prepareStatement(sql)
        ) {

            ps.setInt(
                    1,
                    subjectId
            );

            return ps.executeUpdate() > 0;

        } catch (Exception e) {

            e.printStackTrace();

            return false;
        }
    }
}