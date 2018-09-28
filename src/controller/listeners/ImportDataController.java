package controller.listeners;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

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
			while (input.hasNextLine()) {
				System.out.println(input.nextLine());
			}
			input.close();
		} catch (FileNotFoundException e1) {
			System.out.println(e1.getMessage());
		}
	}

}
