package la_revenche_des_loups;

import java.io.File;
import java.net.URL;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;


@SuppressWarnings("unused")
public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
		BorderPane root = FXMLLoader.load(getClass().getResource("vue/interface.fxml"));
			Scene scene = new Scene(root,1280,720);
			primaryStage.setScene(scene);
			primaryStage.setTitle("La revenge des Loups");
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
