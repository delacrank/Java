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

public class HotTub extends JPanel
{
	static final long serialVersionUID = 1L;
	JButton Hcalculatebutton;
	JButton HexitButton;
	JTextField HlengthField;
	JTextField HwidthField;
	JTextField HdepthField;
	JTextField HvolumeField;
	JLabel Hdepthlabel;
	JLabel Hlengthlabel;
	JLabel Hwidththlabel;
	JLabel Hvolumelabel;
	JLabel HErrorwidthlabel;
	JLabel HErrorlengthlabel;
	JLabel HErrordepthlabel;
	JLabel HTemplabel;
	JTextField HTempField;


	public HotTub()
	{

		setPreferredSize(new Dimension(400, 300));
		HexitButton = new JButton("Exit");
		Hlengthlabel = new JLabel("Enter the length of the Hot Tub (ft): ");
		Hwidththlabel = new JLabel("Enter The width of the Hot Tub (ft): ");
		Hdepthlabel = new JLabel("Enter the average depth of the Hot Tub (ft): ");
		HTemplabel = new JLabel("Enter the temperature of the Hot Tub in degrees: (fh)");
		Hvolumelabel = new JLabel("               Hot Tub Volume (ft^3):              ");
		HErrordepthlabel = new JLabel("");
		HErrorwidthlabel = new JLabel("");
		HErrorlengthlabel = new JLabel("");
		HTempField = new JTextField(5);
		HlengthField =  new JTextField(5);
		HwidthField =  new JTextField(5);
		HvolumeField = new JTextField(5);
		HdepthField = new JTextField(5);
		HvolumeField.setEditable(false);
		Hcalculatebutton = new JButton("Calculate Volume");

		setLayout( new FlowLayout());

		add(Hlengthlabel);
		add(HlengthField);
		add(Hwidththlabel);
		add(HwidthField);
		add(Hdepthlabel);
		add(HdepthField);
		add(HTemplabel);
		add(HTempField);
		add(Hvolumelabel);
		add(HvolumeField);
		add(Hcalculatebutton);
		add(HexitButton);
		add(HErrorlengthlabel);
		add(HErrorwidthlabel);
		add(HErrordepthlabel);

		Hcalculatebutton.setMnemonic('c');
		HexitButton.setMnemonic('X');



		HcalculatebuttonHandler chandler = new HcalculatebuttonHandler();
		Hcalculatebutton.addActionListener(chandler);

		HexitButtonHandler ehandler = new HexitButtonHandler();
		HexitButton.addActionListener(ehandler);

		FocusHandler fhandler = new FocusHandler();
		HlengthField.addFocusListener(fhandler);
		HwidthField.addFocusListener(fhandler);
		HdepthField.addFocusListener(fhandler);
		HvolumeField.addFocusListener(fhandler);

	}
	class HcalculatebuttonHandler implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			DecimalFormat num = new DecimalFormat(",###.##");
			double width, length, depth, volume;
			String inString;

			inString = HlengthField.getText();
			if (inString.equals("")) 
			{
				inString = "0";
				HlengthField.setText("0");
				HErrorlengthlabel.setText("                  Enter a value for the lenth.");
			}

			length = Double.parseDouble(inString);

			inString = HwidthField.getText();
			if (inString.equals(""))
			{
				inString = "0";
				HwidthField.setText("0");
				HErrorwidthlabel.setText("                   Enter a value for the width.");
			}

			width = Double.parseDouble(inString);

			inString = HdepthField.getText();
			if (inString.equals(""))
			{
				inString = "0";
				HdepthField.setText("0");
				HErrordepthlabel.setText("                  Enter a value for the depth.");
			}

			depth = Double.parseDouble(inString);

			volume = length * width * depth;
			HvolumeField.setText(num.format(volume));

			if(depth > 0){
				HErrordepthlabel.setText("");
			}
			if(length > 0){
				HErrorlengthlabel.setText("");
			}
			if(width > 0){
				HErrorwidthlabel.setText("");
			}

			//Will write to the Data.txt file
			try{

				FileWriter fileW = new FileWriter("Data.txt", true);
				System.out.println("Writing data to Data.txt file");
				fileW.write("\nHotTub length:");
				fileW.write(HlengthField.getText());
				fileW.write(",");
				fileW.write(" ");
				fileW.write("HotTub width:");
				fileW.write(HwidthField.getText());
				fileW.write(",");
				fileW.write(" ");
				fileW.write("HotTub avg depth:");
				fileW.write(HdepthField.getText());
				fileW.write(",");
				fileW.write(" ");
				fileW.write("HotTub volume:");
				fileW.write(HvolumeField.getText());
				fileW.write(",");
				fileW.write(" ");
				fileW.write("HotTub temperature:");
				fileW.write(HTempField.getText());
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
	class HexitButtonHandler implements ActionListener
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
			if (e.getSource() == HlengthField || e.getSource() == HwidthField || e.getSource() == HdepthField || e.getSource() == HTempField)
			{
				HvolumeField.setText("");
			}
			else if (e.getSource() == HvolumeField)
			{
				Hcalculatebutton.requestFocus();
			}
		}
		public void focusLost(FocusEvent e)
		{
			if (e.getSource() == HdepthField)
			{
				Hcalculatebutton.requestFocus();
			}
		}
	}
	public static void main(String args[]) 
	{
		@SuppressWarnings("unused")
		HotTub ht;
		ht = new HotTub();
	}
}

