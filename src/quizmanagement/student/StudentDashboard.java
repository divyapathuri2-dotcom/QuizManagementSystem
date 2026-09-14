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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class StudentDashboard extends JFrame {

    private int studentId;
    private String username;

    private final Color PRIMARY =
            new Color(37, 99, 235);

    private final Color DARK =
            new Color(30, 41, 59);

    private final Color BACKGROUND =
            new Color(248, 250, 252);

    private final Color TEXT =
            new Color(30, 41, 59);

    private final Color SECONDARY_TEXT =
            new Color(100, 116, 139);

    private final Color CARD =
            Color.WHITE;

    public StudentDashboard(
            int studentId,
            String username) {

        this.studentId = studentId;
        this.username = username;

        setTitle(
                "Quiz Master - Student Dashboard"
        );

        setSize(1000, 700);

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
                new Dimension(
                        1000,
                        75
                )
        );

        header.setBorder(
                BorderFactory.createEmptyBorder(
                        0,
                        30,
                        0,
                        30
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
                        "SansSerif",
                        Font.BOLD,
                        24
                )
        );

        header.add(
                logo,
                BorderLayout.WEST
        );

        JPanel rightHeader =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.RIGHT,
                                15,
                                15
                        )
                );

        rightHeader.setOpaque(false);

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
                        "SansSerif",
                        Font.PLAIN,
                        14
                )
        );

        JButton logoutButton =
                new JButton(
                        "Logout"
                );

        logoutButton.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        13
                )
        );

        logoutButton.setFocusPainted(
                false
        );

        logoutButton.setForeground(
                Color.WHITE
        );

        logoutButton.setBackground(
                new Color(
                        71,
                        85,
                        105
                )
        );

        logoutButton.setCursor(
                new Cursor(
                        Cursor.HAND_CURSOR
                )
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

        rightHeader.add(
                studentLabel
        );

        rightHeader.add(
                logoutButton
        );

        header.add(
                rightHeader,
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
                        new BorderLayout()
                );

        mainPanel.setBackground(
                BACKGROUND
        );

        mainPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        30,
                        50,
                        30,
                        50
                )
        );

        // =========================
        // WELCOME
        // =========================

        JPanel welcomePanel =
                new JPanel(
                        new GridLayout(
                                2,
                                1
                        )
                );

        welcomePanel.setBackground(
                BACKGROUND
        );

        JLabel welcome =
                new JLabel(
                        "Welcome, " + username + "!",
                        SwingConstants.CENTER
                );

        welcome.setForeground(
                TEXT
        );

        welcome.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        30
                )
        );

        JLabel subtitle =
                new JLabel(
                        "Choose an option to continue",
                        SwingConstants.CENTER
                );

        subtitle.setForeground(
                SECONDARY_TEXT
        );

        subtitle.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        15
                )
        );

        welcomePanel.add(
                welcome
        );

        welcomePanel.add(
                subtitle
        );

        mainPanel.add(
                welcomePanel,
                BorderLayout.NORTH
        );

        // =========================
        // CARDS PANEL
        // =========================

        JPanel cardsPanel =
                new JPanel(
                        new GridLayout(
                                2,
                                2,
                                25,
                                25
                        )
                );

        cardsPanel.setBackground(
                BACKGROUND
        );

        cardsPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        35,
                        10,
                        20,
                        10
                )
        );

        // =========================
        // START QUIZ
        // =========================

        JPanel quizCard =
                createCard(
                        "Start Quiz",
                        "Select a subject and start a quiz"
                );

        JButton startQuizButton =
                (JButton) quizCard.getClientProperty(
                        "button"
                );

        startQuizButton.addActionListener(e -> {

            dispose();

            new SubjectSelectionFrame(
                    studentId,
                    username
            );
        });

        cardsPanel.add(
                quizCard
        );

        // =========================
        // QUIZ HISTORY
        // =========================

        JPanel historyCard =
                createCard(
                        "Quiz History",
                        "View your previous quiz results"
                );

        JButton historyButton =
                (JButton) historyCard.getClientProperty(
                        "button"
                );

        historyButton.addActionListener(e -> {

            dispose();

            new QuizHistoryFrame(
                    studentId,
                    username
            );
        });

        cardsPanel.add(
                historyCard
        );

        // =========================
        // MY PROFILE
        // =========================

        JPanel profileCard =
                createCard(
                        "My Profile",
                        "View your student information"
                );

        JButton profileButton =
                (JButton) profileCard.getClientProperty(
                        "button"
                );

        profileButton.addActionListener(e -> {

            new ProfileFrame(
                    studentId,
                    username
            );
        });

        cardsPanel.add(
                profileCard
        );

        // =========================
        // LEADERBOARD
        // =========================

        JPanel leaderboardCard =
                createCard(
                        "Leaderboard",
                        "Compare your performance with students"
                );

        JButton leaderboardButton =
                (JButton) leaderboardCard.getClientProperty(
                        "button"
                );

        leaderboardButton.addActionListener(e -> {

            new LeaderboardFrame(
                    studentId,
                    username
            );
        });

        cardsPanel.add(
                leaderboardCard
        );

        mainPanel.add(
                cardsPanel,
                BorderLayout.CENTER
        );

        add(
                mainPanel,
                BorderLayout.CENTER
        );
    }

    // =========================
    // CREATE CARD
    // =========================

    private JPanel createCard(
            String title,
            String description) {

        JPanel card =
                new JPanel(
                        new GridLayout(
                                3,
                                1,
                                5,
                                5
                        )
                );

        card.setBackground(
                CARD
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
                                20,
                                15,
                                20,
                                15
                        )
                )
        );

        JLabel titleLabel =
                new JLabel(
                        title,
                        SwingConstants.CENTER
                );

        titleLabel.setForeground(
                PRIMARY
        );

        titleLabel.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        21
                )
        );

        JLabel descriptionLabel =
                new JLabel(
                        description,
                        SwingConstants.CENTER
                );

        descriptionLabel.setForeground(
                SECONDARY_TEXT
        );

        descriptionLabel.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        13
                )
        );

        JButton button =
                new JButton(
                        title
                );

        button.setForeground(
                Color.WHITE
        );

        button.setBackground(
                PRIMARY
        );

        button.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        14
                )
        );

        button.setFocusPainted(
                false
        );

        button.setCursor(
                new Cursor(
                        Cursor.HAND_CURSOR
                )
        );

        button.setPreferredSize(
                new Dimension(
                        160,
                        40
                )
        );

        card.add(
                titleLabel
        );

        card.add(
                descriptionLabel
        );

        JPanel buttonPanel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.CENTER
                        )
                );

        buttonPanel.setBackground(
                CARD
        );

        buttonPanel.add(
                button
        );

        card.add(
                buttonPanel
        );

        card.putClientProperty(
                "button",
                button
        );

        return card;
    }
}