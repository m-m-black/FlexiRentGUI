package view;

import java.io.File;
import java.util.HashMap;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class PropertyListItem extends HBox {
	
	private ImageView imageBox;
	private VBox titleBox;
	private Label description;
	private Label title;
	private Button detailsButton;
	private HashMap<String, String> property;
	private String propertyID;
	private String imageURL;
	private String titleText;
	private String descriptionText;
	
	public PropertyListItem(HashMap<String, String> property) {
		this.property = property;
		//setStyle("-fx-background-color: lightblue");
		extractInfo();
		makeItem(propertyID, imageURL, titleText, descriptionText);
		getChildren().addAll(imageBox, titleBox, detailsButton);
	}
	
	private void makeItem(String propertyID, String imageURL, String titleText, String descriptionText) {
		Image img = new Image(new File("images/" + imageURL).toURI().toString());
		imageBox = new ImageView();
		imageBox.setImage(img);
		titleBox = new VBox();
		this.makeTitle(titleText, descriptionText);
		detailsButton = new Button("Details");
		detailsButton.setOnAction(new EventHandler<ActionEvent> () {
			@Override
			public void handle(ActionEvent e) {
				// create new PropertyDetailWindow
				PropertyDetailWindow window = new PropertyDetailWindow(property);
				// switch Home view to PropertyDetailWindow
				StartUp.switchView(false, window);
			}
		});
	}
	
	private void makeTitle(String titleText, String descriptionText) {
		title = new Label(titleText);
		description = new Label(descriptionText);
		titleBox.getChildren().addAll(title, description);
	}
	
	private void extractInfo() {
		this.propertyID = property.get("propertyID");
		this.imageURL = property.get("image");
		this.titleText = property.get("streetNumber") + " " + property.get("streetName") + ", " + 
				property.get("suburb");
		this.descriptionText = property.get("description");
	}

}
