package application;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class Last implements Initializable {
	@FXML
    private TableColumn<Etudiant, String> fname;

    @FXML
    private TableColumn<Etudiant, String> lname;

    @FXML
    private TableColumn<Etudiant, Integer> num_tel;
    @FXML
    private TableColumn<Chambre, String> num_chambre;
    @FXML
    private TableColumn<Chambre, String> bloc;
    @FXML
    private TableView<Etudiant> table;
    @FXML
    private TableView<Chambre> table1;
	public ObservableList<Etudiant> data=FXCollections.observableArrayList();
	public ObservableList<Chambre> data1=FXCollections.observableArrayList();
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		data.addAll(
				new Etudiant(94789654,"ALi","Mohammed"),
				new Etudiant(27489657,"Ben Rejeb","Taher"),
				new Etudiant(24156963,"Rebhy","Salma"),
				new Etudiant(21036745,"Hemdi","Fatma"),
				new Etudiant(97845895,"Cherif","Saleh")
				);
		try {
			String sql="SELECT * FROM `chambre` where num_chambre not in (select Chambre_num_chambre from `Etudiant`) ";
			Connection con=DBinfo.Getconnection();
			PreparedStatement ps=(PreparedStatement)con.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				data1.add( new Chambre(rs.getString(1), rs.getString(2)));
			}
			con.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		fname.setCellValueFactory(new PropertyValueFactory<Etudiant,String>("fname"));
		lname.setCellValueFactory(new PropertyValueFactory<Etudiant,String>("lname"));
		num_tel.setCellValueFactory(new PropertyValueFactory<Etudiant,Integer>("num_tel"));
		table.setItems(data);
		num_chambre.setCellValueFactory(new PropertyValueFactory<Chambre,String>("num_chambre"));
		bloc.setCellValueFactory(new PropertyValueFactory<Chambre,String>("bloc"));
		table1.setItems(data1);	
	}

}
