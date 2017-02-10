package week7;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.text.DecimalFormat;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;


public class DistanceConverter extends JPanel
{
	static final long serialVersionUID = 1L;
	JButton Ccalculatebutton;
	JButton CexitButton;
	JTextField CMeterField;
	JTextField CFeetField;
	JTextField CMiliMetersField;
	JTextField CInchesField;
	JLabel CMeterlabel;
	JLabel CFeetlabel;
	JLabel CMiliMeterslabel;
	JLabel CIncheslabel;
	JLabel CErrorFeet;

	public DistanceConverter()
	{

		setPreferredSize(new Dimension(400, 300));
		CexitButton = new JButton("Exit");
		CFeetlabel = new JLabel("Feet: ");
		CMiliMeterslabel = new JLabel("MiliMeters: ");
		CMeterlabel = new JLabel("Meters: ");
		CIncheslabel = new JLabel("Inches: ");
		CErrorFeet = new JLabel("");
		CMeterField =  new JTextField(3);
		CFeetField =  new JTextField(3);
		CInchesField = new JTextField(3);
		CMiliMetersField = new JTextField(5);
		CInchesField.setEditable(false);
		CMeterField.setEditable(false);
		CMiliMetersField.setEditable(false);
		Ccalculatebutton = new JButton("Convert Units");

		setLayout( new FlowLayout());

		add(CFeetlabel);
		add(CFeetField);
		add(CIncheslabel);
		add(CInchesField);
		add(CMeterlabel);
		add(CMeterField);
		add(CMiliMeterslabel);
		add(CMiliMetersField);
		add(Ccalculatebutton);
		add(CexitButton);
		add(CErrorFeet);

		Ccalculatebutton.setMnemonic('c');
		CexitButton.setMnemonic('X');

		CcalculatebuttonHandler chandler = new CcalculatebuttonHandler();
		Ccalculatebutton.addActionListener(chandler);

		CexitButtonHandler ehandler = new CexitButtonHandler();
		CexitButton.addActionListener(ehandler);

		FocusHandler fhandler = new FocusHandler();
		CMeterField.addFocusListener(fhandler);
		CFeetField.addFocusListener(fhandler);
		CMiliMetersField.addFocusListener(fhandler);
		CInchesField.addFocusListener(fhandler);

	}
	class CcalculatebuttonHandler implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			DecimalFormat num = new DecimalFormat(",###.##");
			double feet, inches, meters, milimeters;

			String inString;

			inString = CFeetField.getText();
			if (inString.equals("")) 
			{
				inString = "0";
				CFeetField.setText("0");
				CErrorFeet.setText("                               Enter a value for the feet.             ");
			}

			feet = Double.parseDouble(inString);
			
			if(feet > 0){
				CErrorFeet.setText("");
			}
			

			inches = Double.parseDouble(inString);

			inches = feet * 12; 
			meters = feet * 0.3048;
			milimeters = feet * 304.8; 
			 
			CInchesField.setText(num.format(inches));
			CMeterField.setText(num.format(meters));
			CMiliMetersField.setText(num.format(milimeters));

		}
	}
	class CexitButtonHandler implements ActionListener
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
			if (e.getSource() == CMeterField || e.getSource() == CFeetField || e.getSource() == CMiliMetersField)
			{
				CInchesField.setText("");
			}
			else if (e.getSource() == CInchesField)
			{
				Ccalculatebutton.requestFocus();
			}
		}
		public void focusLost(FocusEvent e)
		{
			if (e.getSource() == CMiliMetersField)
			{
				Ccalculatebutton.requestFocus();
			}
		}
	}
	public static void main(String args[]) 
	{
		@SuppressWarnings("unused")
		DistanceConverter DC;
		DC = new DistanceConverter();
	}
}



