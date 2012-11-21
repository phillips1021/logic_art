package name.brucephillips.app;
import name.brucephillips.model.FixedPoint;

/**
 * Demonstrates how to use the FixedPoint object in a 
 * Java application.
 * @author brucephillips
 *
 */
public class FixedPointApp {

	/**
	 * Convert from a FixedPoint value stored in a 32-bit integer to a float value and
	 * from a float value to a FixedPoint value stored in a 32-bit integer.
	 * @param args - not used
	 */
	public static void main(String[] args) {
		
		int [] fixedPointPackedArray = {0x00008000,0x80008000,0x00010000,0x80014000,0x000191eb,0x00327eb8};
		
		for ( int fixedPointPacked : fixedPointPackedArray ) {
			
			FixedPoint fixedPoint = new FixedPoint( fixedPointPacked ) ;

			System.out.printf("A packed FixedPoint of 0x%08X equals %02f", fixedPointPacked, fixedPoint.getFloatValue() ) ;
			
			System.out.println();

		}
		
		
		float [] floatValuesArray = {1.0f, -1.0f, 2.0f, -2.5f, 3.14f, 100.99f};
		
		for (float floatValue : floatValuesArray) {
			
			FixedPoint fixedPoint = new FixedPoint( floatValue );
			
			System.out.printf("A float value of %02f equals a packed FixedPoint of  0x%08X", floatValue, fixedPoint.getFixedPointPacked() ) ;
			
			System.out.println();

		}
		
		System.out.println("The next usage of FixedPoint demonstrates what happens if the float value is too large or too small.");
		
		try {
			
			float aBigFloat = 999999999999.99999f;

			FixedPoint fixedPoint = new FixedPoint( aBigFloat );
		
			System.out.printf("A float value of %02f equals a packed FixedPoint of  0x%08X", aBigFloat, fixedPoint.getFixedPointPacked() ) ;
		
			System.out.println();
			
		} catch (Exception ex) {
			
			System.out.println("An error occurred - " + ex.getMessage() );
			
		}
		
		System.out.println("Display the FixedPoint value as a String.") ;
		
		FixedPoint fixedPoint = new FixedPoint( 0x80008000 ) ;

		System.out.println("A packed FixedPoint of 0x80008000 equals " + fixedPoint.toFloatString() ) ;


	}

}
