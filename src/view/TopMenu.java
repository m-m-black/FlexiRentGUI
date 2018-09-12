package view;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

public class TopMenu extends MenuBar {
	
	private Menu menu;
	private MenuItem importData;
	private MenuItem exportData;
	
	public TopMenu() {
		menu = new Menu("File");
		importData = new MenuItem("Import Data");
		exportData = new MenuItem("Export Data");
		menu.getItems().addAll(importData, exportData);
		this.getMenus().add(menu);
	}
	
}
