package name.brucephillips.fileparser.model;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

public class SectionTest {
	
	private static final String SECTION_NAME = "header" ;
	
	private static final String SECTION1_DATA1_KEY = "project";
	
	private static final String SECTION1_DATA1_VALUE = "Programming Test";

	@Test
	public void testGetSectionName() {
		
		Section section1 = new Section();
		
		setupSectionData(section1);
		
		String sectionNameActual = section1.getSectionName() ;
		
		assertEquals("Section 1's name is not " + SECTION_NAME + " but should be.", 
				SECTION_NAME, sectionNameActual);
		
	}



	@Test
	public void testGetSectionData() {
		
		Section section1 = new Section();
		
		setupSectionData(section1);
		
		Map<String, String> section1Data1 = section1.getSectionDataList().get(0);
		
		boolean correctKey =  section1Data1.containsKey(SECTION1_DATA1_KEY);
		
		boolean correctValue = section1Data1.containsValue(SECTION1_DATA1_VALUE);

		assertTrue("The section data object does not contain the correct key and value but should", correctKey && correctValue);
	
	}
	
	
	private void setupSectionData(Section section1) {
		
		section1.setSectionName(SECTION_NAME);
		
		List <Map<String, String>> sectionDataList = new ArrayList<Map<String, String>>();

		Map<String, String> section1Data1 = new HashMap<String, String>();

		section1Data1.put(SECTION1_DATA1_KEY, SECTION1_DATA1_VALUE);
		
		sectionDataList.add(section1Data1);
		
		
		Map<String, String> section1Data2 = new HashMap<String, String>();

		section1Data2.put("budget", "4.5");
		
		sectionDataList.add(section1Data2);
		
	    section1.setSectionDataList(sectionDataList);
	}

}
