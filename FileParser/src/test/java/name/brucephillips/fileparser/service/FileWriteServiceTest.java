package name.brucephillips.fileparser.service;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.List;

import name.brucephillips.fileparser.model.Section;

import org.junit.Test;

public class FileWriteServiceTest {

	@Test
	public void test() throws IOException {
		
		String newValue = "Programming Challenge";
		
		String sectionName = "header";
		
		String key = "project";
		
		String filePath = "/apps";
		
		String fileName = "testfile.txt" ;
		
		String newFileName = "testfile_new.txt";
		
		ParserService fileParserService = new FileParserService(filePath, fileName);
		
		fileParserService.setValue(sectionName, key, newValue);
		
		List<Section> sectionList = fileParserService.getSectionList() ;
		
		WriterService<List<Section>> fileWriterService = new FileWriterService(filePath, newFileName) ;
		
		fileWriterService.write(sectionList);
		
		ParserService newFileParserService = new FileParserService(filePath, newFileName) ;
		
		String newValueActual = newFileParserService.getStringValue(sectionName, key) ;
		
		assertEquals("The value for key of " + key + " in section " + sectionName 
				+ " is not " + newValue + " but should be.", newValue, newValueActual);
		
		
	}

}
