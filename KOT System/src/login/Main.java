package login;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;

	

	public class Main extends Application {
		@Override
		public void start(Stage primaryStage) {
			try {
				
				
				Parent root = FXMLLoader.load(getClass().getResource("loginwindows.fxml"));
				
				Scene scene = new Scene(root);
				scene.getStylesheets().add(getClass().getResource("/login/login.css").toExternalForm());
				primaryStage.getIcons().add(new Image("file:loging.PNG"));
				primaryStage.setTitle("KOT SYSTEM");
				primaryStage.setScene(scene);
				primaryStage.setResizable(false);
				primaryStage.show();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		
		
		
		
		public static void main(String[] args) {
			launch(args);

		}
	
	
}
