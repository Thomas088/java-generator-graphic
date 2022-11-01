module mainmodule {
	
	requires javafx.controls;
	requires javafx.fxml;
	requires transitive javafx.graphics;
	requires java.sql;
	requires java.desktop;
	
	opens org.openjfx to javafx.fxml;
	exports org.openjfx;
}