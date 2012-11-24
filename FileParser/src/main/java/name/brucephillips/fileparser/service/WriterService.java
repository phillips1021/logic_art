
package name.brucephillips.fileparser.service;

import java.io.IOException;

/**
 * Specifies what behaviors a WriterService class
 * must implement to write an Object of Type T that contains
 * a collection of String headers and within
 * each header section a collection of key:value 
 * pairs.
 * @author brucephillips
 *
 */
public interface WriterService<T> 
{
	
	
	void write( T t) throws IOException ;
	

}
