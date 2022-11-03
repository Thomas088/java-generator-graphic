package org.openjfx;

public class FXMLDataHelper {

	private String attributeName;
	private String typeName;
	private String databaseLinkType;
	
	
	public FXMLDataHelper() {
		
		this.attributeName = "";
		this.typeName = "";
		this.databaseLinkType = "";
		
//		this.attributeName.setLength(0);
//		this.typeName.setLength(0);
//		this.databaseLinkType.setLength(0);
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


	public String getDatabaseLinkType() {
		return databaseLinkType;
	}


	public void setDatabaseLinkType(String databaseLinkType) {
		this.databaseLinkType = databaseLinkType;
	}

	

}
