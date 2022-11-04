package classes.from.console.project;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Map;

import javafx.beans.property.StringProperty;

/**
 * TableData => The architecture defined for retrieves data from .sql file (used in Parser class)
 * @author Java Generator Team
 *
 */
public class TableData {
	
	// TO REPLACE
	private ArrayList<String> attributeList;
	private ArrayList<String> databaseEquivalenceList;
	private ArrayList<String> typesList;
	private ArrayList<String> varcharList;

	private ArrayList<String> foreignKeyList;
	private ArrayList<String> isNullList;

	private String tableName = "";
	
	// NEW DATA ARCHITECTURE 
	private Map<String, ArrayList<String>> attributeDatas; 

	private boolean isIntermediaryTable;

	public TableData() {
		
		this.tableName = "";
		this.isIntermediaryTable = false;
		
		// ARRAYS VERSION - FIRST ARCHITECTURE
		this.attributeList = new ArrayList<String>();
		this.databaseEquivalenceList = new ArrayList<String>();	
		this.typesList = new ArrayList<String>();
		this.foreignKeyList = new ArrayList<String>();
		this.isNullList = new ArrayList<String>();
		
		// Why so many arrays ?? 
		// Explanations 
		// We play with the index of each tables
		
		// Examples :
		
		// 1)
		// attributeList : [idMenu, name, price, pictureUrl, littleDescription, isDeleted, idCatalogfood]
		// typesList :     [int, varchar, float, varchar, text, bool, int]
		// nullList :      [not-null, not-null, not-null, null (it's just url), null (it's just text), not-null, not-null]
		
		// 2)
		// attributeList : [idOrderfood, reference, date, amount, state, shipStart, shipEnd, isDeleted, idDrone, idAddress]
	    // typesList :     [int, int, date, float, int, text, text, bool, int, int]
		// nullList :      [not-null, not-null, not-null, null (it's just url), null (it's just text), not-null, not-null]
		
		// So for retrives the type of... 'reference' for example
		// attributeList(specified index) will always be the same index as typesList(specified index)
		
		// Result example
		// attributeList(specified index) = reference (order reference number)
		// AND typesList(specified index) = int
		// AND nullList(specified index) = not-null
		
		// ------------------------------------------------------------------ //
		
		// HASHMAP VERSION (DICTIONNARY)
		this.attributeDatas = new HashMap<String, ArrayList<String>>();
		
		// attributeDatas ???
		
		// Explanations of the new architecture 
		// A map that contains all the attributs informations 
		
		// The left side is for the attribute (the key), the right side is the attribute datas stored in array
		// You have to handle and memorize the order
		
		// Order :
		
		// 1) Database equivalence (firstname, lastname etc) - For primitives types, we will use the mariaTypes Enum
		// 2) The type (obviously - int, varchar etc)
		// 3) The length (if attribute = varchar, you have to get the length... else 0)
		// 4) NULL / NOT NULL (obviously - useful if the user want to enter null values)
		
		// Exemple :
		
		// Key = firstname, Value = ["no-link-to-db-yet", varchar, 50, false]
		// Key = birthdate, Value = ["DATE", text, 0, false]
	    // Key = gender, Value = ["TEXT", varchar, 50, false]
	    // Key = avatarUrl, Value = ["TEXT", varchar, 255, false]
		// Key = usertype, Value = ["TINYINT", int, 0, false]
		// Key = isDeleted, Value = ["BOOLEAN", bool, 0, false]
	}
	
	// ------------------- GETTERS / SETTERS --------------------- //
	
	/**
	 * getTableName() : get name of current table
	 * @return {String} the tableName
	 */
	public String getTableName() {
		return tableName;
	}

	/**
	 * setTableName() : set name of current table
	 * @param {String} tableName the tableName to set
	 */
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	/**
	 * getAttributeList() : get the attribute list of the current table
	 * @return {ArrayList<String>} the attributeList
	 */
	public ArrayList<String> getAttributeList() {
		return attributeList;
	}
	
	/**
	 * pushInAttributeList() : insert new attribute into the attribute list of the current table
	 * @param {String} attributeList the attributeList to set
	 */
	public void pushInAttributeList(String attribute) {
		this.attributeList.add(attribute);
	}

	/**
	 * setAttributeList() : set the new list of attribute into the current attributeList of the current table
	 * @param {ArrayList<String>} attributeList the attributeList to set
	 */
	public void setAttributeList(ArrayList<String> attributeList) {
		this.attributeList = attributeList;
	}

