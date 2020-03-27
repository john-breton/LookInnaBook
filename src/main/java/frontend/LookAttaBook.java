package frontend;
/*
 * Copyright © 3.2020. Ryan Godfrey, John Breton.
 * All rights reserved.
 */

import backend.LookForaBook;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.ColorUIResource;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;
import java.util.ArrayList;

/**
 * The frontend.LookAttaBook class represents the front-end for the util.LookInnaBook application.
 * Users and admins interact with a GUI to register and login into their respective
 * application screens.
 *
 * @author Ryan Godfrey, John Breton
 * @version 1.0
 */
public class LookAttaBook implements ActionListener, ChangeListener {
    final JFrame f = new JFrame("util.LookInnaBook");
    final Container c = f.getContentPane();

    /* JComboBoxes */
    // User
    final JComboBox<String> resultFilters = new JComboBox<>(FrontEndUtilities.resultFilterArr),
            searchFilters = new JComboBox<>(FrontEndUtilities.searchFilterArr),
            reportChoiceBox = new JComboBox<>(FrontEndUtilities.reportChoiceArr),
            shipProvinceCB = new JComboBox<>(FrontEndUtilities.provincesArr),
            billProvinceCB = new JComboBox<>(FrontEndUtilities.provincesArr),
            pubProvinceCB = new JComboBox<>(FrontEndUtilities.provincesArr),
            shippingAdminProvinceCB = new JComboBox<>(FrontEndUtilities.provincesArr),
            billAdminProvinceCB = new JComboBox<>(FrontEndUtilities.provincesArr),
            editShippingProvinceCB = new JComboBox<>(FrontEndUtilities.provincesArr),
            editBillProvinceCB = new JComboBox<>(FrontEndUtilities.provincesArr),
            checkoutShippingProvinceCB = new JComboBox<>(FrontEndUtilities.provincesArr),
            checkoutBillingProvinceCB = new JComboBox<>(FrontEndUtilities.provincesArr);

    /* JCheckBoxes */
    final JCheckBox editBillingSameAsShipping = new JCheckBox("Billing Address is the same as Shipping Address");
    final JCheckBox isUserAdminCB = new JCheckBox("Is the user an admin?");

    /* JLabels -- Global to update users */
    // Login
    private final JLabel loginSuccess = new JLabel("", JLabel.CENTER);
    // Registration
    private final JLabel confirmRegistration = new JLabel("", JLabel.CENTER);
    // Admin
    private final JLabel confirmNewBookAddition = new JLabel("", JLabel.CENTER),
            confirmNewPublisherAddition = new JLabel("", JLabel.CENTER),
            confirmAdminReg = new JLabel("", JLabel.CENTER),
            addUserErrorLabel = new JLabel("", JLabel.CENTER),
            addBookErrorLabel = new JLabel("", JLabel.CENTER),
            addPublisherErrorLabel = new JLabel("", JLabel.CENTER),
            editUserErrorLabel = new JLabel("", JLabel.CENTER),
            currentUserNameLabel = new JLabel(""),
            confirmUserEditLabel = new JLabel("", JLabel.CENTER),
            editBookErrorLabel = new JLabel("", JLabel.CENTER),
            currentISBNLabel = new JLabel("Current ISBN Goes Here"),
            confirmBookEditLabel = new JLabel("", JLabel.CENTER);
    // User
    private final JLabel totalPrice = new JLabel("$0.00", JLabel.CENTER);
    // Checkout
    private final JLabel checkoutTotalPriceValueLabel = new JLabel("$0.00", JLabel.CENTER),
            checkoutUsernameLabel = new JLabel(""),
            checkoutNameField = new JLabel(""),
            checkoutEmailConfirm = new JLabel(""),
            checkoutSuccessLabel = new JLabel("", JLabel.CENTER),
            checkoutErrorLabel = new JLabel("", JLabel.CENTER);
    // Order Lookup
    private final JLabel trackingNumber = new JLabel("123456789101112"),
            dateOrderPlaced = new JLabel("March 25, 2020"),
            userNameOrder = new JLabel("User's Username"),
            orderStatus = new JLabel("TBH Dont know where it is");

    /* JTextFields -- Global in order for methods to access */
    // Login
    private final JTextField usernameField = new JTextField(15);
    private final JPasswordField passwordField = new JPasswordField(15);
    // Registration
    private final JTextField billStreetNumTF = new JTextField(), shipStreetNumTF = new JTextField(),
            billStreetNameTF = new JTextField(), shipStreetNameTF = new JTextField(),
            billApartmentTF = new JTextField(10), shipApartmentTF = new JTextField(),
            billCityTF = new JTextField(), shipCityTF = new JTextField(),
            billCountryTF = new JTextField(), shipCountryTF = new JTextField(),
            billPostalCodeTF = new JTextField(), shipPostalCodeTF = new JTextField(),
            newUsernameTF = new JTextField(),
            firstNameTF = new JTextField(),
            lastNameTF = new JTextField(),
            emailTF = new JTextField();
    private final JPasswordField newPasswordTF = new JPasswordField(),
            confirmPasswordTF = new JPasswordField();
    private final JCheckBox billingSameAsShipping = new JCheckBox("Billing Address is the same as Shipping Address");
    // AdminScreen
    // addBook
    private final JTextField newISBNTF = new JTextField(15),
            newBookTitleTF = new JTextField(15),
            newBookVersionTF = new JTextField(15),
            newBookGenreTF = new JTextField(15),
            newBookNumPagesTF = new JTextField(15),
            newBookPriceTF = new JTextField(15),
            newBookRoyaltyTF = new JTextField(15),
            newBookStockTF = new JTextField("0", 15),
            newBookAuthorFNTF = new JTextField(15),
            newBookAuthorLNTF = new JTextField(15),
            newBookPublisherTF = new JTextField(15),
            newBookYearTF = new JTextField(15);
    // addPublisher
    private final JTextField newPublisherNameTF = new JTextField(15),
            newPublisherStreetNumTF = new JTextField(5),
            newPublisherStreetNameTF = new JTextField(5),
            newPublisherApartmentTF = new JTextField(5),
            newPublisherCityTF = new JTextField(15),
            newPublisherCountryTF = new JTextField(15),
            newPublisherPostalCodeTF = new JTextField(15),
            newPublisherEmailTF = new JTextField(15),
            newPublisherPhoneTF = new JTextField(15),
            newPublisherBankAccountTF = new JTextField(15);
    // addUser
    // Admin info
    private final JTextField newAdminUsernameTF = new JTextField(15),
            firstNameAdminTF = new JTextField(15),
            lastNameAdminTF = new JTextField(15),
            emailAdminTF = new JTextField(15),
            salaryAdminTF = new JTextField(15);
    private final JPasswordField newAdminPasswordTF = new JPasswordField(15),
            confirmAdminPasswordTF = new JPasswordField(15);
    // Admin shipping address info (can be EMPTY)
    private final JTextField shippingAdminStreetNumTF = new JTextField(15),
            shippingAdminStreetNameTF = new JTextField(15),
            shippingAdminApartmentTF = new JTextField(15),
            shippingAdminCityTF = new JTextField(15),
            shippingAdminCountryTF = new JTextField(15),
            shippingAdminPostalCodeTF = new JTextField(15),
    // Admin billing address info
    billAdminStreetNumTF = new JTextField(15),
            billAdminStreetNameTF = new JTextField(15),
            billAdminApartmentTF = new JTextField(15),
            billAdminCityTF = new JTextField(15),
            billAdminCountryTF = new JTextField(15),
            billAdminPostalCodeTF = new JTextField(15);
    //editBook
    private final JTextField editBookSearchTF = new JTextField(15),
            editBookTitleTF = new JTextField(15),
            editBookVersionTF = new JTextField(15),
            editBookGenreTF = new JTextField(15),
            editBookNumPagesTF = new JTextField(15),
            editBookPriceTF = new JTextField(15),
            editBookRoyaltyTF = new JTextField(15),
            editBookStockTF = new JTextField("0", 15),
            editBookAuthorFNTF = new JTextField(15),
            editBookAuthorLNTF = new JTextField(15),
            editBookPublisherTF = new JTextField(15),
            editBookYearTF = new JTextField(15);
    // editUser
    private final JTextField editFirstNameTF = new JTextField(15),
            editLastNameTF = new JTextField(15),
            editEmailTF = new JTextField(15),
            editSalaryTF = new JTextField(15),
            editUserSearchTF = new JTextField(15);
    private final JPasswordField editPasswordTF = new JPasswordField(15),
            confirmEditPasswordTF = new JPasswordField(15);
    // Admin shipping address info (can be EMPTY)
    private final JTextField editShippingStreetNumTF = new JTextField(15),
            editShippingStreetNameTF = new JTextField(15),
            editShippingApartmentTF = new JTextField(15),
            editShippingCityTF = new JTextField(15),
            editShippingCountryTF = new JTextField(15),
            editShippingPostalCodeTF = new JTextField(15),
    // Admin billing address info
    editBillStreetNumTF = new JTextField(15),
            editBillStreetNameTF = new JTextField(15),
            editBillApartmentTF = new JTextField(15),
            editBillCityTF = new JTextField(15),
            editBillCountryTF = new JTextField(15),
            editBillPostalCodeTF = new JTextField(15);
    // User
    private final JTextField userSearchTF = new JTextField();
    // Checkout
    private final JTextField checkoutStreetNumTF = new JTextField(5), // shipping info
            checkoutStreetNameTF = new JTextField(15),
            checkoutApartmentTF = new JTextField(5),
            checkoutCityTF = new JTextField(15),
            checkoutCountryTF = new JTextField(15),
            checkoutPostalCodeTF = new JTextField(15),
    //  checkout Billing info
    checkoutCreditCardNumTF = new JTextField(15),
            checkoutCreditCardExpTF = new JTextField(5),
            checkoutCreditCardCVVTF = new JTextField(5),
            checkoutBillingStreetNumTF = new JTextField(5),
            checkoutBillingStreetNameTF = new JTextField(15),
            checkoutBillingApartmentTF = new JTextField(5),
            checkoutBillingCityTF = new JTextField(15),
            checkoutBillingCountryTF = new JTextField(15),
            checkoutBillingPostalCodeTF = new JTextField(15);
    // Order Lookup
    private final JTextField orderNumber = new JTextField();

    public LookAttaBook() {
        // If you want to disable dark mode, this is the code for it. You can just remove it.
        // I haven't decided if it even looks good yet.
        // I decided I don't like it.
        /*try {
            UIManager.setLookAndFeel("com.jtattoo.plaf.noire.NoireLookAndFeel");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException
                | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }*/

        super(); // create Connection

        f.setIconImage(FrontEndUtilities.WINDOW_ICON.getImage());
        UIManager.getLookAndFeelDefaults().put("Button.focus", new ColorUIResource(new Color(100, 0, 0, 0)));
        loginScreen();
    }

    /**
     * Displays a screen for users to login. Minimalistic by design.
     */
    private void loginScreen() {
        // Clear GUI to load new contents
        f.setPreferredSize(new Dimension(300, 300));
        if (f.getJMenuBar() != null) f.getJMenuBar().setVisible(false);
        c.removeAll();
        usernameField.setText("");
        passwordField.setText("");
        loginSuccess.setText("");

        c.setLayout(new BoxLayout(c, BoxLayout.PAGE_AXIS));

        /* Component declarations */
        // JButtons
        JButton loginButton = new JButton("Login");
        JButton registerButton = new JButton("Register");
        JButton lookupOrderButton = new JButton("Order Lookup");

        // JLabels
        JLabel usernameLabel = new JLabel("Username: ");
        JLabel passwordLabel = new JLabel("Password: ");

        // JPanels
        JPanel mainLoginPage = new JPanel();
        JPanel usernamePanel = new JPanel();
        JPanel passwordPanel = new JPanel();
        JPanel loginPanel = new JPanel();


        /* Component setup */
        // JButton formatting
        loginButton.setBackground(Color.WHITE);
        registerButton.setBackground(Color.WHITE);
        lookupOrderButton.setBackground(Color.WHITE);

        // Login Page
        mainLoginPage.setLayout(new BoxLayout(mainLoginPage, BoxLayout.PAGE_AXIS));

        // Username panel
        usernamePanel.setMaximumSize(new Dimension(300, 20));
        usernamePanel.add(usernameLabel);
        usernamePanel.add(usernameField);

        // Password panel
        passwordPanel.setMaximumSize(new Dimension(300, 20));
        passwordPanel.add(passwordLabel);
        passwordPanel.add(passwordField);

        // Login options
        loginPanel.setMinimumSize(new Dimension(300, 20));
        loginPanel.add(loginSuccess);
        loginPanel.add(registerButton);
        loginPanel.add(loginButton);
        loginPanel.add(lookupOrderButton);

        // Setup ActionListeners
        loginButton.addActionListener(this);
        registerButton.addActionListener(this);
        lookupOrderButton.addActionListener(this);

        // Add to page
        mainLoginPage.add(Box.createVerticalGlue());
        mainLoginPage.add(usernamePanel);
        mainLoginPage.add(passwordPanel);
        mainLoginPage.add(loginPanel);

        c.add(mainLoginPage);

        f.pack();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.getRootPane().setDefaultButton(loginButton);
        f.setLocationRelativeTo(null);
        f.setVisible(true);
        f.setResizable(false);
    }

