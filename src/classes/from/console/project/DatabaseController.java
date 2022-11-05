package classes.from.console.project;
// db config

import java.util.ArrayList;
// datas
import java.util.Vector;

//import io.github.cdimascio.dotenv.Dotenv;

import static java.lang.System.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * DatabaseController => The class who interact with the database
 * @author Java Generator Team
 *
 */
	public class DatabaseController {
		
		// Credentials (in clear for the moment)
		private static final StringBuilder databaseName = new StringBuilder();
		private static final StringBuilder url = new StringBuilder();
		private static final StringBuilder user = new StringBuilder(); // The main user in MariaDB
		private static final StringBuilder pwd = new StringBuilder(); // Type your password
		
		// Utils
		private static final GeneratorLogger logger = new GeneratorLogger();
		
		// DB
		private static Connection connection;
		private static PreparedStatement statement;
		
		public DatabaseController() {
			
			initCredentials();
			
			databaseName.append("fake_database");
			url.append("jdbc:mariadb://localhost:3306/");
	    	user.append("root");
	    	pwd.append("password01");
			
		}
		
		/**
		 * initStrings() : init all the StringBuilders
		 */
		private static void initCredentials() {
			
			databaseName.setLength(0);
			url.setLength(0);
			user.setLength(0);
			pwd.setLength(0);
		}
		
		/**
		 * callSearchDatasProcedure() - Call the main procedure in database
		 * @param {String} tableToSearch => the table to seach in database
		 * @param {int} limit
		 * @return {Vector<String>} the data result
		 */
		public Vector<String> callSearchDatasProcedure(String tableToSearch, int limit) {
			
		    Vector<String> strArr = new Vector<String>();
				
			try {	
				
					statement = connection.prepareStatement("{call search_datas(?,?);}");
					
					statement.setString(1, tableToSearch);
					statement.setInt(2, limit);
					
					if(!statement.isClosed()) {
						
						ResultSet datas = statement.executeQuery();
			
						while (datas.next()) {
							strArr.add(datas.getString(1).trim());
						}
						
						out.println("SUCCESS : \n");
						return strArr;
						
					} else {
						out.println("STATEMENT NOT OPEN");
					}		
				
			} catch (SQLException e) {
				logger.logError("callSearchDatasProcedure()", e.getMessage()); 			
			}
			
			return strArr;
		}
		
		
		/**
		 * callGenerateRandomNumber() - Call the random number procedure
		 * @param {int} min
		 * @param {int} max
		 * @return {ArrayList<Integer>} the data result
		 */
		public ArrayList<Integer> callGenerateRandomNumber(int min, int max, int limitResult) {
						
			ArrayList<Integer> intArr = new ArrayList<Integer>();
							
			try {	
				
					statement = connection.prepareStatement("{call generate_random_number(?,?,?);}");
					
					statement.setInt(1, min);
					statement.setInt(2, max);
					statement.setInt(3, limitResult);
					
					if(!statement.isClosed()) {
						
						ResultSet datas = statement.executeQuery();
						
						out.println("SUCCESS : \n");
						
						while (datas.next()) {				
							intArr.add(datas.getInt(1));	
						}
						
						out.println("SUCCESS : \n");
						return intArr;
						
					} else {
						out.println("STATEMENT NOT OPEN");
					}		
				
			} catch (SQLException e) {
				logger.logError("callGenerateRandomNumber()", e.getMessage()); 			
			}
			
			return intArr;
		}
		
		/**
		 * callGenerateRandomDate() - Call the random number date
		 * @param {String} dateStart
		 * @param {String} dateEnd
		 * @return {Vector<String>} the data response 
		 */
		public Vector<String> callGenerateRandomDate(String dateStart, String dateEnd, int limitResult) {			
			
			Vector<String> strArr = new Vector<String>();
							
			try {	
				
					statement = connection.prepareStatement("{call generate_random_date(?,?,?);}");
					
					statement.setString(1, dateStart);
					statement.setString(2, dateEnd);
					statement.setInt(3, limitResult);
					
					if(!statement.isClosed()) {
						
						ResultSet datas = statement.executeQuery();
						
						while (datas.next()) {
							strArr.add(datas.getString(1).trim());
						} 
						
						out.println("SUCCESS : \n");
						return strArr;
						
					} else {
						out.println("STATEMENT NOT OPEN");
					}
					
			} catch (SQLException e) {
				logger.logError("callGenerateRandomDate()", e.getMessage()); 			
			}
			
			return strArr;
		}
		
		/**
		 * callGenerateRandomDatetime() - Call the random number date
		 * @param {String} dateStart
		 * @param {String} dateEnd
		 * @return {Vector<String>} the data response 
		 */
		public Vector<String> callGenerateRandomDatetime(String dateStart, String dateEnd, int limitResult) {			
			
			Vector<String> strArr = new Vector<String>();
							
			try {	
				
					statement = connection.prepareStatement("{call generate_random_datetime(?,?,?);}");
					
					statement.setString(1, dateStart);
					statement.setString(2, dateEnd);
					statement.setInt(3, limitResult);
					
					if(!statement.isClosed()) {
						
						ResultSet datas = statement.executeQuery();
						
						while (datas.next()) {
							strArr.add(datas.getString(1).trim());
						} 
						
						out.println("SUCCESS : \n");
						return strArr;
						
					} else {
						out.println("STATEMENT NOT OPEN");
					}
					
			} catch (SQLException e) {
				logger.logError("callGenerateRandomDatetime()", e.getMessage()); 			
			}
			
			return strArr;
		}
		
		
		/**
		 * callGenerateRandomTimestamp() - Call the random number date
		 * @param {String} dateStart
		 * @param {String} dateEnd
		 * @return {Vector<String>} the data response
		 */
		public Vector<String> callGenerateRandomTimestamp(String TPStart, String TPEnd, int limitResult) {
			
            boolean isLogged = createConnection();
            
            if (isLogged) 
            	 logger.logInfo("createConnection()", "Connexion success.");
	        else logger.logError("createConnection()", "Error on connexion to database.");
					
			Vector<String> strArr = new Vector<String>();
							
			try {	
				
					statement = connection.prepareStatement("{call generate_random_timestamp(?,?,?);}");
					
					statement.setString(1, TPStart);
					statement.setString(2, TPEnd);
					statement.setInt(3, limitResult);
					
					if(!statement.isClosed()) {
						
						ResultSet datas = statement.executeQuery();
					
						while (datas.next()) {
							strArr.add(datas.getString(1).trim());
						} 
						
						out.println("SUCCESS : \n");
						return strArr;
						
					} else {
						out.println("STATEMENT NOT OPEN");
					}		
				
			} catch (SQLException e) {
				logger.logError("callGenerateRandomTimestamp()", e.getMessage()); 			
			}
			
			return strArr;
		}
		
		/**
		 * callGenerateRandomTimestamp() - Call the random number date
		 * @return {String} the data response
		 */
		public String callGenerateCurrentTimestamp() {
			
            boolean isLogged = createConnection();
            
            if (isLogged) 
            	 logger.logInfo("createConnection()", "Connexion success.");
	        else logger.logError("createConnection()", "Error on connexion to database.");
					
			Vector<String> strArr = new Vector<String>();
							
			try {	
				
					statement = connection.prepareStatement("{call generate_current_timestamp();}");
					
					if(!statement.isClosed()) {
						
						ResultSet datas = statement.executeQuery();
					
						while (datas.next()) {
							strArr.add(datas.getString(1).trim());
						} 
						
						out.println("SUCCESS : \n");
						return strArr.get(0);
						
					} else {
						out.println("STATEMENT NOT OPEN");
					}		
				
			} catch (SQLException e) {
				logger.logError("callGenerateRandomTimestamp()", e.getMessage()); 			
			}
			
			return strArr.get(0);
		}
		
		/**
		 * callGenerateLipsum() - Call the random number date
		 * @param {int} min
		 * @param {int} max
		 * @return {Vector<String>} the data response
		 */
		public String callGenerateLipsum(int min, int max, int startsWithLoremIpsum) {
			
            boolean isLogged = createConnection();
            
            if (isLogged) 
            	 logger.logInfo("createConnection()", "Connexion success.");
	        else logger.logError("createConnection()", "Error on connexion to database.");
					
			Vector<String> strArr = new Vector<String>();
							
			try {	
				
					statement = connection.prepareStatement("{call loremIpsum(?, ?, ?);}");
					statement.setInt(1, min);
					statement.setInt(2, max);
					statement.setInt(3, startsWithLoremIpsum);
					
					if(!statement.isClosed()) {
						
						ResultSet datas = statement.executeQuery();
					
						while (datas.next()) {
							strArr.add(datas.getString(1).trim());
						} 
						
						out.println("SUCCESS : \n");
						return strArr.get(0);
						
					} else {
						out.println("STATEMENT NOT OPEN");
					}		
				
			} catch (SQLException e) {
				logger.logError("callGenerateRandomTimestamp()", e.getMessage()); 			
			}
			
			return strArr.get(0);
		}
		
		
		
		/**
		 * callGenerateRandomBoolean() - Call the random number boolean
		 * @param {String} dateStart
		 * @param {String} dateEnd
		 * @return {Vector<String>} the data response 
		 */
		public Vector<String> callGenerateRandomBoolean(int limitResult) {			
			
			Vector<String> strArr = new Vector<String>();
							
			try {	
				
					statement = connection.prepareStatement("{call generate_random_boolean(?);}");
					
					statement.setInt(1, limitResult);
					
					if(!statement.isClosed()) {
						
						ResultSet datas = statement.executeQuery();
						
						while (datas.next()) {
							strArr.add(datas.getString(1).trim());
						} 
						
						out.println("SUCCESS : \n");
						return strArr;
						
					} else {
						out.println("STATEMENT NOT OPEN");
					}
					
			} catch (SQLException e) {
				logger.logError("callGenerateRandomBoolean()", e.getMessage()); 			
			}
			
			return strArr;
		}
		
		
		/**
		 * createConnection() : create a session connexion to mariaDB 
		 * @return {boolean} : if the connection success or fail
		 */
		public boolean createConnection() {

			try {
	
				    connection = DriverManager.getConnection(url.toString() 
				    										+ databaseName.toString() 
				    										+ "?user="+ user.toString() 
				    										+ "&password="+ pwd.toString());
				    
				    if (connection.isValid(5)) return true;
			
				} catch (SQLException e) {
					logger.logError("createConnection()", e.getMessage());
					return false;
					
				}
			
			return false;
		}
		
		/**
		 * callGenerateRandomTime() - Call the random number date
		 * @param {String} dateStart
		 * @param {String} dateEnd
		 * @return {Vector<String>} the data response
		 */
		public Vector<String> callGenerateRandomTime(String TPStart, String TPEnd, int limitResult) {
			
            boolean isLogged = createConnection();
            
            if (isLogged) 
            	 logger.logInfo("createConnection()", "Connexion success.");
	        else logger.logError("createConnection()", "Error on connexion to database.");
					
			Vector<String> strArr = new Vector<String>();
							
			try {	
				
					statement = connection.prepareStatement("{call generate_random_time(?,?,?);}");
					
					statement.setString(1, TPStart);
					statement.setString(2, TPEnd);
					statement.setInt(3, limitResult);
					
					if(!statement.isClosed()) {
						
						ResultSet datas = statement.executeQuery();
					
						while (datas.next()) {
							strArr.add(datas.getString(1).trim());
						} 
						
						out.println("SUCCESS : \n");
						return strArr;
						
					} else {
						out.println("STATEMENT NOT OPEN");
					}		
				
			} catch (SQLException e) {
				logger.logError("callGenerateRandomTimestamp()", e.getMessage()); 			
			}
			
			return strArr;
		}
		
    }

