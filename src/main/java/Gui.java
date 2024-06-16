/**************************************************************************
* File name: Gui 
*
* Description: Creating a Gui object and a static action method
*
* Author: Thomas Murzaku  
*
* Date: June 3rd, 2024  
*
* Concepts: Swing components, Layout manager, Action Listeners, Constant variables
*           Data validation
* Input: Static loadRecipes method, recipes field of Recipe class
* Processing: Creating and adding different components to a JFrame, adding
*             action listeners to desired objects
* Output: GUI
***************************************************************************/

//Importing desired packages
import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Gui {

  // Declaring necessary fields
  private JFrame mainF;
  private JPanel northP, westP, mainC, southP;
  private JComboBox<String> carbFilter, fatFilter, calFilter, proteinFilter, typeC;
  private JTextField searchT, calsT, carbsT, protT, fatT;
  private JTextArea display;
  private JScrollPane displayP;
  private JButton searchB, clearB, exitB, filterB;
  private JLabel filtersL, blankTl;
  private final String[] TYPE = { "Type", "Lunch", "Dinner", "Breakfast", "Dessert" };
  private final String[] COMPARE = { ">", "<" };
  private final String INFO = "Enter recipe or ingredient name: ";
  private ArrayList<Recipe> collection;
  private String fileName = "recipes.txt";

  // Constructor method for Gui object
  public Gui() {
    // Compiling and initailizing list of recipes to collection arraylist
    boolean correctFile = false;
    while (!correctFile){
      
      try{
        
      Recipe.loadRecipes(fileName);
      collection = Recipe.recipes;
      correctFile = true;  
    }catch(NullPointerException npe){
        
      fileName = JOptionPane.showInputDialog("Enter correct file name: ");
      
    }
    }
    
    

    // Initializing main JFrame
    mainF = new JFrame("CookLook V1");
    mainF.setLayout(new BorderLayout());
    mainF.setSize(500, 500);
    mainF.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    // Initializing north panel
    northP = new JPanel(true);
    northP.setLayout(new BorderLayout());
    northP.setPreferredSize(new Dimension(500, 50));
    mainF.add(northP, "North");
    // Adding text field to north panel
    searchT = new JTextField(INFO);
    searchT.setPreferredSize(new Dimension(200, 40));
    northP.add(searchT, "Center");
    // adding button to north panel
    searchB = new JButton("Search: ");
    searchB.setPreferredSize(new Dimension(100, 40));
    northP.add(searchB, "East");

    // Creating new central panel
    mainC = new JPanel(true);
    mainC.setLayout(new BorderLayout());
    mainC.setPreferredSize(new Dimension(500, 300));
    mainF.add(mainC, "Center");

    // Creating a display text area
    display = new JTextArea("There are " + collection.size() + " recipes available");
    display.setLineWrap(true);
    display.setWrapStyleWord(true);
    display.setEditable(false);
    displayP = new JScrollPane(display, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
        JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    displayP.setPreferredSize(new Dimension(300, 400));
    mainC.add(displayP, "Center");

    // Creating west panel and adding it to main JFrame
    westP = new JPanel(true);
    westP.setLayout(new GridLayout(6, 2));
    westP.setPreferredSize(new Dimension(150, 400));
    mainF.add(westP, "West");

    // Adding all buttons/text field for different filters
    filterB = new JButton("Apply");
    filterB.setPreferredSize(new Dimension(80, 40));
    blankTl = new JLabel();
    blankTl.setPreferredSize(new Dimension(40, 40));
    calsT = new JTextField("Cals");
    calsT.setPreferredSize(new Dimension(40, 40));
    carbsT = new JTextField("Carbs");
    carbsT.setPreferredSize(new Dimension(40, 40));
    protT = new JTextField("Protein");
    protT.setPreferredSize(new Dimension(40, 40));
    fatT = new JTextField("Fat");
    fatT.setPreferredSize(new Dimension(40, 40));
    filtersL = new JLabel("Filters");
    filtersL.setPreferredSize(new Dimension(70, 40));
    typeC = new JComboBox<String>(TYPE);
    typeC.setPreferredSize(new Dimension(70, 40));
    calFilter = new JComboBox<String>(COMPARE);
    calFilter.setPreferredSize(new Dimension(40, 40));
    carbFilter = new JComboBox<String>(COMPARE);
    carbFilter.setPreferredSize(new Dimension(40, 40));
    proteinFilter = new JComboBox<String>(COMPARE);
    proteinFilter.setPreferredSize(new Dimension(40, 40));
    fatFilter = new JComboBox<String>(COMPARE);
    fatFilter.setPreferredSize(new Dimension(40, 40));
    // Adding all to the west panel
    westP.add(filtersL);
    westP.add(filterB);
    westP.add(typeC);
    westP.add(blankTl);
    westP.add(calFilter);
    westP.add(calsT);
    westP.add(carbFilter);
    westP.add(carbsT);
    westP.add(proteinFilter);
    westP.add(protT);
    westP.add(fatFilter);
    westP.add(fatT);

    // Creating south panel and adding it to main JFrame
    southP = new JPanel(true);
    southP.setLayout(new BorderLayout());
    southP.setPreferredSize(new Dimension(500, 50));
    mainF.add(southP, "South");

    // Adding clear and exit buttons and adding it to south panels
    clearB = new JButton("Clear");
    clearB.setPreferredSize(new Dimension(90, 40));
    southP.add(clearB, "East");
    exitB = new JButton("Exit");
    exitB.setPreferredSize(new Dimension(90, 40));
    southP.add(exitB, "West");

    // Setting visible
    mainF.setVisible(true);

  }

  // This method creates action listeners for the desired components
  public void actions() {

    /*Next four mouse listeners are used to clear 
    the text field when user clicks on textfield */
    
    searchT.addMouseListener(new MouseAdapter(){
        @Override
        public void mouseClicked(MouseEvent e){
            searchT.setText("");
        }
    });

    calsT.addMouseListener(new MouseAdapter(){
        @Override
        public void mouseClicked(MouseEvent e){
            calsT.setText("");
        }
    });

    carbsT.addMouseListener(new MouseAdapter(){
        @Override
        public void mouseClicked(MouseEvent e){
            carbsT.setText("");
        }
    });

    fatT.addMouseListener(new MouseAdapter(){
        @Override
        public void mouseClicked(MouseEvent e){
            fatT.setText("");
        }
    });

    protT.addMouseListener(new MouseAdapter(){
        @Override
        public void mouseClicked(MouseEvent e){
            protT.setText("");
        }
    });


    // Action listener for search button
    searchB.addActionListener(e -> {
      String allRecipes = "";
      // Checking if anything has changed
      if (searchT.getText().equals(INFO)) {
        collection = Recipe.recipes;
      } else {
        // Using static search by token method
        collection = Recipe.searchByToken(searchT.getText(), collection);
      }
      // Displaying compatible recipes
      allRecipes += "Found " + collection.size() + " recipe" + ((collection.size() > 1) ? "s " : " ")
          + "matching your criteria. \n";
      for (Recipe r : collection) {
        allRecipes += r + "\n";
      }
      display.setText(allRecipes);
    });

    // Action Listener for each filter
    filterB.addActionListener(e -> {
      String type = String.valueOf(typeC.getSelectedItem());
      // Checking if anything has changed
      if (!type.equals("Type") && !type.equals("")) {
        collection = Recipe.searchByType(type, collection);
      }

      try {
        // Searching by calories
        int cals = Integer.parseInt(calsT.getText().trim());
        boolean less = String.valueOf(calFilter.getSelectedItem()).equals("<");
        collection = Recipe.searchByCalories(cals, less, collection);

      } catch (NumberFormatException nfe) {

      }

      try {
        // Searching by carbs
        int carbs = Integer.parseInt(carbsT.getText().trim());
        boolean less = String.valueOf(carbFilter.getSelectedItem()).equals("<");
        collection = Recipe.searchByCarbs(carbs, less, collection);

      } catch (NumberFormatException nfe) {

      }
      try {
        // Searching by fat
        int fat = Integer.parseInt(fatT.getText().trim());
        boolean less = String.valueOf(fatFilter.getSelectedItem()).equals("<");
        collection = Recipe.searchByFat(fat, less, collection);

      } catch (NumberFormatException nfe) {

      }

      try {
        // Searching by protein
        int protein = Integer.parseInt(protT.getText().trim());
        boolean less = String.valueOf(proteinFilter.getSelectedItem()).equals("<");
        collection = Recipe.searchByProtein(protein, less, collection);

      } catch (NumberFormatException nfe) {

      }
      // Displaying compatible recipes
      String allRecipes = "";
      allRecipes += "Found " + collection.size() + " recipe" + ((collection.size() > 1) ? "s " : " ")
          + "matching your criteria. \n";
      for (Recipe r : collection) {
        allRecipes += r + "\n";
      }
      display.setText(allRecipes);

    });
    // Action listener for exit button
    exitB.addActionListener(e -> {
      System.exit(0);
    });

    // Action listener for clear button which resets all components of the Gui
    // object and the list of recipes for the collection arraylist
    clearB.addActionListener(e -> {
      carbsT.setText("carbs");
      fatT.setText("fat");
      protT.setText("protein");
      calsT.setText("cals");
      typeC.setSelectedItem(TYPE[0]);
      calFilter.setSelectedItem(COMPARE[0]);
      proteinFilter.setSelectedItem(COMPARE[0]);
      carbFilter.setSelectedItem(COMPARE[0]);
      fatFilter.setSelectedItem(COMPARE[0]);
      searchT.setText(INFO);
      display.setText("");
      collection = Recipe.recipes;
    });

  }

}