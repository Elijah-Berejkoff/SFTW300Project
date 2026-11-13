import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;   

public class UserInterface {
   RunController controller;
   String currentScreen;
   double inputValues;
   String statusMessage;
   boolean simulationRunning;
   JTextArea outputArea;
   JTextArea setupOutputArea;

   JFrame frame;
   JFrame setupFrame;
   Font buttonFont = new Font("Arial", Font.BOLD, 30);

   public UserInterface() {
      frame = new JFrame("Altruism Simulation");
      frame.setSize(2000, 1000);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setLayout(new BorderLayout());
      outputArea = new JTextArea();
      outputArea.setEditable(false);
      frame.add(new JScrollPane(outputArea), BorderLayout.CENTER);

      JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 10, 10));
      buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
      buttonPanel.setPreferredSize(new Dimension(2000,300));
      JPanel topRow = new JPanel(new GridLayout(1, 3, 10, 10));
      JPanel bottomRow = new JPanel(new GridLayout(1, 3, 10, 10));

      JButton setBtn = new JButton("Test Case Setup");
      JButton strtBtn = new JButton("Start Simulation");
      JButton saveBtn = new JButton("Save data to file");
      JButton anylBtn = new JButton("Analyze Results");
      JButton rstBtn = new JButton("Reset data");
      JButton exitBtn = new JButton("Exit");
      setBtn.setFont(buttonFont);
      strtBtn.setFont(buttonFont);
      saveBtn.setFont(buttonFont);
      anylBtn.setFont(buttonFont);
      rstBtn.setFont(buttonFont);
      exitBtn.setFont(buttonFont);
      topRow.add(setBtn);
      topRow.add(strtBtn);
      topRow.add(saveBtn);
      bottomRow.add(anylBtn);
      bottomRow.add(rstBtn);
      bottomRow.add(exitBtn);
      buttonPanel.add(topRow);
      buttonPanel.add(bottomRow);
      frame.add(buttonPanel, BorderLayout.SOUTH);
      outputArea.setFont(new Font("Monospaced", Font.PLAIN, 50));
      outputArea.setText("Welcome to the Altruism Simulation!\n");
      setBtn.addActionListener(e -> setupMenu());
      
     /* startBtn.addActionListener(e -> );
       viewBtn.addActionListener(e -> displayVaults());
       saveBtn.addActionListener(e -> {
           saveToFile("vault_data.txt", vSystem);
           append("Saved data to file\n");
       });
*/  
       exitBtn.addActionListener(e -> System.exit(0));
       frame.setVisible(true);
   }
   private void setupMenu() {
    ArrayList<Integer> setupValues = new ArrayList<>();
      setupFrame = new JFrame("Setup");
      setupFrame.setSize(2000, 1000);
      setupFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setupFrame.setLayout(new BorderLayout());
      setupOutputArea = new JTextArea();
      setupOutputArea.setEditable(false);
      setupFrame.add(new JScrollPane(setupOutputArea), BorderLayout.CENTER);
      

      JPanel setupButtonPanel = new JPanel(new GridLayout(1, 2, 10, 10));
      setupButtonPanel.setLayout(new BoxLayout(setupButtonPanel, BoxLayout.Y_AXIS));
      setupButtonPanel.setPreferredSize(new Dimension(2000,300));
      JPanel setupRow1 = new JPanel(new GridLayout(1, 3, 10, 10));
      JPanel setupRow2 = new JPanel(new GridLayout(1, 3, 10, 10));
      JPanel setupRow3 = new JPanel(new GridLayout(1, 3, 10, 10));
      JPanel setupRow4 = new JPanel(new FlowLayout(FlowLayout.CENTER));

      JButton popBtn = new JButton("Population Size");
      JButton roundBtn = new JButton("Number of Rounds");
      JButton propBtn = new JButton("Proportions of behavior");
      JButton costBtn = new JButton("Cost of helping");
      JButton benefitBtn = new JButton("Benefit of helping");
      JButton turnsBtn = new JButton("Number of turns");
      JButton interactionBtn = new JButton("Interaction rate");
      JButton refreshBtn = new JButton("Resource refresh rate");
      JButton resourcesBtn = new JButton("Amount of resources");
      JButton exitBtn = new JButton("Exit");
      popBtn.setFont(buttonFont);
      roundBtn.setFont(buttonFont);
      propBtn.setFont(buttonFont);
      costBtn.setFont(buttonFont);
      benefitBtn.setFont(buttonFont);
      turnsBtn.setFont(buttonFont);
      interactionBtn.setFont(buttonFont);
      refreshBtn.setFont(buttonFont);
      resourcesBtn.setFont(buttonFont);
      exitBtn.setFont(buttonFont);
      setupRow1.add(popBtn);
      setupRow1.add(roundBtn);
      setupRow1.add(propBtn);
      setupRow2.add(costBtn);
      setupRow2.add(benefitBtn);
      setupRow2.add(turnsBtn);
      setupRow3.add(interactionBtn);
      setupRow3.add(refreshBtn);
      setupRow3.add(resourcesBtn);
      setupRow4.add(exitBtn);
      setupButtonPanel.add(setupRow1);
      setupButtonPanel.add(setupRow2);
      setupButtonPanel.add(setupRow3);
      setupButtonPanel.add(setupRow4);
      setupFrame.add(setupButtonPanel, BorderLayout.SOUTH);
      setupOutputArea.setFont(new Font("Monospaced", Font.PLAIN, 50));
      setupOutputArea.setText("Setup Menu\n");
      setupOutputArea.append("Please configure the simulation parameters here.\n");
      exitBtn.addActionListener(e -> setupFrame.dispose());
      popBtn.addActionListener(e -> {
          int populationSize = changePrompt("population size");
          if (populationSize != -1) {
              setupOutputArea.append("Population size set to: " + populationSize + "\n");
          } else {
              setupOutputArea.append("Invalid, try again\n");
          }
      });
      roundBtn.addActionListener(e -> {
          int numberOfRounds = changePrompt("number of rounds");
          if (numberOfRounds != -1) {
              setupOutputArea.append("Number of rounds set to: " + numberOfRounds + "\n");
          } else {
              setupOutputArea.append("Invalid, try again\n");
          }
      });
      propBtn.addActionListener(e -> {
          int proportionsOfBehavior = changePrompt("proportions of behavior");
          if (proportionsOfBehavior != -1) {
              setupOutputArea.append("Proportions of behavior set to: " + proportionsOfBehavior + "\n");
          } else {
              setupOutputArea.append("Invalid, try again\n");
          }
      });
      costBtn.addActionListener(e -> {
          int costOfHelping = changePrompt("cost of helping");
          if (costOfHelping != -1) {
              setupOutputArea.append("Cost of helping set to: " + costOfHelping + "\n");
          } else {
              setupOutputArea.append("Invalid, try again\n");
          }
      });
      benefitBtn.addActionListener(e -> {
          int benefitOfHelping = changePrompt("benefit of helping");
          if (benefitOfHelping != -1) {
              setupOutputArea.append("Benefit of helping set to: " + benefitOfHelping + "\n");
          } else {
              setupOutputArea.append("Invalid, try again\n");
          }
      });
      turnsBtn.addActionListener(e -> {
          int numberOfTurns = changePrompt("number of turns");
          if (numberOfTurns != -1) {
              setupOutputArea.append("Number of turns set to: " + numberOfTurns + "\n");
          } else {
              setupOutputArea.append("Invalid, try again\n");
          }
      });
      interactionBtn.addActionListener(e -> {
          int interactionRate = changePrompt("interaction rate");
          if (interactionRate != -1) {
              setupOutputArea.append("Interaction rate set to: " + interactionRate + "\n");
          } else {
              setupOutputArea.append("Invalid, try again\n");
          }
      });
      refreshBtn.addActionListener(e -> {
          int refreshRate = changePrompt("refresh rate");
          if (refreshRate != -1) {
              setupOutputArea.append("Refresh rate set to: " + refreshRate + "\n");
          } else {
              setupOutputArea.append("Invalid, try again\n");
          }
      });
      resourcesBtn.addActionListener(e -> {
          int resources = changePrompt("resources");
          if (resources != -1) {
              setupOutputArea.append("Resources set to: " + resources + "\n");
          } else {
              setupOutputArea.append("Invalid, try again\n");
          }
      });
      setupFrame.setVisible(true);

   }

   private int changePrompt (String parameterName){
      JOptionPane pane = new JOptionPane();
      String input = JOptionPane.showInputDialog("Enter a new " + parameterName + ":");
      if (input != null) {
          try {
              return Integer.parseInt(input);
          } catch (NumberFormatException e) {
              JOptionPane.showMessageDialog(null, "Invalid input. Please enter a number.");
          }
      }
      return -1; 
   }
}

