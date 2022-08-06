package soen_6011;

import java.applet.Applet;
import java.awt.Button;
import java.awt.Color;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.InputMismatchException;

import javax.swing.JOptionPane;

public class Calculator extends Applet implements ActionListener 
{

	private static final long serialVersionUID = 1L;
	TextField inp;
	String op = "";
	String num1 = "";
	String num2 = "";
	String num3 = "";

	public void init() 
	{
		setBackground(Color.white);
		setLayout(null);
		setSize(400, 300);
		int i;
		inp = new TextField();
		inp.setBounds(0, 0, 250, 50);
		this.add(inp);
		Button button[] = new Button[10];
		int calcOrderFormat[] = { 7, 8, 9, 4, 5, 6, 1, 2, 3, 0 };
		for (i = 0; i < 9; i++) 
		{
			button[i] = new Button(String.valueOf(calcOrderFormat[i]));
			button[i].setBounds(0 + ((i % 3) * 50), 50 + ((i / 3) * 50), 50, 50);
			this.add(button[i]);
			button[i].addActionListener(this);
		}

		Button dec = new Button(".");
		dec.setBounds(0, 200, 50, 50);
		this.add(dec);
		dec.addActionListener(this);

		Button zero = new Button(String.valueOf(0));
		zero.setBounds(50, 200, 50, 50);
		this.add(zero);
		zero.addActionListener(this);

		Button clr = new Button("-");
		clr.setBounds(100, 200, 50, 50);
		this.add(clr);
		clr.addActionListener(this);

		Button operator[] = new Button[3];
		operator[0] = new Button("tan(x)");
		operator[1] = new Button("=");

		operator[0].setBounds(150, 50 + (0 / 2 * 50), 50, 50);
		this.add(operator[0]);
		operator[0].addActionListener(this);
		operator[1].setBounds(150, 100, 50, 50);
		this.add(operator[1]);
		operator[1].addActionListener(this);

	}

	public void actionPerformed(ActionEvent e) 
	{
		String button = e.getActionCommand();
		char ch = button.charAt(0);
		if (ch >= '0' && ch <= '9' || ch == '.' || ch == '-') 
		{
			inp.setText(inp.getText() + ch);
		} else if (ch == 'C') 
		{
			num1 = op = num2 = "";
			inp.setText("");
		} 
		else if (button.equals("tan(x)")) 
		{
			inp.setText("tan(x) : x = ");
		}    
		else if (inp.getText().startsWith("tan(x)")) 
		{
				try 
				{
					num1 = inp.getText().substring(12);
					F2 f = new F2();
					System.out.println(num1);
					String result = F2.getTanX(num1);
					inp.setText(result);
				} 
				catch (NumberFormatException F2Exception) 
				{
					JOptionPane.showMessageDialog(this, F2Exception.getMessage());
				} 
				catch (InputMismatchException F2Exception) 
				{
					JOptionPane.showMessageDialog(this, F2Exception.getMessage());
				} 
				catch (ArithmeticException F2Exception) 
				{
					JOptionPane.showMessageDialog(this, F2Exception.getMessage());
				} 
				catch (Exception F2Exception) 
				{
					JOptionPane.showMessageDialog(this, F2Exception.getMessage());
				}
			}
		}
}
