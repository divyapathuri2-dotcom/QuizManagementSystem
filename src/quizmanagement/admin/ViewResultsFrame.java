package quizmanagement.admin;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Insets;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import quizmanagement.DBConnection;

public class ViewResultsFrame extends JFrame {

    private JTable table;
    private DefaultTableModel model;

    private JTextField searchField;
    private JComboBox<String> subjectFilter;

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

    // =========================
    // CONSTRUCTOR
    // =========================

    public ViewResultsFrame() {

        setTitle(
                "Quiz Master - View Results"
        );

        setSize(
                1150,
                700
        );

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

        loadResults();

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
                        1150,
                        80
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

        JLabel title =
                new JLabel(
                        "QUIZ RESULTS"
                );

        title.setForeground(
                Color.WHITE
        );

        title.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        25
                )
        );

        header.add(
                title,
                BorderLayout.WEST
        );

        JLabel subtitle =
                new JLabel(
                        "Student performance and quiz attempts"
                );

        subtitle.setForeground(
                new Color(
                        203,
                        213,
                        225
                )
        );

        subtitle.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        14
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

        // =========================
        // MAIN PANEL
        // =========================

        JPanel mainPanel =
                new JPanel(
                        new BorderLayout(
                                0,
                                15
                        )
                );

        mainPanel.setBackground(
                BACKGROUND
        );

        mainPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        20,
                        25,
                        10,
                        25
                )
        );

        // =========================
        // FILTER PANEL
        // =========================

        JPanel filterPanel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.LEFT,
                                12,
                                5
                        )
                );

        filterPanel.setBackground(
                BACKGROUND
        );

        JLabel searchLabel =
                new JLabel(
                        "Search:"
                );

        searchLabel.setForeground(
                TEXT
        );

        searchLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        14
                )
        );

        searchField =
                new JTextField();

        searchField.setPreferredSize(
                new Dimension(
                        250,
                        38
                )
        );

        searchField.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        14
                )
        );

        searchField.setToolTipText(
                "Search by student name or username"
        );

        // =========================
        // SUBJECT FILTER
        // =========================

        JLabel subjectLabel =
                new JLabel(
                        "Subject:"
                );

        subjectLabel.setForeground(
                TEXT
        );

        subjectLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        14
                )
        );

        subjectFilter =
                new JComboBox<>();

        subjectFilter.addItem(
                "All Subjects"
        );

        subjectFilter.addItem(
                "Java"
        );

        subjectFilter.addItem(
                "DBMS"
        );

        subjectFilter.addItem(
                "Python"
        );

        subjectFilter.addItem(
                "Operating Systems"
        );

        subjectFilter.addItem(
                "Computer Networks"
        );

        subjectFilter.addItem(
                "Data Structures"
        );

        subjectFilter.addItem(
                "Computer Architecture"
        );

        subjectFilter.addItem(
                "Software Engineering"
        );

        subjectFilter.addItem(
                "Web Technologies"
        );

        subjectFilter.addItem(
                "Algorithms"
        );

        subjectFilter.setPreferredSize(
                new Dimension(
                        190,
                        38
                )
        );

        subjectFilter.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        14
                )
        );

        // =========================
        // SEARCH BUTTON
        // =========================

        JButton searchButton =
                createButton(
                        "Search"
                );

        searchButton.addActionListener(
                e -> filterResults()
        );

        // =========================
        // REFRESH BUTTON
        // =========================

        JButton refreshButton =
                createButton(
                        "Refresh"
                );

        refreshButton.addActionListener(
                e -> {

                    searchField.setText("");

                    subjectFilter.setSelectedIndex(
                            0
                    );

                    loadResults();
                }
        );

        filterPanel.add(
                searchLabel
        );

        filterPanel.add(
                searchField
        );

        filterPanel.add(
                subjectLabel
        );

        filterPanel.add(
                subjectFilter
        );

        filterPanel.add(
                searchButton
        );

        filterPanel.add(
                refreshButton
        );

        // IMPORTANT:
        // Filter goes NORTH, NOT CENTER.

        mainPanel.add(
                filterPanel,
                BorderLayout.NORTH
        );

        // =========================
        // TABLE
        // =========================

        model =
                new DefaultTableModel(
                        new String[] {
                                "ID",
                                "Student Name",
                                "Username",
                                "Subject",
                                "Score",
                                "Total",
                                "Percentage",
                                "Quiz Date"
                        },
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

        table =
                new JTable(
                        model
                );

        table.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        13
                )
        );

        table.setForeground(
                TEXT
        );

        table.setBackground(
                Color.WHITE
        );

        table.setRowHeight(
                35
        );

        table.setSelectionMode(
                ListSelectionModel.SINGLE_SELECTION
        );

        table.setGridColor(
                new Color(
                        226,
                        232,
                        240
                )
        );

        table.setShowVerticalLines(
                false
        );

        table.setShowHorizontalLines(
                true
        );

        table.getTableHeader().setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        13
                )
        );

        table.getTableHeader().setBackground(
                DARK
        );

        table.getTableHeader().setForeground(
                Color.WHITE
        );

        table.getTableHeader().setPreferredSize(
                new Dimension(
                        0,
                        40
                )
        );

        table.getTableHeader()
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

        table.getColumnModel()
                .getColumn(0)
                .setCellRenderer(
                        centerRenderer
                );

        table.getColumnModel()
                .getColumn(4)
                .setCellRenderer(
                        centerRenderer
                );

        table.getColumnModel()
                .getColumn(5)
                .setCellRenderer(
                        centerRenderer
                );

        table.getColumnModel()
                .getColumn(6)
                .setCellRenderer(
                        centerRenderer
                );

        // =========================
        // COLUMN WIDTHS
        // =========================

        table.getColumnModel()
                .getColumn(0)
                .setPreferredWidth(50);

        table.getColumnModel()
                .getColumn(1)
                .setPreferredWidth(170);

        table.getColumnModel()
                .getColumn(2)
                .setPreferredWidth(130);

        table.getColumnModel()
                .getColumn(3)
                .setPreferredWidth(150);

        table.getColumnModel()
                .getColumn(4)
                .setPreferredWidth(70);

        table.getColumnModel()
                .getColumn(5)
                .setPreferredWidth(70);

        table.getColumnModel()
                .getColumn(6)
                .setPreferredWidth(100);

        table.getColumnModel()
                .getColumn(7)
                .setPreferredWidth(180);

        JScrollPane scrollPane =
                new JScrollPane(
                        table
                );

        scrollPane.setBorder(
                BorderFactory.createLineBorder(
                        new Color(
                                226,
                                232,
                                240
                        )
                )
        );

        // IMPORTANT:
        // Table goes CENTER.

        mainPanel.add(
                scrollPane,
                BorderLayout.CENTER
        );

        // =========================
        // BOTTOM PANEL
        // =========================

        JPanel bottomPanel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.RIGHT,
                                10,
                                5
                        )
                );

        bottomPanel.setBackground(
                BACKGROUND
        );

        JButton backButton =
                createButton(
                        "Back to Dashboard"
                );

        backButton.addActionListener(
                e -> {

                    dispose();

                    AdminDashboard dashboard =
                            new AdminDashboard();

                    dashboard.setVisible(true);
                }
        );

        bottomPanel.add(
                backButton
        );

        mainPanel.add(
                bottomPanel,
                BorderLayout.SOUTH
        );

        add(
                mainPanel,
                BorderLayout.CENTER
        );
    }

    // =========================
    // LOAD RESULTS
    // =========================

    private void loadResults() {

        model.setRowCount(0);

        String sql =
                "SELECT " +
                "r.id, " +
                "u.name, " +
                "u.username, " +
                "s.subject_name, " +
                "r.score, " +
                "r.total_questions, " +
                "r.quiz_date " +
                "FROM results r " +
                "JOIN users u " +
                "ON r.student_id = u.id " +
                "JOIN subjects s " +
                "ON r.subject_id = s.id " +
                "ORDER BY r.quiz_date DESC";

        try (
                Connection con =
                        DBConnection.getConnection();

                PreparedStatement ps =
                        con.prepareStatement(sql);

                ResultSet rs =
                        ps.executeQuery()
        ) {

            while (rs.next()) {

                int id =
                        rs.getInt("id");

                String name =
                        rs.getString("name");

                String username =
                        rs.getString("username");

                String subject =
                        rs.getString(
                                "subject_name"
                        );

                int score =
                        rs.getInt("score");

                int total =
                        rs.getInt(
                                "total_questions"
                        );

                double percentage = 0;

                if (total > 0) {

                    percentage =
                            (score * 100.0)
                            / total;
                }

                String date =
                        String.valueOf(
                                rs.getTimestamp(
                                        "quiz_date"
                                )
                        );

                if (name == null ||
                        name.trim().isEmpty()) {

                    name = "Not Set";
                }

                model.addRow(
                        new Object[] {
                                id,
                                name,
                                username,
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
                    "Error loading results:\n\n"
                            + e.getMessage(),
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // =========================
    // FILTER RESULTS
    // =========================

    private void filterResults() {

        String search =
                searchField
                        .getText()
                        .trim()
                        .toLowerCase();

        String selectedSubject =
                subjectFilter
                        .getSelectedItem()
                        .toString();

        // Reload all results first.
        loadResults();

        // Remove rows that don't match.
        for (
                int i = model.getRowCount() - 1;
                i >= 0;
                i--
        ) {

            String name =
                    String.valueOf(
                            model.getValueAt(
                                    i,
                                    1
                            )
                    ).toLowerCase();

            String username =
                    String.valueOf(
                            model.getValueAt(
                                    i,
                                    2
                            )
                    ).toLowerCase();

            String subject =
                    String.valueOf(
                            model.getValueAt(
                                    i,
                                    3
                            )
                    );

            boolean matchesSearch =
                    search.isEmpty()
                    || name.contains(search)
                    || username.contains(search);

            boolean matchesSubject =
                    selectedSubject.equals(
                            "All Subjects"
                    )
                    || subject.equals(
                            selectedSubject
                    );

            if (!matchesSearch ||
                    !matchesSubject) {

                model.removeRow(i);
            }
        }
    }

    // =========================
    // CREATE BUTTON
    // =========================

    private JButton createButton(
            String text
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
                PRIMARY
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

        button.setMargin(
                new Insets(
                        8,
                        16,
                        8,
                        16
                )
        );

        return button;
    }
}