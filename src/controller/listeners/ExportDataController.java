package controller.listeners;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import main.DatabaseMethods;

public class ExportDataController implements EventHandler<ActionEvent>{
	
	private Stage stage;
	
	public ExportDataController(Stage stage) {
		this.stage = stage;
	}
 
	@Override
	public void handle(ActionEvent e) {
		DirectoryChooser dirChooser = new DirectoryChooser();
		File selectedDir = dirChooser.showDialog(stage);
		writeToFile(selectedDir.getAbsolutePath(), "export_data.txt");
	}
	
	private void writeToFile(String absolutePath, String fileName) {
		PrintWriter pw;
		ArrayList<String> rows = DatabaseMethods.getAllRows();
		try {
			pw = new PrintWriter(absolutePath + "/" + fileName, "UTF-8");
			// write each row to file
			for (String row: rows) {
				pw.println(row);
			}
			pw.close();
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			System.out.println(e.getMessage());
		}
	}

}
