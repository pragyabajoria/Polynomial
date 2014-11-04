/**
 * Implements a dense polynomial with operations such as add, subtract, multiply, minus.
 * @author pragyabajoria
 *
 */
public class DensePolynomial implements Polynomial {
	
	/* Largest exponent with a non-zero coefficient in the polynomial. */
	private int degree;
	
	/* Minimum exponent in the polynomial. */
	private int minExp;
	
	/* An array to store the coefficients of a polynomial where the exponent is given by index. */
	private int coeff[];

	/**
	 * Constructor that initializes the Dense Polynomial
	 * 
	 * Precondition: The exponent of a polynomial cannot be negative, and should only be an integer.
	 * Postcondition: A polynomial has been created with the given exponent and coefficient.
	 * 
	 * @param c a single coefficient in the polynomial
	 * @param exp the exponent of the coefficient in the polynomial
	 */
	public DensePolynomial(int c, int exp) {
		super();
		
		// Precondition to check if the exponent is non-negative.
		assert(exp > -1);
		
		// Create a new array of coefficients of size exponent + 1
		coeff = new int[exp + 1];
		coeff[exp] = c;
		
		// Sets the degree and min exponent to the current exponent if the coefficient is not 0.
		degree = exp;
		minExp = exp;
		
		// Maintain class invariant
		assert(wellFormed());
	}
	
	/**
	 * Returns the degree of the polynomial.
	 * 
	 * Postcondition: The return value is the degree of the polynomial. If all terms have zero exponents, it returns 0.
	 * 
	 * @return the largest exponent with a non-zero coefficient.  If all
	 * terms have zero exponents, it returns 0.
	 */
	@Override
	public int getDegree() {
		// Check class invariants
		assert (degree == coeff.length-1);
		
		return degree;
	}

	/**
	 * Returns the smallest exponent that appears in the polynomial.
	 * 
	 * Postcondition: The return value is the minimum exponent of the polynomial with a non-zero coefficient. 
	 * 				  If all terms have zero exponents, it returns 0.
     *
	 * @return the smallest exponent with a non-zero coefficient.  If all
     * terms have zero exponents, it returns 0.
	 */
	@Override
	public int getMinExponent() {
		// Post-condition to check that min exponent is greater than 0
		assert (minExp > 0);
		assert (degree >= minExp);
		assert (this.isZero() && minExp == 0);
		return minExp;
	}

	/**
	 * Returns the coefficient corresponding to the given exponent.  
     * Returns 0 if there is no term with that exponent in the polynomial.
	 *
	 * Postcondition: If d is less than or equal to the degree of the polynomial, then the return value is the coefficient
	 *                of x^d. Else, the return value is 0.
	 * 
	 * @param d the exponent whose co-efficent is returned.
	 * @return the coefficient of the term of whose exponent is d.
	 */
	@Override
	public int getCoeff(int d) {
		//Precondition to check that index d is greater than -1, and less than or equal to the degree of the polynomial.
		assert (d >= 0 && d <= degree) : "The exponent should be greater than -1 and less than the degree of the polynomial.";
		
		// Returns the coefficient at exponent d if the exponent exists else returns 0
		return coeff[d] != -1 ? coeff[d] : 0;
	}
	
	/**
	 * Sets the coefficient corresponding to the given exponent.  
     * Returns 0 if there is no term with that exponent in the polynomial.
	 *
	 * Precondition: The exponent exp is greater than equal to 0 and less than or equal to the length of the 
	 *               coefficients array 'coeff'
	 * Postcondition: The coefficient of the x^exp term has been set to c. 
	 * 				  The degree of the polynomial is modified if required while maintaining the class invariants.
	 *
	 * @param exp the exponent whose coefficent is to be set.
	 * @param c the coefficient of the exponent
	 */
	private void setCoeff(int exp, int c) {
		
		// Preconditions
		assert(exp > -1 && exp <= degree) : "The exponent is greater than the degree of the polynomial.";
		assert(c != 0) : "The coefficient should not be equal to 0";
		
		coeff[exp] = c;
		
		// Updates the degree and minExp.
		if (degree < exp) {
			degree = exp;
		}
		
		if (minExp > exp) {
			minExp = exp;
		}
		
		// Post-condition to check for the class invariants
		assert(wellFormed());
	}
	
	/**
	 * @return true if the polynomial represents the zero constant
	 */
	@Override
	public boolean isZero() {
		// Invariant
		assert(wellFormed());
		
		// Checks if the length of the array is 1, and the coefficient is 0.
		return coeff.length == 1 && coeff[0] == 0;
	}

	/**
	 * Returns a polynomial by adding the parameter to this.  Neither this
     * nor the parameter are modified.
	 * 
	 * Precondition: q is not null.
	 * Postcondition: Returns the sum of the two polynomials such that the class invariants are maintained.
	 * 
	 * @param q the polynomial to add to this.  q should not be null.
	 * @return this + q
	 * @exception NullPointerException if q is null
	 */
	@Override
	public Polynomial add(Polynomial q) {
		// Precondition to check if q is not null.
		assert q != null : "parameter is null";
		
		// Create a new Dense Polynomial with the higher degree of the two given polynomials as its degree
		DensePolynomial sum = new DensePolynomial(0, Math.max(this.getDegree(), q.getDegree()));
		
		// Add the non-zero coefficients of both polynomials for each exponent.
		for (int i = 0; i <= Math.max(this.getDegree(), q.getDegree()); i++) {	
			int val = 0;
			if (i <= this.getDegree()) {
				val = this.getCoeff(i);
			}
			if (i <= q.getDegree()) {
				val += q.getCoeff(i);
			}
			// Set the coefficient if it is not zero.
			if (val != 0) {
				sum.setCoeff(i, val);
			}
		}
		// Postcondition to check the class invariants.
		assert(sum.wellFormed()) : "Inconsistent degree in add()";
		
		return sum;
	}
	
