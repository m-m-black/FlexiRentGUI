package controller.listeners;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import view.MainProgramWindow;

public class SideMenuClickHandler implements EventHandler<MouseEvent> {
	
	private String label;
	
	public SideMenuClickHandler(String label) {
		this.label = label;
	}

	@Override
	public void handle(MouseEvent e) {
		switch (label) {
			case "Home":					System.out.println(label);
										MainProgramWindow.setWindow("Home");
										break;
			case "Add Property":			System.out.println(label);
										MainProgramWindow.setWindow("Add Property");
										break;
			case "Rent Property":		System.out.println(label);
										MainProgramWindow.setWindow("Rent Property");
										break;
			case "Return Property":		System.out.println(label);
										MainProgramWindow.setWindow("Return Property");
										break;
			case "Property Maintenance":	System.out.println(label);
										MainProgramWindow.setWindow("Property Maintenance");
										break;
		}
	}

}
