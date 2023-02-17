import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.json.JSONException;
import org.json.simple.parser.ParseException;

public class myWindow extends JFrame implements ActionListener{
    JFrame frame = new JFrame();
    JPanel panel = new JPanel();
    JTextField textField1 = new JTextField();
    JTextField textField2 = new JTextField();
    JTextArea response = new JTextArea();
    JButton submit = new JButton();
    //JScrollPane scrollPane = new JScrollPane();
    myWindow(){
    	submit.setText("Submit");
    	submit.addActionListener(this);
    	textField1.setPreferredSize(new Dimension(250,40));
    	textField1.setText("Type here the City's name");
    	textField2.setPreferredSize(new Dimension(250,40));
    	textField2.setText("City's Country (ex.Greece = gr)");
    	//response.setText("Program response will appear");
    	//response.append("\nhere:\n");
    	//scrollPane.getViewport().add(response); 
    	
    	panel.setBackground(Color.lightGray);
    	panel.setPreferredSize(new Dimension(300,400));
    	panel.setLayout(new FlowLayout());
    	panel.add(textField1);
    	panel.add(textField2);
    	panel.add(submit);
        //panel.add(scrollPane);
    	panel.add(response);
    	
    	ImageIcon appIcon = new ImageIcon("city.png");
    	frame.setTitle("Insert a new City");
    	frame.setIconImage(appIcon.getImage());
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	frame.setSize(500,500);
    	frame.getContentPane().setBackground(new Color(0x123456));
    	frame.add(panel);
    	frame.setLayout(new FlowLayout());
    	frame.pack();
    	frame.setVisible(true);
    }
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==submit) {
			
			String cityName = textField1.getText();
			String country = textField2.getText();
			OpenData ob = new OpenData();
			Boolean existsInFile = null;
			try {
				existsInFile = ob.insertNewCity(cityName, country);
				if(existsInFile == true) {
					JOptionPane.showMessageDialog(null, "City succesfully added to cities.json file!","Info",JOptionPane.INFORMATION_MESSAGE);
				}else {
					JOptionPane.showMessageDialog(null, "City already added to cities.json file!","Warning!",JOptionPane.WARNING_MESSAGE);
				}
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (JSONException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
			
		}
	}

		
		
	

