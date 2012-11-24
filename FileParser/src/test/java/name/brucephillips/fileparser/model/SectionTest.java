package name.brucephillips.fileparser.model;

import static org.junit.Assert.*;
import java.util.Map;

import org.junit.Test;

public class SectionTest {
	
	private static final String SECTION_NAME = "header" ;
	
	private static final String SECTION1_DATA1_KEY = "project";
	
	private static final String SECTION1_DATA1_VALUE = "Programming Test";

	@Test
	public void testGetSectionName() {
		
		Section section1 = new Section(SECTION_NAME);
		
		setupSectionData(section1);
		
		String sectionNameActual = section1.getSectionName() ;
		
		assertEquals("Section 1's name is not " + SECTION_NAME + " but should be.", 
				SECTION_NAME, sectionNameActual);
		
	}



	@Test
	public void testGetSectionData() {
		
		Section section1 = new Section(SECTION_NAME);
		
		setupSectionData(section1);
		
		Map<String, String> section1Data = section1.getSectionDataMap() ;
		
		boolean correctKey =  section1Data.containsKey(SECTION1_DATA1_KEY);
		
		boolean correctValue = section1Data.containsValue(SECTION1_DATA1_VALUE);

		assertTrue("The section data object does not contain the correct key and value but should", correctKey && correctValue);
	
	}
	
	
	private void setupSectionData(Section section1) {
		
		section1.setSectionName(SECTION_NAME);
		
		section1.getSectionDataMap().put(SECTION1_DATA1_KEY, SECTION1_DATA1_VALUE);

		section1.getSectionDataMap().put("budget", "4.5");
		

	}

}
