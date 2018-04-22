package waiter;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.mysql.jdbc.PreparedStatement;

import Database.DBConnection;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MultipleSelectionModelBuilder;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeView.EditEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import login.loginWindowController;
import table.tablewinowsController;

public class waiterwindowsController implements Initializable {

	@FXML
	private JFXTextField disid;

	@FXML
	public JFXTextField invoice;

	@FXML
	private JFXButton dishes;
	
	@FXML
	private JFXButton logout;

	@FXML
	private JFXButton drings;

	@FXML
	private JFXButton addfood;

	@FXML
	private Button reset;

	@FXML
	private static Label lblwaiter;

	@FXML
	public static String wid;

	@FXML
	private TextArea billArea;

	@FXML
	private Label total;

	@FXML
	private JFXButton confirm;

	@FXML
	private JFXButton printrecipt;

	@FXML
	private TableView<fooddetials> ftable;

	@FXML
	private TableColumn<fooddetials, String> fidcoloumn;

	@FXML
	private TableColumn<fooddetials, String> foodNamecoloumn;

	@FXML
	private TableColumn<fooddetials, Integer> stockcoloumn;

	@FXML
	private TableColumn<fooddetials, Double> pricecoloumn;

	@FXML
	private TableColumn<fooddetials, TextField> imgcoloumn;

	@FXML
	private TableColumn<fooddetials, String> catergories;

	@FXML
	private TableView<order> billTable;

	@FXML
	private TableColumn<order, Integer> bidcoloumn;

	@FXML
	private TableColumn<order, String> bfid;

	@FXML
	private TableColumn<order, String> bNamecoloumn;

	@FXML
	private TableColumn<order, Integer> bqtycoloumn;

	@FXML
	private TableColumn<order, Double> bPricecoloumn;

	@FXML
	private TableColumn<order, String> bdeletecoloumn;

	String cat = "";

	private static int invoiceid;

	private static double gtotal;

	private final Random r = new Random();

	private DBConnection db;
	private ObservableList<fooddetials> data;
	private ObservableList<order> Orderdata;

	private String sql = "Select * from foodlist";
	private String getting = "SELECT * FROM kot_system.`order`";

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		LoadData();
		btableset();
		Loadorder();
		total();
		auotinvoice();
		
		
		
		
		

		// reset(null);

