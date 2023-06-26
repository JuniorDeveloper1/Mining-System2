package javaproject.game;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class Game extends Application{

	public void start(Stage primaryStage) throws IOException {
		URL main_menuFXML = getClass().getClassLoader().getResource("main_menu.fxml");
		Parent root = FXMLLoader.load(main_menuFXML);
		primaryStage.setScene(new Scene(root));
		primaryStage.setTitle("Main Menu");
		primaryStage.show();
	}


	public static void main(String[] args) {
		launch(args);
	}

}
