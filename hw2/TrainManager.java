/*Nur Nibir
 * 111059470
 * nur.nibir@stonybrook.edu
 * HW #2
 * CSE 214 R08
*/
package cse214;
import java.util.Scanner;

public class TrainManager {
	
	/**
	 * The main method of the TrainManager class creates an empty TrainLinkedList and then prompts the user to input a selection from a number of options
	 * @param args
	 */
	public static void main(String[] args) {
		TrainLinkedList list = new TrainLinkedList();
			
		Scanner input = new Scanner(System.in);
		
		String option;
		
		do {
			
			
			System.out.print("\n(F) - Move Cursor Forward\n" +
							   "(B) - Move Cursor Backward\n" +
							   "(I) - Insert Car After Cursor\n" +
							   "(R) - Remove Car At Cursor\n" +
							   "(L) - Set Product Load\n" +
							   "(S) - Search For Product\n" +
							   "(T) - Display Train\n" +
							   "(M) - Display Manifest\n" + 
							   "(D) - Remove Dangerous Cars\n" +
							   "(Q) - Quit\n\n" + 
							   "Enter a selection: ");
			
			option = input.next();
			
			switch(option) {
				
				case "F": list.cursorForward(); System.out.println(); break;
				
				case "B": list.cursorBackward(); System.out.println(); break;
				
				case "I" :
					System.out.print("Enter car length in meters: ");
					double carLength = input.nextDouble();
					System.out.print("Enter car weight in tons: ");
					double carWeight = input.nextDouble();
					TrainCar trainCar = new TrainCar(carLength, carWeight);
					
					list.insertAfterCursor(trainCar);
		
					System.out.println("New train car " + carLength + " meters "
									+ carWeight + " tons inserted into train\n"); break;
					
				case "R": 
					TrainCar removedCar = list.removeCursor(); 
					System.out.print("Car successfully unlinked. "
							+ "The following load has been removed from the train: ");
					System.out.println(); 
					System.out.printf("%-20s%-17s%-18s%-20s", "Name", "Weight(t)", "Value($)","Dangerous");
					System.out.println();
					System.out.println("=================================================================");
					System.out.printf("%-20s%-17.1f%-18.2f%-20s", removedCar.getProductLoad().getName(),
																  removedCar.getProductLoad().getWeight(),
																  removedCar.getProductLoad().getValue(),
																  removedCar.getProductLoad().isDangerous());
					System.out.println();
					
					break;
						
		
				case "L": 
					ProductLoad newProduct = new ProductLoad();
					System.out.print("Enter produce name: ");
					input.nextLine();
					newProduct.setName(input.nextLine());
					
					System.out.print("Enter product weight in tons: ");
					newProduct.setWeight(input.nextDouble());
					
					System.out.print("Enter product value in dollars: ");
					newProduct.setValue(input.nextDouble());
					
					System.out.print("Enter is product dangerous? (y/n): ");
					if(input.next().equals("y"))
						newProduct.setIsDangerous(true);
					else
						newProduct.setIsDangerous(false);
					
					list.getCursorData().setProductLoad(newProduct);
					list.setTotalValue(newProduct.getValue());
					list.setTotalWeight(newProduct.getWeight());
					
					if(newProduct.isDangerous())
						list.setDangerousCount(1);
					System.out.println();
					System.out.println(newProduct.getWeight() + " tons of " 
									 + newProduct.getName() + " added to the current car");
					
					break;
					
				case "S":
					System.out.print("Enter name of product you are searching for: ");
					input.nextLine();
					String productSearch = input.nextLine();
					list.findProduct(productSearch); break;
					
					
				case "T":
					System.out.println(list.toString()); break;
					
				case "M":
					list.printManifest(); break;
					
				case "D":
					list.removeDangerousCars(); 
					System.out.println("Dangerous cars successfully removed from the train."); break;
				
				case "Q":
					System.out.println("Program terminating successfully...");
					System.exit(1); break;
					
			}
				
		
				
			
		} while (option.equals("Q") != true);

	}

}
