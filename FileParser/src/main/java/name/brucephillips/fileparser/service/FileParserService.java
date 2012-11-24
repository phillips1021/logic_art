package name.brucephillips.fileparser.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import name.brucephillips.fileparser.model.Section;

/**
 * Provides services to parse a file and create a collection of Section objects
 * that represent the file's contents.
 * 
 * @author brucephillips
 * 
 */
public class FileParserService implements ParserService {
	
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
	 * Find the Section object with the provided sectionName
	 * in the sectionList and then find the String value
	 * associated with the provided keyValue in that Section's
	 * map.
	 * @param sectionName - String name of section
	 * @param key - String key value for map entry 
	 * whose value should be returned.
	 * @return String value of the map entry that 
	 * matches the provided key value in the Section with 
	 * the provided name--if the section name or key value is not found 
	 * then returns null.
	 */
	@Override
	public String getStringValue(String sectionName, String key) 
	{
		
		String value = null;
		
		for (Section section : sectionList) 
		{
			
			if (sectionName.equals( section.getSectionName() ) ) 
			{
			
				Map<String,String> sectionDataMap = section.getSectionDataMap() ;

				value = sectionDataMap.get(key);
			}
			
		}

		return value;

	}
	
	/**
	 * Find the Section object with the provided sectionName
	 * in the sectionList and then find the String value
	 * associated with the provided keyValue in that Section's
	 * map and then convert the String to an Integer.
	 * @param sectionName - String name of section
	 * @param key - String key value for map entry 
	 * whose value should be returned.
	 * @return Integer format of the String value of the map entry that 
	 * matches the provided key value in the Section with 
	 * the provided name--if the section name or key value is not found 
	 * then returns null.
	 * @throws NumberFormatException if String value is found but unable to
	 * parse that String value to an integer.
	 */
	@Override
	public Integer getIntegerValue(String sectionName, String key) throws NumberFormatException
	{
		
		Integer value = null;
		
		for (Section section : sectionList) 
		{
			
			if (sectionName.equals( section.getSectionName() ) ) 
			{
			
				Map<String,String> sectionDataMap = section.getSectionDataMap() ;

				String valueString = sectionDataMap.get(key);
				
				if (valueString != null) {
	
						value = Integer.parseInt(valueString);

				}
			}
			
		}

		return value;

	}
	
	
	/**
	 * Find the Section object with the provided sectionName
	 * in the sectionList and then find the String value
	 * associated with the provided keyValue in that Section's
	 * map and then convert the String to Float.
	 * @param sectionName - String name of section
	 * @param key - String key value for map entry 
	 * whose value should be returned as a Float.
	 * @return Float format of the String value of the map entry that 
	 * matches the provided key value in the Section with 
	 * the provided name--if the section name or key value is not found 
	 * then returns null.
	 * @throws NumberFormatException if String value is found but unable to
	 * parse that String value to Float.
	 */
	@Override
	public Float getFloatValue(String sectionName, String key) throws NumberFormatException
	{
		
		Float value = null;
		
		for (Section section : sectionList) 
		{
			
			if (sectionName.equals( section.getSectionName() ) ) 
			{
			
				Map<String,String> sectionDataMap = section.getSectionDataMap() ;

				String valueString = sectionDataMap.get(key);
				
				if (valueString != null) {
	
						value = Float.parseFloat(valueString);

				}
			}
			
		}

		return value;

	}
	
