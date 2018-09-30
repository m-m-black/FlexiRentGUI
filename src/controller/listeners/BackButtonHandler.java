package controller.listeners;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import view.PropertyDetailWindow;

public class BackButtonHandler implements EventHandler<ActionEvent> {

	@Override
	public void handle(ActionEvent e) {
		PropertyDetailWindow.getButtonsView().setVisible(true);
		PropertyDetailWindow.getButtonsView().toFront();
		PropertyDetailController.setNotVisible();
	}

}