    /**
     * Display the screen for registration of a new customer.
     */
    private void regScreen() {
        // Clear GUI in order to reload
        f.setPreferredSize(new Dimension(700, 550));
        if (f.getJMenuBar() != null) f.getJMenuBar().setVisible(false);
        c.removeAll();

        /* Component declarations */
        // JTextFields
        // Fields

        // JLabels
        // User info
        JLabel newUsernameLabel = new JLabel("*Username: ");
        JLabel newPasswordLabel = new JLabel("*Password: ");
        JLabel confirmPasswordLabel = new JLabel("*Confirm Password: ", JLabel.RIGHT);
        JLabel firstNameLabel = new JLabel("*First Name: ");
        JLabel lastNameLabel = new JLabel("*Last Name: ", JLabel.RIGHT);
        JLabel emailLabel = new JLabel("*Email: ");
        // User shipping address info
        JLabel shippingAddressLabel = new JLabel("Shipping Address");
        JLabel shippingStreetNumLabel = new JLabel("*Street Number: ");
        JLabel shippingStreetNameLabel = new JLabel("*Street Name: ", JLabel.RIGHT);
        JLabel shippingApartmentLabel = new JLabel("Apartment: ", JLabel.RIGHT);
        JLabel shippingCityLabel = new JLabel("*City: ");
        JLabel shippingProvinceLabel = new JLabel("*Province: ", JLabel.RIGHT);
        JLabel shippingCountryLabel = new JLabel("*Country: ");
        JLabel shippingPostalCodeLabel = new JLabel("*Postal Code: ", JLabel.RIGHT);
        // User billing address info
        JLabel billingAddressLabel = new JLabel("Billing Address");
        JLabel billStreetNumLabel = new JLabel("Street Number: ");
        JLabel billStreetNameLabel = new JLabel("Street Name: ", JLabel.RIGHT);
        JLabel billApartmentLabel = new JLabel("Apartment: ", JLabel.RIGHT);
        JLabel billCityLabel = new JLabel("City: ");
        JLabel billProvinceLabel = new JLabel("Province: ", JLabel.RIGHT);
        JLabel billCountryLabel = new JLabel("Country: ");
        JLabel billPostalCodeLabel = new JLabel("Postal Code: ", JLabel.RIGHT);
        // Welcome message
        JLabel newUserWelcome1 = new JLabel("Welcome to util.LookInnaBook!", JLabel.CENTER);
        JLabel newUserWelcome2 = new JLabel("Enter your information in the space provided below.", JLabel.CENTER);
        JLabel newUserWelcome3 = new JLabel("Required fields are indicated with a \"*\".", JLabel.CENTER);

        // JPanels
        JPanel regPage = new JPanel(new GridBagLayout());
        regPage.setBorder(new EmptyBorder(15,15,15,15));
        JPanel buttons = new JPanel();
        buttons.setAlignmentX(JPanel.CENTER_ALIGNMENT);

        // JButton
        JButton cancelReg = new JButton("Cancel Registration");
        JButton submitReg = new JButton("Submit");
        buttons.add(cancelReg);
        buttons.add(submitReg);


        /* Component setup*/
        // JButton formatting
        cancelReg.setBackground(Color.WHITE);
        submitReg.setBackground(Color.WHITE);

        // Add action listeners
        cancelReg.addActionListener(this);
        submitReg.addActionListener(this);
        billingSameAsShipping.addChangeListener(e -> {
            boolean sameAsBilling = !billingSameAsShipping.isSelected();
            billStreetNumTF.setEnabled(sameAsBilling);
            billStreetNameTF.setEnabled(sameAsBilling);
            billApartmentTF.setEnabled(sameAsBilling);
            billCityTF.setEnabled(sameAsBilling);
            billProvinceCB.setEnabled(sameAsBilling);
            billCountryTF.setEnabled(sameAsBilling);
            billPostalCodeTF.setEnabled(sameAsBilling);
        });

        // Setup Registration Panel
        {
            GridBagConstraints con = new GridBagConstraints();
            Dimension spacer = new Dimension(35, 35);
            con.gridy = 0;
            con.gridx = 0;
            con.gridwidth = 6;
            con.fill = GridBagConstraints.HORIZONTAL;
            con.anchor = GridBagConstraints.CENTER;
            regPage.add(newUserWelcome1, con);

            con.gridy = 1;
            regPage.add(newUserWelcome2, con);

            con.gridy = 2;
            regPage.add(newUserWelcome3, con);

            con.gridy = 3;
            con.gridx = 0;
            con.weightx = 1.0;
            con.gridwidth = 2;
            con.anchor = GridBagConstraints.LINE_START;
            regPage.add(new JLabel("Login Credentials"), con);

            con.gridy = 4;
            con.gridx = 0;
            con.gridwidth = 1;
            regPage.add(newUsernameLabel, con);
            con.gridx = 1;
            con.gridwidth = 2;
            regPage.add(newUsernameTF, con);

            con.gridy = 5;
            con.gridx = 0;
            con.gridwidth = 1;
            regPage.add(newPasswordLabel, con);
            con.gridx = 3;
            regPage.add(confirmPasswordLabel, con);
            con.gridx = 1;
            con.gridwidth = 2;
            regPage.add(newPasswordTF, con);
            con.gridx = 4;
            regPage.add(confirmPasswordTF, con);

            con.gridy = 6;
            con.gridx = 0;
            con.gridwidth = 1;
            regPage.add(firstNameLabel, con);
            con.gridx = 3;
            regPage.add(lastNameLabel, con);
            con.gridx = 1;
            con.gridwidth = 2;
            regPage.add(firstNameTF, con);
            con.gridx = 4;
            regPage.add(lastNameTF, con);

            con.gridy = 7;
            con.gridx = 0;
            con.gridwidth = 1;
            regPage.add(emailLabel, con);
            con.gridx = 1;
            con.gridwidth = 2;
            regPage.add(emailTF, con);

            con.gridy = 8;
            con.gridx = 0;
            con.gridwidth = 1;
            regPage.add(Box.createRigidArea(spacer), con);

            con.gridy = 9;
            con.gridx = 0;
            con.gridwidth = 3;
            regPage.add(shippingAddressLabel, con);

            con.gridy = 10;
            con.gridx = 0;
            con.gridwidth = 1;
            regPage.add(shippingStreetNumLabel, con);
            con.gridx = 1;
            regPage.add(shipStreetNumTF, con);
            con.gridx = 2;
            regPage.add(shippingStreetNameLabel, con);
            con.gridx = 3;
            regPage.add(shipStreetNameTF, con);
            con.gridx = 4;
            regPage.add(shippingApartmentLabel, con);
            con.gridx = 5;
            regPage.add(shipApartmentTF, con);

            con.gridy = 11;
            con.gridx = 0;
            con.gridwidth = 1;
            regPage.add(shippingCityLabel, con);
            con.gridx = 3;
            regPage.add(shippingProvinceLabel, con);
            con.gridx = 1;
            con.gridwidth = 2;
            regPage.add(shipCityTF, con);
            con.gridx = 4;
            con.gridwidth = 1;
            regPage.add(shipProvinceCB, con);

            con.gridy = 12;
            con.gridx = 0;
            con.gridwidth = 1;
            regPage.add(shippingCountryLabel, con);
            con.gridx = 3;
            regPage.add(shippingPostalCodeLabel, con);
            con.gridx = 1;
            con.gridwidth = 2;
            regPage.add(shipCountryTF, con);
            con.gridx = 4;
            regPage.add(shipPostalCodeTF, con);

            con.gridy = 13;
            con.gridx = 0;
            con.gridwidth = 1;
            regPage.add(Box.createRigidArea(spacer), con);

            con.gridy = 14;
            con.gridx = 0;
            con.gridwidth = 3;
            regPage.add(billingAddressLabel, con);
            con.gridx = 1;
            regPage.add(billingSameAsShipping,con);

            con.gridy = 15;
            con.gridx = 0;
            con.gridwidth = 1;
            regPage.add(billStreetNumLabel, con);
            con.gridx = 1;
            regPage.add(billStreetNumTF, con);
            con.gridx = 2;
            regPage.add(billStreetNameLabel, con);
            con.gridx = 3;
            regPage.add(billStreetNameTF, con);
            con.gridx = 4;
            regPage.add(billApartmentLabel, con);
            con.gridx = 5;
            regPage.add(billApartmentTF, con);

            con.gridy = 16;
            con.gridx = 0;
            con.gridwidth = 1;
            regPage.add(billCityLabel, con);
            con.gridx = 3;
            regPage.add(billProvinceLabel, con);
            con.gridx = 1;
            con.gridwidth = 2;
            regPage.add(billCityTF, con);
            con.gridx = 4;
            con.gridwidth = 1;
            regPage.add(billProvinceCB, con);

            con.gridy = 17;
            con.gridx = 0;
            con.gridwidth = 1;
            regPage.add(billCountryLabel, con);
            con.gridx = 3;
            regPage.add(billPostalCodeLabel, con);
            con.gridx = 1;
            con.gridwidth = 2;
            regPage.add(billCountryTF, con);
            con.gridx = 4;
            regPage.add(billPostalCodeTF, con);

            con.gridy = 18;
            con.gridx = 0;
            con.gridwidth = 6;
            regPage.add(buttons, con);

            con.gridy = 19;
            con.gridx = 0;
            con.gridwidth = 6;
            regPage.add(confirmRegistration, con);

            con.gridy = 19; // shift everything to the top
            con.gridx = 0;
            con.weighty = 1.0;
            con.gridwidth = 6;
            con.anchor = GridBagConstraints.CENTER;
            con.fill = GridBagConstraints.BOTH;
            Component glue = Box.createVerticalGlue();
            regPage.add(glue, con);
        }

        c.add(regPage);
        f.pack();
        f.setLocationRelativeTo(null);
    }

    /**
     * Display the screen for a librarian
     *
     */
    private void adminScreen() {
        f.setPreferredSize(new Dimension(950, 800));
        c.removeAll();

        /* JMenu Setup */
        JMenuBar adminMenuBar = new JMenuBar();
        JMenu adminMenu = new JMenu("Admin");
        JMenuItem switchToUserScreen = new JMenuItem("Switch to User View");
        JMenuItem logoutMenuItem = new JMenuItem("Logout");
        adminMenuBar.add(adminMenu);
        adminMenu.add(switchToUserScreen);
        adminMenu.addSeparator();
        adminMenu.add(logoutMenuItem);
        switchToUserScreen.addActionListener(this);
        logoutMenuItem.addActionListener(this);

        /* JTabbedPanes */
        JTabbedPane adminView = new JTabbedPane();
        JTabbedPane newEntitiesPanel = new JTabbedPane(JTabbedPane.BOTTOM);
        JTabbedPane editEntitiesPanel = new JTabbedPane(JTabbedPane.BOTTOM);

        // Setup Tabbed Panes
        adminView.addTab("Add Stuff", null, newEntitiesPanel, "Add a new book or publisher");
        adminView.addTab("Edit Stuff", null, editEntitiesPanel, "Edit the stock of a book");
        adminView.addTab("Reports", null, reportPanel(), "Generate sales reports");
        newEntitiesPanel.addTab("Add Book", null, addBook(), "Add a new book to inventory");
        newEntitiesPanel.addTab("Add Publisher", null, addPublisher(), "Add a new publisher to database");
        newEntitiesPanel.addTab("Add Users", null, addUser(), "Add a new user to the database");
        editEntitiesPanel.addTab("Edit Books", null, editBook(), "Edit properties of existing books");
        editEntitiesPanel.addTab("Edit User", null, editUser(), "Edit properties of existing users");

        /* ChangeListeners */
        adminView.addChangeListener(this);
        newEntitiesPanel.addChangeListener(this);
        editEntitiesPanel.addChangeListener(this);

        c.add(adminView);
        f.setJMenuBar(adminMenuBar);
        f.pack();
        f.setLocationRelativeTo(null);
    }

