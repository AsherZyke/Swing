import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JFrame;


@SuppressWarnings("serial")
public class MainFrame extends JFrame {
    
    private Toolbar toolbar;  
    private TextPanel textPanel;
    
    public MainFrame() {
        super("Hello World");
        
        setLayout(new BorderLayout());
        
        toolbar = new Toolbar();
        textPanel = new TextPanel();
        
        //Tells this particular stringListener what to do with
        //parameter that is passed to it.
        toolbar.setStringListener(s -> textPanel.appendText(s));
        
        add(toolbar, BorderLayout.NORTH);
        add(textPanel, BorderLayout.CENTER);
        
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }


}
