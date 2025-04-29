import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.Pattern;

public class RegisterDialog extends JDialog {

    private static final long serialVersionUID = 1L;
    private JTextField idField;
    private JPasswordField passwordField;
    private JPasswordField confirmField;
    private JButton registerButton;
    private JButton cancelButton;
    private boolean registeredSuccessfully;
    private String userName;
    private JTextField nameField;
    private JLabel genderLabel;
    private JRadioButton maleButton;
    private JRadioButton femaleButton;
    private ButtonGroup genderGroup;
    private JLabel titleLabel;
    private JLabel explainLabel;

    /**
     * Create the dialog.
     */
    public RegisterDialog() {
        setTitle("Register to StyleHub");
        setBounds(100, 100, 423, 452);
        getContentPane().setLayout(new BorderLayout());
        JPanel contentPanel = new JPanel();
        contentPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(null);

        JLabel nameLabel = new JLabel("Full Name:");
        nameLabel.setFont(new Font("Arial", Font.PLAIN, 15));
        nameLabel.setBounds(22, 100, 131, 47);
        nameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        contentPanel.add(nameLabel);

        nameField = new JTextField();
        nameField.setFont(new Font("굴림", Font.BOLD, 15));
        nameField.setBounds(165, 101, 232, 47);
        contentPanel.add(nameField);
        nameField.setColumns(10);

        JLabel idLabel = new JLabel("ID: ");
        idLabel.setBounds(12, 211, 141, 47);
        idLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        idLabel.setFont(new Font("Arial", Font.PLAIN, 15));
        contentPanel.add(idLabel);

        idField = new JTextField();
        idField.setFont(new Font("굴림", Font.BOLD, 15));
        idField.setBounds(165, 211, 232, 47);
        contentPanel.add(idField);
        idField.setColumns(10);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(12, 268, 141, 47);
        passwordLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        passwordLabel.setFont(new Font("Arial", Font.PLAIN, 15));
        contentPanel.add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setFont(new Font("굴림", Font.BOLD, 15));
        passwordField.setBounds(165, 268, 232, 47);
        contentPanel.add(passwordField);

        JLabel confirmLabel = new JLabel("Confirm Password:");
        confirmLabel.setBounds(12, 325, 141, 47);
        confirmLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        confirmLabel.setFont(new Font("Arial", Font.PLAIN, 15));
        contentPanel.add(confirmLabel);

        confirmField = new JPasswordField();
        confirmField.setFont(new Font("굴림", Font.BOLD, 15));
        confirmField.setBounds(165, 325, 232, 47);
        contentPanel.add(confirmField);

        genderLabel = new JLabel("Gender:");
        genderLabel.setFont(new Font("Arial", Font.PLAIN, 15));
        genderLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        genderLabel.setBounds(22, 158, 131, 47);
        contentPanel.add(genderLabel);

        maleButton = new JRadioButton("Male");
        maleButton.setFont(new Font("Arial", Font.PLAIN, 15));
        maleButton.setBounds(165, 162, 76, 31);
        contentPanel.add(maleButton);

        femaleButton = new JRadioButton("Female");
        femaleButton.setFont(new Font("Arial", Font.PLAIN, 15));
        femaleButton.setBounds(262, 162, 89, 31);
        contentPanel.add(femaleButton);

        genderGroup = new ButtonGroup();
        genderGroup.add(maleButton);
        genderGroup.add(femaleButton);
        
        titleLabel = new JLabel("Welcome to StyleHub");
        titleLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 23));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setBounds(69, 10, 260, 31);
        contentPanel.add(titleLabel);
        
        explainLabel = new JLabel("(Register)");
        explainLabel.setFont(new Font("Arial", Font.BOLD, 15));
        explainLabel.setHorizontalAlignment(SwingConstants.CENTER);
        explainLabel.setBounds(124, 43, 141, 23);
        contentPanel.add(explainLabel);

        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
        getContentPane().add(buttonPane, BorderLayout.SOUTH);

        registerButton = new JButton("Register");
        registerButton.setFont(new Font("Arial", Font.PLAIN, 12));
        registerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String nameInput = nameField.getText().trim();
                    String idInput = idField.getText().trim();
                    String passwordInput = new String(passwordField.getPassword());
                    String confirmInput = new String(confirmField.getPassword());
                    String genderInput = maleButton.isSelected() ? "Male" : (femaleButton.isSelected() ? "Female" : "Unknown");

                    if (nameInput.isEmpty() || idInput.isEmpty() || passwordInput.isEmpty() || confirmInput.isEmpty()) {
                        throw new IllegalArgumentException("Please fill out all fields.");
                    }
                    if (containsSpecialCharacter(idInput)) {		// case containing special characters
                        throw new IllegalArgumentException("ID cannot contain special characters.");
                    }
                    if (!passwordInput.equals(confirmInput)) { 		// case that don't match passwords
                        throw new IllegalArgumentException("Passwords do not match. Please try again.");
                    }
                    if (!isPasswordValid(passwordInput)) {
                        throw new IllegalArgumentException("Password must contain at least one uppercase letter, one lowercase letter, one digit, and one special character.");
                    }
                    if (registerUser(nameInput, genderInput, idInput, passwordInput)) {
                        registeredSuccessfully = true;
                        userName = idInput;
                        dispose();
                    } else {
                        throw new IOException("Failed to register user. Please try again.");
                    }
                } catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(RegisterDialog.this, ex.getMessage(), "Registration Failed", JOptionPane.ERROR_MESSAGE);
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(RegisterDialog.this, ex.getMessage(), "Registration Failed", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        buttonPane.add(registerButton);
        getRootPane().setDefaultButton(registerButton);

        cancelButton = new JButton("Cancel");		
        cancelButton.setFont(new Font("Arial", Font.PLAIN, 12));
        cancelButton.addActionListener(new ActionListener() {		// press cancel, the registration will be again
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        buttonPane.add(cancelButton);
        registeredSuccessfully = false;
    }

    private boolean containsSpecialCharacter(String input) {		// contain special characters
        return !Pattern.compile("^[a-zA-Z0-9]*$").matcher(input).matches();
    }

    private boolean registerUser(String userName, String gender, String id, String password) {		// save information to a file
        File file = new File("data.txt");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
            writer.write("User Name:" + userName + "\n");
            writer.write("Gender:" + gender + "\n");
            writer.write("ID:" + id + "\n");
            writer.write("Password:" + password + "\n");
            writer.write("-----\n");
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    private boolean isPasswordValid(String password) {		// include uppercase, lowercase, numbers, and special characters
        return Pattern.compile(".*[A-Z].*").matcher(password).find() && Pattern.compile(".*[a-z].*").matcher(password).find() &&
               Pattern.compile(".*\\d.*").matcher(password).find() && Pattern.compile(".*[!@#$%^&*(),.?\":{}|<>].*").matcher(password).find();
    }

    public boolean isRegisteredSuccessfully() {
        return registeredSuccessfully;
    }

    public String getUserName() {
        return userName;
    }
}