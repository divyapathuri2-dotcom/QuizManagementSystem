package quizmanagement.student;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.util.List;

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

import quizmanagement.dao.ResultDAO;

public class QuizHistoryFrame extends JFrame {

    private int studentId;
    private String username;

    private JTable historyTable;
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

    public QuizHistoryFrame(
            int studentId,
            String username) {

        this.studentId = studentId;
        this.username = username;

        setTitle("Quiz Master - Quiz History");

        setSize(950, 650);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(
                JFrame.DISPOSE_ON_CLOSE
        );

        setLayout(
                new BorderLayout()
        );

        getContentPane().setBackground(
                BACKGROUND
        );

        createUI();

        loadHistory();

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
                new Dimension(950, 80)
        );

        header.setBorder(
                BorderFactory.createEmptyBorder(
                        0,
                        35,
                        0,
                        35
                )
        );

        // Logo
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

        // Student information
        JLabel studentLabel =
                new JLabel(
                        "Student: " + username
                );

        studentLabel.setForeground(
                new Color(226, 232, 240)
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
        // TITLE SECTION
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
                        "Quiz History"
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
                        "Track your previous quiz attempts and scores"
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
        // TABLE MODEL
        // =========================

        String[] columns = {
                "Subject",
                "Score",
                "Total Questions",
                "Percentage",
                "Quiz Date"
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

        historyTable =
                new JTable(
                        tableModel
                );

        // =========================
        // TABLE DESIGN
        // =========================

        historyTable.setRowHeight(
                42
        );

        historyTable.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        14
                )
        );

        historyTable.setForeground(
                TEXT
        );

        historyTable.setBackground(
                Color.WHITE
        );

        historyTable.setSelectionBackground(
                new Color(219, 234, 254)
        );

        historyTable.setSelectionForeground(
                TEXT
        );

        historyTable.setGridColor(
                new Color(241, 245, 249)
        );

        historyTable.setShowVerticalLines(
                false
        );

        historyTable.setShowHorizontalLines(
                true
        );

        historyTable.setIntercellSpacing(
                new Dimension(0, 1)
        );

        historyTable.setSelectionMode(
                ListSelectionModel.SINGLE_SELECTION
        );

        // =========================
        // TABLE HEADER
        // =========================

        historyTable.getTableHeader()
                .setPreferredSize(
                        new Dimension(
                                0,
                                45
                        )
                );

        historyTable.getTableHeader()
                .setBackground(
                        DARK
                );

        historyTable.getTableHeader()
                .setForeground(
                        Color.WHITE
                );

        historyTable.getTableHeader()
                .setFont(
                        new Font(
                                "Segoe UI",
                                Font.BOLD,
                                14
                        )
                );

        historyTable.getTableHeader()
                .setReorderingAllowed(
                        false
                );

        // =========================
        // COLUMN WIDTHS
        // =========================

        historyTable
                .getColumnModel()
                .getColumn(0)
                .setPreferredWidth(180);

        historyTable
                .getColumnModel()
                .getColumn(1)
                .setPreferredWidth(100);

        historyTable
                .getColumnModel()
                .getColumn(2)
                .setPreferredWidth(150);

        historyTable
                .getColumnModel()
                .getColumn(3)
                .setPreferredWidth(120);

        historyTable
                .getColumnModel()
                .getColumn(4)
                .setPreferredWidth(180);

        // =========================
        // CENTER ALIGNMENT
        // =========================

        DefaultTableCellRenderer centerRenderer =
                new DefaultTableCellRenderer();

        centerRenderer.setHorizontalAlignment(
                SwingConstants.CENTER
        );

        historyTable
                .getColumnModel()
                .getColumn(1)
                .setCellRenderer(
                        centerRenderer
                );

        historyTable
                .getColumnModel()
                .getColumn(2)
                .setCellRenderer(
                        centerRenderer
                );

        historyTable
                .getColumnModel()
                .getColumn(3)
                .setCellRenderer(
                        centerRenderer
                );

        historyTable
                .getColumnModel()
                .getColumn(4)
                .setCellRenderer(
                        centerRenderer
                );

        // =========================
        // SCROLL PANE
        // =========================

        JScrollPane scrollPane =
                new JScrollPane(
                        historyTable
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

        // Refresh button
        JButton refreshButton =
                createButton(
                        "⟳  Refresh",
                        new Color(71, 85, 105),
                        130
                );

        refreshButton.addActionListener(
                e -> loadHistory()
        );

        // Back button
        JButton backButton =
                createButton(
                        "←  Back",
                        PRIMARY,
                        140
                );

        backButton.addActionListener(e -> {

            dispose();

            new StudentDashboard(
                    studentId,
                    username
            );
        });

        bottomPanel.add(
                refreshButton,
                BorderLayout.WEST
        );

        JPanel rightButtons =
                new JPanel();

        rightButtons.setBackground(
                BACKGROUND
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
    }

    // =========================
    // BUTTON CREATOR
    // =========================

    private JButton createButton(
            String text,
            Color color,
            int width) {

        JButton button =
                new JButton(text);

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
                new java.awt.Cursor(
                        java.awt.Cursor.HAND_CURSOR
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
    // LOAD HISTORY
    // =========================

    private void loadHistory() {

        try {

            List<Object[]> results =
                    ResultDAO.getStudentResults(
                            studentId
                    );

            tableModel.setRowCount(0);

            if (results.isEmpty()) {

                JOptionPane.showMessageDialog(
                        this,
                        "You have not completed any quizzes yet.",
                        "Quiz History",
                        JOptionPane.INFORMATION_MESSAGE
                );

                return;
            }

            for (Object[] result :
                    results) {

                String subject =
                        (String) result[0];

                int score =
                        (int) result[1];

                int total =
                        (int) result[2];

                Object date =
                        result[3];

                double percentage = 0;

                if (total > 0) {

                    percentage =
                            ((double) score / total)
                                    * 100;
                }

                tableModel.addRow(
                        new Object[] {

                                subject,

                                score,

                                total,

                                String.format(
                                        "%.1f%%",
                                        percentage
                                ),

                                date
                        }
                );
            }

        } catch (Exception e) {

            e.printStackTrace();

            JOptionPane.showMessageDialog(
                    this,
                    "Unable to load quiz history.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }
}