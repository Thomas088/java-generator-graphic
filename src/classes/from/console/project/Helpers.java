package classes.from.console.project;
import static java.lang.System.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;

/**
 * Helpers => A class for group all useful methods 
 * @author Java Generator Team
 *
 */
public class Helpers {

	// DECLARE CONSTANTS (const => static final)
	// https://www.theserverside.com/video/Why-we-use-static-final-in-Java-for-constants#:~:text=The%20static%20keyword%20means%20the,to%20create%20a%20constant%20value
	private static final Scanner scanner = new Scanner(new InputStreamReader(System.in));
	private static final StringBuilder inputCurrentValue = new StringBuilder();
	private static final StringBuilder toConcat = new StringBuilder();
	private static final RegexRepertory regexRepertory = new RegexRepertory();
	private static final GeneratorLogger logger = new GeneratorLogger();
	private static Matcher matcher; // not final because different regex evaluated in different helpers 
	private static final String quote = "'";
	
	
	private static int i;
	private static int allAttributesLength;
	private static boolean isLetterFounded;
	private static boolean isNumberFounded;
	private static boolean isSpecialCharFounded;
	private static boolean isInvalid;
	private static boolean isSingleDotForDecimals;
	
	
//	private static final String newline = System.getProperty("line.separator"); // a voir si besoin par la suite

	// FOR TEMPLATE
	public static String startTemplateInsert(String nameTable) {
	    return "INSERT INTO " + nameTable;
	}
	
	public static String printAllAttributes(TableData table) {
		
		toConcat.setLength(0);
		toConcat.append("(");
		allAttributesLength = table.getAttributeList().size();
		
		i = 0;
		for (String attr: table.getAttributeList()) { 
			
			if (i < (allAttributesLength - 1)) {
				toConcat.append(attr + ", ");
			} else {
				toConcat.append(attr);
			}
			
			i++;
		}
		
	    toConcat.append(") ");
	    return toConcat.toString();
	}
	
	public static String addValuesInsert(String str) {
	    return quote + str + quote;
	}
	
	public static String addValues() {
	    return "VALUES " + " (";
	}
	
	// MYSQL - MARIA
	
	public static String transformToMySQLDate(int[] date) {
		
		String dateStr = "";
		
		for (int i = 0; i < date.length; i++) {
			dateStr += date[i] + ((i != date.length - 1) ? "-" : "");
		}
		
		return dateStr;
	}
	
	
	public static String transformToMySQLDatetime(int[] date, int[] time) {
		
		String dateTimeStr = "";
		return ""; // TODO - A CREER
	} 
	
	// FOR USER PROMPT
	
	/**
	 * chooseContinueState() = method for ask to user for continue or not
	 * @return {String} the response (Yy/Nn)
	 */
	public static String chooseContinueState() {
	
		while(true) {
			
			// tip for better performance if we use StringBuilder in a loop
			// https://stackoverflow.com/questions/242438/is-it-better-to-reuse-a-stringbuilder-in-a-loop
			inputCurrentValue.setLength(0); // or choice.delete(0, choice.length()) => choice = ""
			
			out.println("Do you want to continue ? (Yy / Nn)");
			inputCurrentValue.append(scanner.next().trim());
			
			if (inputCurrentValue.toString().length() == 1) {
				
				if (!inputCurrentValue.toString().equalsIgnoreCase("y") && !inputCurrentValue.toString().equalsIgnoreCase("n")) {
					logger.logWarning("chooseContinueState()", "Error bad value - invalid value entered - re-do.");
				} else {
					logger.logInfo("chooseContinueState()", "Value OK - You choosed : " + inputCurrentValue.toString());
					break;
				}
				
			} else {
				logger.logWarning("chooseContinueState()", "Bad value - re-do.");
			}
		}
		
		return inputCurrentValue.toString();
	}
	
	/**
	 * readInt() = method for ask to user for enter a int value
	 * @return {Integer} the result
	 */
	public static Integer readInt() {
		
		while (true) {
			
			inputCurrentValue.setLength(0);
			
			out.println("Enter int value :");
			inputCurrentValue.append(scanner.next().trim());
			
			matcher = regexRepertory.getLettersPattern().matcher(inputCurrentValue.toString());
			isLetterFounded = matcher.find();
			
			matcher = regexRepertory.getSpecialSharactersPattern().matcher(inputCurrentValue.toString());
			isSpecialCharFounded = matcher.find();
			
			isInvalid = isLetterFounded || isSpecialCharFounded;
			
			if (isInvalid) {
				logger.logWarning("readInt()", "Error bad value - letter or special character founded - re-do.");
			} else if (inputCurrentValue.toString().length() == 0) {
				logger.logWarning("readInt()", "Empty value - re-do.");
			} else {
				break;
			}
		}
		
		Integer value = Integer.parseInt(inputCurrentValue.toString());
		logger.logInfo("readInt()", "Value OK - You defined : " + value);
		return value;
  }
	