    /**
     * Clears all admin view fields and returns to default
     */
    private void defaultAdminViewFields(){
        // addBook
        newBookTitleTF.setText("");
        newISBNTF.setText("");
        newBookVersionTF.setText("");
        newBookNumPagesTF.setText("");
        newBookGenreTF.setText("");
        newBookStockTF.setText("");
        newBookYearTF.setText("");
        newBookPriceTF.setText("");
        newBookRoyaltyTF.setText("");
        newBookAuthorFNTF.setText("");
        newBookAuthorLNTF.setText("");
        newBookPublisherTF.setText("");
        addBookErrorLabel.setText("");
        confirmNewBookAddition.setText("");

        // addPublisher
        newPublisherNameTF.setText("");
        newPublisherEmailTF.setText("");
        newPublisherPhoneTF.setText("");
        newPublisherStreetNumTF.setText("");
        newPublisherStreetNameTF.setText("");
        newPublisherApartmentTF.setText("");
        newPublisherCityTF.setText("");
        newPublisherCountryTF.setText("");
        newPublisherPostalCodeTF.setText("");
        newPublisherBankAccountTF.setText("");
        pubProvinceCB.setSelectedIndex(0);
        addPublisherErrorLabel.setText("");
        confirmNewPublisherAddition.setText("");

        // addUser
        newAdminUsernameTF.setText("");
        newAdminPasswordTF.setText("");
        confirmAdminPasswordTF.setText("");
        firstNameAdminTF.setText("");
        lastNameAdminTF.setText("");
        emailAdminTF.setText("");
        salaryAdminTF.setText("");
        shippingAdminStreetNameTF.setText("");
        shippingAdminStreetNumTF.setText("");
        shippingAdminApartmentTF.setText("");
        shippingAdminCityTF.setText("");
        shippingAdminCountryTF.setText("");
        shippingAdminPostalCodeTF.setText("");
        billAdminStreetNameTF.setText("");
        billAdminStreetNumTF.setText("");
        billAdminApartmentTF.setText("");
        billAdminCityTF.setText("");
        billAdminCountryTF.setText("");
        billAdminPostalCodeTF.setText("");
        billAdminProvinceCB.setSelectedIndex(0);
        shippingAdminProvinceCB.setSelectedIndex(0);
        addUserErrorLabel.setText("");
        confirmUserEditLabel.setText("");

        // editBook
        editBookSearchTF.setText("");
        editBookTitleTF.setText("");
        currentISBNLabel.setText("");
        editBookVersionTF.setText("");
        editBookNumPagesTF.setText("");
        editBookGenreTF.setText("");
        editBookStockTF.setText("");
        editBookYearTF.setText("");
        editBookPriceTF.setText("");
        editBookRoyaltyTF.setText("");
        editBookAuthorFNTF.setText("");
        editBookAuthorLNTF.setText("");
        editBookPublisherTF.setText("");
        editBookErrorLabel.setText("");
        confirmBookEditLabel.setText("");

        // editUser
        isUserAdminCB.setSelected(false);
        editBillingSameAsShipping.setSelected(false);
        editUserSearchTF.setText("");
        currentUserNameLabel.setText("");
        editPasswordTF.setText("");
        confirmEditPasswordTF.setText("");
        editFirstNameTF.setText("");
        editLastNameTF.setText("");
        editEmailTF.setText("");
        editSalaryTF.setText("");
        editShippingStreetNameTF.setText("");
        editShippingStreetNumTF.setText("");
        editShippingApartmentTF.setText("");
        editShippingCityTF.setText("");
        editShippingCountryTF.setText("");
        editShippingPostalCodeTF.setText("");
        editBillStreetNameTF.setText("");
        editBillStreetNumTF.setText("");
        editBillApartmentTF.setText("");
        editBillCityTF.setText("");
        editBillCountryTF.setText("");
        editBillPostalCodeTF.setText("");
        editShippingProvinceCB.setSelectedIndex(0);
        editBillProvinceCB.setSelectedIndex(0);
        editUserErrorLabel.setText("");
        confirmUserEditLabel.setText("");

        // reports


        // Disable the fields by default.
        billingSameAsShipping.setSelected(true);
        billStreetNumTF.setEnabled(false);
        billStreetNameTF.setEnabled(false);
        billApartmentTF.setEnabled(false);
        billCityTF.setEnabled(false);
        billProvinceCB.setEnabled(false);
        billCountryTF.setEnabled(false);
        billPostalCodeTF.setEnabled(false);
        editBookTitleTF.setEnabled(false);
        currentISBNLabel.setEnabled(false);
        editBookVersionTF.setEnabled(false);
        editBookNumPagesTF.setEnabled(false);
        editBookGenreTF.setEnabled(false);
        editBookStockTF.setEnabled(false);
        editBookYearTF.setEnabled(false);
        editBookPriceTF.setEnabled(false);
        editBookRoyaltyTF.setEnabled(false);
        editBookAuthorFNTF.setEnabled(false);
        editBookAuthorLNTF.setEnabled(false);
        editBookPublisherTF.setEnabled(false);
        editBookErrorLabel.setEnabled(false);
        editBillStreetNumTF.setEnabled(false);
        editBillStreetNameTF.setEnabled(false);
        editBillApartmentTF.setEnabled(false);
        editBillCityTF.setEnabled(false);
        editBillProvinceCB.setEnabled(false);
        editBillCountryTF.setEnabled(false);
        editBillPostalCodeTF.setEnabled(false);
        editPasswordTF.setEnabled(false);
        confirmEditPasswordTF.setEnabled(false);
        editFirstNameTF.setEnabled(false);
        editLastNameTF.setEnabled(false);
        editEmailTF.setEnabled(false);
        editSalaryTF.setEnabled(false);
        editShippingStreetNumTF.setEnabled(false);
        editShippingStreetNameTF.setEnabled(false);
        editShippingApartmentTF.setEnabled(false);
        editShippingCityTF.setEnabled(false);
        editShippingCountryTF.setEnabled(false);
        editShippingPostalCodeTF.setEnabled(false);
        editBillingSameAsShipping.setEnabled(false);
        editShippingProvinceCB.setEnabled(false);

    }

    /**
     * Display the screen for a user
     */
    private void userScreen() {
        f.setPreferredSize(new Dimension(798, 850));
        if (f.getJMenuBar() != null) f.getJMenuBar().setVisible(false);
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
        f.pack();
        f.setLocationRelativeTo(null);
    }

    /**
     * Creates the "Checkout" interface for the userScreen
     * Accepts new shipping and billing addresses
     * Accepts credit card details
     * <p>
     * TODO: Possibly add a cart view during checkout, but we could also not...
     */
    private void checkoutScreen() {
        // Clear GUI in order to reload
        f.setPreferredSize(new Dimension(800, 800));
        if (f.getJMenuBar() != null) f.getJMenuBar().setVisible(false);
        c.removeAll();

        // Setup Components
        /* JButtons */
        JButton cancelOrder = new JButton("Cancel Checkout"),
                submitOrder = new JButton("Confirm Order");

        /* Panel */
        final GridBagLayout layout = new GridBagLayout();
        JPanel checkoutPanel = new JPanel(layout) {
            // This will paint grid lines on the layout
            /*@Override
            public void paint(Graphics g)
            {
                super.paint(g);
                int[][] dims = layout.getLayoutDimensions();
                g.setColor(Color.BLUE);
                int x = 0;
                for (int add : dims[0])
                {
                    x += add;
                    g.drawLine(x, 0, x, getHeight());
                }
                int y = 0;
                for (int add : dims[1])
                {
                    y += add;
                    g.drawLine(0, y, getWidth(), y);
                }
            }*/
        };

        /* JLabels */
        JLabel checkoutTotalPriceLabel = new JLabel("Total Price: ", JLabel.RIGHT),
                checkoutNameLabel = new JLabel("Name: "),
                checkoutEmailLabel = new JLabel("Receipt will be sent to: "),
                // checkout shipping address info
                checkoutShippingLabel = new JLabel("Confirm Shipping Address"),
                checkoutStreetNumLabel = new JLabel("*Street Number: "),
                checkoutStreetNameLabel = new JLabel("*Street Name: ", JLabel.RIGHT),
                checkoutApartmentLabel = new JLabel("*Apartment: ", JLabel.RIGHT),
                checkoutCityLabel = new JLabel("*City: "),
                checkoutProvinceLabel = new JLabel("*Province: ", JLabel.RIGHT),
                checkoutCountryLabel = new JLabel("*Country: "),
                checkoutPostalCodeLabel = new JLabel("*Postal Code: ", JLabel.RIGHT),
                //  checkout Billing info
                checkoutBillingAddressLabel = new JLabel("Billing Info"),
                checkoutCreditCardNumLabel = new JLabel("Credit Card #: "),
                checkoutCreditCardExpLabel = new JLabel("EXP: "),
                checkoutCreditCardCVV = new JLabel("CVV: ", JLabel.RIGHT),
                checkoutBillingStreetNumLabel = new JLabel("Street Number: "),
                checkoutBillingStreetNameLabel = new JLabel("Street Name: ", JLabel.RIGHT),
                checkoutBillingApartmentLabel = new JLabel("Apartment: ", JLabel.RIGHT),
                checkoutBillingCityLabel = new JLabel("City: "),
                checkoutBillingProvinceLabel = new JLabel("Province: ", JLabel.RIGHT),
                checkoutBillingCountryLabel = new JLabel("Country: "),
                checkoutBillingPostalCodeLabel = new JLabel("Postal Code: ", JLabel.RIGHT);

        /* JCheckBoxes */
        JCheckBox billingSameAsShipping = new JCheckBox("Billing Address is the same as Shipping Address");
        billingSameAsShipping.setSelected(true); // selected by default

        /* ActionListeners */
        cancelOrder.addActionListener(this);
        submitOrder.addActionListener(this);
        billingSameAsShipping.addActionListener(e -> {
            boolean sameAsBilling = !billingSameAsShipping.isSelected();
            checkoutBillingStreetNumTF.setEnabled(sameAsBilling);
            checkoutBillingStreetNameTF.setEnabled(sameAsBilling);
            checkoutBillingApartmentTF.setEnabled(sameAsBilling);
            checkoutBillingCityTF.setEnabled(sameAsBilling);
            checkoutBillingProvinceCB.setEnabled(sameAsBilling);
            checkoutBillingCountryTF.setEnabled(sameAsBilling);
            checkoutBillingPostalCodeTF.setEnabled(sameAsBilling);
        });

        // Setup checkoutPanel
        {
            checkoutBillingStreetNumTF.setEnabled(false);
            checkoutBillingStreetNameTF.setEnabled(false);
            checkoutBillingApartmentTF.setEnabled(false);
            checkoutBillingCityTF.setEnabled(false);
            checkoutBillingProvinceCB.setEnabled(false);
            checkoutBillingCountryTF.setEnabled(false);
            checkoutBillingPostalCodeTF.setEnabled(false);

            GridBagConstraints con = new GridBagConstraints();
            Dimension spacer = new Dimension(35, 35);
            con.gridy = 0;
            con.gridx = 1;
            con.gridwidth = 2;
            con.anchor = GridBagConstraints.LINE_START;
            con.fill = GridBagConstraints.NONE;
            checkoutPanel.add(cancelOrder, con);
            con.gridx = 0;
            con.gridwidth = 8;
            checkoutErrorLabel.setForeground(Color.red);
            con.fill = GridBagConstraints.HORIZONTAL;
            checkoutPanel.add(checkoutErrorLabel, con);

            con.gridy = 1;
            con.gridx = 1;
            con.weightx = 1.0;
            con.gridwidth = 3;
            con.anchor = GridBagConstraints.LINE_END;
            checkoutPanel.add(checkoutUsernameLabel, con);

            con.gridy = 2;
            con.gridx = 1;
            con.gridwidth = 1;
            con.anchor = GridBagConstraints.LINE_START;
            checkoutPanel.add(checkoutNameLabel, con);
            con.gridx = 2;
            con.gridwidth = 4;
            checkoutPanel.add(checkoutNameField, con);

            con.gridy = 3;
            con.gridx = 1;
            con.gridwidth = 2;
            checkoutPanel.add(checkoutEmailLabel, con);
            con.gridx = 3;
            con.gridwidth = 4;
            checkoutPanel.add(checkoutEmailConfirm, con);

            con.gridy = 4;
            con.gridx = 1;
            checkoutPanel.add(Box.createRigidArea(spacer), con);

            con.gridy = 5;
            con.gridx = 1;
            con.gridwidth = 3;
            checkoutPanel.add(checkoutShippingLabel, con);

            con.gridy = 6;
            con.gridx = 1;
            con.gridwidth = 1;
            checkoutPanel.add(checkoutStreetNumLabel, con);
            con.gridx = 2;
            con.fill = GridBagConstraints.NONE;
            checkoutPanel.add(checkoutStreetNumTF, con);
            con.gridx = 3;
            con.fill = GridBagConstraints.HORIZONTAL;
            checkoutPanel.add(checkoutStreetNameLabel, con);
            con.gridx = 4;
            con.fill = GridBagConstraints.NONE;
            checkoutPanel.add(checkoutStreetNameTF, con);
            con.gridx = 5;
            con.fill = GridBagConstraints.HORIZONTAL;
            checkoutPanel.add(checkoutApartmentLabel, con);
            con.gridx = 6;
            con.fill = GridBagConstraints.NONE;
            checkoutPanel.add(checkoutApartmentTF, con);

            con.gridy = 7;
            con.gridx = 1;
            con.gridwidth = 1;
            con.fill = GridBagConstraints.HORIZONTAL;
            checkoutPanel.add(checkoutCityLabel, con);
            con.gridx = 4;
            checkoutPanel.add(checkoutProvinceLabel, con);
            con.gridx = 2;
            con.gridwidth = 2;
            con.fill = GridBagConstraints.NONE;
            checkoutPanel.add(checkoutCityTF, con);
            con.gridx = 5;
            con.gridwidth = 1;
            checkoutPanel.add(checkoutShippingProvinceCB, con);

            con.gridy = 8;
            con.gridx = 1;
            con.gridwidth = 1;
            con.fill = GridBagConstraints.HORIZONTAL;
            checkoutPanel.add(checkoutCountryLabel, con);
            con.gridx = 4;
            checkoutPanel.add(checkoutPostalCodeLabel, con);
            con.gridx = 2;
            con.gridwidth = 2;
            con.fill = GridBagConstraints.NONE;
            checkoutPanel.add(checkoutCountryTF, con);
            con.gridx = 5;
            con.anchor = GridBagConstraints.LINE_START;
            checkoutPanel.add(checkoutPostalCodeTF, con);

            con.gridy = 9;
            con.gridx = 1;
            checkoutPanel.add(Box.createRigidArea(spacer), con);

            con.gridy = 10;
            con.gridx = 1;
            con.gridwidth = 3;
            con.fill = GridBagConstraints.HORIZONTAL;
            checkoutPanel.add(checkoutBillingAddressLabel, con);
            con.gridx = 3;
            checkoutPanel.add(billingSameAsShipping, con);

            con.gridy = 11;
            con.gridx = 1;
            con.gridwidth = 1;
            checkoutPanel.add(checkoutCreditCardNumLabel, con);
            con.gridx = 2;
            con.gridwidth = 3;
            checkoutPanel.add(checkoutCreditCardNumTF, con);

            con.gridy = 12;
            con.gridx = 1;
            con.gridwidth = 1;
            con.fill = GridBagConstraints.HORIZONTAL;
            checkoutPanel.add(checkoutCreditCardExpLabel, con);
            con.gridx = 2;
            con.fill = GridBagConstraints.NONE;
            checkoutPanel.add(checkoutCreditCardExpTF, con);
            con.gridx = 3;
            con.fill = GridBagConstraints.HORIZONTAL;
            checkoutPanel.add(checkoutCreditCardCVV, con);
            con.gridx = 4;
            con.fill = GridBagConstraints.NONE;
            checkoutPanel.add(checkoutCreditCardCVVTF, con);

            con.gridy = 13;
            con.gridx = 1;
            checkoutPanel.add(Box.createRigidArea(spacer), con);

            con.gridy = 14;
            con.gridx = 1;
            con.gridwidth = 1;
            con.fill = GridBagConstraints.HORIZONTAL;
            checkoutPanel.add(checkoutBillingStreetNumLabel, con);
            con.gridx = 2;
            con.fill = GridBagConstraints.NONE;
            checkoutPanel.add(checkoutBillingStreetNumTF, con);
            con.gridx = 3;
            con.fill = GridBagConstraints.HORIZONTAL;
            checkoutPanel.add(checkoutBillingStreetNameLabel, con);
            con.gridx = 4;
            con.fill = GridBagConstraints.NONE;
            checkoutPanel.add(checkoutBillingStreetNameTF, con);
            con.gridx = 5;
            con.fill = GridBagConstraints.HORIZONTAL;
            checkoutPanel.add(checkoutBillingApartmentLabel, con);
            con.gridx = 6;
            con.fill = GridBagConstraints.NONE;
            checkoutPanel.add(checkoutBillingApartmentTF, con);

            con.gridy = 15;
            con.gridx = 1;
            con.gridwidth = 1;
            con.fill = GridBagConstraints.HORIZONTAL;
            checkoutPanel.add(checkoutBillingCityLabel, con);
            con.gridx = 4;
            checkoutPanel.add(checkoutBillingProvinceLabel, con);
            con.gridx = 2;
            con.gridwidth = 2;
            con.fill = GridBagConstraints.NONE;
            checkoutPanel.add(checkoutBillingCityTF, con);
            con.gridx = 5;
            con.gridwidth = 1;
            checkoutPanel.add(checkoutBillingProvinceCB, con);

            con.gridy = 16;
            con.gridx = 1;
            con.gridwidth = 1;
            con.fill = GridBagConstraints.HORIZONTAL;
            checkoutPanel.add(checkoutBillingCountryLabel, con);
            con.gridx = 4;
            checkoutPanel.add(checkoutBillingPostalCodeLabel, con);
            con.gridx = 2;
            con.gridwidth = 2;
            con.fill = GridBagConstraints.NONE;
            checkoutPanel.add(checkoutBillingCountryTF, con);
            con.gridx = 5;
            con.anchor = GridBagConstraints.LINE_START;
            checkoutPanel.add(checkoutBillingPostalCodeTF, con);

            con.gridy = 17;
            con.gridx = 1;
            checkoutPanel.add(Box.createRigidArea(spacer), con);

            con.gridy = 18;
            con.gridx = 4;
            con.gridwidth = 1;
            con.anchor = GridBagConstraints.LINE_END;
            con.fill = GridBagConstraints.HORIZONTAL;
            checkoutPanel.add(checkoutTotalPriceLabel, con);
            con.gridx = 5;
            con.anchor = GridBagConstraints.CENTER;
            checkoutPanel.add(checkoutTotalPriceValueLabel, con);
            con.gridwidth = 2;
            con.gridx = 6;
            con.anchor = GridBagConstraints.LINE_END;
            con.fill = GridBagConstraints.NONE;
            checkoutPanel.add(submitOrder, con);

            con.gridy = 19;
            con.gridx = 5;
            con.gridwidth = 2;
            con.anchor = GridBagConstraints.LINE_END;
            checkoutPanel.add(checkoutSuccessLabel, con);

            con.gridy = 20; // shift everything to the top and center
            con.gridx = 0;
            con.weighty = 1.0;
            con.weightx = 1.0;
            con.gridwidth = 1;
            con.anchor = GridBagConstraints.CENTER;
            con.fill = GridBagConstraints.BOTH;
            checkoutPanel.add(Box.createGlue(), con);
            con.gridx = 6;
            checkoutPanel.add(Box.createGlue(), con);

            checkoutPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

            c.add(checkoutPanel);
            f.pack();
            f.setLocationRelativeTo(null);
        }
    }

