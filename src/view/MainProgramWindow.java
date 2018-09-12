package view;

import javafx.scene.layout.StackPane;

public class MainProgramWindow extends StackPane {
	
	private static HomeView home;
	private static AddPropertyView addProperty;
	private static RentPropertyView rentProperty;
	private static ReturnPropertyView returnProperty;
	private static PropertyMaintenanceView maintainProperty;
	
	public MainProgramWindow() {
		this.setStyle("-fx-background-color: white");
		home = new HomeView();
		addProperty = new AddPropertyView();
		rentProperty = new RentPropertyView();
		returnProperty = new ReturnPropertyView();
		maintainProperty = new PropertyMaintenanceView();
		this.getChildren().addAll(home, addProperty, rentProperty, returnProperty, maintainProperty);
		home.toFront();
	}
	
	public static void setWindow(String view) {
		switch (view) {
			case "Home": 				home.toFront();
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

}
