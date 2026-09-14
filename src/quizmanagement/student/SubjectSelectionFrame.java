package quizmanagement.student;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import quizmanagement.dao.SubjectDAO;

public class SubjectSelectionFrame extends JFrame {

    private int studentId;
    private String username;

    private final Color PRIMARY = new Color(31, 78, 121);
    private final Color BACKGROUND = new Color(245, 248, 252);
    private final Color TEXT = new Color(25, 45, 70);

    public SubjectSelectionFrame(
            int studentId,
            String username) {

        this.studentId = studentId;
        this.username = username;

        setTitle("Quiz Master - Select Subject");
        setSize(1100, 750);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        createUI();

        setVisible(true);
    }

    private void createUI() {

        setLayout(new BorderLayout());
        getContentPane().setBackground(BACKGROUND);

        // =========================
        // TOP HEADER
        // =========================

        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(new Color(31, 42, 60));
        header.setBorder(
                BorderFactory.createEmptyBorder(
                        25, 30, 25, 30));

        JLabel title = new JLabel("QUIZ MASTER");

        title.setForeground(Color.WHITE);
        title.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        30));

        JLabel welcome =
                new JLabel("Welcome, " + username);

        welcome.setForeground(Color.WHITE);
        welcome.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        20));

        header.add(title, BorderLayout.WEST);
        header.add(welcome, BorderLayout.EAST);

        add(header, BorderLayout.NORTH);

        // =========================
        // MAIN CONTENT
        // =========================

        JPanel mainPanel =
                new JPanel(new BorderLayout());

        mainPanel.setBackground(BACKGROUND);

        mainPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        40, 70, 30, 70));

        // Heading

        JPanel headingPanel =
                new JPanel();

        headingPanel.setBackground(BACKGROUND);

        headingPanel.setLayout(
                new javax.swing.BoxLayout(
                        headingPanel,
                        javax.swing.BoxLayout.Y_AXIS));

        JLabel heading =
                new JLabel("Choose Your Subject");

        heading.setAlignmentX(
                JLabel.CENTER_ALIGNMENT);

        heading.setForeground(TEXT);

        heading.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        36));

        JLabel subtitle =
                new JLabel(
                        "Select a subject and test your knowledge");

        subtitle.setAlignmentX(
                JLabel.CENTER_ALIGNMENT);

        subtitle.setForeground(
                new Color(90, 110, 135));

        subtitle.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        18));

        headingPanel.add(heading);
        headingPanel.add(
                javax.swing.Box.createVerticalStrut(12));
        headingPanel.add(subtitle);

        mainPanel.add(
                headingPanel,
                BorderLayout.NORTH);

        // =========================
        // SUBJECT BUTTON PANEL
        // =========================

        JPanel subjectPanel =
                new JPanel(
                        new GridLayout(
                                0,
                                2,
                                30,
                                25));

        subjectPanel.setBackground(BACKGROUND);

        subjectPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        50, 20, 30, 20));

        Map<Integer, String> subjects =
                SubjectDAO.getAllSubjects();

        if (subjects.isEmpty()) {

            JLabel noSubjects =
                    new JLabel(
                            "No subjects available.");

            noSubjects.setHorizontalAlignment(
                    JLabel.CENTER);

            noSubjects.setFont(
                    new Font(
                            "SansSerif",
                            Font.BOLD,
                            20));

            subjectPanel.add(noSubjects);

        } else {

            for (Map.Entry<Integer, String> entry
                    : subjects.entrySet()) {

                int subjectId =
                        entry.getKey();

                String subjectName =
                        entry.getValue();

                // IMPORTANT:
                // Set subject name here
                JButton subjectButton =
                        new JButton(subjectName);

                subjectButton.setFont(
                        new Font(
                                "SansSerif",
                                Font.BOLD,
                                19));

                subjectButton.setForeground(TEXT);

                subjectButton.setBackground(
                        Color.WHITE);

                subjectButton.setFocusPainted(false);

                subjectButton.setBorder(
                        BorderFactory.createCompoundBorder(
                                BorderFactory.createLineBorder(
                                        new Color(
                                                215,
                                                223,
                                                232)),
                                BorderFactory.createEmptyBorder(
                                        18,
                                        20,
                                        18,
                                        20)));

                subjectButton.setPreferredSize(
                        new Dimension(400, 65));

                // Click subject

                subjectButton.addActionListener(e -> {

                    try {

                        QuizFrame quizFrame =
                                new QuizFrame(
                                        studentId,
                                        username,
                                        subjectId,
                                        subjectName);

                        quizFrame.setVisible(true);

                        dispose();

                    } catch (Exception ex) {

                        ex.printStackTrace();

                        JOptionPane.showMessageDialog(
                                this,
                                "Unable to start quiz.\n"
                                        + "Please check whether questions "
                                        + "are available for this subject.",
                                "Quiz Error",
                                JOptionPane.ERROR_MESSAGE);
                    }
                });

                subjectPanel.add(subjectButton);
            }
        }

        mainPanel.add(
                subjectPanel,
                BorderLayout.CENTER);

        add(mainPanel, BorderLayout.CENTER);

        // =========================
        // BACK BUTTON
        // =========================

        JPanel bottomPanel =
                new JPanel();

        bottomPanel.setBackground(BACKGROUND);

        JButton backButton =
                new JButton("←  Back to Dashboard");

        backButton.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        17));

        backButton.setForeground(TEXT);

        backButton.setBackground(Color.WHITE);

        backButton.setFocusPainted(false);

        backButton.setPreferredSize(
                new Dimension(260, 50));

        backButton.setBorder(
                BorderFactory.createLineBorder(
                        new Color(
                                190,
                                205,
                                220)));

        backButton.addActionListener(e -> {

            new StudentDashboard(
                    studentId,
                    username);

            dispose();
        });

        bottomPanel.add(backButton);

        add(bottomPanel, BorderLayout.SOUTH);
    }
}