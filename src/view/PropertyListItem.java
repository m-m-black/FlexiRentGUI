package view;

import java.io.File;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class PropertyListItem extends HBox {
	
	private ImageView imageBox;
	private VBox titleBox;
	private TextField description;
	private TextField title;
	private Button detailsButton;
	
	public PropertyListItem(String imageURL, String titleText, String descriptionText) {
		this.setStyle("-fx-background-color: lightblue");
		this.makeItem(imageURL, titleText, descriptionText);
		this.getChildren().addAll(imageBox, titleBox, detailsButton);
	}
	
	private void makeItem(String imageURL, String titleText, String descriptionText) {
		Image image = new Image(new File("images/" + imageURL).toURI().toString());
		imageBox = new ImageView();
		imageBox.setImage(image);
		titleBox = new VBox();
		this.makeTitle(titleText, descriptionText);
		detailsButton = new Button("Details");
	}
	
	private void makeTitle(String titleText, String descriptionText) {
		title = new TextField(titleText);
		description = new TextField(descriptionText);
		titleBox.getChildren().addAll(title, description);
	}

}
