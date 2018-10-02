package controller.listeners;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.Apartment;
import model.PremiumSuite;
import model.PropertyStatus;
import model.PropertyType;
import model.db.DatabaseMethods;
import view.MainProgramWindow;
import view.StartUp;

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
						// skip this line
						input.nextLine();
					} 
				}
				if (elements[4].compareTo("Apartment") == 0) {
					new Apartment(Integer.valueOf(elements[1]), elements[2], elements[3], 
						Integer.valueOf(elements[5]), PropertyType.Apartment, 
						PropertyStatus.valueOf(elements[6]), elements[8], elements[7]);
				} else if (elements[4].compareTo("Premium Suite") == 0) {
					new PremiumSuite(Integer.valueOf(elements[1]), elements[2], elements[3], 
						PropertyType.PremiumSuite, PropertyStatus.valueOf(elements[6]), 
						elements[9], elements[8]);
				}
			}
			input.close();
		} catch (FileNotFoundException e1) {
			System.out.println(e1.getMessage());
		}
		StartUp.getList().populate();
		MainProgramWindow.setWindow("Home");
	}

}
