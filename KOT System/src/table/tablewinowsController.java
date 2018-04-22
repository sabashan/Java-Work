package table;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTimePicker;
import com.mysql.jdbc.PreparedStatement;

import Database.DBConnection;
import addFood.fc;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import login.Department;
import waiter.waiterwindowsController;

public class tablewinowsController implements Initializable {

	private DBConnection db;

	@FXML
	private JFXComboBox<wtable> tablename;

	@FXML
	private JFXTimePicker rtime;

	@FXML
	private Label lbldishcount;

	@FXML
	private Label lbldrinkscount;

	@FXML
	private JFXButton orderNow;

	private int countdish;

	private int countdrinks;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		this.db = new DBConnection();
		waiterwindowsController wc = new waiterwindowsController();

		this.tablename.setItems(FXCollections.observableArrayList(wtable.values()));
		getcount();

	}

	//////////////////////////////////////////////////////////////////////////////////////////////////////////

	public int getCountdish() {
		return countdish;
	}

	public void setCountdish(int countdish) {
		this.countdish = countdish;
	}

	public int getCountdrinks() {
		return countdrinks;
	}

	public void setCountdrinks(int countdrinks) {
		this.countdrinks = countdrinks;
	}

	/////////////////////////////////////////////////////////////////////////////////////////////////////////

	public void getcount() {

		try {

			ResultSet rs3;

			Connection connn = DBConnection.getConnection();

			String uqty = "select (select count(catergories) from kot_system.order where catergories= ? ) "
					+ "as countdish,(select count(catergories) from kot_system.order where catergories= ?) "
					+ "as countdrinks;";

			PreparedStatement ps5;

			ps5 = (PreparedStatement) connn.prepareStatement(uqty);

			ps5.setString(1, "dish");
			ps5.setString(2, "drinks");

			rs3 = ps5.executeQuery();

			while (rs3.next()) {

				setCountdish(rs3.getInt("countdish"));
				setCountdrinks(rs3.getInt("countdrinks"));

				ps5.executeQuery();

			}

		} catch (SQLException e) {

			e.printStackTrace();
		}

		lbldishcount.setText(String.valueOf(getCountdish()));
		lbldrinkscount.setText(String.valueOf(getCountdrinks()));

	}

	public String getdate() {

		String date = new SimpleDateFormat("YYYY/MM/dd").format(Calendar.getInstance().getTime());

		return date;

	}

	public String gettime() {

		String time = new SimpleDateFormat("hh:mm a").format(Calendar.getInstance().getTime());

		return time;

	}

	@FXML
	public void invoiceInsert(ActionEvent event) {

		waiterwindowsController wc = new waiterwindowsController();

		String sqlinsert = "INSERT INTO kot_system.`invoice` (invoiceid,waiterid,tableid ,date, time, dish,drinks, total) VALUES (?, ?, ?, ?,?,?,?,?)";

		try {

			Connection conn = DBConnection.getConnection();

			PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sqlinsert);

			ps.setInt(1, waiterwindowsController.getInvoiceid());
			ps.setString(2, waiterwindowsController.getwaiterid());
			ps.setString(3, ((wtable) this.tablename.getValue()).toString());
			ps.setString(4, getdate());
			ps.setString(5, gettime());
			ps.setInt(6, getCountdish());
			ps.setInt(7, getCountdrinks());
			ps.setDouble(8, waiterwindowsController.getGtotal());
			ps.executeUpdate();

			conn.close();

			Stage stage = (Stage) this.orderNow.getScene().getWindow();
			stage.close();
			
			
			String sqldelete = "DELETE  FROM kot_system.`order` ;";

			Connection conn8;

			conn8 = DBConnection.getConnection();

			PreparedStatement stmt = (PreparedStatement) conn8.prepareStatement(sqldelete);
			

			// ResultSet rs = conn.createStatement().executeQuery(sqldelete);

			stmt.execute();

		
			wc.auotinvoice();
			wc.initialize(null, null);

			waiterwindowsController.setGtotal(00.0);
			
			
			conn8.close();

		} catch (SQLException e) {

			e.printStackTrace();

		}

	}

	
}
