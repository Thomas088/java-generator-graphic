package classes.from.console.project;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * EnumList => The class who store all the enums used in app
 * @author Java Generator Team
 *
 */
public class EnumList {
	
	// AN ENUM FOR DISPLAY ALL MARIADB DATA TYPES
	// NOT EXHAUSTIVE
	/**
	 * 
	 * @author Java Generator Team
	 *
	 */
	public static enum MariaAttributeTypesListEnum { 
		
		// Integers
		INT("INT"),
		SMALLINT("SMALLINT"),
		TINYINT("TINYINT"),
		MEDIUMINT("MEDIUMINT"),
		BIGINT("BIGINT"),
		
		// Floats and Doubles
		FLOAT("FLOAT"),
		DOUBLE("DOUBLE"),
		DECIMAL("DECIMAL"),
		
		// Strings
		VARCHAR("VARCHAR"),
		TINYTEXT("TINYTEXT"),
		TEXT("TEXT"),
		LONGTEXT("LONGTEXT"),
		
		// ID's
		AUTO_INCREMENT("AUTO_INCREMENT"),
		PRIMARY_KEY("PRIMARY_KEY"),
		FOREIGN_KEY("FOREIGN_KEY"),

		// Binary types
		BLOB("BLOB"),
		
		// Date / Time
		DATE("DATE"),
		TIME("TIME"),
		DATETIME("DATETIME"),
		TIMESTAMP("TIMESTAMP"),
		
		// booleans
		BOOL("BOOL"),
		BOOLEAN("BOOLEAN"),
		
		// MISC
		NULL("NULL");

		private String currentType;
	    private static Map<String, MariaAttributeTypesListEnum> MARIA_TYPES_MAP;

	    /**
	     * MariaAttributeTypesListEnum() - ENUM CONSTRUCTOR
	     * @param currentType
	     */
	    MariaAttributeTypesListEnum(String currentType) {
	        this.currentType = currentType;
	    }

	    public String getType() {
	        return this.currentType;
	    }

	    static {
	    	
	        Map<String, MariaAttributeTypesListEnum> mariaTypesMap = new HashMap<String, MariaAttributeTypesListEnum>();
	        
	        for (MariaAttributeTypesListEnum type : MariaAttributeTypesListEnum.values()) {
	        	mariaTypesMap.put(type.getType().toLowerCase(), type); // on lie le nom du type avec son l'enum associe
	        }
	        
	        MARIA_TYPES_MAP = Collections.unmodifiableMap(mariaTypesMap);
	    }

	    // [BONUS] : obtenir l'enum via la string (donc l'inverse)
	    public static MariaAttributeTypesListEnum getAsEnum(String name) {
	        return MARIA_TYPES_MAP.get(name.toLowerCase());
	    }
	}
	
	
	// AN ENUM FOR DISPLAY ALL FAKE_DATABASE.FAKE_DATAS DATA TYPES
	// NOT EXHAUSTIVE
	/**
	 * 
	 * @author Java Generator Team
	 *
	 */
	public static enum FakeDatasFromFakeDatabaseTypesEnum {
		
		FIRSTNAME("FIRSTNAME"),
		LASTNAME("LASTNAME"),
		ADDRESS("ADDRESS"),
		ZIP_CODE("ZIP_CODE"),
		CITY("CITY"),
		COUNTRY("COUNTRY"),
		PHONE("TEXT"),
		EMAIL("EMAIL"),
		JOB("JOB");
	    
		private String dataType;

	    // CONSTRUCTOR
	    FakeDatasFromFakeDatabaseTypesEnum(String currentType) {
	        this.dataType = currentType;
	    }

	    public String getName() {
	        return this.dataType;
	    }
	}
	
	// AN ENUM FOR DISPLAY ALL COLORS (Optionnal - for style JavaFX components ?)
	/**
	 * 
	 * @author Java Generator Team
	 *
	 */
	public static enum ColorPaletteEnum {
		
	    //Color end string, color reset
	    RESET("\033[0m"),

	    // Regular Colors (CLASSIC)
	    BLACK("\033[0;30m"),    
	    RED("\033[0;31m"),      
	    GREEN("\033[0;32m"),    
	    YELLOW("\033[0;33m"),   
	    BLUE("\033[0;34m"),     
	    MAGENTA("\033[0;35m"),  
	    CYAN("\033[0;36m"),     
	    WHITE("\033[0;37m"),    

