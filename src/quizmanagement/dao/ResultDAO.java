package quizmanagement.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import quizmanagement.DBConnection;

public class ResultDAO {

    // =========================================================
    // SAVE QUIZ RESULT
    // =========================================================

    public static boolean saveResult(
            int studentId,
            int subjectId,
            int score,
            int totalQuestions) {

        String sql =
                "INSERT INTO results " +
                "(student_id, subject_id, score, total_questions) " +
                "VALUES (?, ?, ?, ?)";

        try (
                Connection con =
                        DBConnection.getConnection();

                PreparedStatement ps =
                        con.prepareStatement(sql)
        ) {

            ps.setInt(1, studentId);
            ps.setInt(2, subjectId);
            ps.setInt(3, score);
            ps.setInt(4, totalQuestions);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {

            e.printStackTrace();

            return false;
        }
    }

    // =========================================================
    // GET STUDENT QUIZ HISTORY
    // =========================================================

    public static List<Object[]> getStudentResults(
            int studentId) {

        List<Object[]> results =
                new ArrayList<>();

        String sql =
                "SELECT s.subject_name, " +
                "r.score, " +
                "r.total_questions, " +
                "r.quiz_date " +
                "FROM results r " +
                "JOIN subjects s " +
                "ON r.subject_id = s.id " +
                "WHERE r.student_id = ? " +
                "ORDER BY r.quiz_date DESC";

        try (
                Connection con =
                        DBConnection.getConnection();

                PreparedStatement ps =
                        con.prepareStatement(sql)
        ) {

            ps.setInt(1, studentId);

            try (
                    ResultSet rs =
                            ps.executeQuery()
            ) {

                while (rs.next()) {

                    results.add(
                            new Object[] {

                                    rs.getString(
                                            "subject_name"
                                    ),

                                    rs.getInt(
                                            "score"
                                    ),

                                    rs.getInt(
                                            "total_questions"
                                    ),

                                    rs.getTimestamp(
                                            "quiz_date"
                                    )
                            }
                    );
                }
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return results;
    }

    // =========================================================
    // GET ALL RESULTS FOR ADMIN
    // =========================================================

    public static ResultSet getAllResults() {

        String sql =
                "SELECT r.id, " +
                "u.name, " +
                "u.username, " +
                "s.subject_name, " +
                "r.score, " +
                "r.total_questions, " +
                "r.quiz_date " +
                "FROM results r " +
                "JOIN users u " +
                "ON r.student_id = u.id " +
                "JOIN subjects s " +
                "ON r.subject_id = s.id " +
                "ORDER BY r.quiz_date DESC";

        try {

            Connection con =
                    DBConnection.getConnection();

            PreparedStatement ps =
                    con.prepareStatement(sql);

            return ps.executeQuery();

        } catch (Exception e) {

            e.printStackTrace();

            return null;
        }
    }
}