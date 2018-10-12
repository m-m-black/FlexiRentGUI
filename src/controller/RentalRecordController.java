package controller;

import java.util.ArrayList;
import java.util.HashMap;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import model.db.DatabaseMethods;

public class RentalRecordController implements EventHandler<ActionEvent> {
	
	private VBox recordsView;
	private String propertyID;
	private ArrayList<HashMap<String, String>> recordHashMaps;
	private ArrayList<String> recordStrings;
	
	public RentalRecordController(VBox recordsView, String propertyID) {
		this.recordsView = recordsView;
		this.propertyID = propertyID;
		fetchRecords();
		makeRecords();
		setRecords();
	}

	@Override
	public void handle(ActionEvent e) {
		
	}
	
	private void fetchRecords() {
		recordHashMaps = DatabaseMethods.getPropertyRecords(propertyID);
	}
	
	private void makeRecords() {
		recordStrings = new ArrayList<String>();
		for (HashMap<String, String> map: recordHashMaps) {
			String record = "";
			record += map.get("recordID");
			record += ", ";
			record += map.get("rentDate");
			record += ", ";
			record += map.get("estReturnDate");
			record += ", ";
			record += map.get("actReturnDate");
			record += ", ";
			String rentalFee = "$" + String.format("%.2f", Double.parseDouble(map.get("rentalFee")));
			record += rentalFee;
			record += ", ";
			String lateFee = "$" + String.format("%.2f", Double.parseDouble(map.get("lateFee")));
			record += lateFee;
			recordStrings.add(record);
		}
	}
	
	private void setRecords() {
		for (String record: recordStrings) {
			Label recordLabel = new Label(record);
			recordsView.getChildren().add(recordLabel);
		}
	}

}
