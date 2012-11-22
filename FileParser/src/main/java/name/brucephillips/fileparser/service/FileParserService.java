/**
 * 
 */
package name.brucephillips.fileparser.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import name.brucephillips.fileparser.model.Section;

/**
 * Provides services to parse a file and create a collection of Section objects
 * that represent the file's contents.
 * 
 * @author brucephillips
 * 
 */
public class FileParserService {
	
	private static final Logger LOGGER = Logger.getLogger(FileParserService.class.getName());

	private List<Section> sectionList;

	private File file;

	/**
	 * Overloaded constructor that creates the file object using the provided
	 * filePath and fileName values and then parses the file to create the
	 * sectionList object.
	 * 
	 * @param filePath
	 *            - path to the file
	 * @param fileName
	 *            - name of the file
	 * @throws IOException
	 */
	public FileParserService(String filePath, String fileName)
			throws IOException 
	{

		file = new File(filePath, fileName);

		if (!file.isFile()) 
		{
			throw new IllegalStateException("The path you provided " + filePath
					+ " and the file name you provided " + fileName
					+ " could not be used to create a valid file object.");
		}

		parseFile();

	}

	/**
	 * Parse the file object and create 
	 * the sectionList object with the
	 * file's content.
	 * @throws IOException
	 */
	private void parseFile() throws IOException 
	{

		sectionList = new ArrayList<Section>();

		FileReader reader = new FileReader(file);

		BufferedReader bufReader = new BufferedReader(reader);
		
		boolean firstLine = true ;

		String aLine = bufReader.readLine();

		while (aLine != null) 
		{
			
			LOGGER.debug("Line read from file: " + aLine );
			
			if (firstLine || isSectionHeader(aLine) )
			{
				createSectionHeader( aLine ) ;
				
				firstLine = false ;
			}

			aLine = bufReader.readLine();
			
		}

		bufReader.close();

	}

	/**
	 * Return true if the provide line value
	 * meets the business rules for a section header.
	 * @param aLine
	 * @return true if section header
	 */
	private boolean isSectionHeader(String aLine) {
		
		boolean isHeader = false ;
		
		if ( ! aLine.equals("")  && aLine.charAt(0) == '[') 
		{
			isHeader = true ;
		}
		
		return isHeader ;
		
	}
	
    /**
     * Using the provide line value create the 
     * a new Section object, set its name value
     * to the line value (after cleaning it up), 
     * and add the Section object to the sectionList.
     * @param aLine line value that contains a section header
     */
	private void createSectionHeader(String aLine) 
	{
		LOGGER.debug("Creating section header using " + aLine);
		
		String header = createHeader(aLine) ;
		
		Section section = new Section();
		
		section.setSectionName(header);
		
		sectionList.add(section);
		
	}

	/**
	 * Clean up the line value so that only 
	 * the text that should be a section header
	 * value is returned.
	 * @param aLine
	 * @return text that should be a section header value
	 */
	private String createHeader(String aLine) {
		
		String header = aLine.trim() ;
		
		header = header.replace('[', ' ');
		
		header = header.replace(']', ' ');
		
		header = header.trim();
		
		return header ;
	
	}

	public List<Section> getSectionList() 
	{
		return sectionList;
	}

	public void setSectionList(List<Section> sectionList) 
	{
		this.sectionList = sectionList;
	}

	public File getFile() 
	{
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

}
