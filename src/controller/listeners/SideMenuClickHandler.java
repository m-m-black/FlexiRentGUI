package controller.listeners;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class SideMenuClickHandler implements EventHandler<MouseEvent> {
	
	private String label;
	
	public SideMenuClickHandler(String label) {
		this.label = label;
	}

	@Override
	public void handle(MouseEvent e) {
		switch (label) {
			case "Home":					System.out.println("Home");
										break;
			case "Add Property":			System.out.println("Add Property");
										break;
			case "Rent Property":		System.out.println("Rent Property");
										break;
			case "Return Property":		System.out.println("Return Property");
										break;
			case "Property Maintenance":	System.out.println("Property Maintenance");
										break;
		}
	}

}
