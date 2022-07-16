package teamF.calc;

import java.util.InputMismatchException;

public class F2 {


	public static double reducesAnguleTaxX(double x) {
		x = x % 360;
		x = x > 180 ? x - 180 : x;
		x = x < 0 ? 180 + x : x;
		return x;
	}
	
	public static double reducesAnguleTaxX(double x) {
		x = x % 360;
		x = x > 180 ? x - 180 : x;
		x = x < 0 ? 180 + x : x;
		return x;
	}
	
	public static String getTanX(String value) {
		try {
			double x = Double.parseDouble(value);
			if(Double.isNaN(x) || Double.isInfinite(x)) {
				 throw new ArithmeticException("Value is NaN or not finite");
			 } else if(x%90==0 && (x/90)%2!=0) {
				 throw new InputMismatchException("Value must not be the odd multiple of pi/2");
			 } else{
				x = reducesAnguleTaxX(x);
				return String.valueOf(String.format("%.5f", tanXPolynomialOperations(x)));
			 }
		}catch(NumberFormatException e) {
			throw new NumberFormatException("Input should be a real number");
		}
	}
}