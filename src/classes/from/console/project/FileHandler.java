package classes.from.console.project;
import static java.lang.System.*;

import java.awt.Desktop;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;

/**
 * FileHandler => A class for handle all file operations
 * @author Java Generator Team
 */
public class FileHandler {
	
	// Global variables
	private static final DatabaseController database = new DatabaseController();
	private static final GeneratorLogger logger = new GeneratorLogger();
	private static final StringBuilder queryToPrepare = new StringBuilder();
	private static FileWriter writer;
	private static File sqlFileToCreate;
	private static File sqlFileToRead;
	
	private static ArrayList<Integer> intDataAttr;
	
	private static ArrayList<ArrayList<String>> tempData; 
	
	private static HashMap<String, Vector<String>> mapTemp;
	
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
				mapTemp = new HashMap<String, Vector<String>>();
				
				if (!Helpers.isDocumentValidForWrite(sqlFileToRead)) {
					logger.logError("writeToFile()", "Fail on check file.");
					return;
				}

			    Iterator<TableData> iterator = tables.iterator();
			    
			    String attrTemp;
			    int num;
			    int pk_fk_nb = 0;
			      
			    while (iterator.hasNext()) {
			    	  
			    	TableData current = (TableData) iterator.next();
			    	 
				    for (int i = 0; i < quantityOfLines; i++) { 
				    	
				    	pk_fk_nb++;
				    	  
				    	 queryToPrepare.append(Helpers.startTemplateInsert(current.getTableName()) + " " +
				    			 			   Helpers.printAllAttributes(current) +
				    			 			   Helpers.addValues()			    			 			   );
				    	 
				    	 for (int j = 0; j < current.getDatabaseEquivalenceList().size(); j++) {	
				    		 
				    		 attrTemp = current.getDatabaseEquivalenceList().get(j).toString().toLowerCase().trim();
				    		    		 
				    		 switch (EnumList.MariaAttributeTypesListEnum.getAsEnum(attrTemp)) {
				    		 		 
					    		 case INT:
					    			 	
						    		  if (!mapTemp.containsKey(attrTemp)) {
						    			  
						    			  Vector<String> c_Temp = new Vector<String>();
						    			  
						    			  for (Integer value : database.callGenerateRandomNumber(0, 100000, quantityOfLines)) {				    				  
						    				  
						    				  c_Temp.add(String.valueOf(value));
						    			  }					    			 						    			 
						    			 
						    			  mapTemp.put(attrTemp, c_Temp);
						    		  }
						    		  
						    		  num = Helpers.generateRandom(0, (mapTemp.get(attrTemp).size() - 1));
						    		  
						    		  if (j < (current.getDatabaseEquivalenceList().size() - 1)) { 
						    			  queryToPrepare.append(Helpers.addValuesInsert(mapTemp.get(attrTemp).get(num) + ", "));
						    		  } else {
						    			  queryToPrepare.append(Helpers.addValuesInsert(mapTemp.get(attrTemp).get(num) + ");"));
						    		  }
						    		 
					    			 break;
					    			 
					    		 case FLOAT:
					    			 
					    			 Float newFloat = Helpers.generateRandomFloat(0.0f, 100000.00f);
					    			 
						    		  if (j < (current.getDatabaseEquivalenceList().size() - 1)) { 
						    			  queryToPrepare.append(Helpers.addValuesInsert(newFloat + ", "));
						    		  } else {
						    			  queryToPrepare.append(Helpers.addValuesInsert(newFloat + ");"));
						    		  }
					    			 	
					    			 break;
					    			 
					    		 case DOUBLE:
					    			 
					    			  Double newDouble = Helpers.generateRandomDouble(0.00, 100000.00);
					    			 
						    		  if (j < (current.getDatabaseEquivalenceList().size() - 1)) { 
						    			  queryToPrepare.append(Helpers.addValuesInsert(newDouble + ", "));
						    		  } else {
						    			  queryToPrepare.append(Helpers.addValuesInsert(newDouble + ");"));
						    		  }
					    			 	
					    			 break;	
					    			 
					    		 case PRIMARY_KEY:
					    		 case FOREIGN_KEY:
					   					    		  					    		  
						    		  if (j < (current.getDatabaseEquivalenceList().size() - 1)) { 
						    			  queryToPrepare.append(String.valueOf(pk_fk_nb) + ", ");
						    		  } else {
						    			  queryToPrepare.append(String.valueOf(pk_fk_nb) + ");");
						    		  }
						    		  
					    			 break;
					    			 
					    		 case DATE:
					    			 
						    		  if (!mapTemp.containsKey(attrTemp)) {
						    			  
						    			  Vector<String> c_Temp = new Vector<String>();
						    			  
						    			  for (String value : database.callGenerateRandomDate("2022-01-01", "2022-12-01", quantityOfLines)) {				    				  
						    				  
						    				  c_Temp.add(String.valueOf(value));
						    			  }					    			 						    			 
						    			 
						    			  mapTemp.put(attrTemp, c_Temp);
						    		  }
						    		  
						    		  num = Helpers.generateRandom(0, (mapTemp.get(attrTemp).size() - 1));
						    		  
						    		  if (j < (current.getDatabaseEquivalenceList().size() - 1)) { 
						    			  queryToPrepare.append(Helpers.addValuesInsert(mapTemp.get(attrTemp).get(num) + ", "));
						    		  } else {
						    			  queryToPrepare.append(Helpers.addValuesInsert(mapTemp.get(attrTemp).get(num) + ");"));
						    		  }
						    		  
					    			 break;
					    			 
					    		 case DATETIME:
					    			 
						    		  if (!mapTemp.containsKey(attrTemp)) {
						    			  
						    			  Vector<String> c_Temp = new Vector<String>();
						    			  
						    			  for (String value : database.callGenerateRandomDatetime("2022-01-01", "2022-12-01", quantityOfLines)) {				    				  
						    				  
						    				  c_Temp.add(String.valueOf(value));
						    			  }					    			 						    			 
						    			 
						    			  mapTemp.put(attrTemp, c_Temp);
						    		  }
						    		  
						    		  num = Helpers.generateRandom(0, (mapTemp.get(attrTemp).size() - 1));
						    		  
						    		  if (j < (current.getDatabaseEquivalenceList().size() - 1)) { 
						    			  queryToPrepare.append(Helpers.addValuesInsert(mapTemp.get(attrTemp).get(num) + ", "));
						    		  } else {
						    			  queryToPrepare.append(Helpers.addValuesInsert(mapTemp.get(attrTemp).get(num) + ");"));
						    		  }
						    		  
					    			 break;
					    					    			 
					    		 case TIMESTAMP:
					    			 
						    		  if (!mapTemp.containsKey(attrTemp)) {
						    			  
						    			  Vector<String> c_Temp = new Vector<String>();
						    			  
						    			  for (String value : database.callGenerateRandomTimestamp("2022-01-01", "2022-12-01", quantityOfLines)) {				    				  
						    				  
						    				  c_Temp.add(String.valueOf(value));
						    			  }					    			 						    			 
						    			 
						    			  mapTemp.put(attrTemp, c_Temp);
						    		  }
						    		  
						    		  num = Helpers.generateRandom(0, (mapTemp.get(attrTemp).size() - 1));
						    		  
						    		  if (j < (current.getDatabaseEquivalenceList().size() - 1)) { 
						    			  queryToPrepare.append(Helpers.addValuesInsert(mapTemp.get(attrTemp).get(num) + ", "));
						    		  } else {
						    			  queryToPrepare.append(Helpers.addValuesInsert(mapTemp.get(attrTemp).get(num) + ");"));
						    		  }
						    		  
					    			 break;
					    			 
					    		 case BOOL:
					    		 case BOOLEAN:
					    			 
					    			 
						    		  if (!mapTemp.containsKey(attrTemp)) {
						    			  
						    			  Vector<String> c_Temp = new Vector<String>();
						    			  
						    			  for (String value : database.callGenerateRandomBoolean(quantityOfLines)) {				    				  
						    				  
						    				  c_Temp.add(String.valueOf(value));
						    			  }					    			 						    			 
						    			 
						    			  mapTemp.put(attrTemp, c_Temp);
						    		  }
						    		  
						    		  num = Helpers.generateRandom(0, (mapTemp.get(attrTemp).size() - 1));
						    		  
						    		  if (j < (current.getDatabaseEquivalenceList().size() - 1)) { 
						    			  queryToPrepare.append(Helpers.addValuesInsert(mapTemp.get(attrTemp).get(num) + ", "));
						    		  } else {
						    			  queryToPrepare.append(Helpers.addValuesInsert(mapTemp.get(attrTemp).get(num) + ");"));
						    		  }
						    		 
					    			 break;
					    			 
					    		 case NULL:
					    			 
						    		  if (j < (current.getDatabaseEquivalenceList().size() - 1)) { 
						    			  
						    			  queryToPrepare.append(Helpers.addValuesInsert("null" + ", "));
						    		  } else {
						    			  queryToPrepare.append(Helpers.addValuesInsert("null" + ");"));
						    		  }
					    			 
					    			 break;
					    		
					    	default:
					    		
					    		  if (!mapTemp.containsKey(attrTemp)) {
					    			  
					    			  Vector<String> tempDataAttr = database.callSearchDatasProcedure(attrTemp, quantityOfLines);
					    			  mapTemp.put(attrTemp, tempDataAttr);
					    		  }
					    		  
					    		  num = Helpers.generateRandom(0, (mapTemp.get(attrTemp).size() - 1));
					    		  
					    		  if (j < (current.getDatabaseEquivalenceList().size() - 1)) { 
					    			  queryToPrepare.append(Helpers.addValuesInsert(mapTemp.get(attrTemp).get(num) + ", "));
					    		  } else {
					    			  queryToPrepare.append(Helpers.addValuesInsert(mapTemp.get(attrTemp).get(num) + ");"));
					    		  }
					    		  
			    				 break;
				    		 
				    		 }
			    	    }  	 
				    	 
				    	writer.write(queryToPrepare.toString() + "\n");
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
