package name.brucephillips.fileparser.service;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.Map;

import name.brucephillips.fileparser.model.Section;

import org.junit.Test;

public class FileParserServiceTest {

	@Test
	public void testParsingFileForSectionHeaders() throws IOException {
		
		String filePath = "/apps";
		
		String fileName = "testfile.txt" ;
		
		String header1 = "header";
		
		String header2 = "meta data";
		
		String header3 = "trailer";
		
		ParserService fileParserService = new FileParserService(filePath, fileName);
		
		Section section = fileParserService.getSectionList().get(0);
		
		String header1Actual = section.getSectionName() ;

		assertEquals("Section header 1 is not " + header1 + " but should be.", header1, header1Actual);

		section = fileParserService.getSectionList().get(1);
		
		String header2Actual = section.getSectionName() ;

		assertEquals("Section header 2 is not " + header2 + " but should be.", header2, header2Actual);
		
		section = fileParserService.getSectionList().get(2);
		
		String header3Actual = section.getSectionName() ;
		
		assertEquals("Section header 3 is not " + header3 + " but should be.", header3, header3Actual);

	}
	
	@Test
	public void testParsingFileForSectionDataLists() throws IOException {
		
		String filePath = "/apps";
		
		String fileName = "testfile.txt" ;
		
		ParserService fileParserService = new FileParserService(filePath, fileName);
		
		int expectedNumberOfSectionHeaders = 3;
		
		int actualNumberOfSectionHeaders = fileParserService.getSectionList().size() ;
		
		assertEquals("number of section headers is not " + expectedNumberOfSectionHeaders + " but should be.", 
				expectedNumberOfSectionHeaders, actualNumberOfSectionHeaders) ;
		
		int expectedNumberOfKeyValuesInASecton = 3 ;
		
		Map<String,String> sectionDataMap = fileParserService.getSectionList().get(0).getSectionDataMap() ;
		
		int actualNumberOfKeyValuesInASecton = sectionDataMap.size();
		
		assertEquals("Number of map entries in section " + fileParserService.getSectionList().get(0).getSectionName() +
				" is not " + expectedNumberOfKeyValuesInASecton + " but should be.", 
				expectedNumberOfKeyValuesInASecton, actualNumberOfKeyValuesInASecton );
		
		expectedNumberOfKeyValuesInASecton = 2 ;
		
		sectionDataMap = fileParserService.getSectionList().get(1).getSectionDataMap() ;
		
		actualNumberOfKeyValuesInASecton = sectionDataMap.size();
		
		assertEquals("Number of map entries objects in section " + fileParserService.getSectionList().get(0).getSectionName() +
				" is not " + expectedNumberOfKeyValuesInASecton + " but should be.", 
				expectedNumberOfKeyValuesInASecton, actualNumberOfKeyValuesInASecton );
		
		expectedNumberOfKeyValuesInASecton = 1 ;
		
		sectionDataMap = fileParserService.getSectionList().get(2).getSectionDataMap() ;
		
		actualNumberOfKeyValuesInASecton = sectionDataMap.size();
		
		assertEquals("Number of map entries objects in section " + fileParserService.getSectionList().get(0).getSectionName() +
				" is not " + expectedNumberOfKeyValuesInASecton + " but should be.", 
				expectedNumberOfKeyValuesInASecton, actualNumberOfKeyValuesInASecton );

	}
	
	
	@Test
	public void testGetStringValue() throws IOException {
		
		String expectedValue = "Programming Test";
		
		String sectionName = "header";
		
		String key = "project";
		
		String filePath = "/apps";
		
		String fileName = "testfile.txt" ;
		
		ParserService fileParserService = new FileParserService(filePath, fileName);
		
		String actualValue = fileParserService.getStringValue(sectionName, key) ;
		
		assertEquals("Value found using " + sectionName + " - " + key + " was not " + 
		   expectedValue + " but should be.", expectedValue, actualValue);
		

	}
	
