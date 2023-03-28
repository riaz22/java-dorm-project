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

public class Viewroom implements Initializable {
	@FXML
	private TableView<Chambre> table ;
	@FXML
	private TableColumn<Chambre,String> num_chambre ;
	@FXML
	private TableColumn<Chambre,String> bloc ;
	public ObservableList<Chambre> data=FXCollections.observableArrayList();
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
			try {
				String sql="SELECT * FROM `chambre` where num_chambre not in (select Chambre_num_chambre from `Etudiant`) ";
				Connection con=DBinfo.Getconnection();
				PreparedStatement ps=(PreparedStatement)con.prepareStatement(sql);
				ResultSet rs=ps.executeQuery();
				while(rs.next()) {
					data.add( new Chambre(rs.getString(1), rs.getString(2)));
				}
				con.close();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			num_chambre.setCellValueFactory(new PropertyValueFactory<Chambre,String>("num_chambre"));
			bloc.setCellValueFactory(new PropertyValueFactory<Chambre,String>("bloc"));
			table.setItems(data);
	}

}
