import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

/**
 * Test for Polynomial class.
 * @author pragyabajoria
 *
 */
public class DensePolynomialTest {
	
	private Polynomial p1;
	private Polynomial p2;
	private Polynomial p3;
	private Polynomial p4;
	private Polynomial zero;
	private Polynomial sum1;
	private Polynomial prod1;
	private Polynomial zeroProd;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		p1 = new DensePolynomial(2,3);
		p2 = new DensePolynomial(3,2);
		p3 = new DensePolynomial(1,0);
		p4 = new DensePolynomial(1,1);
		zero = new DensePolynomial(0,0);
		sum1 = p1.add(p2);
		prod1 = p3.multiply(p4);
		zeroProd = p3.multiply(zero);
	}

	/**
	 * Test method for {@link DensePolynomial#getDegree()}.
	 */
	@Test
	public void testGetDegree() {
		Assert.assertEquals(0, zeroProd.getDegree());
		Assert.assertEquals(3, sum1.getDegree());
	}

	/**
	 * Test method for {@link DensePolynomial#getMinExponent()}.
	 */
	@Test
	public void testGetMinExponent() {
		Assert.assertEquals(2, sum1.getMinExponent());
		Assert.assertEquals(0, zeroProd.getMinExponent());
	}

	/**
	 * Test method for {@link DensePolynomial#getCoeff(int)}.
	 */
	@Test
	public void testGetCoeff() {
		Assert.assertEquals(2, p1.getCoeff(3));
		Assert.assertEquals(0, zero.getCoeff(0));
	}

	/**
	 * Test method for {@link DensePolynomial#isZero()}.
	 */
	@Test
	public void testIsZero() {
		Assert.assertTrue(zero.isZero());
		Assert.assertFalse(p1.isZero());
	}

	/**
	 * Test method for {@link DensePolynomial#add(Polynomial)}.
	 */
	@Test
	public void testAdd() {
		// Testing the sum of two regular polynomials
		Assert.assertEquals("2x^3+3x^2", sum1.toString());
		
		// Testing sum of zero and a polynomial
		Polynomial sum2 = zero.add(p1);
		Assert.assertEquals(p1.toString(), sum2.toString());
		
		// Testing sum of zero with zero
		Polynomial sum3 = zero.add(zero);
		Assert.assertEquals(zero.toString(), sum3.toString());
	}

	/**
	 * Test method for {@link DensePolynomial#multiply(Polynomial)}.
	 */
	@Test
	public void testMultiply() {
		Assert.assertEquals("x", prod1.toString());
		
		Polynomial prod2 = p1.multiply(p2);
		Assert.assertEquals("6x^5", prod2.toString());
		
		Polynomial prod3 = zero.multiply(p2);
		Assert.assertEquals(zero.toString(), prod3.toString());
	}

	/**
	 * Test method for {@link DensePolynomial#subtract(Polynomial)}.
	 */
	@Test
	public void testSubtract() {
		// Testing the sum of two regular polynomials
		Polynomial diff1 = p1.subtract(p2);
		Assert.assertEquals("2x^3-3x^2", diff1.toString());

		// Testing sum of zero and a polynomial
		Polynomial diff2 = zero.subtract(p1);
		Assert.assertEquals(p1.minus().toString(), diff2.toString());
	}

	/**
	 * Test method for {@link DensePolynomial#minus()}.
	 */
	@Test
	public void testMinus() {
		// Testing the minus of Polynomial p1 = 2x^3
		Assert.assertEquals("-2x^3", p1.minus().toString());
		
		// Testing the minus of Polynomial sum1 = 2x^3+3x^2
		Assert.assertEquals("-2x^3-3x^2", sum1.minus().toString());
		
		Assert.assertEquals("0", zero.minus().toString());
	}

	/**
	 * Test method for {@link DensePolynomial#toString()}.
	 */
	@Test
	public void testToString() {
		Assert.assertEquals("2x^3", p1.toString());
		Assert.assertEquals("0", zero.toString());
		Assert.assertEquals("1", p3.toString());
	}
}
