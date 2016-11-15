package gui;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JPanel;


@SuppressWarnings("serial")
public class Toolbar extends JPanel {
    
    private JButton helloButton;
    private JButton goodbyeButton;
    private StringListener textListener;
    
    public Toolbar() {
        helloButton = new JButton("Hello");
        goodbyeButton = new JButton("Goodbye");
        
        helloButton.addActionListener(a -> {
            if (textListener != null) {
                textListener.textEmitted("Hello\n");
            }
        });
        
        goodbyeButton.addActionListener(a -> {
            if (textListener != null) {
                textListener.textEmitted("Goodbye\n");
            }
        });
        
        setLayout(new FlowLayout(FlowLayout.LEFT));
        
        add(helloButton);
        add(goodbyeButton);
        
    }

    public void setStringListener(StringListener listener) {
        this.textListener = listener;
    }
}
