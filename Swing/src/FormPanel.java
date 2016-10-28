import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

@SuppressWarnings("serial")
public class FormPanel extends JPanel {

    // Labels
    private JLabel nameLabel;
    private JLabel occupationLabel;

    // TextFields
    private JTextField nameField;
    private JTextField occupationField;

    // Buttons
    private JButton okBtn;

    // Custom listeners
    private FormListener formListener;

    // Lists
    private JList ageList;

    private JComboBox empCombo;

    public FormPanel() {
        setPreferredSize(new Dimension(250, 250));

        nameLabel = new JLabel("Name: ");
        occupationLabel = new JLabel("Occupation: ");
        nameField = new JTextField(10);
        occupationField = new JTextField(10);
        okBtn = new JButton("OK");
        ageList = new JList();
        empCombo = new JComboBox();

        ageList.setPreferredSize(new Dimension(110, 75));
        ageList.setBorder(BorderFactory.createEtchedBorder());

        // Setup list box
        DefaultListModel ageModel = new DefaultListModel();
        ageModel.addElement(new AgeCategory(0, "Under 18"));
        ageModel.addElement(new AgeCategory(1, "18 to 65"));
        ageModel.addElement(new AgeCategory(2, "65 or over"));
        ageModel.addElement(new AgeCategory(3, "5 or 6"));
        ageList.setModel(ageModel);
        ageList.setSelectedIndex(1);

        // Set up combo box
        DefaultComboBoxModel empModel = new DefaultComboBoxModel();
        empModel.addElement("employed");
        empModel.addElement("self-employed");
        empModel.addElement("unemployed");
        empCombo.setModel(empModel);

        okBtn.addActionListener(a -> {
            String name = nameField.getText();
            String occupation = occupationField.getText();
            AgeCategory ageCat = (AgeCategory) ageList.getSelectedValue();

            FormEvent ev = new FormEvent(this, name, occupation, ageCat.getID());

            System.out.println(ageCat.getID());

            if (formListener != null) {
                formListener.formEventOccurred(ev);
            }
        });

        Border innerBorder = BorderFactory.createTitledBorder("Add Person");
        Border outerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
        setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));

    }

    public void layoutComponents() {
        setLayout(new GridBagLayout());

        GridBagConstraints gc = new GridBagConstraints();

        gc.weightx = 1;
        gc.weighty = 0.1;

        // First row
        
        gc.gridy = 0;
        
        gc.gridx = 0;
        gc.anchor = GridBagConstraints.LINE_END;
        gc.fill = GridBagConstraints.NONE;
        gc.insets = new Insets(0, 0, 0, 5);
        add(nameLabel, gc);

        gc.gridx = 1;
        gc.gridy = 0;
        gc.anchor = GridBagConstraints.LINE_START;
        gc.insets = new Insets(0, 0, 0, 0);
        add(nameField, gc);

        // Second row
        
        gc.gridy++;
        
        gc.gridx = 0;
        gc.anchor = GridBagConstraints.LINE_END;
        gc.insets = new Insets(0, 0, 0, 5);
        add(occupationLabel, gc);

        gc.gridx = 1;
        gc.gridy = 1;
        gc.anchor = GridBagConstraints.LINE_START;
        gc.insets = new Insets(0, 0, 0, 0);
        add(occupationField, gc);

        // Third row
        
        gc.gridy++;
        
        gc.weightx = 1;
        gc.weighty = 0.2;
        gc.gridx = 1;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        gc.insets = new Insets(0, 0, 0, 0);
        add(ageList, gc);

        // Fourth row
        
        gc.gridy++;
        
        gc.weightx = 1;
        gc.weighty = 2;
        gc.gridx = 1;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        gc.insets = new Insets(0, 0, 0, 0);
        add(okBtn, gc);
    }

    public void setFormListener(FormListener listener) {
        formListener = listener;
    }

    class AgeCategory {
        private int id;
        private String text;

        public AgeCategory(int id, String text) {
            this.id = id;
            this.text = text;
        }

        @Override
        public String toString() {
            return text;
        }

        public int getID() {
            return id;
        }
    }
}
