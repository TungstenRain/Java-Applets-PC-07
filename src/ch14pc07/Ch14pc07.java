package ch14pc07;
import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;
import java.awt.event.*;

/**
 * Applet that displays a vending machine to select a soda while keeping track
 * of the amount and cost
 * 
 * @author frank
 */
public class Ch14pc07 extends JApplet {
    // Variables
    private Container container;
    private JPanel panelDrinks, panelMoney, panelPurchased;
    
    private JButton buttonCola, buttonLemonLime, buttonGrape, buttonRootBeer, 
            buttonWater, buttonAddCredits, buttonReturnCredits, buttonCollectCredits;
    private JTextField textfieldCredits, textfieldAddCredits, textfieldChange,
            textfieldCola, textfieldLemonLime, textfieldGrape, textfieldRootBeer,
            textfieldWater;
    private JLabel labelAvailableCredits, labelAddCredits, labelCola,
            labelLemonLime, labelGrape, labelRootBeer, labelWater;
    
    private DecimalFormat creditFormat = new DecimalFormat("#0.00");
    private double credits = 0.0;
    private double change = 0.0;
    private final double drinkPrice = 0.75;
    private int[] remainingDrinks, drinksPurchased;
    
    /**
     * Initialize the applet
     */
    public void init() {
        
        container = getContentPane();
        container.setLayout(new BorderLayout());
        
        // Instantiate and initialize remainingDrinks
        remainingDrinks = new int[]{20, 20, 20, 20, 20};
        drinksPurchased = new int[]{0, 0, 0, 0, 0};
        
        // Call the methods to build the panels
        buildDrinksPanel();
        buildMoneyPanel();
        
        // Add panels to container
        container.add(panelDrinks, BorderLayout.NORTH);
        container.add(panelMoney, BorderLayout.SOUTH);
    }
    
    /**
     * Build the drinks panel
     */
    private void buildDrinksPanel() {
        // Instantiate the drinks panel
        panelDrinks = new JPanel();
        panelDrinks.setLayout(new GridLayout(1, 5));
        panelDrinks.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panelDrinks.setBackground(Color.ORANGE);
        
        // Instantiate buttons
        buttonCola = new JButton("Cola");
        buttonLemonLime = new JButton("Lemon-Lime");
        buttonGrape = new JButton("Grape");
        buttonRootBeer = new JButton("Root Beer");
        buttonWater = new JButton("Bottled Water");
        
        // Add action listener
        buttonCola.addActionListener(new DrinkButtonListener());
        buttonLemonLime.addActionListener(new DrinkButtonListener());
        buttonGrape.addActionListener(new DrinkButtonListener());
        buttonRootBeer.addActionListener(new DrinkButtonListener());
        buttonWater.addActionListener(new DrinkButtonListener());
        
        // Add buttons to panel
        panelDrinks.add(buttonCola);
        panelDrinks.add(buttonLemonLime);
        panelDrinks.add(buttonGrape);
        panelDrinks.add(buttonRootBeer);
        panelDrinks.add(buttonWater);
    }
    