	/**
	 * getTypesList() : return the array of types (int, varchar etc)
	 * @return {ArrayList<String>} the typesList
	 */
	public ArrayList<String> getTypesList() {
		return typesList;
	}
	
	/**
	 * pushInTypesList() : insert new type into the typeList of the current table
	 * @param {String} typesList the typesList to set
	 */
	public void pushInTypesList(String type) {
		this.typesList.add(type);
	}

	/**
	 * setTypesList() : set the new list of types into the current typesList
	 * @param {ArrayList<String>} typesList the typesList to set
	 */
	public void setTypesList(ArrayList<String> typesList) {
		this.typesList = typesList;
	}

	/**
	 * isIntermediaryTable() : return if the table is intermediary (pivot) or not
	 * @return {boolean} the isIntermediaryTable
	 */
	public boolean isIntermediaryTable() {
		return isIntermediaryTable;
	}

	/**
	 * setIntermediaryTable() : set the table intermediary state of the current table
	 * @param {boolean} isIntermediaryTable : the isIntermediaryTable variable to set
	 */
	public void setIntermediaryTable(boolean isIntermediaryTable) {
		this.isIntermediaryTable = isIntermediaryTable;
	}

	/**
	 * getForeignKeyList() : return the list of the foreign keys (if the table is intermediary)
	 * @return {ArrayList<String>} the current foreign key list
	 */
	public ArrayList<String> getForeignKeyList() {
		return foreignKeyList;
	}

	/**
	 * pushInForeignKeyList() : insert new foreign key into the foreignKeyList of the current tableData
	 * @param {String} foreignKey : the attributeList to set
	 */
	public void pushInForeignKeyList(String foreignKey) {
		this.foreignKeyList.add(foreignKey);
	}

	/**
	 * setForeignKeyList() : set the new list of foreign keys into the current foreignKeyList of the current tableData
	 * @param {ArrayList<String>} newForeignKeyList
	 */
	public void setForeignKeyList(ArrayList<String> newForeignKeyList) {
		this.foreignKeyList = newForeignKeyList;
	}
	
	/**
	 * getAttributeDatas() : print all datas for the current TableData
	 * @return {Map<String, ArrayList<String>>} the current map
	 */
	public Map<String, ArrayList<String>> getAttributeDatas() {
		return attributeDatas;
	}

	/**
	 * setAttributeDatas() : set the new data list into the current TableData
	 * @param {Map<String, ArrayList<String>>} attributeAndLength
	 */
	public void setAttributeDatas(Map<String, ArrayList<String>> attributeAndLength) {
		this.attributeDatas = attributeAndLength;
	}
	
	/**
	 * pushInAttributeDatas() : push for each attributes the datas in the current TableData
	 * @param {String} attributeKey
	 * @param {ArrayList<String>} attributeDatas
	 */
	public void pushInAttributeDatas(String attributeKey, ArrayList<String> attributeDatas) {
		this.attributeDatas.put(attributeKey, attributeDatas);
	}
	
	/**
	 * getDatabaseEquivalenceList() : return the equivalence list for all the attribute
	 * @return {ArrayList<String>} the equivalence list as array
	 */
	public ArrayList<String> getDatabaseEquivalenceList() {
		return databaseEquivalenceList;
	}

	/**
	 * setDatabaseEquivalenceList() : set the database data type array (who match the array this.attributeList)
	 * [helper for next procedures calls]  
	 * @param databaseEquivalenceList
	 */
	public void setDatabaseEquivalenceList(ArrayList<String> databaseEquivalenceList) {
		this.databaseEquivalenceList = databaseEquivalenceList;
	}
	
	/**
	 * getIsNullList() : get the list of null / no-null values (useful if the user explicitely specify null values to add)
	 * @return {ArrayList<String>} the list of null as array (who match the array this.attributeList)
	 */
	public ArrayList<String> getIsNullList() {
		return isNullList;
	}

	/**
	 * isNullList() : set the list of null / no-null values
	 * @param {ArrayList<String>} the list of null / no-null
	 */
	public void setIsNullList(ArrayList<String> isNullList) {
		this.isNullList = isNullList;
	}
	
	/**
	 * getVarcharList : get the varchar list of the current tableData attribute list (if the attribute is varchar)
	 * @return {ArrayList<String>} the varcharList
	 */
	public ArrayList<String> getVarcharList() {
		return varcharList;
	}

	/**
	 * setVarcharList() : set the new array of varchar list
	 * @param {ArrayList<String>} varcharList
	 */
	public void setVarcharList(ArrayList<String> varcharList) {
		this.varcharList = varcharList;
	}
}
