
package name.brucephillips.fileparser.model;

import java.util.List;
import java.util.Map;

/**
 * Represents a section of data.
 * @author brucephillips
 *
 */
public class Section 
{
	
	private String sectionName ;
	
	
	private List <Map<String, String>> sectionDataList ;


	@Override
	public String toString() 
	{
		return "Section [sectionName=" + sectionName + ", sectionDataList="
				+ sectionDataList + "]";
	}


	public String getSectionName() 
	{
		return sectionName;
	}


	public void setSectionName(String sectionName) 
	{
		this.sectionName = sectionName;
	}


	public List<Map<String, String>> getSectionDataList() 
	{
		return sectionDataList;
	}


	public void setSectionDataList(List<Map<String, String>> sectionDataList) 
	{
		this.sectionDataList = sectionDataList;
	}
	
	



	
	

}
