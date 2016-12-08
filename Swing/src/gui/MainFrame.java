package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

import controller.Controller;

@SuppressWarnings("serial")
public class MainFrame extends JFrame {

    // commented out code related to the toolbar because it is currently
    // unnecessary
    // private Toolbar toolbar;
    private TextPanel textPanel;
    private FormPanel formPanel;
    private JFileChooser fileChooser;
    private Controller controller;
    private TablePanel tablePanel;
    private PrefsDialog prefsDialog;

    public MainFrame() {
        super("Employment Info");

        setLayout(new BorderLayout());

        // toolbar = new Toolbar();
        textPanel = new TextPanel();
        formPanel = new FormPanel();
        tablePanel = new TablePanel();
        fileChooser = new JFileChooser();
        controller = new Controller();
        prefsDialog = new PrefsDialog(this);

        tablePanel.setData(controller.getPeople());

        fileChooser.addChoosableFileFilter(new PersonFileFilter());

        setJMenuBar(createMenuBar());

        // Tells this particular stringListener what to do with
        // parameter that is passed to it.
        // toolbar.setStringListener(s -> textPanel.appendText(s));
        
        tablePanel.setPersonTableListener(row -> {
                controller.removePerson(row);
        });

        formPanel.setFormListener(e -> {
            controller.addPerson(e);
            tablePanel.refresh();
        });

        // add(toolbar, BorderLayout.NORTH);
        add(tablePanel, BorderLayout.CENTER);
        add(formPanel, BorderLayout.WEST);

        setMinimumSize(new Dimension(900, 400));
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu("File");
        JMenuItem exportDataItem = new JMenuItem("Export Data...");
        JMenuItem importDataItem = new JMenuItem("Import Data...");
        JMenuItem exitItem = new JMenuItem("Exit");

        fileMenu.add(exportDataItem);
        fileMenu.add(importDataItem);
        fileMenu.addSeparator();
        fileMenu.add(exitItem);

        JMenu windowMenu = new JMenu("Window");
        JMenu showMenu = new JMenu("Show");
        JMenuItem prefsItem = new JMenuItem("Preferences...");
        
        JCheckBoxMenuItem showFormItem = new JCheckBoxMenuItem("Person Form");
        showFormItem.setSelected(true);
        showMenu.add(showFormItem);
        windowMenu.add(showMenu);
        windowMenu.add(prefsItem);

        menuBar.add(fileMenu);
        menuBar.add(windowMenu);
        
        prefsItem.addActionListener(al -> {
            prefsDialog.setVisible(true);
        });

        showFormItem.addActionListener(a -> {
            JCheckBoxMenuItem menuItem = (JCheckBoxMenuItem) a.getSource();

            formPanel.setVisible(menuItem.isSelected());
        });

        fileMenu.setMnemonic(KeyEvent.VK_F);
        exitItem.setMnemonic(KeyEvent.VK_X);

        exitItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X,
                ActionEvent.CTRL_MASK));
        
        importDataItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I,
                ActionEvent.CTRL_MASK));

        importDataItem
                .addActionListener(a -> {
                    if (fileChooser.showOpenDialog(MainFrame.this) == JFileChooser.APPROVE_OPTION) {
                        try {
                            controller.loadFromFile(fileChooser
                                    .getSelectedFile());
                            tablePanel.refresh();
                        } catch (Exception e) {
                            JOptionPane.showMessageDialog(MainFrame.this,
                                    "Could not load data from file.", "Error",
                                    JOptionPane.ERROR_MESSAGE);
                        }
                    }
                });

        exportDataItem
                .addActionListener(a -> {
                    if (fileChooser.showSaveDialog(MainFrame.this) == JFileChooser.APPROVE_OPTION) {
                        try {
                            controller.saveToFile(fileChooser
                                    .getSelectedFile());
                        } catch (Exception e) {
                            JOptionPane.showMessageDialog(MainFrame.this,
                                    "Could not save data to file.", "Error",
                                    JOptionPane.ERROR_MESSAGE);
                        }
                    }
                });

        exitItem.addActionListener(a -> {
            int action = JOptionPane.showConfirmDialog(MainFrame.this,
                    "Do you really want to exit the application?",
                    "Confirm Exit", JOptionPane.OK_CANCEL_OPTION);
            if (action == JOptionPane.OK_OPTION) {
                System.exit(0);
            }

        });

        return menuBar;
    }
}
