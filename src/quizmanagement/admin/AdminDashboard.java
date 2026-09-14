package quizmanagement.admin;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import quizmanagement.DBConnection;
import quizmanagement.dao.QuestionDAO;
import quizmanagement.dao.SubjectDAO;

public class AdminDashboard extends JFrame {

    private JLabel questionsValue;
    private JLabel subjectsValue;
    private JLabel studentsValue;
    private JLabel attemptsValue;
    private JLabel averageValue;

    private JTable performanceTable;
    private DefaultTableModel tableModel;

    // =========================
    // COLORS
    // =========================

    private final Color DARK =
            new Color(15, 23, 42);

    private final Color PRIMARY =
            new Color(37, 99, 235);

    private final Color BACKGROUND =
            new Color(248, 250, 252);

    private final Color CARD =
            Color.WHITE;

    private final Color TEXT =
            new Color(30, 41, 59);

    private final Color SECONDARY_TEXT =
            new Color(100, 116, 139);

    private final Color BORDER =
            new Color(226, 232, 240);

    // =========================
    // CONSTRUCTOR
    // =========================

    public AdminDashboard() {

        setTitle("Quiz Master - Admin Dashboard");

        setSize(1200, 800);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(
                JFrame.EXIT_ON_CLOSE
        );

        setLayout(
                new BorderLayout()
        );

        getContentPane().setBackground(
                BACKGROUND
        );

        createUI();

        loadStatistics();

        loadPerformance();

        setVisible(true);
    }

    // =========================
    // CREATE UI
    // =========================

