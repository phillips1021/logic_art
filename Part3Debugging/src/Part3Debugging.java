
public class Part3Debugging {

	/**
	 * Convert a char array to a new char array
	 * that matches the target char array.
	 * @param args
	 */
	public static void main(String[] args) {
		
		int i ;
		
		char [] buf = {0x42, 0x73, 0x75, 0x27, 0x13, 0x1C, 0x68, 0x1B, 0x64};
		
		char [] kTarget = {'A','r','t','&','L','o','g','i','c'};
		
		char [] buffer = convert( buf.clone() );
		
		for (i = 0; i < kTarget.length ; i++) {
			
			System.out.println("buf \t buffer \t target") ;
			System.out.println( buf[i] + " \t " + buffer[i] + " \t\t " + kTarget[i]);
			
			
		}
		

	}

	/**
	 * Convert each char stored in the provided 
	 * array to a new char value.
	 * @param buf array of char
	 * @return array of char where each char value 
	 * has been converted.
	 */
	private static char[] convert(char[] buf) {
		
		char space = 0x20;
		
		int i ;
		
		for (i = 0; i < buf.length; i++) {
			
			if (buf[i] > space) {
				
				buf[i] -= 1;
				
			} else {
				
				buf[i] = (char) ( (int)( buf[i] << 2 ) + 4 - i) ;
				
			}
			
			
		}
		
		
		return buf ;
		
	}

}