	/**
	 * Find the Section object with the provided sectionName
	 * in the sectionList and then replace the String value
	 * associated with the provided key in that Section's
	 * map with the provided Object's String value.  
	 * If provided header is not 
	 * found create new header and key:value map entry in sectionList.
	 * If header is found but not the key create new key:value map
	 * enter for the Section object with that header.
	 * @param header String header name
	 * @param key 
	 * @param value
	 */
	@Override
	public void setValue(String header, String key, Object value) 
	{
		
		boolean headerAndKeyFound = false ;
		
		/*
		 * Try to find existing match for header and key and if 
		 * found update the value for that key.
		 */
		for (Section section : sectionList) 
		{
			
			if (header.equals( section.getSectionName() ) ) 
			{
			
				Map<String,String> sectionDataMap = section.getSectionDataMap() ;

				if (sectionDataMap.containsKey(key)) {
					
					sectionDataMap.put(key, value.toString());
					
					headerAndKeyFound = true ;
					
				}
			}
			
		}
		
		boolean headerFound = false ;
		
		if (! headerAndKeyFound ) 
		{
			
			/*
			 * Try to find existing match for header and add a new map entry 
			 * to the Section for that header.
			 */
			for (Section section : sectionList) 
			{
				
				if (header.equals( section.getSectionName() ) ) 
				{
				
					Map<String,String> sectionDataMap = section.getSectionDataMap() ;

					sectionDataMap.put(key, value.toString() ) ;
					
					headerFound = true ;

				}
			
			}
		}
		
		if (! headerFound) 
		{
			
			Section section = new Section(header) ;
			
			Map<String,String> sectionDataMap = section.getSectionDataMap() ;

			sectionDataMap.put(key, value.toString()) ;
			
			sectionList.add(section);
			
		}
		
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
				LOGGER.debug("Last line read is a line that has section header value");
				
				createSectionHeader( aLine ) ;
				
				firstLine = false ;
			}
			else 
				if ( isKeyLine(aLine) ) 
				{
					
					LOGGER.debug("Last line read is a line that has a key:value");
					
					createSectionKeyValue(aLine) ;
					
				}
			else 
				if ( isContinuationOfValue(aLine) ) 
				{
					
					LOGGER.debug("Last line read is a line that continues the value for the last key read.");
					addToValue(aLine);
					
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
	private boolean isSectionHeader(String aLine) 
	{
		
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
		
		Section section = new Section(header);
		
		sectionList.add(section);
		
	}

	/**
	 * Clean up the line value so that only 
	 * the text that should be a section header
	 * value is returned.
	 * @param aLine
	 * @return text that should be a section header value
	 */
	private String createHeader(String aLine) 
	{
		
		String header = aLine.trim() ;
		
		header = header.replace('[', ' ');
		
		header = header.replace(']', ' ');
		
		header = header.trim();
		
		return header ;
	
	}
	
	
    /**
     * Determine if the line represents 
     * a key:value combination.
     * @return true if line represents a key:value
     */
	private boolean isKeyLine(String aLine) 
	{
		
		boolean isKeyLine = false;
		
		if ( ! aLine.equals("")  && aLine.charAt(0) != '[' && aLine.charAt(0) != ' ') 
		{
			
			isKeyLine = true ;
			
		}
		
		return isKeyLine ;
		
	}
	
	/**
	 * Create a Map that represents the key:value
	 * of the provided line and add the Map 
	 * to the last Section object's sectionDataList stored 
	 * in the SectionList.
	 * @param aLine line formatted as key:value
	 */
	private void createSectionKeyValue(String aLine) 
	{
		
		//The : character is used to separate the key and value
		
		String [] tokens = aLine.split(":");
		
		String key = tokens[0];
		
		String value = tokens[1];
		
		key = key.trim();
		
		value = value.trim();
		
		int sectionListSize = sectionList.size() ;
		
		Section section = sectionList.get( sectionListSize - 1);
		
		Map<String,String> sectionDataMap = section.getSectionDataMap() ;

		sectionDataMap.put(key, value);

		section.setSectionDataMap(sectionDataMap);
				
		LOGGER.debug("Added a map entry with key of " + key + " and a value of " + value + " to the section data map of section with header " + section.getSectionName() );		
		
		
	}
	

	
    /**
     * Determine if the value of the line provided
     * is a continuation of the previous value.
     * @param aLine
     * @return true if line value is a continuation 
     */
	private boolean isContinuationOfValue(String aLine) 
	{
		boolean isContinuation = false ;
		/*
		 * Business rule is that a line is a continuation of the 
		 * previous value if it starts with one or more spaces.
		 */
		
		aLine = aLine.trim();
		
		if (! aLine.equals("") && ! aLine.equals("\n") && ! aLine.equals("\r") 
				&& ! aLine.equals("\n\r") && ! aLine.equals("\r\n") ) {
			
			isContinuation = true ;
		}
		
		return isContinuation ;
		
		
	}
	
	

    /**
     * Add the value of aLine to the 
     * value for the last Map object
     * of the last Map list object
     * of the last section object
     * in the sectionList.
     * 
     * @param aLine
     */
	private void addToValue(String aLine) 
	{
		
		int sectionListSize = sectionList.size() ;
		
		Section section = sectionList.get( sectionListSize - 1);
		
		Map<String,String> sectionDataMap = section.getSectionDataMap() ;
		
		//Get the last entry in sectionDataMap
		
		int sectionDataMapSize = sectionDataMap.size() ;
		
		//since we cannot access the last entry directly we 
		//will loop over the sectionDayMap and 
		//the last time the loop is executed will be the 
		//last map entry
		
		int loopRun = 1;
		
		for ( Map.Entry<String, String> entry : sectionDataMap.entrySet() ) 
		{
			
			if (loopRun == sectionDataMapSize ) 
			{
				String entryValue = entry.getValue() ;
				
				entryValue = entryValue + " " + aLine ;
				
				entry.setValue(entryValue);
				
				LOGGER.debug("Modified the map with key of " + entry.getKey() + " so that its value is now " + entryValue );

			}

		}

		
	}

	
	@Override
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
