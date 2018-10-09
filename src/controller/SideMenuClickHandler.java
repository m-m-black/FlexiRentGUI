package controller;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import view.MainProgramWindow;
import view.StartUp;

public class SideMenuClickHandler implements EventHandler<MouseEvent> {
	
	private String label;
	
	public SideMenuClickHandler(String label) {
		this.label = label;
	}

	@Override
	public void handle(MouseEvent e) {
		StartUp.switchView(true, null);
		switch (label) {
			case "Home":					MainProgramWindow.setWindow("Home");
										break;
			case "Add Property":			MainProgramWindow.setWindow("Add Property");
										break;
		}
	}

}
