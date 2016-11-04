import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;


@SuppressWarnings("serial")
public class MainFrame extends JFrame {
    
    private Toolbar toolbar;  
    private TextPanel textPanel;
    private FormPanel formPanel;
    
    public MainFrame() {
        super("Hello World");
        
        setLayout(new BorderLayout());
        
        toolbar = new Toolbar();
        textPanel = new TextPanel();
        formPanel = new FormPanel();
        
        setJMenuBar(createMenuBar());
        
        //Tells this particular stringListener what to do with
        //parameter that is passed to it.
        toolbar.setStringListener(s -> textPanel.appendText(s));
        
        formPanel.setFormListener(e -> {
            String name = e.getName();
            String occupation = e.getOccupation();
            int ageCat = e.getAgeCategory();
            String empCat = e.getEmploymentCategory();
            
            textPanel.appendText(name + ": " + occupation + ": " + ageCat + ": " + empCat + "\n");
        });
        
        add(toolbar, BorderLayout.NORTH);
        add(textPanel, BorderLayout.CENTER);
        add(formPanel, BorderLayout.WEST);
        
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
        JMenuItem showFormItem = new JMenuItem("Person Form");
        showMenu.add(showFormItem);
        windowMenu.add(showMenu);
        
        menuBar.add(fileMenu);
        menuBar.add(windowMenu);
        
        return menuBar;
    }
}
