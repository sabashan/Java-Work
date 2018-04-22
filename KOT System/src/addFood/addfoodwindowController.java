package addFood;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;
import com.mysql.jdbc.PreparedStatement;

import Database.DBConnection;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.cell.PropertyValueFactory;
import login.Department;
import waiter.fooddetials;
import waiter.waiterwindowsController;

public class addfoodwindowController implements Initializable {

	@FXML
	private JFXTextField foodid;

	@FXML
	private JFXTextField foodName;

	@FXML
	private JFXTextField stock;

	@FXML
	private JFXTextField fprice;

	@FXML
	private JFXComboBox<fc> cfood;

	@FXML
	private JFXButton addFoodBtn;

	@FXML
	private JFXToggleButton tgbtn;

	@FXML
	private JFXButton addstockbtn;

	private ObservableList<fooddetials> data;

	FXMLLoader loader = new FXMLLoader();

	String id = "";
	int iid;

	waiterwindowsController wc = new waiterwindowsController();

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		this.cfood.setItems(FXCollections.observableArrayList(fc.values()));

		autoincrement();
		valueset();
		addstockbtn.setDisable(true);

	}

	public void clear() {

		this.foodid.setText("");
		this.foodName.setText("");
		this.stock.setText("");
		this.fprice.setText("");
		this.cfood.setValue(null);

	}

	@FXML
	public void valueset() {

		tgbtn.selectedProperty().addListener(new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {

				if (tgbtn.isSelected() == true) {

					foodName.setDisable(true);
					fprice.setDisable(true);
					cfood.setDisable(true);
					addFoodBtn.setDisable(true);
					addstockbtn.setDisable(false);
					

				} else {

					foodName.setDisable(false);
					fprice.setDisable(false);
					cfood.setDisable(false);
					addFoodBtn.setDisable(false);
					addstockbtn.setDisable(true);

				}

			}

		});

	}

	@FXML
	private void adding(ActionEvent event) {

		String sqlinsert = "INSERT INTO foodlist (fid, foodName, stock, price,catergories) VALUES (?, ?, ?, ?,?)";

		try {

			Connection conn = DBConnection.getConnection();

			PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sqlinsert);

			ps.setString(1, this.foodid.getText());
			ps.setString(2, this.foodName.getText());
			ps.setString(3, this.stock.getText());
			ps.setString(4, this.fprice.getText());
			ps.setString(5, ((fc) this.cfood.getValue()).toString());
			ps.executeUpdate();
			clear();
			conn.close();

		} catch (SQLException e) {

			e.printStackTrace();

		}

	}

	public void autoincrement() {

		try {

			PreparedStatement ps;
			ResultSet rs;

			Connection conn = DBConnection.getConnection();

			ps = (PreparedStatement) conn.prepareStatement("SELECT MAX(fid) AS id FROM foodlist");
			rs = ps.executeQuery();

			while (rs.next()) {

				id = rs.getString("id");

				this.foodid.setText(id);

			}

		} catch (SQLException er) {

			System.err.println("error " + er);

		}

	}

	@FXML
	public void stockupdate(ActionEvent event) {

		try {

			Connection connn = DBConnection.getConnection();

			String ustock = "Update foodlist set stock = stock + ?  where fid = ? ";
			PreparedStatement ps2 = (PreparedStatement) connn.prepareStatement(ustock);
			ps2.setInt(1, Integer.valueOf(stock.getText()));
			ps2.setString(2, foodid.getText());

			ps2.executeUpdate();
			connn.close();

		} catch (Exception e) {

			e.printStackTrace();

		}

	}

}
