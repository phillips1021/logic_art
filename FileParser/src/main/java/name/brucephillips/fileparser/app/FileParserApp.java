
package name.brucephillips.fileparser.app;

import java.io.IOException;
import java.util.List;

import name.brucephillips.fileparser.model.Section;
import name.brucephillips.fileparser.service.FileParserService;
import name.brucephillips.fileparser.service.FileWriterService;
import name.brucephillips.fileparser.service.ParserService;
import name.brucephillips.fileparser.service.WriterService;

/**
 * Demonstrates how to use the FileParser in an 
 * application.  Main method expects two parameters-
 * first if path to file's directory and 
 * second is the file name to parse.
 * @author brucephillips
 *
 */
public class FileParserApp {

	/**
	 * Use the two provided arguments (first 
	 * is the file path and second is the 
	 * file name) create a FileParserService
	 * object and demonstrate calling its
	 * public methods.
	 * @param args
	 * @throws IOException if unable to read/write file
	 */
	public static void main(String[] args) throws IOException {
		
		
		if (args.length != 2) {
			
			System.out.println("You must run this program with two parameters - first is the file path and second is the file name.") ;
			throw new IllegalArgumentException("You must run this program with two parameters - first is the file path and second is the file name.");
			
		}

		ParserService fileParserService = new FileParserService( args[0], args[1]);
		
		String valueStr = fileParserService.getStringValue("header", "project");
		
		System.out.println("The value of key project in section named header is " + valueStr );
		
		int valueInt = fileParserService.getIntegerValue("header", "accessed");
		
		System.out.println("The value of key accessed in section named header is " + valueInt );

		float valueFloat = fileParserService.getFloatValue("header", "budget");
		
		System.out.println("The value of key budget in section named header is " + valueFloat );
		
		System.out.println("Change the value of the project key to be Programming Challenge in the header section.");
		
		fileParserService.setValue("header", "project", "Programming Challenge");
		
		valueStr = fileParserService.getStringValue("header", "project");
		
		System.out.println("New value for project key is now " + valueStr);
		
		System.out.println("Set an integer value of 2012 for header section with new key of year started");
		
		//Note the third argument is of type Object and therefore
		//can be a String, an Integer, or a Float
		fileParserService.setValue("header", "year started", 2005);
		
		valueInt = fileParserService.getIntegerValue("header", "year started");
		
		System.out.println("The value of key year started in section named header is " + valueInt );
		
		System.out.println("Set float value of 140.5 for new section named estimate with new key of man hours");
		
		//Note the third argument is of type Object and therefore
		//can be a String, an Integer, or a Float
		fileParserService.setValue("estimate", "man hours", 140.5);
		
		valueFloat = fileParserService.getFloatValue("estimate", "man hours");
		
		System.out.println("The value of key man hours started in section named estimate is " + valueFloat );
		
		List<Section> sectionList = fileParserService.getSectionList() ;
		
		WriterService<List<Section>> writerService = new FileWriterService( args[0], "new_"+args[1] ) ;
		
		writerService.write(sectionList) ;
		
		System.out.println("Updated contents written to " +args[0]+"/"+"new_"+args[1]);

	}

}
