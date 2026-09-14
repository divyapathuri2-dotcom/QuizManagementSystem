package quizmanagement.student;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
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
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import quizmanagement.DBConnection;

public class LeaderboardFrame extends JFrame {

    private int studentId;
    private String username;

    private JTable leaderboardTable;
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

    public LeaderboardFrame(
            int studentId,
            String username) {

        this.studentId = studentId;
        this.username = username;

        setTitle(
                "Quiz Master - Leaderboard"
        );

        setSize(
                950,
                650
        );

        setLocationRelativeTo(null);

        setDefaultCloseOperation(
                JFrame.DISPOSE_ON_CLOSE
        );

        setLayout(
                new BorderLayout()
        );

        getContentPane()
                .setBackground(
                        BACKGROUND
                );

        createUI();

        loadLeaderboard();

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

        header.setBackground(
                DARK
        );

        header.setPreferredSize(
                new Dimension(
                        950,
                        80
                )
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
                new JLabel(
                        "QUIZ MASTER"
                );

        logo.setForeground(
                Color.WHITE
        );

        logo.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        24
                )
        );

        header.add(
                logo,
                BorderLayout.WEST
        );

        JLabel studentLabel =
                new JLabel(
                        "Student: " + username
                );

        studentLabel.setForeground(
                new Color(
                        226,
                        232,
                        240
                )
        );

        studentLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        15
                )
        );

        header.add(
                studentLabel,
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
                        30,
                        40,
                        10,
                        40
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
                        "Leaderboard"
                );

        title.setForeground(
                TEXT
        );

        title.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        30
                )
        );

        JLabel subtitle =
                new JLabel(
                        "See the top student performances"
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
        // TABLE CARD
        // =========================

        JPanel tableCard =
                new JPanel(
                        new BorderLayout()
                );

        tableCard.setBackground(
                CARD
        );

        tableCard.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                BORDER
                        ),
                        BorderFactory.createEmptyBorder(
                                10,
                                10,
                                10,
                                10
                        )
                )
        );

        // =========================
        // TABLE
        // =========================

        String[] columns = {
                "Rank",
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

        leaderboardTable =
                new JTable(
                        tableModel
                );

        leaderboardTable.setRowHeight(
                42
        );

        leaderboardTable.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        14
                )
        );

        leaderboardTable.setForeground(
                TEXT
        );

        leaderboardTable.setBackground(
                Color.WHITE
        );

        leaderboardTable.setSelectionBackground(
                new Color(
                        219,
                        234,
                        254
                )
        );

        leaderboardTable.setSelectionForeground(
                TEXT
        );

        leaderboardTable.setGridColor(
                new Color(
                        241,
                        245,
                        249
                )
        );

        leaderboardTable.setShowVerticalLines(
                false
        );

        leaderboardTable.setShowHorizontalLines(
                true
        );

        leaderboardTable.setSelectionMode(
                ListSelectionModel.SINGLE_SELECTION
        );

        // =========================
        // TABLE HEADER
        // =========================

        leaderboardTable
                .getTableHeader()
                .setPreferredSize(
                        new Dimension(
                                0,
                                45
                        )
                );

        leaderboardTable
                .getTableHeader()
                .setBackground(
                        DARK
                );

        leaderboardTable
                .getTableHeader()
                .setForeground(
                        Color.WHITE
                );

        leaderboardTable
                .getTableHeader()
                .setFont(
                        new Font(
                                "Segoe UI",
                                Font.BOLD,
                                13
                        )
                );

        leaderboardTable
                .getTableHeader()
                .setReorderingAllowed(
                        false
                );

        // =========================
        // CENTER ALIGNMENT
        // =========================

        DefaultTableCellRenderer centerRenderer =
                new DefaultTableCellRenderer();

        centerRenderer.setHorizontalAlignment(
                SwingConstants.CENTER
        );

        leaderboardTable
                .getColumnModel()
                .getColumn(0)
                .setCellRenderer(
                        centerRenderer
                );

        leaderboardTable
                .getColumnModel()
                .getColumn(3)
                .setCellRenderer(
                        centerRenderer
                );

        leaderboardTable
                .getColumnModel()
                .getColumn(4)
                .setCellRenderer(
                        centerRenderer
                );

        leaderboardTable
                .getColumnModel()
                .getColumn(5)
                .setCellRenderer(
                        centerRenderer
                );

        // =========================
        // COLUMN WIDTHS
        // =========================

        leaderboardTable
                .getColumnModel()
                .getColumn(0)
                .setPreferredWidth(
                        70
                );

        leaderboardTable
                .getColumnModel()
                .getColumn(1)
                .setPreferredWidth(
                        180
                );

        leaderboardTable
                .getColumnModel()
                .getColumn(2)
                .setPreferredWidth(
                        120
                );

        leaderboardTable
                .getColumnModel()
                .getColumn(3)
                .setPreferredWidth(
                        100
                );

        leaderboardTable
                .getColumnModel()
                .getColumn(4)
                .setPreferredWidth(
                        130
                );

        leaderboardTable
                .getColumnModel()
                .getColumn(5)
                .setPreferredWidth(
                        150
                );

        JScrollPane scrollPane =
                new JScrollPane(
                        leaderboardTable
                );

        scrollPane.setBorder(
                BorderFactory.createEmptyBorder()
        );

        scrollPane.getViewport()
                .setBackground(
                        Color.WHITE
                );

        tableCard.add(
                scrollPane,
                BorderLayout.CENTER
        );

        mainPanel.add(
                tableCard,
                BorderLayout.CENTER
        );

        add(
                mainPanel,
                BorderLayout.CENTER
        );

        // =========================
        // BOTTOM PANEL
        // =========================

        JPanel bottomPanel =
                new JPanel(
                        new BorderLayout()
                );

        bottomPanel.setBackground(
                BACKGROUND
        );

        bottomPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        5,
                        40,
                        25,
                        40
                )
        );

        JButton refreshButton =
                createButton(
                        "Refresh",
                        new Color(
                                71,
                                85,
                                105
                        ),
                        120
                );

        refreshButton.addActionListener(
                e -> loadLeaderboard()
        );

        JButton backButton =
                createButton(
                        "←  Back",
                        PRIMARY,
                        130
                );

        backButton.addActionListener(
                e -> {

                    dispose();

                    new StudentDashboard(
                            studentId,
                            username
                    );
                }
        );

        bottomPanel.add(
                refreshButton,
                BorderLayout.WEST
        );

        bottomPanel.add(
                backButton,
                BorderLayout.EAST
        );

        add(
                bottomPanel,
                BorderLayout.SOUTH
        );
    }

    // =========================
    // BUTTON CREATOR
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
                        14
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

        button.setMargin(
                new Insets(
                        5,
                        15,
                        5,
                        15
                )
        );

        return button;
    }

    // =========================
    // LOAD LEADERBOARD
    // =========================

    private void loadLeaderboard() {

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

            int rank = 1;

            while (rs.next()) {

                String name =
                        rs.getString(
                                "name"
                        );

                String user =
                        rs.getString(
                                "username"
                        );

                int attempts =
                        rs.getInt(
                                "attempts"
                        );

                double avgScore =
                        rs.getDouble(
                                "avg_score"
                        );

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

                                rank,

                                name,

                                user,

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

                rank++;
            }

            if (rank == 1) {

                JOptionPane.showMessageDialog(
                        this,
                        "No quiz results available yet.",
                        "Leaderboard",
                        JOptionPane.INFORMATION_MESSAGE
                );
            }

        } catch (Exception e) {

            e.printStackTrace();

            JOptionPane.showMessageDialog(
                    this,
                    "Unable to load leaderboard.",
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }
}