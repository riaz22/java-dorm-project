package application;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Login_Etudiant {
	@FXML
	TextField username ;
	@FXML
	TextField password ;
	@FXML
	Label check ;
	@FXML 
	AnchorPane pane ;
	

	public void EntreCP(ActionEvent e)throws IOException,SQLException{
		   List<Admins>list=AdminsDB.getAdmins("coord_compte_etud");
		   Map <String,String> map= new HashMap<String,String>();
		   for (Admins a:list) {
			   map.put(a.getUsername(), a.getPassword());   
		   }
		   if(map.containsValue(username.getText())) {
			   String val2=map.get(username.getText());
			   if (val2.equals(password.getText())) {
				   Alert alert=new Alert(AlertType.INFORMATION);
					alert.setTitle("Login");
					alert.setHeaderText("Login");
					alert.setContentText("Login Successfully");
					alert.showAndWait(); 	   
				   AdminsDB.Getconnection();
				   Stage stage ;
				   stage=(Stage) pane.getScene().getWindow();
				   stage.close();
				   Stage primaryStage =new Stage() ;
				   Parent root =FXMLLoader.load(getClass().getResource("Last.fxml"));
				   Scene scene = new Scene(root);
				   scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				   primaryStage.setScene(scene);
				   primaryStage.setTitle("Listes des Administrateurs");
				   primaryStage.show();
			   }else {
				   Alert alert=new Alert(AlertType.INFORMATION);
					alert.setTitle("Login");
					alert.setHeaderText("Login");
					alert.setContentText("Check your username or password");
					alert.showAndWait();
				  	   
			   } 
		   }else {
			   Alert alert=new Alert(AlertType.INFORMATION);
				alert.setTitle("Login");
				alert.setHeaderText("Login");
				alert.setContentText("Check your username or password");
				alert.showAndWait();
		   }	   	   	   	   	  	   	   
	}
	public void logout1(ActionEvent e) {
		Stage stage ;
		stage=(Stage) pane.getScene().getWindow();
		stage.close();
	}
	

}
