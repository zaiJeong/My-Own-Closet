import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class MainInterface extends JFrame {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
    private JButton registerButton;
    private JButton lookButton;
    private JButton cancelButton;
    private JButton loginButton;
    private JLabel titleLabel;
    private JLabel subtitleLabel;

    private ArrayList<String> clothesList = new ArrayList<>();
    private String loggedInUser;
    private JLabel backgroundLabel;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                MainInterface frame = new MainInterface();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public MainInterface() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 431, 471);
        setTitle("My Own Closet: StyleHub");

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        titleLabel = new JLabel("My Own Closet");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 25));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setBounds(97, 10, 209, 44);
        contentPane.add(titleLabel);

        subtitleLabel = new JLabel(": StyleHub");
        subtitleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        subtitleLabel.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 22));
        subtitleLabel.setBounds(140, 53, 120, 23);
        contentPane.add(subtitleLabel);

        registerButton = new JButton("Register Clothes");
        registerButton.setBackground(SystemColor.info);
        registerButton.setFont(new Font("Arial", Font.BOLD, 17));
        registerButton.setBounds(97, 226, 209, 44);
        registerButton.setEnabled(false);
        contentPane.add(registerButton);

        lookButton = new JButton("Look My Closet");
        lookButton.setBackground(SystemColor.info);
        lookButton.setFont(new Font("Arial", Font.BOLD, 17));
        lookButton.setBounds(97, 294, 209, 44);
        lookButton.setEnabled(false);
        contentPane.add(lookButton);

        cancelButton = new JButton("Close");
        cancelButton.setBackground(SystemColor.info);
        cancelButton.setFont(new Font("Arial", Font.BOLD, 17));
        cancelButton.setBounds(97, 371, 209, 44);
        contentPane.add(cancelButton);

        loginButton = new JButton("Login");
        loginButton.setBackground(SystemColor.info);
        loginButton.setFont(new Font("Arial", Font.BOLD, 17));
        loginButton.setBounds(97, 158, 209, 44);
        contentPane.add(loginButton);
        
        backgroundLabel = new JLabel("");
        //ImageIcon imageIcon = new ImageIcon("Image/closet.png");
        java.net.URL url = getClass().getClassLoader().getResource("Image/closet.png");
        ImageIcon imageIcon = new ImageIcon(url);
        Image image = imageIcon.getImage(); 
        Image scaledImage = image.getScaledInstance(417, 434, Image.SCALE_SMOOTH); 
        backgroundLabel.setIcon(new ImageIcon(scaledImage)); 
        backgroundLabel.setBounds(0, 0, 417, 434); 
        contentPane.add(backgroundLabel); 

        registerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                RegisterClothes registerFrame = new RegisterClothes(clothesList);
                registerFrame.setVisible(true);
            }
        });

        lookButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                MyCloset myCloset = new MyCloset(clothesList);
                myCloset.setVisible(true);
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showRegisterDialog();
            }
        });

        if (isUserLoggedIn()) {
            loggedInUser = readLoggedInUserFromFile("loggedInUser.txt");
            enableButtons(true);
            loginButton.setEnabled(false);
        }
    }

    private boolean isUserLoggedIn() {			// checking login information
        try {
            File file = new File("loggedInUser.txt");
            return file.exists();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    private void showRegisterDialog() {			// creating a registration window
        RegisterDialog registerDialog = new RegisterDialog();
        registerDialog.setVisible(true);
        registerDialog.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                if (registerDialog.isRegisteredSuccessfully()) {
                    JOptionPane.showMessageDialog(MainInterface.this, "Registration successful. Please log in.", "Registration Success", JOptionPane.INFORMATION_MESSAGE);
                    showLoginDialog(); 
                }
            }
        });
    }

    private void showLoginDialog() {			// creating a Login window
        LoginDialog loginDialog = new LoginDialog();
        loginDialog.setVisible(true);
        loginDialog.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                if (loginDialog.isLoggedIn()) {
                    loggedInUser = loginDialog.getUserName();
                    saveLoggedInUser(loggedInUser);
                    enableButtons(true); 
                    loginButton.setEnabled(false);
                }
            }
        });
    }

    private void saveLoggedInUser(String userName) {
        try (FileWriter writer = new FileWriter("loggedInUser.txt")) {
            writer.write(userName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String readLoggedInUserFromFile(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            return reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void enableButtons(boolean enabled) {
        registerButton.setEnabled(enabled);
        lookButton.setEnabled(enabled);
    }
}