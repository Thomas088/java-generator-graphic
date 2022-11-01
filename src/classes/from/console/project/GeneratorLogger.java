package classes.from.console.project;

import java.util.logging.Level; 
import java.util.logging.Logger;
// logs 
/**
 * GeneratorLogger => Class for log all types of message
 * @author Java Generator Team
 *
 */
public class GeneratorLogger {
	
	private static Logger logger = Logger.getLogger(GeneratorLogger.class.getName());
	
	// [ENUM COLORS] Note that the colors is not enabled in Windows (Linux, macOS OK)
	
	/**
	 * logInfo() : Log information message (success exec, variable value and other stuff)
	 * @param methodName
	 * @param message
	 */
	public void logInfo(String methodName, String message) {
	  logger.log(Level.INFO, EnumList.ColorPaletteEnum.ANSI_BLUE + methodName + " : " + message + EnumList.ColorPaletteEnum.ANSI_RESET);
	}
	
	/**
	 * logWarning() : Log warning message (no credentials, forget value etc)
	 * @param methodName
	 * @param message
	 */
	public void logWarning(String methodName, String message) {
		logger.log(Level.WARNING, EnumList.ColorPaletteEnum.ANSI_YELLOW + methodName + " : " + message + EnumList.ColorPaletteEnum.ANSI_RESET);
	}
	
	/**
	 * logError() : Log error messages (in case of crash, no connexion etc)
	 * @param methodName
	 * @param message
	 */
	public void logError(String methodName, String message) {
		logger.log(Level.SEVERE, EnumList.ColorPaletteEnum.ANSI_RED + methodName + " : " + message + EnumList.ColorPaletteEnum.ANSI_RESET);
	}

}
