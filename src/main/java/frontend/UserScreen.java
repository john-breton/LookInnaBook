package frontend;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserScreen extends JFrame implements ActionListener {

    public UserScreen() {
        JTextField userSearchTF = new JTextField();
        JComboBox<String> searchFilters = new JComboBox<>(FrontEndUtilities.searchFilterArr);
        JComboBox<String> resultFilters = new JComboBox<>(FrontEndUtilities.resultFilterArr);
        JLabel totalPrice = new JLabel("$0.00", JLabel.CENTER);
        Container c = this.getContentPane();
        this.setPreferredSize(new Dimension(798, 850));
        if (this.getJMenuBar() != null) this.getJMenuBar().setVisible(false);
        c.removeAll();

        // Dimensions
        Dimension addRemoveButtonDimensions = new Dimension(25, 25);
        Dimension searchResultDimension = new Dimension(500, c.getHeight());
        Dimension cartDimension = new Dimension(c.getWidth() - (int) searchResultDimension.getWidth(), c.getHeight());

        /* Components */
        // Panels and Panes
        JSplitPane userView = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, true);
        JPanel searchAndResults = new JPanel();
        JPanel cartPanel = new JPanel();
        JPanel pricePanel = new JPanel();
        JPanel checkoutPanel = new JPanel();

        // Labels
        JLabel searchLabel = new JLabel("Search: ");
        JLabel cartLabel = new JLabel("Cart: ");
        JLabel filterLabel = new JLabel("Sort by: ");
        JLabel totalPriceLabel = new JLabel("Total Price: ");

        // Buttons
        JButton addToCart = new JButton("+");
        JButton removeFromCart = new JButton("-");
        JButton checkoutButton = new JButton("Checkout");
        JButton searchButton = new JButton("Search");
        JButton logoutButton = new JButton("Logout");
        addToCart.setBackground(Color.WHITE);
        removeFromCart.setBackground(Color.WHITE);
        checkoutButton.setBackground(Color.WHITE);
        searchButton.setBackground(Color.WHITE);
        logoutButton.setBackground(Color.WHITE);

        // ActionListeners
        addToCart.addActionListener(this);
        removeFromCart.addActionListener(this);
        checkoutButton.addActionListener(this);
        searchButton.addActionListener(this);
        logoutButton.addActionListener(this);

        // ScrollPanes
        JScrollPane currentCart = new JScrollPane();
        JScrollPane searchResult = new JScrollPane();

        /* Setup Panels */
        // Price panel
        addToCart.setPreferredSize(addRemoveButtonDimensions);
        pricePanel.add(addToCart);
        pricePanel.add(totalPriceLabel);
        pricePanel.add(totalPrice);
        removeFromCart.setPreferredSize(addRemoveButtonDimensions);
        pricePanel.add(removeFromCart);

        // Checkout Panel
        checkoutPanel.setLayout(new BoxLayout(checkoutPanel, BoxLayout.PAGE_AXIS));
        checkoutPanel.add(pricePanel);
        checkoutButton.setAlignmentX(JPanel.CENTER_ALIGNMENT);
        checkoutPanel.add(checkoutButton);

        searchAndResults.setLayout(new GridBagLayout());
        GridBagConstraints con = new GridBagConstraints();

        // setup left side of window
        Insets leftEdge = new Insets(5, 5, 5, 0);
        Insets everythingElse = new Insets(5, 0, 5, 0);

        con.insets = leftEdge;
        con.anchor = GridBagConstraints.LINE_START;
        con.weighty = 0.0;
        con.weightx = 0.0;
        con.gridx = 0;
        con.gridy = 0;
        searchAndResults.add(logoutButton, con);

        con.gridx = 0;
        con.gridy = 1;
        searchLabel.setAlignmentX(JPanel.LEFT_ALIGNMENT);
        searchAndResults.add(searchLabel, con);

        con.insets = everythingElse;
        con.gridx = 1;
        searchAndResults.add(searchFilters, con);

        con.fill = GridBagConstraints.HORIZONTAL;
        con.gridx = 2;
        userSearchTF.setPreferredSize(new Dimension(200, 20));
        searchAndResults.add(userSearchTF, con);

        con.fill = GridBagConstraints.NONE;
        con.gridx = 3;
        searchAndResults.add(searchButton, con);

        con.gridx = 0;
        con.gridy = 2;
        con.insets = leftEdge;
        con.fill = GridBagConstraints.NONE;
        filterLabel.setAlignmentX(JPanel.LEFT_ALIGNMENT);
        searchAndResults.add(filterLabel, con);

        con.gridx = 1;
        con.insets = everythingElse;
        searchAndResults.add(resultFilters, con);

        con.gridx = 0;
        con.gridy = 3;
        con.weighty = 1.0;
        con.weightx = 1.0;
        con.gridwidth = 4;
        con.insets = new Insets(5, 5, 5, 5);
        con.fill = GridBagConstraints.BOTH;
        con.anchor = GridBagConstraints.CENTER;
        searchAndResults.add(searchResult, con);

        searchAndResults.setMinimumSize(searchResultDimension);

        // setup right side of window
        cartPanel.setLayout(new BorderLayout());
        cartLabel.setAlignmentX(JPanel.LEFT_ALIGNMENT);
        cartPanel.add(cartLabel, BorderLayout.PAGE_START);
        cartPanel.add(checkoutPanel, BorderLayout.PAGE_END);
        cartPanel.add(currentCart, BorderLayout.CENTER);
        cartPanel.setPreferredSize(cartDimension);

        userView.resetToPreferredSizes();
        userView.setLeftComponent(searchAndResults);
        userView.setRightComponent(cartPanel);


        c.add(userView);
        this.pack();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("LookInnaBook");
        this.setIconImage(FrontEndUtilities.WINDOW_ICON.getImage());
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    /**
     * Implements ActionListeners for GUI components
     *
     * @param e The ActionEvent that was triggered via a JButton.
     */
    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();

        switch (((JButton) o).getText()) {
            case "Logout" -> {
                FrontEndUtilities.confirmLogout(); // Anywhere and everywhere
            }
            case "+" -> System.out.println("Item Added"); // User screen
            case "-" -> System.out.println("Item removed"); // User screen
            case "Checkout" -> new CheckoutScreen(); // User Screen
            case "Search" -> System.out.println("Searching Books"); // User screen
            default -> System.out.println("Error");
        }
    }
}