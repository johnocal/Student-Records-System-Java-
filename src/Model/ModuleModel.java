// Name: John O'Callaghan
// Student ID: R00200243


package Model;

public class ModuleModel { // this class is for the Module Object

	String moduleName;
	int moduleGrade;

	
	public ModuleModel(String moduleName, int moduleGrade) { // this is the constructor for the name and grade
		super();
		this.moduleName = moduleName;
		this.moduleGrade = moduleGrade;
	}


	public String getModuleName() { // this method returns the module name
		return moduleName;
	}


	public void setModuleName(String moduleName) { // this method is to set the value of the module name
		this.moduleName = moduleName;
	}


	public int getModuleGrade() { // this method is to return the module grade
		return moduleGrade;
	}


	public void setModuleGrade(int moduleGrade) { // this method is to set the value of the module grade
		this.moduleGrade = moduleGrade;
	}


	
	public boolean equals(Object obj) { // this method is to compare modules
		// TODO Auto-generated method stub
		return super.equals(obj);
	}

}