    /**
     * Creates the "Lookup Order" interface for the loginScreen
     * Finds a current order
     * <p>
     */
    private void lookupOrderScreen() {
        // Clear GUI in order to reload
        f.setPreferredSize(new Dimension(400, 200));
        if (f.getJMenuBar() != null) f.getJMenuBar().setVisible(false);
        c.removeAll();

        /* JPanels */
        JPanel orderScreen = new JPanel(new GridBagLayout());
        orderScreen.setBorder(new EmptyBorder(10, 10, 10, 10));

        /* JButtons */
        JButton cancelLookup = new JButton("Cancel Lookup");
        JButton lookupOrder = new JButton("Lookup Order");

        /* JLabels */
        JLabel orderNumberLabel = new JLabel("Order Number: ");
        JLabel trackingNumberLabel = new JLabel("Tracking Number: ");
        JLabel dateOrderPlacedLabel = new JLabel("Date Placed: ");
        JLabel userNameOrderLabel = new JLabel("Order Owner: ");
        JLabel orderStatustatusLabel = new JLabel("Status: ");

        /* ActionListeners */
        cancelLookup.addActionListener(this);
        lookupOrder.addActionListener(this);

        // Setup Panel
        GridBagConstraints con = new GridBagConstraints();
        con.gridy = 0;
        con.gridx = 0;
        con.gridwidth = 1;
        con.fill = GridBagConstraints.HORIZONTAL;
        con.anchor = GridBagConstraints.LINE_START;
        orderScreen.add(cancelLookup, con);
        con.gridx = 1;
        orderScreen.add(lookupOrder, con);

        con.gridy = 1;
        con.gridx = 0;
        orderScreen.add(orderNumberLabel, con);
        con.gridx = 1;
        orderScreen.add(orderNumber, con);

        con.gridy = 2;
        con.gridx = 0;
        orderScreen.add(trackingNumberLabel, con);
        con.gridx = 1;
        orderScreen.add(trackingNumber, con);

        con.gridy = 3;
        con.gridx = 0;
        orderScreen.add(dateOrderPlacedLabel, con);
        con.gridx = 1;
        orderScreen.add(dateOrderPlaced, con);

        con.gridy = 4;
        con.gridx = 0;
        orderScreen.add(userNameOrderLabel, con);
        con.gridx = 1;
        orderScreen.add(userNameOrder, con);

        con.gridy = 5;
        con.gridx = 0;
        orderScreen.add(orderStatustatusLabel, con);
        con.gridx = 1;
        orderScreen.add(orderStatus, con);

        c.add(orderScreen);
        f.pack();
        f.setLocationRelativeTo(null);
    }

    /**
     * Creates the "Add Book" tab for the "Add Stuff" tab of the adminView
     *
     * @return addNewBookPanel for adminView
     */
    private JPanel addBook() {
        JPanel addNewBookPanel = new JPanel(new GridBagLayout());

        /* JButtons */
        JButton logoutAddBookButton = new JButton("Logout");
        JButton addBookButton = new JButton("Add Book");

        /* JLabels */
        JLabel newBookLabel = new JLabel("Enter Book Information (required fields indicated by *): "),
                newISBNLabel = new JLabel("*ISBN: ", JLabel.RIGHT), // this doesn't work and idk why
                newBookTitleLabel = new JLabel("*Title: "),
                newBookVersionLabel = new JLabel("Version: "),
                newBookGenreLabel = new JLabel("Genre: "),
                newBookNumPagesLabel = new JLabel("Pg Count: ", JLabel.RIGHT),
                newBookPriceLabel = new JLabel("*Price:"),
                newBookRoyaltyLabel = new JLabel("*Royalty (%): ", JLabel.RIGHT),
                newBookStockLabel = new JLabel("Stock:", JLabel.RIGHT),
                newBookAuthorLabel = new JLabel("Author: "),
                newBookAuthorFNLabel = new JLabel("First Name: "),
                newBookAuthorLNLabel = new JLabel("Last Name: ", JLabel.RIGHT),
                newBookPublisherLabel = new JLabel("Publisher (be sure to add new publishers before adding books): "),
                newBookYearLabel = new JLabel("Year: "),
                accountingLabel = new JLabel("*Accounting: ");

        /* ActionListeners */
        logoutAddBookButton.addActionListener(this);
        addBookButton.addActionListener(this);

        // Setup addBookPanel
        {
            GridBagConstraints bookCon = new GridBagConstraints();
            Dimension spacer = new Dimension(15, 15);
            bookCon.gridx = 0;
            bookCon.gridy = 0;
            addNewBookPanel.add(logoutAddBookButton, bookCon);

            bookCon.gridx = 1;
            bookCon.gridwidth = 4;
            bookCon.anchor = GridBagConstraints.CENTER;
            addBookErrorLabel.setForeground(Color.red);
            addNewBookPanel.add(addBookErrorLabel, bookCon);

            bookCon.gridx = 5;
            bookCon.gridwidth = 1;
            bookCon.anchor = GridBagConstraints.FIRST_LINE_END;
            addNewBookPanel.add(addBookButton, bookCon);

            bookCon.gridx = 1;
            bookCon.gridy = 1;
            bookCon.gridwidth = 3;
            bookCon.anchor = GridBagConstraints.LINE_START;
            addNewBookPanel.add(newBookLabel, bookCon);

            bookCon.gridy = 2;
            bookCon.gridx = 1;
            bookCon.gridwidth = 1;
            addNewBookPanel.add(newBookTitleLabel, bookCon);
            bookCon.gridx = 2;
            addNewBookPanel.add(newBookTitleTF, bookCon);
            bookCon.gridx = 3;
            addNewBookPanel.add(newISBNLabel, bookCon);
            bookCon.gridx = 4;
            addNewBookPanel.add(newISBNTF, bookCon);

            bookCon.gridy = 3;
            bookCon.gridx = 1;
            addNewBookPanel.add(newBookVersionLabel, bookCon);
            bookCon.gridx = 2;
            addNewBookPanel.add(newBookVersionTF, bookCon);
            bookCon.gridx = 3;
            addNewBookPanel.add(newBookNumPagesLabel, bookCon);
            bookCon.gridx = 4;
            addNewBookPanel.add(newBookNumPagesTF, bookCon);

            bookCon.gridy = 4;
            bookCon.gridx = 1;
            addNewBookPanel.add(newBookGenreLabel, bookCon);
            bookCon.gridx = 2;
            addNewBookPanel.add(newBookGenreTF, bookCon);
            bookCon.gridx = 3;
            addNewBookPanel.add(newBookStockLabel, bookCon);
            bookCon.gridx = 4;
            addNewBookPanel.add(newBookStockTF, bookCon);

            bookCon.gridy = 5;
            bookCon.gridx = 1;
            addNewBookPanel.add(newBookYearLabel, bookCon);
            bookCon.gridx = 2;
            addNewBookPanel.add(newBookYearTF, bookCon);

            bookCon.gridy = 6;
            bookCon.gridx = 1;
            addNewBookPanel.add(Box.createRigidArea(spacer), bookCon);

            bookCon.gridy = 7;
            bookCon.gridx = 1;
            bookCon.gridwidth = 2;
            addNewBookPanel.add(accountingLabel, bookCon);

            bookCon.gridy = 8;
            bookCon.gridx = 1;
            bookCon.gridwidth = 1;
            addNewBookPanel.add(newBookPriceLabel, bookCon);
            bookCon.gridx = 2;
            addNewBookPanel.add(newBookPriceTF, bookCon);
            bookCon.gridx = 3;
            addNewBookPanel.add(newBookRoyaltyLabel, bookCon);
            bookCon.gridx = 4;
            addNewBookPanel.add(newBookRoyaltyTF, bookCon);

            bookCon.gridy = 9;
            bookCon.gridx = 1;
            addNewBookPanel.add(Box.createRigidArea(spacer), bookCon);

            bookCon.gridy = 10;
            bookCon.gridx = 1;
            addNewBookPanel.add(newBookAuthorLabel, bookCon);

            bookCon.gridy = 11;
            bookCon.gridx = 1;
            addNewBookPanel.add(newBookAuthorFNLabel, bookCon);
            bookCon.gridx = 2;
            addNewBookPanel.add(newBookAuthorFNTF, bookCon);
            bookCon.gridx = 3;
            addNewBookPanel.add(newBookAuthorLNLabel, bookCon);
            bookCon.gridx = 4;
            addNewBookPanel.add(newBookAuthorLNTF, bookCon);

            bookCon.gridy = 12;
            bookCon.gridx = 1;
            addNewBookPanel.add(Box.createRigidArea(spacer), bookCon);

            bookCon.gridy = 13;
            bookCon.gridwidth = 4;
            addNewBookPanel.add(newBookPublisherLabel, bookCon);
            bookCon.gridx = 2;
            bookCon.gridy = 14;
            bookCon.gridwidth = 3;
            bookCon.fill = GridBagConstraints.HORIZONTAL;
            addNewBookPanel.add(newBookPublisherTF, bookCon);

            bookCon.gridwidth = 2;
            bookCon.gridy = 1;
            bookCon.gridx = 5;
            bookCon.anchor = GridBagConstraints.LINE_END;
            confirmNewBookAddition.setAlignmentX(JPanel.RIGHT_ALIGNMENT);
            addNewBookPanel.add(confirmNewBookAddition, bookCon);

            bookCon.gridy = 15; // shift everything to the top
            bookCon.gridx = 0;
            bookCon.weighty = 1.0;
            bookCon.gridwidth = 6;
            bookCon.anchor = GridBagConstraints.CENTER;
            bookCon.fill = GridBagConstraints.BOTH;
            Component glue = Box.createVerticalGlue();
            addNewBookPanel.add(glue, bookCon);
        }
        return addNewBookPanel;
    }

