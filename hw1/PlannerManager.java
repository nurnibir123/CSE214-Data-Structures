package cse214;
import java.util.Scanner;
public class PlannerManager {
	
	/**
	 * The main method runs a menu driven application which first creates an empty Planner object. The program prompts the user for a command to execute an operation. Once a command has been chosen, the program may ask the user for additional information if necessary, and performs the operation.
	 * @param args
	 */
	public static void main(String[] args) {
		Planner planner = new Planner();

		Planner backup = new Planner();
		Scanner input = new Scanner(System.in);
		
		String option;
		
		do{
			
		System.out.print("\n(A) Add Course\n" + 
				"(G) Get Course\n" + 
				"(R) Remove Course\n" + 
				"(P) Print Courses in Planner\n" + 
				"(F) Filter by Department Code\n" + 
				"(L) Look For Course\n" + 
				"(S) Size\n" + 
				"(B) Backup\n" + 
				"(PB) Print Courses in Backup\n" + 
				"(RB) Revert to Backup\n" + 
				"(Q) Quit\n\n" + 
				"Enter a selection: ");
		
		option = input.next();
		
		
		
		switch(option) {
			case "A": 
				input.nextLine();
				Course newCourse = new Course();
				System.out.print("Enter course name: ");
				newCourse.setName(input.nextLine()); 
				
				System.out.print("Enter department: ");
				newCourse.setDepartment(input.nextLine()); 
				
				System.out.print("Enter course code: ");
				newCourse.setCode(input.nextInt()); 
				
				System.out.print("Enter course section: ");
				newCourse.setSection(input.nextByte());
				
				input.nextLine();
				System.out.print("Enter instructor: ");
				
				newCourse.setInstructor(input.nextLine()); 
				
				System.out.print("Enter position: ");
				int position = input.nextInt();
				
				System.out.println();
				
			try {
				planner.addCourse(newCourse, position);
			} catch (FullPlannerException e) {
				e.printStackTrace();
			} 
			System.out.println(newCourse.getDepartment() + " " + newCourse.getCode() + "." + 
								newCourse.getSection() + " successfully added to planner");
			break;
			
			case "G":
				System.out.print("Enter position: ");
				int p = input.nextInt();
				System.out.printf("%-21s%-26s%19s%6s%9s%13s","No.","Course Name", "Department", "Code", "Section", "Instructor");
				System.out.println("\n---------------------------------------------------------------------------------------------------");
				System.out.printf("%-21s%-26s%19s%6d%9s%13s",p,
													planner.getCourse(p).getName(),
													planner.getCourse(p).getDepartment(),
													planner.getCourse(p).getCode(),
													planner.getCourse(p).getSection(),
													planner.getCourse(p).getInstructor());
				break;
			
			case "R":
				System.out.print("Enter position: ");
				int p1 = input.nextInt();
				System.out.println(planner.getCourse(p1).getDepartment() + " " 
									+ planner.getCourse(p1).getCode() 
									+ " has been successfully removed from the planner");
				planner.removeCourse(p1);
				System.out.println();
				break;
				
			case "P":
				planner.printAllCourses(); break;
				
			case "F":
				System.out.print("Enter department code: ");
				input.nextLine();
				String department = input.nextLine();
				planner.filter(planner, department); 
				System.out.println(); break;
				
				
			case "L": 
				Course searched = new Course();
				input.nextLine();
				System.out.print("Enter course name: ");
				searched.setName(input.nextLine()); 
				System.out.print("Enter department: ");
				searched.setDepartment(input.nextLine());
				System.out.print("Enter course code: ");
				searched.setCode(input.nextInt()); 
				System.out.print("Enter course section: ");
				searched.setSection(input.nextByte());
				input.nextLine();
				System.out.print("Enter instructor: ");
				searched.setInstructor(input.nextLine());
				
				
				
				if(planner.exists(searched))
					System.out.println(searched.getDepartment() + " "
										+ searched.getCode() + "." 
										+ searched.getSection() 
										+ " is found in the planner at position "  );
				else
					System.out.println(searched.getDepartment() + " "
							+ searched.getCode() + "." 
							+ searched.getSection() 
							+ " cannot be found in the planner" );
				break;	
				
			case "S":
				System.out.print("There are " + planner.size() + " courses in the planner."); 
				System.out.println(); break;
				
			case "B":
			
			backup = (Planner) planner.clone();
				System.out.println("Created a backup of the current planner"); break;
				
			case "PB":
				
				backup.printAllCourses(); break;
				
			case "RB":
				
			planner = (Planner) backup.clone(); 
				System.out.println("Planner successfully reverted to the backup copy."); break;
				
				
			case "Q":
				System.out.println("Program terminating successfully...");
				System.exit(0); break;
			}
		
		} while(option != "Q");
		
		}
}
