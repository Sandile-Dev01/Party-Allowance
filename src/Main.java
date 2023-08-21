import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Model: Contains the business logic and data handling
class PartyModel {
    public boolean isEligible(int age) {
        return age >= 18;
    }
}

// View: Handles the GUI components and their presentation
class PartyView extends JFrame {
    private JTextField ageField;
    private JButton checkButton;

    public PartyView() {
        initUI(); // Initialize the graphical user interface
    }

    // Set up the user interface components
    private void initUI() {
        setTitle("Party Allowance App");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ageField = new JTextField();
        ageField.setPreferredSize(new Dimension(150, 25));
        ageField.setToolTipText("Enter your age");

        checkButton = new JButton("Check Eligibility");

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.add(ageField);
        panel.add(checkButton);

        add(panel);
        pack();
        setLocationRelativeTo(null);
    }

    // Add a listener to the Check Eligibility button
    public void addCheckButtonListener(ActionListener listener) {
        checkButton.addActionListener(listener);
    }

    // Get the age input from the text field
    public String getAgeInput() {
        return ageField.getText();
    }

    // Show a message for being allowed to the party
    public void showAllowedAlert() {
        showMessageDialog("You are allowed to the party!");
    }

    // Show a message for not being allowed to the party
    public void showNotAllowedAlert() {
        showMessageDialog("You are under the age and not allowed to the party!");
    }

    // Show a message for invalid input
    public void showInvalidInputAlert() {
        showMessageDialog("Please enter a valid age.");
    }

    // Show a general message dialog
    private void showMessageDialog(String message) {
        JOptionPane.showMessageDialog(this, message, "Party Allowance", JOptionPane.INFORMATION_MESSAGE);
    }
}

// Controller: Handles interactions between the model and view
class PartyController {
    private PartyModel model;
    private PartyView view;

    public PartyController(PartyModel model, PartyView view) {
        this.model = model;
        this.view = view;

        view.addCheckButtonListener(new CheckButtonListener());
    }

    // ActionListener for the Check Eligibility button
    private class CheckButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String ageText = view.getAgeInput();
            try {
                int age = Integer.parseInt(ageText);
                boolean isEligible = model.isEligible(age);
                if (isEligible) {
                    view.showAllowedAlert();
                } else {
                    view.showNotAllowedAlert();
                }
            } catch (NumberFormatException ex) {
                view.showInvalidInputAlert();
            }
        }
    }
}

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            PartyModel model = new PartyModel();
            PartyView view = new PartyView();
            PartyController controller = new PartyController(model, view);
            view.setVisible(true); // Show the application window
        });
    }
}
