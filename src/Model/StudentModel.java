// Name: John O'Callaghan
// Student ID: R00200243


package Model;

import java.time.LocalDate;


public class StudentModel { // this is the class for creating a student model object

	int id;
	NameModel name;
	LocalDate dateofbirth;
	ModuleModel module;

	
	public StudentModel() { // this is the constructor for the variables of the student
		super();
		this.id = 0;
		this.name = null;
		this.dateofbirth = null;
		this.module = null;
	}


	public StudentModel(int id, NameModel name, LocalDate dateofbirth, ModuleModel module) { // this is the constructor for the student model
		super();
		this.id = id;
		this.name = name;
		this.dateofbirth = dateofbirth;
		this.module = module;
	}


	public int getId() { // this method returns the student id
		return id;
	}


	public void setId(int id) { // this method sets the student id
		this.id = id;
	}


	public NameModel getName() { // this method returns the name model
		return name;
	}


	public void setName(NameModel name) { // this method sets the name model
		this.name = name;
	}


	public LocalDate getDateofbirth() { // this method returns the date of birth
		return dateofbirth;
	}


	public void setDateofbirth(LocalDate dateofbirth) { // this method sets the date of birth
		this.dateofbirth = dateofbirth;
	}


	public ModuleModel getModule() { // this method returns the module
		return module;
	}


	public void setModule(ModuleModel module) { // this method sets the module
		this.module = module;
	}


	public boolean equals(Object obj) { // this method returns true if the objects are equal
		// TODO Auto-generated method stub
		return super.equals(obj);
	}

}
