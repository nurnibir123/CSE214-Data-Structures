package cse214;

public class Course implements Cloneable{
	private String name;
	private String department;
	private int code;
	private byte section;
	private String instructor;
	
	/**
	 * A getter method for the name variable
	 * @return 
	 * 	Returns the name variable of a Course object
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * A setter method for the name variable
	 * @param name
	 *	a String value to set the name variable to
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * A getter method for the department variable
	 * @return
	 * 	Returns the department variable of a Course object
	 */
	public String getDepartment() {
		return this.department;
	}
	
	/**
	 * A setter method for the department variable
	 * @param department
	 *	a String value to set the department variable to	
	 */
	public void setDepartment(String department) {
		this.department = department;
	}
	
	/**
	 * A getter method for the code variable
	 * @return
	 * 	Returns the code variable of a Course object
	 */
	public int getCode() {
		return this.code;
	}
	
	/**
	 * A setter method for the code variable 
	 * @param code
	 * 	an integer value to set the code variable to
	 */
	public void setCode(int code) {
		if(code < 0)
			throw new IllegalArgumentException("Invalid Code");
		else 
			this.code = code;
	}
	
	/**
	 * A getter method for the section variable
	 * @return
	 * 	Returns the section variable of a Course object
	 */
	public byte getSection() {
		return this.section;
	}
	
	/**
	 * A setter method for the section variable
	 * @param section
	 * 	a byte value to set the section variable to
	 */
	public void setSection(byte section) {
		if(section < 0)
			throw new IllegalArgumentException("Invalid Section");
		this.section = section;
	}
	
	/**
	 * A getter method for the instructor variable
	 * @return
	 * 	Returns the instructor variable of a Course object
	 */
	public String getInstructor() {
		return this.instructor;
	}
	
	/**
	 * A setter method for the instructor variable
	 * @param instructor
	 * 	a String value to set the instructor variable to
	 */
	public void setInstructor(String instructor) {
		this.instructor = instructor;
	}
	
	/**
	 * A constructor with parameters for a Course object
	 * @param name
	 * @param department
	 * @param code
	 * @param section
	 * @param instructor
	 */
	public Course(String name, String department, int code, byte section, String instructor){
		this.name = name;
		this.department = department;
		this.code = code;
		this.section = section;
		this.instructor = instructor;
	}
	
	/**
	 * A constructor with no parameters for a Course Object
	 */
	public Course() {
	}
	
	/**
	 * Clones a Course object
	 * @throws CloneNotSupportedException
	 * 	Indicates that this object cannot be cloned
	 */
	public Object clone() throws CloneNotSupportedException {
		return (Course)super.clone();
	}
	
	/**
	 * Compares two Course objects, returns true if they are equal and false otherwise
	 * @param obj
	 * 	an object 
	 * @return
	 * 	Returns true if the two Course objects are equivalent and false otherwise
	 */
	public boolean equals(Object obj){
		if(obj instanceof Course) 
			return this.name.equalsIgnoreCase(((Course)obj).name)
				&& this.department.equalsIgnoreCase(((Course)obj).department)
				&& this.code == ((Course)obj).code 
				&& this.section == ((Course)obj).section 
				&& this.instructor.equalsIgnoreCase(((Course)obj).instructor);
		else
			return false;
	}
	
}