    /**
     * Creates the "Add Publisher" tab for the "Add Stuff" tab of the adminView
     *
     * @return addNewPublisherPanel for adminView
     */
    private JPanel addPublisher() {
        JPanel addNewPublisherPanel = new JPanel(new GridBagLayout());

        /* JButtons */
        JButton logoutAddPublisherButton = new JButton("Logout");
        JButton addPublisherButton = new JButton("Add Publisher");

        /* JLabels */
        JLabel newPublisherLabel = new JLabel("Enter Publisher Information (required fields indicated by *): "),
                newPublisherNameLabel = new JLabel("*Name: "),
                newPublisherAddressLabel = new JLabel("*Address: "),
                newPublisherStreetNumLabel = new JLabel("*Street Number:"),
                newPublisherStreetNameLabel = new JLabel("*Street Name:", JLabel.RIGHT),
                newPublisherApartmentLabel = new JLabel("Apartment:", JLabel.RIGHT),
                newPublisherCityLabel = new JLabel("*City: "),
                newPublisherProvinceLabel = new JLabel("*Province: ", JLabel.RIGHT),
                newPublisherCountryLabel = new JLabel("*Country: "),
                newPublisherPostalCodeLabel = new JLabel("*Postal Code: ", JLabel.RIGHT),
                newPublisherEmailLabel = new JLabel("*Email: "),
                newPublisherPhoneLabel = new JLabel("Phone: ", JLabel.RIGHT),
                newPublisherBankAccountLabel = new JLabel("*Bank Account Number: ");

        /* ActionListeners */
        logoutAddPublisherButton.addActionListener(this);
        addPublisherButton.addActionListener(this);

        // Setup addPublisherPanel
        {
            GridBagConstraints pubCon = new GridBagConstraints();
            Dimension spacer = new Dimension(15, 15);
            pubCon.gridx = 0;
            pubCon.gridy = 0;
            pubCon.anchor = GridBagConstraints.FIRST_LINE_START;
            addNewPublisherPanel.add(logoutAddPublisherButton, pubCon);

            pubCon.gridx = 1;
            pubCon.gridwidth = 6;
            pubCon.anchor = GridBagConstraints.CENTER;
            addPublisherErrorLabel.setForeground(Color.red);
            addNewPublisherPanel.add(addPublisherErrorLabel, pubCon);

            pubCon.gridx = 7;
            pubCon.gridwidth = 1;
            pubCon.anchor = GridBagConstraints.FIRST_LINE_END;
            addNewPublisherPanel.add(addPublisherButton, pubCon);

            pubCon.gridx = 1;
            pubCon.gridy = 1;
            pubCon.weightx = 1.0;
            pubCon.gridwidth = 7;
            pubCon.anchor = GridBagConstraints.LINE_START;
            pubCon.fill = GridBagConstraints.HORIZONTAL;
            addNewPublisherPanel.add(newPublisherLabel, pubCon);

            pubCon.gridy = 2;
            pubCon.gridx = 1;
            pubCon.gridwidth = 1;
            addNewPublisherPanel.add(newPublisherNameLabel, pubCon);
            pubCon.gridx = 2;
            pubCon.gridwidth = 5;
            addNewPublisherPanel.add(newPublisherNameTF, pubCon);

            pubCon.gridy = 3;
            pubCon.gridx = 1;
            pubCon.gridwidth = 1;
            addNewPublisherPanel.add(newPublisherEmailLabel, pubCon);
            pubCon.gridx = 2;
            pubCon.gridwidth = 2;
            addNewPublisherPanel.add(newPublisherEmailTF, pubCon);
            pubCon.gridx = 4;
            pubCon.gridwidth = 1;
            addNewPublisherPanel.add(newPublisherPhoneLabel, pubCon);
            pubCon.gridx = 5;
            pubCon.gridwidth = 2;
            addNewPublisherPanel.add(newPublisherPhoneTF, pubCon);

            pubCon.gridy = 4;
            pubCon.gridx = 1;
            addNewPublisherPanel.add(Box.createRigidArea(spacer), pubCon);

            pubCon.gridy = 5;
            pubCon.gridx = 1;
            addNewPublisherPanel.add(newPublisherAddressLabel, pubCon);

            pubCon.gridy = 6;
            pubCon.gridx = 1;
            pubCon.gridwidth = 1;
            addNewPublisherPanel.add(newPublisherStreetNumLabel, pubCon);
            pubCon.gridx = 2;
            addNewPublisherPanel.add(newPublisherStreetNumTF, pubCon);
            pubCon.gridx = 3;
            addNewPublisherPanel.add(newPublisherStreetNameLabel, pubCon);
            pubCon.gridx = 4;
            addNewPublisherPanel.add(newPublisherStreetNameTF, pubCon);
            pubCon.gridx = 5;
            addNewPublisherPanel.add(newPublisherApartmentLabel, pubCon);
            pubCon.gridx = 6;
            addNewPublisherPanel.add(newPublisherApartmentTF, pubCon);

            pubCon.gridy = 7;
            pubCon.gridx = 1;
            pubCon.gridwidth = 1;
            addNewPublisherPanel.add(newPublisherCityLabel, pubCon);
            pubCon.gridx = 2;
            pubCon.gridwidth = 2;
            addNewPublisherPanel.add(newPublisherCityTF, pubCon);
            pubCon.gridx = 4;
            pubCon.gridwidth = 1;
            addNewPublisherPanel.add(newPublisherProvinceLabel, pubCon);
            pubCon.gridx = 5;
            pubCon.gridwidth = 2;
            addNewPublisherPanel.add(pubProvinceCB, pubCon);

            pubCon.gridy = 8;
            pubCon.gridx = 1;
            pubCon.gridwidth = 1;
            addNewPublisherPanel.add(newPublisherCountryLabel, pubCon);
            pubCon.gridx = 2;
            pubCon.gridwidth = 2;
            addNewPublisherPanel.add(newPublisherCountryTF, pubCon);
            pubCon.gridx = 4;
            pubCon.gridwidth = 1;
            addNewPublisherPanel.add(newPublisherPostalCodeLabel, pubCon);
            pubCon.gridx = 5;
            pubCon.gridwidth = 2;
            addNewPublisherPanel.add(newPublisherPostalCodeTF, pubCon);

            pubCon.gridy = 9;
            pubCon.gridx = 1;
            addNewPublisherPanel.add(Box.createRigidArea(spacer), pubCon);

            pubCon.gridy = 10;
            pubCon.gridx = 1;
            pubCon.gridwidth = 2;
            addNewPublisherPanel.add(newPublisherBankAccountLabel, pubCon);
            pubCon.gridx = 3;
            pubCon.gridwidth = 4;
            addNewPublisherPanel.add(newPublisherBankAccountTF, pubCon);

            pubCon.gridwidth = 2;
            pubCon.gridy = 1;
            pubCon.gridx = 7;
            pubCon.anchor = GridBagConstraints.LINE_END;
            addNewPublisherPanel.add(confirmNewPublisherAddition, pubCon);

            pubCon.gridy = 15; // shift everything to the top
            pubCon.gridx = 0;
            pubCon.weighty = 1.0;
            pubCon.gridwidth = 8;
            pubCon.anchor = GridBagConstraints.CENTER;
            pubCon.fill = GridBagConstraints.BOTH;
            Component glue = Box.createVerticalGlue();
            addNewPublisherPanel.add(glue, pubCon);
        }

        return addNewPublisherPanel;
    }

