import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MyCloset extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JList<ImageIcon> clothList;
    private DefaultListModel<ImageIcon> listModel;

    private ArrayList<String> clothesList;
    private Map<String, java.net.URL> imageMap;
    private JLabel topCnt;
    private JLabel bottomCnt;
    private JLabel outerCnt;
    private JLabel shoesCnt;
    private DistributionWorker distributionWorker;
    private JButton allButton;
    private JButton topButton;
    private JButton bottomButton;
    private JButton outerButton;
    private JButton shoesButton;
    private JButton registerButton;
    private JLabel titleLabel;

    public MyCloset(ArrayList<String> clothesList) {
        this.clothesList = clothesList;

        setTitle("My Closet");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 619, 584);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(240, 240, 240));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(12, 83, 569, 398);
        contentPane.add(scrollPane);

        listModel = new DefaultListModel<>();
        clothList = new JList<>(listModel);
        clothList.setFont(new Font("Arial", Font.PLAIN, 14));
        clothList.setLayoutOrientation(JList.HORIZONTAL_WRAP);
        clothList.setVisibleRowCount(-1);
        clothList.setFixedCellWidth(250);
        clothList.setFixedCellHeight(200);
        scrollPane.setViewportView(clothList);

        JLabel topLabel = new JLabel("Tops:");
        topLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        topLabel.setBounds(22, 501, 52, 15);
        contentPane.add(topLabel);

        topCnt = new JLabel("");
        topCnt.setFont(new Font("Arial", Font.PLAIN, 14));
        topCnt.setBounds(64, 501, 27, 15);
        contentPane.add(topCnt);

        JLabel bottomLabel = new JLabel("Bottoms:");
        bottomLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        bottomLabel.setBounds(98, 501, 60, 15);
        contentPane.add(bottomLabel);

        bottomCnt = new JLabel("");
        bottomCnt.setFont(new Font("Arial", Font.PLAIN, 14));
        bottomCnt.setBounds(161, 501, 20, 15);
        contentPane.add(bottomCnt);

        JLabel outerLabel = new JLabel("Outers:");
        outerLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        outerLabel.setBounds(193, 501, 60, 15);
        contentPane.add(outerLabel);

        outerCnt = new JLabel("");
        outerCnt.setFont(new Font("Arial", Font.PLAIN, 14));
        outerCnt.setBounds(240, 501, 20, 15);
        contentPane.add(outerCnt);
        
        JLabel shoesLabel = new JLabel("Shoes:");
        shoesLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        shoesLabel.setBounds(278, 501, 60, 15);
        contentPane.add(shoesLabel);

        shoesCnt = new JLabel("");
        shoesCnt.setFont(new Font("Arial", Font.PLAIN, 14));
        shoesCnt.setBounds(328, 501, 20, 15);
        contentPane.add(shoesCnt);

        JButton combineButton = new JButton("Combine");
        combineButton.setBackground(SystemColor.info);
        combineButton.setFont(new Font("Arial", Font.BOLD, 15));
        combineButton.setBounds(460, 10, 121, 27);
        contentPane.add(combineButton);

        JButton deleteButton = new JButton("Delete");
        deleteButton.setBackground(SystemColor.info);
        deleteButton.setFont(new Font("Arial", Font.BOLD, 15));
        deleteButton.setBounds(363, 497, 107, 27);
        contentPane.add(deleteButton);
        
        allButton = new JButton("All");
        allButton.setFont(new Font("Arial", Font.PLAIN, 14));
        allButton.setBackground(new Color(255, 255, 255));
        allButton.setBounds(12, 61, 87, 23);
        contentPane.add(allButton);
        
        topButton = new JButton("Top");
        topButton.setFont(new Font("Arial", Font.PLAIN, 14));
        topButton.setBackground(new Color(255, 255, 255));
        topButton.setBounds(98, 61, 87, 23);
        contentPane.add(topButton);
        
        bottomButton = new JButton("Bottom");
        bottomButton.setFont(new Font("Arial", Font.PLAIN, 14));
        bottomButton.setBackground(new Color(255, 255, 255));
        bottomButton.setBounds(184, 61, 92, 23);
        contentPane.add(bottomButton);
        
        outerButton = new JButton("Outer");
        outerButton.setFont(new Font("Arial", Font.PLAIN, 14));
        outerButton.setBackground(new Color(255, 255, 255));
        outerButton.setBounds(275, 61, 87, 23);
        contentPane.add(outerButton);
        
        shoesButton = new JButton("Shoes");
        shoesButton.setFont(new Font("Arial", Font.PLAIN, 14));
        shoesButton.setBackground(new Color(255, 255, 255));
        shoesButton.setBounds(361, 61, 87, 23);
        contentPane.add(shoesButton);
        
        registerButton = new JButton("Register");
        registerButton.setBackground(SystemColor.info);
        registerButton.setFont(new Font("Arial", Font.BOLD, 15));
        registerButton.setBounds(477, 497, 104, 27);
        contentPane.add(registerButton);
        
        titleLabel = new JLabel("My Own Closet");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 23));
        titleLabel.setBounds(22, 13, 187, 27);
        contentPane.add(titleLabel);

        initializeImageMap();
        updateClothesList(clothesList);

        distributionWorker = new DistributionWorker();
        distributionWorker.execute();

        combineButton.addActionListener(new ActionListener() {			// create the combine window
            @Override
            public void actionPerformed(ActionEvent e) {
                CombineClothes combineClothesFrame = new CombineClothes(clothesList, imageMap);
                combineClothesFrame.setVisible(true);
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {			
                JDialog deleteDialog = new JDialog(MyCloset.this, "Delete Items", true);		// create delete window
                deleteDialog.setBounds(200, 200, 400, 300);
                deleteDialog.getContentPane().setLayout(new BorderLayout());

                JList<String> deleteList = new JList<>(clothesList.toArray(new String[0]));		// list of clothes
                JScrollPane scrollPane = new JScrollPane(deleteList);
                deleteDialog.getContentPane().add(scrollPane, BorderLayout.CENTER);

                JPanel btnPanel = new JPanel();
                deleteDialog.getContentPane().add(btnPanel, BorderLayout.SOUTH);

                JButton btnDeleteItem = new JButton("Delete Selected Item");
                btnPanel.add(btnDeleteItem);
                btnDeleteItem.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {			// delete Selected Item
                        int selectedIndex = deleteList.getSelectedIndex();
                        if (selectedIndex != -1) {
                            String selectedItem = deleteList.getSelectedValue();
                            clothesList.remove(selectedIndex);
                            updateClothesList(clothesList);
                            deleteList.setListData(clothesList.toArray(new String[0]));
                        } else {
                            JOptionPane.showMessageDialog(deleteDialog, "Please select an item to delete.");
                        }
                    }
                });
                JButton btnClose = new JButton("Close");			// press the close button to close the window
                btnPanel.add(btnClose);
                btnClose.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        deleteDialog.dispose();
                    }
                });
                deleteDialog.setVisible(true);
            }
        });

        
        registerButton.addActionListener(new ActionListener() {			// create registerClothes window
            @Override
            public void actionPerformed(ActionEvent e) {
                RegisterClothes registerClothesFrame = new RegisterClothes(clothesList);
                registerClothesFrame.setVisible(true);
                dispose(); 
            }
        });
    
        allButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateClothesList(clothesList);
            }
        });
        
        topButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateFilteredClothesList("Top");
            }
        });
        
        bottomButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateFilteredClothesList("Bottom");
            }
        });
        
        outerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateFilteredClothesList("Outer");
            }
        });
        
        shoesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateFilteredClothesList("Shoes");
            }
        });
    }

    public void updateClothesList(ArrayList<String> clothesList) {		
        this.clothesList = clothesList;
        listModel.clear();
        for (String item : clothesList) {
        	java.net.URL imageFilePath = imageMap.get(item);
            if (imageFilePath != null) {
                ImageIcon imageIcon = new ImageIcon(imageFilePath);
                Image scaledImage = imageIcon.getImage().getScaledInstance(250, 200, Image.SCALE_SMOOTH);
                ImageIcon scaledIcon = new ImageIcon(scaledImage);
                listModel.addElement(scaledIcon);
            }
        }
        updateDistributionCounts();
    }

    private void updateFilteredClothesList(String category) {			// list of clothes by category
        listModel.clear();
        for (String item : clothesList) {
            if (item.contains("Category: " + category)) {
            	java.net.URL imageFilePath = imageMap.get(item);
                if (imageFilePath != null) {
                    ImageIcon imageIcon = new ImageIcon(imageFilePath);
                    Image scaledImage = imageIcon.getImage().getScaledInstance(250, 200, Image.SCALE_SMOOTH);
                    ImageIcon scaledIcon = new ImageIcon(scaledImage);
                    listModel.addElement(scaledIcon);
                }
            }
        }
    }
    // Add Image File
    private void initializeImageMap() {
    	
        imageMap = new HashMap<>();
        
        java.net.URL short1 = getClass().getClassLoader().getResource("Image/beigeShortT.png");
        imageMap.put("Category: Top, Type: Short T-shirt, Color: Beige", short1);
        java.net.URL short2 = getClass().getClassLoader().getResource("Image/navyShortT.png");
        imageMap.put("Category: Top, Type: Short T-shirt, Color: Navy", short2);
        java.net.URL short3 = getClass().getClassLoader().getResource("Image/blackShortT.png");
        imageMap.put("Category: Top, Type: Short T-shirt, Color: Black", short3);
        java.net.URL short4 = getClass().getClassLoader().getResource("Image/grayShortT.png");
        imageMap.put("Category: Top, Type: Short T-shirt, Color: Gray", short4);
        java.net.URL short5 = getClass().getClassLoader().getResource("Image/whiteShortT.png");
        imageMap.put("Category: Top, Type: Short T-shirt, Color: White", short5);
        java.net.URL short6 = getClass().getClassLoader().getResource("Image/skyblueShortT.png");
        imageMap.put("Category: Top, Type: Short T-shirt, Color: Sky blue", short6);
        
        java.net.URL sweat1 = getClass().getClassLoader().getResource("Image/beigeSweatshirt.png");
        imageMap.put("Category: Top, Type: Sweatshirt, Color: Beige", sweat1);
        java.net.URL sweat2 = getClass().getClassLoader().getResource("Image/navySweatshirt.png");
        imageMap.put("Category: Top, Type: Sweatshirt, Color: Navy", sweat2); 
        java.net.URL sweat3 = getClass().getClassLoader().getResource("Image/blackSweatshirt.png");
        imageMap.put("Category: Top, Type: Sweatshirt, Color: Black", sweat3);
        java.net.URL sweat4 = getClass().getClassLoader().getResource("Image/graySweatshirt.png");
        imageMap.put("Category: Top, Type: Sweatshirt, Color: Gray", sweat4);
        java.net.URL sweat5 = getClass().getClassLoader().getResource("Image/whiteSweatshirt.png");
        imageMap.put("Category: Top, Type: Sweatshirt, Color: White", sweat5);
        java.net.URL sweat6 = getClass().getClassLoader().getResource("Image/skyblueSweatshirt.png");
        imageMap.put("Category: Top, Type: Sweatshirt, Color: Sky blue", sweat6);

        java.net.URL knit1 = getClass().getClassLoader().getResource("Image/beigeKnit.png");
        imageMap.put("Category: Top, Type: Knit, Color: Beige", knit1);
        java.net.URL knit2 = getClass().getClassLoader().getResource("Image/navyKnit.png");
        imageMap.put("Category: Top, Type: Knit, Color: Navy", knit2); 
        java.net.URL knit3 = getClass().getClassLoader().getResource("Image/blackKnit.png");
        imageMap.put("Category: Top, Type: Knit, Color: Black", knit3);
        java.net.URL knit4 = getClass().getClassLoader().getResource("Image/grayKnit.png");
        imageMap.put("Category: Top, Type: Knit, Color: Gray", knit4);
        java.net.URL knit5 = getClass().getClassLoader().getResource("Image/whiteKnit.png");
        imageMap.put("Category: Top, Type: Knit, Color: White", knit5);
        java.net.URL knit6 = getClass().getClassLoader().getResource("Image/skyblueKnit.png");
        imageMap.put("Category: Top, Type: Knit, Color: Sky blue", knit6);
        
        java.net.URL shirt1 = getClass().getClassLoader().getResource("Image/beigeShirt.png");
        imageMap.put("Category: Top, Type: Shirt, Color: Beige", shirt1);
        java.net.URL shirt2 = getClass().getClassLoader().getResource("Image/navyShirt.png");
        imageMap.put("Category: Top, Type: Shirt, Color: Navy", shirt2); 
        java.net.URL shirt3 = getClass().getClassLoader().getResource("Image/blackShirt.png");
        imageMap.put("Category: Top, Type: Shirt, Color: Black", shirt3);
        java.net.URL shirt4 = getClass().getClassLoader().getResource("Image/grayShirt.png");
        imageMap.put("Category: Top, Type: Shirt, Color: Gray", shirt4);
        java.net.URL shirt5 = getClass().getClassLoader().getResource("Image/whiteShirt.png");
        imageMap.put("Category: Top, Type: Shirt, Color: White", shirt5);
        java.net.URL shirt6 = getClass().getClassLoader().getResource("Image/skyblueShirt.png");
        imageMap.put("Category: Top, Type: Shirt, Color: Sky blue", shirt6);
   
        java.net.URL hood1 = getClass().getClassLoader().getResource("Image/beigeHoodie.png");
        imageMap.put("Category: Top, Type: Hoodie, Color: Beige", hood1);
        java.net.URL hood2 = getClass().getClassLoader().getResource("Image/navyHoodie.png");
        imageMap.put("Category: Top, Type: Hoodie, Color: Navy", hood2); 
        java.net.URL hood3 = getClass().getClassLoader().getResource("Image/blackHoodie.png");
        imageMap.put("Category: Top, Type: Hoodie, Color: Black", hood3);
        java.net.URL hood4 = getClass().getClassLoader().getResource("Image/grayHoodie.png");
        imageMap.put("Category: Top, Type: Hoodie, Color: Gray", hood4);
        java.net.URL hood5 = getClass().getClassLoader().getResource("Image/whiteHoodie.png");
        imageMap.put("Category: Top, Type: Hoodie, Color: White", hood5);
        java.net.URL hood6 = getClass().getClassLoader().getResource("Image/skyblueHoodie.png");
        imageMap.put("Category: Top, Type: Hoodie, Color: Sky blue", hood6);

        java.net.URL cargo1 = getClass().getClassLoader().getResource("Image/beigeCargo.png");
        imageMap.put("Category: Bottom, Type: Cargo pants, Color: Beige", cargo1);
        java.net.URL cargo2 = getClass().getClassLoader().getResource("Image/blueCargo.png");
        imageMap.put("Category: Bottom, Type: Cargo pants, Color: Blue", cargo2); 
        java.net.URL cargo3 = getClass().getClassLoader().getResource("Image/skyblueCargo.png");
        imageMap.put("Category: Bottom, Type: Cargo pants, Color: Black", cargo3);
        java.net.URL cargo4 = getClass().getClassLoader().getResource("Image/grayCargo.png");
        imageMap.put("Category: Bottom, Type: Cargo pants, Color: Gray", cargo4);
        java.net.URL cargo5 = getClass().getClassLoader().getResource("Image/blackCargo.png");
        imageMap.put("Category: Bottom, Type: Cargo pants, Color: White", cargo5);
        java.net.URL cargo6 = getClass().getClassLoader().getResource("Image/whiteCargo.png");
        imageMap.put("Category: Bottom, Type: Cargo pants, Color: Sky blue", cargo6);
        
        java.net.URL shorts1 = getClass().getClassLoader().getResource("Image/beigeShorts.png");
        imageMap.put("Category: Bottom, Type: Shorts, Color: Beige", shorts1);
        java.net.URL shorts2 = getClass().getClassLoader().getResource("Image/blueShorts.png");
        imageMap.put("Category: Bottom, Type: Shorts, Color: Blue", shorts2); 
        java.net.URL shorts3 = getClass().getClassLoader().getResource("Image/skyblueShorts.png");
        imageMap.put("Category: Bottom, Type: Shorts, Color: Black", shorts3);
        java.net.URL shorts4 = getClass().getClassLoader().getResource("Image/grayShorts.png");
        imageMap.put("Category: Bottom, Type: Shorts, Color: Gray", shorts4);
        java.net.URL shorts5 = getClass().getClassLoader().getResource("Image/blackShorts.png");
        imageMap.put("Category: Bottom, Type: Shorts, Color: White", shorts5);
        java.net.URL shorts6 = getClass().getClassLoader().getResource("Image/whiteShorts.png");
        imageMap.put("Category: Bottom, Type: Shorts, Color: Sky blue", shorts6);

        java.net.URL slacks1 = getClass().getClassLoader().getResource("Image/beigeSlacks.png");
        imageMap.put("Category: Bottom, Type: Slacks, Color: Beige", slacks1);
        java.net.URL slacks2 = getClass().getClassLoader().getResource("Image/blueSlacks.png");
        imageMap.put("Category: Bottom, Type: Slacks, Color: Blue", slacks2); 
        java.net.URL slacks3 = getClass().getClassLoader().getResource("Image/skyblueSlacks.png");
        imageMap.put("Category: Bottom, Type: Slacks, Color: Black", slacks3);
        java.net.URL slacks4 = getClass().getClassLoader().getResource("Image/graySlacks.png");
        imageMap.put("Category: Bottom, Type: Slacks, Color: Gray", slacks4);
        java.net.URL slacks5 = getClass().getClassLoader().getResource("Image/blackSlacks.png");
        imageMap.put("Category: Bottom, Type: Slacks, Color: White", slacks5);
        java.net.URL slacks6 = getClass().getClassLoader().getResource("Image/whiteSlacks.png");
        imageMap.put("Category: Bottom, Type: Slacks, Color: Sky blue", slacks6);
        
        java.net.URL jeans1 = getClass().getClassLoader().getResource("Image/beigeJeans.png");
        imageMap.put("Category: Bottom, Type: Jeans, Color: Beige", jeans1);
        java.net.URL jeans2 = getClass().getClassLoader().getResource("Image/blueJeans.png");
        imageMap.put("Category: Bottom, Type: Jeans, Color: Blue", jeans2); 
        java.net.URL jeans3 = getClass().getClassLoader().getResource("Image/skyblueJeans.png");
        imageMap.put("Category: Bottom, Type: Jeans, Color: Black", jeans3);
        java.net.URL jeans4 = getClass().getClassLoader().getResource("Image/grayJeans.png");
        imageMap.put("Category: Bottom, Type: Jeans, Color: Gray", jeans4);
        java.net.URL jeans5 = getClass().getClassLoader().getResource("Image/blackJeans.png");
        imageMap.put("Category: Bottom, Type: Jeans, Color: White", jeans5);
        java.net.URL jeans6 = getClass().getClassLoader().getResource("Image/whiteJeans.png");
        imageMap.put("Category: Bottom, Type: Jeans, Color: Sky blue", jeans6);

        java.net.URL jacket1 = getClass().getClassLoader().getResource("Image/beigeJacket.png");
        imageMap.put("Category: Outer, Type: Jacket, Color: Beige", jacket1);
        java.net.URL jacket2 = getClass().getClassLoader().getResource("Image/navyJacket.png");
        imageMap.put("Category: Outer, Type: Jacket, Color: Navy", jacket2); 
        java.net.URL jacket3 = getClass().getClassLoader().getResource("Image/whiteJacket.png");
        imageMap.put("Category: Outer, Type: Jacket, Color: White", jacket3);
        java.net.URL jacket4 = getClass().getClassLoader().getResource("Image/brownJacket.png");
        imageMap.put("Category: Outer, Type: Jacket, Color: Brown", jacket4);
        java.net.URL jacket5 = getClass().getClassLoader().getResource("Image/blackJacket.png");
        imageMap.put("Category: Outer, Type: Jacket, Color: Black", jacket5);

        java.net.URL coat1 = getClass().getClassLoader().getResource("Image/beigeCoat.png");
        imageMap.put("Category: Outer, Type: Coat, Color: Beige", coat1);
        java.net.URL coat2 = getClass().getClassLoader().getResource("Image/navyCoat.png");
        imageMap.put("Category: Outer, Type: Coat, Color: Navy", coat2); 
        java.net.URL coat3 = getClass().getClassLoader().getResource("Image/whiteCoat.png");
        imageMap.put("Category: Outer, Type: Coat, Color: White", coat3);
        java.net.URL coat4 = getClass().getClassLoader().getResource("Image/brownCoat.png");
        imageMap.put("Category: Outer, Type: Coat, Color: Brown", coat4);
        java.net.URL coat5 = getClass().getClassLoader().getResource("Image/blackCoat.png");
        imageMap.put("Category: Outer, Type: Coat, Color: Black", coat5);

        java.net.URL card1 = getClass().getClassLoader().getResource("Image/beigeCardigan.png");
        imageMap.put("Category: Outer, Type: Cardigan, Color: Beige", card1);
        java.net.URL card2 = getClass().getClassLoader().getResource("Image/navyCardigan.png");
        imageMap.put("Category: Outer, Type: Cardigan, Color: Navy", card2); 
        java.net.URL card3 = getClass().getClassLoader().getResource("Image/whiteCardigan.png");
        imageMap.put("Category: Outer, Type: Cardigan, Color: White", card3);
        java.net.URL card4 = getClass().getClassLoader().getResource("Image/brownCardigan.png");
        imageMap.put("Category: Outer, Type: Cardigan, Color: Brown", card4);
        java.net.URL card5 = getClass().getClassLoader().getResource("Image/blackCardigan.png");
        imageMap.put("Category: Outer, Type: Cardigan, Color: Black", card5);

        java.net.URL pad1 = getClass().getClassLoader().getResource("Image/beigePadding.png");
        imageMap.put("Category: Outer, Type: Padding, Color: Beige", pad1);
        java.net.URL pad2 = getClass().getClassLoader().getResource("Image/navyPadding.png");
        imageMap.put("Category: Outer, Type: Padding, Color: Navy", pad2); 
        java.net.URL pad3 = getClass().getClassLoader().getResource("Image/whitePadding.png");
        imageMap.put("Category: Outer, Type: Padding, Color: White", pad3);
        java.net.URL pad4 = getClass().getClassLoader().getResource("Image/brownPadding.png");
        imageMap.put("Category: Outer, Type: Padding, Color: Brown", pad4);
        java.net.URL pad5 = getClass().getClassLoader().getResource("Image/blackPadding.png");
        imageMap.put("Category: Outer, Type: Padding, Color: Black", pad5);

        java.net.URL sne1 = getClass().getClassLoader().getResource("Image/blackSneakers.png");
        imageMap.put("Category: Shoes, Type: Sneakers, Color: Black", sne1);
        java.net.URL sne2 = getClass().getClassLoader().getResource("Image/graySneakers.png");
        imageMap.put("Category: Shoes, Type: Sneakers, Color: Gray", sne2); 
        java.net.URL sne3 = getClass().getClassLoader().getResource("Image/whiteSneakers.png");
        imageMap.put("Category: Shoes, Type: Sneakers, Color: White", sne3);
        java.net.URL sne4 = getClass().getClassLoader().getResource("Image/brownSneakers.png");
        imageMap.put("Category: Shoes, Type: Sneakers, Color: Brown", sne4);
        
        java.net.URL shoes1 = getClass().getClassLoader().getResource("Image/blackShoes.png");
        imageMap.put("Category: Shoes, Type: Shoes, Color: Black", shoes1);
        java.net.URL shoes2 = getClass().getClassLoader().getResource("Image/grayShoes.png");
        imageMap.put("Category: Shoes, Type: Shoes, Color: Gray", shoes2); 
        java.net.URL shoes3 = getClass().getClassLoader().getResource("Image/whiteShoes.png");
        imageMap.put("Category: Shoes, Type: Shoes, Color: White", shoes3);
        java.net.URL shoes4 = getClass().getClassLoader().getResource("Image/brownShoes.png");
        imageMap.put("Category: Shoes, Type: Shoes, Color: Brown", shoes4);

        java.net.URL loaf1 = getClass().getClassLoader().getResource("Image/blackLoafers.png");
        imageMap.put("Category: Shoes, Type: Loafers, Color: Black", loaf1);
        java.net.URL loaf2 = getClass().getClassLoader().getResource("Image/grayLoafers.png");
        imageMap.put("Category: Shoes, Type: Loafers, Color: Gray", loaf2); 
        java.net.URL loaf3 = getClass().getClassLoader().getResource("Image/whiteLoafers.png");
        imageMap.put("Category: Shoes, Type: Loafers, Color: White", loaf3);
        java.net.URL loaf4 = getClass().getClassLoader().getResource("Image/brownLoafers.png");
        imageMap.put("Category: Shoes, Type: Loafers, Color: Brown", loaf4);
        
        
//        imageMap.put("Category: Shoes, Type: Loafers, Color: Black", "Image/blackLoafers.png");
//        imageMap.put("Category: Shoes, Type: Loafers, Color: Gray", "Image/grayLoafers.png");
//        imageMap.put("Category: Shoes, Type: Loafers, Color: White", "Image/whiteLoafers.png");
//        imageMap.put("Category: Shoes, Type: Loafers, Color: Brown", "Image/brownLoafers.png");
    }

    private class DistributionWorker extends SwingWorker<Void, Void> {		// count the number of clothes in each category

        private int topCount;
        private int bottomCount;
        private int outerCount;
        private int shoesCount;

        @Override
        protected Void doInBackground() throws Exception {
            while (true) {
                topCount = 0;
                bottomCount = 0;
                outerCount = 0;
                shoesCount = 0;

                for (String item : clothesList) {
                    if (item.contains("Category: Top")) {
                        topCount++;
                    } else if (item.contains("Category: Bottom")) {
                        bottomCount++;
                    } else if (item.contains("Category: Outer")) {
                        outerCount++;
                    } else if (item.contains("Category: Shoes")) {
                        shoesCount++;
                    }
                }
                SwingUtilities.invokeLater(() -> {
                    topCnt.setText(String.valueOf(topCount));
                    bottomCnt.setText(String.valueOf(bottomCount));
                    outerCnt.setText(String.valueOf(outerCount));
                    shoesCnt.setText(String.valueOf(shoesCount));
                });
                Thread.sleep(100);
            }
        }
    }

    private void updateDistributionCounts() {
        int topCount = 0;
        int bottomCount = 0;
        int outerCount = 0;
        int shoesCount = 0;

        for (String item : clothesList) {
            if (item.contains("Category: Top")) {
                topCount++;
            } else if (item.contains("Category: Bottom")) {
                bottomCount++;
            } else if (item.contains("Category: Outer")) {
                outerCount++;
            } else if (item.contains("Category: Shoes")) {
                shoesCount++;
            }
        }
        topCnt.setText(String.valueOf(topCount));
        bottomCnt.setText(String.valueOf(bottomCount));
        outerCnt.setText(String.valueOf(outerCount));
        shoesCnt.setText(String.valueOf(shoesCount));
    }

    public static void main(String[] args) {
        ArrayList<String> clothesList = new ArrayList<>();
        EventQueue.invokeLater(() -> {
            MyCloset myCloset = new MyCloset(clothesList);
            myCloset.setVisible(true);
        });
    }
}