	    // Bold
	    BLACK_BOLD("\033[1;30m"),   
	    RED_BOLD("\033[1;31m"),     
	    GREEN_BOLD("\033[1;32m"),   
	    YELLOW_BOLD("\033[1;33m"),  
	    BLUE_BOLD("\033[1;34m"),    
	    MAGENTA_BOLD("\033[1;35m"), 
	    CYAN_BOLD("\033[1;36m"),    
	    WHITE_BOLD("\033[1;37m"),   

	    // Underline
	    BLACK_UNDERLINED("\033[4;30m"),     
	    RED_UNDERLINED("\033[4;31m"),       
	    GREEN_UNDERLINED("\033[4;32m"),     
	    YELLOW_UNDERLINED("\033[4;33m"),    
	    BLUE_UNDERLINED("\033[4;34m"),      
	    MAGENTA_UNDERLINED("\033[4;35m"),   
	    CYAN_UNDERLINED("\033[4;36m"),     
	    WHITE_UNDERLINED("\033[4;37m"),    

	    // Background
	    BLACK_BACKGROUND("\033[40m"),   
	    RED_BACKGROUND("\033[41m"),     
	    GREEN_BACKGROUND("\033[42m"),   
	    YELLOW_BACKGROUND("\033[43m"),  
	    BLUE_BACKGROUND("\033[44m"),    
	    MAGENTA_BACKGROUND("\033[45m"), 
	    CYAN_BACKGROUND("\033[46m"),    
	    WHITE_BACKGROUND("\033[47m"),   

	    // High Intensity
	    BLACK_BRIGHT("\033[0;90m"),     
	    RED_BRIGHT("\033[0;91m"),       
	    GREEN_BRIGHT("\033[0;92m"),     
	    YELLOW_BRIGHT("\033[0;93m"),    
	    BLUE_BRIGHT("\033[0;94m"),      
	    MAGENTA_BRIGHT("\033[0;95m"),   
	    CYAN_BRIGHT("\033[0;96m"),      
	    WHITE_BRIGHT("\033[0;97m"),     

	    // Bold High Intensity
	    BLACK_BOLD_BRIGHT("\033[1;90m"),    
	    RED_BOLD_BRIGHT("\033[1;91m"),     
	    GREEN_BOLD_BRIGHT("\033[1;92m"),    
	    YELLOW_BOLD_BRIGHT("\033[1;93m"),   
	    BLUE_BOLD_BRIGHT("\033[1;94m"),     
	    MAGENTA_BOLD_BRIGHT("\033[1;95m"),  
	    CYAN_BOLD_BRIGHT("\033[1;96m"),     
	    WHITE_BOLD_BRIGHT("\033[1;97m"),    

	    // High Intensity background
	    BLACK_BACKGROUND_BRIGHT("\033[0;100m"),     
	    RED_BACKGROUND_BRIGHT("\033[0;101m"),       
	    GREEN_BACKGROUND_BRIGHT("\033[0;102m"),     
	    YELLOW_BACKGROUND_BRIGHT("\033[0;103m"),    
	    BLUE_BACKGROUND_BRIGHT("\033[0;104m"),      
	    MAGENTA_BACKGROUND_BRIGHT("\033[0;105m"),   
	    CYAN_BACKGROUND_BRIGHT("\033[0;106m"),      
	    WHITE_BACKGROUND_BRIGHT("\033[0;107m"),     
		
		// ANSI
	    ANSI_RESET("\u001B[0m"),
	    ANSI_BLACK("\u001B[30m"),
	    ANSI_RED("\u001B[31m"),
	    ANSI_GREEN("\u001B[32m"),
	    ANSI_YELLOW("\u001B[33m"),
	    ANSI_BLUE("\u001B[34m"),
	    ANSI_PURPLE("\u001B[35m"),
	    ANSI_CYAN("\u001B[36m"),
	    ANSI_WHITE("\u001B[37m");

	    private final String colorCode;

	    /**
	     * ColorPaletteEnum() : A basic color palette (with ANSI Colors) - ENUM CONSTRUCTOR
	     * @param code
	     */
	    ColorPaletteEnum(String code) {
	        this.colorCode = code;
	    }

	    public String getColorCode() {
	        return this.colorCode;
	    }
	}
}
