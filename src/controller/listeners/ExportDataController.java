package controller.listeners;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

public class ExportDataController implements EventHandler<ActionEvent>{
	
	private Stage primaryStage;
	
	public ExportDataController(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}
 
	@Override
	public void handle(ActionEvent e) {
		DirectoryChooser dirChooser = new DirectoryChooser();
		File selectedDir = dirChooser.showDialog(primaryStage);
		writeToFile(selectedDir.getAbsolutePath(), "export_data.txt");
	}
	
	private void writeToFile(String absolutePath, String fileName) {
		PrintWriter pw;
		try {
			pw = new PrintWriter(absolutePath + "/" + fileName, "UTF-8");
			pw.println("Test line");
			pw.close();
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			System.out.println(e.getMessage());
		}
	}

}
