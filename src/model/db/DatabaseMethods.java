package model.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import model.DateTime;

public class DatabaseMethods {
	
	public static void insertRow(String tableName, String valueString) {
		final String DB_NAME = "flexiRentDB";
		final String TABLE_NAME = tableName;
		String values = valueString;
		try (Connection con = FlexiRentDBConnection.getConnection(DB_NAME);
				Statement stmt = con.createStatement();
				) {
			String query = "INSERT INTO " + TABLE_NAME +
					" VALUES (" + values + ")";
			int result = stmt.executeUpdate(query);
			con.commit();
			System.out.println("Insert into table " + TABLE_NAME + " executed successfully");
			System.out.println(result + " row(s) affected");
		} catch (Exception e) {
			System.out.println("insertRow Exception");
			System.out.println(e.getMessage());
		}
	}
	
	public static void performMaintenance(String propertyID, String status) {
		final String DB_NAME = "flexiRentDB";
		final String TABLE_NAME = "RENTAL_PROPERTY";
		try (Connection con = FlexiRentDBConnection.getConnection(DB_NAME);
				Statement stmt = con.createStatement();
				) {
			String query = "UPDATE " + TABLE_NAME +
					" SET status = '" + status + "'" +
					" WHERE propertyID LIKE '" + propertyID + "'";
			int result = stmt.executeUpdate(query);
			System.out.println("Update table " + TABLE_NAME + " executed successfully");
			System.out.println(result + " row(s) affected");

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public static void completeMaintenance(String propertyID, DateTime completionDate) {
		final String DB_NAME = "flexiRentDB";
		final String TABLE_NAME = "RENTAL_PROPERTY";
		try (Connection con = FlexiRentDBConnection.getConnection(DB_NAME);
				Statement stmt = con.createStatement();
				) {
			String query = "UPDATE " + TABLE_NAME +
					" SET lastMaintenanceDate = '" + completionDate.toString() +
					"' WHERE propertyID LIKE '" + propertyID + "'";
			int result = stmt.executeUpdate(query);
			System.out.println("Update table " + TABLE_NAME + " executed successfully");
			System.out.println(result + " row(s) affected");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public static void updateRecord(String recordID, String actReturnDate, double rentalFee, double lateFee) {
		final String DB_NAME = "flexiRentDB";
		final String TABLE_NAME = "RENTAL_RECORD";
		try (Connection con = FlexiRentDBConnection.getConnection(DB_NAME);
				Statement stmt = con.createStatement();
				) {
			String query = "UPDATE " + TABLE_NAME + 
					" SET actReturnDate = '" + actReturnDate + 
					"', rentalFee = " + rentalFee + ", lateFee = " + lateFee + 
					" WHERE recordID LIKE '" + recordID + "'";
			int result = stmt.executeUpdate(query);
			System.out.println("Update table " + TABLE_NAME + " executed successfully");
			System.out.println(result + " row(s) affected");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public static void updateStatus(String propertyID, String status) {
		final String DB_NAME = "flexiRentDB";
		final String TABLE_NAME = "RENTAL_PROPERTY";
		try (Connection con = FlexiRentDBConnection.getConnection(DB_NAME);
				Statement stmt = con.createStatement();
				) {
			String query = "UPDATE " + TABLE_NAME +
					" SET status = '" + status + "'" +
					" WHERE propertyID LIKE '" + propertyID + "'";
			int result = stmt.executeUpdate(query);
			System.out.println("Update table " + TABLE_NAME + " executed successfully");
			System.out.println(result + " row(s) affected");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public static String selectRow(String tableName, String id) {
		final String DB_NAME = "flexiRentDB";
		final String TABLE_NAME = tableName;
		String identifier = null;
		String returnValue = null;
		if (TABLE_NAME.compareTo("RENTAL_PROPERTY") == 0) {
			identifier = "propertyID";
		} else if (TABLE_NAME.compareTo("RENTAL_RECORD") == 0) {
			identifier = "recordID";
		}
		try (Connection con = FlexiRentDBConnection.getConnection(DB_NAME);
				Statement stmt = con.createStatement();
				) {
			String query = "SELECT * FROM " + TABLE_NAME + " WHERE " + identifier + " LIKE " + id;
			try (ResultSet resultSet = stmt.executeQuery(query)) {
				returnValue = resultSet.getString(identifier);
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
 		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return returnValue;
	}
	
	public static String getCurrentRecord(String propertyID) {
		final String DB_NAME = "flexiRentDB";
		final String TABLE_NAME = "RENTAL_RECORD";
		String recordID = null;
		try (Connection con = FlexiRentDBConnection.getConnection(DB_NAME);
				Statement stmt = con.createStatement();
				) {
			String query = "SELECT recordID FROM " + TABLE_NAME + 
					" WHERE propertyID LIKE " + propertyID + 
					" AND actReturnDate IS NULL";
			try (ResultSet resultSet = stmt.executeQuery(query)) {
				recordID = resultSet.getString("recordID");
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
 		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return recordID;
	}
	
	public static HashMap<String, String> getRentalRecord(String recordID) {
		final String DB_NAME = "flexiRentDB";
		final String TABLE_NAME = "RENTAL_RECORD";
		HashMap<String, String> record = new HashMap<String, String>();
		try (Connection con = FlexiRentDBConnection.getConnection(DB_NAME);
				Statement stmt = con.createStatement();
				) {
			String query = "SELECT * FROM " + TABLE_NAME + 
					" WHERE recordID LIKE " + "'" + recordID + "'" + 
					" AND actReturnDate IS NULL";
			try (ResultSet resultSet = stmt.executeQuery(query)) {
				record.put("recordID", resultSet.getString(1));
				record.put("propertyID", resultSet.getString(2));
				record.put("rentDate", resultSet.getString(3));
				record.put("estReturnDate", resultSet.getString(4));
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
 		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return record;
	}
	
	public static ArrayList<HashMap<String, String>> getProperties() {
		final String DB_NAME = "flexiRentDB";
		final String TABLE_NAME = "RENTAL_PROPERTY";
		// ArrayList to store temporary RentalProperty objects, where each object is represented as a HashMap
		ArrayList<HashMap<String, String>> propertiesResultSets = new ArrayList<HashMap<String, String>>();
		try (Connection con = FlexiRentDBConnection.getConnection(DB_NAME);
				Statement stmt = con.createStatement();
				) {
			String query = "SELECT * FROM " + TABLE_NAME;
			try (ResultSet resultSet = stmt.executeQuery(query)) {
				while (resultSet.next()) {
					// Build HashMap from each DB row, add to ArrayList
					HashMap<String, String> p = new HashMap<String, String>();
					p.put("propertyID", resultSet.getString(1));
					p.put("streetNumber", resultSet.getString(2));
					p.put("streetName", resultSet.getString(3));
					p.put("suburb", resultSet.getString(4));
					p.put("numBedrooms", resultSet.getString(5));
					p.put("type", resultSet.getString(6));
					p.put("status", resultSet.getString(7));
					p.put("lastMaintenanceDate", resultSet.getString(8));
					p.put("description", resultSet.getString(9));
					p.put("image", resultSet.getString(10));
					propertiesResultSets.add(p);
				}
			} catch (SQLException e) {
				System.out.println("getProperties SQLException");
				System.out.println(e.getMessage());
			}
		} catch (Exception e) {
			System.out.println("getProperties Exception");
			System.out.println(e.getMessage());
		}
		return propertiesResultSets;
	}
	
	public static ArrayList<String> getAllRows() {
		final String DB_NAME = "flexiRentDB";
		// ArrayList to store a String for each row
		ArrayList<String> rows = new ArrayList<String>();
		try (Connection con = FlexiRentDBConnection.getConnection(DB_NAME);
				Statement stmt = con.createStatement();
				) {
			String query = "SELECT * FROM RENTAL_PROPERTY";
			try (ResultSet resultSet = stmt.executeQuery(query)) {
				while (resultSet.next()) {
					String row = "";
					// build String with ":" separator for each row
					row += resultSet.getString(1) + ":";
					row += resultSet.getString(2) + ":";
					row += resultSet.getString(3) + ":";
					row += resultSet.getString(4) +":";
					row += resultSet.getString(6) + ":";
					row += resultSet.getString(5) + ":";
					row += resultSet.getString(7) + ":";
					if (resultSet.getString(6).compareTo("Premium Suite") == 0) {
						row += resultSet.getString(9) + ":";
					}
					row += resultSet.getString(11) + ":";
					row += resultSet.getString(10);
					rows.add(row);
				}
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
 		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return rows;
	}
	
	public static ArrayList<String> getIDs() {
		ArrayList<String> ids = new ArrayList<String>();
		final String DB_NAME = "flexiRentDB";
		final String TABLE_NAME = "RENTAL_PROPERTY";
		try (Connection con = FlexiRentDBConnection.getConnection(DB_NAME);
				Statement stmt = con.createStatement();
				) {
			String query = "SELECT propertyID FROM " + TABLE_NAME;
			try (ResultSet resultSet = stmt.executeQuery(query)) {
				while (resultSet.next()) {
					ids.add(resultSet.getString(1));
				}
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
 		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return ids;
	}
	
	public static int getPropertyCount(String type) {
		final String DB_NAME = "flexiRentDB";
		final String TABLE_NAME = "RENTAL_PROPERTY";
		int rowCount = 0;
		try (Connection con = FlexiRentDBConnection.getConnection(DB_NAME);
				Statement stmt = con.createStatement();
				) {
			String query = "SELECT COUNT(propertyID) FROM " + TABLE_NAME + " WHERE type LIKE '" + type + "'";
			try (ResultSet resultSet = stmt.executeQuery(query)) {
				resultSet.next();
				rowCount = resultSet.getInt(1);
			} catch (SQLException e) {
				System.out.println("getPropertyCount SQLException");
				System.out.println(e.getMessage());
			}
 		} catch (Exception e) {
 			System.out.println("getPropertyCount Exception");
			System.out.println(e.getMessage());
		}
		return rowCount;
	}

	public static int getNumBedrooms(String propertyID) {
		final String DB_NAME = "flexiRentDB";
		final String TABLE_NAME = "RENTAL_PROPERTY";
		int numBedrooms = 0;
		try (Connection con = FlexiRentDBConnection.getConnection(DB_NAME);
				Statement stmt = con.createStatement();
				) {
			String query = "SELECT numBedrooms FROM " + TABLE_NAME + 
					" WHERE propertyID LIKE " + "'" + propertyID + "'";
			try (ResultSet resultSet = stmt.executeQuery(query)) {
				numBedrooms = resultSet.getInt(5);
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
 		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return numBedrooms;
	}
	

	public static String getStatus(String propertyID) {
		final String DB_NAME = "flexiRentDB";
		final String TABLE_NAME = "RENTAL_PROPERTY";
		String status = "";
		try (Connection con = FlexiRentDBConnection.getConnection(DB_NAME);
				Statement stmt = con.createStatement();
				) {
			String query = "SELECT status FROM " + TABLE_NAME + " WHERE propertyID LIKE '" + propertyID + "'";
			try (ResultSet resultSet = stmt.executeQuery(query)) {
				status = resultSet.getString(1);
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return status;
	}
	
	public static String getLastMaintenanceDate(String propertyID) {
		final String DB_NAME = "flexiRentDB";
		final String TABLE_NAME = "RENTAL_PROPERTY";
		String lastMaintenanceDate = "";
		try (Connection con = FlexiRentDBConnection.getConnection(DB_NAME);
				Statement stmt = con.createStatement();
				) {
			String query = "SELECT lastMaintenanceDate FROM " + 
				TABLE_NAME + " WHERE propertyID LIKE '" + propertyID + "'";
			try (ResultSet resultSet = stmt.executeQuery(query)) {
				lastMaintenanceDate = resultSet.getString(1);
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return lastMaintenanceDate;
	}

}
