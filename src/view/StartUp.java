package view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.PopulatePropertyList;

public class StartUp extends Application {
	
	private static BorderPane pane;
	private static boolean isHome;
	private int width = 800;
	private int height = 600;
	private static MainProgramWindow main;

	@Override
	public void start(Stage primaryStage) throws Exception {
		main = new MainProgramWindow();
		setHome(true);
		pane = new BorderPane();
		pane.setTop(new TopMenu(primaryStage));
		pane.setLeft(new SideMenu(width * 0.1));
		pane.setCenter(main);
		
		PopulatePropertyList list = new PopulatePropertyList();
		list.populate();
		
		Scene scene = new Scene(pane, width, height);
		
		primaryStage.setTitle("FlexiRent");
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.show();
	}
	
	// When "Details" button of a property is clicked, the main view will change from Home to PropertyDetailWindow
	public static void switchView(boolean isHome, PropertyDetailWindow window) {
		setHome(isHome);
		if (isHome) {
			pane.setCenter(main);
		} else {
			pane.setCenter(window);
		}
	}
	
	public boolean isHome() {
		return isHome;
	}

	public static void setHome(boolean home) {
		isHome = home;
	}
	
	public static void run() {
		Application.launch();
	}

}
