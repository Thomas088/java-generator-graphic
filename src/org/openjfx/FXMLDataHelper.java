package org.openjfx;
import classes.from.console.project.EnumList;
import javafx.scene.control.ComboBox;

public class FXMLDataHelper {

	private String attributeName;
	private String typeName;
	private ComboBox<EnumList.MariaAttributeTypesListEnum> databaseLinkType;

	public FXMLDataHelper() {
			
		databaseLinkType = new ComboBox<EnumList.MariaAttributeTypesListEnum>();
		for (EnumList.MariaAttributeTypesListEnum type : EnumList.MariaAttributeTypesListEnum.values()) {
			databaseLinkType.getItems().add(type);
		}

	}
	
	public String getAttributeName() {
		return attributeName;
	}


	public void setAttributeName(String attributeName) {
		this.attributeName = attributeName;
	}


	public String getTypeName() {
		return typeName;
	}


	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}


	public ComboBox<EnumList.MariaAttributeTypesListEnum> getDatabaseLinkType() {
		return databaseLinkType;
	}


	public void setDatabaseLinkType(ComboBox<EnumList.MariaAttributeTypesListEnum> databaseLinkType) {
		this.databaseLinkType = databaseLinkType;
	}

	

}
