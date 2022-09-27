// Name: John O'Callaghan
// Student ID: R00200243


package Controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import Model.StudentModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DAOController { // this class is for interacting with the database class and performing operations with the database

	private ResultSet rs; // variable to store records gotten from the database
	private ObservableList<ObservableList> data; // list to store the records gotten from the rs variable
	private DatabaseSetUp db; // this is to use the database class's methods for interacting with the database


	public DAOController() { // constructor
		db = new DatabaseSetUp();
	}


	public ObservableList<ObservableList> getAllData() throws SQLException { // this method is for getting all the record from the database table
		data = FXCollections.observableArrayList();

		
		String query = "Select * from Students";
		rs = db.executeQuery(query);

		
		while (rs.next()) { // the data gets added to the observable list
			ObservableList<String> row = FXCollections.observableArrayList(); // Iterate Row
			for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) { // Iterate Column
				row.add(rs.getString(i));
			}
			System.out.println("Row [1] added " + row);
			data.add(row);

		}
		db.closeConnection();
		return data;
	}

	
	public ObservableList<ObservableList> getFilterData() throws SQLException { // this method is for getting the records where the grade was 70 or greater from the database
		data = FXCollections.observableArrayList();

		
		String query = "Select * from Students where grade>=70";
		rs = db.executeQuery(query);


		while (rs.next()) { // data gets added to the observable list
			ObservableList<String> row = FXCollections.observableArrayList(); // Iterate Row
			for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
				row.add(rs.getString(i)); // Iterate Column
			}
			System.out.println("Row [1] added " + row);
			data.add(row);

		}

		return data;
	}


	public ObservableList<ObservableList> getSearchData(int id) throws SQLException { // this method is for getting the records from the database wherein the id is equal to the given search id
		data = FXCollections.observableArrayList();

		String query = "Select * from Students where id=" + id;
		rs = db.executeQuery(query);


		while (rs.next()) { // data gets added to the observable list
			ObservableList<String> row = FXCollections.observableArrayList(); // Iterate Row
			for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
				row.add(rs.getString(i)); // Iterate Column
			}
			System.out.println("Row [1] added " + row);
			data.add(row);

		}

		return data;
	}


	public int AddStudent(StudentModel s) { // this method is for inserting a new student into the database
		int count = 0;

		int id = s.getId();
		String Fname = s.getName().getFirstName();
		String Mname = s.getName().getMiddleInitial();
		String Lname = s.getName().getLastName();
		LocalDate dateofbirth = s.getDateofbirth();
		String module = s.getModule().getModuleName();
		int grade = s.getModule().getModuleGrade();

		String query = "INSERT INTO students values (" + id + ",'" + Fname + "','" + Mname + "','" + Lname + "','"
				+ dateofbirth + "','" + module + "'," + grade + ")";

		System.out.println("This query will execute:" + query);
		try {
			count = db.executeUpdateQuery(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		db.closeConnection();
		return count;
	}


	public int DeleteStudent(int id) { // this method is for deleting a student from the database

		String query = "DELETE from Students where id=" + id;

		System.out.println("This query will execute:" + query);

		int count = 0;
		try {
			count = db.executeUpdateQuery(query);
			db.closeConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			db.closeConnection();
		}

		return count;
	}


	public int UpdateStudent(StudentModel s, int id_index) { // this method is for updating the records in the database
		int id = s.getId();
		String Fname = s.getName().getFirstName();
		String Mname = s.getName().getMiddleInitial();
		String Lname = s.getName().getLastName();
		LocalDate dateofbirth = s.getDateofbirth();
		String module = s.getModule().getModuleName();
		int grade = s.getModule().getModuleGrade();

		String query = "UPDATE students SET id=" + id + ",first_name='" + Fname + "',middle_name='" + Mname
				+ "',last_name='" + Lname + "',date_of_birth='" + dateofbirth + "',module='" + module + "',grade="
				+ grade + " where id=" + id_index;

		System.out.println("This query will execute:" + query);

		int count = 0;
		try {
			count = db.executeUpdateQuery(query);
			db.closeConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			db.closeConnection();
		}

		return count;
	}


	public int createTable() { // this method is for creating a table in the database
		String query = "Create Table Students (id int, first_name varchar(20),middle_name varchar(15),last_name varchar(25),date_of_birth date,module varchar(120),grade int)";
		int count = 0;
		try {
			count = db.executeUpdateQuery(query);
			db.closeConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			db.closeConnection();
		}

		return count;
	}
}
