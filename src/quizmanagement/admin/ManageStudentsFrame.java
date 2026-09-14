package quizmanagement.admin;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
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
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import quizmanagement.DBConnection;

public class ManageStudentsFrame extends JFrame {

    private JTable studentTable;
    private DefaultTableModel tableModel;
    private JTextField searchField;

    private final Color primaryColor = new Color(31, 78, 121);
    private final Color buttonColor = new Color(45, 100, 220);
    private final Color backgroundColor = new Color(245, 248, 252);

    public ManageStudentsFrame() {

        setTitle("Quiz Master - Manage Students");
        setSize(1200, 750);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        createUI();

        loadStudents();
    }

    private void createUI() {

        setLayout(new BorderLayout());

        // ================= HEADER =================

        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(new Color(29, 43, 63));
        headerPanel.setPreferredSize(new Dimension(1200, 95));
        headerPanel.setBorder(
                BorderFactory.createEmptyBorder(0, 35, 0, 35)
        );

        JLabel titleLabel = new JLabel("MANAGE STUDENTS");
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(
                new Font("SansSerif", Font.BOLD, 30)
        );

        JLabel subtitleLabel = new JLabel(
                "Manage student accounts"
        );
        subtitleLabel.setForeground(
                new Color(220, 225, 235)
        );
        subtitleLabel.setFont(
                new Font("SansSerif", Font.PLAIN, 16)
        );

        headerPanel.add(titleLabel, BorderLayout.WEST);
        headerPanel.add(subtitleLabel, BorderLayout.EAST);

        add(headerPanel, BorderLayout.NORTH);

        // ================= MAIN PANEL =================

        JPanel mainPanel = new JPanel(new BorderLayout(15, 15));
        mainPanel.setBackground(backgroundColor);
        mainPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        20, 18, 15, 18
                )
        );

        // ================= SEARCH PANEL =================

        JPanel searchPanel = new JPanel();
        searchPanel.setBackground(backgroundColor);

        JLabel searchLabel = new JLabel("Search:");
        searchLabel.setFont(
                new Font("SansSerif", Font.BOLD, 15)
        );

        searchField = new JTextField(22);
        searchField.setFont(
                new Font("SansSerif", Font.PLAIN, 15)
        );
        searchField.setPreferredSize(
                new Dimension(280, 40)
        );

        JButton searchButton =
                createButton("Search");

        JButton refreshButton =
                createButton("Refresh");

        JButton addButton =
                createButton("Add Student");

        searchPanel.add(searchLabel);
        searchPanel.add(searchField);
        searchPanel.add(searchButton);
        searchPanel.add(refreshButton);
        searchPanel.add(addButton);

        mainPanel.add(searchPanel, BorderLayout.NORTH);

        // ================= TABLE =================

        String[] columns = {
                "ID",
                "Name",
                "Username",
                "Role"
        };

        tableModel = new DefaultTableModel(columns, 0) {

            @Override
            public boolean isCellEditable(
                    int row,
                    int column) {

                return false;
            }
        };

        studentTable = new JTable(tableModel);

        studentTable.setFont(
                new Font("SansSerif", Font.PLAIN, 15)
        );

        studentTable.setRowHeight(38);

        studentTable.setSelectionMode(
                ListSelectionModel.SINGLE_SELECTION
        );

        studentTable.getTableHeader().setFont(
                new Font("SansSerif", Font.BOLD, 15)
        );

        studentTable.getTableHeader().setBackground(
                primaryColor
        );

        studentTable.getTableHeader().setForeground(
                Color.WHITE
        );

        studentTable.getTableHeader().setPreferredSize(
                new Dimension(0, 42)
        );

        studentTable.setGridColor(
                new Color(210, 215, 220)
        );

        studentTable.setShowGrid(true);

        studentTable.setFillsViewportHeight(true);

        // Column widths

        studentTable.getColumnModel()
                .getColumn(0)
                .setPreferredWidth(70);

        studentTable.getColumnModel()
                .getColumn(1)
                .setPreferredWidth(350);

        studentTable.getColumnModel()
                .getColumn(2)
                .setPreferredWidth(300);

        studentTable.getColumnModel()
                .getColumn(3)
                .setPreferredWidth(150);

        JScrollPane scrollPane =
                new JScrollPane(studentTable);

        scrollPane.setBorder(
                BorderFactory.createLineBorder(
                        new Color(200, 205, 215)
                )
        );

        // IMPORTANT:
        // Table goes in CENTER so it gets the available space.

        mainPanel.add(
                scrollPane,
                BorderLayout.CENTER
        );

        // ================= BUTTON PANEL =================

        JPanel buttonPanel =
                new JPanel(new GridLayout(1, 3, 15, 0));

        buttonPanel.setBackground(backgroundColor);

        JButton resetButton =
                createButton("Reset Password");

        JButton deleteButton =
                createButton("Delete Student");

        JButton backButton =
                createButton("Back");

        buttonPanel.add(resetButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(backButton);

        mainPanel.add(
                buttonPanel,
                BorderLayout.SOUTH
        );

        add(mainPanel, BorderLayout.CENTER);

        // ================= ACTIONS =================

        searchButton.addActionListener(e ->
                searchStudents()
        );

        refreshButton.addActionListener(e -> {

            searchField.setText("");

            loadStudents();
        });

        addButton.addActionListener(e ->
                showAddStudentDialog()
        );

        resetButton.addActionListener(e ->
                resetPassword()
        );

        deleteButton.addActionListener(e ->
                deleteStudent()
        );

        backButton.addActionListener(e ->
                dispose()
        );

        searchField.addActionListener(e ->
                searchStudents()
        );
    }

    // ================= BUTTON =================

    private JButton createButton(String text) {

        JButton button = new JButton(text);

        button.setFont(
                new Font("SansSerif", Font.BOLD, 15)
        );

        button.setForeground(Color.WHITE);
        button.setBackground(buttonColor);

        button.setFocusPainted(false);

        button.setBorder(
                BorderFactory.createEmptyBorder(
                        10, 20, 10, 20
                )
        );

        button.setCursor(
                new Cursor(Cursor.HAND_CURSOR)
        );

        return button;
    }

    // ================= LOAD STUDENTS =================

    private void loadStudents() {

        tableModel.setRowCount(0);

        String sql =
                "SELECT id, name, username, role " +
                "FROM users " +
                "WHERE role = 'STUDENT' " +
                "ORDER BY id";

        try (
                Connection con =
                        DBConnection.getConnection();

                PreparedStatement ps =
                        con.prepareStatement(sql);

                ResultSet rs =
                        ps.executeQuery()
        ) {

            while (rs.next()) {

                tableModel.addRow(
                        new Object[] {
                                rs.getInt("id"),
                                rs.getString("name"),
                                rs.getString("username"),
                                rs.getString("role")
                        }
                );
            }

        } catch (Exception e) {

            e.printStackTrace();

            JOptionPane.showMessageDialog(
                    this,
                    "Unable to load students.\n\n"
                    + e.getMessage(),
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // ================= SEARCH =================

    private void searchStudents() {

        String keyword =
                searchField.getText().trim();

        if (keyword.isEmpty()) {

            loadStudents();

            return;
        }

        tableModel.setRowCount(0);

        String sql =
                "SELECT id, name, username, role " +
                "FROM users " +
                "WHERE role = 'STUDENT' " +
                "AND (LOWER(name) LIKE ? " +
                "OR LOWER(username) LIKE ?) " +
                "ORDER BY id";

        try (
                Connection con =
                        DBConnection.getConnection();

                PreparedStatement ps =
                        con.prepareStatement(sql)
        ) {

            String searchValue =
                    "%" + keyword.toLowerCase() + "%";

            ps.setString(1, searchValue);
            ps.setString(2, searchValue);

            try (ResultSet rs =
                    ps.executeQuery()) {

                while (rs.next()) {

                    tableModel.addRow(
                            new Object[] {
                                    rs.getInt("id"),
                                    rs.getString("name"),
                                    rs.getString("username"),
                                    rs.getString("role")
                            }
                    );
                }
            }

        } catch (Exception e) {

            e.printStackTrace();

            JOptionPane.showMessageDialog(
                    this,
                    "Search failed.\n\n"
                    + e.getMessage(),
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // ================= ADD STUDENT =================

    private void showAddStudentDialog() {

        JTextField nameField =
                new JTextField();

        JTextField usernameField =
                new JTextField();

        JTextField passwordField =
                new JTextField();

        JPanel panel = new JPanel(
                new GridLayout(0, 1, 5, 5)
        );

        panel.add(new JLabel("Student Name:"));
        panel.add(nameField);

        panel.add(new JLabel("Username:"));
        panel.add(usernameField);

        panel.add(new JLabel("Password:"));
        panel.add(passwordField);

        int result =
                JOptionPane.showConfirmDialog(
                        this,
                        panel,
                        "Add Student",
                        JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.PLAIN_MESSAGE
                );

        if (result != JOptionPane.OK_OPTION) {
            return;
        }

        String name =
                nameField.getText().trim();

        String username =
                usernameField.getText().trim();

        String password =
                passwordField.getText().trim();

        if (name.isEmpty()
                || username.isEmpty()
                || password.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please fill all fields.",
                    "Validation",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        String sql =
                "INSERT INTO users " +
                "(name, username, password, role) " +
                "VALUES (?, ?, ?, 'STUDENT')";

        try (
                Connection con =
                        DBConnection.getConnection();

                PreparedStatement ps =
                        con.prepareStatement(sql)
        ) {

            ps.setString(1, name);
            ps.setString(2, username);
            ps.setString(3, password);

            int rows =
                    ps.executeUpdate();

            if (rows > 0) {

                JOptionPane.showMessageDialog(
                        this,
                        "Student added successfully!",
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE
                );

                loadStudents();
            }

        } catch (Exception e) {

            if (e.getMessage() != null
                    && e.getMessage()
                       .toLowerCase()
                       .contains("duplicate")) {

                JOptionPane.showMessageDialog(
                        this,
                        "Username already exists.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );

            } else {

                e.printStackTrace();

                JOptionPane.showMessageDialog(
                        this,
                        "Unable to add student.\n\n"
                        + e.getMessage(),
                        "Database Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        }
    }

    // ================= RESET PASSWORD =================

    private void resetPassword() {

        int row =
                studentTable.getSelectedRow();

        if (row == -1) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please select a student first.",
                    "Select Student",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        int studentId =
                Integer.parseInt(
                        studentTable
                                .getValueAt(row, 0)
                                .toString()
                );

        String username =
                studentTable
                        .getValueAt(row, 2)
                        .toString();

        String newPassword =
                JOptionPane.showInputDialog(
                        this,
                        "Enter new password for "
                        + username + ":",
                        "Reset Password",
                        JOptionPane.PLAIN_MESSAGE
                );

        if (newPassword == null) {
            return;
        }

        newPassword =
                newPassword.trim();

        if (newPassword.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Password cannot be empty.",
                    "Validation",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        String sql =
                "UPDATE users " +
                "SET password = ? " +
                "WHERE id = ? " +
                "AND role = 'STUDENT'";

        try (
                Connection con =
                        DBConnection.getConnection();

                PreparedStatement ps =
                        con.prepareStatement(sql)
        ) {

            ps.setString(1, newPassword);
            ps.setInt(2, studentId);

            if (ps.executeUpdate() > 0) {

                JOptionPane.showMessageDialog(
                        this,
                        "Password reset successfully!",
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE
                );
            }

        } catch (Exception e) {

            e.printStackTrace();

            JOptionPane.showMessageDialog(
                    this,
                    "Unable to reset password.\n\n"
                    + e.getMessage(),
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // ================= DELETE STUDENT =================

    private void deleteStudent() {

        int row =
                studentTable.getSelectedRow();

        if (row == -1) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please select a student first.",
                    "Select Student",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        int studentId =
                Integer.parseInt(
                        studentTable
                                .getValueAt(row, 0)
                                .toString()
                );

        String username =
                studentTable
                        .getValueAt(row, 2)
                        .toString();

        int confirm =
                JOptionPane.showConfirmDialog(
                        this,
                        "Delete student '" + username + "'?\n\n"
                        + "This may fail if the student has quiz results.",
                        "Confirm Delete",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.WARNING_MESSAGE
                );

        if (confirm != JOptionPane.YES_OPTION) {
            return;
        }

        String sql =
                "DELETE FROM users " +
                "WHERE id = ? " +
                "AND role = 'STUDENT'";

        try (
                Connection con =
                        DBConnection.getConnection();

                PreparedStatement ps =
                        con.prepareStatement(sql)
        ) {

            ps.setInt(1, studentId);

            if (ps.executeUpdate() > 0) {

                JOptionPane.showMessageDialog(
                        this,
                        "Student deleted successfully!",
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE
                );

                loadStudents();
            }

        } catch (Exception e) {

            e.printStackTrace();

            JOptionPane.showMessageDialog(
                    this,
                    "Student could not be deleted.\n\n"
                    + "The student may have quiz results linked "
                    + "to this account.\n\n"
                    + e.getMessage(),
                    "Delete Failed",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }
}