    /**
     * Creates the "Add User" tab for the "Add Stuff" tab of the adminView
     *
     * @return addNewUserPanel for adminView
     */
    private JPanel addUser() {
        JPanel newUserPanel = new JPanel(new GridBagLayout());

        /* JButtons */
        JButton logoutAddUserButton = new JButton("Logout");
        JButton addUserButton = new JButton("Add User");

        /* JLabels */
        // Admin info
        JLabel newUserLoginCred = new JLabel("Login Credentials: "),
                newUserDetailLabel = new JLabel("User Details: "),
                newAdminUsernameLabel = new JLabel("*Username: "),
                newAdminPasswordLabel = new JLabel("*Password: "),
                confirmAdminPasswordLabel = new JLabel("*Confirm Password: ", JLabel.RIGHT),
                firstNameAdminLabel = new JLabel("*First Name: "),
                lastNameAdminLabel = new JLabel("*Last Name: ", JLabel.RIGHT),
                emailAdminLabel = new JLabel("*Email: "),
                salaryAdminLabel = new JLabel("Salary: ", JLabel.RIGHT),
                // Admin shipping address info (can be EMPTY)
                shippingAdminAddressLabel = new JLabel("Shipping Address"),
                shippingAdminStreetNumLabel = new JLabel("*Street Number: "),
                shippingAdminStreetNameLabel = new JLabel("*Street Name: ", JLabel.RIGHT),
                shippingAdminApartmentLabel = new JLabel("*Apartment: ", JLabel.RIGHT),
                shippingAdminCityLabel = new JLabel("*City: "),
                shippingAdminProvinceLabel = new JLabel("*Province: ", JLabel.RIGHT),
                shippingAdminCountryLabel = new JLabel("*Country: "),
                shippingAdminPostalCodeLabel = new JLabel("*Postal Code: ", JLabel.RIGHT),
                // Admin billing address info
                billingAdminAddressLabel = new JLabel("Billing Address"),
                billAdminStreetNumLabel = new JLabel("Street Number: "),
                billAdminStreetNameLabel = new JLabel("Street Name: ", JLabel.RIGHT),
                billAdminApartmentLabel = new JLabel("Apartment: ", JLabel.RIGHT),
                billAdminCityLabel = new JLabel("City: "),
                billAdminProvinceLabel = new JLabel("Province: ", JLabel.RIGHT),
                billAdminCountryLabel = new JLabel("Country: "),
                billAdminPostalCodeLabel = new JLabel("Postal Code: ", JLabel.RIGHT);

        /* JCheckBoxes */
        JCheckBox billingSameAsShipping = new JCheckBox("Billing Address is the same as Shipping Address");
        billingSameAsShipping.setSelected(true); // selected by default
        JCheckBox isUserAdminCB = new JCheckBox("Is the user an admin?");

        /* ActionListeners */
        logoutAddUserButton.addActionListener(this);
        addUserButton.addActionListener(this);
        billingSameAsShipping.addChangeListener(e -> {
            boolean sameAsBilling = !billingSameAsShipping.isSelected();
            billAdminStreetNumTF.setEnabled(sameAsBilling);
            billAdminStreetNameTF.setEnabled(sameAsBilling);
            billAdminApartmentTF.setEnabled(sameAsBilling);
            billAdminCityTF.setEnabled(sameAsBilling);
            billAdminProvinceCB.setEnabled(sameAsBilling);
            billAdminCountryTF.setEnabled(sameAsBilling);
            billAdminPostalCodeTF.setEnabled(sameAsBilling);
        });
        isUserAdminCB.addChangeListener(e -> {
            boolean admin = isUserAdminCB.isSelected();
            salaryAdminTF.setEnabled(admin);
            if (admin) {
                salaryAdminLabel.setText("*Salary: ");
                shippingAdminStreetNumLabel.setText("Street Number: ");
                shippingAdminStreetNameLabel.setText("Street Name: ");
                shippingAdminApartmentLabel.setText("Apartment: ");
                shippingAdminCityLabel.setText("City: ");
                shippingAdminProvinceLabel.setText("Province: ");
                shippingAdminCountryLabel.setText("Country: ");
                shippingAdminPostalCodeLabel.setText("Postal Code: ");
            } else {
                salaryAdminLabel.setText("Salary: ");
                shippingAdminStreetNumLabel.setText("*Street Number: ");
                shippingAdminStreetNameLabel.setText("*Street Name: ");
                shippingAdminApartmentLabel.setText("*Apartment: ");
                shippingAdminCityLabel.setText("*City: ");
                shippingAdminProvinceLabel.setText("*Province: ");
                shippingAdminCountryLabel.setText("*Country: ");
                shippingAdminPostalCodeLabel.setText("*Postal Code: ");
            }
        });

        // Setup newUserPanel
        {
            // Disable fields by default
            billAdminStreetNumTF.setEnabled(false);
            billAdminStreetNameTF.setEnabled(false);
            billAdminApartmentTF.setEnabled(false);
            billAdminCityTF.setEnabled(false);
            billAdminProvinceCB.setEnabled(false);
            billAdminCountryTF.setEnabled(false);
            billAdminPostalCodeTF.setEnabled(false);
            salaryAdminTF.setEnabled(false);

            GridBagConstraints userCon = new GridBagConstraints();
            Dimension spacer = new Dimension(35, 35);
            userCon.gridy = 0;
            userCon.gridx = 0;
            userCon.anchor = GridBagConstraints.FIRST_LINE_START;
            newUserPanel.add(logoutAddUserButton, userCon);
            userCon.gridx = 1;
            userCon.gridwidth = 6;
            userCon.anchor = GridBagConstraints.CENTER;
            addUserErrorLabel.setForeground(Color.red);
            newUserPanel.add(addUserErrorLabel, userCon);
            userCon.gridx = 7;
            userCon.gridwidth = 2;
            userCon.anchor = GridBagConstraints.FIRST_LINE_END;
            newUserPanel.add(addUserButton, userCon);

            userCon.gridy = 1;
            userCon.gridx = 1;
            userCon.weightx = 1.0;
            userCon.gridwidth = 6;
            userCon.anchor = GridBagConstraints.LINE_END;
            userCon.fill = GridBagConstraints.HORIZONTAL;
            newUserPanel.add(newUserLoginCred, userCon);
            userCon.gridx = 7;
            userCon.gridwidth = 1;
            newUserPanel.add(confirmAdminReg, userCon);

            userCon.gridy = 2;
            userCon.gridx = 1;
            newUserPanel.add(newAdminUsernameLabel, userCon);
            userCon.gridx = 4;
            userCon.gridwidth = 2;
            newUserPanel.add(isUserAdminCB, userCon);
            userCon.gridx = 2;
            newUserPanel.add(newAdminUsernameTF, userCon);

            userCon.gridy = 3;
            userCon.gridx = 1;
            userCon.gridwidth = 1;
            newUserPanel.add(newAdminPasswordLabel, userCon);
            userCon.gridx = 4;
            newUserPanel.add(confirmAdminPasswordLabel, userCon);
            userCon.gridx = 2;
            userCon.gridwidth = 2;
            newUserPanel.add(newAdminPasswordTF, userCon);
            userCon.gridx = 5;
            newUserPanel.add(confirmAdminPasswordTF, userCon);

            userCon.gridy = 4;
            userCon.gridx = 1;
            newUserPanel.add(Box.createRigidArea(spacer), userCon);

            userCon.gridy = 5;
            userCon.gridx = 1;
            userCon.gridwidth = 3;
            newUserPanel.add(newUserDetailLabel, userCon);

            userCon.gridy = 6;
            userCon.gridx = 1;
            userCon.gridwidth = 1;
            newUserPanel.add(firstNameAdminLabel, userCon);
            userCon.gridx = 4;
            newUserPanel.add(lastNameAdminLabel, userCon);
            userCon.gridx = 2;
            userCon.gridwidth = 2;
            newUserPanel.add(firstNameAdminTF, userCon);
            userCon.gridx = 5;
            newUserPanel.add(lastNameAdminTF, userCon);

            userCon.gridy = 7;
            userCon.gridx = 1;
            userCon.gridwidth = 1;
            newUserPanel.add(emailAdminLabel, userCon);
            userCon.gridx = 4;
            newUserPanel.add(salaryAdminLabel, userCon);
            userCon.gridx = 2;
            userCon.gridwidth = 2;
            newUserPanel.add(emailAdminTF, userCon);
            userCon.gridx = 5;
            newUserPanel.add(salaryAdminTF, userCon);

            userCon.gridy = 8;
            userCon.gridx = 1;
            newUserPanel.add(Box.createRigidArea(spacer), userCon);

            userCon.gridy = 9;
            userCon.gridx = 1;
            userCon.gridwidth = 3;
            newUserPanel.add(new JLabel("Address Information"), userCon);

            userCon.gridy = 10;
            userCon.gridx = 1;
            userCon.gridwidth = 3;
            newUserPanel.add(shippingAdminAddressLabel, userCon);

            userCon.gridy = 11;
            userCon.gridx = 1;
            userCon.gridwidth = 1;
            newUserPanel.add(shippingAdminStreetNumLabel, userCon);
            userCon.gridx = 2;
            newUserPanel.add(shippingAdminStreetNumTF, userCon);
            userCon.gridx = 3;
            newUserPanel.add(shippingAdminStreetNameLabel, userCon);
            userCon.gridx = 4;
            newUserPanel.add(shippingAdminStreetNameTF, userCon);
            userCon.gridx = 5;
            newUserPanel.add(shippingAdminApartmentLabel, userCon);
            userCon.gridx = 6;
            newUserPanel.add(shippingAdminApartmentTF, userCon);

            userCon.gridy = 12;
            userCon.gridx = 1;
            userCon.gridwidth = 1;
            newUserPanel.add(shippingAdminCityLabel, userCon);
            userCon.gridx = 4;
            newUserPanel.add(shippingAdminProvinceLabel, userCon);
            userCon.gridx = 2;
            userCon.gridwidth = 2;
            newUserPanel.add(shippingAdminCityTF, userCon);
            userCon.gridx = 5;
            newUserPanel.add(shippingAdminProvinceCB, userCon);

            userCon.gridy = 13;
            userCon.gridx = 1;
            userCon.gridwidth = 1;
            newUserPanel.add(shippingAdminCountryLabel, userCon);
            userCon.gridx = 4;
            newUserPanel.add(shippingAdminPostalCodeLabel, userCon);
            userCon.gridx = 2;
            userCon.gridwidth = 2;
            newUserPanel.add(shippingAdminCountryTF, userCon);
            userCon.gridx = 5;
            newUserPanel.add(shippingAdminPostalCodeTF, userCon);

            userCon.gridy = 14;
            userCon.gridx = 1;
            newUserPanel.add(Box.createRigidArea(spacer), userCon);

            userCon.gridy = 15;
            userCon.gridx = 1;
            userCon.gridwidth = 2;
            newUserPanel.add(billingAdminAddressLabel, userCon);
            userCon.gridx = 3;
            userCon.gridwidth = 4;
            newUserPanel.add(billingSameAsShipping, userCon);

            userCon.gridy = 16;
            userCon.gridx = 1;
            userCon.gridwidth = 1;
            newUserPanel.add(billAdminStreetNumLabel, userCon);
            userCon.gridx = 2;
            newUserPanel.add(billAdminStreetNumTF, userCon);
            userCon.gridx = 3;
            newUserPanel.add(billAdminStreetNameLabel, userCon);
            userCon.gridx = 4;
            newUserPanel.add(billAdminStreetNameTF, userCon);
            userCon.gridx = 5;
            newUserPanel.add(billAdminApartmentLabel, userCon);
            userCon.gridx = 6;
            newUserPanel.add(billAdminApartmentTF, userCon);

            userCon.gridy = 17;
            userCon.gridx = 1;
            userCon.gridwidth = 1;
            newUserPanel.add(billAdminCityLabel, userCon);
            userCon.gridx = 4;
            newUserPanel.add(billAdminProvinceLabel, userCon);
            userCon.gridx = 2;
            userCon.gridwidth = 2;
            newUserPanel.add(billAdminCityTF, userCon);
            userCon.gridx = 5;
            newUserPanel.add(billAdminProvinceCB, userCon);

            userCon.gridy = 18;
            userCon.gridx = 1;
            userCon.gridwidth = 1;
            newUserPanel.add(billAdminCountryLabel, userCon);
            userCon.gridx = 4;
            newUserPanel.add(billAdminPostalCodeLabel, userCon);
            userCon.gridx = 2;
            userCon.gridwidth = 2;
            newUserPanel.add(billAdminCountryTF, userCon);
            userCon.gridx = 5;
            newUserPanel.add(billAdminPostalCodeTF, userCon);

            userCon.gridy = 19; // shift everything to the top
            userCon.gridx = 0;
            userCon.weighty = 1.0;
            userCon.gridwidth = 8;
            userCon.anchor = GridBagConstraints.CENTER;
            userCon.fill = GridBagConstraints.BOTH;
            Component glue = Box.createVerticalGlue();
            newUserPanel.add(glue, userCon);
        }

        return newUserPanel;
    }

