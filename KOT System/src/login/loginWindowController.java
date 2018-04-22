package login;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import Kitchen.kitchenwindowsController;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import managment.managmentwindowsController;
import waiter.waiterwindowsController;

public class loginWindowController implements Initializable {

	LoginModal lm = new LoginModal();
	waiterwindowsController wc = new waiterwindowsController();

	@FXML
	private Label dbstatus;

	@FXML
	private Label loginstatus;

	@FXML
	private JFXTextField userId;

	private static String user;

	@FXML
	private JFXComboBox<Department> comBox;

	@FXML
	private JFXButton Blogin;

	@FXML
	private JFXPasswordField passWord;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		try {

			if (lm.isDataBaseConnected()) {

				this.dbstatus.setText("Connected");
			} else {

				this.dbstatus.setText("Not Connected");

			}

			this.comBox.setItems(FXCollections.observableArrayList(Department.values()));

		} catch (Exception e) {

			e.printStackTrace();

		}

	}

	@FXML
	public void login(ActionEvent event) {

		try {

			if (this.lm.islogined(this.userId.getText(), this.passWord.getText(),
					((Department) this.comBox.getValue()).toString())) {
				Stage stage = (Stage) this.Blogin.getScene().getWindow();
				stage.close();

				switch (((Department) this.comBox.getValue()).toString()) {
				case "kitchen":

					kitchen();
					break;

				case "waiter":

					waiter();

					break;

				case "management":

					management();

					break;

				}

			} else {

				this.loginstatus.setText("LogIn Fail");

				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Login warning");
				alert.setHeaderText("Login fail");
				alert.setContentText("please input correct User name and Password");
				alert.showAndWait();

			}

		} catch (Exception local) {

			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Login warning");
			alert.setHeaderText("Login fail");
			alert.setContentText("please input User name and Password");
			alert.showAndWait();

			local.printStackTrace();
		}

	}

	public static String getUser() {
		return user;
	}

	public static void setUser(String user) {
		loginWindowController.user = user;
	}

	public void waiter() {

		try {

			Stage wStage = new Stage();
			FXMLLoader loader = new FXMLLoader();
			Pane root = (Pane) loader.load(getClass().getResource("/waiter/waiterwindows.fxml").openStream());

			waiterwindowsController controller = (waiterwindowsController) loader.getController();

			Scene scene = new Scene(root);

			waiterwindowsController.setwaiterid(userId.getText());

			scene.getStylesheets().add(getClass().getResource("/waiter/waiter.css").toExternalForm());

			wStage.getIcons().add(new Image("file:ggg.PNG"));

			wStage.setScene(scene);
			wStage.setTitle("Waiter Dashboard");
			wStage.setResizable(true);
			wStage.show();

		} catch (IOException ex) {

			ex.printStackTrace();
		}

	}

	public void kitchen() {

		try {

			Stage kStage = new Stage();
			FXMLLoader kLoader = new FXMLLoader();
			Pane root = (Pane) kLoader.load(getClass().getResource("/kitchen/kitchenwindows.fxml").openStream());

			kitchenwindowsController controller = (kitchenwindowsController) kLoader.getController();

			Scene scene = new Scene(root);

			// scene.getStylesheets().add(getClass().getResource("/Kitchen/Kitchen.css").toExternalForm());
			kStage.setScene(scene);
			kStage.setTitle("Kitchen Dashboard");
			kStage.setResizable(true);
			kStage.show();

		} catch (IOException ex) {

			ex.printStackTrace();

		}

	}

	public void management() {

		try {

			Stage mStage = new Stage();
			FXMLLoader loader = new FXMLLoader();
			Pane root = (Pane) loader.load(getClass().getResource("/managment/managmentwindows.fxml").openStream());

			managmentwindowsController controller = (managmentwindowsController) loader.getController();

			Scene scene = new Scene(root);

			scene.getStylesheets().add(getClass().getResource("/managment/management.css").toExternalForm());

			mStage.setScene(scene);
			mStage.setTitle("Management Dashboard");
			mStage.setResizable(true);
			mStage.show();

		} catch (IOException ex) {

			ex.printStackTrace();
		}

	}

}
