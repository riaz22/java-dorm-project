module Test1 {
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.graphics;
	requires java.sql;
	requires javafx.base;
	requires mysql.connector.java;
	requires java.desktop;
	opens application to javafx.graphics, javafx.fxml,javafx.base ;
}
