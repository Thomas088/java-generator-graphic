package org.openjfx;

import static java.lang.System.*;

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
	FileHandler fileHandler = new FileHandler();
	GeneratorLogger logger = new GeneratorLogger();
	File fileToExport;
	boolean isLogged = false;
	
	static Stage currentStage;
	
    @Override
    public void start(Stage stage) throws Exception {
    	
        boolean isLogged = database.createConnection();
        
        if (isLogged) 
        	 logger.logInfo("createConnection()", "Connexion success.");
        else logger.logError("createConnection()", "Error on connexion to database.");
    	
        Parent root = FXMLLoader.load(getClass().getResource("scene.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
        stage.setTitle("JAVA GENERATOR ECI");
        stage.setScene(scene);
        setPrimaryStage(stage);
        stage.show();
    }

    public static void main(String[] args) {
    	launch(args);
    }
    
    public static void setPrimaryStage(Stage newStage) {
        currentStage = newStage;
    }
    
    public static Stage getPrimaryStage() {
        return currentStage;
    }

}