import java.awt.*;
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

   public UserInterface() {
      frame = new JFrame("Altruism Simulation");
      frame.setSize(2000, 1000);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setLayout(new BorderLayout());
      outputArea = new JTextArea();
      outputArea.setEditable(false);
      frame.add(new JScrollPane(outputArea), BorderLayout.CENTER);

      JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 10, 10));
      buttonPanel.setPreferredSize(new Dimension(2000,300));
      JButton setBtn = new JButton("Test Case Setup");
      JButton strtBtn = new JButton("Start Simulation");
      JButton saveBtn = new JButton("Save data to file");
      JButton anylBtn = new JButton("Analyze Results");
      JButton rstBtn = new JButton("Reset data");
      JButton exitBtn = new JButton("Exit");
      buttonPanel.add(setBtn);
      buttonPanel.add(strtBtn);
      buttonPanel.add(saveBtn);
      buttonPanel.add(anylBtn);
      buttonPanel.add(rstBtn);
      buttonPanel.add(exitBtn);
      frame.add(buttonPanel, BorderLayout.SOUTH);
      outputArea.setFont(new Font("Monospaced", Font.PLAIN, 80));
      outputArea.setText("Welcome to the Altruism Simulation!\n");
      setBtn.addActionListener(e -> setupMenu());

       exitBtn.addActionListener(e -> System.exit(0));
       frame.setVisible(true);
   }
   private void setupMenu() {
      setupFrame = new JFrame("Setup");
      setupFrame.setSize(2000, 1000);
      setupFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setupFrame.setLayout(new BorderLayout());
      setupOutputArea = new JTextArea();
      setupOutputArea.setEditable(false);
      setupFrame.add(new JScrollPane(setupOutputArea), BorderLayout.CENTER);
      

      JPanel setupButtonPanel = new JPanel(new GridLayout(1, 2, 10, 10));
      setupButtonPanel.setPreferredSize(new Dimension(2000,300));
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
      setupButtonPanel.add(popBtn);
      setupButtonPanel.add(roundBtn);
      setupButtonPanel.add(propBtn);
      setupButtonPanel.add(costBtn);
      setupButtonPanel.add(benefitBtn);
      setupButtonPanel.add(turnsBtn);
      setupButtonPanel.add(interactionBtn);
      setupButtonPanel.add(refreshBtn);
      setupButtonPanel.add(resourcesBtn);
      setupButtonPanel.add(exitBtn);
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
      setupFrame.setVisible(true);

   }

   private int changePrompt (String parameterName){
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

