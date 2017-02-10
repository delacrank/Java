package week7;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class Pool extends JPanel
{
	static final long serialVersionUID = 1L;
	JButton calculatebutton;
	JButton exitButton;
	JTextField PlengthField;
	JTextField PwidthField;
	JTextField PdepthField;
	JTextField PvolumeField;
	JLabel Pdepthlabel;
	JLabel Plengthlabel;
	JLabel Pwidththlabel;
	JLabel Pvolumelabel;
	JLabel PErrorwidthlabel;
	JLabel PErrorlengthlabel;
	JLabel PErrordepthlabel;

	public Pool()
	{

		setPreferredSize(new Dimension(350, 300));
		exitButton = new JButton("Exit");
		Plengthlabel = new JLabel("Enter the length of the Swimming Pool (ft): ");
		Pwidththlabel = new JLabel("Enter The width of the Swimming Pool (ft): ");
		Pdepthlabel = new JLabel("Enter the average depth of the Swimming Pool (ft): ");
		Pvolumelabel = new JLabel("    Swimming Pool Volume (ft^3):     ");
		PErrordepthlabel = new JLabel("");
		PErrorwidthlabel = new JLabel("");
		PErrorlengthlabel = new JLabel("");
		PlengthField =  new JTextField(5);
		PwidthField =  new JTextField(5);
		PvolumeField = new JTextField(5);
		PdepthField = new JTextField(5);
		PvolumeField.setEditable(false);
		calculatebutton = new JButton("Calculate Volume");


		setLayout( new FlowLayout());

		add(Plengthlabel);
		add(PlengthField);
		add(Pwidththlabel);
		add(PwidthField);
		add(Pdepthlabel);
		add(PdepthField);
		add(Pvolumelabel);
		add(PvolumeField);
		add(calculatebutton);
		add(exitButton);
		add(PErrorlengthlabel);
		add(PErrorwidthlabel);
		add(PErrordepthlabel);

		calculatebutton.setMnemonic('c');
		exitButton.setMnemonic('X');

		CalculateButtonHandler chandler = new CalculateButtonHandler();
		calculatebutton.addActionListener(chandler);

		ExitButtonHandler ehandler = new ExitButtonHandler();
		exitButton.addActionListener(ehandler);

		FocusHandler fhandler = new FocusHandler();
		PlengthField.addFocusListener(fhandler);
		PwidthField.addFocusListener(fhandler);
		PdepthField.addFocusListener(fhandler);
		PvolumeField.addFocusListener(fhandler);

	}
	class CalculateButtonHandler implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			DecimalFormat num = new DecimalFormat(",###.##");
			double width, length, depth, volume;
			String inString;

			inString = PlengthField.getText();
			if (inString.equals("")) 
			{
				inString = "0";
				PlengthField.setText("0");
				PErrorlengthlabel.setText("                 Enter a value for the length.");
			}

			length = Double.parseDouble(inString);

			inString = PwidthField.getText();
			if (inString.equals(""))
			{
				inString = "0";
				PwidthField.setText("0");
				PErrorwidthlabel.setText("                 Enter a value for the width.");
			}

			width = Double.parseDouble(inString);

			inString = PdepthField.getText();
			if (inString.equals(""))
			{
				inString = "0";
				PdepthField.setText("0");
				PErrordepthlabel.setText("                 Enter a value for the depth.");
			}

			depth = Double.parseDouble(inString);

			volume = length * width * depth;
			PvolumeField.setText(num.format(volume));

			if(depth > 0){
				PErrordepthlabel.setText("");
			}
			if(length > 0){
				PErrorlengthlabel.setText("");
			}
			if(width > 0){
				PErrorwidthlabel.setText("");
			}

			//Will write to the Data.txt file
			try{

				FileWriter fileW = new FileWriter("Data.txt");
				System.out.println("Writing data to Data.txt file");
				fileW.write("Pool length:");
				fileW.write(PlengthField.getText());
				fileW.write(",");
				fileW.write(" ");
				fileW.write("Pool width:");
				fileW.write(PwidthField.getText());
				fileW.write(",");
				fileW.write(" ");
				fileW.write("Pool avg depth:");
				fileW.write(PdepthField.getText());
				fileW.write(",");
				fileW.write(" ");
				fileW.write("Pool volume:");
				fileW.write(PvolumeField.getText());
				fileW.write(",");
				fileW.close();

				FileReader fileR = new FileReader("Data.txt");
				BufferedReader buffIn = new BufferedReader(fileR);

				String textData = buffIn.readLine();
				System.out.println(textData);
				buffIn.close();
				System.out.println("\n");

			}
			catch(IOException e1)
			{
				JOptionPane.showMessageDialog(null,e1.getMessage(), "ERROR",2);
			}
		}
	}
	class ExitButtonHandler implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			System.exit(0);
		}
	}
	class FocusHandler implements FocusListener
	{
		public void focusGained(FocusEvent e)
		{
			if (e.getSource() == PlengthField || e.getSource() == PwidthField || e.getSource() == PdepthField)
			{
				PvolumeField.setText("");
			}
			else if (e.getSource() == PvolumeField)
			{
				calculatebutton.requestFocus();
			}
		}
		public void focusLost(FocusEvent e)
		{
			if (e.getSource() == PdepthField)
			{
				calculatebutton.requestFocus();
			}
		}
	}
	public static void main(String args[]) 
	{
		Pool pl;
		pl = new Pool();
	}
}

