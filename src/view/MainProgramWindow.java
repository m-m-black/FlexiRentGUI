package view;

import javafx.scene.control.ScrollPane;
import javafx.scene.layout.StackPane;

public class MainProgramWindow extends StackPane {
	
	private static ScrollPane homeScroller;
	private static HomeView home;
	private static AddPropertyView addProperty;
	
	public MainProgramWindow() {
		this.setStyle("-fx-background-color: white");
		home = new HomeView();
		homeScroller = new ScrollPane();
		homeScroller.setContent(home);
		addProperty = new AddPropertyView();
		this.getChildren().addAll(homeScroller, addProperty);
		homeScroller.toFront();
	}
	
	public static void setWindow(String view) {
		switch (view) {
			case "Home": 				homeScroller.toFront();
										break;
			case "Add Property":			addProperty.toFront();
										break;
		}
	}
	
	public static HomeView getHomeView() {
		return home;
	}

}
