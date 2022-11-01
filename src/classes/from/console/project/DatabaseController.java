package classes.from.console.project;
// db config

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
		private static final Vector<String> dataVector = new Vector<String>();
		
		// DB
		private static Connection connection;
		private static PreparedStatement statement;
		
		public DatabaseController() {
			
			initCredentials();
			
//		------------------------------------------------------- // 
//			TODO : handle Dotenv !
			
//			Dotenv dotenv = Dotenv.load();			
//			databaseName.append(dotenv.get("MARIADB_DB_NAME"));
//			url.append(dotenv.get("MARIADB_DB_URL"));
//	    	user.append(dotenv.get("MARIADB_USER"));
//	    	pwd.append(dotenv.get("MARIADB_PASSWORD"));
			
//			------------------------------------------------------- // 
			
			databaseName.append("fake_database");
			url.append("jdbc:mariadb://localhost:3306/");
	    	user.append("root");
	    	pwd.append("");
			
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
		 */
		public void callSearchDatasProcedure(String tableToSearch, int limit) {
				
			try {	
				
					statement = connection.prepareStatement("{call search_datas(?,?);}");
					
					statement.setString(1, tableToSearch);
					statement.setInt(2, limit);
					
					if(!statement.isClosed()) {
						
						ResultSet datas = statement.executeQuery();
			
						while (datas.next()) {
							dataVector.add(datas.getString(1).trim());
						}
						
						out.println("SUCCESS : \n");
						out.println(dataVector);
						
					} else {
						out.println("STATEMENT NOT OPEN");
					}		
				
			} catch (SQLException e) {
				logger.logError("callSearchDatasProcedure()", e.getMessage()); 			
			}		
		}
		
		/**
		 * callGenerateRandomNumber() - Call the random number procedure
		 * @param {int} min
		 * @param {int} max
		 */
		public void callGenerateRandomNumber(int min, int max) {
							
			try {	
				
					statement = connection.prepareStatement("{call generate_random_number(?,?);}");
					
					statement.setInt(1, min);
					statement.setInt(2, max);
					
					if(!statement.isClosed()) {
						
						ResultSet datas = statement.executeQuery();
						
						out.println("SUCCESS : \n");
						
						while (datas.next()) out.println(datas.getInt(1));
						
					} else {
						out.println("STATEMENT NOT OPEN");
					}		
				
			} catch (SQLException e) {
				logger.logError("callGenerateRandomNumber()", e.getMessage()); 			
			}		
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
    }

