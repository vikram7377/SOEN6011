package teamF.calc;

import java.util.InputMismatchException;

public class F2 {

	final static double pi = 3.14159;

	static double tanXPolynomialOperations(double x) {
		if (x == 0 || x == 180)
			return 0.0;
		else if (x == 45)
			return 1.0;
		else if (x < 22.5) {
			x = x * (pi / 180);
			double cube = x * x * x;
			double square = x * x;
			return x + ((cube) / 3) + ((2 * (square * cube)) / 15) + ((17 * (cube * cube * x)) / 315);
		} else if (x >= 22.5 && x < 45)
			return (2 * (tanXPolynomialOperations(x / 2))) / (1 - (tanXPolynomialOperations(x / 2) * tanXPolynomialOperations(x / 2)));
		else if (x >= 45 && x < 90)
			return 1 / tanXPolynomialOperations(90 - x);
		else
			return -tanXPolynomialOperations(180 - x);
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