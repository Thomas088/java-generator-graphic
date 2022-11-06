package org.openjfx;
import static java.lang.System.out;

// JFX Data Arrays
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

// JFX components
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;

// Utils
import java.io.File;
import java.util.Vector;

import classes.from.console.project.EnumList;
// Console project packages
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
	private GeneratorLogger logger = new GeneratorLogger();
	
    @FXML
    private Pagination pagination;
	
	private StringBuilder attrTempStr = new StringBuilder();
	private StringBuilder typeTempStr = new StringBuilder();

	private Vector<TableData> listOfTables;
	private Vector<FXMLDataHelper> attributeListHelper;
	
	private FXMLDataHelper newHelper;
	
	// Observables '$' suffixed because of JavaScript convention (useful)
    private ObservableList<FXMLDataHelper> helperTableArray$;
    
    // UI TABLE VIEW VARIABLES
    
    private TableView<FXMLDataHelper> table;    
    private TableColumn<FXMLDataHelper, String> fieldNameColumn;   
    private TableColumn<FXMLDataHelper, String> typesColumn;    
    private TableColumn<FXMLDataHelper, ComboBox<EnumList.MariaAttributeTypesListEnum>> dataTypeColumn;
    
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
    private VBox TblN;
    
    @FXML
    private ImageView Image ;
    
    @FXML
    /**
     * initialize() : init variables
     */
    public void initialize() {
    		
    	// INIT INTEGER VARIABLES
    	indexTable = 0;
    	nbLines = 0;
    	itr = 0;
    	
        fieldNameColumn = new TableColumn<FXMLDataHelper, String>("ATTRIBUTE");
        typesColumn = new TableColumn<FXMLDataHelper, String>("TYPE");
        dataTypeColumn = new TableColumn<FXMLDataHelper, ComboBox<EnumList.MariaAttributeTypesListEnum>>("DATABASE DATA TYPE NEEDED");  
        
        // SET COLUMNS WITH CORRECT SIZES
        fieldNameColumn.setPrefWidth(250);
        typesColumn.setPrefWidth(150);
        dataTypeColumn.setPrefWidth(250);
       	
    }
     
    @Override
    public void finalize() {
    	helperTableArray$.stream().close();
    }
    
    
    @FXML
    /**
     * parseFile() : Parse the current selected file
     */
    private void parseFile() {
   
    try {
     	
    		final FileChooser fileChooser = new FileChooser();		
    		File file = fileChooser.showOpenDialog(MainApp.getPrimaryStage());
    		
    	  	listOfTables = parser.parse(file.getAbsolutePath());	  	
    	  	createFxElementsFromDataParse();
    		
    	} catch (Exception e) {
    		logger.logError("parseFile()", e.getMessage());
    	}
    	
    }
    
    private void convertDataForTableView(TableData table) {
    	
    try { 
    	
    		attributeListHelper = new Vector<FXMLDataHelper>();
    		
    		for (int i = 0; i < table.getAttributeList().size(); i++) {
    			
    	        newHelper = new FXMLDataHelper();
    			final int iTemp = i;
    			
    			// set helper for datas
    			attrTempStr.append(table.getAttributeList().get(iTemp));
    			typeTempStr.append(table.getTypesList().get(iTemp).toUpperCase()); // uppsercase because only for display
    			newHelper.setAttributeName(attrTempStr.toString());
    			newHelper.setTypeName(typeTempStr.toString());
    			
    			// push
    			attributeListHelper.add(newHelper);		
    			initStrings();
    		}
    		
    	} catch (Exception e) {
    		logger.logError("convertDataForTableView()", e.getMessage());
    	}
    	
    }  
    
	@FXML
    /*
     * createFxElementsFromDataParse()
     */
    private void createFxElementsFromDataParse() {
		
		
	    	// INIT COLUMNS
		    table = new TableView<FXMLDataHelper>();
		
		    convertDataForTableView(listOfTables.get(indexTable));
		    
 	    	String tablename = listOfTables.get(indexTable).getTableName();
 	    	currentTableNameUI.setText("TABLE : "+ tablename.toUpperCase());
 	    	
		    helperTableArray$ = FXCollections.observableArrayList(attributeListHelper);
    
    	    fieldNameColumn.setCellValueFactory(new PropertyValueFactory<FXMLDataHelper, String>("attributeName"));
    	    typesColumn.setCellValueFactory(new PropertyValueFactory<FXMLDataHelper, String>("typeName"));
    	    dataTypeColumn.setCellValueFactory(new PropertyValueFactory<FXMLDataHelper, ComboBox<EnumList.MariaAttributeTypesListEnum>>("databaseLinkType"));
 	   	
 	    	table.setItems(helperTableArray$);
 	    	table.getColumns().addAll(fieldNameColumn, typesColumn, dataTypeColumn);
 	    	tablesViewContainer.getChildren().add(table);
    }
    
    @FXML
    /**
     * generateFile() : generate file
     */
    private void generateFile() {
    	fileToExport = fileHandler.createFile("eci-insert-test");
    	fileHandler.writeToFile(20, listOfTables, fileToExport.getAbsolutePath());;
    }
   
    // -------- NAVIGATION ------- // 
    
    @FXML
    /** typescript
     *  num: number = 0;
     * getPreviousTable()
     */
    private void getPreviousTable() {
    	   	
    	if (listOfTables.get(indexTable).getDatabaseEquivalenceList().size() == 0) {
    		
        	for (int i = 0; i < listOfTables.get(indexTable).getAttributeList().size(); i++) {
        		
        			if (table.getItems().get(i).getDatabaseLinkType().toString().length() == 0 || 
        			    table.getItems().get(i).getDatabaseLinkType() == null) break;
        		
            		String item = table.getItems().get(i).getDatabaseLinkType().getValue().getType().toLowerCase();      	
            		listOfTables.get(indexTable).pushInDatabaseEquivalenceList(item);     	         	
    	    }
  	
    	}
    	
    	out.println("TABLE : " + listOfTables.get(indexTable).getTableName());
    	out.println("ATTRIBUTES : " + listOfTables.get(indexTable).getAttributeList());
    	out.println("DB LIST : " + listOfTables.get(indexTable).getDatabaseEquivalenceList());
    	
    indexTable--;
		
	if (indexTable < 0) {
		indexTable++;
//		nextTableButton.setDisable(true);
		return;
	}
	
	clearTableView();
	createFxElementsFromDataParse();	
    }
    
    @FXML
    /**
     * getNextTable()
     */
    private void getNextTable() {

    		
        	if (listOfTables.get(indexTable).getDatabaseEquivalenceList().size() == 0) {
        		
            	for (int i = 0; i < listOfTables.get(indexTable).getAttributeList().size(); i++) {
            		
            			if (table.getItems().get(i).getDatabaseLinkType().toString().length() == 0 || 
            			    table.getItems().get(i).getDatabaseLinkType() == null) break;
            		
                		String item = table.getItems().get(i).getDatabaseLinkType().getValue().getType().toLowerCase();      	
                		listOfTables.get(indexTable).pushInDatabaseEquivalenceList(item);     	         	
        	    }
      	
        	}
        	
        	out.println("TABLE : " + listOfTables.get(indexTable).getTableName());
        	out.println("ATTRIBUTES : " + listOfTables.get(indexTable).getAttributeList());
        	out.println("DB LIST : " + listOfTables.get(indexTable).getDatabaseEquivalenceList());
        	
        indexTable++;
    		
		if (indexTable == listOfTables.size()) {	
			indexTable--;
//			nextTableButton.setDisable(true);
			return;
		}
		
    	clearTableView();
    	createFxElementsFromDataParse();	
    }
    
 
 	// -------- MISC ------- // 
    
    private void initStrings() {
		attrTempStr.setLength(0);
		typeTempStr.setLength(0);
    }
    
    /**
     *  clearTableView()
     */ 
    private void clearTableView() {
    	tablesViewContainer.getChildren().clear();
    }
    
    @FXML
    /**
     * exitProgram()
     */
    private void exitProgram() {
        System.exit(0);
    }
}