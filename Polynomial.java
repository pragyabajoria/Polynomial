public interface Polynomial {
     /**
	 * Returns the degree of the polynomial.
	 * 
	 * @return the largest exponent with a non-zero coefficient.  If all
     * terms have zero exponents, it returns 0.
	 */
	public int getDegree();
	
	/**
	 * Returns the smallest exponent that appears in the polynomial.
     *
	 * @return the smallest exponent with a non-zero coefficient.  If all
       * terms have zero exponents, it returns 0.
	 */
	public int getMinExponent ();

	/**
	 * Returns the coefficient corresponding to the given exponent.  
     * Returns 0 if there is no term with that exponent in the polynomial.
	 *
	 * @param d the exponent whose coefficent is returned.
	 * @return the coefficient of the term of whose exponent is d.
	 */
	public int getCoeff (int d);
	
	/**
	 * @return true if the polynomial represents the zero constant
	 */
	public boolean isZero();
	
     /**
	 * Returns a polynomial by adding the parameter to this.  Neither this
     * nor the parameter are modified.
	 * 
	 * @param q the polynomial to add to this.  q should not be null.
	 * @return this + q
	 * @exception NullPointerException if q is null
	 */
	public Polynomial add (Polynomial q);
	
	/**
	 * Returns a polynomial by multiplying the parameter with this.  
     * Neither this nor the parameter are modified.  q should not be null.
	 * 
	 * @param q the polynomial to multiply with this
	 * @return this * q
	 * @exception NullPointerException if q is null
	 */
	public Polynomial multiply (Polynomial q);
	
	/**
	 * Returns a  polynomial by subtracting the parameter from this.  
     * Neither this nor the parameter are modified.
	 * 
	 * @param q the polynomial to subtract from this.  q should not be 
     * null.
	 * @return this - q
	 * @exception NullPointerException if q is null
	 */
	public Polynomial subtract (Polynomial q);
	
	/**
	 * Returns a polynomial by negating this.  this is not modified.
	 * @return -this
	 */
	public Polynomial minus ();
	
	/**
	 * Returns true if the object's class invariant holds
	 * @return true iff the class invariant holds
	 */
	public boolean wellFormed ();
}
