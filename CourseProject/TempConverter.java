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


public class TempConverter extends JPanel
{
	static final long serialVersionUID = 1L;
	JButton Tcalculatebutton;
	JButton TexitButton;
	JTextField TFahrenheitField;
	JTextField TcelsiusField;
	JLabel TFahrenheitlabel;
	JLabel Tcelsiuslabel;
	JLabel CErrorFahrenheit;

	public TempConverter()
	{

		setPreferredSize(new Dimension(400, 300));
		TexitButton = new JButton("Exit");
		Tcelsiuslabel = new JLabel("                     Celsius: ");
		TFahrenheitlabel = new JLabel("                      Fahrenheit: ");
		TFahrenheitField =  new JTextField(4);
		TcelsiusField =  new JTextField(4);
		CErrorFahrenheit = new JLabel("");
		TcelsiusField.setEditable(false);
		Tcalculatebutton = new JButton("Convert Units");

		setLayout( new FlowLayout());

		add(TFahrenheitlabel);
		add(TFahrenheitField);
		add(Tcelsiuslabel);
		add(TcelsiusField);
		add(Tcalculatebutton);
		add(TexitButton);
		add(CErrorFahrenheit);

		Tcalculatebutton.setMnemonic('c');
		TexitButton.setMnemonic('X');

		TcalculatebuttonHandler chandler = new TcalculatebuttonHandler();
		Tcalculatebutton.addActionListener(chandler);

		TexitButtonHandler ehandler = new TexitButtonHandler();
		TexitButton.addActionListener(ehandler);

		FocusHandler fhandler = new FocusHandler();
		TFahrenheitField.addFocusListener(fhandler);
		TcelsiusField.addFocusListener(fhandler);

	}
	class TcalculatebuttonHandler implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			DecimalFormat num = new DecimalFormat(",###.##");
			double fahrenheit, celsius;

			String inString;

			inString = TFahrenheitField.getText();
			if (inString.equals("")) 
			{
				inString = "0";
				TcelsiusField.setText("0");
				CErrorFahrenheit.setText("                                    Please enter a valid temperature");
			}

			fahrenheit = Double.parseDouble(inString);

			celsius = ((fahrenheit - 32) * 5) / 9;
			 
			TcelsiusField.setText(num.format(celsius));
		}
	}
	class TexitButtonHandler implements ActionListener
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
			if (e.getSource() == TFahrenheitField || e.getSource() == TcelsiusField)
			{
				TFahrenheitField.setText("");
			}
			else if (e.getSource() == TcelsiusField)
			{
				Tcalculatebutton.requestFocus();
			}
		}
		public void focusLost(FocusEvent e)
		{
			if (e.getSource() == TFahrenheitField)
			{
				Tcalculatebutton.requestFocus();
			}
		}
	}
	public static void main(String args[]) 
	{
		@SuppressWarnings("unused")
		TempConverter TC;
		TC = new TempConverter();
	}
}

