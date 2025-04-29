import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class RegisterClothes extends JFrame {

    private JPanel contentPane;
    private JLabel categoryLabel;
    private JRadioButton topButton;
    private JRadioButton bottomButton;
    private JRadioButton outerButton;
    private JRadioButton shoesButton;
    private JComboBox<String> typeComboBox;
    private JComboBox<String> colorComboBox;
    private JButton registerButton;
    private JButton viewButton;
    private ArrayList<String> clothesList;

    public RegisterClothes(ArrayList<String> clothesList) {
        this.clothesList = clothesList;

        setTitle("Register Clothes");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 497, 403);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        categoryLabel = new JLabel("Category");
        categoryLabel.setFont(new Font("Arial", Font.BOLD, 15));
        categoryLabel.setBounds(66, 62, 85, 18);
        contentPane.add(categoryLabel);

        topButton = new JRadioButton("Top");
        topButton.setBounds(170, 62, 56, 23);
        contentPane.add(topButton);

        bottomButton = new JRadioButton("Bottom");
        bottomButton.setBounds(261, 62, 85, 23);
        contentPane.add(bottomButton);

        outerButton = new JRadioButton("Outer");
        outerButton.setBounds(170, 103, 67, 23);
        contentPane.add(outerButton);
        
        shoesButton = new JRadioButton("Shoes");
        shoesButton.setBounds(261, 103, 67, 23);
        contentPane.add(shoesButton);

        ButtonGroup group = new ButtonGroup();
        group.add(topButton);
        group.add(bottomButton);
        group.add(outerButton);
        group.add(shoesButton);

        typeComboBox = new JComboBox<>();
        typeComboBox.setBounds(159, 169, 214, 25);
        contentPane.add(typeComboBox);

        JLabel typeLabel = new JLabel("Type");
        typeLabel.setFont(new Font("Arial", Font.BOLD, 15));
        typeLabel.setBounds(66, 171, 85, 18);
        contentPane.add(typeLabel);

        JLabel colorLabel = new JLabel("Color");
        colorLabel.setFont(new Font("Arial", Font.BOLD, 15));
        colorLabel.setBounds(66, 246, 85, 18);
        contentPane.add(colorLabel);

        colorComboBox = new JComboBox<>();
        colorComboBox.setBounds(159, 244, 214, 25);
        contentPane.add(colorComboBox);

        registerButton = new JButton("Register");
        registerButton.setBounds(124, 333, 95, 23);
        contentPane.add(registerButton);

        viewButton = new JButton("View Closet");
        viewButton.setBounds(263, 333, 120, 23);
        contentPane.add(viewButton);

        topButton.addActionListener(new ActionListener() {			// add clothes corresponding to top
            public void actionPerformed(ActionEvent e) {
                typeComboBox.removeAllItems();
                typeComboBox.addItem("Short T-shirt");
                typeComboBox.addItem("Sweatshirt");
                typeComboBox.addItem("Knit");
                typeComboBox.addItem("Shirt");
                typeComboBox.addItem("Hoodie");
                setTopColors();
            }
        });

        bottomButton.addActionListener(new ActionListener() {		// add clothes corresponding to bottom
            public void actionPerformed(ActionEvent e) {
                typeComboBox.removeAllItems();
                typeComboBox.addItem("Shorts");
                typeComboBox.addItem("Slacks");
                typeComboBox.addItem("Cargo pants");
                typeComboBox.addItem("Jeans");
                setBottomColors();
            }
        });

        outerButton.addActionListener(new ActionListener() {		// add clothes corresponding to outer
            public void actionPerformed(ActionEvent e) {
                typeComboBox.removeAllItems();
                typeComboBox.addItem("Jacket");
                typeComboBox.addItem("Cardigan");
                typeComboBox.addItem("Coat");
                typeComboBox.addItem("Padding");
                setOuterColors();
            }
        });

        shoesButton.addActionListener(new ActionListener() {		// add clothes corresponding to shoes
            public void actionPerformed(ActionEvent e) {
                typeComboBox.removeAllItems();
                typeComboBox.addItem("Sneakers");
                typeComboBox.addItem("Loafers");
                typeComboBox.addItem("Shoes");
                setShoesColors();
            }
        });

        registerButton.addActionListener(new ActionListener() {			// resister clothes to list
            public void actionPerformed(ActionEvent e) {
                String category = "";
                if (topButton.isSelected()) {
                    category = "Top";
                } else if (bottomButton.isSelected()) {
                    category = "Bottom";
                } else if (outerButton.isSelected()) {
                    category = "Outer";
                } else if (shoesButton.isSelected()) {
                    category = "Shoes";
                } else {						// nothing selected
                    JOptionPane.showMessageDialog(RegisterClothes.this, "Please select a category.", "Error", JOptionPane.ERROR_MESSAGE);
                    return; 
                }
                if (typeComboBox.getSelectedItem() == null || colorComboBox.getSelectedItem() == null) {
                    JOptionPane.showMessageDialog(RegisterClothes.this, "Please select a type and color.", "Error", JOptionPane.ERROR_MESSAGE);
                    return; 
                }
                String type = (String) typeComboBox.getSelectedItem();
                String color = (String) colorComboBox.getSelectedItem();
                String item = String.format("Category: %s, Type: %s, Color: %s", category, type, color);			// add to list
                clothesList.add(item);
            }
        });

        viewButton.addActionListener(new ActionListener() {			// create MyCloset window
            public void actionPerformed(ActionEvent e) {
                dispose();
                MyCloset myCloset = new MyCloset(clothesList);
                myCloset.setVisible(true);
            }
        });
    }

    private void setTopColors() {			// add the specified color for each category to comboBox
        colorComboBox.removeAllItems();
        colorComboBox.addItem("Beige");
        colorComboBox.addItem("Sky blue");
        colorComboBox.addItem("Navy");
        colorComboBox.addItem("Gray");
        colorComboBox.addItem("Black");
        colorComboBox.addItem("White");
    }

    private void setBottomColors() {
        colorComboBox.removeAllItems();
        colorComboBox.addItem("Beige");
        colorComboBox.addItem("Sky blue");
        colorComboBox.addItem("Blue");
        colorComboBox.addItem("Gray");
        colorComboBox.addItem("Black");
        colorComboBox.addItem("White");
    }

    private void setOuterColors() {
        colorComboBox.removeAllItems();
        colorComboBox.addItem("Beige");
        colorComboBox.addItem("Navy");
        colorComboBox.addItem("Brown");
        colorComboBox.addItem("Black");
        colorComboBox.addItem("White");
    }
    
    private void setShoesColors() {
        colorComboBox.removeAllItems();
        colorComboBox.addItem("Brown");
        colorComboBox.addItem("Gray");
        colorComboBox.addItem("Black");
        colorComboBox.addItem("White");
    }
}