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
import java.util.List;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import quizmanagement.dao.QuestionDAO;
import quizmanagement.dao.SubjectDAO;

public class ManageQuestionsFrame extends JFrame {

    private JTable questionTable;
    private DefaultTableModel tableModel;

    private JComboBox<String> subjectFilter;

    private Map<Integer, String> subjects;

    // =========================================================
    // COLORS
    // =========================================================

    private static final Color DARK =
            new Color(25, 32, 45);

    private static final Color LIGHT =
            new Color(245, 247, 250);

    private static final Color BLUE =
            new Color(52, 152, 219);

    private static final Color GREEN =
            new Color(46, 204, 113);

    private static final Color RED =
            new Color(231, 76, 60);

    private static final Color GRAY =
            new Color(100, 110, 120);


    // =========================================================
    // CONSTRUCTOR
    // =========================================================

    public ManageQuestionsFrame() {

        setTitle("Manage Questions - Quiz Master");

        setSize(1200, 700);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(
                JFrame.DISPOSE_ON_CLOSE
        );

        setLayout(
                new BorderLayout()
        );

        createUI();

        loadSubjects();

        loadQuestions(0);

        setVisible(true);
    }


    // =========================================================
    // MAIN UI
    // =========================================================

    private void createUI() {

        // -----------------------------------------------------
        // HEADER
        // -----------------------------------------------------

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


        JLabel title =
                new JLabel(
                        "Manage Questions"
                );

        title.setForeground(
                Color.WHITE
        );

        title.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        26
                )
        );


        JLabel subtitle =
                new JLabel(
                        "Add, edit and manage quiz questions"
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


        JPanel titlePanel =
                new JPanel(
                        new BorderLayout()
                );

        titlePanel.setOpaque(false);

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


        JButton closeButton =
                createButton(
                        "Close",
                        new Color(
                                90,
                                100,
                                115
                        )
                );

        closeButton.addActionListener(
                e -> dispose()
        );


        header.add(
                closeButton,
                BorderLayout.EAST
        );


        add(
                header,
                BorderLayout.NORTH
        );


        // -----------------------------------------------------
        // CONTENT
        // -----------------------------------------------------

        JPanel content =
                new JPanel(
                        new BorderLayout(
                                15,
                                15
                        )
                );

        content.setBackground(
                LIGHT
        );

        content.setBorder(
                BorderFactory.createEmptyBorder(
                        20,
                        20,
                        20,
                        20
                )
        );


        // -----------------------------------------------------
        // FILTER PANEL
        // -----------------------------------------------------

        JPanel filterPanel =
                new JPanel(
                        new BorderLayout()
                );

        filterPanel.setBackground(
                Color.WHITE
        );

        filterPanel.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(
                                        220,
                                        225,
                                        230
                                )
                        ),
                        BorderFactory.createEmptyBorder(
                                15,
                                18,
                                15,
                                18
                        )
                )
        );


        // LEFT SIDE

        JPanel filterLeft =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.LEFT,
                                10,
                                0
                        )
                );

        filterLeft.setOpaque(false);


        JLabel filterLabel =
                new JLabel(
                        "Filter by Subject:"
                );

        filterLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        14
                )
        );


        subjectFilter =
                new JComboBox<>();

        subjectFilter.setPreferredSize(
                new Dimension(
                        220,
                        35
                )
        );

        subjectFilter.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        14
                )
        );


        subjectFilter.addActionListener(
                e -> {

                    if (
                            subjectFilter
                                    .getSelectedIndex()
                                    >= 0
                    ) {

                        int subjectId =
                                getSelectedSubjectId();

                        loadQuestions(
                                subjectId
                        );
                    }
                }
        );


        filterLeft.add(
                filterLabel
        );

        filterLeft.add(
                subjectFilter
        );


        filterPanel.add(
                filterLeft,
                BorderLayout.WEST
        );


        // -----------------------------------------------------
        // BUTTONS
        // -----------------------------------------------------

        JPanel buttonPanel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.RIGHT,
                                8,
                                0
                        )
                );

        buttonPanel.setOpaque(false);


        JButton refreshButton =
                createButton(
                        "Refresh",
                        BLUE
                );

        refreshButton.addActionListener(
                e -> {

                    loadSubjects();

                    loadQuestions(
                            getSelectedSubjectId()
                    );
                }
        );


        JButton addButton =
                createButton(
                        "Add Question",
                        GREEN
                );

        addButton.addActionListener(
                e -> showQuestionDialog(
                        false,
                        -1
                )
        );


        JButton editButton =
                createButton(
                        "Edit",
                        BLUE
                );

        editButton.addActionListener(
                e -> editSelectedQuestion()
        );


        JButton deleteButton =
                createButton(
                        "Delete",
                        RED
                );

        deleteButton.addActionListener(
                e -> deleteSelectedQuestion()
        );


        buttonPanel.add(
                refreshButton
        );

        buttonPanel.add(
                addButton
        );

        buttonPanel.add(
                editButton
        );

        buttonPanel.add(
                deleteButton
        );


        filterPanel.add(
                buttonPanel,
                BorderLayout.EAST
        );


        content.add(
                filterPanel,
                BorderLayout.NORTH
        );


        // -----------------------------------------------------
        // TABLE
        // -----------------------------------------------------

        String[] columns = {

                "ID",
                "Subject",
                "Question",
                "Option A",
                "Option B",
                "Option C",
                "Option D",
                "Correct"

        };


        tableModel =
                new DefaultTableModel(
                        columns,
                        0
                ) {

                    @Override
                    public boolean isCellEditable(
                            int row,
                            int column
                    ) {

                        return false;
                    }
                };


        questionTable =
                new JTable(
                        tableModel
                );


        questionTable.setRowHeight(
                40
        );

        questionTable.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        13
                )
        );


        questionTable.setSelectionMode(
                ListSelectionModel.SINGLE_SELECTION
        );


        questionTable.setAutoResizeMode(
                JTable.AUTO_RESIZE_OFF
        );


        questionTable.setShowGrid(
                false
        );


        questionTable.setIntercellSpacing(
                new Dimension(
                        0,
                        0
                )
        );


        questionTable.getTableHeader()
                .setFont(
                        new Font(
                                "Segoe UI",
                                Font.BOLD,
                                13
                        )
                );


        questionTable.getTableHeader()
                .setBackground(
                        DARK
                );


        questionTable.getTableHeader()
                .setForeground(
                        Color.WHITE
                );


        questionTable.getTableHeader()
                .setPreferredSize(
                        new Dimension(
                                0,
                                40
                        )
                );


        // -----------------------------------------------------
        // COLUMN WIDTHS
        // -----------------------------------------------------

        questionTable
                .getColumnModel()
                .getColumn(0)
                .setPreferredWidth(50);

        questionTable
                .getColumnModel()
                .getColumn(1)
                .setPreferredWidth(130);

        questionTable
                .getColumnModel()
                .getColumn(2)
                .setPreferredWidth(300);

        questionTable
                .getColumnModel()
                .getColumn(3)
                .setPreferredWidth(180);

        questionTable
                .getColumnModel()
                .getColumn(4)
                .setPreferredWidth(180);

        questionTable
                .getColumnModel()
                .getColumn(5)
                .setPreferredWidth(180);

        questionTable
                .getColumnModel()
                .getColumn(6)
                .setPreferredWidth(180);

        questionTable
                .getColumnModel()
                .getColumn(7)
                .setPreferredWidth(70);


        JScrollPane scrollPane =
                new JScrollPane(
                        questionTable
                );

        scrollPane.setBorder(
                BorderFactory.createLineBorder(
                        new Color(
                                220,
                                225,
                                230
                        )
                )
        );


        content.add(
                scrollPane,
                BorderLayout.CENTER
        );


        // -----------------------------------------------------
        // FOOTER
        // -----------------------------------------------------

        JPanel footer =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.LEFT
                        )
                );

        footer.setBackground(
                LIGHT
        );


        JLabel info =
                new JLabel(
                        "Select a question to edit or delete"
                );

        info.setForeground(
                GRAY
        );

        info.setFont(
                new Font(
                        "Segoe UI",
                        Font.ITALIC,
                        12
                )
        );


        footer.add(
                info
        );


        content.add(
                footer,
                BorderLayout.SOUTH
        );


        add(
                content,
                BorderLayout.CENTER
        );
    }


    // =========================================================
    // LOAD SUBJECTS
    // =========================================================

    private void loadSubjects() {

        subjects =
                SubjectDAO.getAllSubjects();

        subjectFilter.removeAllItems();

        subjectFilter.addItem(
                "All Subjects"
        );


        for (
                String subjectName :
                subjects.values()
        ) {

            subjectFilter.addItem(
                    subjectName
            );
        }
    }


    // =========================================================
    // GET SELECTED SUBJECT ID
    // =========================================================

    private int getSelectedSubjectId() {

        if (subjectFilter == null) {
            return 0;
        }


        int selectedIndex =
                subjectFilter.getSelectedIndex();


        if (selectedIndex <= 0) {

            return 0;
        }


        int counter = 1;


        for (
                Integer id :
                subjects.keySet()
        ) {

            if (
                    counter ==
                    selectedIndex
            ) {

                return id;
            }

            counter++;
        }


        return 0;
    }


    // =========================================================
    // LOAD QUESTIONS
    // =========================================================

    private void loadQuestions(
            int subjectId
    ) {

        tableModel.setRowCount(
                0
        );


        List<Object[]> questions =
                QuestionDAO.getQuestions(
                        subjectId
                );


        for (
                Object[] q :
                questions
        ) {

            tableModel.addRow(
                    new Object[] {

                            q[0],
                            q[1],
                            q[2],
                            q[3],
                            q[4],
                            q[5],
                            q[6],
                            q[7]

                    }
            );
        }
    }


    // =========================================================
    // EDIT SELECTED QUESTION
    // =========================================================

    private void editSelectedQuestion() {

        int selectedRow =
                questionTable.getSelectedRow();


        if (selectedRow == -1) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please select a question first.",
                    "No Question Selected",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }


        int questionId =
                Integer.parseInt(
                        tableModel
                                .getValueAt(
                                        selectedRow,
                                        0
                                )
                                .toString()
                );


        showQuestionDialog(
                true,
                questionId
        );
    }


    // =========================================================
    // DELETE SELECTED QUESTION
    // =========================================================

    private void deleteSelectedQuestion() {

        int selectedRow =
                questionTable.getSelectedRow();


        if (selectedRow == -1) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please select a question first.",
                    "No Question Selected",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }


        int questionId =
                Integer.parseInt(
                        tableModel
                                .getValueAt(
                                        selectedRow,
                                        0
                                )
                                .toString()
                );


        String question =
                tableModel
                        .getValueAt(
                                selectedRow,
                                2
                        )
                        .toString();


        int confirm =
                JOptionPane.showConfirmDialog(
                        this,
                        "Are you sure you want to delete this question?\n\n"
                        + question,
                        "Confirm Delete",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.WARNING_MESSAGE
                );


        if (
                confirm ==
                JOptionPane.YES_OPTION
        ) {

            boolean deleted =
                    QuestionDAO.deleteQuestion(
                            questionId
                    );


            if (deleted) {

                JOptionPane.showMessageDialog(
                        this,
                        "Question deleted successfully!",
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE
                );


                loadQuestions(
                        getSelectedSubjectId()
                );

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Unable to delete question.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        }
    }


    // =========================================================
    // ADD / EDIT QUESTION DIALOG
    // =========================================================

    private void showQuestionDialog(
            boolean editMode,
            int questionId
    ) {

        JDialog dialog =
                new JDialog(
                        this,
                        editMode
                                ? "Edit Question"
                                : "Add New Question",
                        true
                );


        dialog.setSize(
                620,
                680
        );


        dialog.setLocationRelativeTo(
                this
        );


        dialog.setLayout(
                new BorderLayout()
        );


        // -----------------------------------------------------
        // HEADER
        // -----------------------------------------------------

        JPanel header =
                new JPanel(
                        new BorderLayout()
                );

        header.setBackground(
                DARK
        );


        header.setBorder(
                BorderFactory.createEmptyBorder(
                        18,
                        22,
                        18,
                        22
                )
        );


        JLabel title =
                new JLabel(
                        editMode
                                ? "Edit Question"
                                : "Add New Question"
                );


        title.setForeground(
                Color.WHITE
        );


        title.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        22
                )
        );


        JLabel subTitle =
                new JLabel(
                        editMode
                                ? "Update question details"
                                : "Create a new quiz question"
                );


        subTitle.setForeground(
                new Color(
                        190,
                        200,
                        215
                )
        );


        subTitle.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        12
                )
        );


        JPanel titlePanel =
                new JPanel(
                        new BorderLayout()
                );


        titlePanel.setOpaque(
                false
        );


        titlePanel.add(
                title,
                BorderLayout.NORTH
        );


        titlePanel.add(
                subTitle,
                BorderLayout.SOUTH
        );


        header.add(
                titlePanel,
                BorderLayout.WEST
        );


        dialog.add(
                header,
                BorderLayout.NORTH
        );


        // -----------------------------------------------------
        // FORM
        // -----------------------------------------------------

        JPanel form =
                new JPanel(
                        new GridBagLayout()
                );


        form.setBackground(
                Color.WHITE
        );


        form.setBorder(
                BorderFactory.createEmptyBorder(
                        20,
                        25,
                        10,
                        25
                )
        );


        GridBagConstraints gbc =
                new GridBagConstraints();


        gbc.insets =
                new Insets(
                        7,
                        5,
                        7,
                        5
                );


        gbc.fill =
                GridBagConstraints.HORIZONTAL;


        gbc.weightx = 1;


        // -----------------------------------------------------
        // SUBJECT
        // -----------------------------------------------------

        JLabel subjectLabel =
                createLabel(
                        "Subject"
                );


        JComboBox<String> subjectCombo =
                new JComboBox<>();


        for (
                String subject :
                subjects.values()
        ) {

            subjectCombo.addItem(
                    subject
            );
        }


        styleComboBox(
                subjectCombo
        );


        addFormRow(
                form,
                gbc,
                0,
                subjectLabel,
                subjectCombo
        );


        // -----------------------------------------------------
        // QUESTION
        // -----------------------------------------------------

        JLabel questionLabel =
                createLabel(
                        "Question"
                );


        JTextArea questionArea =
                new JTextArea(
                        3,
                        30
                );


        questionArea.setLineWrap(
                true
        );


        questionArea.setWrapStyleWord(
                true
        );


        questionArea.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        14
                )
        );


        questionArea.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(
                                        210,
                                        215,
                                        220
                                )
                        ),
                        BorderFactory.createEmptyBorder(
                                8,
                                8,
                                8,
                                8
                        )
                )
        );


        JScrollPane questionScroll =
                new JScrollPane(
                        questionArea
                );


        addFormRow(
                form,
                gbc,
                1,
                questionLabel,
                questionScroll
        );


        // -----------------------------------------------------
        // OPTIONS
        // -----------------------------------------------------

        JTextField optionA =
                createTextField();


        addFormRow(
                form,
                gbc,
                2,
                createLabel("Option A"),
                optionA
        );


        JTextField optionB =
                createTextField();


        addFormRow(
                form,
                gbc,
                3,
                createLabel("Option B"),
                optionB
        );


        JTextField optionC =
                createTextField();


        addFormRow(
                form,
                gbc,
                4,
                createLabel("Option C"),
                optionC
        );


        JTextField optionD =
                createTextField();


        addFormRow(
                form,
                gbc,
                5,
                createLabel("Option D"),
                optionD
        );


        // -----------------------------------------------------
        // CORRECT ANSWER
        // -----------------------------------------------------

        JComboBox<String> correctCombo =
                new JComboBox<>(
                        new String[] {
                                "A",
                                "B",
                                "C",
                                "D"
                        }
                );


        styleComboBox(
                correctCombo
        );


        addFormRow(
                form,
                gbc,
                6,
                createLabel(
                        "Correct Answer"
                ),
                correctCombo
        );


        dialog.add(
                form,
                BorderLayout.CENTER
        );


        // -----------------------------------------------------
        // BUTTONS
        // -----------------------------------------------------

        JPanel buttons =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.RIGHT,
                                10,
                                15
                        )
                );


        buttons.setBackground(
                new Color(
                        248,
                        249,
                        250
                )
        );


        JButton cancel =
                createButton(
                        "Cancel",
                        new Color(
                                120,
                                130,
                                140
                        )
                );


        cancel.addActionListener(
                e -> dialog.dispose()
        );


        JButton save =
                createButton(
                        editMode
                                ? "Update Question"
                                : "Save Question",
                        GREEN
                );


        save.addActionListener(
                e -> {

                    String question =
                            questionArea
                                    .getText()
                                    .trim();


                    String a =
                            optionA
                                    .getText()
                                    .trim();


                    String b =
                            optionB
                                    .getText()
                                    .trim();


                    String c =
                            optionC
                                    .getText()
                                    .trim();


                    String d =
                            optionD
                                    .getText()
                                    .trim();


                    // -------------------------------------------------
                    // VALIDATION
                    // -------------------------------------------------

                    if (
                            subjectCombo
                                    .getSelectedItem()
                                    == null
                    ) {

                        JOptionPane.showMessageDialog(
                                dialog,
                                "Please select a subject.",
                                "Missing Subject",
                                JOptionPane.WARNING_MESSAGE
                        );

                        return;
                    }


                    if (
                            question.isEmpty()
                            || a.isEmpty()
                            || b.isEmpty()
                            || c.isEmpty()
                            || d.isEmpty()
                    ) {

                        JOptionPane.showMessageDialog(
                                dialog,
                                "Please fill all fields.",
                                "Missing Information",
                                JOptionPane.WARNING_MESSAGE
                        );

                        return;
                    }


                    if (
                            question.length() > 1000
                    ) {

                        JOptionPane.showMessageDialog(
                                dialog,
                                "Question must be 1000 characters or less.",
                                "Question Too Long",
                                JOptionPane.WARNING_MESSAGE
                        );

                        return;
                    }


                    if (
                            a.length() > 255
                            || b.length() > 255
                            || c.length() > 255
                            || d.length() > 255
                    ) {

                        JOptionPane.showMessageDialog(
                                dialog,
                                "Each option must be 255 characters or less.",
                                "Option Too Long",
                                JOptionPane.WARNING_MESSAGE
                        );

                        return;
                    }


                    String selectedSubject =
                            subjectCombo
                                    .getSelectedItem()
                                    .toString();


                    int selectedSubjectId =
                            getSubjectIdFromName(
                                    selectedSubject
                            );


                    if (
                            selectedSubjectId == 0
                    ) {

                        JOptionPane.showMessageDialog(
                                dialog,
                                "Invalid subject selected.",
                                "Error",
                                JOptionPane.ERROR_MESSAGE
                        );

                        return;
                    }


                    String correct =
                            correctCombo
                                    .getSelectedItem()
                                    .toString();


                    boolean success;


                    // -------------------------------------------------
                    // UPDATE
                    // -------------------------------------------------

                    if (editMode) {

                        success =
                                QuestionDAO.updateQuestion(
                                        questionId,
                                        selectedSubjectId,
                                        question,
                                        a,
                                        b,
                                        c,
                                        d,
                                        correct
                                );

                    }

                    // -------------------------------------------------
                    // ADD
                    // -------------------------------------------------

                    else {

                        success =
                                QuestionDAO.addQuestion(
                                        selectedSubjectId,
                                        question,
                                        a,
                                        b,
                                        c,
                                        d,
                                        correct
                                );
                    }


                    // -------------------------------------------------
                    // RESULT
                    // -------------------------------------------------

                    if (success) {

                        JOptionPane.showMessageDialog(
                                dialog,
                                editMode
                                        ? "Question updated successfully!"
                                        : "Question added successfully!",
                                "Success",
                                JOptionPane.INFORMATION_MESSAGE
                        );


                        dialog.dispose();


                        loadQuestions(
                                getSelectedSubjectId()
                        );

                    } else {

                        JOptionPane.showMessageDialog(
                                dialog,
                                "Operation failed.\n"
                                + "Please check your database connection.",
                                "Error",
                                JOptionPane.ERROR_MESSAGE
                        );
                    }
                }
        );


        buttons.add(
                cancel
        );


        buttons.add(
                save
        );


        dialog.add(
                buttons,
                BorderLayout.SOUTH
        );


        // -----------------------------------------------------
        // LOAD EXISTING DATA FOR EDIT
        // -----------------------------------------------------

        if (editMode) {

            int row =
                    questionTable
                            .getSelectedRow();


            if (row >= 0) {

                String subject =
                        tableModel
                                .getValueAt(
                                        row,
                                        1
                                )
                                .toString();


                String question =
                        tableModel
                                .getValueAt(
                                        row,
                                        2
                                )
                                .toString();


                String a =
                        tableModel
                                .getValueAt(
                                        row,
                                        3
                                )
                                .toString();


                String b =
                        tableModel
                                .getValueAt(
                                        row,
                                        4
                                )
                                .toString();


                String c =
                        tableModel
                                .getValueAt(
                                        row,
                                        5
                                )
                                .toString();


                String d =
                        tableModel
                                .getValueAt(
                                        row,
                                        6
                                )
                                .toString();


                String correct =
                        tableModel
                                .getValueAt(
                                        row,
                                        7
                                )
                                .toString();


                subjectCombo.setSelectedItem(
                        subject
                );


                questionArea.setText(
                        question
                );


                optionA.setText(
                        a
                );


                optionB.setText(
                        b
                );


                optionC.setText(
                        c
                );


                optionD.setText(
                        d
                );


                correctCombo.setSelectedItem(
                        correct
                );
            }
        }


        dialog.setVisible(
                true
        );
    }


    // =========================================================
    // GET SUBJECT ID FROM NAME
    // =========================================================

    private int getSubjectIdFromName(
            String name
    ) {

        for (
                Map.Entry<Integer, String> entry :
                subjects.entrySet()
        ) {

            if (
                    entry.getValue()
                            .equals(name)
            ) {

                return entry.getKey();
            }
        }


        return 0;
    }


    // =========================================================
    // FORM ROW
    // =========================================================

    private void addFormRow(
            JPanel panel,
            GridBagConstraints gbc,
            int row,
            JLabel label,
            java.awt.Component component
    ) {

        gbc.gridx = 0;

        gbc.gridy = row;

        gbc.weightx = 0;

        gbc.gridwidth = 1;


        panel.add(
                label,
                gbc
        );


        gbc.gridx = 1;

        gbc.weightx = 1;

        gbc.gridwidth = 2;


        panel.add(
                component,
                gbc
        );
    }


    // =========================================================
    // LABEL
    // =========================================================

    private JLabel createLabel(
            String text
    ) {

        JLabel label =
                new JLabel(
                        text
                );


        label.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        13
                )
        );


        label.setForeground(
                new Color(
                        50,
                        60,
                        70
                )
        );


        return label;
    }


    // =========================================================
    // TEXT FIELD
    // =========================================================

    private JTextField createTextField() {

        JTextField field =
                new JTextField();


        field.setPreferredSize(
                new Dimension(
                        300,
                        36
                )
        );


        field.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        14
                )
        );


        field.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(
                                        210,
                                        215,
                                        220
                                )
                        ),
                        BorderFactory.createEmptyBorder(
                                5,
                                8,
                                5,
                                8
                        )
                )
        );


        return field;
    }


    // =========================================================
    // COMBO BOX
    // =========================================================

    private void styleComboBox(
            JComboBox<String> combo
    ) {

        combo.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        14
                )
        );


        combo.setBackground(
                Color.WHITE
        );


        combo.setPreferredSize(
                new Dimension(
                        220,
                        36
                )
        );
    }


    // =========================================================
    // BUTTON
    // =========================================================

    private JButton createButton(
            String text,
            Color color
    ) {

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


        button.setBorder(
                BorderFactory.createEmptyBorder(
                        9,
                        16,
                        9,
                        16
                )
        );


        button.setCursor(
                new Cursor(
                        Cursor.HAND_CURSOR
                )
        );


        return button;
    }


    // =========================================================
    // MAIN
    // =========================================================

    public static void main(
            String[] args
    ) {

        new ManageQuestionsFrame();
    }
}