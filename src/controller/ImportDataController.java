package controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.Apartment;
import model.PremiumSuite;
import model.PropertyStatus;
import model.PropertyType;
import model.RentalRecord;
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
			ArrayList<String> propertyIDs = DatabaseMethods.getPropertyIDs();
			ArrayList<String> recordIDs = DatabaseMethods.getRecordIDs();
			while (input.hasNextLine()) {
				boolean matchFound = false;
				String[] elements = input.nextLine().split(":");
				String firstElement = elements[0];
				if (!isRentalRecord(firstElement)) {
					if (propertyIDs.size() > 0) {
						for (String propertyID: propertyIDs) {
							if (firstElement.compareTo(propertyID) == 0) {
								matchFound = true;
								Alert alert = new Alert(AlertType.WARNING, "Duplicate PropertyID: " + firstElement);
								alert.showAndWait();
								continue;
							}
						}
						if (!matchFound) {
							addRentalProperty(elements);
						}
					} else {
						addRentalProperty(elements);
					}
				}
				if (isRentalRecord(firstElement)) {
					if (recordIDs.size() > 0) {
						for (String recordID: recordIDs) {
							if (firstElement.compareTo(recordID) == 0) {
								matchFound = true;
								Alert alert = new Alert(AlertType.WARNING, "Duplicate RecordID: " + firstElement);
								alert.showAndWait();
								continue;
							}
						}
						if (!matchFound) {
							addRentalRecord(elements);
						}
					} else {
						addRentalRecord(elements);
					}
				}
			}
			input.close();
		} catch (FileNotFoundException e1) {
			System.out.println(e1.getMessage());
		}
		StartUp.getList().populate();
		MainProgramWindow.setWindow("Home");
	}
	
	private void addRentalRecord(String[] elements) {
		new RentalRecord(elements[0], elements[1], elements[2], elements[3], elements[4], elements[5]);
	}
	
	private void addRentalProperty(String[] elements) {
		if (elements[4].compareTo("Apartment") == 0) {
			new Apartment(elements[0], Integer.valueOf(elements[1]), elements[2], elements[3], 
				Integer.valueOf(elements[5]), PropertyType.Apartment, 
				PropertyStatus.valueOf(elements[6]), elements[8], elements[7]);
		} else if (elements[4].compareTo("Premium Suite") == 0) {
			new PremiumSuite(elements[0], Integer.valueOf(elements[1]), elements[2], elements[3], 
				PropertyType.PremiumSuite, PropertyStatus.valueOf(elements[6]), 
				elements[9], elements[8]);
		}
	}
	
	private boolean isRentalRecord(String id) {
		int delimCount = 0;
		for (int i=0; i<id.length(); i++) {
			if (id.substring(i, i+1).matches("_")) {
				delimCount++;
			}
		}
		return delimCount > 1 ? true : false;
	}

}
