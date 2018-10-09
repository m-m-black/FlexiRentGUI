package view;

import java.io.File;
import java.util.HashMap;

import controller.PropertyDetailController;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class PropertyDetailWindow extends GridPane {
	
	private HashMap<String, String> property;
	private String propertyID;
	private String streetNumber;
	private String streetName;
	private String suburb;
	private String numBedrooms;
	private String type;
	private static String status;
	private String lastMaintenanceDate;
	private String description;
	private String image;
	private ImageView imageView;
	private GridPane detailsView;
	private ScrollPane recordsScroller;
	private VBox recordsView;
	private StackPane buttonsStack;
	private static GridPane buttonsView;
	private static RentView rentView;
	private static ReturnView returnView;
	
	public PropertyDetailWindow(HashMap<String, String> property) {
		this.property = property;
		setHgap(10);
		setVgap(10);
		extractInfo();
		makeImage(image);
		makeDetails();
		makeRecords();
		makeButtons();
		add(imageView, 0, 0);
		add(detailsView, 1, 0);
		add(recordsScroller, 0, 1);
		add(buttonsStack, 1, 1);
	}
	
	private void extractInfo() {
		this.propertyID = property.get("propertyID");
		this.streetNumber = property.get("streetNumber");
		this.streetName = property.get("streetName");
		this.suburb = property.get("suburb");
		this.numBedrooms = property.get("numBedrooms");
		this.type = property.get("type");
		status = property.get("status");
		this.lastMaintenanceDate = property.get("lastMaintenanceDate");
		this.description = property.get("description");
		this.image = property.get("image");
	}
	
	private void makeImage(String image) {
		Image img = new Image(new File("images/" + image).toURI().toString());
		imageView = new ImageView();
		imageView.setImage(img);
	}
	
	private void makeDetails() {
		detailsView = new GridPane();
		Label propertyIDLabel = new Label("Property ID: ");
		Label propertyIDText = new Label(propertyID);
		detailsView.add(propertyIDLabel, 0, 0);
		detailsView.add(propertyIDText, 1, 0);
		Label addressLabel = new Label("Address: ");
		Label addressText = new Label(streetNumber + " " + streetName + ", " + suburb);
		detailsView.add(addressLabel, 0, 1);
		detailsView.add(addressText, 1, 1);
		Label typeLabel = new Label("Type: ");
		Label typeText = new Label(type);
		detailsView.add(typeLabel, 0, 2);
		detailsView.add(typeText, 1, 2);
		Label bedroomsLabel = new Label("Bedrooms: ");
		Label bedroomsText = new Label(numBedrooms);
		detailsView.add(bedroomsLabel, 0, 3);
		detailsView.add(bedroomsText, 1, 3);
		Label descriptionLabel = new Label("Description: ");
		Label descriptionText = new Label(description);
		detailsView.add(descriptionLabel, 0, 4);
		detailsView.add(descriptionText, 1, 4);
		Label maintainLabel = new Label("Last maintenance date: ");
		Label maintainText = new Label(lastMaintenanceDate);
		detailsView.add(maintainLabel, 0, 5);
		detailsView.add(maintainText, 1, 5);
		Label statusLabel = new Label("Status: ");
		Label statusText = new Label(status);
		detailsView.add(statusLabel, 0, 6);
		detailsView.add(statusText, 1, 6);
	}
	
	private void makeRecords() {
		recordsScroller = new ScrollPane();
		recordsView = new VBox();
		recordsScroller.setContent(recordsView);
	}
	
	private void makeButtons() {
		makeSubViews();
		buttonsStack = new StackPane();
		buttonsView = new GridPane();
		buttonsView.setHgap(10);
		buttonsView.setVgap(10);
		buttonsStack.getChildren().addAll(buttonsView, rentView, returnView);
		Button rentButton = new Button("Rent");
		Button returnButton = new Button("Return");
		Button maintainButton = new Button("Maintenance");
		Button completeButton = new Button("Complete Maintenance");
		PropertyDetailController controller = new PropertyDetailController();
		rentButton.setOnAction(controller);
		returnButton.setOnAction(controller);
		maintainButton.setOnAction(controller);
		completeButton.setOnAction(controller);
		buttonsView.add(rentButton, 0, 0);
		buttonsView.add(maintainButton, 1, 0);
		buttonsView.add(returnButton, 0, 1);
		buttonsView.add(completeButton, 1, 1);
		buttonsView.toFront();
	}
	
	private void makeSubViews() {
		rentView = new RentView();
		returnView = new ReturnView();
		rentView.setVisible(false);
		returnView.setVisible(false);
	}
	
	public static GridPane getButtonsView() {
		return buttonsView;
	}
	
	public static RentView getRentView() {
		return rentView;
	}
	
	public static ReturnView getReturnView() {
		return returnView;
	}
	
	public static String getStatus() {
		return status;
	}

}
