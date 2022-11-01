package classes.from.console.project;
import static java.lang.System.*;

import java.awt.Desktop;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.Vector;

/**
 * FileHandler => A class for handle all file operations
 * @author Java Generator Team
 */
public class FileHandler {
	
	// Global variables
	private static final GeneratorLogger logger = new GeneratorLogger();
	private static final StringBuilder queryToPrepare = new StringBuilder();
	private static FileWriter writer;
	private static File sqlFileToCreate;
	private static File sqlFileToRead;
	
	/**
	 * createFile() : Create a simple file (no write in file - only blank)
	 * @param {String} filename
	 * @return {File} the new file created
	 */
	public File createFile(String filename) {
		
		  sqlFileToCreate = new File("./" + filename + ".sql");
		
	      try {
	    	  
				if (sqlFileToCreate.createNewFile()) {
					
				    System.out.println("File created : " + sqlFileToCreate.getName());
				    return sqlFileToCreate;
				    
				  } else {  
				    System.out.println("File : " + sqlFileToCreate.getName() + " already exists.");
				    return sqlFileToCreate;
				  }
			
		} catch (IOException e) {
			logger.logError("createFile()", e.getMessage());
			e.printStackTrace();
		}
	      
		return sqlFileToCreate;
	}

	/**
	 * writeInsertsToFile() : Write the statements into the selected file
	 * @param quantityOfLines
	 * @param tables
	 * @param filePath
	 */
	public void writeToFile(int quantityOfLines, Vector<TableData> tables, String filePath) {
		
		try {
			    queryToPrepare.setLength(0);
				sqlFileToRead = new File(filePath);
				writer = new FileWriter(filePath);
				
				if (!Helpers.isDocumentValidForWrite(sqlFileToRead)) {
					logger.logError("writeToFile()", "Fail on check file.");
					return;
				}

			    Iterator<TableData> iterator = tables.iterator();
			      
			    while (iterator.hasNext()) {
			    	  
			    	TableData current = (TableData) iterator.next();
			    	 
				    for (int i = 0; i < quantityOfLines; i++) { 
				    	  
				    	 queryToPrepare.append(
				    			 			   Helpers.startTemplateInsert(current.getTableName()) + " " +
				    			 			   Helpers.printAllAttributes(current) + 
				    			 			   Helpers.addValuesInsert() + "\n"
				    			 			   );
				    	writer.write(queryToPrepare.toString());
				    	queryToPrepare.setLength(0); // init for the new query
				  }    
		      }
		           
		      logger.logInfo("writeToFile()", "Success write on file.");
		      writer.close();
	
		} catch (IOException e) {
			
			logger.logError("writeToFile()", e.getMessage());
			e.printStackTrace();
			
		} finally {
			
			// Open file for view datas
			openFile(sqlFileToRead);
		}	
	}
	
	/**
	 * openFile() : open a file
	 * @param {File} theFile
	 */
	public void openFile(File theFile) {
		
		try {
			
			Desktop desktop = Desktop.getDesktop();
			
			if (!Desktop.isDesktopSupported()) {
				logger.logError("openFile()", "Desktop is not supported.");	
				return;			
			}  
				
		    if (!sqlFileToRead.exists())  {
				logger.logError("openFile()", "File has been not found.");	
				return;	
			}
		    
			desktop.open(sqlFileToRead);
			logger.logInfo("openFile()", "File opened successufully.");
		    
		} catch (IOException e) {
			
			logger.logError("openFile()", "Unable to open file for preview.");
			e.printStackTrace();
		}  	
	}
}