    private void createUI() {

        // =========================
        // HEADER
        // =========================

        JPanel header =
                new JPanel(
                        new BorderLayout()
                );

        header.setBackground(DARK);

        header.setPreferredSize(
                new Dimension(1200, 80)
        );

        header.setBorder(
                BorderFactory.createEmptyBorder(
                        0,
                        35,
                        0,
                        35
                )
        );

        JLabel logo =
                new JLabel("QUIZ MASTER");

        logo.setForeground(Color.WHITE);

        logo.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        25
                )
        );

        header.add(
                logo,
                BorderLayout.WEST
        );

        JLabel adminLabel =
                new JLabel("Administrator");

        adminLabel.setForeground(
                new Color(226, 232, 240)
        );

        adminLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        15
                )
        );

        header.add(
                adminLabel,
                BorderLayout.EAST
        );

        add(
                header,
                BorderLayout.NORTH
        );

        // =========================
        // MAIN PANEL
        // =========================

        JPanel mainPanel =
                new JPanel(
                        new BorderLayout(
                                0,
                                20
                        )
                );

        mainPanel.setBackground(
                BACKGROUND
        );

        mainPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        25,
                        35,
                        15,
                        35
                )
        );

        // =========================
        // TITLE
        // =========================

        JPanel titlePanel =
                new JPanel(
                        new BorderLayout()
                );

        titlePanel.setBackground(
                BACKGROUND
        );

        JLabel title =
                new JLabel(
                        "Admin Dashboard"
                );

        title.setForeground(TEXT);

        title.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        30
                )
        );

        JLabel subtitle =
                new JLabel(
                        "Manage quizzes and monitor student performance"
                );

        subtitle.setForeground(
                SECONDARY_TEXT
        );

        subtitle.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        14
                )
        );

        titlePanel.add(
                title,
                BorderLayout.NORTH
        );

        titlePanel.add(
                subtitle,
                BorderLayout.SOUTH
        );

        mainPanel.add(
                titlePanel,
                BorderLayout.NORTH
        );

        // =========================
        // CENTER PANEL
        // =========================

        JPanel centerPanel =
                new JPanel(
                        new BorderLayout(
                                0,
                                20
                        )
                );

        centerPanel.setBackground(
                BACKGROUND
        );

        // =========================
        // STATISTICS
        // =========================

        JPanel statsPanel =
                new JPanel(
                        new GridLayout(
                                1,
                                5,
                                15,
                                0
                        )
                );

        statsPanel.setBackground(
                BACKGROUND
        );

        questionsValue =
                new JLabel("0");

        subjectsValue =
                new JLabel("0");

        studentsValue =
                new JLabel("0");

        attemptsValue =
                new JLabel("0");

        averageValue =
                new JLabel("0%");

        statsPanel.add(
                createStatCard(
                        "Questions",
                        questionsValue
                )
        );

        statsPanel.add(
                createStatCard(
                        "Subjects",
                        subjectsValue
                )
        );

        statsPanel.add(
                createStatCard(
                        "Students",
                        studentsValue
                )
        );

        statsPanel.add(
                createStatCard(
                        "Attempts",
                        attemptsValue
                )
        );

        statsPanel.add(
                createStatCard(
                        "Average Score",
                        averageValue
                )
        );

        centerPanel.add(
                statsPanel,
                BorderLayout.NORTH
        );

        // =========================
        // PERFORMANCE CARD
        // =========================

        JPanel performanceCard =
                new JPanel(
                        new BorderLayout()
                );

        performanceCard.setBackground(
                CARD
        );

        performanceCard.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                BORDER
                        ),
                        BorderFactory.createEmptyBorder(
                                15,
                                15,
                                15,
                                15
                        )
                )
        );

        JLabel performanceTitle =
                new JLabel(
                        "Student Performance"
                );

        performanceTitle.setForeground(
                TEXT
        );

        performanceTitle.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        20
                )
        );

        performanceCard.add(
                performanceTitle,
                BorderLayout.NORTH
        );

        // =========================
        // TABLE
        // =========================

        String[] columns = {
                "Student Name",
                "Username",
                "Attempts",
                "Average Score",
                "Average Percentage"
        };

        tableModel =
                new DefaultTableModel(
                        columns,
                        0
                ) {

                    @Override
                    public boolean isCellEditable(
                            int row,
                            int column) {

                        return false;
                    }
                };

        performanceTable =
                new JTable(
                        tableModel
                );

        performanceTable.setRowHeight(38);

        performanceTable.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        13
                )
        );

        performanceTable.setForeground(TEXT);

        performanceTable.setBackground(
                Color.WHITE
        );

        performanceTable.setGridColor(
                new Color(
                        241,
                        245,
                        249
                )
        );

        performanceTable.setShowVerticalLines(
                false
        );

        performanceTable.setSelectionBackground(
                new Color(
                        219,
                        234,
                        254
                )
        );

        performanceTable.setSelectionForeground(
                TEXT
        );

        performanceTable
                .getTableHeader()
                .setPreferredSize(
                        new Dimension(
                                0,
                                42
                        )
                );

        performanceTable
                .getTableHeader()
                .setBackground(
                        DARK
                );

        performanceTable
                .getTableHeader()
                .setForeground(
                        Color.WHITE
                );

        performanceTable
                .getTableHeader()
                .setFont(
                        new Font(
                                "Segoe UI",
                                Font.BOLD,
                                13
                        )
                );

        performanceTable
                .getTableHeader()
                .setReorderingAllowed(
                        false
                );

        DefaultTableCellRenderer centerRenderer =
                new DefaultTableCellRenderer();

        centerRenderer.setHorizontalAlignment(
                SwingConstants.CENTER
        );

        performanceTable
                .getColumnModel()
                .getColumn(2)
                .setCellRenderer(
                        centerRenderer
                );

        performanceTable
                .getColumnModel()
                .getColumn(3)
                .setCellRenderer(
                        centerRenderer
                );

        performanceTable
                .getColumnModel()
                .getColumn(4)
                .setCellRenderer(
                        centerRenderer
                );

        JScrollPane scrollPane =
                new JScrollPane(
                        performanceTable
                );

        scrollPane.setBorder(
                BorderFactory.createEmptyBorder()
        );

        scrollPane
                .getViewport()
                .setBackground(
                        Color.WHITE
                );

        performanceCard.add(
                scrollPane,
                BorderLayout.CENTER
        );

        centerPanel.add(
                performanceCard,
                BorderLayout.CENTER
        );

        mainPanel.add(
                centerPanel,
                BorderLayout.CENTER
        );

        add(
                mainPanel,
                BorderLayout.CENTER
        );

        // =========================
        // BOTTOM BUTTONS
        // =========================

        JPanel bottomPanel =
                new JPanel();

        bottomPanel.setBackground(
                BACKGROUND
        );

        // =========================
        // ADD QUESTION
        // =========================

        JButton addQuestionButton =
                createButton(
                        "Add Question",
                        PRIMARY,
                        140
                );

        addQuestionButton.addActionListener(e -> {

            AddQuestionFrame frame =
                    new AddQuestionFrame();

            frame.setVisible(true);
        });

        // =========================
        // MANAGE QUESTIONS
        // =========================

        JButton manageQuestionsButton =
                createButton(
                        "Manage Questions",
                        new Color(
                                71,
                                85,
                                105
                        ),
                        165
                );

        manageQuestionsButton.addActionListener(e -> {

            ManageQuestionsFrame frame =
                    new ManageQuestionsFrame();

            frame.setVisible(true);
        });

        // =========================
        // VIEW RESULTS
        // =========================

        JButton viewResultsButton =
                createButton(
                        "View Results",
                        new Color(
                                16,
                                185,
                                129
                        ),
                        140
                );

        viewResultsButton.addActionListener(e -> {

            ViewResultsFrame frame =
                    new ViewResultsFrame();

            frame.setVisible(true);
        });

        // =========================
        // MANAGE SUBJECTS
        // =========================

        JButton subjectsButton =
                createButton(
                        "Manage Subjects",
                        new Color(
                                124,
                                58,
                                237
                        ),
                        160
                );

        subjectsButton.addActionListener(e -> {

            ManageSubjectsFrame frame =
                    new ManageSubjectsFrame();

            frame.setVisible(true);
        });

        // =========================
        // MANAGE STUDENTS
        // =========================

        JButton manageStudentsButton =
                createButton(
                        "Manage Students",
                        new Color(
                                14,
                                116,
                                144
                        ),
                        165
                );

        manageStudentsButton.addActionListener(e -> {

            ManageStudentsFrame frame =
                    new ManageStudentsFrame();

            frame.setVisible(true);
        });

        // =========================
        // REFRESH
        // =========================

        JButton refreshButton =
                createButton(
                        "Refresh",
                        new Color(
                                100,
                                116,
                                139
                        ),
                        110
                );

        refreshButton.addActionListener(e -> {

            loadStatistics();

            loadPerformance();
        });

        // =========================
        // LOGOUT
        // =========================

        JButton logoutButton =
                createButton(
                        "Logout",
                        new Color(
                                220,
                                38,
                                38
                        ),
                        110
                );

        logoutButton.addActionListener(e -> {

            int choice =
                    JOptionPane.showConfirmDialog(
                            this,
                            "Are you sure you want to logout?",
                            "Logout",
                            JOptionPane.YES_NO_OPTION
                    );

            if (choice ==
                    JOptionPane.YES_OPTION) {

                dispose();

                new quizmanagement.LoginFrame();
            }
        });

        // =========================
        // ADD BUTTONS
        // =========================

        bottomPanel.add(
                addQuestionButton
        );

        bottomPanel.add(
                manageQuestionsButton
        );

        bottomPanel.add(
                viewResultsButton
        );

        bottomPanel.add(
                subjectsButton
        );

        bottomPanel.add(
                manageStudentsButton
        );

        bottomPanel.add(
                refreshButton
        );

        bottomPanel.add(
                logoutButton
        );

        add(
                bottomPanel,
                BorderLayout.SOUTH
        );
    }

    // =========================
    // CREATE STAT CARD
    // =========================

    private JPanel createStatCard(
            String title,
            JLabel valueLabel) {

        JPanel card =
                new JPanel(
                        new BorderLayout()
                );

        card.setBackground(
                CARD
        );

        card.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                BORDER
                        ),
                        BorderFactory.createEmptyBorder(
                                15,
                                15,
                                15,
                                15
                        )
                )
        );

        JLabel titleLabel =
                new JLabel(
                        title
                );

        titleLabel.setForeground(
                SECONDARY_TEXT
        );

        titleLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        13
                )
        );

        valueLabel.setForeground(
                PRIMARY
        );

        valueLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        27
                )
        );

        valueLabel.setHorizontalAlignment(
                SwingConstants.CENTER
        );

        card.add(
                titleLabel,
                BorderLayout.NORTH
        );

        card.add(
                valueLabel,
                BorderLayout.CENTER
        );

        return card;
    }

    // =========================
    // CREATE BUTTON
    // =========================

    private JButton createButton(
            String text,
            Color color,
            int width) {

        JButton button =
                new JButton(
                        text
                );

        button.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        13
                )
        );

        button.setForeground(
                Color.WHITE
        );

        button.setBackground(
                color
        );

        button.setFocusPainted(
                false
        );

        button.setBorderPainted(
                false
        );

        button.setCursor(
                new Cursor(
                        Cursor.HAND_CURSOR
                )
        );

        button.setPreferredSize(
                new Dimension(
                        width,
                        42
                )
        );

        return button;
    }

    // =========================
    // LOAD STATISTICS
    // =========================

    private void loadStatistics() {

        try {

            int questions =
                    QuestionDAO
                            .getQuestions(0)
                            .size();

            int subjects =
                    SubjectDAO
                            .getAllSubjects()
                            .size();

            int students =
                    getStudentCount();

            int attempts =
                    getAttemptCount();

            double average =
                    getAveragePercentage();

            questionsValue.setText(
                    String.valueOf(
                            questions
                    )
            );

            subjectsValue.setText(
                    String.valueOf(
                            subjects
                    )
            );

            studentsValue.setText(
                    String.valueOf(
                            students
                    )
            );

            attemptsValue.setText(
                    String.valueOf(
                            attempts
                    )
            );

            averageValue.setText(
                    String.format(
                            "%.1f%%",
                            average
                    )
            );

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    // =========================
    // STUDENT COUNT
    // =========================

    private int getStudentCount() {

        String sql =
                "SELECT COUNT(*) " +
                "FROM users " +
                "WHERE role = 'STUDENT'";

        try (
                Connection con =
                        DBConnection.getConnection();

                PreparedStatement ps =
                        con.prepareStatement(sql);

                ResultSet rs =
                        ps.executeQuery()
        ) {

            if (rs.next()) {

                return rs.getInt(1);
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return 0;
    }

    // =========================
    // ATTEMPT COUNT
    // =========================

    private int getAttemptCount() {

        String sql =
                "SELECT COUNT(*) " +
                "FROM results";

        try (
                Connection con =
                        DBConnection.getConnection();

                PreparedStatement ps =
                        con.prepareStatement(sql);

                ResultSet rs =
                        ps.executeQuery()
        ) {

            if (rs.next()) {

                return rs.getInt(1);
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return 0;
    }

    // =========================
    // AVERAGE PERCENTAGE
    // =========================

    private double getAveragePercentage() {

        String sql =
                "SELECT AVG(" +
                "(score * 100.0) / " +
                "NULLIF(total_questions, 0)" +
                ") " +
                "FROM results";

        try (
                Connection con =
                        DBConnection.getConnection();

                PreparedStatement ps =
                        con.prepareStatement(sql);

                ResultSet rs =
                        ps.executeQuery()
        ) {

            if (rs.next()) {

                return rs.getDouble(1);
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return 0;
    }

    // =========================
    // STUDENT PERFORMANCE
    // =========================

    private void loadPerformance() {

        if (tableModel == null) {
            return;
        }

        tableModel.setRowCount(0);

        String sql =
                "SELECT " +
                "u.name, " +
                "u.username, " +
                "COUNT(r.id) AS attempts, " +
                "AVG(r.score) AS avg_score, " +
                "AVG((r.score * 100.0) / " +
                "NULLIF(r.total_questions, 0)) " +
                "AS avg_percentage " +
                "FROM users u " +
                "JOIN results r " +
                "ON u.id = r.student_id " +
                "WHERE u.role = 'STUDENT' " +
                "GROUP BY u.id, u.name, u.username " +
                "ORDER BY avg_percentage DESC";

        try (
                Connection con =
                        DBConnection.getConnection();

                PreparedStatement ps =
                        con.prepareStatement(sql);

                ResultSet rs =
                        ps.executeQuery()
        ) {

            while (rs.next()) {

                String name =
                        rs.getString("name");

                String username =
                        rs.getString("username");

                int attempts =
                        rs.getInt("attempts");

                double avgScore =
                        rs.getDouble("avg_score");

                double avgPercentage =
                        rs.getDouble(
                                "avg_percentage"
                        );

                if (name == null ||
                        name.trim().isEmpty()) {

                    name = "Not Set";
                }

                tableModel.addRow(
                        new Object[] {
                                name,
                                username,
                                attempts,
                                String.format(
                                        "%.1f",
                                        avgScore
                                ),
                                String.format(
                                        "%.1f%%",
                                        avgPercentage
                                )
                        }
                );
            }

        } catch (Exception e) {

            e.printStackTrace();

            JOptionPane.showMessageDialog(
                    this,
                    "Unable to load student performance.\n"
                            + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }
}