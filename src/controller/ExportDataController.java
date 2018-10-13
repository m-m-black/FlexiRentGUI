package controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import model.db.DatabaseMethods;

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
		ArrayList<String> propertyRows = DatabaseMethods.getAllPropertyRows();
		ArrayList<ArrayList<String>> recordRows = DatabaseMethods.getAllRecordRows();
		try {
			pw = new PrintWriter(absolutePath + "/" + fileName, "UTF-8");
			// write each row to file
			for (String row: propertyRows) {
				pw.println(row);
				for (ArrayList<String> record: recordRows) {
					// if propertyID in record matches propertyID in row
					if (row.split(":")[0].compareTo(record.get(1)) == 0) {
						pw.println(record.get(0));
					}
				}
			}
			pw.close();
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			System.out.println(e.getMessage());
		}
	}

}
