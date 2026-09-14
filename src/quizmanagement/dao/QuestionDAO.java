package quizmanagement.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import quizmanagement.DBConnection;

public class QuestionDAO {

    // =========================================================
    // ADD QUESTION
    // =========================================================

    public static boolean addQuestion(
            int subjectId,
            String question,
            String optionA,
            String optionB,
            String optionC,
            String optionD,
            String correctAnswer) {

        String sql =
                "INSERT INTO questions " +
                "(subject_id, question, option_a, option_b, " +
                "option_c, option_d, correct_answer) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (
                Connection con = DBConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)
        ) {

            ps.setInt(1, subjectId);
            ps.setString(2, question);
            ps.setString(3, optionA);
            ps.setString(4, optionB);
            ps.setString(5, optionC);
            ps.setString(6, optionD);
            ps.setString(7, correctAnswer);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {

            e.printStackTrace();
            return false;
        }
    }


    // =========================================================
    // GET QUESTIONS
    // =========================================================

    public static List<Object[]> getQuestions(int subjectId) {

        List<Object[]> questions =
                new ArrayList<>();

        String sql;

        if (subjectId == 0) {

            sql =
                    "SELECT q.id, s.subject_name, q.question, " +
                    "q.option_a, q.option_b, q.option_c, " +
                    "q.option_d, q.correct_answer " +
                    "FROM questions q " +
                    "JOIN subjects s " +
                    "ON q.subject_id = s.id " +
                    "ORDER BY q.id";

        } else {

            sql =
                    "SELECT q.id, s.subject_name, q.question, " +
                    "q.option_a, q.option_b, q.option_c, " +
                    "q.option_d, q.correct_answer " +
                    "FROM questions q " +
                    "JOIN subjects s " +
                    "ON q.subject_id = s.id " +
                    "WHERE q.subject_id = ? " +
                    "ORDER BY q.id";
        }

        try (
                Connection con = DBConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)
        ) {

            if (subjectId != 0) {
                ps.setInt(1, subjectId);
            }

            try (ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {

                    questions.add(
                            new Object[] {
                                    rs.getInt("id"),
                                    rs.getString("subject_name"),
                                    rs.getString("question"),
                                    rs.getString("option_a"),
                                    rs.getString("option_b"),
                                    rs.getString("option_c"),
                                    rs.getString("option_d"),
                                    rs.getString("correct_answer")
                            }
                    );
                }
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return questions;
    }


    // =========================================================
    // UPDATE QUESTION
    // =========================================================

    public static boolean updateQuestion(
            int questionId,
            int subjectId,
            String question,
            String optionA,
            String optionB,
            String optionC,
            String optionD,
            String correctAnswer) {

        String sql =
                "UPDATE questions SET " +
                "subject_id = ?, " +
                "question = ?, " +
                "option_a = ?, " +
                "option_b = ?, " +
                "option_c = ?, " +
                "option_d = ?, " +
                "correct_answer = ? " +
                "WHERE id = ?";

        try (
                Connection con = DBConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)
        ) {

            ps.setInt(1, subjectId);
            ps.setString(2, question);
            ps.setString(3, optionA);
            ps.setString(4, optionB);
            ps.setString(5, optionC);
            ps.setString(6, optionD);
            ps.setString(7, correctAnswer);
            ps.setInt(8, questionId);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {

            e.printStackTrace();
            return false;
        }
    }


    // =========================================================
    // DELETE QUESTION
    // =========================================================

    public static boolean deleteQuestion(
            int questionId) {

        String sql =
                "DELETE FROM questions " +
                "WHERE id = ?";

        try (
                Connection con = DBConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)
        ) {

            ps.setInt(1, questionId);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {

            e.printStackTrace();
            return false;
        }
    }
}