package org.openjfx;

import static java.lang.System.out;

import java.io.File;
import java.util.Vector;

import classes.from.console.project.DatabaseController;
import classes.from.console.project.FileHandler;
import classes.from.console.project.GeneratorLogger;
import classes.from.console.project.Parser;
import classes.from.console.project.TableData;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class MainApp extends Application {
	
	// IMPORT DES AUTRES CLASSES NECESSAIRE AU MENU
	DatabaseController database = new DatabaseController();
	Parser parser = new Parser();
	FileHandler fileHandler = new FileHandler();
	Vector<TableData> listOfTables = new Vector<TableData>();
	GeneratorLogger logger = new GeneratorLogger();
	File fileToExport;
	boolean isLogged = false;

    @Override
    public void start(Stage stage) throws Exception {
    	
        Parent root = FXMLLoader.load(getClass().getResource("scene.fxml"));

        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
        
        // START PROGRAM
        try {
        	   	
            isLogged = database.createConnection();
            
            if (isLogged) 
            	 logger.logInfo("createConnection()", "Connexion success.");
	        else logger.logError("createConnection()", "Error on connexion to database.");
 
        	// Parsing automatic mode at start 
        	listOfTables = parser.parse("./labo-test/mcfly.sql");
        	
        	out.println("Here is the list af the tables : ");
        	parser.printArrayTableData(listOfTables);
            
        

        	// OK - TESTED 
        	fileToExport = fileHandler.createFile("eci-insert-test");	
        	fileHandler.writeToFile(10, listOfTables, fileToExport.getAbsolutePath());
        	
        	out.println("FILE PATH : " + fileToExport.getAbsolutePath());
        	
        } catch (Exception e) {
        	logger.logError("Main()", e.getMessage());			
		}	

        stage.setTitle("JavaFX 11");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}