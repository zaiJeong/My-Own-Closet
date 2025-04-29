import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CombineClothes extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JLabel titleLabel;
    private JComboBox<String> topComboBox;
    private JComboBox<String> bottomComboBox;
    private JComboBox<String> outerComboBox;
    private JComboBox<String> shoesComboBox;
    private JButton combineButton;
    private JButton resetButton;
    private JButton cancelButton;
    private JPanel clothesPanel;

    private Map<String, java.net.URL> imageMap;
    private ArrayList<String> clothesList;
    private Map<String, String> displayNameToFullNameMap;
    private JLabel topLabel;
    private JLabel bottomLabel;
    private JLabel outerLabel;
    private JLabel shoesLabel;

    public CombineClothes(ArrayList<String> clothesList, Map<String, java.net.URL> imageMap) {
        this.clothesList = clothesList;
        this.imageMap = imageMap;
        this.displayNameToFullNameMap = new HashMap<>();

        setTitle("Combine Clothes");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 800, 600);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        titleLabel = new JLabel("Combining Clothes");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 19));
        titleLabel.setBounds(24, 10, 211, 29);
        contentPane.add(titleLabel);

        topComboBox = new JComboBox<>();
        bottomComboBox = new JComboBox<>();
        outerComboBox = new JComboBox<>();
        shoesComboBox = new JComboBox<>();

        for (String item : clothesList) {			// add a list of clothes to the combo box
            String displayName = parseItemName(item);
            displayNameToFullNameMap.put(displayName, item);

            if (item.contains("Category: Top")) {
                topComboBox.addItem(displayName);
            } else if (item.contains("Category: Bottom")) {
                bottomComboBox.addItem(displayName);
            } else if (item.contains("Category: Outer")) {
                outerComboBox.addItem(displayName);
            } else if (item.contains("Category: Shoes")) {
                shoesComboBox.addItem(displayName);
            }
        }
        topComboBox.setBounds(12, 99, 300, 21);
        contentPane.add(topComboBox);

        bottomComboBox.setBounds(12, 200, 300, 21);
        contentPane.add(bottomComboBox);

        outerComboBox.setBounds(12, 300, 300, 21);
        contentPane.add(outerComboBox);

        shoesComboBox.setBounds(12, 400, 300, 21);
        contentPane.add(shoesComboBox);

        combineButton = new JButton("Combine");
        combineButton.setFont(new Font("Arial", Font.PLAIN, 15));
        combineButton.setBounds(12, 512, 109, 23);
        contentPane.add(combineButton);

        resetButton = new JButton("Reset");
        resetButton.setFont(new Font("Arial", Font.PLAIN, 14));
        resetButton.setBounds(133, 512, 81, 23);
        contentPane.add(resetButton);

        cancelButton = new JButton("Cancel");
        cancelButton.setFont(new Font("Arial", Font.PLAIN, 14));
        cancelButton.setBounds(226, 512, 86, 23);
        contentPane.add(cancelButton);

        clothesPanel = new JPanel(new GridBagLayout());
        clothesPanel.setBackground(new Color(255, 255, 255));
        clothesPanel.setBounds(340, 55, 434, 440);
        contentPane.add(clothesPanel);

        topLabel = new JLabel("Top");
        topLabel.setFont(new Font("Arial", Font.BOLD, 15));
        topLabel.setBounds(12, 74, 41, 15);
        contentPane.add(topLabel);

        bottomLabel = new JLabel("Bottom");
        bottomLabel.setFont(new Font("Arial", Font.BOLD, 15));
        bottomLabel.setBounds(12, 175, 81, 15);
        contentPane.add(bottomLabel);

        outerLabel = new JLabel("Outer");
        outerLabel.setFont(new Font("Arial", Font.BOLD, 15));
        outerLabel.setBounds(12, 278, 81, 15);
        contentPane.add(outerLabel);

        shoesLabel = new JLabel("Shoes");
        shoesLabel.setFont(new Font("Arial", Font.BOLD, 15));
        shoesLabel.setBounds(12, 375, 81, 15);
        contentPane.add(shoesLabel);

        combineButton.addActionListener(new ActionListener() {			
            @Override
            public void actionPerformed(ActionEvent e) {
                combineClothes();
            }
        });

        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetFields();
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

    private String parseItemName(String item) {				// modify the name of the clothes shown
        String[] parts = item.split(",");
        String type = parts[1].split(":")[1].trim();
        String color = parts[2].split(":")[1].trim();
        return type + " (" + color + ")";
    }

    private void combineClothes() {					// combine clothes
        clothesPanel.removeAll();

        String selectedTop = displayNameToFullNameMap.get((String) topComboBox.getSelectedItem());
        String selectedBottom = displayNameToFullNameMap.get((String) bottomComboBox.getSelectedItem());
        String selectedOuter = displayNameToFullNameMap.get((String) outerComboBox.getSelectedItem());
        String selectedShoes = displayNameToFullNameMap.get((String) shoesComboBox.getSelectedItem());

        java.net.URL topImageFilePath = imageMap.get(selectedTop);
        java.net.URL bottomImageFilePath = imageMap.get(selectedBottom);
        java.net.URL outerImageFilePath = imageMap.get(selectedOuter);
        java.net.URL shoesImageFilePath = imageMap.get(selectedShoes);

        ImageIcon topIcon = new ImageIcon(topImageFilePath);
        ImageIcon bottomIcon = new ImageIcon(bottomImageFilePath);
        ImageIcon outerIcon = new ImageIcon(outerImageFilePath);
        ImageIcon shoesIcon = new ImageIcon(shoesImageFilePath);

        Image scaledTopImage = topIcon.getImage().getScaledInstance(180, 180, Image.SCALE_SMOOTH);
        Image scaledBottomImage = bottomIcon.getImage().getScaledInstance(180, 180, Image.SCALE_SMOOTH);
        Image scaledOuterImage = outerIcon.getImage().getScaledInstance(250, 180, Image.SCALE_SMOOTH);
        Image scaledShoesImage = shoesIcon.getImage().getScaledInstance(180, 100, Image.SCALE_SMOOTH);

        ImageIcon scaledTopIcon = new ImageIcon(scaledTopImage);
        ImageIcon scaledBottomIcon = new ImageIcon(scaledBottomImage);
        ImageIcon scaledOuterIcon = new ImageIcon(scaledOuterImage);
        ImageIcon scaledShoesIcon = new ImageIcon(scaledShoesImage);

        JLabel topLabel = new JLabel(scaledTopIcon);
        JLabel bottomLabel = new JLabel(scaledBottomIcon);
        JLabel outerLabel = new JLabel(scaledOuterIcon);
        JLabel shoesLabel = new JLabel(scaledShoesIcon);

        GridBagConstraints gbc = new GridBagConstraints();		// Gray layout to print each garment by location
        gbc.insets = new Insets(10, 10, 10, 10);

        gbc.gridx = 0;
        gbc.gridy = 0;
        clothesPanel.add(topLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        clothesPanel.add(outerLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        clothesPanel.add(bottomLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        clothesPanel.add(shoesLabel, gbc);

        clothesPanel.revalidate();
        clothesPanel.repaint();
    }

    private void resetFields() {			// clear clothing combine
        boolean hasItems = false;

        if (topComboBox.getItemCount() > 0) {			// case of no items
            topComboBox.setSelectedIndex(0);
            hasItems = true;
        }
        if (bottomComboBox.getItemCount() > 0) {
            bottomComboBox.setSelectedIndex(0);
            hasItems = true;
        }
        if (outerComboBox.getItemCount() > 0) {
            outerComboBox.setSelectedIndex(0);
            hasItems = true;
        }
        if (shoesComboBox.getItemCount() > 0) {
            shoesComboBox.setSelectedIndex(0);
            hasItems = true;
        }
        if (!hasItems) {
            JOptionPane.showMessageDialog(this, "No items to reset.", "Warning", JOptionPane.ERROR_MESSAGE);
        }
        clothesPanel.removeAll();
        clothesPanel.revalidate();
        clothesPanel.repaint();
    }
}