package application;

import java.awt.Panel;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class ControllerPanel implements Initializable {
	@FXML
	AnchorPane pane ;
	@FXML
	TextField T9 ;
	@FXML
	Label T10 ;
	@FXML
	Label T11 ;
	@FXML
    private TextField R1;
	@FXML
	 private TextField R2;
	@FXML
	 private TextField R3;
   @FXML
	 private TextField R4;
	@FXML
	 private TextField R5;
	@FXML
	private TableView<Etudiant> table ;
	@FXML
	private TableColumn<Etudiant,Integer> id;
	@FXML
	private TableColumn<Etudiant,String> fname ;
	@FXML
	private TableColumn<Etudiant,String> lname ;
	@FXML
	private TableColumn<Etudiant,Integer> num_tel ;
	@FXML
	private TableColumn<Etudiant,String> num_chambre ;
	public void Viewroom(ActionEvent e)throws IOException,SQLException{
		   Stage primaryStage =new Stage() ;
		   Parent root =FXMLLoader.load(getClass().getResource("Viewroom.fxml"));
		   Scene scene = new Scene(root);
		   scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		   primaryStage.setScene(scene);
		   primaryStage.setTitle("Liste Des Chambres Vides");
		   primaryStage.show();
		
	}
	

	public void Return(ActionEvent e) throws IOException {
		
		Parent root =FXMLLoader.load(getClass().getResource("Login_Admin.fxml"));
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		Stage primaryStage = new Stage();
		primaryStage.setScene(scene);
		primaryStage.setTitle("Login");
		primaryStage.show();
		Stage stage ;
		stage=(Stage) pane.getScene().getWindow();
		stage.close();
		
	}
	public ObservableList<Etudiant> data=FXCollections.observableArrayList();
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		try {
			String sql="SELECT * FROM `Etudiant`  ";
			Connection con=DBinfo.Getconnection();
			PreparedStatement ps=(PreparedStatement)con.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				data.add(new Etudiant(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getString(5)));
			}
			con.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		id.setCellValueFactory(new PropertyValueFactory<Etudiant,Integer>("id"));
		fname.setCellValueFactory(new PropertyValueFactory<Etudiant,String>("fname"));
		lname.setCellValueFactory(new PropertyValueFactory<Etudiant,String>("lname"));
		num_tel.setCellValueFactory(new PropertyValueFactory<Etudiant,Integer>("num_tel"));
		num_chambre.setCellValueFactory(new PropertyValueFactory<Etudiant,String>("num_chambre"));
		table.setItems(data);
			T10.setText(data.size()+"");
			T11.setText(DBinfo.chambreV()-data.size()+"");	
			
	}
	public void searchD() {
		T9.textProperty().addListener(new InvalidationListener() {	
			@Override
			public void invalidated(Observable arg0) {
				// TODO Auto-generated method stub
				if(T9.textProperty().get().isEmpty()) {
					table.setItems(data);
					return;
				}
				ObservableList<Etudiant> items=FXCollections.observableArrayList();
				for(int i=0;i<data.size();i++) {
					if (T9.getText().equals(data.get(i).getNum_chambre())|| 
							T9.getText().equals(String.valueOf(data.get(i).getId()) )) {
						items.add(data.get(i));
						break;
					}
				}			
				table.setItems(items);		
			}
		});
		
	}
	 public void getselected(ActionEvent e) {
		int i=table.getSelectionModel().getSelectedIndex();
		Etudiant etd=table.getItems().get(i);		
		R1.setText(String.valueOf(etd.getId()));
		R2.setText(etd.getFname());
		R3.setText(etd.getLname());
		R4.setText(String.valueOf(etd.getNum_tel()));
		R5.setText(etd.getNum_chambre());
	}
	 public void Insert(ActionEvent e)throws IOException,SQLException{
			Etudiant emp =new Etudiant();
			emp.setId(Integer.parseInt(R1.getText()));
			emp.setFname(R2.getText());
			emp.setLname(R3.getText());
			emp.setNum_tel(Integer.parseInt(R4.getText()));
			emp.setNum_chambre(R5.getText());
			int st=DBinfo.save(emp);
			if (st>0) {
				Alert alert=new Alert(AlertType.INFORMATION);
				alert.setTitle("Data Insert");
				alert.setHeaderText("Information Dialog");
				alert.setContentText("Record saved successfully");
				alert.showAndWait();
				T10.setText(data.size()+1+"");
				T11.setText(DBinfo.chambreV()-data.size()-1+"");
				return;
			}else {
				Alert alert=new Alert(AlertType.INFORMATION);
				alert.setTitle("Data Insert");
				alert.setHeaderText("Information Dialog");
				alert.setContentText("Ncin ou num chambre  existe déjà");
				alert.showAndWait();
				
			}
			
		}	 
	 public void UpdateE(ActionEvent e)throws IOException,SQLException{
			Etudiant emp =new Etudiant(Integer.parseInt(R1.getText()),R2.getText(),R3.getText(),Integer.parseInt(R4.getText()),R5.getText());	
			int st=DBinfo.update(emp,Integer.parseInt(R1.getText()));
			
			if (st>0) {
				Alert alert=new Alert(AlertType.INFORMATION);
				alert.setTitle("Data Update");
				alert.setHeaderText("Information Dialog");
				alert.setContentText("Update saved successfully");
				alert.showAndWait();
				
				
			}else {
				Alert alert=new Alert(AlertType.INFORMATION);
				alert.setTitle("Data Update");
				alert.setHeaderText("Information Dialog");
				alert.setContentText("Chambre occupée");
				alert.showAndWait();		
			}
		}
	 public void DeleteE(ActionEvent e)throws IOException,SQLException{	
			int st=DBinfo.delete(Integer.parseInt(R1.getText()));
			if (st>0) {
				Alert alert=new Alert(AlertType.INFORMATION);
				alert.setTitle("Data Delete");
				alert.setHeaderText("Information Dialog");
				alert.setContentText("Deleted");
				alert.showAndWait();
				T10.setText(data.size()-1+"");
				T11.setText(DBinfo.chambreV()-data.size()+1+"");
			}else {
				Alert alert=new Alert(AlertType.INFORMATION);
				alert.setTitle("Data Delete");
				alert.setHeaderText("Information Dialog");
				alert.setContentText("Check the Identity Number");
				alert.showAndWait();	
			}
		}
	
	

}
