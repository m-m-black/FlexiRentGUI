package controller.listeners;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import main.DatabaseMethods;

public class ImportDataController implements EventHandler<ActionEvent> {
	
	private Stage stage;
	
	public ImportDataController(Stage stage) {
		this.stage = stage;
	}

	@Override
	public void handle(ActionEvent e) {
		FileChooser fileChooser = new FileChooser();
		File file = fileChooser.showOpenDialog(stage);
		try {
			Scanner input = new Scanner(file);
			ArrayList<String> ids = DatabaseMethods.getIDs();
			while (input.hasNextLine()) {
				// split Strings at ":"
				String[] elements = input.nextLine().split(":");
				// check for duplicate IDs
				for (String id: ids) {
					if (elements[0].compareTo(id) == 0) {
						// do not add this row to DB
					} else {
						// add row to DB with new RentalProperty()
					}
				}
			}
			input.close();
		} catch (FileNotFoundException e1) {
			System.out.println(e1.getMessage());
		}
	}

}
