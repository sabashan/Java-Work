
package managment;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import Database.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import waiter.fooddetials;
import waiter.order;

public class managmentwindowsController implements Initializable {

    @FXML
    private TableView<invoice> tinvoice;

    @FXML
    private TableColumn<invoice, String> ivnum;
    
    
    @FXML
    private TableColumn<invoice, String> iwaiterid;

    @FXML
    private TableColumn<invoice, String> itableid;

    @FXML
    private TableColumn<invoice, String> idate;

    @FXML
    private TableColumn<invoice, String> itime;

    @FXML
    private TableColumn<invoice, Integer> idishc;

    @FXML
    private TableColumn<invoice, Integer> idrinkc;

    @FXML
    private TableColumn<invoice, Double> igtotal;
    
    
    
    @FXML
    private JFXButton logoutbtn;
    
    
    
    private DBConnection db;
	private ObservableList<invoice> data;
	

	
	private String getting = "SELECT * FROM kot_system.`invoice`";

	
    
    @Override
	public void initialize(URL location, ResourceBundle resources) {

    	loadinvoice(null);
		
	}
    
    
    
    
    
    @FXML
    public void loadinvoice(ActionEvent event) {
    	
    	try {

			Connection conn = DBConnection.getConnection();
			this.data = FXCollections.observableArrayList();

			ResultSet rs = conn.createStatement().executeQuery(getting);

			while (rs.next()) {

				this.data.add(new invoice(rs.getInt(1),rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6),
						rs.getInt(7),rs.getDouble(8)));

			}

		} catch (SQLException er) {

			System.err.println("error " + er);

		}

		this.ivnum.setCellValueFactory(new PropertyValueFactory<>("invoiceid"));
		this.iwaiterid.setCellValueFactory(new PropertyValueFactory<>("waiterid"));
		this.itableid.setCellValueFactory(new PropertyValueFactory<>("tableid"));
		this.idate.setCellValueFactory(new PropertyValueFactory<>("date"));
		this.itime.setCellValueFactory(new PropertyValueFactory<>("time"));
		this.idishc.setCellValueFactory(new PropertyValueFactory<>("dish"));
		this.idrinkc.setCellValueFactory(new PropertyValueFactory<>("drinks"));
		this.igtotal.setCellValueFactory(new PropertyValueFactory<>("price"));

		// this.imgcoloumn.setCellValueFactory(new
		// PropertyValueFactory<>("F:/eclipse/bcas new/KOT System/kl.png"));

		this.tinvoice.setItems(null);
		this.tinvoice.setItems(this.data);
    	
    	
    	
    	
    	
    }
    
    
    @FXML
	public void logout() {

		try {

			Stage loginStage = new Stage();

			Parent root2 = FXMLLoader.load(getClass().getResource("/login/loginwindows.fxml"));

			Scene scene2 = new Scene(root2);
			scene2.getStylesheets().add(getClass().getResource("/login/login.css").toExternalForm());
			loginStage.getIcons().add(new Image("file:loging.PNG"));
			loginStage.setTitle("KOT SYSTEM");
			loginStage.setScene(scene2);
			loginStage.setResizable(false);
			loginStage.show();
			
			Stage stage = (Stage) this.logoutbtn.getScene().getWindow();
			stage.close();

		} catch (IOException ex) {

			ex.printStackTrace();
		}

	}

    
    
    
    

}