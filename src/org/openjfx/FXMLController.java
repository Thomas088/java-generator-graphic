package org.openjfx;
import static java.lang.System.out;

import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.value.ObservableValue;

//import javafx.beans.property.SimpleStringProperty;

// JFX Data Arrays
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

// JFX components
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

// Utils
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;

// Console project packages
import classes.from.console.project.DatabaseController;
import classes.from.console.project.FileHandler;
import classes.from.console.project.GeneratorLogger;
import classes.from.console.project.Parser;
import classes.from.console.project.TableData;

/**
 * FXMLController => The main controller who handle the whole user interactions
 * @author Java Generator Team
 *
 */
public class FXMLController {
	
	// CLASSES NEEDED FOR OPERATIONS
	private Parser parser = new Parser();
	private File fileToExport;
	private FileHandler fileHandler = new FileHandler();
	GeneratorLogger logger = new GeneratorLogger();
	
	private Vector<TableData> listOfTables;
	
	// Observables '$' suffixed because of JavaScript convention (useful)
    private ObservableList<String> datasTablesList$; 
    private ObservableList<TableData> objTableArray$;
    
    // UI TABLE VIEW VARIABLES
    private TableView<TableData> table;
    private TableColumn<TableData, String> fieldNameColumn;
    private TableColumn<TableData, String> typesColumn;
    private TableColumn<TableData, String> dataTypeColumn;
    private TableColumn<TableData, String> dbDataTypeColumn;
    
    private int indexTable;
    private int nbLines;
    private int itr;
    
    @FXML
    private Button parseFileButton;
    
    @FXML
    private Button previousTableButton;
    
    @FXML
    private Button nextTableButton;
    
    @FXML
    private VBox tablesViewContainer;
    
    @FXML
    private Label currentTableNameUI;
    
    @FXML
    /**
     * initialize() : init variables
     */
    public void initialize() {
    	
    	// INIT VARIABLES
    	indexTable = 0;
    	nbLines = 0;
    	itr = 0;
    	
    	table = new TableView<TableData>();
        fieldNameColumn = new TableColumn<TableData, String>("ATTRIBUTE");
        typesColumn = new TableColumn<TableData, String>("TYPES");
        dataTypeColumn = new TableColumn<TableData, String>("FROM FILE DATA TYPE");
        dbDataTypeColumn = new TableColumn<TableData, String>("DATABASE DATA TYPE NEEDED");
        
        // SET COLUMNS WITH CORRECT SIZES
        fieldNameColumn.setPrefWidth(220);
        typesColumn.setPrefWidth(220);
        dataTypeColumn.setPrefWidth(220);
        dbDataTypeColumn.setPrefWidth(220);
        
    }
    
    @FXML
    /**
     * parseFile() : Parse the current selected file
     */
    private void parseFile() {
    	
    	out.println("PARSE FILE BUTTON OK");
    	
    try {
    		
    	  	listOfTables = parser.parse("./labo-test/mcfly.sql");
    	  	parser.printArrayTableData(listOfTables);
    	  	createFxElementsFromDataParse();
    		
    	} catch (Exception e) {
    		logger.logError("parseFile()", e.getMessage());
    	}
    	
    }  
    

	@FXML
    /*
     * createFxElementsFromDataParse()
     */
    private void createFxElementsFromDataParse() {
    	   
    	    objTableArray$ = FXCollections.observableArrayList(listOfTables.get(indexTable));
 	    	String tablename = objTableArray$.get(indexTable).getTableName();
 	    	currentTableNameUI.setText(tablename);

 	    	for (String attr : objTableArray$.get(indexTable).getAttributeList()) {
 	    		
	    		fieldNameColumn.setCellValueFactory(p -> new ReadOnlyStringWrapper(attr));
	    		typesColumn.setCellValueFactory(p -> new ReadOnlyStringWrapper(attr));
 	    	}
 	    	
 	    	table.setItems(objTableArray$);
 	    	table.getColumns().addAll(fieldNameColumn, typesColumn, dataTypeColumn, dbDataTypeColumn);
 	    	tablesViewContainer.getChildren().add(table);
    }
    
    @FXML
    /**
     * generateFile() : generate file
     */
    private void generateFile() {
    	fileToExport = fileHandler.createFile("eci-insert-test");
    	fileHandler.writeToFile(10, listOfTables, fileToExport.getAbsolutePath());;
    }
   
    // -------- NAVIGATION ------- // 
    
    @FXML
    /**
     * getPreviousTable()
     */
    private void getPreviousTable() {
    	
    	if(indexTable == 0) return;   	
    	clearTableView();
    	createFxElementsFromDataParse();
    	out.println("PREVIOUS BUTTON");
    }
    
    @FXML
    /**
     * getNextTable()
     */
    private void getNextTable() {
    	
    	if(indexTable == (listOfTables.size() - 1)) return;
    	clearTableView();
    	createFxElementsFromDataParse();
    	out.println("NEXT BUTTON");
    }
    
 	// -------- MISC ------- // 
    
    /**
     *  clearTableView()
     */ 
    private void clearTableView() {
    	table.getItems().clear();
    }
    
    @FXML
    /**
     * exitProgram()
     */
    private void exitProgram() {
        System.exit(0);
    }
}