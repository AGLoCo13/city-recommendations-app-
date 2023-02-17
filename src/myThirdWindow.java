import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class myThirdWindow extends JFrame implements ActionListener{
	JFrame frame = new JFrame();
    JPanel panel = new JPanel();
    JButton button = new JButton();
    JTextArea area = new JTextArea();
    myThirdWindow(){
    	area.setText("Cities imported and their timestamps will appearHere:");
    	area.append("\n");
    	panel.setBackground(Color.lightGray);
    	panel.setPreferredSize(new Dimension(500,500));
    	panel.setLayout(new FlowLayout());
    	button.setText("Show");
    	button.addActionListener(this);
    	panel.add(area);
    	panel.add(button);
    	
    	ImageIcon appIcon = new ImageIcon("city.png");
    	frame.setTitle("Recommend me Cities!");
    	frame.setIconImage(appIcon.getImage());
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	frame.setSize(600,600);
    	frame.getContentPane().setBackground(new Color(0x123456));
    	frame.setLayout(new FlowLayout());
    	frame.add(panel);
    	frame.pack();
    	frame.setVisible(true);
    	
    	
    }
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == button) {
			ArrayList<String> Cities = new ArrayList<String>();
			JacksonTester tester = new JacksonTester();
			try {
				Cities = tester.Added();
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (org.json.simple.parser.ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			for (String str : Cities){
				area.append(str+"\n");
			}
		}
		
	}
}
