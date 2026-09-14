package quizmanagement.admin;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import quizmanagement.dao.QuestionDAO;
import quizmanagement.dao.SubjectDAO;

public class AddQuestionFrame extends JFrame {

    private JComboBox<String> subjectComboBox;
    private JComboBox<String> correctAnswerComboBox;

    private JTextArea questionArea;

    private JTextField optionAField;
    private JTextField optionBField;
    private JTextField optionCField;
    private JTextField optionDField;

    // Stores Subject Name -> Subject ID
    private Map<String, Integer> subjectMap =
            new LinkedHashMap<>();

    // =====================================================
    // COLORS
    // =====================================================

    private final Color primaryColor =
            new Color(31, 78, 121);

    private final Color backgroundColor =
            new Color(245, 248, 252);

    private final Color saveColor =
            new Color(0, 150, 90);

    private final Color clearColor =
            new Color(230, 150, 30);

    private final Color closeColor =
            new Color(100, 110, 120);

    // =====================================================
    // CONSTRUCTOR
    // =====================================================

    public AddQuestionFrame() {

        setTitle("Quiz Master - Add Question");

        setSize(800, 650);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(
                JFrame.DISPOSE_ON_CLOSE
        );

        setLayout(new BorderLayout());

        getContentPane().setBackground(
                backgroundColor
        );

        // =================================================
        // HEADER
        // =================================================

        JPanel header =
                new JPanel(new BorderLayout());

        header.setBackground(primaryColor);

        header.setPreferredSize(
                new Dimension(800, 75)
        );

        JLabel title =
                new JLabel(
                        "  Add New Question"
                );

        title.setForeground(Color.WHITE);

        title.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        24
                )
        );

        header.add(
                title,
                BorderLayout.WEST
        );

        JLabel subtitle =
                new JLabel(
                        "Create a question for your quiz  "
                );

        subtitle.setForeground(
                new Color(220, 230, 240)
        );

        subtitle.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        13
                )
        );

        header.add(
                subtitle,
                BorderLayout.EAST
        );

        add(
                header,
                BorderLayout.NORTH
        );

        // =================================================
        // FORM CONTAINER
        // =================================================

        JPanel formContainer =
                new JPanel(
                        new BorderLayout()
                );

        formContainer.setBackground(
                backgroundColor
        );

        formContainer.setBorder(
                BorderFactory.createEmptyBorder(
                        20,
                        30,
                        10,
                        30
                )
        );

        // =================================================
        // FORM PANEL
        // =================================================

        JPanel formPanel =
                new JPanel(
                        new GridBagLayout()
                );

        formPanel.setBackground(Color.WHITE);

        formPanel.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(220, 225, 232)
                        ),
                        BorderFactory.createEmptyBorder(
                                20,
                                25,
                                20,
                                25
                        )
                )
        );

        GridBagConstraints gbc =
                new GridBagConstraints();

        gbc.insets =
                new Insets(
                        8,
                        8,
                        8,
                        8
                );

        gbc.fill =
                GridBagConstraints.HORIZONTAL;

        gbc.anchor =
                GridBagConstraints.WEST;

        // =================================================
        // SUBJECT
        // =================================================

        addLabel(
                formPanel,
                gbc,
                0,
                "Subject:"
        );

        subjectComboBox =
                new JComboBox<>();

        subjectComboBox.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        14
                )
        );

        subjectComboBox.setPreferredSize(
                new Dimension(400, 38)
        );

        gbc.gridx = 1;
        gbc.gridy = 0;

        gbc.weightx = 1;

        formPanel.add(
                subjectComboBox,
                gbc
        );

        loadSubjects();

        // =================================================
        // QUESTION
        // =================================================

        addLabel(
                formPanel,
                gbc,
                1,
                "Question:"
        );

        questionArea =
                new JTextArea(4, 30);

        questionArea.setLineWrap(true);

        questionArea.setWrapStyleWord(true);

        questionArea.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        14
                )
        );

        questionArea.setMargin(
                new Insets(
                        8,
                        8,
                        8,
                        8
                )
        );

        JScrollPane questionScroll =
                new JScrollPane(
                        questionArea
                );

        questionScroll.setPreferredSize(
                new Dimension(
                        400,
                        100
                )
        );

        gbc.gridx = 1;
        gbc.gridy = 1;

        gbc.fill =
                GridBagConstraints.BOTH;

        gbc.weighty = 1;

        formPanel.add(
                questionScroll,
                gbc
        );

        gbc.weighty = 0;

        // =================================================
        // OPTIONS
        // =================================================

        optionAField =
                new JTextField();

        optionBField =
                new JTextField();

        optionCField =
                new JTextField();

        optionDField =
                new JTextField();

        addOption(
                formPanel,
                gbc,
                2,
                "Option A:",
                optionAField
        );

        addOption(
                formPanel,
                gbc,
                3,
                "Option B:",
                optionBField
        );

        addOption(
                formPanel,
                gbc,
                4,
                "Option C:",
                optionCField
        );

        addOption(
                formPanel,
                gbc,
                5,
                "Option D:",
                optionDField
        );

        // =================================================
        // CORRECT ANSWER
        // =================================================

        addLabel(
                formPanel,
                gbc,
                6,
                "Correct Answer:"
        );

        correctAnswerComboBox =
                new JComboBox<>(
                        new String[] {
                                "A",
                                "B",
                                "C",
                                "D"
                        }
                );

        correctAnswerComboBox.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        14
                )
        );

        correctAnswerComboBox.setPreferredSize(
                new Dimension(
                        400,
                        38
                )
        );

        gbc.gridx = 1;
        gbc.gridy = 6;

        gbc.fill =
                GridBagConstraints.HORIZONTAL;

        formPanel.add(
                correctAnswerComboBox,
                gbc
        );

        formContainer.add(
                formPanel,
                BorderLayout.CENTER
        );

        add(
                formContainer,
                BorderLayout.CENTER
        );

        // =================================================
        // BUTTON PANEL
        // =================================================

        JPanel buttonPanel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.RIGHT,
                                10,
                                10
                        )
                );

        buttonPanel.setBackground(
                backgroundColor
        );

        buttonPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        0,
                        30,
                        15,
                        30
                )
        );

        JButton saveButton =
                new JButton(
                        "Save Question"
                );

        JButton clearButton =
                new JButton(
                        "Clear"
                );

        JButton closeButton =
                new JButton(
                        "Close"
                );

        styleButton(
                saveButton,
                saveColor,
                145
        );

        styleButton(
                clearButton,
                clearColor,
                100
        );

        styleButton(
                closeButton,
                closeColor,
                100
        );

        buttonPanel.add(
                saveButton
        );

        buttonPanel.add(
                clearButton
        );

        buttonPanel.add(
                closeButton
        );

        add(
                buttonPanel,
                BorderLayout.SOUTH
        );

        // =================================================
        // ACTIONS
        // =================================================

        saveButton.addActionListener(
                e -> saveQuestion()
        );

        clearButton.addActionListener(
                e -> clearFields()
        );

        closeButton.addActionListener(
                e -> dispose()
        );

        // Press Ctrl + Enter to save
        questionArea.getInputMap().put(
                javax.swing.KeyStroke.getKeyStroke(
                        "ctrl ENTER"
                ),
                "saveQuestion"
        );

        questionArea.getActionMap().put(
                "saveQuestion",
                new javax.swing.AbstractAction() {

                    @Override
                    public void actionPerformed(
                            java.awt.event.ActionEvent e) {

                        saveQuestion();
                    }
                }
        );

        setVisible(true);
    }

    // =====================================================
    // ADD LABEL
    // =====================================================

    private void addLabel(
            JPanel panel,
            GridBagConstraints gbc,
            int row,
            String text) {

        JLabel label =
                new JLabel(text);

        label.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        14
                )
        );

        label.setForeground(
                new Color(50, 60, 70)
        );

        gbc.gridx = 0;
        gbc.gridy = row;

        gbc.fill =
                GridBagConstraints.HORIZONTAL;

        gbc.weightx = 0;

        panel.add(
                label,
                gbc
        );
    }

    // =====================================================
    // ADD OPTION
    // =====================================================

    private void addOption(
            JPanel panel,
            GridBagConstraints gbc,
            int row,
            String label,
            JTextField field) {

        addLabel(
                panel,
                gbc,
                row,
                label
        );

        field.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        14
                )
        );

        field.setPreferredSize(
                new Dimension(
                        400,
                        38
                )
        );

        gbc.gridx = 1;
        gbc.gridy = row;

        gbc.weightx = 1;

        gbc.fill =
                GridBagConstraints.HORIZONTAL;

        panel.add(
                field,
                gbc
        );
    }

    // =====================================================
    // LOAD SUBJECTS
    // =====================================================

    private void loadSubjects() {

        subjectComboBox.removeAllItems();

        subjectMap.clear();

        try {

            Map<Integer, String> subjects =
                    SubjectDAO.getAllSubjects();

            for (
                    Map.Entry<Integer, String> entry :
                    subjects.entrySet()
            ) {

                int id =
                        entry.getKey();

                String name =
                        entry.getValue();

                subjectComboBox.addItem(
                        name
                );

                subjectMap.put(
                        name,
                        id
                );
            }

        } catch (Exception e) {

            e.printStackTrace();

            JOptionPane.showMessageDialog(
                    this,
                    "Unable to load subjects.\n"
                    + "Please check the database connection.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // =====================================================
    // SAVE QUESTION
    // =====================================================

    private void saveQuestion() {

        // ---------------------------------------------
        // SUBJECT CHECK
        // ---------------------------------------------

        if (
                subjectComboBox.getSelectedItem()
                == null
        ) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please select a subject.",
                    "Missing Subject",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        // ---------------------------------------------
        // GET VALUES
        // ---------------------------------------------

        String question =
                questionArea
                        .getText()
                        .trim();

        String optionA =
                optionAField
                        .getText()
                        .trim();

        String optionB =
                optionBField
                        .getText()
                        .trim();

        String optionC =
                optionCField
                        .getText()
                        .trim();

        String optionD =
                optionDField
                        .getText()
                        .trim();

        String correctAnswer =
                String.valueOf(
                        correctAnswerComboBox
                                .getSelectedItem()
                );

        // ---------------------------------------------
        // EMPTY FIELD VALIDATION
        // ---------------------------------------------

        if (question.isEmpty()) {

            showValidationMessage(
                    "Please enter the question."
            );

            questionArea.requestFocus();

            return;
        }

        if (optionA.isEmpty()) {

            showValidationMessage(
                    "Please enter Option A."
            );

            optionAField.requestFocus();

            return;
        }

        if (optionB.isEmpty()) {

            showValidationMessage(
                    "Please enter Option B."
            );

            optionBField.requestFocus();

            return;
        }

        if (optionC.isEmpty()) {

            showValidationMessage(
                    "Please enter Option C."
            );

            optionCField.requestFocus();

            return;
        }

        if (optionD.isEmpty()) {

            showValidationMessage(
                    "Please enter Option D."
            );

            optionDField.requestFocus();

            return;
        }

        // ---------------------------------------------
        // LENGTH VALIDATION
        // ---------------------------------------------

        if (question.length() > 1000) {

            showValidationMessage(
                    "Question is too long.\n"
                    + "Please keep it within 1000 characters."
            );

            return;
        }

        if (optionA.length() > 255
                || optionB.length() > 255
                || optionC.length() > 255
                || optionD.length() > 255) {

            showValidationMessage(
                    "Each option must be 255 characters or less."
            );

            return;
        }

        // ---------------------------------------------
        // GET SUBJECT ID
        // ---------------------------------------------

        String selectedSubject =
                String.valueOf(
                        subjectComboBox
                                .getSelectedItem()
                );

        Integer subjectId =
                subjectMap.get(
                        selectedSubject
                );

        if (subjectId == null) {

            showValidationMessage(
                    "Unable to identify the selected subject."
            );

            return;
        }

        // ---------------------------------------------
        // SAVE TO DATABASE
        // ---------------------------------------------

        boolean success =
                QuestionDAO.addQuestion(
                        subjectId,
                        question,
                        optionA,
                        optionB,
                        optionC,
                        optionD,
                        correctAnswer
                );

        // ---------------------------------------------
        // RESULT
        // ---------------------------------------------

        if (success) {

            JOptionPane.showMessageDialog(
                    this,
                    "Question added successfully!",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE
            );

            clearFields();

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "Failed to add question.\n"
                    + "Please check the database connection.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // =====================================================
    // VALIDATION MESSAGE
    // =====================================================

    private void showValidationMessage(
            String message) {

        JOptionPane.showMessageDialog(
                this,
                message,
                "Please Check",
                JOptionPane.WARNING_MESSAGE
        );
    }

    // =====================================================
    // CLEAR FIELDS
    // =====================================================

    private void clearFields() {

        questionArea.setText("");

        optionAField.setText("");

        optionBField.setText("");

        optionCField.setText("");

        optionDField.setText("");

        correctAnswerComboBox
                .setSelectedIndex(0);

        questionArea.requestFocus();
    }

    // =====================================================
    // BUTTON STYLE
    // =====================================================

    private void styleButton(
            JButton button,
            Color color,
            int width) {

        button.setFont(
                new Font(
                        "SansSerif",
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

        button.setFocusPainted(false);

        button.setBorderPainted(false);

        button.setOpaque(true);

        button.setCursor(
                new Cursor(
                        Cursor.HAND_CURSOR
                )
        );

        button.setPreferredSize(
                new Dimension(
                        width,
                        38
                )
        );

        button.setMargin(
                new Insets(
                        5,
                        12,
                        5,
                        12
                )
        );
    }
}