	/**
	 * readFloat() = method for ask to user for enter a float value
	 * @return {Float} the result
	 */
	public static Float readFloat() {
		
		Float value = 0.0f;
		
			while (true) {
				
				inputCurrentValue.setLength(0);
				
				out.println("Enter float value (only the '.' for the decimals) : ");
				inputCurrentValue.append(scanner.next().trim());
				
				matcher = regexRepertory.getLettersPattern().matcher(inputCurrentValue.toString());
				isLetterFounded = matcher.find();
				
				matcher = regexRepertory.getSpecialCharactersWithoutDotPattern().matcher(inputCurrentValue.toString());
				isSpecialCharFounded = matcher.find();
				
				// Check decimal dot character
				matcher = regexRepertory.getSingleDotForDecimals().matcher(inputCurrentValue.toString());
				isSingleDotForDecimals = matcher.find();
				
				if (isSingleDotForDecimals) {
					
					if (isLetterFounded || isSpecialCharFounded) {
						logger.logWarning("readFloat()", "Error bad value - letter or special character founded - re-do.");
					} else if (inputCurrentValue.toString().length() == 0) {
						logger.logWarning("readFloat()", "Empty value - re-do.");
					}  else {
						break;
					}
					
				} else {
					logger.logWarning("readFloat()", "You have to set ONLY one dot - re-do.");
				}
			}
		
		value = Float.parseFloat(inputCurrentValue.append("f").toString());
		logger.logInfo("readFloat()", "Value OK - You defined : " + value);
		return value;
	}
	
	/**
	 * readDouble() = method for ask to user for enter a double value
	 * @return {Double} the result
	 */
	public static Double readDouble() {
		
		Double value = 0.0;
	
		while (true) {
			
			inputCurrentValue.setLength(0);
			
			out.println("Enter double value (only the '.' for the decimals) : ");
			inputCurrentValue.append(scanner.next().trim());
			
			matcher = regexRepertory.getLettersPattern().matcher(inputCurrentValue.toString());
			isLetterFounded = matcher.find();
			
			matcher = regexRepertory.getSpecialCharactersWithoutDotPattern().matcher(inputCurrentValue.toString());
			isSpecialCharFounded = matcher.find();
			
			// Check decimal dot character
			matcher = regexRepertory.getSingleDotForDecimals().matcher(inputCurrentValue.toString());
			isSingleDotForDecimals = matcher.find();
			
			if (isSingleDotForDecimals) {
				
				if (isLetterFounded || isSpecialCharFounded) {
					logger.logWarning("readDouble()", "Error bad value - letter or special character founded - re-do.");
				} else if (inputCurrentValue.toString().length() == 0) {
					logger.logWarning("readDouble()", "Empty value - re-do.");
				}  else {
					break;
				}
				
			} else {
				logger.logWarning("readDouble()", "You have to set ONLY one dot - re-do.");
			}
		}
		
		value = Double.parseDouble(inputCurrentValue.toString());
		logger.logInfo("readDouble()", "Value OK - You defined : " + value);
		return value;
	}
	
	/**
	 * readString() = Method for ask to user for enter a string value
	 * @return {String} the result
	 */
	public static String readString() {
		
		while(true) {
			
			inputCurrentValue.setLength(0);
			
			out.println("Enter string value :");
			inputCurrentValue.append(scanner.next().trim());
			
			if (inputCurrentValue.toString().length() == 0) {
				err.println("Empty value - re-do.");
			} else {
				break;
			}	
		}
		
		logger.logInfo("readString()", "Value OK - You defined : " + inputCurrentValue.toString());
		return inputCurrentValue.toString();
	}
	
