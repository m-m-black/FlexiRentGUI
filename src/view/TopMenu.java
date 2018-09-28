package view;

import controller.listeners.ExportDataController;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

public class TopMenu extends MenuBar {
	
	private Menu menu;
	private MenuItem importData;
	private MenuItem exportData;
	
	public TopMenu(Stage primaryStage) {
		menu = new Menu("File");
		importData = new MenuItem("Import Data");
		exportData = new MenuItem("Export Data");
		exportData.setOnAction(new ExportDataController(primaryStage));
		menu.getItems().addAll(importData, exportData);
		this.getMenus().add(menu);
	}
	
}
