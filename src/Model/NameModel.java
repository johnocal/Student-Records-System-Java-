// Name: John O'Callaghan
// Student ID: R00200243


package Model;

public class NameModel { // this is the class for the name object

	String FirstName;
	String MiddleInitial;
	String LastName;


	public NameModel(String firstName, String middleInitial, String lastName) { // this is the constructor for the names
		super();
		FirstName = firstName;
		MiddleInitial = middleInitial;
		LastName = lastName;
	}


	public String getFullname() { // this method returns the combined full name
		if (LastName == null && MiddleInitial == null)
			return FirstName;
		else if (LastName != null && MiddleInitial != null)
			return FirstName + " " + MiddleInitial + " " + LastName;
		else if (MiddleInitial == null)
			return FirstName + " " + LastName;
		else
			return FirstName + " " + MiddleInitial;

	}


	public String getFirstName() { // this method returns just the first name
		return FirstName;
	}


	public void setFirstName(String firstName) { // this method is to set the first name 
		FirstName = firstName;
	}


	public String getMiddleInitial() { // this method is to return the initial of the middle name
		return MiddleInitial;
	}


	public void setMiddleInitial(String middleInitial) { // this method is to set the middle name
		MiddleInitial = middleInitial;
	}


	public String getLastName() { // this method is to return the last name
		return LastName;
	}


	public void setLastName(String lastName) { // this method is to set the last name
		LastName = lastName;
	}


	public boolean equals(Object obj) { // this method is to check whether the objects are equal or not
		// TODO Auto-generated method stub
		return super.equals(obj);
	}

}
