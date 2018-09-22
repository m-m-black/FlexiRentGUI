package view;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class PropertyListItem extends VBox {
	
	private ImageView imageBox;
	private HBox titleBox;
	private TextField descriptionBox;
	private TextField title;
	private Button detailsButton;
	
	public PropertyListItem(String imageURL, String titleText, String descriptionText) {
		this.setStyle("-fx-background-color: blue");
		this.makeItem(imageURL, titleText, descriptionText);
		this.getChildren().addAll(imageBox, titleBox, descriptionBox);
	}
	
	private void makeItem(String imageURL, String titleText, String descriptionText) {
		imageBox = new ImageView(new Image("images/" + imageURL));
		titleBox = new HBox();
		this.makeTitle(titleText);
		descriptionBox = new TextField(descriptionText);
	}
	
	private void makeTitle(String titleText) {
		title = new TextField(titleText);
		detailsButton = new Button("Details");
		titleBox.getChildren().addAll(title, detailsButton);
	}

}
