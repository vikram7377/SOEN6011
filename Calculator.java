package teamF.calc;

import java.applet.Applet;
import java.awt.Button;
import java.awt.Color;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.InputMismatchException;

import javax.swing.JOptionPane;

public class Calculator extends Applet implements ActionListener {
	
	private static final long serialVersionUID = 1L;
	TextField inp;
	String op = "";
	String num1 = "";
	String num2 = "";
	String num3 = "";
	public void init() {
		setBackground(Color.white);
		setLayout(null);
		setSize(400, 300);
		int i;
		inp = new TextField();
		inp.setBounds(0, 0, 250, 50);
		this.add(inp);
		Button button[] = new Button[10];
		int calcOrderFormat[] = { 7, 8, 9, 4, 5, 6, 1, 2, 3, 0 };
		for (i = 0; i < 9; i++) {
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

		Button operator[] = new Button[6];
		operator[0] = new Button("tan(x)");
		operator[1] = new Button("ab^x");
		operator[2] = new Button("x^y");
		operator[3] = new Button("sinh(x)");
		operator[4] = new Button("=");
		operator[5] = new Button("C");

		for (i = 0; i < 3; i++) {
			if (i % 2 == 0) {
				operator[i].setBounds(150, 50 + (i / 2 * 50), 50, 50);
				this.add(operator[i]);
				operator[i].addActionListener(this);
			} else {
				operator[i].setBounds(200, 50 + (i / 2 * 50), 50, 50);
				this.add(operator[i]);
				operator[i].addActionListener(this);
			}
		}
		operator[3].setBounds(200, 100, 50, 50);
		this.add(operator[3]);
		operator[3].addActionListener(this);
		
		operator[4].setBounds(200, 150, 50, 100);
		this.add(operator[4]);
		operator[4].addActionListener(this);
		
		operator[5].setBounds(150, 150, 50, 100);
		this.add(operator[5]);
		operator[5].addActionListener(this);
	}
	
	
	public void actionPerformed(ActionEvent e) {
			String button = e.getActionCommand();
			char ch = button.charAt(0);
			if (ch >= '0' && ch <= '9' || ch == '.' || ch == '-') {
				inp.setText(inp.getText()+ch);
			} else if (ch == 'C') {
				num1 = op = num2 = "";
				inp.setText("");
			} else if(button.equals("tan(x)")){
				inp.setText("tan(x) : x = ");
			} else if(button.equals("ab^x")){
				inp.setText("ab^x : a = ");
			} else if(button.equals("x^y")){
				inp.setText("x^y : x = ");
			} else if(button.equals("sinh(x)")){
				inp.setText("sinh(x) : x = ");
			} else if(button.equals("=")) {
				if(inp.getText().startsWith("ab^x")) {
					if(inp.getText().substring(7).contains("a")) {
						num1=inp.getText().substring(10);
						inp.setText("ab^x : b = ");
					} else if(inp.getText().substring(7).contains("b")) {
						num2=inp.getText().substring(10);
						inp.setText("ab^x : x = ");
					} else {
						try {
							num3=inp.getText().substring(11);
							F5 f5 = new F5();
							double a = Double.parseDouble(num1);
							double b = Double.parseDouble(num2);
							int x = Integer.parseInt(num3);
							inp.setText(f5.calc_F5(a, b, x)+"");
						} catch (NumberFormatException F5Exception) {
							JOptionPane.showMessageDialog(this, "Enter Valid values. a, b should be Integer or Float. x should be Integer. " + F5Exception.toString());
			            } catch (IllegalArgumentException F5Exception) {
			            	JOptionPane.showMessageDialog(this, "Undefined output. " + F5Exception.toString());
			            } catch (ArithmeticException F5Exception) {
			            	JOptionPane.showMessageDialog(this, "Overflowed result. " + F5Exception.toString());
			            } catch (Exception F5Exception) {
			            	JOptionPane.showMessageDialog(this, F5Exception.toString());
			            }
					}
				} else if(inp.getText().startsWith("tan(x)")) {
					try {
						num1=inp.getText().substring(12);
						F2 f = new F2();
						String result = F2.getTanX(num1);
						inp.setText(result);
					} catch(NumberFormatException F2Exception) {
						JOptionPane.showMessageDialog(this, F2Exception.getMessage());
					} catch(InputMismatchException F2Exception) {
						JOptionPane.showMessageDialog(this, F2Exception.getMessage());
					} catch(ArithmeticException F2Exception) {
						JOptionPane.showMessageDialog(this, F2Exception.getMessage());
					} catch(Exception F2Exception) {
						JOptionPane.showMessageDialog(this, F2Exception.getMessage());
					}
				} else if(inp.getText().startsWith("x^y")) {
					System.out.println(inp.getText().substring(3));
					if(inp.getText().substring(3).contains("x")) {
						num1=inp.getText().substring(10);
						System.out.println(num1);
						inp.setText("x^y : y = ");
					} else {
						try {
							num2=inp.getText().substring(10);
							System.out.println(num1+" "+num2);
							inp.setText(F7.myPow(Double.parseDouble(num1), Long.parseLong(num2))+"");
						} catch(IllegalArgumentException F7Exception) {
							JOptionPane.showMessageDialog(this, F7Exception.getMessage() + ".Invalid Input: the x should be floating number or integer. y should be an integer");
			            } catch (ArithmeticException F7Exception) {
			            	JOptionPane.showMessageDialog(this, F7Exception.getMessage());
			            }catch (Exception F7Exception) {
			            	JOptionPane.showMessageDialog(this, "Undefined output: " + F7Exception.toString());
			            }
					}
				}  else if(inp.getText().startsWith("sinh(x)")) {
					try {
						num1=inp.getText().substring(13);
						F3 f = new F3();
						String result = F3.getSinhx(num1);
						inp.setText(result);
					} catch(NumberFormatException F2Exception) {
						JOptionPane.showMessageDialog(this, F2Exception.getMessage());
					} catch(InputMismatchException F2Exception) {
						JOptionPane.showMessageDialog(this, F2Exception.getMessage());
					} catch(ArithmeticException F2Exception) {
						JOptionPane.showMessageDialog(this, F2Exception.getMessage());
					} catch(Exception F2Exception) {
						JOptionPane.showMessageDialog(this, F2Exception.getMessage());
					}
				}
			}
	}
}