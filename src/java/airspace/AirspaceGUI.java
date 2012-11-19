package airspace;

import java.awt.*;

import javax.swing.*;

public class AirspaceGUI extends JFrame {
    private AirspaceEnv environment;

    private JTextArea output;
    private JScrollPane outScroll;
    private Box box;
    private JLabel label;
    public AirspaceGUI(AirspaceEnv env) {
        super("Multi Agent System - Resource Collection");
        environment = env;
        initialise();
    }
    
    public void initialise() {
        
        Container c = getContentPane();     
    
        label = new JLabel("Output");

        output = new JTextArea(6, 18); 
        output.setEditable(false);
        
        outScroll = new JScrollPane();
        outScroll.setViewportView(output);
        outScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        
        box = new Box(BoxLayout.Y_AXIS);
        box.add(label);
        box.add(outScroll);
        
        c.add(box);
        setSize(610, 750);
        setVisible(true);
    }
}
