import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
public class GUI extends JFrame implements ActionListener{
	JFrame frame = new JFrame();
	JPanel panel = new JPanel();
	JLabel welcomeLabel = new JLabel();
	JLabel chooseLabel = new JLabel();
	JButton option1 = new JButton();
	JButton option2 = new JButton();
	JButton option3 = new JButton();
	
	  public void MyFrame(){
		  
		  
		  
		  welcomeLabel.setText("Welcome to the Cities Recommendation App!"); //create a label
		  //welcomeLabel.setIcon(inFrameImg); //set text of label 
		  welcomeLabel.setHorizontalTextPosition(JLabel.CENTER); //set text LEFT ,CENTER , RIGHT of imageIcon
		  welcomeLabel.setVerticalTextPosition(JLabel.TOP); //set text TOP , CENTER , BOTTOM of imageICon
		  welcomeLabel.setForeground(new Color(0xFFFDE4)); //set font color
		  welcomeLabel.setFont(new Font("MV Boli",Font.PLAIN,30)); //set font of text
		  welcomeLabel.setIconTextGap(0); //set gap of text to image
		  //label.setBackground(Color.black); //set background color
		  //label.setOpaque(true); //display Background color
		  chooseLabel.setText("    Please Choose an option..");
		  chooseLabel.setFont(new Font("Comic Sans",Font.BOLD,20));
		  option1.setText("Insert a new city!");
		  option1.setFont(new Font("Comic Sans", Font.BOLD,15));
		  option1.setBorder(BorderFactory.createEtchedBorder());
		  option1.addActionListener(this);
		  option2.setText("Recommend me Cities!");
		  option2.setFont(new Font("Comic Sans",Font.BOLD,15));
		  option2.setBorder(BorderFactory.createEtchedBorder());
		  option2.addActionListener(this);
		  option3.setText("Show me the cities added to the list!");
		  option3.setFont(new Font("Comic Sans", Font.BOLD,15));
		  option3.setBorder(BorderFactory.createEtchedBorder());
		  option3.addActionListener(this);
		  panel.add(chooseLabel);
		  panel.setBackground(Color.lightGray);
		  panel.setBounds(10,10,250,250);
		  panel.setPreferredSize(new Dimension(300,400));
		  panel.setLayout(new GridLayout(4,1,10,10));
		  panel.add(option1);
		  panel.add(option2);
		  panel.add(option3);
		  
		  
		 
		  
	      frame.setTitle("Cities Recommendations"); //sets title of frame 
	      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
	      frame.setSize(750,550);
	      frame.setLayout(new FlowLayout(FlowLayout.CENTER));//exit out of application
	      frame.setResizable(false);  //prevent from being resized
	      ImageIcon image = new ImageIcon("city.png"); //create an ImageIcon
	      frame.setIconImage(image.getImage());  //change icon of frame
	      frame.getContentPane().setBackground(new Color(0x123456));
	      frame.add(welcomeLabel);
	      frame.getContentPane().add(panel);
	      
	      //frame.pack();
	      frame.setVisible(true);
	      
	  }
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==option1) {
			myWindow window1 = new myWindow();
		}
		if(e.getSource()==option2) {
			mySecondWindow window2 = new mySecondWindow();
		}
		if(e.getSource()==option3) {
			myThirdWindow window3 = new myThirdWindow();
		}
		
	}
	
}
