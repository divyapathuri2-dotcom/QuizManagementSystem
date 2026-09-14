package quizmanagement;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.sql.ResultSet;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import quizmanagement.dao.UserDAO;

public class LoginFrame extends JFrame {

    private JTextField usernameField;
    private JPasswordField passwordField;
    private JComboBox<String> roleBox;

    public LoginFrame() {

        setTitle("Quiz Management System - Login");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Main panel
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(
                BorderFactory.createEmptyBorder(30, 50, 30, 50)
        );

        // Title
        JLabel titleLabel =
                new JLabel("QUIZ MANAGEMENT SYSTEM");

        titleLabel.setFont(
                new Font("Arial", Font.BOLD, 22)
        );

        titleLabel.setHorizontalAlignment(
                JLabel.CENTER
        );

        mainPanel.add(titleLabel, BorderLayout.NORTH);

        // Form panel
        JPanel formPanel = new JPanel(new GridBagLayout());

        GridBagConstraints gbc =
                new GridBagConstraints();

        gbc.insets =
                new Insets(10, 10, 10, 10);

        gbc.fill =
                GridBagConstraints.HORIZONTAL;

        // Username
        gbc.gridx = 0;
        gbc.gridy = 0;

        formPanel.add(
                new JLabel("Username:"),
                gbc
        );

        usernameField =
                new JTextField(20);

        gbc.gridx = 1;

        formPanel.add(
                usernameField,
                gbc
        );

        // Password
        gbc.gridx = 0;
        gbc.gridy = 1;

        formPanel.add(
                new JLabel("Password:"),
                gbc
        );

        passwordField =
                new JPasswordField(20);

        gbc.gridx = 1;

        formPanel.add(
                passwordField,
                gbc
        );

        // Role
        gbc.gridx = 0;
        gbc.gridy = 2;

        formPanel.add(
                new JLabel("Role:"),
                gbc
        );

        roleBox =
                new JComboBox<>(
                        new String[]{
                                "ADMIN",
                                "STUDENT"
                        }
                );

        gbc.gridx = 1;

        formPanel.add(
                roleBox,
                gbc
        );

        mainPanel.add(
                formPanel,
                BorderLayout.CENTER
        );

        // Buttons
        JPanel buttonPanel = new JPanel();

        JButton loginButton =
                new JButton("LOGIN");

        JButton clearButton =
                new JButton("CLEAR");

        buttonPanel.add(loginButton);
        buttonPanel.add(clearButton);

        mainPanel.add(
                buttonPanel,
                BorderLayout.SOUTH
        );

        // Login action
        loginButton.addActionListener(
                e -> login()
        );

        // Clear action
        clearButton.addActionListener(e -> {

            usernameField.setText("");
            passwordField.setText("");

        });

        add(mainPanel);

        setVisible(true);
    }

    private void login() {

        String username =
                usernameField.getText().trim();

        String password =
                new String(
                        passwordField.getPassword()
                );

        String selectedRole =
                roleBox.getSelectedItem().toString();

        if (username.isEmpty()
                || password.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter username and password."
            );

            return;
        }

        try {

            ResultSet rs =
                    UserDAO.login(
                            username,
                            password
                    );

            if (rs != null && rs.next()) {

                String databaseRole =
                        rs.getString("role");

                if (!databaseRole.equals(selectedRole)) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Incorrect role selected."
                    );

                    return;
                }

                int userId =
                        rs.getInt("id");

                JOptionPane.showMessageDialog(
                        this,
                        "Login successful!"
                );

                dispose();

                if (databaseRole.equals("ADMIN")) {

                    new quizmanagement.admin.AdminDashboard();

                } else {

                    new quizmanagement.student.StudentDashboard(
                            userId,
                            username
                    );
                }

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Invalid username or password."
                );
            }

        } catch (Exception e) {

            e.printStackTrace();

            JOptionPane.showMessageDialog(
                    this,
                    "Login error: " + e.getMessage()
            );
        }
    }
}