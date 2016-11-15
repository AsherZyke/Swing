package gui;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.Border;

@SuppressWarnings("serial")
public class FormPanel extends JPanel {

    // Labels
    private JLabel nameLabel;
    private JLabel occupationLabel;
    private JLabel taxLabel;

    // TextFields
    private JTextField nameField;
    private JTextField occupationField;
    private JTextField taxField;

    // Buttons
    private JButton okBtn;

    // Custom listeners
    private FormListener formListener;

    // Lists
    private JList ageList;

    // Combo boxes
    private JComboBox empCombo;

    // Checkboxes
    private JCheckBox citizenCheck;

    // Radio buttons
    private JRadioButton maleRadio;
    private JRadioButton femaleRadio;
    private ButtonGroup genderGroup;

    public FormPanel() {
        setPreferredSize(new Dimension(250, 250));

        nameLabel = new JLabel("Name: ");
        occupationLabel = new JLabel("Occupation: ");
        nameField = new JTextField(10);
        occupationField = new JTextField(10);
        okBtn = new JButton("OK");
        ageList = new JList();
        empCombo = new JComboBox();
        citizenCheck = new JCheckBox();
        taxField = new JTextField(10);
        taxLabel = new JLabel("Tax ID: ");
        maleRadio = new JRadioButton("male");
        femaleRadio = new JRadioButton("female");
        genderGroup = new ButtonGroup();
        
        //Setup mneumonics
        okBtn.setMnemonic(KeyEvent.VK_O);
        
        nameLabel.setDisplayedMnemonic(KeyEvent.VK_N);
        nameLabel.setLabelFor(nameField);

        ageList.setPreferredSize(new Dimension(110, 75));
        ageList.setBorder(BorderFactory.createEtchedBorder());

        // Set up gender radios
        maleRadio.setActionCommand("male");
        femaleRadio.setActionCommand("female");
        maleRadio.setSelected(true);
        genderGroup.add(maleRadio);
        genderGroup.add(femaleRadio);

        // Setup tax ID
        taxLabel.setEnabled(false);
        taxField.setEnabled(false);

        citizenCheck.addActionListener(a -> {
            boolean isTicked = citizenCheck.isSelected();
            taxLabel.setEnabled(isTicked);
            taxField.setEnabled(isTicked);

        });

        // Setup list box
        DefaultListModel ageModel = new DefaultListModel();
        ageModel.addElement(new AgeCategory(0, "Under 18"));
        ageModel.addElement(new AgeCategory(1, "18 to 65"));
        ageModel.addElement(new AgeCategory(2, "65 or over"));
        ageList.setModel(ageModel);
        ageList.setSelectedIndex(1);

        // Set up combo box
        DefaultComboBoxModel empModel = new DefaultComboBoxModel();
        empModel.addElement("employed");
        empModel.addElement("self-employed");
        empModel.addElement("unemployed");
        empCombo.setModel(empModel);
        empCombo.setSelectedIndex(0);
        empCombo.setEditable(true);

        okBtn.addActionListener(a -> {
            String name = nameField.getText();
            String occupation = occupationField.getText();
            AgeCategory ageCat = (AgeCategory) ageList.getSelectedValue();
            String empCat = (String) empCombo.getSelectedItem();
            String taxID = taxField.getText();
            boolean usCitizen = citizenCheck.isSelected();
            String genderCommand = genderGroup.getSelection().getActionCommand();

            FormEvent ev = new FormEvent(this, name, occupation,
                    ageCat.getID(), empCat, taxID, usCitizen, genderCommand);

            System.out.println(empCat);

            if (formListener != null) {
                formListener.formEventOccurred(ev);
            }
        });

        layoutComponents();

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

        // Next row

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

        // Next row

        gc.gridy++;

        gc.weightx = 1;
        gc.weighty = 0.2;

        gc.gridx = 0;
        gc.insets = new Insets(0, 0, 0, 5);
        gc.anchor = GridBagConstraints.FIRST_LINE_END;
        add(new JLabel("Age: "), gc);

        gc.gridx = 1;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        gc.insets = new Insets(0, 0, 0, 0);
        add(ageList, gc);

        // Next row

        gc.gridy++;

        gc.weightx = 1;
        gc.weighty = 0.2;

        gc.gridx = 0;
        gc.insets = new Insets(0, 0, 0, 5);
        gc.anchor = GridBagConstraints.FIRST_LINE_END;
        add(new JLabel("Employment: "), gc);

        gc.gridx = 1;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        gc.insets = new Insets(0, 0, 0, 0);
        add(empCombo, gc);

        // Next row

        gc.gridy++;

        gc.weightx = 1;
        gc.weighty = 0.2;

        gc.gridx = 0;
        gc.insets = new Insets(0, 0, 0, 5);
        gc.anchor = GridBagConstraints.FIRST_LINE_END;
        add(new JLabel("US Citizen: "), gc);

        gc.gridx = 1;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        gc.insets = new Insets(0, 0, 0, 0);
        add(citizenCheck, gc);

        // Next row

        gc.gridy++;

        gc.weightx = 1;
        gc.weighty = 0.2;

        gc.gridx = 0;
        gc.insets = new Insets(0, 0, 0, 5);
        gc.anchor = GridBagConstraints.FIRST_LINE_END;
        add(taxLabel, gc);

        gc.gridx = 1;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        gc.insets = new Insets(0, 0, 0, 0);
        add(taxField, gc);

        // Next row

        gc.gridy++;

        gc.weightx = 1;
        gc.weighty = 0.05;

        gc.gridx = 0;
        gc.insets = new Insets(0, 0, 0, 5);
        gc.anchor = GridBagConstraints.LINE_END;
        add(new JLabel("Gender: "), gc);

        gc.gridx = 1;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        gc.insets = new Insets(0, 0, 0, 0);
        add(maleRadio, gc);
        
        // Next row

        gc.gridy++;

        gc.weightx = 1;
        gc.weighty = 0.2;

        gc.gridx = 1;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        gc.insets = new Insets(0, 0, 0, 0);
        add(femaleRadio, gc);

        // Next row

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
