package controller;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;

public class SideMenuHandler implements EventHandler<MouseEvent> {
	
	private FlowPane label;
	private boolean mouseOver;
	
	public SideMenuHandler(FlowPane label, boolean mouseOver) {
		this.label = label;
		this.mouseOver = mouseOver;
	}

	@Override
	public void handle(MouseEvent e) {
		if (mouseOver) {
			label.setStyle("-fx-background-color: lightgreen");
		} else {
			label.setStyle("-fx-background-color: null");
		}
	}

}
