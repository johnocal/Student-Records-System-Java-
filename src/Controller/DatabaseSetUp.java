// Name: John O'Callaghan
// Student ID: R00200243


package Controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class DatabaseSetUp { // this class is for connecting and interacting with the Derby Database
	String URL = "jdbc:derby:MTUDB;create=true";
	Connection conn;
	Statement st;
	ResultSet rs = null;


	private void startConnection() { // this method is for opening the connection to the database
		try {
			conn = DriverManager.getConnection(URL);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	public void closeConnection() { //  this method is for closing the connection to the database
		try {
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	public int executeUpdateQuery(String query) throws SQLException { // this method is for returning the records after using the table operations 
		startConnection();
		st = conn.createStatement();
		return st.executeUpdate(query);
	}


	public ResultSet executeQuery(String query) throws SQLException { // this method is for selecting all records from the table
		startConnection();
		st = conn.createStatement();
		rs = st.executeQuery(query);
		return rs;
	}
}
