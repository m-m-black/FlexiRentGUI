package view;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class SideMenu extends BorderPane {
	
	private VBox topLabels;
	private VBox bottomLabels;
	private Label[] labels;
	private Label exitLabel;
	
	public SideMenu() {
		topLabels = new VBox();
		bottomLabels = new VBox();
		labels = new Label[5];
		
		this.setTop(topLabels);
		this.setBottom(bottomLabels);
		
		setLabels();
		addLabels();
		
		exitLabel = new Label("Exit");
		bottomLabels.getChildren().add(exitLabel);
		
		setPadding(new Insets(10, 0, 10, 10));
	}
	
	private void setLabels() {
		labels[0] = new Label("Home");
		labels[1] = new Label("Add Property");
		labels[2] = new Label("Rent Property");
		labels[3] = new Label("Return Property");
		labels[4] = new Label("Property Maintenance");
	}
	
	private void addLabels() {
		for (Label l: labels) {
			l.setPadding(new Insets(10, 0, 10, 0));
			topLabels.getChildren().add(l);
		}
	}

}
