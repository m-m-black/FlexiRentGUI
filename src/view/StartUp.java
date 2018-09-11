package view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class StartUp extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		BorderPane pane = new BorderPane();
		pane.setTop(new TopMenu());
		pane.setLeft(new SideMenu());
		pane.setCenter(new MainProgramWindow());
		
		Scene scene = new Scene(pane, 800, 600);
		
		primaryStage.setTitle("FlexiRent");
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		Application.launch(args);
	}

}
