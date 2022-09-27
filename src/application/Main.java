// Name: John O'Callaghan
// Student ID: R00200243


package application;


import java.sql.SQLException;
import java.time.LocalDate;
import Controller.DAOController;
import Model.ModuleModel;
import Model.NameModel;
import Model.StudentModel;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class Main extends Application {

	
	DAOController dao; // DAOController object to interact with controller class

	
	private ObservableList<ObservableList> data; // table data


	private VBox vbox_Main; // Layout using vBox
	private GridPane gridPane; // Layout using GridPane
	private TableView tableview; // Layout using TableView
	private HBox hbox; // Layout using hBox
	private HBox hbox_Filter;

	
	TableColumn col1; // this column is for the ID
	TableColumn col2; // this column is for the First Name
	TableColumn col3; // this column is for the Middle Name
	TableColumn col4; // this column is for the Last Name
	TableColumn col5; // this column is for the date of birth

	
	// Labels
	private Label firstNameLabel;
	private Label middleNameLabel;
	private Label lastNameLabel;
	private Label idLabel;
	private Label dateOfBirthLabel;
	private Label moduleLabel;
	private Label gradeLabel;
	private Label statusLabel;

	
	// TextFields
	private TextField txtFName;
	private TextField txtMName;
	private TextField txtLName;
	private TextField txtId;
	private TextField txtModule;
	private TextField txtGrade;
	private TextField txtSearch;

	
	// DatePicker
	private DatePicker dateDOB;

	
	// Buttons
	private Button btnAdd;
	private Button btnDelete;
	private Button btnUpdate;
	private Button btnQuit;
	private Button btnClear;
	private Button btnShowAll;
	private Button btnSearch;
	private Button btnFilter;

	
	public void start(Stage primaryStage) {
		try {
			dao = new DAOController(); 	// Initialize DAOController object
		    dao.createTable(); 	// Creating new table

		    
			// Layouts
			vbox_Main = new VBox();
			gridPane = new GridPane();
			hbox = new HBox();
			tableview = new TableView();
			hbox_Filter = new HBox();

			
			// Columns
			col1 = new TableColumn("ID");
			col2 = new TableColumn("First Name");
			col3 = new TableColumn("Middle Name");
			col4 = new TableColumn("Last Name");
			col5 = new TableColumn("Date Of Birth");

			
			// Labels
			idLabel = new Label("ID:");
		    firstNameLabel = new Label("First Name: ");
			middleNameLabel = new Label("Middle Name: ");
			lastNameLabel = new Label("Last Name: ");
			dateOfBirthLabel = new Label("Date Of Birth: ");
			moduleLabel = new Label("Module: ");
			gradeLabel = new Label("Grade: ");
			statusLabel = new Label("Status: ");

			
			// TextFields
			txtId = new TextField();
			txtFName = new TextField();
			txtMName = new TextField();
			txtLName = new TextField();
			dateDOB = new DatePicker();
			txtModule = new TextField();
			txtGrade = new TextField();
			txtSearch = new TextField();

			
			// Buttons
			btnAdd = new Button("Add");
			btnDelete = new Button("Delete");
			btnUpdate = new Button("Update");
			btnQuit = new Button("Quit");
			btnClear = new Button("Clear");
			btnShowAll = new Button("ShowAll");
			btnSearch = new Button("Search");
			btnFilter = new Button("Filter(70% Grades)");


			// GridPane settings
			gridPane.setMinSize(400, 200);
			gridPane.setPadding(new Insets(10, 10, 10, 10));
			gridPane.setVgap(5);
			gridPane.setHgap(5);

			
			// Adding everything into the grid
			gridPane.add(idLabel, 0, 1);
			gridPane.add(middleNameLabel, 0, 2);
			gridPane.add(dateOfBirthLabel, 0, 3);
			gridPane.add(gradeLabel, 0, 4);
			gridPane.add(txtId, 1, 1);
			gridPane.add(txtMName, 1, 2);
			gridPane.add(dateDOB, 1, 3);
			gridPane.add(txtGrade, 1, 4);
			gridPane.add(firstNameLabel, 2, 1);
			gridPane.add(lastNameLabel, 2, 2);
			gridPane.add(moduleLabel, 2, 3);
			gridPane.add(txtFName, 3, 1);
			gridPane.add(txtLName, 3, 2);
			gridPane.add(txtModule, 3, 3);

			
			vbox_Main.getChildren().add(gridPane); // Adding grid layout into main vertical box layout

			
			hbox.getChildren().addAll(btnAdd, btnDelete, btnUpdate, btnClear, btnShowAll, btnQuit); // Adding the buttons into the hBox Layout

			
			vbox_Main.getChildren().add(hbox); // Adding Horizontal layout into main vertical box layout

			
			hbox_Filter.getChildren().addAll(btnFilter, txtSearch, btnSearch); // Adding search/filter buttons into the Horizontal Layout 

			
			vbox_Main.getChildren().add(hbox_Filter); // Adding Horizontal layout into main vertical box layout

			
			txtSearch.setPromptText("Enter Student ID"); // to set the hint text
			
			
			tableview.getColumns().addAll(col1, col2, col3, col4, col5); // Adding columns into the table


			vbox_Main.getChildren().addAll(tableview, statusLabel); // Adding Table Layout into the main vertical box layout 


			btnAdd.setOnAction(e -> { // Add Button
				addStudent();
			});


			btnDelete.setOnAction(e -> { // Delete Button

			
				int selectedIndex = data.indexOf(tableview.getSelectionModel().getSelectedItem()); // for checking the selected index
				if (selectedIndex >= 0) {
					int s_id = Integer.parseInt(data.get(selectedIndex).get(0).toString());


					System.out.println("Removing ID:" + s_id); // removing from db
					dao.DeleteStudent(s_id);
				}});
			

			btnUpdate.setOnAction(e -> { // Update Button

				int selectedIndex = data.indexOf(tableview.getSelectionModel().getSelectedItem());
				if (selectedIndex >= 0) {
					int s_id = Integer.parseInt(data.get(selectedIndex).get(0).toString());
				}});

			
			btnSearch.setOnAction(e -> { // Search Button

				if (txtSearch.getText().toString() == "" || !txtSearch.getText().toString().matches("\\b[0-9]+\\b"))
					statusLabel.setText("Enter ID to Search: ");
				else {
					tableview.getItems().clear();
					int id = Integer.parseInt(txtSearch.getText().toString());
					searchData(id);
				}

			});

			
			btnQuit.setOnAction(e -> { // Quit Button
				Platform.exit();
			});

			
			btnClear.setOnAction(e -> { // Clear Button
				txtFName.setText("");
				txtMName.setText("");
				txtLName.setText("");
				txtId.setText("");
				dateDOB.setValue(null);
				txtModule.setText("");
				txtGrade.setText("");

			});

			
			btnShowAll.setOnAction(e -> { // show all button
				refreshTable();
			});

			
			btnFilter.setOnAction(e -> { // Filter Button
				tableview.getItems().clear();
				filterData();

			});


			refreshTable(); // refreshTable is supposed to update the table to get records from the database

			Scene scene = new Scene(vbox_Main, 500, 500);
			primaryStage.setScene(scene);
			primaryStage.setTitle("MTU Student Records");
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

	
	public int validateData() { // this method is for checking if the information given is valid before changing the table
		if (txtId.getText().toString() == "" || !txtId.getText().toString().matches("\\b[0-9]+\\b")) {
			statusLabel.setText("Invalid ID");
			return 0;
		} else if (txtFName.getText().toString() == "") {
			statusLabel.setText("Invalid First Name");
			return 0;
		} else if (txtMName.getText().toString() == "") {
			statusLabel.setText("Invalid Middle Name");
			return 1;
		} else if (txtLName.getText().toString() == "") {
			statusLabel.setText("Invalid Last Name");
			return 2;
		} else if (txtModule.getText().toString() == "") {
			statusLabel.setText("Invalid Module");
			return 3;
		} else if (txtGrade.getText().toString() == "" || !txtId.getText().toString().matches("\\b[0-9]+\\b")) {
			statusLabel.setText("Invalid Grade");
			return 4;
		} else if (dateDOB.valueProperty().get() == null) {
			statusLabel.setText("Invalid Date Of Birth");
			return 5;
		} else
			return 6;
	}

	
	public StudentModel generateStudent() { // this method is for generating the student object according to the given data
		NameModel name = new NameModel(txtFName.getText().toString(), txtMName.getText().toString(),
				txtLName.getText().toString());
		ModuleModel module = new ModuleModel(txtModule.getText().toString(),
				Integer.parseInt(txtGrade.getText().toString()));
		LocalDate d = dateDOB.valueProperty().get();

		StudentModel s = new StudentModel();
		s.setId(Integer.parseInt(txtId.getText().toString()));
		s.setName(name);
		s.setModule(module);
		s.setDateofbirth(d);

		return s;
	}


	public void updateData(int id) { // this method will validate the given data and update the table 
		if (validateData() == 6) {
			StudentModel s = generateStudent();
			dao.UpdateStudent(s, id);
			refreshTable();
			statusLabel.setText("Record Updated Successfully");
		}
	}


	public void addStudent() { // this method will validate the given data for a new student entry and then add it to the database
		if (validateData() == 6) {
			StudentModel s = generateStudent();

			if (!exist(s.getId())) {
				dao.AddStudent(s);
				refreshTable();
				statusLabel.setText("Record Added Successfully");
			} else
				statusLabel.setText("This ID already exists in the database");

		}

	}


	public boolean exist(int id) { // this method will check if the new student id already exists in the database
		for (int i = 0; i < data.size(); i++) {
			if (Integer.parseInt(data.get(i).get(0).toString()) == id)
				return true;
		}
		return false;
	}

	public void refreshTable() { // this method is to refresh the table 

		try {
			data = dao.getAllData();
			tableview.setItems(data);
			statusLabel.setText("Status: " + data.size() + " Record(s) Found");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	public void filterData() { // this method will filter the data 
		try {
			data = dao.getFilterData();
			tableview.setItems(data);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	
	public void searchData(int id) { // this method will return the data of a given id
		try {
			data = dao.getSearchData(id);
			tableview.setItems(data);
			statusLabel.setText("Status: " + data.size() + " Record founded");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
