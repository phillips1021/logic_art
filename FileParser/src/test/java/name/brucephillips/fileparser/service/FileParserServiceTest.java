package name.brucephillips.fileparser.service;

import static org.junit.Assert.*;

import java.io.IOException;

import name.brucephillips.fileparser.model.Section;

import org.junit.Test;

public class FileParserServiceTest {

	@Test
	public void testGetSectionList() throws IOException {
		
		String filePath = "/apps";
		
		String fileName = "testfile.txt" ;
		
		String header1 = "header";
		
		String header2 = "meta data";
		
		String header3 = "trailer";
		
		FileParserService fileParserService = new FileParserService(filePath, fileName);
		
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

}
