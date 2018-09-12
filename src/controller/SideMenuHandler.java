package controller;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;

public class SideMenuHandler implements EventHandler<MouseEvent> {
	
	private FlowPane label;
	private boolean mouseOn;
	
	public SideMenuHandler(FlowPane label, boolean mouseOn) {
		this.label = label;
		this.mouseOn = mouseOn;
	}

	@Override
	public void handle(MouseEvent e) {
		if (mouseOn) {
			label.setStyle("-fx-background-color: lightgrey");
		} else {
			label.setStyle("-fx-background-color: white");
		}
	}

}
