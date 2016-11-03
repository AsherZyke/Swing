import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JFrame;


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
}
