
package name.brucephillips.fileparser.model;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Represents a section of data.
 * @author brucephillips
 *
 */
public class Section 
{
	
	private String sectionName ;
	
	
	private Map<String, String> sectionDataMap ;
	
	
	public Section( String sectionName ) {
		
		this.sectionName = sectionName ;
		
		sectionDataMap = new LinkedHashMap<String,String>();
	
	}



	public String getSectionName() 
	{
		return sectionName;
	}


	public void setSectionName(String sectionName) 
	{
		this.sectionName = sectionName;
	}


	public Map<String, String> getSectionDataMap() {
		return sectionDataMap;
	}


	public void setSectionDataMap(Map<String, String> sectionDataMap) {
		this.sectionDataMap = sectionDataMap;
	}
	
	
}
	
	


