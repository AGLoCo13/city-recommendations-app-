import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class mySecondWindow extends JFrame implements ActionListener{
	/**
	 * 
	 */
	JFrame frame = new JFrame();
    JPanel panel = new JPanel();
    JTextField age = new JTextField();
    JTextField cafeTextField = new JTextField();
    JTextField seaTextField = new JTextField();
    JTextField museumTextField = new JTextField();
    JTextField restaurantTextField = new JTextField();
    JTextField mountainTextField = new JTextField();
    JTextField stadiumTextField = new JTextField();
    JTextField etcTextField = new JTextField();
    JTextArea textArea = new JTextArea();
    JButton Submit = new JButton();
    JLabel prompt1 = new JLabel();
    JLabel prompt2 = new JLabel();
    mySecondWindow(){
    	prompt1.setText("Please Enter your age");
    	age.setText("Enter your age here");
    	age.setPreferredSize(new Dimension(250,40));
    	prompt2.setText("Add a number from 0.0 to 1.0 that represents how much you like this term");
    	cafeTextField.setText("cafe");
    	cafeTextField.setPreferredSize(new Dimension(250,40));
    	seaTextField.setText("sea");
    	seaTextField.setPreferredSize(new Dimension(250,40));
    	museumTextField.setText("museum");
    	museumTextField.setPreferredSize(new Dimension(250,40));
    	restaurantTextField.setText("restaurant");
    	restaurantTextField.setPreferredSize(new Dimension(250,40));
    	mountainTextField.setText("mountain");
    	mountainTextField.setPreferredSize(new Dimension(250,40));
    	stadiumTextField.setText("stadium");
    	stadiumTextField.setPreferredSize(new Dimension(250,40));
    	etcTextField.setText("..etc");
    	etcTextField.setPreferredSize(new Dimension(250,40));
    	Submit.setText("Submit");
    	Submit.addActionListener(this);
    	textArea.setText("Recommended Cities will appear here:");
    	textArea.append("\n");
    	
    	panel.setBackground(Color.lightGray);
    	panel.setPreferredSize(new Dimension(500,500));
    	panel.setLayout(new FlowLayout());
    	panel.add(prompt1);
    	panel.add(age);
    	panel.add(prompt2);
    	panel.add(cafeTextField);
    	panel.add(seaTextField);
    	panel.add(restaurantTextField);
    	panel.add(museumTextField);
    	panel.add(stadiumTextField);
    	panel.add(mountainTextField);
    	panel.add(etcTextField);
    	panel.add(Submit);
    	panel.add(textArea);
    	
    	
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
		if (e.getSource()==Submit) {
			if(Integer.parseInt(age.getText())>115 || Integer.parseInt(age.getText())<16 ) {
				JOptionPane.showMessageDialog(null, "Please enter a valid Age number (Between 16 and 115)","Warning!",JOptionPane.WARNING_MESSAGE);
			}else {
			if (Integer.parseInt(age.getText())>=16 && Integer.parseInt(age.getText())<=25 ) {
				List<City> Recommended = new ArrayList<City>();
				PerceptronYoungTraveller young = new PerceptronYoungTraveller();
				float cafeTerm = Float.parseFloat(cafeTextField.getText());
				float seaTerm = Float.parseFloat(seaTextField.getText());
				float restaurantTerm = Float.parseFloat(restaurantTextField.getText());
				float museumTerm = Float.parseFloat(museumTextField.getText());
				float stadiumTerm = Float.parseFloat(stadiumTextField.getText());
				float mountainTerm = Float.parseFloat(mountainTextField.getText());
				float etcTerm = Float.parseFloat(etcTextField.getText());
				young.weightBiasSetter(cafeTerm, seaTerm, restaurantTerm, museumTerm, stadiumTerm, mountainTerm, etcTerm);
				Recommended = young.recommend();
				textArea.setText("Recommendations for Young Traveller:\n");
				for (City c : Recommended) {
					
					textArea.append(c.getCityName()+"\n");
				}
			}else if(Integer.parseInt(age.getText())>25 && Integer.parseInt(age.getText())<=60) {
				List<City> Recommended = new ArrayList<City>();
				PerceptronMiddleTraveller middle = new PerceptronMiddleTraveller();
				float cafeTerm = Float.parseFloat(cafeTextField.getText());
				float seaTerm = Float.parseFloat(seaTextField.getText());
				float restaurantTerm = Float.parseFloat(restaurantTextField.getText());
				float museumTerm = Float.parseFloat(museumTextField.getText());
				float stadiumTerm = Float.parseFloat(stadiumTextField.getText());
				float mountainTerm = Float.parseFloat(mountainTextField.getText());
				float etcTerm = Float.parseFloat(etcTextField.getText());
				middle.weightBiasSetter(cafeTerm, seaTerm, restaurantTerm, museumTerm, stadiumTerm, mountainTerm, etcTerm);
				Recommended =  middle.recommend();
				textArea.setText("Recommendations for Middle Traveller:\n");
				for (City c : Recommended) {
					
					textArea.append(c.getCityName()+"\n");
			}
			}else {
				List<City> Recommended = new ArrayList<City>();
				PerceptronElderTraveller elder = new PerceptronElderTraveller();
				float cafeTerm = Float.parseFloat(cafeTextField.getText());
				float seaTerm = Float.parseFloat(seaTextField.getText());
				float restaurantTerm = Float.parseFloat(restaurantTextField.getText());
				float museumTerm = Float.parseFloat(museumTextField.getText());
				float stadiumTerm = Float.parseFloat(stadiumTextField.getText());
				float mountainTerm = Float.parseFloat(mountainTextField.getText());
				float etcTerm = Float.parseFloat(etcTextField.getText());
				elder.weightBiasSetter(cafeTerm, seaTerm, restaurantTerm, museumTerm, stadiumTerm, mountainTerm, etcTerm);
				Recommended =  elder.recommend();
				textArea.setText("Recommendations for Elder Traveller:\n");
				for (City c : Recommended) {
					
					textArea.append(c.getCityName()+"\n");
			}
			}
				
				
		}
		}
		}
		
	}

