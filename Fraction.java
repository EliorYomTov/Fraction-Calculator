
public class Fraction {
	private int m_numerator;
	private int m_denominator;

	public Fraction() {
		this(0);
	}

	public Fraction(int numerator) {
		this(numerator, 1);
	}

	public Fraction(int numerator, int denominator) {
		if (denominator == 0)
			throw new IllegalArgumentException("The denominator cannot be zero!");
		if (denominator < 0) {
			denominator = Math.abs(denominator);
			numerator *= -1;
		}
		this.m_numerator = numerator;
		this.m_denominator = denominator;
	}

	public int getM_numerator() {
		return m_numerator;
	}

	public int getM_denominator() {
		return m_denominator;
	}

	@Override
	public String toString() {
		return m_numerator + "/" + m_denominator;
	}

	public double toDouble() {
		return (double) m_numerator / (double) m_denominator;
	}

	public Fraction add(Fraction other) {
		Fraction tmpFrac = new Fraction();
		int lcm = lcm(this.m_denominator, other.m_denominator);
		tmpFrac.m_numerator = (lcm / this.m_denominator * this.m_numerator)
				+ (lcm / other.m_denominator * other.m_numerator);
		tmpFrac.m_denominator = lcm;
		tmpFrac.reduce();
		return tmpFrac;
	}

	public Fraction subtract(Fraction other) {
		int doublingInMinus = 1;
		Fraction tmpFrac = new Fraction(this.m_numerator, this.m_denominator);
		if (tmpFrac.m_numerator < 0 || other.m_numerator < 0)
			doublingInMinus *= -1;
		int lcm = lcm(this.m_denominator, other.m_denominator);
		tmpFrac.m_numerator = Math
				.abs(((this.m_numerator * other.m_denominator) - (other.m_numerator * this.m_denominator)))
				* doublingInMinus;
		tmpFrac.m_denominator = lcm;
		tmpFrac.reduce();
		return tmpFrac;
	}

	public Fraction multiply(Fraction other) {
		Fraction tmpFrac = new Fraction(this.m_numerator, this.m_denominator);
		tmpFrac.m_numerator *= other.m_numerator;
		tmpFrac.m_denominator *= other.m_denominator;
		tmpFrac.reduce();
		return tmpFrac;
	}

	public Fraction divide(Fraction other) {
		if (other.m_denominator == 0)
			throw new IllegalArgumentException("Number cannot be divided by 0 !");
		Fraction tmpFrac = new Fraction(this.m_numerator, this.m_denominator);
		tmpFrac.m_numerator *= other.m_denominator;
		tmpFrac.m_denominator *= other.m_numerator;
		tmpFrac.reduce();
		return tmpFrac;
	}

	public static int gcd(int a, int b) {
		int remainder;
		while (b != 0) {
			remainder = a % b;
			a = b;
			b = remainder;
		}
		return a;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Fraction) {
			Fraction otherFraction = (Fraction) obj;
			this.reduce();
			otherFraction.reduce();
			if (this.toDouble() == otherFraction.toDouble())
			return true;
		}
		return false;
	}

	private static int lcm(int a, int b) {
		return (a / gcd(a, b)) * b;
	}

	private void reduce() {
		int gcd = gcd(m_numerator, m_denominator);
		this.m_numerator = m_numerator / gcd;
		this.m_denominator = m_denominator / gcd;
	}

}
