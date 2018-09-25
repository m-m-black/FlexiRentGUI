package view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.PopulatePropertyList;

public class StartUp extends Application {
	
	private int width = 800;
	private int height = 600;

	@Override
	public void start(Stage primaryStage) throws Exception {
		BorderPane pane = new BorderPane();
		pane.setTop(new TopMenu());
		pane.setLeft(new SideMenu(width * 0.1));
		pane.setCenter(new MainProgramWindow());
		
		PopulatePropertyList list = new PopulatePropertyList();
		list.populate();
		
		Scene scene = new Scene(pane, width, height);
		
		primaryStage.setTitle("FlexiRent");
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		Application.launch(args);
	}

}
