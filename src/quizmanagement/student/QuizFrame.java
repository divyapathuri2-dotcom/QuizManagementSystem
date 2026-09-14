package quizmanagement.student;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import quizmanagement.dao.QuestionDAO;
import quizmanagement.dao.ResultDAO;

public class QuizFrame extends JFrame {

    private int studentId;
    private String username;
    private int subjectId;
    private String subjectName;

    private List<Object[]> questions;

    private int currentQuestion = 0;
    private int score = 0;

    private int[] selectedAnswers;

    private JLabel questionNumberLabel;
    private JLabel progressLabel;
    private JTextArea questionTextArea;

    private JRadioButton optionA;
    private JRadioButton optionB;
    private JRadioButton optionC;
    private JRadioButton optionD;

    private ButtonGroup optionGroup;

    private JButton previousButton;
    private JButton nextButton;
    private JButton submitButton;

    private static final Color DARK =
            new Color(25, 32, 45);

    private static final Color BLUE =
            new Color(52, 152, 219);

    private static final Color GREEN =
            new Color(46, 204, 113);

    private static final Color RED =
            new Color(231, 76, 60);

    private static final Color LIGHT =
            new Color(245, 247, 250);

    private static final Color TEXT =
            new Color(45, 55, 72);


    public QuizFrame(
            int studentId,
            String username,
            int subjectId,
            String subjectName) {

        this.studentId = studentId;
        this.username = username;
        this.subjectId = subjectId;
        this.subjectName = subjectName;

        setTitle(
                "Quiz Master - " + subjectName
        );

        setSize(
                950,
                650
        );

        setMinimumSize(
                new Dimension(
                        800,
                        600
                )
        );

        setDefaultCloseOperation(
                JFrame.DISPOSE_ON_CLOSE
        );

        setLocationRelativeTo(null);

        loadQuestions();

        if (questions == null ||
                questions.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "No questions available for "
                            + subjectName
                            + ".\nPlease ask the admin to add questions.",
                    "No Questions",
                    JOptionPane.WARNING_MESSAGE
            );

            dispose();

            return;
        }

        selectedAnswers =
                new int[questions.size()];

        for (int i = 0;
                i < selectedAnswers.length;
                i++) {

            selectedAnswers[i] = -1;
        }

        createUI();

        showQuestion();