    /**
     * Creates the "Edit Book" tab for the "Edit Stuff" tab of the adminView
     * The idea is for the JTextFields to populate with the current data for the book after "Search Books" is pressed
     * then update the data with the contents of the JTextFields after "Update Book" is pressed
     * The currentISBNLabel should populate with the found book's ISBN
     *
     * @return editBookPanel for adminView
     */
    private JPanel editBook() {
        JPanel editBookPanel = new JPanel(new GridBagLayout());

        /* JButtons */
        JButton updateBookButton = new JButton("Update Book");
        JButton logoutButton = new JButton("Logout");
        JButton searchBookButton = new JButton("Search Books");

        /* JLabel */
        JLabel editBookLabel = new JLabel("Edit Book Information (required fields indicated by *): "),
                searchISBNLabel = new JLabel("Search ISBNs"),
                editISBNLabel = new JLabel("Book ISBN: ", JLabel.RIGHT),
                editBookTitleLabel = new JLabel("*Title: "),
                editBookVersionLabel = new JLabel("Version: "),
                editBookGenreLabel = new JLabel("Genre: "),
                editBookNumPagesLabel = new JLabel("Pg Count: ", JLabel.RIGHT),
                editBookPriceLabel = new JLabel("*Price:"),
                editBookRoyaltyLabel = new JLabel("*Royalty (%): ", JLabel.RIGHT),
                editBookStockLabel = new JLabel("Stock:", JLabel.RIGHT),
                editBookAuthorLabel = new JLabel("Author: "),
                editBookAuthorFNLabel = new JLabel("First Name: "),
                editBookAuthorLNLabel = new JLabel("Last Name: ", JLabel.RIGHT),
                editBookPublisherLabel = new JLabel("Publisher (be sure to add new publishers before editing books): "),
                editBookYearLabel = new JLabel("Year: "),
                accountingLabel = new JLabel("*Accounting: ");

        /* ActionListeners */
        logoutButton.addActionListener(this);
        updateBookButton.addActionListener(this);
        searchBookButton.addActionListener(this);

        // Setup editBookPanel
        {
            GridBagConstraints con = new GridBagConstraints();
            Dimension spacer = new Dimension(15, 15);
            con.gridx = 0;
            con.gridy = 0;
            editBookPanel.add(logoutButton, con);
            con.gridx = 1;
            con.gridwidth = 4;
            con.anchor = GridBagConstraints.CENTER;
            editBookErrorLabel.setForeground(Color.red);
            editBookPanel.add(editBookErrorLabel, con);
            con.gridx = 5;
            con.gridwidth = 1;
            con.anchor = GridBagConstraints.FIRST_LINE_END;
            editBookPanel.add(updateBookButton, con);

            con.gridy = 1;
            con.gridx = 1;
            con.gridwidth = 1;
            con.weightx = 1.0;
            con.anchor = GridBagConstraints.LINE_END;
            con.fill = GridBagConstraints.HORIZONTAL;
            editBookPanel.add(searchISBNLabel, con);
            con.gridwidth = 2;
            con.gridx = 2;
            editBookPanel.add(editBookSearchTF, con);
            con.gridwidth = 1;
            con.gridx = 4;
            editBookPanel.add(searchBookButton, con);
            con.gridx = 5;
            editBookPanel.add(confirmBookEditLabel, con);

            con.gridy = 2;
            con.gridx = 1;
            editBookPanel.add(Box.createRigidArea(spacer), con);

            con.gridy = 3;
            con.gridx = 1;
            con.gridwidth = 3;
            con.anchor = GridBagConstraints.LINE_START;
            editBookPanel.add(editBookLabel, con);

            con.gridy = 4;
            con.gridx = 1;
            con.gridwidth = 1;
            editBookPanel.add(editBookTitleLabel, con);
            con.gridx = 2;
            editBookPanel.add(editBookTitleTF, con);
            con.gridx = 3;
            editBookPanel.add(editISBNLabel, con);
            con.gridx = 4;
            editBookPanel.add(currentISBNLabel, con);

            con.gridy = 5;
            con.gridx = 1;
            editBookPanel.add(editBookVersionLabel, con);
            con.gridx = 2;
            editBookPanel.add(editBookVersionTF, con);
            con.gridx = 3;
            editBookPanel.add(editBookNumPagesLabel, con);
            con.gridx = 4;
            editBookPanel.add(editBookNumPagesTF, con);

            con.gridy = 6;
            con.gridx = 1;
            editBookPanel.add(editBookGenreLabel, con);
            con.gridx = 2;
            editBookPanel.add(editBookGenreTF, con);
            con.gridx = 3;
            editBookPanel.add(editBookStockLabel, con);
            con.gridx = 4;
            editBookPanel.add(editBookStockTF, con);

            con.gridy = 7;
            con.gridx = 1;
            editBookPanel.add(editBookYearLabel, con);
            con.gridx = 2;
            editBookPanel.add(editBookYearTF, con);

            con.gridy = 8;
            con.gridx = 1;
            editBookPanel.add(Box.createRigidArea(spacer), con);

            con.gridy = 9;
            con.gridx = 1;
            editBookPanel.add(accountingLabel, con);

            con.gridy = 10;
            con.gridx = 1;
            editBookPanel.add(editBookPriceLabel, con);
            con.gridx = 2;
            editBookPanel.add(editBookPriceTF, con);
            con.gridx = 3;
            editBookPanel.add(editBookRoyaltyLabel, con);
            con.gridx = 4;
            editBookPanel.add(editBookRoyaltyTF, con);

            con.gridy = 11;
            con.gridx = 1;
            editBookPanel.add(Box.createRigidArea(spacer), con);

            con.gridy = 12;
            con.gridx = 1;
            editBookPanel.add(editBookAuthorLabel, con);

            con.gridy = 13;
            con.gridx = 1;
            editBookPanel.add(editBookAuthorFNLabel, con);
            con.gridx = 2;
            editBookPanel.add(editBookAuthorFNTF, con);
            con.gridx = 3;
            editBookPanel.add(editBookAuthorLNLabel, con);
            con.gridx = 4;
            editBookPanel.add(editBookAuthorLNTF, con);

            con.gridy = 14;
            con.gridx = 1;
            editBookPanel.add(Box.createRigidArea(spacer), con);

            con.gridy = 15;
            con.gridwidth = 4;
            editBookPanel.add(editBookPublisherLabel, con);
            con.gridx = 2;

            con.gridy = 16;
            con.gridwidth = 3;
            con.fill = GridBagConstraints.HORIZONTAL;
            editBookPanel.add(editBookPublisherTF, con);

            con.gridy = 17; // shift everything to the top
            con.gridx = 0;
            con.weighty = 1.0;
            con.gridwidth = 6;
            con.anchor = GridBagConstraints.CENTER;
            con.fill = GridBagConstraints.BOTH;
            Component glue = Box.createVerticalGlue();
            editBookPanel.add(glue, con);
        }

        return editBookPanel;
    }

    /**
     * Creates the "Edit User" tab for the "Edit Stuff" tab of the adminView
     * The idea is for the JTextFields to populate with the current data for the user after "Search Users" is pressed
     * then update the data with the contents of the JTextFields after "Update User" is pressed
     *
     * @return editUserPanel for adminView
     */
    private JPanel editUser() {
        JPanel editUserPanel = new JPanel(new GridBagLayout());

        /* JButton */
        JButton logoutButton = new JButton("Logout"),
                confirmButton = new JButton("Update User"),
                searchButton = new JButton("Search Users");

        /* JLabels */
        // Search
        JLabel searchUserLabel = new JLabel("Search Username:"),
                editUserLabel = new JLabel("Edit:"),
                // User info
                editUserLoginCredLabel = new JLabel("Login Credentials: "),
                editUserDetailLabel = new JLabel("User Details: "),
                editUsernameLabel = new JLabel("Username: "),
                editPasswordLabel = new JLabel("New Password: "),
                editConfirmPasswordLabel = new JLabel("Confirm Password: ", JLabel.RIGHT),
                editFirstNameLabel = new JLabel("*First Name: "),
                editLastNameLabel = new JLabel("*Last Name: ", JLabel.RIGHT),
                editEmailLabel = new JLabel("*Email: "),
                editSalaryLabel = new JLabel("Salary: ", JLabel.RIGHT),
                // Admin shipping address info (can be EMPTY)
                editShippingAddressLabel = new JLabel("Shipping Address"),
                editShippingStreetNumLabel = new JLabel("*Street Number: "),
                editShippingStreetNameLabel = new JLabel("*Street Name: ", JLabel.RIGHT),
                editShippingApartmentLabel = new JLabel("*Apartment: ", JLabel.RIGHT),
                editShippingCityLabel = new JLabel("*City: "),
                editShippingProvinceLabel = new JLabel("*Province: ", JLabel.RIGHT),
                editShippingCountryLabel = new JLabel("*Country: "),
                editShippingPostalCodeLabel = new JLabel("*Postal Code: ", JLabel.RIGHT),
                // Admin billing address info
                editBillingAddressLabel = new JLabel("Billing Address"),
                editBillStreetNumLabel = new JLabel("Street Number: "),
                editBillStreetNameLabel = new JLabel("Street Name: ", JLabel.RIGHT),
                editBillApartmentLabel = new JLabel("Apartment: ", JLabel.RIGHT),
                editBillCityLabel = new JLabel("City: "),
                editBillProvinceLabel = new JLabel("Province: ", JLabel.RIGHT),
                editBillCountryLabel = new JLabel("Country: "),
                editBillPostalCodeLabel = new JLabel("Postal Code: ", JLabel.RIGHT);

        /* JCheckBoxes */
        editBillingSameAsShipping.setSelected(true); // selected by default

        /* ActionListeners */
        logoutButton.addActionListener(this);
        confirmButton.addActionListener(this);
        searchButton.addActionListener(this);
        editBillingSameAsShipping.addChangeListener(e -> {
            boolean sameAsBilling = !editBillingSameAsShipping.isSelected();
            editBillStreetNumTF.setEnabled(sameAsBilling);
            editBillStreetNameTF.setEnabled(sameAsBilling);
            editBillApartmentTF.setEnabled(sameAsBilling);
            editBillCityTF.setEnabled(sameAsBilling);
            editBillProvinceCB.setEnabled(sameAsBilling);
            editBillCountryTF.setEnabled(sameAsBilling);
            editBillPostalCodeTF.setEnabled(sameAsBilling);
        });
        isUserAdminCB.addChangeListener(e -> {
            boolean admin = isUserAdminCB.isSelected();
            editSalaryTF.setEnabled(admin);
            if (admin) {
                editSalaryLabel.setText("*Salary: ");
                editShippingStreetNumLabel.setText("Street Number: ");
                editShippingStreetNameLabel.setText("Street Name: ");
                editShippingApartmentLabel.setText("Apartment: ");
                editShippingCityLabel.setText("City: ");
                editShippingProvinceLabel.setText("Province: ");
                editShippingCountryLabel.setText("Country: ");
                editShippingPostalCodeLabel.setText("Postal Code: ");
            } else {
                editSalaryLabel.setText("Salary: ");
                editShippingStreetNumLabel.setText("*Street Number: ");
                editShippingStreetNameLabel.setText("*Street Name: ");
                editShippingApartmentLabel.setText("*Apartment: ");
                editShippingCityLabel.setText("*City: ");
                editShippingProvinceLabel.setText("*Province: ");
                editShippingCountryLabel.setText("*Country: ");
                editShippingPostalCodeLabel.setText("*Postal Code: ");
            }
        });

        // Setup editUserPanel
        {
            // Disable fields by default
            boolean enable = false;
            editBillStreetNumTF.setEnabled(enable);
            editBillStreetNameTF.setEnabled(enable);
            editBillApartmentTF.setEnabled(enable);
            editBillCityTF.setEnabled(enable);
            editBillProvinceCB.setEnabled(enable);
            editBillCountryTF.setEnabled(enable);
            editBillPostalCodeTF.setEnabled(enable);
            editPasswordTF.setEnabled(enable);
            confirmEditPasswordTF.setEnabled(enable);
            editFirstNameTF.setEnabled(enable);
            editLastNameTF.setEnabled(enable);
            editEmailTF.setEnabled(enable);
            editSalaryTF.setEnabled(enable);
            editShippingStreetNumTF.setEnabled(enable);
            editShippingStreetNameTF.setEnabled(enable);
            editShippingApartmentTF.setEnabled(enable);
            editShippingCityTF.setEnabled(enable);
            editShippingCountryTF.setEnabled(enable);
            editShippingPostalCodeTF.setEnabled(enable);
            isUserAdminCB.setEnabled(enable);
            editBillingSameAsShipping.setEnabled(enable);
            editShippingProvinceCB.setEnabled(enable);

            GridBagConstraints con = new GridBagConstraints();
            Dimension spacer = new Dimension(35, 35);

            con.gridy = 0;
            con.gridx = 0;
            con.anchor = GridBagConstraints.FIRST_LINE_START;
            editUserPanel.add(logoutButton, con);
            con.gridx = 0;
            con.gridwidth = 8;
            con.anchor = GridBagConstraints.CENTER;
            editUserErrorLabel.setForeground(Color.red);
            editUserPanel.add(editUserErrorLabel, con);
            con.gridx = 7;
            con.gridwidth = 2;
            con.anchor = GridBagConstraints.FIRST_LINE_END;
            editUserPanel.add(confirmButton, con);

            con.gridy = 1;
            con.gridx = 1;
            con.gridwidth = 1;
            con.weightx = 1.0;
            con.anchor = GridBagConstraints.LINE_END;
            con.fill = GridBagConstraints.HORIZONTAL;
            editUserPanel.add(searchUserLabel, con);
            con.gridwidth = 2;
            con.gridx = 2;
            editUserPanel.add(editUserSearchTF, con);
            con.gridwidth = 1;
            con.gridx = 4;
            editUserPanel.add(searchButton, con);
            con.gridx = 7;
            editUserPanel.add(confirmUserEditLabel, con);

            con.gridy = 2;
            con.gridx = 1;
            editUserPanel.add(Box.createRigidArea(spacer), con);

            con.gridy = 3;
            editUserPanel.add(editUserLabel, con);

            con.gridy = 4;
            con.gridx = 1;
            con.gridwidth = 6;
            con.anchor = GridBagConstraints.LINE_END;
            con.fill = GridBagConstraints.HORIZONTAL;
            editUserPanel.add(editUserLoginCredLabel, con);

            con.gridy = 5;
            con.gridx = 1;
            editUserPanel.add(editUsernameLabel, con);
            con.gridx = 4;
            con.gridwidth = 2;
            editUserPanel.add(isUserAdminCB, con);
            con.gridx = 2;
            editUserPanel.add(currentUserNameLabel, con);

            con.gridy = 6;
            con.gridx = 1;
            con.gridwidth = 1;
            editUserPanel.add(editPasswordLabel, con);
            con.gridx = 4;
            editUserPanel.add(editConfirmPasswordLabel, con);
            con.gridx = 2;
            con.gridwidth = 2;
            editUserPanel.add(editPasswordTF, con);
            con.gridx = 5;
            editUserPanel.add(confirmEditPasswordTF, con);

            con.gridy = 7;
            con.gridx = 1;
            editUserPanel.add(Box.createRigidArea(spacer), con);

            con.gridy = 8;
            con.gridx = 1;
            con.gridwidth = 3;
            editUserPanel.add(editUserDetailLabel, con);

            con.gridy = 9;
            con.gridx = 1;
            con.gridwidth = 1;
            editUserPanel.add(editFirstNameLabel, con);
            con.gridx = 4;
            editUserPanel.add(editLastNameLabel, con);
            con.gridx = 2;
            con.gridwidth = 2;
            editUserPanel.add(editFirstNameTF, con);
            con.gridx = 5;
            editUserPanel.add(editLastNameTF, con);

            con.gridy = 10;
            con.gridx = 1;
            con.gridwidth = 1;
            editUserPanel.add(editEmailLabel, con);
            con.gridx = 4;
            editUserPanel.add(editSalaryLabel, con);
            con.gridx = 2;
            con.gridwidth = 2;
            editUserPanel.add(editEmailTF, con);
            con.gridx = 5;
            editUserPanel.add(editSalaryTF, con);

            con.gridy = 11;
            con.gridx = 1;
            editUserPanel.add(Box.createRigidArea(spacer), con);

            con.gridy = 12;
            con.gridx = 1;
            con.gridwidth = 3;
            editUserPanel.add(new JLabel("Address Information"), con);

            con.gridy = 13;
            con.gridx = 1;
            con.gridwidth = 3;
            editUserPanel.add(editShippingAddressLabel, con);

            con.gridy = 14;
            con.gridx = 1;
            con.gridwidth = 1;
            editUserPanel.add(editShippingStreetNumLabel, con);
            con.gridx = 2;
            editUserPanel.add(editShippingStreetNumTF, con);
            con.gridx = 3;
            editUserPanel.add(editShippingStreetNameLabel, con);
            con.gridx = 4;
            editUserPanel.add(editShippingStreetNameTF, con);
            con.gridx = 5;
            editUserPanel.add(editShippingApartmentLabel, con);
            con.gridx = 6;
            editUserPanel.add(editShippingApartmentTF, con);

            con.gridy = 15;
            con.gridx = 1;
            con.gridwidth = 1;
            editUserPanel.add(editShippingCityLabel, con);
            con.gridx = 4;
            editUserPanel.add(editShippingProvinceLabel, con);
            con.gridx = 2;
            con.gridwidth = 2;
            editUserPanel.add(editShippingCityTF, con);
            con.gridx = 5;
            editUserPanel.add(editShippingProvinceCB, con);

            con.gridy = 16;
            con.gridx = 1;
            con.gridwidth = 1;
            editUserPanel.add(editShippingCountryLabel, con);
            con.gridx = 4;
            editUserPanel.add(editShippingPostalCodeLabel, con);
            con.gridx = 2;
            con.gridwidth = 2;
            editUserPanel.add(editShippingCountryTF, con);
            con.gridx = 5;
            editUserPanel.add(editShippingPostalCodeTF, con);

            con.gridy = 17;
            con.gridx = 1;
            editUserPanel.add(Box.createRigidArea(spacer), con);

            con.gridy = 18;
            con.gridx = 1;
            con.gridwidth = 2;
            editUserPanel.add(editBillingAddressLabel, con);
            con.gridx = 3;
            con.gridwidth = 4;
            editUserPanel.add(editBillingSameAsShipping, con);

            con.gridy = 19;
            con.gridx = 1;
            con.gridwidth = 1;
            editUserPanel.add(editBillStreetNumLabel, con);
            con.gridx = 2;
            editUserPanel.add(editBillStreetNumTF, con);
            con.gridx = 3;
            editUserPanel.add(editBillStreetNameLabel, con);
            con.gridx = 4;
            editUserPanel.add(editBillStreetNameTF, con);
            con.gridx = 5;
            editUserPanel.add(editBillApartmentLabel, con);
            con.gridx = 6;
            editUserPanel.add(editBillApartmentTF, con);

            con.gridy = 20;
            con.gridx = 1;
            con.gridwidth = 1;
            editUserPanel.add(editBillCityLabel, con);
            con.gridx = 4;
            editUserPanel.add(editBillProvinceLabel, con);
            con.gridx = 2;
            con.gridwidth = 2;
            editUserPanel.add(editBillCityTF, con);
            con.gridx = 5;
            editUserPanel.add(editBillProvinceCB, con);

            con.gridy = 21;
            con.gridx = 1;
            con.gridwidth = 1;
            editUserPanel.add(editBillCountryLabel, con);
            con.gridx = 4;
            editUserPanel.add(editBillPostalCodeLabel, con);
            con.gridx = 2;
            con.gridwidth = 2;
            editUserPanel.add(editBillCountryTF, con);
            con.gridx = 5;
            editUserPanel.add(editBillPostalCodeTF, con);

            con.gridy = 22; // shift everything to the top
            con.gridx = 0;
            con.weighty = 1.0;
            con.gridwidth = 8;
            con.anchor = GridBagConstraints.CENTER;
            con.fill = GridBagConstraints.BOTH;
            Component glue = Box.createVerticalGlue();
            editUserPanel.add(glue, con);

            return editUserPanel;
        }
    }