	/**
	 * readStringWithoutNumbers() = Method for ask to user for enter a string value (without numbers)
	 * @return {String} the result
	 */
	public static String readStringWithoutNumbers() {
		
		while(true) {
			
			out.println("Type your string :");
			inputCurrentValue.append(scanner.next().trim());
			
			matcher = regexRepertory.getNumbersPattern().matcher(inputCurrentValue.toString());
			isNumberFounded = matcher.find();
			
			if (isNumberFounded) {			
				err.println("Error Bad value - number founded -  re-do.");		
			} else if (inputCurrentValue.toString().length() == 0) {
				err.println("Empty value - re-do.");
			} else {
				break;
			}
			
		}
		
		logger.logInfo("readStringWithoutNumbers()", "Value OK - You defined : " + inputCurrentValue.toString());
		return inputCurrentValue.toString();
	}
	
	// --------- Helpers for Files (read, write etc) ----------- // 
	
	/**
	 * readStringWithoutSpecialCharacters() = Method for ask to user for enter a string value (without special characters)
	 * @return {String} the result
	 */
	public static String readStringWithoutSpecialCharacters() {
		
		while(true) {
			
			inputCurrentValue.setLength(0);
			
			out.println("Enter string value :");
			inputCurrentValue.append(scanner.next().trim());
				
			matcher = regexRepertory.getSpecialSharactersPattern().matcher(inputCurrentValue.toString());
			isSpecialCharFounded = matcher.find();
			
			if (isSpecialCharFounded) {
				logger.logWarning("readStringWithoutSpecialCharacters()", "Error bad value - special character founded - re-do.");
			} else if (inputCurrentValue.toString().length() == 0) {
				logger.logWarning("readStringWithoutSpecialCharacters()", "Empty value - re-do.");
			} else {
				break;
			}
		}
		
		logger.logInfo("readStringWithoutSpecialCharacters()", "Value OK - You defined : " + inputCurrentValue.toString());
		return inputCurrentValue.toString();
	}
	
	/**
	 * isDocumentValidForRead() : check if the .sql file is valid and available (or not)
	 * @param {File} currentFile
	 * @return {boolean} the response
	 */
	public static boolean isDocumentValidForRead(File currentFile) {
		
        if(!currentFile.exists()) {   	
        	logger.logError("isDocumentValidForRead()", "The file " + currentFile.getName() + " not exists.");
        	return false;
        	
        } else if (!currentFile.canRead()) {
        
        	logger.logError("isDocumentValidForRead()", "The file " + currentFile.getName() + " is unreadable.");
        	return false;
        }
        
        logger.logInfo("isDocumentValidForRead()", "File " + currentFile.getName() + " opened with success.");
        return true;
	}
	
	
	/**
	 * isDocumentValidForWrite() : check if the .sql file is valid and available (or not)
	 * @param {File} currentFile
	 * @return {boolean} the response
	 */
	public static boolean isDocumentValidForWrite(File currentFile) {
		
        if(!currentFile.exists()) {   	
        	logger.logError("isDocumentValidForWrite()", "The file " + currentFile.getName() + " not exists.");
        	return false;
        	
        } else if (!currentFile.canWrite()) {
        
        	logger.logError("isDocumentValidForWrite()", "The file " + currentFile.getName() + " is unreadable.");
        	return false;
        }
        
        logger.logInfo("isDocumentValidForWrite()", "File " + currentFile.getName() + " opened with success.");
        return true;
	}
	

	// MISC
	/**
	 * clearConsole() => clear the terminal console
	 * @throws {Exception}
	 */
	public static void clearConsole() throws Exception {
		
	    try {

	     if (System.getProperty("os.name").contains("Windows")) {
	         new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
	     	 out.println('\n');
	     	 out.println("SCREEN CLEARED");
	     } else {
	    	 Runtime.getRuntime().exec("clear");
	    	 out.println("SCREEN CLEARED");
	     }    
	    } catch (IOException | InterruptedException ex) {
	    	throw ex;
	    }
	}
	
	/**
	 *generateRandom()
	 * @param max
	 * @param min
	 * @return {int}
	 */
	public static int generateRandom(int max, int min) {
		int random = (int)Math.floor( Math.random() * (max - min + 1) + min);
		return random;
	}
	
	/**
	 *generateRandomFloat()
	 * @param max
	 * @param min
	 * @return {Float}
	 */
	public static Float generateRandomFloat(Float max, Float min) {

		Float random = Float.valueOf(String.valueOf(Math.floor( Math.random() * (max - min + 1) + min)));  ;
		return random;
	}
	
	/**
	 *generateRandomDouble()
	 * @param max
	 * @param min
	 * @return {Double}
	 */
	public static Double generateRandomDouble(Double max, Double min) {

		Double random = Double.valueOf(String.valueOf(Math.floor( Math.random() * (max - min + 1) + min)));  ;
		return random;
	}

}
