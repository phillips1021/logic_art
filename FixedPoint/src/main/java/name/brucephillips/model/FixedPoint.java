package name.brucephillips.model;


/**
 * An abstract data type that maintains the 
 * state of an object that stores float
 * values in a 32-bit integer.
 * The first bit is the sign (1 for negative).
 * The next 16 bits are the integer portion.
 * The last 15 bits are the fraction portion.
 * @author brucephillips
 *
 */
public class FixedPoint 
{
	
	private static final float MAX_VALUE = 65535.99997f;
	
	private static final float MIN_VALUE = -65535.99997f ;

	private int integerPortion ;
	
	private float fractionPortion ;
	
	private boolean isNegative ;
	
	/**
	 * Overloaded constructor that takes the provided 
	 * 32 bit integer that has the three parts of a
	 * FixedPoint object.
	 * @param fixedPointPacked 32-bit integer in the format of 
	 * 1st bit is the sign (1 is negative), next 16 bits 
	 * are the integer portion, and last 15 bits are the
	 * fraction portion.
	 */
	public FixedPoint( int fixedPointPacked) 
	{
		
		isNegative = isFixedPointPackedNegative(fixedPointPacked) ;
		
		integerPortion = getIntegerPortionFromFixedPointPacked(fixedPointPacked)  ;
		
		fractionPortion = getFractionPortionFromFixedPointPacked(fixedPointPacked)  ;
	}
	
	/**
	 * Overloaded constructor that takes the provided 
	 * float value and creates creates a FixedPoint object
	 * by converting the floatValue into the three parts
	 * of a FixedPoint object.
	 * @param floatValue 
	 */
	public FixedPoint (float floatValue) 
	{
		
		if (floatValue > MAX_VALUE ||floatValue < MIN_VALUE ) 
		{
			
			throw  new IllegalArgumentException("The floatValue you provided " + 
			  floatValue + " is too large or too small.  The float value must be between " +
					" -65535.99997 and 65535.99997");
			
		}
		
		if (floatValue < 0 ) 
		{
			
			isNegative = true ;
			
			floatValue = Math.abs(floatValue);
			
		} 
		else 
		{
			
			isNegative = false ;
		}
		
		integerPortion =  (int) floatValue  ;
		
		fractionPortion =  floatValue % 1 ;

	}
	
    /**
     * Determines if the provided 32-bit integer
     * representation of a FixedPoint object is
     * is storing a negative value.
     * @param fixedPointPacked 32-bit integer in the format of 
	 * 1st bit is the sign (1 is negative), next 16 bits 
	 * are the integer portion, and last 15 bits are the
	 * fraction portion.
     * @return true if fixedPointPacked is storing a negative value 
     * otherwise false
     */
	public static boolean isFixedPointPackedNegative(int fixedPointPacked) 
	{

		return (fixedPointPacked >> 31) < 0 ? true : false ;
	
	}
	
	
	/**
	 * Gets the integer value being stored in the provided
	 * FixedPoint object packed into a 32-bit integer.
	 * @param fixedPointPacked 32-bit integer in the format of 
	 * 1st bit is the sign (1 is negative), next 16 bits 
	 * are the integer portion, and last 15 bits are the
	 * fraction portion.
	 * @return integer value stored in the fixedPointPacked
	 */
	public static int getIntegerPortionFromFixedPointPacked(int fixedPointPacked) 
	{
		
		int integerPortion = 0;
		
		if ( isFixedPointPackedNegative(fixedPointPacked) ) 
		{
			
			integerPortion = fixedPointPacked << 1;
			
			integerPortion = integerPortion >>> 16;
			
		} 
		else 
		{

			integerPortion = fixedPointPacked >> 15 ;
		}
		
		return integerPortion ;
		
	}
	
	/**
	 * Gets the fraction value being stored in the provided
	 * fixedPointPacked value.
	 * @param fixedPointPacked 32-bit integer in the format of 
	 * 1st bit is the sign (1 is negative), next 16 bits 
	 * are the integer portion, and last 15 bits are the
	 * fraction portion.
	 * @return fraction value stored in the provided fixedPointPacked
	 */
	public static float getFractionPortionFromFixedPointPacked(int fixedPointPacked) 
	{
		
		float fractionPortion = 0.0f;
		
		int fractionPortionInt = (fixedPointPacked << 17);
		
		fractionPortionInt = (fractionPortionInt >>> 17);
		
		fractionPortion = fractionPortionInt / (float)0x8000;

		return fractionPortion ;

	}
	
	
	/**
	 * Gets the float value of this FixedPoint object.
	 * @return float value
	 */
	public float getFloatValue() 
	{
		
		float floatValue = integerPortion + fractionPortion ;
		
		if ( isNegative ) 
		{
			
			floatValue = -floatValue ;
			
		}
		
		return floatValue ;
		
	}
	
	
	/**
	 * Creates a 32-bit integer that stores the
	 * sign value in the 1st bit, the integer 
	 * value in the next 16 bits, and the fraction
	 * value in the last 15 bits.
	 * @return 32-bit integer representing the
	 * float value packed into it.
	 */
	public int getFixedPointPacked() 
	{
		
		int fixedPointPacked = integerPortion << 15;
		
		fixedPointPacked = fixedPointPacked | (int) (fractionPortion *  (float)(0x8000)  );
		
		if (isNegative) 
		{
			
				fixedPointPacked = (fixedPointPacked | 0x80000000);
		}
		
		return fixedPointPacked ;
		
	}
	
	/**
	 * Returns the float value as a String.
	 * @return float value as a String
	 */
	public String toFloatString() 
	{
		
		return String.valueOf( getFloatValue() ) ;
		
	}

	
	@Override
	public String toString() 
	{
		return "FixedPoint [integerPortion=" + integerPortion
				+ ", fractionPortion=" + fractionPortion + ", isNegative="
				+ isNegative + "]";
	}

	public int getIntegerPortion() 
	{
		return integerPortion;
	}

	public void setIntegerPortion(int integerPortion) 
	{
		this.integerPortion = integerPortion;
	}

	public float getFractionPortion() 
	{
		return fractionPortion;
	}

	public void setFractionPortion(float fractionPortion) 
	{
		this.fractionPortion = fractionPortion;
	}

	public boolean isNegative() 
	{
		return isNegative;
	}

	public void setNegative(boolean isNegative) 
	{
		this.isNegative = isNegative;
	}
	
	

}
