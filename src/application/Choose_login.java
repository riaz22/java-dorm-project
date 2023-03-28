package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Choose_login {
	@FXML
	AnchorPane T1 ;
	public void Login_Admin(ActionEvent e) throws IOException {
		Stage primaryStage= new Stage();
		Parent root =FXMLLoader.load(getClass().getResource("Login_Admin.fxml"));
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.setTitle("Login");
		primaryStage.show();
		Stage stage ;
		stage=(Stage) T1.getScene().getWindow();
		stage.close();
		
	}
	public void Login_Etudiant(ActionEvent e) throws IOException {
		Stage primaryStage= new Stage();
		Parent root =FXMLLoader.load(getClass().getResource("Login_Etudiant.fxml"));
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.setTitle("Login");
		primaryStage.show();
		Stage stage ;
		stage=(Stage) T1.getScene().getWindow();
		stage.close();
		
	}


}