	@Test
	public void testGetStringValueHeaderDoesNotExist() throws IOException {
		
		
		String sectionName = "does not exist";
		
		String key = "doesnotexist";
		
		String filePath = "/apps";
		
		String fileName = "testfile.txt" ;
		
		ParserService fileParserService = new FileParserService(filePath, fileName);
		
		String actualValue = fileParserService.getStringValue(sectionName, key) ;
		
		assertEquals("Value found using " + sectionName + " - " + key + " was not null " + 
		  " but should be.", null, actualValue);
		

	}
	
	
	@Test
	public void testGetStringKeyDoesNotExist() throws IOException {
		
		
		String sectionName = "Programming Test";
		
		String key = "project";
		
		String filePath = "/apps";
		
		String fileName = "testfile.txt" ;
		
		ParserService fileParserService = new FileParserService(filePath, fileName);
		
		String actualValue = fileParserService.getStringValue(sectionName, key) ;
		
		assertEquals("Value found using " + sectionName + " - " + key + " was not null " + 
		  " but should be.", null, actualValue);
		

	}
	
	
	@Test
	public void testGetIntegerValue() throws IOException {
		
		int expectedValue = 205;
		
		String sectionName = "header";
		
		String key = "accessed";
		
		String filePath = "/apps";
		
		String fileName = "testfile.txt" ;
		
		ParserService fileParserService = new FileParserService(filePath, fileName);
		
		int actualValue = fileParserService.getIntegerValue(sectionName, key) ;
		
		assertEquals("Value found using " + sectionName + " - " + key + " was not " + 
		   expectedValue + " but should be.", expectedValue, actualValue);
		

	}
	
	
	@Test(expected=NumberFormatException.class)
	public void testGetIntegerValueNotAbleToFormatAsInteger() throws IOException {
		
			
		String sectionName = "header";
		
		String key = "project";
		
		String filePath = "/apps";
		
		String fileName = "testfile.txt" ;
		
		ParserService fileParserService = new FileParserService(filePath, fileName);
		
		fileParserService.getIntegerValue(sectionName, key) ;

	}
	
	@Test
	public void testGetFloatValue() throws IOException {
		
		float expectedValue = 4.5f;
		
		String sectionName = "header";
		
		String key = "budget";
		
		String filePath = "/apps";
		
		String fileName = "testfile.txt" ;
		
		ParserService fileParserService = new FileParserService(filePath, fileName);
		
		float actualValue = fileParserService.getFloatValue(sectionName, key) ;
		
		assertTrue("Value found using " + sectionName + " - " + key + " was not " + 
		   expectedValue + " but should be.", actualValue == expectedValue);

	}
	
	
	@Test
	public void testSetStringValueExistingHeaderAndKey() throws IOException {
		
		String newValue = "Programming Challenge";
		
		String sectionName = "header";
		
		String key = "project";
		
		String filePath = "/apps";
		
		String fileName = "testfile.txt" ;
		
		ParserService fileParserService = new FileParserService(filePath, fileName);
		
		fileParserService.setValue(sectionName, key, newValue);
		
		String newActualValue = fileParserService.getStringValue(sectionName, key);
		
		assertEquals("Value found using " + sectionName + " - " + key + " was not " + 
				   newValue + " but should be.", newValue, newActualValue);
		
	}
	
	
	@Test
	public void testSetIntegerValueExistingHeaderAndKey() throws IOException {
		
		int newValue = 1000;
		
		String sectionName = "header";
		
		String key = "accessed";
		
		String filePath = "/apps";
		
		String fileName = "testfile.txt" ;
		
		ParserService fileParserService = new FileParserService(filePath, fileName);
		
		fileParserService.setValue(sectionName, key, newValue);
		
		int newActualValue = fileParserService.getIntegerValue(sectionName, key);
		
		assertEquals("Value found using " + sectionName + " - " + key + " was not " + 
				   newValue + " but should be.", newValue, newActualValue);
		
	}
	
	
	@Test
	public void testSetFloatValueExistingHeaderAndKey() throws IOException {
		
		float newValue = 7.2f;
		
		String sectionName = "header";
		
		String key = "budget";
		
		String filePath = "/apps";
		
		String fileName = "testfile.txt" ;
		
		ParserService fileParserService = new FileParserService(filePath, fileName);
		
		fileParserService.setValue(sectionName, key, newValue);
		
		float newActualValue = fileParserService.getFloatValue(sectionName, key);
		
		assertTrue("Value found using " + sectionName + " - " + key + " was not " + 
				   newValue + " but should be.", newValue == newActualValue);
		
	}
	
	
	@Test
	public void testSetStringValueExistingHeaderOnly() throws IOException {
		
		String newValue = "Writing unit tests is helpful";
		
		String sectionName = "header";
		
		String key = "remarks";
		
		String filePath = "/apps";
		
		String fileName = "testfile.txt" ;
		
		ParserService fileParserService = new FileParserService(filePath, fileName);
		
		fileParserService.setValue(sectionName, key, newValue);
		
		String newActualValue = fileParserService.getStringValue(sectionName, key);
		
		assertEquals("Value found using " + sectionName + " - " + key + " was not " + 
				   newValue + " but should be.", newValue, newActualValue);
		
	}
	
	
	@Test
	public void testSetStringValueHeaderDoesNotExist() throws IOException {
		
		String newValue = "Writing unit tests is helpful";
		
		String sectionName = "hints";
		
		String key = "remarks";
		
		String filePath = "/apps";
		
		String fileName = "testfile.txt" ;
		
		ParserService fileParserService = new FileParserService(filePath, fileName);
		
		fileParserService.setValue(sectionName, key, newValue);
		
		String newActualValue = fileParserService.getStringValue(sectionName, key);
		
		assertEquals("Value found using " + sectionName + " - " + key + " was not " + 
				   newValue + " but should be.", newValue, newActualValue);
		
	}

}