        setVisible(true);
    }


    // =========================================================
    // LOAD QUESTIONS
    // =========================================================

    private void loadQuestions() {

        questions =
                QuestionDAO.getQuestions(
                        subjectId
                );
    }


    // =========================================================
    // CREATE UI
    // =========================================================

    private void createUI() {

        setLayout(
                new BorderLayout()
        );

        // =====================================================
        // HEADER
        // =====================================================

        JPanel header =
                new JPanel(
                        new BorderLayout()
                );

        header.setBackground(DARK);

        header.setBorder(
                BorderFactory.createEmptyBorder(
                        18,
                        25,
                        18,
                        25
                )
        );


        JPanel titlePanel =
                new JPanel(
                        new BorderLayout()
                );

        titlePanel.setOpaque(false);


        JLabel title =
                new JLabel(
                        "QUIZ MASTER"
                );

        title.setForeground(
                Color.WHITE
        );

        title.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        24
                )
        );


        JLabel subtitle =
                new JLabel(
                        subjectName + " Quiz"
                );

        subtitle.setForeground(
                new Color(
                        190,
                        200,
                        215
                )
        );

        subtitle.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        13
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


        header.add(
                titlePanel,
                BorderLayout.WEST
        );


        // Question number

        questionNumberLabel =
                new JLabel();

        questionNumberLabel.setForeground(
                Color.WHITE
        );

        questionNumberLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        15
                )
        );

        questionNumberLabel.setHorizontalAlignment(
                SwingConstants.RIGHT
        );


        header.add(
                questionNumberLabel,
                BorderLayout.EAST
        );


        add(
                header,
                BorderLayout.NORTH
        );


        // =====================================================
        // MAIN
        // =====================================================

        JPanel main =
                new JPanel(
                        new BorderLayout(
                                0,
                                15
                        )
                );

        main.setBackground(LIGHT);

        main.setBorder(
                BorderFactory.createEmptyBorder(
                        20,
                        30,
                        15,
                        30
                )
        );


        // =====================================================
        // PROGRESS
        // =====================================================

        JPanel progressPanel =
                new JPanel(
                        new BorderLayout()
                );

        progressPanel.setOpaque(false);


        progressLabel =
                new JLabel();

        progressLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        13
                )
        );

        progressLabel.setForeground(
                new Color(
                        100,
                        110,
                        120
                )
        );


        progressPanel.add(
                progressLabel,
                BorderLayout.WEST
        );


        main.add(
                progressPanel,
                BorderLayout.NORTH
        );


        // =====================================================
        // QUESTION CARD
        // =====================================================

        JPanel questionCard =
                new JPanel(
                        new BorderLayout(
                                0,
                                20
                        )
                );

        questionCard.setBackground(
                Color.WHITE
        );

        questionCard.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(
                                        225,
                                        228,
                                        232
                                )
                        ),
                        BorderFactory.createEmptyBorder(
                                25,
                                25,
                                25,
                                25
                        )
                )
        );


        // Question text

        questionTextArea =
                new JTextArea();

        questionTextArea.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        19
                )
        );

        questionTextArea.setForeground(TEXT);

        questionTextArea.setLineWrap(true);

        questionTextArea.setWrapStyleWord(true);

        questionTextArea.setEditable(false);

        questionTextArea.setFocusable(false);

        questionTextArea.setBackground(
                Color.WHITE
        );

        questionTextArea.setBorder(
                BorderFactory.createEmptyBorder(
                        5,
                        5,
                        10,
                        5
                )
        );


        JScrollPane questionScroll =
                new JScrollPane(
                        questionTextArea
                );

        questionScroll.setBorder(
                BorderFactory.createEmptyBorder()
        );

        questionScroll.setBackground(
                Color.WHITE
        );


        questionCard.add(
                questionScroll,
                BorderLayout.NORTH
        );


        // =====================================================
        // OPTIONS
        // =====================================================

        JPanel optionsPanel =
                new JPanel(
                        new GridLayout(
                                4,
                                1,
                                0,
                                12
                        )
                );

        optionsPanel.setOpaque(false);


        optionA =
                createOptionButton(
                        "A"
                );

        optionB =
                createOptionButton(
                        "B"
                );

        optionC =
                createOptionButton(
                        "C"
                );

        optionD =
                createOptionButton(
                        "D"
                );


        optionGroup =
                new ButtonGroup();

        optionGroup.add(optionA);
        optionGroup.add(optionB);
        optionGroup.add(optionC);
        optionGroup.add(optionD);


        optionsPanel.add(optionA);
        optionsPanel.add(optionB);
        optionsPanel.add(optionC);
        optionsPanel.add(optionD);


        questionCard.add(
                optionsPanel,
                BorderLayout.CENTER
        );


        main.add(
                questionCard,
                BorderLayout.CENTER
        );


        add(
                main,
                BorderLayout.CENTER
        );


        // =====================================================
        // BOTTOM
        // =====================================================

        JPanel bottom =
                new JPanel(
                        new BorderLayout()
                );

        bottom.setBackground(LIGHT);

        bottom.setBorder(
                BorderFactory.createEmptyBorder(
                        5,
                        30,
                        20,
                        30
                )
        );


        // Previous

        previousButton =
                createButton(
                        "Previous",
                        BLUE
                );


        previousButton.addActionListener(
                e -> {

                    saveAnswer();

                    if (currentQuestion > 0) {

                        currentQuestion--;

                        showQuestion();
                    }
                }
        );


        // Next

        nextButton =
                createButton(
                        "Next",
                        BLUE
                );


        nextButton.addActionListener(
                e -> {

                    saveAnswer();

                    if (currentQuestion
                            < questions.size() - 1) {

                        currentQuestion++;

                        showQuestion();

                    } else {

                        JOptionPane.showMessageDialog(
                                this,
                                "This is the last question.\n"
                                        + "Click Submit Quiz to finish.",
                                "Quiz",
                                JOptionPane.INFORMATION_MESSAGE
                        );
                    }
                }
        );


        // Submit

        submitButton =
                createButton(
                        "Submit Quiz",
                        GREEN
                );


        submitButton.addActionListener(
                e -> {

                    saveAnswer();

                    submitQuiz();
                }
        );


        JPanel leftButtons =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.LEFT,
                                0,
                                0
                        )
                );

        leftButtons.setOpaque(false);

        leftButtons.add(
                previousButton
        );


        JPanel rightButtons =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.RIGHT,
                                10,
                                0
                        )
                );

        rightButtons.setOpaque(false);

        rightButtons.add(
                nextButton
        );

        rightButtons.add(
                submitButton
        );


        bottom.add(
                leftButtons,
                BorderLayout.WEST
        );

        bottom.add(
                rightButtons,
                BorderLayout.EAST
        );


        add(
                bottom,
                BorderLayout.SOUTH
        );
    }


    // =========================================================
    // OPTION BUTTON
    // =========================================================

    private JRadioButton createOptionButton(
            String letter) {

        JRadioButton button =
                new JRadioButton();

        button.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        16
                )
        );

        button.setForeground(TEXT);

        button.setBackground(
                new Color(
                        250,
                        251,
                        253
                )
        );

        button.setFocusPainted(false);

        button.setCursor(
                new Cursor(
                        Cursor.HAND_CURSOR
                )
        );

        button.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(
                                        225,
                                        228,
                                        232
                                )
                        ),
                        BorderFactory.createEmptyBorder(
                                10,
                                15,
                                10,
                                15
                        )
                )
        );

        button.setText(
                letter + "."
        );

        return button;
    }


    // =========================================================
    // NORMAL BUTTON
    // =========================================================

    private JButton createButton(
            String text,
            Color color) {

        JButton button =
                new JButton(text);

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

        button.setCursor(
                new Cursor(
                        Cursor.HAND_CURSOR
                )
        );

        button.setBorder(
                BorderFactory.createEmptyBorder(
                        10,
                        20,
                        10,
                        20
                )
        );

        button.setPreferredSize(
                new Dimension(
                        130,
                        42
                )
        );

        return button;
    }


    // =========================================================
    // SHOW QUESTION
    // =========================================================

    private void showQuestion() {

        Object[] question =
                questions.get(
                        currentQuestion
                );


        String questionText =
                (String) question[2];

        String optionTextA =
                (String) question[3];

        String optionTextB =
                (String) question[4];

        String optionTextC =
                (String) question[5];

        String optionTextD =
                (String) question[6];


        int total =
                questions.size();


        int number =
                currentQuestion + 1;


        questionNumberLabel.setText(
                "Question "
                        + number
                        + " / "
                        + total
        );


        progressLabel.setText(
                "Question "
                        + number
                        + " of "
                        + total
        );


        questionTextArea.setText(
                questionText
        );


        optionA.setText(
                "A. " + optionTextA
        );

        optionB.setText(
                "B. " + optionTextB
        );

        optionC.setText(
                "C. " + optionTextC
        );

        optionD.setText(
                "D. " + optionTextD
        );


        optionGroup.clearSelection();


        int selected =
                selectedAnswers[
                        currentQuestion
                ];


        if (selected == 0) {

            optionA.setSelected(true);

        } else if (selected == 1) {

            optionB.setSelected(true);

        } else if (selected == 2) {

            optionC.setSelected(true);

        } else if (selected == 3) {

            optionD.setSelected(true);
        }


        previousButton.setEnabled(
                currentQuestion > 0
        );


        if (currentQuestion ==
                questions.size() - 1) {

            nextButton.setEnabled(false);

        } else {

            nextButton.setEnabled(true);
        }
    }


    // =========================================================
    // SAVE ANSWER
    // =========================================================

    private void saveAnswer() {

        if (optionA.isSelected()) {

            selectedAnswers[
                    currentQuestion
            ] = 0;

        } else if (optionB.isSelected()) {

            selectedAnswers[
                    currentQuestion
            ] = 1;

        } else if (optionC.isSelected()) {

            selectedAnswers[
                    currentQuestion
            ] = 2;

        } else if (optionD.isSelected()) {

            selectedAnswers[
                    currentQuestion
            ] = 3;
        }
    }


    // =========================================================
    // SUBMIT QUIZ
    // =========================================================

    private void submitQuiz() {

        int unanswered = 0;


        for (int answer :
                selectedAnswers) {

            if (answer == -1) {

                unanswered++;
            }
        }


        // Unanswered questions

        if (unanswered > 0) {

            int choice =
                    JOptionPane.showConfirmDialog(
                            this,
                            unanswered
                                    + " question(s) are unanswered.\n"
                                    + "Do you want to submit anyway?",
                            "Confirm Submission",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.WARNING_MESSAGE
                    );


            if (choice !=
                    JOptionPane.YES_OPTION) {

                return;
            }
        }


        // =====================================================
        // CALCULATE SCORE
        // =====================================================

        score = 0;


        for (int i = 0;
                i < questions.size();
                i++) {

            Object[] question =
                    questions.get(i);


            String correctAnswer =
                    ((String) question[7])
                            .trim()
                            .toUpperCase();


            int selected =
                    selectedAnswers[i];


            String selectedAnswer =
                    "";


            if (selected == 0) {

                selectedAnswer = "A";

            } else if (selected == 1) {

                selectedAnswer = "B";

            } else if (selected == 2) {

                selectedAnswer = "C";

            } else if (selected == 3) {

                selectedAnswer = "D";
            }


            if (correctAnswer.equals(
                    selectedAnswer)) {

                score++;
            }
        }


        // =====================================================
        // SAVE RESULT
        // =====================================================

        boolean saved =
                ResultDAO.saveResult(
                        studentId,
                        subjectId,
                        score,
                        questions.size()
                );


        if (!saved) {

            JOptionPane.showMessageDialog(
                    this,
                    "Quiz completed, but the result could not be saved.",
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE
            );

            return;
        }


        // =====================================================
        // OPEN RESULT
        // =====================================================

        new ResultFrame(
                studentId,
                username,
                subjectId,
                subjectName,
                score,
                questions.size()
        );


        dispose();
    }
}