    /**
     * Creates the "Reports" tab for the adminScreen
     * TODO: reportPanel()
     *
     * @return reportPanel for adminView
     */
    private JPanel reportPanel() {
        JPanel generateReportPanel = new JPanel(new GridBagLayout());
        JScrollPane reportContainer = new JScrollPane();

        /* JButtons */
        JButton generateReport = new JButton("Generate Report");
        JButton logout = new JButton("Logout");



        /* JLabels */

        /* ActionListeners */

        return new JPanel();
    }

    /**
     * Confirms that the user wishes to logout
     */
    private void confirmLogout() {
        JButton logoutButton = new JButton("Logout");
        JButton cancelButton = new JButton("Cancel");

        Object[] options = {logoutButton, cancelButton};
        final JOptionPane areYouSure = new JOptionPane("Are you sure you want to logout?", JOptionPane.PLAIN_MESSAGE, JOptionPane.YES_NO_OPTION, null, options, options[1]);
        areYouSure.setIcon(FrontEndUtilities.WINDOW_ICON);
        final JDialog dialog = areYouSure.createDialog("Logout");
        dialog.setContentPane(areYouSure);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        logoutButton.addActionListener(e -> {
            loginScreen();
            dialog.setVisible(false);
        });
        cancelButton.addActionListener(e -> dialog.setVisible(false));

        dialog.pack();
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }

    /**
     * Confirms the admin wants to exit the admin view
     */
    private void confirmViewSwitch() {
        JButton cancelButton = new JButton("Cancel");
        JButton userButton = new JButton("Customer View");

        Object[] options = {userButton, cancelButton};
        final JOptionPane screenChoice = new JOptionPane("Are you sure you wish to change views?\nYou cannot switch back without logging out.", JOptionPane.PLAIN_MESSAGE, JOptionPane.YES_NO_OPTION, null, options, options[1]);
        screenChoice.setIcon(FrontEndUtilities.WINDOW_ICON);
        final JDialog dialog = screenChoice.createDialog("Admin");
        dialog.setContentPane(screenChoice);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        userButton.addActionListener(e -> {
            userScreen();
            dialog.setVisible(false);
        });
        cancelButton.addActionListener(e -> dialog.setVisible(false));

        dialog.pack();
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }

    /**
     * Populates edit user fields with appropriate data from database
     * and correctly adjusts checkboxes
     */
    private void fetchEditUserData() {
        ArrayList<Object> update = LookForaBook.lookForaUser(editUserSearchTF.getText());
        if(editUserSearchTF.getText().isEmpty()){
            editUserErrorLabel.setText("Enter a username before searching");
        }else if(update == null || update.size() == 0){
            editUserErrorLabel.setText("User Not Found");
        } else if (update.get(0).equals("-1")){
            editUserErrorLabel.setText("Big boy error...contact someone");
        } else {
            editUserErrorLabel.setText(""); // reset errors
            editUserSearchTF.setText(""); // clear search bar
            // update fields
            currentUserNameLabel.setText((String)update.get(0));
            editFirstNameTF.setText((String)update.get(2));
            editLastNameTF.setText((String)update.get(3));
            editEmailTF.setText((String)update.get(4));
            editSalaryTF.setText("$" + update.get(5));
            if(editSalaryTF.getText().equals("$null")) {
                editSalaryTF.setText("");
                editSalaryTF.setEnabled(false);
                isUserAdminCB.setSelected(false);
            } else {
                editSalaryTF.setEnabled(true);
                isUserAdminCB.setSelected(true);
            }

            editShippingStreetNumTF.setText((String)((ArrayList)update.get(6)).get(0));
            editShippingStreetNameTF.setText((String)((ArrayList)update.get(6)).get(1));
            editShippingApartmentTF.setText((String)((ArrayList)update.get(6)).get(2));
            editShippingCityTF.setText((String)((ArrayList)update.get(6)).get(3));
            editShippingProvinceCB.setSelectedItem(((ArrayList)update.get(6)).get(4));
            editShippingCountryTF.setText((String)((ArrayList)update.get(6)).get(5));
            editShippingPostalCodeTF.setText((String)((ArrayList)update.get(6)).get(6));

            boolean isShipping = (boolean)((ArrayList)update.get(6)).get(7);
            boolean isBilling= (boolean)((ArrayList)update.get(6)).get(8);

            if(isShipping && isBilling){ // shipping == billing, user can only have 1 shipping/ billing address
                editBillingSameAsShipping.setSelected(true);
                editBillStreetNumTF.setText("");
                editBillStreetNameTF.setText("");
                editBillApartmentTF.setText("");
                editBillCityTF.setText("");
                editBillProvinceCB.setSelectedItem("");
                editBillCountryTF.setText("");
                editBillPostalCodeTF.setText("");
            } else {
                editBillingSameAsShipping.setSelected(false);

                editBillStreetNumTF.setText((String)((ArrayList)update.get(7)).get(0));
                editBillStreetNameTF.setText((String)((ArrayList)update.get(7)).get(1));
                editBillApartmentTF.setText((String)((ArrayList)update.get(7)).get(2));
                editBillCityTF.setText((String)((ArrayList)update.get(7)).get(3));
                editBillProvinceCB.setSelectedItem(((ArrayList)update.get(6)).get(4));
                editBillCountryTF.setText((String)((ArrayList)update.get(7)).get(5));
                editBillPostalCodeTF.setText((String)((ArrayList)update.get(7)).get(6));
            }

            // Enable fields after search
            boolean enable = true;
            editPasswordTF.setEnabled(enable);
            confirmEditPasswordTF.setEnabled(enable);
            editFirstNameTF.setEnabled(enable);
            editLastNameTF.setEnabled(enable);
            editEmailTF.setEnabled(enable);
            editShippingStreetNumTF.setEnabled(enable);
            editShippingStreetNameTF.setEnabled(enable);
            editShippingApartmentTF.setEnabled(enable);
            editShippingCityTF.setEnabled(enable);
            editShippingCountryTF.setEnabled(enable);
            editShippingPostalCodeTF.setEnabled(enable);
            isUserAdminCB.setEnabled(enable);
            editBillingSameAsShipping.setEnabled(enable);
            editShippingProvinceCB.setEnabled(enable);
        }
    }

    /**
     * Sends edit user fields with appropriate data to the database
     * Updates user about success
     * Clears fields if successful
     */
    private void sendEditUserData(){
    // Check to see if the password matches the confirm password textfield.
        if (!(new String(confirmEditPasswordTF.getPassword()).equals(new String(editPasswordTF.getPassword())))) {
            editUserErrorLabel.setText("Update Failed. New passwords do not match.");
        }
    }

    /**
     * Implements ActionListeners for GUI components
     *
     * @param e The ActionEvent that was triggered via a JButton.
     */
    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();


        if (o instanceof JButton) {
            switch (((JButton) o).getText()) {
                case "Logout" -> confirmLogout(); // Anywhere and everywhere
                case "Order Lookup" -> lookupOrderScreen(); // Login Screen
                case "Proceed to Login" -> loginScreen(); // Registration screen
                case "+" -> System.out.println("Item Added"); // User screen
                case "-" -> System.out.println("Item removed"); // User screen
                case "Checkout" -> checkoutScreen(); // User Screen
                case "Search" -> System.out.println("Searching Books"); // User screen
                case "Search Users" -> fetchEditUserData(); // Admin Edit User Screen
                case "Update User" -> {defaultAdminViewFields(); sendEditUserData();} // Admin Edit User Screen
                case "Search Books" -> System.out.println("Searching Books"); // Admin Edit Books Screen
                case "Update Book" -> {defaultAdminViewFields(); confirmBookEditLabel.setText("Book Updated");} // Admin Edit Books Screen
                case "Add Book" -> {defaultAdminViewFields(); confirmNewBookAddition.setText("New Book Added");}// Admin Add Book Screen
                case "Add Publisher" -> {defaultAdminViewFields(); confirmNewPublisherAddition.setText("New Publisher Added");} // Admin Add Publisher Screen
                case "Add User" -> {defaultAdminViewFields(); confirmAdminReg.setText("New User Added");} // Admin Add User Screen
                case "Cancel Checkout" -> userScreen(); // checkoutScreen
                case "Confirm Order" -> System.out.println("Order Submitted"); // checkoutScreen
                case "Cancel Lookup" -> loginScreen();
                case "Lookup Order" -> System.out.println("Looking up order");
                default -> System.out.println("Error");
            }
        }

        if (o instanceof JMenuItem) {
            switch (((JMenuItem) o).getText()) {
                case "Logout" -> confirmLogout(); // Anywhere and everywhere
                case "Switch to User View" -> confirmViewSwitch();
                default -> System.out.println("Error");
            }
        }
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        Object o = e.getSource();

        if (o instanceof JTabbedPane){
            defaultAdminViewFields();
        }

    }
}