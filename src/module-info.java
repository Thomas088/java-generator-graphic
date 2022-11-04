module mainmodule {
	
	requires javafx.controls;
	requires javafx.fxml;
	requires transitive javafx.graphics;
	requires java.sql;
	requires java.desktop;
	requires javafx.base;
	
	opens org.openjfx to javafx.fxml;
	exports org.openjfx;
}