package quizmanagement.admin;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Insets;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import quizmanagement.dao.SubjectDAO;

public class ManageSubjectsFrame extends JFrame {

    private JList<String> subjectList;
    private DefaultListModel<String> listModel;

    private JTextField subjectField;

    private JLabel countLabel;

    // =====================================================
    // COLORS
    // =====================================================

    private final Color primaryColor =
            new Color(31, 78, 121);

    private final Color backgroundColor =
            new Color(245, 248, 252);

    private final Color addColor =
            new Color(0, 150, 90);

    private final Color deleteColor =
            new Color(210, 55, 55);

    private final Color refreshColor =
            new Color(70, 100, 140);

    private final Color backColor =
            new Color(100, 110, 120);

    // =====================================================
    // CONSTRUCTOR
    // =====================================================

    public ManageSubjectsFrame() {

        setTitle("Quiz Master - Manage Subjects");

        setSize(700, 560);

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
                new Dimension(700, 80)
        );

        JLabel title =
                new JLabel(
                        "  Manage Subjects"
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
                        "Add and manage quiz subjects  "
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
        // MAIN PANEL
        // =================================================

        JPanel mainPanel =
                new JPanel(
                        new BorderLayout(
                                10,
                                10
                        )
                );

        mainPanel.setBackground(
                backgroundColor
        );

        mainPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        20,
                        25,
                        10,
                        25
                )
        );

        // =================================================
        // ADD SUBJECT PANEL
        // =================================================

        JPanel addPanel =
                new JPanel(
                        new BorderLayout(
                                10,
                                10
                        )
                );

        addPanel.setBackground(Color.WHITE);

        addPanel.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(220, 225, 232)
                        ),
                        BorderFactory.createEmptyBorder(
                                15,
                                15,
                                15,
                                15
                        )
                )
        );

        JLabel addTitle =
                new JLabel(
                        "Add New Subject"
                );

        addTitle.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        17
                )
        );

        addTitle.setForeground(
                primaryColor
        );

        addPanel.add(
                addTitle,
                BorderLayout.NORTH
        );

        JPanel inputPanel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.LEFT,
                                10,
                                5
                        )
                );

        inputPanel.setOpaque(false);

        subjectField =
                new JTextField();

        subjectField.setPreferredSize(
                new Dimension(350, 38)
        );

        subjectField.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        14
                )
        );

        subjectField.setToolTipText(
                "Enter subject name"
        );

        inputPanel.add(subjectField);

        JButton addButton =
                new JButton(
                        "Add Subject"
                );

        styleButton(
                addButton,
                addColor,
                125
        );

        inputPanel.add(addButton);

        addPanel.add(
                inputPanel,
                BorderLayout.CENTER
        );

        mainPanel.add(
                addPanel,
                BorderLayout.NORTH
        );

        // =================================================
        // SUBJECT LIST PANEL
        // =================================================

        JPanel listPanel =
                new JPanel(
                        new BorderLayout(
                                10,
                                10
                        )
                );

        listPanel.setBackground(Color.WHITE);

        listPanel.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(220, 225, 232)
                        ),
                        BorderFactory.createEmptyBorder(
                                15,
                                15,
                                15,
                                15
                        )
                )
        );

        JPanel listHeader =
                new JPanel(
                        new BorderLayout()
                );

        listHeader.setOpaque(false);

        JLabel listTitle =
                new JLabel(
                        "Available Subjects"
                );

        listTitle.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        17
                )
        );

        listTitle.setForeground(
                primaryColor
        );

        listHeader.add(
                listTitle,
                BorderLayout.WEST
        );

        countLabel =
                new JLabel(
                        "Subjects: 0"
                );

        countLabel.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        13
                )
        );

        countLabel.setForeground(
                new Color(90, 100, 115)
        );

        listHeader.add(
                countLabel,
                BorderLayout.EAST
        );

        listPanel.add(
                listHeader,
                BorderLayout.NORTH
        );

        // Subject list
        listModel =
                new DefaultListModel<>();

        subjectList =
                new JList<>(
                        listModel
                );

        subjectList.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        15
                )
        );

        subjectList.setSelectionMode(
                ListSelectionModel.SINGLE_SELECTION
        );

        subjectList.setFixedCellHeight(40);

        subjectList.setBorder(
                BorderFactory.createEmptyBorder(
                        5,
                        10,
                        5,
                        10
                )
        );

        JScrollPane scrollPane =
                new JScrollPane(
                        subjectList
                );

        scrollPane.setBorder(
                BorderFactory.createLineBorder(
                        new Color(225, 230, 235)
                )
        );

        listPanel.add(
                scrollPane,
                BorderLayout.CENTER
        );

        mainPanel.add(
                listPanel,
                BorderLayout.CENTER
        );

        add(
                mainPanel,
                BorderLayout.CENTER
        );

        // =================================================
        // BOTTOM BUTTONS
        // =================================================

        JPanel bottomPanel =
                new JPanel(
                        new BorderLayout()
                );

        bottomPanel.setBackground(
                backgroundColor
        );

        bottomPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        5,
                        25,
                        15,
                        25
                )
        );

        JButton deleteButton =
                new JButton(
                        "Delete Selected"
                );

        styleButton(
                deleteButton,
                deleteColor,
                145
        );

        bottomPanel.add(
                deleteButton,
                BorderLayout.WEST
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

        JButton refreshButton =
                new JButton(
                        "Refresh"
                );

        styleButton(
                refreshButton,
                refreshColor,
                110
        );

        JButton backButton =
                new JButton(
                        "Back"
                );

        styleButton(
                backButton,
                backColor,
                100
        );

        rightButtons.add(
                refreshButton
        );

        rightButtons.add(
                backButton
        );

        bottomPanel.add(
                rightButtons,
                BorderLayout.EAST
        );

        add(
                bottomPanel,
                BorderLayout.SOUTH
        );

        // =================================================
        // ACTIONS
        // =================================================

        addButton.addActionListener(
                e -> addSubject()
        );

        deleteButton.addActionListener(
                e -> deleteSubject()
        );

        refreshButton.addActionListener(
                e -> loadSubjects()
        );

        backButton.addActionListener(
                e -> dispose()
        );

        // Press Enter to add
        subjectField.addActionListener(
                e -> addSubject()
        );

        // =================================================
        // LOAD SUBJECTS
        // =================================================

        loadSubjects();

        setVisible(true);
    }

    // =====================================================
    // LOAD SUBJECTS
    // =====================================================

    private void loadSubjects() {

        listModel.clear();

        try {

            Map<Integer, String> subjects =
                    SubjectDAO.getAllSubjects();

            for (String subjectName :
                    subjects.values()) {

                listModel.addElement(
                        subjectName
                );
            }

            updateCount();

        } catch (Exception e) {

            e.printStackTrace();

            JOptionPane.showMessageDialog(
                    this,
                    "Unable to load subjects.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // =====================================================
    // ADD SUBJECT
    // =====================================================

    private void addSubject() {

        String subjectName =
                subjectField
                        .getText()
                        .trim();

        // Empty check
        if (subjectName.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter a subject name.",
                    "Missing Subject",
                    JOptionPane.WARNING_MESSAGE
            );

            subjectField.requestFocus();

            return;
        }

        // Length check
        if (subjectName.length() > 100) {

            JOptionPane.showMessageDialog(
                    this,
                    "Subject name must be 100 characters or less.",
                    "Invalid Subject",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        // Check duplicate in current list
        for (int i = 0;
                i < listModel.size();
                i++) {

            if (listModel
                    .getElementAt(i)
                    .equalsIgnoreCase(
                            subjectName
                    )) {

                JOptionPane.showMessageDialog(
                        this,
                        "This subject already exists.",
                        "Duplicate Subject",
                        JOptionPane.WARNING_MESSAGE
                );

                subjectField.requestFocus();

                return;
            }
        }

        boolean added =
                SubjectDAO.addSubject(
                        subjectName
                );

        if (added) {

            JOptionPane.showMessageDialog(
                    this,
                    "Subject added successfully!",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE
            );

            subjectField.setText("");

            loadSubjects();

            subjectField.requestFocus();

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "Unable to add subject.\n"
                    + "The subject may already exist.",
                    "Add Failed",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // =====================================================
    // DELETE SUBJECT
    // =====================================================

    private void deleteSubject() {

        String selectedSubject =
                subjectList.getSelectedValue();

        if (selectedSubject == null) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please select a subject to delete.",
                    "No Subject Selected",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        int confirmation =
                JOptionPane.showConfirmDialog(
                        this,
                        "Are you sure you want to delete\n"
                        + "\"" + selectedSubject + "\"?",
                        "Confirm Delete",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.WARNING_MESSAGE
                );

        if (confirmation !=
                JOptionPane.YES_OPTION) {

            return;
        }

        // Find subject ID
        Map<Integer, String> subjects =
                SubjectDAO.getAllSubjects();

        int subjectId = -1;

        for (Map.Entry<Integer, String> entry :
                subjects.entrySet()) {

            if (entry.getValue()
                    .equals(selectedSubject)) {

                subjectId =
                        entry.getKey();

                break;
            }
        }

        if (subjectId == -1) {

            JOptionPane.showMessageDialog(
                    this,
                    "Unable to find the selected subject.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );

            return;
        }

        boolean deleted =
                SubjectDAO.deleteSubject(
                        subjectId
                );

        if (deleted) {

            JOptionPane.showMessageDialog(
                    this,
                    "Subject deleted successfully!",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE
            );

            loadSubjects();

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "This subject cannot be deleted.\n\n"
                    + "It may already have questions or quiz results "
                    + "associated with it.",
                    "Cannot Delete Subject",
                    JOptionPane.WARNING_MESSAGE
            );
        }
    }

    // =====================================================
    // UPDATE COUNT
    // =====================================================

    private void updateCount() {

        countLabel.setText(
                "Subjects: " + listModel.size()
        );
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