    /**
     * Build the left panel
     */
    private void buildMoneyPanel() {
        // Instantiate panels
        panelMoney = new JPanel();
        panelMoney.setLayout(new GridLayout(9, 1));
        panelPurchased = new JPanel();
        panelPurchased.setLayout(new GridLayout(2, 5));
        
        // Instantiate textfields
        textfieldCredits = new JTextField(10);
        textfieldCredits.setEditable(false);
        textfieldCredits.setHorizontalAlignment(SwingConstants.CENTER);
        textfieldCredits.setText(creditFormat.format(0.0));
        
        textfieldAddCredits = new JTextField(10);
        textfieldAddCredits.setEditable(true);
        textfieldAddCredits.setHorizontalAlignment(SwingConstants.CENTER);
        textfieldAddCredits.setText(creditFormat.format(0.0));
        
        textfieldChange = new JTextField(10);
        textfieldChange.setEditable(false);
        textfieldChange.setHorizontalAlignment(SwingConstants.CENTER);
        textfieldChange.setText(creditFormat.format(0.0));
        
        textfieldCola = new JTextField(10);
        textfieldCola.setEditable(false);
        textfieldCola.setHorizontalAlignment(SwingConstants.CENTER);
        textfieldCola.setText("0");
        
        textfieldLemonLime = new JTextField(10);
        textfieldLemonLime.setEditable(false);
        textfieldLemonLime.setHorizontalAlignment(SwingConstants.CENTER);
        textfieldLemonLime.setText("0");
        
        textfieldGrape = new JTextField(10);
        textfieldGrape.setEditable(false);
        textfieldGrape.setHorizontalAlignment(SwingConstants.CENTER);
        textfieldGrape.setText("0");
        
        textfieldRootBeer = new JTextField(10);
        textfieldRootBeer.setEditable(false);
        textfieldRootBeer.setHorizontalAlignment(SwingConstants.CENTER);
        textfieldRootBeer.setText("0");
        
        textfieldWater = new JTextField(10);
        textfieldWater.setEditable(false);
        textfieldWater.setHorizontalAlignment(SwingConstants.CENTER);
        textfieldWater.setText("0");
        
        // Instantiate labels
        labelAvailableCredits = new JLabel("Available Credits", SwingConstants.CENTER);
        labelAddCredits = new JLabel("Add Credits Below", SwingConstants.CENTER);
        labelCola = new JLabel("Cola Purchased:", SwingConstants.CENTER);
        labelLemonLime = new JLabel("Lemon-Lime Purchased:", SwingConstants.CENTER);
        labelGrape = new JLabel("Grape Purchased:", SwingConstants.CENTER);
        labelRootBeer = new JLabel("Root Beer Purchased:", SwingConstants.CENTER);
        labelWater = new JLabel("Water Purchased:", SwingConstants.CENTER);
        
        // Instantiate buttons and add action listeners
        buttonAddCredits = new JButton("Add Credits");
        buttonAddCredits.addActionListener(new AddCreditsListener());
        buttonReturnCredits = new JButton("Return Credits");
        buttonReturnCredits.addActionListener(new ReturnCreditsListener());
        buttonCollectCredits = new JButton("Collect Credits");
        buttonCollectCredits.addActionListener(new CollectCreditsListener());
        
        // Add components to purchased panel
        panelPurchased.add(labelCola);
        panelPurchased.add(labelLemonLime);
        panelPurchased.add(labelGrape);
        panelPurchased.add(labelRootBeer);
        panelPurchased.add(labelWater);
        panelPurchased.add(textfieldCola);
        panelPurchased.add(textfieldLemonLime);
        panelPurchased.add(textfieldGrape);
        panelPurchased.add(textfieldRootBeer);
        panelPurchased.add(textfieldWater);
        
        // Add components to money panel
        panelMoney.add(labelAvailableCredits);
        panelMoney.add(textfieldCredits);
        panelMoney.add(labelAddCredits);
        panelMoney.add(textfieldAddCredits);
        panelMoney.add(buttonAddCredits);
        panelMoney.add(buttonReturnCredits);
        panelMoney.add(textfieldChange);
        panelMoney.add(buttonCollectCredits);
        panelMoney.add(panelPurchased);
    }
    
    // Private inner classes
    /**
     * Private inner class to catch events when one of the drink buttons are clicked
     */
    private class DrinkButtonListener implements ActionListener {
        
