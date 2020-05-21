package cse214;

public class Planner implements Cloneable{
	private final static int MAX_COURSES = 50;
	private Course[] courses;
	public static int count;
	
	/**
	 * Constructs an instance of the Planner with no Course objects in it
	 */
	public Planner() {
		this.courses = new Course[MAX_COURSES];
	}
	
	public Planner(Course[] courses, int count) {
		this.courses = courses;
		this.count = count;
	}
	
	/**
	 * Determines the number of courses currently in the list.
	 * @return
	 * 	The number of Courses in this Planner.
	 */
	public int size() {
		return count;
	}
	
	/**
	 * Adds a new Course object to the Planner at a specified position
	 * @param newCourse
	 * 	a new Course object that will be added to the courses array
	 * @param position
	 * 	the position the new Course object will be added
	 * @throws FullPlannerException
	 * 	Indicates that position is not within the valid range.
	 */
	public void addCourse(Course newCourse, int position) throws FullPlannerException {
		if(position >= 1  && size()<MAX_COURSES && courses[position-1] == null) {
			courses[position-1] = newCourse;
			count++;
		}
		else if(courses[position-1] instanceof Course){
			if(this.size() == 50)
				throw new FullPlannerException("Planner is full");
			
			for(int i = size()-1; i>= position-1; i--) {
				courses[i+1] = courses[i];
			}
			
		courses[position-1] = newCourse;
		count++;
		
		} else {
			throw new IllegalArgumentException("Invalid position");
		}
		 
	}
	
	/**
	 * Adds a new Course object to the end of the Planner 
	 * @param newCourse
	 * 	a new Course object that will be added to the end of the courses array
	 */
	public void addCourse(Course newCourse) {
		try {
			
			this.addCourse(newCourse, this.size());
		
		} catch (FullPlannerException e) {
			
			e.printStackTrace();
		}
	}
	
	/**
	 * The Course at the desired position will be removed. 
	 * @param position
	 * 	the position in the Planner where the Course will be removed from
	 */
	public void removeCourse(int position) {
		if(position >= 1 && position <= MAX_COURSES) {
			courses[position-1] = null;
	
			for(int i = position; i<count; i++) {
				courses[i-1] = courses[i];
			} 
			count--;
		} else 
			throw new IllegalArgumentException("Invalid position");
	}
	
	/**
	 * Returns the Course object at the specified position
	 * @param position
	 * 	position of the Course to retrieve
	 * @return
	 * 	The Course at the specified position in this Planner object
	 * @throws IllegalArgumentException
	 * 	Indicates that position is not within the valid range
	 */
	public Course getCourse(int position) {
		if(position >= 1 && position <= MAX_COURSES) 
			return courses[position-1];
		else
			throw new IllegalArgumentException("Invalid position");
	}
	
	/**
	 * Prints all Courses that are within the specified department
	 * @param planner
	 * 	 the list of courses to search in
	 * @param department
	 * 	the 3 letter department code for a Course
	 */
	public static void filter(Planner planner, String department) {
		System.out.printf("%-21s%-26s%19s%6s%9s%13s","No.","Course Name", "Department", "Code", "Section", "Instructor");
		System.out.println("\n--------------------------------------------------------------------------------------");
		for(int i = 0; i<planner.size(); i++) {
			if(planner.courses[i].getDepartment().equalsIgnoreCase(department)) {
				System.out.printf("%-21s%-26s%19s%6d%9s%13s", i+1, 
						planner.courses[i].getName(), 
						planner.courses[i].getDepartment(), 
						planner.courses[i].getCode(),
						planner.courses[i].getSection(),
						planner.courses[i].getInstructor() + "\n");
			}
		}
	}
	
	/**
	 * Checks whether a certain Course is already in the list
	 * @param course
	 * 	the Course we are looking for
	 * @return
	 * 	True if the Planner contains this Course, false otherwise
	 */
	public boolean exists(Course course) {
		for(int i = 0; i< this.size();i++) {
			if(this.courses[i].equals(course)) 
				return true;
		}
		return false;
	}

	/**
	 * Creates a copy of this Planner. Subsequent changes to the copy will not affect the original and vice versa
	 * @return
	 * 	A copy (backup) of this Planner object
	 */
	public Object clone() {
		Planner backup = new Planner(this.courses.clone(),count);
		
		return backup;
	}
	
	/**
	 * Prints a neatly formatted table of each item in the list with its position number
	 */
	public void printAllCourses() {
		System.out.printf("%-21s%-26s%19s%6s%9s%13s","No.","Course Name", "Department", "Code", "Section", "Instructor");
		System.out.println("\n------------------------------------------------------------------------------------------");
		for(int i = 0; i<this.size(); i++)
			System.out.printf("%-21s%-26s%19s%6d%9s%13s", i+1, 
							this.courses[i].getName(), 
							this.courses[i].getDepartment(), 
							this.courses[i].getCode(),
							this.courses[i].getSection(),
							this.courses[i].getInstructor() + "\n");
	}
	
	public String toString() {
		return null;
	}
	
	
}

