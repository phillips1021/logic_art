
package name.brucephillips.fileparser.service;

import java.util.List;

import name.brucephillips.fileparser.model.Section;

/**
 * Specifies what behaviors a ParserService class
 * must implement to enable setting and getting 
 * values stored in sections and then by keys within 
 * each section.
 * @author brucephillips
 *
 */
public interface ParserService 
{
	/**
	 * Get collection of Section objects.
	 * @return collection of Section objects
	 */
	List<Section> getSectionList();
	
	/**
	 * Get the String value using the provided 
	 * section name and key.
	 * @param sectionName - String name of section
	 * @param key - String key in that section
	 * whose value should be returned.
	 * @return String value --if the section name or key is not found 
	 * then returns null.
	 */
	String getStringValue(String sectionName, String key) ;
	
	
	/**
	 * Get the Integer value using the provided 
	 * section name and key.
	 * @param sectionName - String name of section
	 * @param key - String key in that section
	 * whose Integer value should be returned.
	 * @return Integer value --if the section name or key is not found 
	 * then returns null.
	 * @throws NumberFormatException if value is found but unable to
	 * parse that value to an integer.
	 */
	Integer getIntegerValue(String sectionName, String key) throws NumberFormatException ;
	
	
	/**
	 * Get the Float value using the provided 
	 * section name and key.
	 * @param sectionName - String name of section
	 * @param key - String key in that section
	 * whose Float value should be returned.
	 * @return Float value --if the section name or key is not found 
	 * then returns null.
	 * @throws NumberFormatException if value is found but unable to
	 * parse that value to an float.
	 */
	Float getFloatValue(String sectionName, String key) throws NumberFormatException ;
	
	
	/*
	 * Set the value for the provided key in the provided section identified by the header value.
	 * @param sectionName - String name of section
	 * @param key - String key value for map entry 
	 * @param value - new value (calls Object's toString) 
	 * @param header String header name
	 * @param key
	 * @param value
	 */
	void setValue(String header, String key, Object value) ;

}
