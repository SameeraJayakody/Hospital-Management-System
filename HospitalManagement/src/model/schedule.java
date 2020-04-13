package model;

import database.dbconnect;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class schedule {

	dbconnect obj = new dbconnect();

	public String insertItem(String sid, String hid, String hname, String did, String dname, String special,
			String date, String start, String end, String room, String sts) {
		String output = "";
		try {
			Connection con = obj.connect();
			if (con == null) {
				return "Error while connecting to the database";
			}
			// create a prepared statement
			String query = "insert into schedule"
					+ "(`ID`, `scheduleID`, `HospitalID`, `HospitalName`, `DoctorID`, `DoctorName`, `Speciality`, `Date`, `StartTime`, `EndTime`, `RoomNumber`,`Status`)"
					+ " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, sid);
			preparedStmt.setString(3, hid);
			preparedStmt.setString(4, hname);
			preparedStmt.setString(5, did);
			preparedStmt.setString(6, dname);
			preparedStmt.setString(7, special);
			preparedStmt.setString(8, date);
			preparedStmt.setString(9, start);
			preparedStmt.setString(10, end);
			preparedStmt.setString(11, room);
			preparedStmt.setString(12, sts);

			// execute the statement
			preparedStmt.execute();
			// System.out.print("successfuly inserted");
			con.close();
			output = "Inserted successfully";
		} catch (Exception e) {
			output = "Error while inserting items";
			System.err.println(e.getMessage());
		}
		return output;
	}

	// read the items

	public String readItems() {
		String output = "";
		try {
			Connection con = obj.connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}
			// Prepare the html table to be displayed
			output = "<table border=\"1\" align=\"center\"><tr><th>scheduleID</th>"
					+ "<th>HospitalID</th><th>HospitalName</th>" + "<th>DoctorID</th>" + "<th>DoctorName</th>"
					+ "<th>Speciality</th>" + "<th>Date</th>" + "<th>StartTime</th>" + "<th>EndTime</th>"
					+ "<th>RoomNumber</th>" + "<th>Status</th>" + "<th>Modifiy</th><th>Remove</th></tr>";
			String query = "select * from schedule";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			// iterate through the rows in the result set
			while (rs.next()) {
				String id = rs.getString("ID");
				String sid = rs.getString("scheduleID");
				String hid = rs.getString("HospitalID");
				String hn = rs.getString("HospitalName");
				String did = rs.getString("DoctorID");
				String dn = rs.getString("DoctorName");
				String sp = rs.getString("Speciality");
				String da = rs.getString("Date");
				String st = rs.getString("StartTime");
				String en = rs.getString("EndTime");
				String rm = rs.getString("RoomNumber");
				String sts = rs.getString("Status");
				// Add into the html table
				output += "<tr><td>" + sid + "</td>";
				output += "<td>" + hid + "</td>";
				output += "<td>" + hn + "</td>";
				output += "<td>" + did + "</td>";
				output += "<td>" + dn + "</td>";
				output += "<td>" + sp + "</td>";
				output += "<td>" + da + "</td>";
				output += "<td>" + st + "</td>";
				output += "<td>" + en + "</td>";
				output += "<td>" + rm + "</td>";
				output += "<td>" + sts + "</td>";
				// buttons
				output += "<td><input name=\"btnUpdate\" " + " type=\"button\" value=\"Update\"></td>"
						+ "<td><form method=\"post\" action=\"tab.jsp\">" + "<input name=\"btnRemove\" "
						+ " type=\"submit\" value=\"Remove\" action=\"tab.jsp\">"
						+ "<input name=\"id\" type=\"hidden\" " + " value=\"" + id + "\">" + "</form></td></tr>";
			}
			con.close();
			// Complete the html table
			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading the items.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	// delete

	public String deleteItem(String ID) {
		String output = "";
		try {
			Connection con = obj.connect();
			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}

			// create a prepared statement
			String query = "delete from schedule where ID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values

			preparedStmt.setInt(1, Integer.parseInt(ID));

			// execute the statement
			preparedStmt.execute();
			con.close();

			output = "Deleted successfully";
		} catch (Exception e) {
			output = "Error while deleting the item.";
			System.err.println(e.getMessage());
		}

		return output;
	}

	// update

	public String updateItem(int id, String sid, String hid, String hname, String did, String dname, String special,
			String date, String start, String end, String room) {
		String output = "";

		try {
			Connection con = obj.connect();
			if (con == null) {

				return "Error while connecting to the database for updating.";
			}

			// create a prepared statement

			String query = "UPDATE schedule SET scheduleID=?, HospitalID=?, HospitalName=?, DoctorID=?, DoctorName=?, Speciality=?, Date=?, StartTime=?, EndTime=?, RoomNumber=? WHERE ID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values

			preparedStmt.setString(1, sid);
			preparedStmt.setString(2, hid);
			preparedStmt.setString(3, hname);
			preparedStmt.setString(4, did);
			preparedStmt.setString(5, dname);
			preparedStmt.setString(6, special);
			preparedStmt.setString(7, date);
			preparedStmt.setString(8, start);
			preparedStmt.setString(9, end);
			preparedStmt.setString(10, room);
			preparedStmt.setInt(11, id);
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Updated successfully";

		} catch (Exception e) {
			output = "Error while updating the item.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String DisplayDoctor() {
		String output = "";
		try {
			Connection con = obj.connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}
			// Prepare the html table to be displayed
			output = "<table border=\"1\" align=\"center\"><tr><th>scheduleID</th>"
					+ "<th>HospitalName</th><th>DoctorName</th>" + "<th>Speciality</th>" + "<th>Date</th>"
					+ "<th>StartTime</th>" + "<th>EndTime</th>" + "<th>RoomNumber</th>" + "<th>Status</th>"
					+ "<th>Modifiy</th></tr>";
			String query = "select * from schedule";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			// iterate through the rows in the result set
			while (rs.next()) {
				String id = rs.getString("ID");
				String sid = rs.getString("scheduleID");
				// String hid = rs.getString("HospitalID");
				String hn = rs.getString("HospitalName");
				// String did = rs.getString("DoctorID");
				String dn = rs.getString("DoctorName");
				String sp = rs.getString("Speciality");
				String da = rs.getString("Date");
				String st = rs.getString("StartTime");
				String en = rs.getString("EndTime");
				String rm = rs.getString("RoomNumber");
				String sts = rs.getString("Status");

				// Add into the html table
				output += "<tr><td>" + sid + "</td>";
				output += "<td>" + hn + "</td>";
				output += "<td>" + dn + "</td>";
				output += "<td>" + sp + "</td>";
				output += "<td>" + da + "</td>";
				output += "<td>" + st + "</td>";
				output += "<td>" + en + "</td>";
				output += "<td>" + rm + "</td>";
				output += "<td>" + sts + "</td>";
				/*
				 * output += "<td>" + rm + "</td>"; output += "<td>" + sts + "</td>";
				 */
				// buttons
				output += "<td><input name=\"btnRemove\" "
						/* + " type=\"button\" value=\"Update\"></td>" */
						/* + "<td><form method=\"post\" action=\"TimeCollector.jsp\">" */
						/* + "<input name=\"btnRemove\" " */
						+ " type=\"submit\" value=\"Update\" action=\"TimeCollector.jsp\">"
						+ "<input name=\"id\" type=\"hidden\" " + " value=\"" + id + "\">" + "</form></td></tr>";
			}
			con.close();
			// Complete the html table
			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading the items.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	// doc insert

	public String insertSchedule(String sid, String hname, String dname, String special, String date, String start,String end, String room, String sts) {
		String output = "";
		try {
			Connection con = obj.connect();
			if (con == null) {
				return "Error while connecting to the database";
			}
			// create a prepared statement
			String query = "insert into docschedule"
					+ "(`scheduleID`, `HospitalName`, `DoctorName`, `Speciality`, `Date`, `StartTime`, `EndTime`, `RoomNumber`,`Status`)"
					+ " values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, sid);
            preparedStmt.setString(3, hname);
            preparedStmt.setString(4, dname);
			preparedStmt.setString(5, special);
			preparedStmt.setString(6, date);
			preparedStmt.setString(7, start);
			preparedStmt.setString(8, end);
			preparedStmt.setString(9, room);
			preparedStmt.setString(10, sts);

			// execute the statement
			preparedStmt.execute();
			// System.out.print("successfuly inserted");
			con.close();
			output = "Inserted successfully";
		} catch (Exception e) {
			output = "Error while inserting items";
			System.err.println(e.getMessage());
		}
		return output;
	}

	
	  // confirm
	  
	  
	/*
	 * public String Confirm() { String output = ""; try { Connection con =
	 * obj.connect(); if (con == null) { return
	 * "Error while connecting to the database for reading."; } // Prepare the html
	 * table to be displayed
	 * 
	 * 
	 * 
	 * 
	 * output = "<table border=\"1\" align=\"center\"><tr><th>scheduleID</th>"
	 * +"<th>HospitalName</th><th>DoctorName</th>" + "<th>Speciality</th>" +
	 * "<th>Date</th>" + "<th>StartTime</th>" + "<th>EndTime</th>" +
	 * "<th>RoomNumber</th>" + "<th>Status</th>" + "<th>Modifiy</th></tr>";
	 * 
	 * 
	 * 
	 * String query = "select ID  from schedule where Status = 'YES'";
	 * 
	 * Statement stmt = con.createStatement(); ResultSet rs =
	 * stmt.executeQuery(query); // iterate through the rows in the result set
	 * 
	 * System.out.print(rs.getInt("ID"));
	 * 
	 * while (rs.next()) {
	 * 
	 * 
	 * Integer id = rs.getInt("ID");
	 * 
	 * }
	 * 
	 * 
	 * String query2 = "select * from schedule where 'ID' = id"; Statement stmt2 =
	 * con.createStatement(); ResultSet rs2 = stmt2.executeQuery(query2);
	 * 
	 * 
	 * while (rs2.next()) { Integer id2 = rs2.getInt("ID"); String sid =
	 * rs2.getString("scheduleID"); String hn = rs2.getString("HospitalName");
	 * String dn = rs2.getString("DoctorName"); String sp =
	 * rs2.getString("Speciality"); String da = rs2.getString("Date"); String st =
	 * rs2.getString("StartTime"); String en = rs2.getString("EndTime"); String rm =
	 * rs2.getString("RoomNumber"); String sts = rs2.getString("Status");
	 * 
	 * insertSchedule(sid,hn,dn,sp,da,st,en,rm,sts); }
	 * 
	 * schedule sk = new schedule();
	 * 
	 * 
	 * 
	 * 
	 * con.close(); } catch (Exception e) { output =
	 * "Error while inserting the items."; System.err.println(e.getMessage()); }
	 * return output; }
	 */
	  
	 
	
	 
	 
	 public String insertConfirmSchedule(String sid, String hname, String dname, String special, String date, String start, String end, String room, String sts) {
		String output = "";
		try {
			Connection con = obj.connect();
			if (con == null) {
				return "Error while connecting to the database";
			}
			// create a prepared statement
			String query = "insert into docschedule"
					+ "(`ID`, `scheduleID`, `HospitalName`, `DoctorName`, `Speciality`, `Date`, `StartTime`, `EndTime`, `RoomNumber`,`Status`)"
					+ " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, sid);
			preparedStmt.setString(3, hname);
			preparedStmt.setString(4, dname);
			preparedStmt.setString(5, special);
			preparedStmt.setString(6, date);
			preparedStmt.setString(7, start);
			preparedStmt.setString(8, end);
			preparedStmt.setString(9, room);
			preparedStmt.setString(10, sts);

			// execute the statement
			preparedStmt.execute();
			// System.out.print("successfuly inserted");
			con.close();
			output = "Inserted successfully";
		} catch (Exception e) {
			output = "Error while inserting items";
			System.err.println(e.getMessage());
		}
		return output;
	}
	 
	 
	 

	/*
	 * public String updateStatus(int id,String sid,String hname,String dname,String
	 * special,String date,String start,String end,String room ,String status) {
	 * String output = "";
	 * 
	 * try { Connection con = obj.connect(); if (con == null) {
	 * 
	 * return "Error while connecting to the database for updating."; }
	 * 
	 * // create a prepared statement
	 * 
	 * String query =
	 * "UPDATE docschedule SET scheduleID=?, HospitalName=?, DoctorName=?, Speciality=?, Date=?, StartTime=?, EndTime=?, RoomNumber=? Status=? WHERE ID=?"
	 * ; PreparedStatement preparedStmt = con.prepareStatement(query); // binding
	 * values
	 * 
	 * 
	 * preparedStmt.setString(2, sid); preparedStmt.setString(3, hname);
	 * preparedStmt.setString(4, dname); preparedStmt.setString(5, special);
	 * preparedStmt.setString(6, date); preparedStmt.setString(7, start);
	 * preparedStmt.setString(8, end); preparedStmt.setString(9, room);
	 * preparedStmt.setString(10, status); preparedStmt.setInt(1, id); // execute
	 * the statement preparedStmt.execute(); con.close();
	 * 
	 * output = "Updated successfully";
	 * 
	 * } catch (Exception e) { output = "Error while updating the item.";
	 * System.err.println(e.getMessage()); } return output; }
	 */
	
	
	
	// detlete doctor schedule
	
	
	public String RemoveRecord(String ID) {
		String output = "";
		try {
			Connection con = obj.connect();
			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}

			// create a prepared statement
			String query = "delete from docschedule where ID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values

			
			preparedStmt.setInt(1, Integer.parseInt(ID));

			// execute the statement
			preparedStmt.execute();
			con.close();

			output = "Deleted successfully";
		} catch (Exception e) {
			output = "Error while deleting the item.";
			System.err.println(e.getMessage());
		}

		return output;
	}
	
	
	
	
	// view table
	
	
	
	
	public String ViewTable() {
		String output = "";
		try {
			Connection con = obj.connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}
			// Prepare the html table to be displayed
			output = "<table border=\"1\" align=\"center\"><tr><th>scheduleID</th>"
					+ "<th>HospitalName</th><th>DoctorName</th>" + "<th>Speciality</th>" + "<th>Date</th>"
					+ "<th>StartTime</th>" + "<th>EndTime</th>" + "<th>RoomNumber</th>" + "<th>Status</th>"
					+ "<th>Modifiy</th></tr>";
			String query = "select * from docschedule";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			// iterate through the rows in the result set
			while (rs.next()) {
				String id = rs.getString("ID");
				String sid = rs.getString("scheduleID");
				// String hid = rs.getString("HospitalID");
				String hn = rs.getString("HospitalName");
				// String did = rs.getString("DoctorID");
				String dn = rs.getString("DoctorName");
				String sp = rs.getString("Speciality");
				String da = rs.getString("Date");
				String st = rs.getString("StartTime");
				String en = rs.getString("EndTime");
				String rm = rs.getString("RoomNumber");
				String sts = rs.getString("Status");

				// Add into the html table
				output += "<tr><td>" + sid + "</td>";
				output += "<td>" + hn + "</td>";
				output += "<td>" + dn + "</td>";
				output += "<td>" + sp + "</td>";
				output += "<td>" + da + "</td>";
				output += "<td>" + st + "</td>";
				output += "<td>" + en + "</td>";
				output += "<td>" + rm + "</td>";
				output += "<td>" + sts + "</td>";
				/*
				 * output += "<td>" + rm + "</td>"; output += "<td>" + sts + "</td>";
				 */
				// buttons
				output += "<td><input name=\"btnRemove\" "
						/* + " type=\"button\" value=\"Update\"></td>" */
						/* + "<td><form method=\"post\" action=\"TimeCollector.jsp\">" */
						/* + "<input name=\"btnRemove\" " */
						+ " type=\"submit\" value=\"Update\" action=\"TimeCollector.jsp\">"
						+ "<input name=\"id\" type=\"hidden\" " + " value=\"" + id + "\">" + "</form></td></tr>";
			}
			con.close();
			// Complete the html table
			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading the items.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	
	
	
	
	
	public String updateTest(int id,String status) {
		String output = "";

		try {
			Connection con = obj.connect();
			if (con == null) {

				return "Error while connecting to the database for updating.";
			}

			// create a prepared statement

			String query = "UPDATE docschedule SET Status=? WHERE ID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values

			
			
			  preparedStmt.setString(1, status);
			  preparedStmt.setInt(2, id);
			// execute the statement
			preparedStmt.execute();
			con.close();
			
			output = "Updated successfully";

		} catch (Exception e)
		{
			output = "Error while updating the item.";
			System.err.println(e.getMessage());
		}
		return output;
	}
	
	
	
	
	

}
