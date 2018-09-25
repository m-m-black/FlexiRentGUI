package view;

import javafx.scene.control.ScrollPane;
import javafx.scene.layout.StackPane;

public class MainProgramWindow extends StackPane {
	
	private static ScrollPane homeScroller;
	private static HomeView home;
	private static AddPropertyView addProperty;
	private static RentPropertyView rentProperty;
	private static ReturnPropertyView returnProperty;
	private static PropertyMaintenanceView maintainProperty;
	
	public MainProgramWindow() {
		this.setStyle("-fx-background-color: white");
		home = new HomeView();
		homeScroller = new ScrollPane();
		homeScroller.setContent(home);
		addProperty = new AddPropertyView();
		rentProperty = new RentPropertyView();
		returnProperty = new ReturnPropertyView();
		maintainProperty = new PropertyMaintenanceView();
		this.getChildren().addAll(homeScroller, addProperty, rentProperty, returnProperty, maintainProperty);
		homeScroller.toFront();
	}
	
	public static void setWindow(String view) {
		switch (view) {
			case "Home": 				homeScroller.toFront();
										break;
			case "Add Property":			addProperty.toFront();
										break;
			case "Rent Property":		rentProperty.toFront();
										break;
			case "Return Property":		returnProperty.toFront();
										break;
			case "Property Maintenance":	maintainProperty.toFront();
										break;
		}
	}
	
	public static HomeView getHomeView() {
		return home;
	}

}