	/**
	 * Returns a polynomial by multiplying the parameter with this.  
     * Neither this nor the parameter are modified.  q should not be null.
	 * 
	 * Precondition: q is not null.
	 * Postcondition: Returns the product of the two polynomials such that the class invariants are maintained. If one 
	 *     			  of the polynomials is zero, it returns 0.
	 * 
	 * @param q the polynomial to multiply with this
	 * @return this * q
	 * @exception NullPointerException if q is null
	 */
	@Override
	public Polynomial multiply(Polynomial q) {
		// Precondition to check if q is null.
		assert q != null : "parameter is null";
		
		// Returns 0 as product if either of the polynomials is 0.
		if (this.isZero() || q.isZero()) {
			return new DensePolynomial(0,0);
		}
		
		// Create a new DensePolynomial with the product of the degree of the two given polynomials as its degree.
		DensePolynomial prod = new DensePolynomial(0, this.getDegree() + q.getDegree());
		
		// Multiply the non-zero coefficients of both polynomials for each exponent.
		for (int i = 0; i <= this.getDegree(); i++) {	
			if (this.getCoeff(i) != 0) {
				for (int j = 0; j <= q.getDegree(); j++) {
					if (q.getCoeff(j) != 0) {
						prod.setCoeff(i+j, prod.getCoeff(i+j) + this.getCoeff(i) * q.getCoeff(j));
					}
				}
			}
		}
		
		// Postcondition to check for the class invariants.
		assert(prod.wellFormed()) : "Inconsistent degree in multiply()";
		
		return prod;
	}

	/**
	 * Returns a  polynomial by subtracting the parameter from this.  
     * Neither this nor the parameter are modified.
     * 
     * Precondition: q is not null.
     * Postcondition: Returns the difference of the two polynomials such that the class invariants are maintained.
	 * 
	 * @param q the polynomial to subtract from this.  q should not be 
     * null.
	 * @return this - q
	 * @exception NullPointerException if q is null
	 */
	@Override
	public Polynomial subtract(Polynomial q) {
		// Precondition to check if q is null.
		assert q != null : "parameter is null";
		
		// Create a new DensePolynomial with the product of the degree of the two given polynomials.
		DensePolynomial diff = new DensePolynomial(0, Math.max(this.getDegree(), q.getDegree()));
		
		// Subtract the coefficients of the second polynomial from the first polynomial for each exponent.
		for (int i = 0; i <= Math.max(this.getDegree(), q.getDegree()); i++) {	
			int val = 0;
			if (i <= this.getDegree()) {
				val = this.getCoeff(i);
			}
			if (i <= q.getDegree()) {
				val -= q.getCoeff(i);
			}
			if (val != 0) {
				diff.setCoeff(i, val);
			}
		}
		// Postcondition to check for the class invariants.
		assert(diff.wellFormed()) : "Inconsistent degree in subtract()";
		
		return diff;
	}

	/**
	 * Returns a polynomial by negating this.  this is not modified.
	 * 
	 * Postcondition: Returns the negation of the polynomial while maintaining the class invariants. Returns 0 if the
	 * 				  polynomial is zero.
	 * @return -this
	 */
	@Override
	public Polynomial minus() {
		
		DensePolynomial minus = new DensePolynomial(0, this.getDegree());
		for (int i = 0; i <= this.getDegree(); i++) {
			minus.setCoeff(i, -this.getCoeff(i));
		}
		assert(wellFormed()) : "Incorrect Degree in minus()";
		return minus;
	}

	/**
	 * Returns true if the object's class invariant holds
	 * @return true iff the class invariant holds
	 */
	@Override
	public boolean wellFormed() {
		return coeff.length - 1 == degree;
	}

	/**
	 * Returns a String representation of the polynomial
	 * @return If the polynomial is 0, returns 0. Else returns the string representation of the polynomial.
	 */
	@Override
	public String toString() {
		
		// Returns 0 if the polynomial is 0
		if (this.isZero()) {
			return "0";
		}
		
		// Display the polynomial in String form by concatenating each term.
		String val = "";
		for (int i = degree; i >= 0; i--) {
			int c = this.getCoeff(i);
			if (c != 0) {
				// Adds a '+' sign before a new expression, except in the first instance.
				if (!val.equals("")) { // If val is "", it is the first instance, so the '+' sign is not added.
					val += c > 0 ? "+" : ""; // Does not add a '+' operator for negative numbers since they have a '-' sign
				}
				// Adds the coefficient, and add x^.. only if the exponent is non-zero.
				// Removes coefficient 1 to show x instead of 1x
				if (i == 0) { // constant
					val += Integer.toString(c);
				} else {
					val += c == 1 ? "" : Integer.toString(c); // Coefficient
					val += "x" + (i == 1 ? "" : "^" + Integer.toString(i)); // Exponent without extra 1s
				}
			}
		}
		return val;
	}
	
	/**
	 * Main application for DensePolynomial.
	 * @param args the arguments for main()
	 */
	public static void main(String[] args) {
		/*
		Polynomial zero = new DensePolynomial(0, 0);
        Polynomial p1   = new DensePolynomial(2, 3);
        Polynomial p2   = new DensePolynomial(3, 2);
        Polynomial p3   = new DensePolynomial(1, 0);
        System.out.println("p1=" + p1.toString() + "\np2=" + p2.toString());
        Polynomial prod = p1.multiply(p2);
        System.out.println("prod = " + prod.toString());
        */
	}
}
