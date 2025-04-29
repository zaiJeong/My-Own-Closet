import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class LoginDialog extends JDialog {

    private static final long serialVersionUID = 1L;
    private JTextField idField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton cancelButton;
    private boolean loggedIn;
    private String userName;
    private JLabel titleLabel;

    /**
     * Create the dialog.
     */
    public LoginDialog() {
        setTitle("Login to StyleHub");
        setBounds(100, 100, 360, 231);
        getContentPane().setLayout(new BorderLayout());
        JPanel contentPanel = new JPanel();
        contentPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(null);

        JLabel idLabel = new JLabel("ID: ");
        idLabel.setBounds(12, 68, 153, 30);
        idLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        idLabel.setFont(new Font("Arial", Font.PLAIN, 15));
        contentPanel.add(idLabel);

        idField = new JTextField();
        idField.setBounds(175, 68, 153, 30);
        contentPanel.add(idField);
        idField.setColumns(10);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(12, 108, 153, 30);
        passwordLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        passwordLabel.setFont(new Font("Arial", Font.PLAIN, 15));
        contentPanel.add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(175, 108, 153, 30);
        contentPanel.add(passwordField);
        
        titleLabel = new JLabel("Please Login");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 17));
        titleLabel.setBounds(114, 10, 138, 30);
        contentPanel.add(titleLabel);

        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
        getContentPane().add(buttonPane, BorderLayout.SOUTH);

        loginButton = new JButton("Login");
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (loggedIn) {									// already logged in
                    JOptionPane.showMessageDialog(LoginDialog.this, "You are already logged in as " + userName + ".", 
                    		"Login Info", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                String idInput = idField.getText().trim();
                String passwordInput = new String(passwordField.getPassword());
                if (checkLogin(idInput, passwordInput)) {
                    loggedIn = true;
                    userName = idInput;
                    JOptionPane.showMessageDialog(LoginDialog.this, "Login successful!", 
                    		"Login Success", JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(LoginDialog.this, "Invalid username or password. Please try again.",
                            "Login Failed", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        buttonPane.add(loginButton);
        getRootPane().setDefaultButton(loginButton);

        cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        buttonPane.add(cancelButton);
        loggedIn = false;
    }
    
    private boolean checkLogin(String userName, String password) {				// method to check user input
        File file = new File("data.txt");
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {				// open a file and compare it to input
                if (line.startsWith("ID:")) {
                    String storedID = line.split(":")[1].trim();
                    String storedPassword = null;
                    while ((line = reader.readLine()) != null && !line.isEmpty()) {
                        if (line.startsWith("Password:")) {
                            storedPassword = line.split(":")[1].trim();
                            break;
                        }
                    }
                    if (storedID.equals(userName) && storedPassword != null && storedPassword.equals(password)) {
                        return true;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean isLoggedIn() {		// return login status
        return loggedIn;
    }

    public String getUserName() {		// return logged-in user's name
        return userName;
    }
}