        /**
         * The event when the user clicks a drink button
         * @param e ActionEvent The event
         */
        public void actionPerformed(ActionEvent e) {
            // If the user has inserted more than 0.75 credits
            if(credits >= drinkPrice) {
                if (e.getSource() == buttonCola && remainingDrinks[0] > 0)
                {
                    credits -= drinkPrice;
                    remainingDrinks[0]--;
                    textfieldCredits.setText(creditFormat.format(credits));
                    drinksPurchased[0]++;
                    textfieldCola.setText(Integer.toString(drinksPurchased[0]));

                    if(remainingDrinks[0] == 0)
                        buttonCola.setEnabled(false);
                }
                else if (e.getSource() == buttonLemonLime && remainingDrinks[1] > 0)
                {
                    credits -= drinkPrice;
                    remainingDrinks[1]--;
                    textfieldCredits.setText(creditFormat.format(credits));
                    drinksPurchased[1]++;
                    textfieldLemonLime.setText(Integer.toString(drinksPurchased[1]));
                    
                    if(remainingDrinks[1] == 0)
                        buttonLemonLime.setEnabled(false);
                }
                else if (e.getSource() == buttonGrape && remainingDrinks[2] > 0)
                {
                    credits -= drinkPrice;
                    remainingDrinks[2]--;
                    textfieldCredits.setText(creditFormat.format(credits));
                    drinksPurchased[2]++;
                    textfieldGrape.setText(Integer.toString(drinksPurchased[2]));

                    if(remainingDrinks[2] == 0)
                        buttonGrape.setEnabled(false);
                }
                else if (e.getSource() == buttonRootBeer && remainingDrinks[3] > 0)
                {
                    credits -= drinkPrice;
                    remainingDrinks[3]--;
                    textfieldCredits.setText(creditFormat.format(credits));
                    drinksPurchased[3]++;
                    textfieldRootBeer.setText(Integer.toString(drinksPurchased[3]));
                    
                    if(remainingDrinks[3] == 0)
                        buttonRootBeer.setEnabled(false);
                }
                else if (e.getSource() == buttonWater && remainingDrinks[4] > 0)
                {
                    credits -= drinkPrice;
                    remainingDrinks[4]--;
                    textfieldCredits.setText(creditFormat.format(credits));
                    drinksPurchased[4]++;
                    textfieldWater.setText(Integer.toString(drinksPurchased[4]));
                    
                    if(remainingDrinks[4] == 0)
                        buttonWater.setEnabled(false);
                }
            }
        }
    }
    
    /**
     * Private inner class to catch events when the user clicks the add button
     */
    private class AddCreditsListener implements ActionListener {
        
        /**
         * The event when the user clicks the add button
         * @param e ActionEvent The event
         */
        public void actionPerformed(ActionEvent e) {
            try {
                double addedCredits = Double.parseDouble(textfieldAddCredits.getText());
                
                if (addedCredits > 0) {
                    credits += addedCredits;
                    textfieldCredits.setText(creditFormat.format(credits));
                    textfieldAddCredits.setText(creditFormat.format(0.0));
                }
                else
                    textfieldAddCredits.setText(creditFormat.format(0.0));
            }
            catch(Exception ex) {
                textfieldAddCredits.setText(creditFormat.format(0.0));
            }
        }
    }
    
    /**
     * Private inner class to catch events when the user clicks the return credits button
     */
    private class ReturnCreditsListener implements ActionListener {
        
        /**
         * The event when the user clicks the return credits button
         * @param e ActionEvent The event
         */
        public void actionPerformed(ActionEvent e) {
            if(credits > 0) {
                change = credits;
                credits = 0.0;
                textfieldCredits.setText(creditFormat.format(credits));
                textfieldChange.setText(creditFormat.format(change));
            }
        }
    }
    
    /**
     * Private inner class to catch events when the user clicks the collect credits button
     */
    private class CollectCreditsListener implements ActionListener {
        
        /**
         * The event when the user clicks the collect credits button
         * @param e ActionEvent The event
         */
        public void actionPerformed(ActionEvent e) {
            change = 0.0;
            textfieldChange.setText(creditFormat.format(change));
        }
    }
}
