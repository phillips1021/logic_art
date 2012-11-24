
package name.brucephillips.fileparser.service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import name.brucephillips.fileparser.model.Section;

/**
 * Implements methods to write a collection of Section objects
 * to a file.
 * @author brucephillips
 *
 */
public class FileWriterService implements WriterService<List<Section>> {
	
	private static final Logger LOGGER = Logger.getLogger( FileWriterService.class.getName() ) ;
	
	private File file;
	
	public FileWriterService(String filePath, String fileName)
			throws IOException 
	{

		file = new File(filePath, fileName);


	}


	@Override
	public void write(List<Section> sectionList) throws IOException 
	{
		
		
		FileWriter sectionListWriter = new FileWriter( file );
		
		PrintWriter sectionListPrintWriter = new PrintWriter( sectionListWriter );
		
		LOGGER.debug("About to write to file: " + file.getAbsolutePath() ) ;
		
		for (Section section : sectionList) 
		{
			
			sectionListPrintWriter.println("["+section.getSectionName()+"]");
			
			LOGGER.debug("Wrote to file: " + "["+section.getSectionName()+"]") ;
			
			Map<String,String> sectionDataMap = section.getSectionDataMap() ;
			
			for ( Map.Entry<String, String> entry : sectionDataMap.entrySet() ) 
			{
				
				sectionListPrintWriter.println(entry.getKey() + ":" + entry.getValue() );
				
				LOGGER.debug("Wrote to file: " + entry.getKey() + ":" + entry.getValue() ) ;
				
			}
			
			
			
		}
		
		sectionListPrintWriter.flush() ;
		
		sectionListPrintWriter.close() ;
		

	}

	

}
