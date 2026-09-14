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
import javax.swing.JPasswordField;

import quizmanagement.dao.UserDAO;

public class ProfileFrame extends JFrame {

    private static final Color DARK =
            new Color(25, 32, 45);

    private static final Color BLUE =
            new Color(52, 152, 219);

    private static final Color GREEN =
            new Color(46, 204, 113);

    private static final Color LIGHT =
            new Color(245, 247, 250);

    private int studentId;

    private String username;

    public ProfileFrame(
            int studentId,
            String username
    ) {

        this.studentId = studentId;
        this.username = username;

        setTitle("Quiz Master - My Profile");

        setSize(700, 550);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(
                JFrame.DISPOSE_ON_CLOSE
        );

        setLayout(new BorderLayout());

        createUI();

        setVisible(true);
    }

    private void createUI() {

        // ================= HEADER =================

        JPanel header =
                new JPanel(new BorderLayout());

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
                new JPanel(new BorderLayout());

        titlePanel.setOpaque(false);

        JLabel title =
                new JLabel("QUIZ MASTER");

        title.setForeground(Color.WHITE);

        title.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        23
                )
        );

        JLabel subtitle =
                new JLabel("My Profile");

        subtitle.setForeground(
                new Color(190, 200, 215)
        );

        subtitle.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        12
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

        JButton backButton =
                createButton(
                        "Back",
                        BLUE
                );

        backButton.addActionListener(
                e -> dispose()
        );

        header.add(
                backButton,
                BorderLayout.EAST
        );

        add(
                header,
                BorderLayout.NORTH
        );


        // ================= MAIN =================

        JPanel main =
                new JPanel(
                        new BorderLayout(
                                0,
                                20
                        )
                );

        main.setBackground(LIGHT);

        main.setBorder(
                BorderFactory.createEmptyBorder(
                        25,
                        40,
                        25,
                        40
                )
        );


        // ================= PROFILE CARD =================

        JPanel profileCard =
                new JPanel(
                        new BorderLayout(
                                0,
                                20
                        )
                );

        profileCard.setBackground(Color.WHITE);

        profileCard.setBorder(
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
                                30,
                                25,
                                30
                        )
                )
        );


        // ================= PROFILE TITLE =================

        JPanel profileTitle =
                new JPanel(new BorderLayout());

        profileTitle.setOpaque(false);

        JLabel profileHeading =
                new JLabel(
                        "Student Information"
                );

        profileHeading.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        20
                )
        );

        profileHeading.setForeground(DARK);

        JLabel profileDescription =
                new JLabel(
                        "Your account information"
                );

        profileDescription.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        12
                )
        );

        profileDescription.setForeground(
                new Color(
                        120,
                        125,
                        130
                )
        );

        profileTitle.add(
                profileHeading,
                BorderLayout.NORTH
        );

        profileTitle.add(
                profileDescription,
                BorderLayout.SOUTH
        );

        profileCard.add(
                profileTitle,
                BorderLayout.NORTH
        );


        // ================= INFORMATION =================

        JPanel information =
                new JPanel(
                        new GridLayout(
                                3,
                                1,
                                0,
                                12
                        )
                );

        information.setOpaque(false);

        information.add(
                createInfoCard(
                        "Student ID",
                        String.valueOf(studentId),
                        BLUE
                )
        );

        information.add(
                createInfoCard(
                        "Username",
                        username,
                        GREEN
                )
        );

        information.add(
                createInfoCard(
                        "Role",
                        "STUDENT",
                        BLUE
                )
        );

        profileCard.add(
                information,
                BorderLayout.CENTER
        );

        main.add(
                profileCard,
                BorderLayout.CENTER
        );


        // ================= BOTTOM BUTTONS =================

        JPanel bottom =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.CENTER,
                                12,
                                0
                        )
                );

        bottom.setOpaque(false);


        // Change Password Button

        JButton passwordButton =
                createButton(
                        "Change Password",
                        GREEN
                );

        passwordButton.addActionListener(
                e -> showChangePasswordDialog()
        );


        // Back Button

        JButton closeButton =
                createButton(
                        "Back to Dashboard",
                        BLUE
                );

        closeButton.addActionListener(
                e -> dispose()
        );

        bottom.add(passwordButton);

        bottom.add(closeButton);

        main.add(
                bottom,
                BorderLayout.SOUTH
        );

        add(
                main,
                BorderLayout.CENTER
        );
    }


    // ================= INFORMATION CARD =================

    private JPanel createInfoCard(
            String label,
            String value,
            Color color
    ) {

        JPanel card =
                new JPanel(
                        new BorderLayout(
                                15,
                                0
                        )
                );

        card.setBackground(
                new Color(
                        250,
                        251,
                        253
                )
        );

        card.setBorder(
                BorderFactory.createLineBorder(
                        new Color(
                                230,
                                233,
                                237
                        )
                )
        );

        JPanel indicator =
                new JPanel();

        indicator.setBackground(color);

        indicator.setPreferredSize(
                new Dimension(
                        6,
                        1
                )
        );

        card.add(
                indicator,
                BorderLayout.WEST
        );


        JPanel textPanel =
                new JPanel(
                        new BorderLayout(
                                0,
                                4
                        )
                );

        textPanel.setOpaque(false);

        JLabel labelText =
                new JLabel(label);

        labelText.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        12
                )
        );

        labelText.setForeground(
                new Color(
                        120,
                        125,
                        130
                )
        );

        JLabel valueText =
                new JLabel(value);

        valueText.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        15
                )
        );

        valueText.setForeground(DARK);

        textPanel.add(
                labelText,
                BorderLayout.NORTH
        );

        textPanel.add(
                valueText,
                BorderLayout.SOUTH
        );

        card.add(
                textPanel,
                BorderLayout.CENTER
        );

        return card;
    }


    // ================= CHANGE PASSWORD =================

    private void showChangePasswordDialog() {

        JPasswordField oldPassword =
                new JPasswordField();

        JPasswordField newPassword =
                new JPasswordField();

        JPasswordField confirmPassword =
                new JPasswordField();


        JPanel panel =
                new JPanel(
                        new GridLayout(
                                3,
                                2,
                                10,
                                10
                        )
                );

        panel.add(
                new JLabel("Current Password:")
        );

        panel.add(oldPassword);

        panel.add(
                new JLabel("New Password:")
        );

        panel.add(newPassword);

        panel.add(
                new JLabel("Confirm Password:")
        );

        panel.add(confirmPassword);


        int result =
                JOptionPane.showConfirmDialog(
                        this,
                        panel,
                        "Change Password",
                        JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.PLAIN_MESSAGE
                );


        if (result != JOptionPane.OK_OPTION) {

            return;
        }


        String oldPass =
                new String(
                        oldPassword.getPassword()
                );

        String newPass =
                new String(
                        newPassword.getPassword()
                );

        String confirmPass =
                new String(
                        confirmPassword.getPassword()
                );


        // Check empty fields

        if (oldPass.isEmpty()
                || newPass.isEmpty()
                || confirmPass.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please fill all password fields.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );

            return;
        }


        // Check password match

        if (!newPass.equals(confirmPass)) {

            JOptionPane.showMessageDialog(
                    this,
                    "New password and confirm password do not match.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );

            return;
        }


        // Check same password

        if (oldPass.equals(newPass)) {

            JOptionPane.showMessageDialog(
                    this,
                    "New password must be different from current password.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );

            return;
        }


        // Update database

        boolean updated =
                UserDAO.changePassword(
                        studentId,
                        oldPass,
                        newPass
                );


        if (updated) {

            JOptionPane.showMessageDialog(
                    this,
                    "Password changed successfully!",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE
            );

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "Current password is incorrect.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }


    // ================= BUTTON =================

    private JButton createButton(
            String text,
            Color color
    ) {

        JButton button =
                new JButton(text);

        button.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        13
                )
        );

        button.setForeground(Color.WHITE);

        button.setBackground(color);

        button.setFocusPainted(false);

        button.setCursor(
                new Cursor(
                        Cursor.HAND_CURSOR
                )
        );

        button.setBorder(
                BorderFactory.createEmptyBorder(
                        10,
                        18,
                        10,
                        18
                )
        );

        return button;
    }


    // ================= MAIN =================

    public static void main(
            String[] args
    ) {

        new ProfileFrame(
                1,
                "student"
        );
    }
}