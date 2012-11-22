package name.brucephillips.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class FixedPointTest {

	@Test
	public void testIsFixedPointPackedNegative() {

		int negativeFixedPointPacked = 0x80008000;
		
		boolean isNegative = FixedPoint.isFixedPointPackedNegative(negativeFixedPointPacked);
		
		assertEquals("FixedPoint is not negative but should be.", true, isNegative);
		
		int positiveFixedPointPacked = 0x00008000; 
		
		boolean isPositive = FixedPoint.isFixedPointPackedNegative(positiveFixedPointPacked);
		
		assertEquals("FixedPoint is not positive but should be.", false, isPositive);
	}

	@Test
	public void testGetIntegerPortionFromFixedPointPacked() {
		
		int fixedPointPacked = 0x00327eb8;
		
		int integerPortion = FixedPoint.getIntegerPortionFromFixedPointPacked(fixedPointPacked);
		
		assertEquals("Integer portion is not 100 but should be.", 100, integerPortion);

	}

	@Test
	public void testGetFractionPortionFromFixedPointPacked() {
	
		int fixedPointPacked = 0x00327eb8;
		
		float fractionPortion = FixedPoint.getFractionPortionFromFixedPointPacked(fixedPointPacked);
		
		assertTrue("Fraction portion is not approximately .99", fractionPortion < .99999 && fractionPortion > .98888);
		
		
	}

	@Test
	public void testGetFloatValue() {
	
		int fixedPointPacked = 0x00327eb8;
		
		float expectedFloatValue = 100.99f ;
		
		FixedPoint fixedPoint = new FixedPoint( fixedPointPacked );
		
		float actualFloatValue = fixedPoint.getFloatValue() ;
		
		assertTrue("Float value is not approximately " + expectedFloatValue + " but should be.", expectedFloatValue < actualFloatValue + 0.005 && expectedFloatValue > actualFloatValue - 0.0005);
	
	}

	@Test
	public void testGetFixedPointPacked() {
		
		float floatValue = -2.5f;
		
		int expectedFixedPointPacked = 0x80014000;
		
		FixedPoint fixedPoint = new FixedPoint( floatValue);
		
		int actualFixedPointPacked = fixedPoint.getFixedPointPacked() ;
		
		assertEquals("FixedPoint packed value is not " + expectedFixedPointPacked + " but should be", expectedFixedPointPacked, actualFixedPointPacked );
		
	}

}