		// System.out.println(getwaiterid());

	}

	
	
	
	
	
	public static double getGtotal() {
		return gtotal;
	}

	public static void setGtotal(double gtotall) {
		gtotal = gtotall;
	}

	public static void setwaiter(String www) {

	}

	public static void setwaiterid(String waiteridd) {

		wid = waiteridd;

	}

	public static String getwaiterid() {

		return wid;

	}

	@FXML
	public void dish(ActionEvent event) {

		try {

			String select = "Select * from foodlist where catergories = 'dish ' ";

			Connection conn = DBConnection.getConnection();
			this.data = FXCollections.observableArrayList();

			ResultSet rs = conn.createStatement().executeQuery(select);

			while (rs.next()) {

				this.data.add(new fooddetials(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getDouble(4),
						rs.getString(5)));

			}

		} catch (SQLException er) {

			System.err.println("error " + er);

		}

		this.fidcoloumn.setCellValueFactory(new PropertyValueFactory<>("fid"));
		this.foodNamecoloumn.setCellValueFactory(new PropertyValueFactory<>("foodName"));
		this.stockcoloumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
		this.pricecoloumn.setCellValueFactory(new PropertyValueFactory<>("price"));
		this.catergories.setCellValueFactory(new PropertyValueFactory<>("catergories"));
		// this.imgcoloumn.setCellValueFactory(new
		// PropertyValueFactory<>("F:/eclipse/bcas new/KOT System/kl.png"));

		this.ftable.setItems(null);
		this.ftable.setItems(this.data);

	}

	@FXML
	public void confimB() {

		try {

			Stage userStage = new Stage();

			Pane root = (Pane) FXMLLoader.load(getClass().getResource("/table/tablewinows.fxml"));

			Scene scene = new Scene(root);
			userStage.setScene(scene);
			userStage.setTitle("Table Dashboard");
			userStage.setResizable(true);
			userStage.show();

		} catch (IOException ex) {

			ex.printStackTrace();
		}

	}

	@FXML
	public void reset(ActionEvent event) {

		int rowcount = billTable.getItems().size();

		for (int c = 0; c < rowcount; c++) {

			billTable.getSelectionModel().select(c);

			try {

				this.Orderdata = FXCollections.observableArrayList();

				ResultSet rsl;

				String stockreset = "Select * from kot_system.`order` where fName = ? ";

				Connection conn = DBConnection.getConnection();

				order or = billTable.getSelectionModel().getSelectedItem();

				PreparedStatement prt;

				prt = (PreparedStatement) conn.prepareStatement(stockreset);

				prt.setString(1, or.getfName());

				rsl = prt.executeQuery();

				while (rsl.next()) {

					this.Orderdata.add(new order(rsl.getInt(1), rsl.getString(2), rsl.getString(3), rsl.getDouble(4),
							rsl.getInt(5), rsl.getString(6)));

				}

				String ustockn = "Update foodlist set stock = stock + ? where foodName = ? ";
				PreparedStatement ps2 = (PreparedStatement) conn.prepareStatement(ustockn);

				ps2.setInt(1, 1);
				ps2.setString(2, or.getfName());

				ps2.executeUpdate();

				/*
				
				
				
				*/

			} catch (Exception e) {

			}

		}

		try {

			String sqldelete = "DELETE  FROM kot_system.`order` ;";

			Connection conn8 = DBConnection.getConnection();
			this.data = FXCollections.observableArrayList();

			PreparedStatement stmt = (PreparedStatement) conn8.prepareStatement(sqldelete);

			// ResultSet rs = conn.createStatement().executeQuery(sqldelete);

			stmt.execute();

			total.setText("0.00");

			conn8.close();

		} catch (Exception e) {
			// TODO: handle exception
		}

		LoadData();
		Loadorder();

	}

	public void onedelete() {

	}

	@FXML
	private void LoadData() {

		System.out.println(getwaiterid());

		try {

			Connection conn = DBConnection.getConnection();
			this.data = FXCollections.observableArrayList();

			ResultSet rs = conn.createStatement().executeQuery(sql);

			while (rs.next()) {

				this.data.add(new fooddetials(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getDouble(4),
						rs.getString(5)));

			}

			conn.close();
		} catch (SQLException er) {

			System.err.println("error " + er);

		}

		this.fidcoloumn.setCellValueFactory(new PropertyValueFactory<>("fid"));
		this.foodNamecoloumn.setCellValueFactory(new PropertyValueFactory<>("foodName"));
		this.stockcoloumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
		this.pricecoloumn.setCellValueFactory(new PropertyValueFactory<>("price"));
		this.catergories.setCellValueFactory(new PropertyValueFactory<>("catergories"));

		// this.imgcoloumn.setCellValueFactory(new
		// PropertyValueFactory<>("F:/eclipse/bcas new/KOT System/kl.png"));

		this.ftable.setItems(null);
		this.ftable.setItems(this.data);

	}
	
	
	

	@FXML
	private void Loadorder() {

		/*
		 * veera vali illaddi order table ikku specific ar primary ke set panni auto
		 * increment pooddu atha select pannina delete aakura maari seijjanum
		 * 
		 */

		try {

			Connection connn = DBConnection.getConnection();
			this.Orderdata = FXCollections.observableArrayList();

			try {

				ResultSet rss = connn.createStatement().executeQuery(getting);

				while (rss.next()) {

					this.Orderdata.addAll(new order(rss.getInt(1), rss.getString(2), rss.getString(3), rss.getDouble(4),
							rss.getInt(5), rss.getString(6)));

				}
				
				connn.close();

			} catch (Exception ee) {

				System.err.println("error to  " + ee);

			}

		} catch (SQLException er) {

			System.err.println("error this line  " + er);

		}

		this.bidcoloumn.setCellValueFactory(new PropertyValueFactory<>("orderid"));
		this.bfid.setCellValueFactory(new PropertyValueFactory<>("fid"));

		this.bNamecoloumn.setCellValueFactory(new PropertyValueFactory<>("fName"));

		this.bPricecoloumn.setCellValueFactory(new PropertyValueFactory<>("price"));

		this.bqtycoloumn.setCellValueFactory(new PropertyValueFactory<>("qty"));
		
		
		
		// this.imgcoloumn.setCellValueFactory(new
		// PropertyValueFactory<>("F:/eclipse/bcas new/KOT System/kl.png"));
		total();
		this.billTable.setItems(null);
		this.billTable.setItems(this.Orderdata);
		

	}

	public void total() {

		double dtotal = 0;

		for (order bt : billTable.getItems()) {

			dtotal = dtotal + bt.getPrice();

			setGtotal(dtotal);

			// double dtotal= btotal-(btotal)*(Double.valueOf(disid.getText())/100);

		}

		total.setText(String.valueOf(getGtotal()));

	}

	public void addfood(ActionEvent event) {

		try {

			Stage foodStage = new Stage();

			Pane root = (Pane) FXMLLoader.load(getClass().getResource("/addFood/addfoodwindow.fxml"));

			Scene scene = new Scene(root);
			foodStage.setScene(scene);
			foodStage.setTitle("add food Dashboard");
			foodStage.setResizable(true);
			foodStage.show();

		} catch (IOException ex) {

			ex.printStackTrace();
		}

	}

	public void btableset() {

		ftable.setOnMouseClicked(e -> {

			this.data = FXCollections.observableArrayList();

			ResultSet rs;
			ResultSet rs2;

			fooddetials user = ftable.getSelectionModel().getSelectedItem();

			Object row = new Object();
			PreparedStatement prt = null;

			try {
				Connection conn = DBConnection.getConnection();

				String query = "Select * from foodlist where fid = ?";

				PreparedStatement prt2;
				prt = (PreparedStatement) conn.prepareStatement(query);

				prt.setString(1, user.getfId());

				rs = prt.executeQuery();

				while (rs.next()) {

					this.data.add(new fooddetials(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getDouble(4),
							rs.getString(5)));

					PreparedStatement ps = null;

					try {

						String bsqlinsert = "INSERT INTO kot_system.`order` (orderid,fid, fName, price,catergories) VALUES (?, ?, ?,?,?)";

						Connection connn;
						connn = DBConnection.getConnection();

						ps = (PreparedStatement) connn.prepareStatement(bsqlinsert);

						/*
						 * 
						 * int use = Integer.valueOf(user.getfId());
						 * 
						 * bqtycoloumn.setEditable(true);
						 * 
						 * //this is not correct code of this but i just used to this for finishing
						 * project
						 * 
						 * double ppp = Double.valueOf(pricecoloumn.getCellData(use-1)); String iii =
						 * String.valueOf(fidcoloumn.getCellData(use-1)); String nnn =
						 * String.valueOf(foodNamecoloumn.getCellData(use-1));
						 * 
						 */

						// i got this yehaaaa

						PreparedStatement ps3 = null;

						try {

							int id;
							ResultSet rs10;

							Connection conn3 = DBConnection.getConnection();

							ps3 = (PreparedStatement) conn3
									.prepareStatement("SELECT MAX(orderid) AS id FROM kot_system.`order`");

							rs10 = ps3.executeQuery();

							while (rs10.next()) {

								id = rs10.getInt("id");

								ps3.executeQuery();

								ps.setInt(1, id + 1);

								System.out.println("i am in max");

								conn3.close();

							}

						} catch (SQLException er) {

							System.err.println("error  max id error" + er);
							er.printStackTrace();

						}

						finally {
							if (ps3 != null) {

								ps3.close();

							}
						}

						PreparedStatement prt4 = null;

						try {

							ResultSet rs4;
							Connection connnew = DBConnection.getConnection();

							String check = "Select * from kot_system.`order` where fid = ? ";

							prt4 = (PreparedStatement) connnew.prepareStatement(check);

							prt4.setString(1, user.getfId());

							rs4 = prt4.executeQuery();

							if (rs4.next()) {

								String uqty = "Update kot_system.`order` set qty = qty + ? where not exists (Select * from kot_system.`order` where fid = ? ) ";
								PreparedStatement ps5 = (PreparedStatement) connnew.prepareStatement(uqty);

								ps5.setInt(1, 1);
								ps5.setString(2, user.getfId());

								ps5.executeUpdate();

								System.out.println("i am in qty set");

								connnew.close();

							} else {

							}

						} catch (SQLException er) {

							System.err.println("error  DUB" + er);

						}

						finally {
							if (prt4 != null) {

								prt4.close();

							}
						}

						ps.setString(2, user.getfId());
						ps.setString(3, user.getfoodName());
						ps.setDouble(4, user.getPrice());
						ps.setString(5, user.getcatergories());

						// ps.setString(5, user.getfId());

						/*
						 * 
						 * ps.setString(1, iii); ps.setString(2, nnn); ps.setDouble(3,ppp );
						 * 
						 */

						ps.executeUpdate();

						Connection connset = DBConnection.getConnection();

						String ustock = "Update foodlist set stock = ? where fid = ? ";
						PreparedStatement ps2 = (PreparedStatement) connset.prepareStatement(ustock);
						ps2.setInt(1, user.getstock() - 1);
						ps2.setString(2, user.getfId());

						ps2.executeUpdate();
						Loadorder();
						LoadData();
						total();

						connset.close();

					} catch (SQLException e1) {

						System.err.println("error  inseret order " + e1);

						e1.printStackTrace();
					}

					finally {
						if (ps != null) {

							ps.close();

						}
					}

				}

			} catch (Exception e2) {

				System.err.println("error  all of them " + e2);
				e2.printStackTrace();
			}

			finally {
				if (prt != null) {

					try {
						prt.close();
					} catch (SQLException e1) {

						e1.printStackTrace();
					}

				}
			}

		});

	}

	public static int getInvoiceid() {
		return invoiceid;
	}

	public static void setInvoiceid(int invoiceide) {
		invoiceid = invoiceide;
	}

	public void auotinvoice() {

		try {

			PreparedStatement ps;
			ResultSet rs;

			Connection conn = DBConnection.getConnection();

			ps = (PreparedStatement) conn.prepareStatement("SELECT MAX(invoiceid) AS id FROM invoice");
			rs = ps.executeQuery();

			while (rs.next()) {

				setInvoiceid(rs.getInt("id"));

				if (getInvoiceid() == 0) {

					setInvoiceid(100000001);

				} else {

					setInvoiceid(getInvoiceid() + 1);
					this.invoice.setText(String.valueOf(getInvoiceid()));

				}

			}

		} catch (SQLException er) {

			System.err.println("error  invoice" + er);

		}

		System.out.println(getInvoiceid());

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
			
			Stage stage = (Stage) this.logout.getScene().getWindow();
			stage.close();

		} catch (IOException ex) {

			ex.printStackTrace();
		}

	}

}
