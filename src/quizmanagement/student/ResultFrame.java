package quizmanagement.student;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class ResultFrame extends JFrame {

    private int studentId;
    private String username;
    private int subjectId;
    private String subjectName;
    private int score;
    private int totalQuestions;

    // =========================
    // COLORS
    // =========================

    private static final Color DARK =
            new Color(15, 23, 42);

    private static final Color BLUE =
            new Color(37, 99, 235);

    private static final Color GREEN =
            new Color(22, 163, 74);

    private static final Color RED =
            new Color(220, 38, 38);

    private static final Color LIGHT =
            new Color(248, 250, 252);

    private static final Color TEXT =
            new Color(30, 41, 59);

    private static final Color SECONDARY =
            new Color(100, 116, 139);

    // =========================
    // CONSTRUCTOR
    // =========================

    public ResultFrame(
            int studentId,
            String username,
            int subjectId,
            String subjectName,
            int score,
            int totalQuestions) {

        this.studentId = studentId;
        this.username = username;
        this.subjectId = subjectId;
        this.subjectName = subjectName;
        this.score = score;
        this.totalQuestions = totalQuestions;

        setTitle("Quiz Master - Result");

        setSize(850, 650);

        setMinimumSize(
                new Dimension(750, 600)
        );

        setLocationRelativeTo(null);

        setDefaultCloseOperation(
                JFrame.DISPOSE_ON_CLOSE
        );

        createUI();

        setVisible(true);
    }

    // =========================
    // CREATE UI
    // =========================

    private void createUI() {

        setLayout(
                new BorderLayout()
        );

        getContentPane().setBackground(
                LIGHT
        );

        // =========================
        // HEADER
        // =========================

        JPanel header =
                new JPanel(
                        new BorderLayout()
                );

        header.setBackground(DARK);

        header.setBorder(
                BorderFactory.createEmptyBorder(
                        18,
                        30,
                        18,
                        30
                )
        );

        // Logo section
        JPanel titlePanel =
                new JPanel(
                        new BorderLayout()
                );

        titlePanel.setOpaque(false);

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

        JLabel headerSubtitle =
                new JLabel(
                        "Quiz Result"
                );

        headerSubtitle.setForeground(
                new Color(
                        203,
                        213,
                        225
                )
        );

        headerSubtitle.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        13
                )
        );

        titlePanel.add(
                logo,
                BorderLayout.NORTH
        );

        titlePanel.add(
                headerSubtitle,
                BorderLayout.SOUTH
        );

        header.add(
                titlePanel,
                BorderLayout.WEST
        );

        // Student
        JLabel studentLabel =
                new JLabel(
                        "Student: " + username
                );

        studentLabel.setForeground(
                Color.WHITE
        );

        studentLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        14
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

        JPanel main =
                new JPanel(
                        new BorderLayout(
                                0,
                                18
                        )
                );

        main.setBackground(LIGHT);

        main.setBorder(
                BorderFactory.createEmptyBorder(
                        25,
                        50,
                        10,
                        50
                )
        );

        // =========================
        // RESULT TITLE
        // =========================

        JPanel resultTitlePanel =
                new JPanel(
                        new GridLayout(
                                3,
                                1,
                                0,
                                4
                        )
                );

        resultTitlePanel.setOpaque(false);

        JLabel completedLabel =
                new JLabel(
                        "Quiz Completed!",
                        SwingConstants.CENTER
                );

        completedLabel.setForeground(
                DARK
        );

        completedLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        30
                )
        );

        JLabel subjectLabel =
                new JLabel(
                        subjectName + " Quiz",
                        SwingConstants.CENTER
                );

        subjectLabel.setForeground(
                BLUE
        );

        subjectLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        19
                )
        );

        JLabel messageLabel =
                new JLabel(
                        getPerformanceMessage(),
                        SwingConstants.CENTER
                );

        messageLabel.setForeground(
                SECONDARY
        );

        messageLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        14
                )
        );

        resultTitlePanel.add(
                completedLabel
        );

        resultTitlePanel.add(
                subjectLabel
        );

        resultTitlePanel.add(
                messageLabel
        );

        main.add(
                resultTitlePanel,
                BorderLayout.NORTH
        );

        // =========================
        // SCORE
        // =========================

        JPanel scoreCard =
                new JPanel(
                        new BorderLayout()
                );

        scoreCard.setBackground(
                Color.WHITE
        );

        scoreCard.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(
                                        226,
                                        232,
                                        240
                                )
                        ),
                        BorderFactory.createEmptyBorder(
                                20,
                                20,
                                20,
                                20
                        )
                )
        );

        double percentage = 0;

        if (totalQuestions > 0) {

            percentage =
                    ((double) score /
                            totalQuestions) * 100;
        }

        JLabel scoreLabel =
                new JLabel(
                        score + " / " +
                                totalQuestions,
                        SwingConstants.CENTER
                );

        scoreLabel.setForeground(
                getScoreColor(percentage)
        );

        scoreLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        48
                )
        );

        JLabel scoreTitle =
                new JLabel(
                        "YOUR SCORE",
                        SwingConstants.CENTER
                );

        scoreTitle.setForeground(
                SECONDARY
        );

        scoreTitle.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        13
                )
        );

        JPanel scoreCenter =
                new JPanel(
                        new GridLayout(
                                2,
                                1
                        )
                );

        scoreCenter.setOpaque(false);

        scoreCenter.add(
                scoreLabel
        );

        scoreCenter.add(
                scoreTitle
        );

        scoreCard.add(
                scoreCenter,
                BorderLayout.CENTER
        );

        main.add(
                scoreCard,
                BorderLayout.CENTER
        );

        // =========================
        // INFORMATION CARDS
        // =========================

        JPanel infoPanel =
                new JPanel(
                        new GridLayout(
                                1,
                                3,
                                15,
                                0
                        )
                );

        infoPanel.setOpaque(false);

        int wrong =
                totalQuestions - score;

        infoPanel.add(
                createInfoCard(
                        "PERCENTAGE",
                        String.format(
                                "%.1f%%",
                                percentage
                        ),
                        BLUE
                )
        );

        infoPanel.add(
                createInfoCard(
                        "CORRECT",
                        String.valueOf(score),
                        GREEN
                )
        );

        infoPanel.add(
                createInfoCard(
                        "WRONG",
                        String.valueOf(wrong),
                        RED
                )
        );

        main.add(
                infoPanel,
                BorderLayout.SOUTH
        );

        add(
                main,
                BorderLayout.CENTER
        );

        // =========================
        // BUTTON PANEL
        // =========================

        JPanel buttonPanel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.CENTER,
                                15,
                                10
                        )
                );

        buttonPanel.setBackground(
                LIGHT
        );

        // Dashboard button
        JButton dashboardButton =
                createButton(
                        "←  Back to Dashboard",
                        BLUE,
                        200
                );

        dashboardButton.addActionListener(
                e -> {

                    dispose();

                    new StudentDashboard(
                            studentId,
                            username
                    );
                }
        );

        // Another quiz
        JButton anotherQuizButton =
                createButton(
                        "Take Another Quiz",
                        GREEN,
                        190
                );

        anotherQuizButton.addActionListener(
                e -> {

                    dispose();

                    new SubjectSelectionFrame(
                            studentId,
                            username
                    );
                }
        );

        buttonPanel.add(
                dashboardButton
        );

        buttonPanel.add(
                anotherQuizButton
        );

        buttonPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        0,
                        0,
                        20,
                        0
                )
        );

        add(
                buttonPanel,
                BorderLayout.SOUTH
        );
    }

    // =========================
    // INFORMATION CARD
    // =========================

    private JPanel createInfoCard(
            String title,
            String value,
            Color color) {

        JPanel card =
                new JPanel(
                        new GridLayout(
                                2,
                                1
                        )
                );

        card.setBackground(
                Color.WHITE
        );

        card.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(
                                        226,
                                        232,
                                        240
                                )
                        ),
                        BorderFactory.createEmptyBorder(
                                12,
                                10,
                                12,
                                10
                        )
                )
        );

        JLabel titleLabel =
                new JLabel(
                        title,
                        SwingConstants.CENTER
                );

        titleLabel.setForeground(
                SECONDARY
        );

        titleLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        12
                )
        );

        JLabel valueLabel =
                new JLabel(
                        value,
                        SwingConstants.CENTER
                );

        valueLabel.setForeground(
                color
        );

        valueLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        25
                )
        );

        card.add(
                titleLabel
        );

        card.add(
                valueLabel
        );

        return card;
    }

    // =========================
    // BUTTON
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
                        44
                )
        );

        return button;
    }

    // =========================
    // PERFORMANCE MESSAGE
    // =========================

    private String getPerformanceMessage() {

        if (totalQuestions == 0) {

            return "Quiz completed.";
        }

        double percentage =
                ((double) score /
                        totalQuestions) * 100;

        if (percentage >= 90) {

            return "Excellent performance! Keep it up!";

        } else if (percentage >= 75) {

            return "Great job! You performed very well.";

        } else if (percentage >= 50) {

            return "Good effort! Keep practicing.";

        } else {

            return "Keep practicing and try again!";
        }
    }

    // =========================
    // SCORE COLOR
    // =========================

    private Color getScoreColor(
            double percentage) {

        if (percentage >= 75) {

            return GREEN;

        } else if (percentage >= 50) {

            return BLUE;

        } else {

            return RED;
        }
    }
}