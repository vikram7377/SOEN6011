package teamF.calc;

import java.util.InputMismatchException;


/**
 * This class F2 is used to find the tangent value as per the Function F2.
 * 
 * Tan(x) is the trigonometric function that relates the angle x to the 
 * opposite and adjacent sides of a right-angle triangle.
 * 
 * 
 * @author WILLIAM MOSES STALIN JEBADOSS
 * @studentID 40186129
 * @created on 01-08-2021
 * @version 1.0
 *
 */
public class F2 {

	final static double pi = 3.14159;

	/**
	 * This function returns the tangent value for the angle X
	 * 
	 * @param x is the input value to find tangent
	 * 
	 * @return the tangent value
	 */
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
	
	/**
	 * This function reduces the angle that is equivalent 
	 * to the angle in the range of 0 and 360
	 * 
	 * @param x is the input degree value
	 * 
	 * @return the reduced angle value
	 */
	public static double reducesAnguleTaxX(double x) {
		x = x % 360;
		x = x > 180 ? x - 180 : x;
		x = x < 0 ? 180 + x : x;
		return x;
	}
	
	/**
     * Returns the trigonometric tangent of an angle.  Special cases:
     * <ul><li>If the argument is NaN or an infinity, then the result
     * is NaN.
     * <li>If the argument is zero, then the result is a zero with the
     * same sign as the argument.</ul>
     *
     * <p>The computed result must be within 1 ulp of the exact result.
     * Results must be semi-monotonic.
     *
     * @param   value   an angle, in radians.
     * @return  the tangent of the argument.
     */
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