/*Nur Nibir
 * 111059470
 * nur.nibir@stonybrook.edu
 * HW #2
 * CSE 214 R08
*/
package cse214;

public class TrainLinkedList {
	private TrainCarNode head;
	private TrainCarNode tail;
	private TrainCarNode cursor;
	private int count;
	private double totalLength;
	private double totalValue;
	private double totalWeight;
	private int dangerousCount;
	
	
	public void setTotalValue(double totalValue) {
		this.totalValue += totalValue;
	}
	public void setTotalWeight(double totalWeight) {
		this.totalWeight += totalWeight;
	}
	public void setDangerousCount(int dangerousCount) {
		this.dangerousCount += dangerousCount;
	}
	
	/**
	 * Constructs an instance of the TrainLinkedList with no TrainCar objects in it.	
	 */
	public TrainLinkedList() {
	}
	
	/**
	 * Returns a reference to the TrainCar at the node currently referenced by the cursor.
	 * @return
	 * 	The reference to the TrainCar at the node currently referenced by the cursor.
	 */
	public TrainCar getCursorData() {
		if(cursor != null)
			return cursor.getCar();
		
		return null;		
	}
	
	/**
	 * Places car in the node currently referenced by the cursor.
	 * @param car
	 * 	A TrainCar object to set the cursor data to
	 */
	public void setCursorData(TrainCar car) {
		if(cursor != null) 
			this.cursor.setCar(car);
	}
	
	/**
	 * Moves the cursor to point at the next TrainCarNode.
	 */
	public void cursorForward() {
		if(cursor != null && cursor != this.tail) {
			cursor = cursor.getNext();
			System.out.print("Cursor has moved forward");
		} else 
			System.out.print("No next car, cannot move cursor forward.");
	}
	
	/**
	 * Moves the cursor to point at the previous TrainCarNode.
	 */
	public void cursorBackward() {
		if(cursor != null && cursor.equals(head) != true) {
			cursor = cursor.getPrev();
			System.out.print("Cursor has moved backward");
		} else
			System.out.print("No previous car, cannot move cursor backwards.");
	}
	
	/**
	 * Inserts a car into the train after the cursor position.
	 * @param newCar
	 * 	the new TrainCar to be inserted into the train.
	 */
	public void insertAfterCursor(TrainCar newCar) {
		TrainCarNode next = new TrainCarNode(newCar);

		if(newCar != null && head == null && cursor == null) {
			this.head = next;
			this.tail = next;
			cursor = next;
			count++;
			totalLength += newCar.getLength();
			totalWeight += newCar.getWeight();
			totalValue += newCar.getProductLoad().getValue();
			if(newCar.getProductLoad().isDangerous())
				dangerousCount++;
			
				
		} else if(newCar != null && head != null && head.equals(tail)) {
			head.setNext(next);
			next.setPrev(head);
			cursor = next;
			tail = next;
			count++;
			totalLength += newCar.getLength();
			totalWeight += newCar.getWeight() + newCar.getProductLoad().getWeight();
			totalValue += newCar.getProductLoad().getValue();
			if(newCar.getProductLoad().isDangerous())
				dangerousCount++;
			
		} else if(newCar != null && head != null && head.equals(tail) != true && cursor != head && cursor != tail) {
			next.setPrev(cursor);

			cursor.getNext().setPrev(next);
			cursor.setNext(next);
			cursor = next;
			count++;
			totalLength += newCar.getLength();
			totalWeight += newCar.getWeight() + newCar.getProductLoad().getWeight();
			totalValue += newCar.getProductLoad().getValue();
			if(newCar.getProductLoad().isDangerous())
				dangerousCount++;
			
		} else if(newCar != null && head != null && cursor.equals(tail)) {
			next.setPrev(tail);
			tail.setNext(next);
			cursor = next;
			tail = next;
			count++;
			totalLength += newCar.getLength();
			totalWeight += newCar.getWeight() + newCar.getProductLoad().getWeight();
			totalValue += newCar.getProductLoad().getValue();
			if(newCar.getProductLoad().isDangerous())
				dangerousCount++;
		
		} else if(newCar != null && cursor.equals(head) && cursor.getNext().equals(tail)) {
			next.setPrev(head);
			next.setNext(tail);
			head.setNext(next);
			tail.setPrev(next);
			cursor = next;
			count++;
			totalLength += newCar.getLength();
			totalWeight += newCar.getWeight() + newCar.getProductLoad().getWeight();
			totalValue += newCar.getProductLoad().getValue();
			if(newCar.getProductLoad().isDangerous())
				dangerousCount++;
		}
		
		
		
		else 
			throw new IllegalArgumentException("New TrainCar added cannot be null");
	}
	
	/**
	 * Removes the TrainCarNode referenced by the cursor and returns the TrainCar contained within the node.
	 * @return
	 * 	The TrainCar contained within the node
	 */
	public TrainCar removeCursor() {
		TrainCar removed = null;
		if(cursor != null && cursor != head && cursor != tail) {
			removed = cursor.getCar();
			TrainCarNode selection = cursor.getPrev();
			selection.setNext(selection.getNext().getNext());
			selection.getNext().setPrev(selection);
			count--;
			totalLength -= removed.getLength();
			totalWeight -= (removed.getWeight() + removed.getProductLoad().getWeight());
			totalValue -= removed.getProductLoad().getValue();
			if(removed.getProductLoad().isDangerous())
				dangerousCount--;
			
			if(selection.getNext() != null)
				cursor = selection.getNext();
			else
				cursor = selection;
			
			
			
			return removed;
			
		} else if(cursor.equals(head) && cursor.getNext() == null) {
			removed = cursor.getCar();
			cursor = null;
			head = null;
			count--;
			totalLength -= removed.getLength();
			totalWeight -= (removed.getWeight() + removed.getProductLoad().getWeight());
			totalValue -= removed.getProductLoad().getValue();
			if(removed.getProductLoad().isDangerous())
				dangerousCount--;
			
			return removed;
			
		} else if(cursor.equals(head) && cursor.getNext() != null) {
			removed = cursor.getCar();
			cursor = cursor.getNext();
			head = cursor;
			cursor.setPrev(null);
			
			count--;
			totalLength -= removed.getLength();
			totalWeight -= (removed.getWeight() + removed.getProductLoad().getWeight());
			totalValue -= removed.getProductLoad().getValue();
			if(removed.getProductLoad().isDangerous())
				dangerousCount--;
			
			return removed;
			
		}
		
		else if(cursor.equals(tail)) {
			removed = cursor.getCar();
			cursor = tail.getPrev();
			tail = tail.getPrev();
			tail.setNext(null);
			count--;
			totalLength -= removed.getLength();
			totalWeight -= removed.getWeight();
			totalValue += removed.getProductLoad().getValue();
			
			
			return removed;
		}
		
			return null;
	}
	
	
	/**
	 * Determines the number of TrainCar objects currently on the train.
	 * @return
	 * 	The number of TrainCar objects on this train.
	 */
	public int size() {
		return count;
	}
	
	/**
	 * Returns the total length of the train in meters.
	 * @return
	 * 	The sum of the lengths of each TrainCar in the train.
	 */
	public double getLength() {
		return totalLength;
	}
	
	/**
	 * Returns the total value of product carried by the train.
	 * @return
	 * 	The sum of the values of each TrainCar in the train.
	 */
	public double getValue() {
		return totalValue;
	}
	
	/**
	 * Returns the total weight in tons of the train. Note that the weight of the train is the sum of the weights of each empty TrainCar, plus the weight of the ProductLoad carried by that car.
	 * @return
	 * 	The sum of the weight of each TrainCar plus the sum of the ProductLoad carried by that car.
	 */
	public double getWeight() {
		return totalWeight;
	}
	
	/**
	 * Whether or not there is a dangerous product on one of the TrainCar objects on the train.
	 * @return
	 * 	Returns true if the train contains at least one TrainCar carrying a dangerous ProductLoad, false otherwise.
	 */
	public boolean isDangerous() {
		if(dangerousCount >= 1)
			return true;
		else
			return false;
	}
	
	/**
	 * Searches the list for all ProductLoad objects with the indicated name and sums together their weight and value
	 * @param name
	 * 	the name of the ProductLoad to find on the train.
	 */
	public void findProduct(String name) {
		TrainCarNode cursor;
		String productName = "";
		double totalValue = 0;
		double totalWeight = 0;
		int isDangerous = 0;
		String dangerous = "";
		boolean doesntExist = false;
		for(cursor = this.head;cursor != null; cursor = cursor.getNext()) {
			if(cursor.getCar().getProductLoad().getName().equalsIgnoreCase(name)) {
				doesntExist = false;
				productName = cursor.getCar().getProductLoad().getName();
				totalValue += cursor.getCar().getProductLoad().getValue();
				totalWeight += cursor.getCar().getProductLoad().getWeight();
				if(cursor.getCar().getProductLoad().isDangerous())
					isDangerous++;
				if(isDangerous >= 1)
					dangerous = "YES";
				else
					dangerous = "NO";
				
			} else if(cursor.getCar().getProductLoad().getName().equalsIgnoreCase(name) != true) 
				doesntExist = true;
		}
		
		if(!doesntExist) {
			System.out.println("The following products were found: ");
			System.out.printf("%-15s%-20s%-20s%-20s", "Corn", "Weight (t)", "Value ($)", "Dangerous");
			System.out.println();
			System.out.println("==================================================================");
			System.out.printf("%-15s%-20.1f%-20.2f%-20s", productName, 
									totalWeight, totalValue, dangerous);
		}
		
		else if(doesntExist)
			System.out.println("No record of " + name + " on board train");
	}
	
	/**
	 * Prints a neatly formatted table of the car number, car length, car weight, load name, load weight, load value, and load dangerousness for all of the car on the train.
	 */
	public void printManifest() {
		TrainCarNode cursor;
	
		System.out.println("CAR: \t\t\t\t\t\t\t\t LOAD: ");
		System.out.printf("%-10s%-26s%19s%10s%-20s%-22s%-20s%-20s", "Num", "Length (m)", "Weight (t)", "|", "Name", "Weight (t)", "Value ($)", "Dangerous");
		System.out.println("\n================================================================+=======================================================================");
		
		int i = 1;
		for(cursor = head; cursor != null; cursor = cursor.getNext()) {
		
			String current = "-> " +  i;
			String dangerous = "";
			if(cursor.getCar().getProductLoad().isDangerous())
				dangerous = "YES";
			else 
				dangerous = "NO";
			
			if(cursor.equals(this.cursor)) {
				System.out.printf("%-10s%-26.1f%19.1f%10s%-20s%-22.1f%-20.2f%-20s", current,cursor.getCar().getLength(),
																cursor.getCar().getWeight(),
																"|",
																cursor.getCar().getProductLoad().getName(),
																cursor.getCar().getProductLoad().getWeight(),
																cursor.getCar().getProductLoad().getValue(),
																dangerous);
				System.out.println();
				i++;
			
			}
			
			else {
				System.out.printf("%-10d%-26.1f%19.1f%10s%-20s%-22.1f%-20.2f%-20s",i,cursor.getCar().getLength(),
						cursor.getCar().getWeight(),
						"|",
						cursor.getCar().getProductLoad().getName(),
						cursor.getCar().getProductLoad().getWeight(),
						cursor.getCar().getProductLoad().getValue(),
						dangerous);
			
			System.out.println();
			i++;
			
			}
		}
	}
	
	/**
	 * Removes all dangerous cars from the train, maintaining the order of the cars in the train.
	 */
	public void removeDangerousCars() {
		TrainCarNode cursor;
		
		for(cursor = head; cursor != null; cursor = cursor.getNext()) {
			if(cursor.getCar().getProductLoad().isDangerous()) {
				this.cursor = cursor;
				this.removeCursor();
			}
				
		}
		
	}
	
	/**
	 * Returns a neatly formatted String representation of the train.
	 */
	public String toString() {
		String dangerous = "";
		if(dangerousCount >= 1)
			dangerous = "is dangerous";
		else 
			dangerous = "not dangerous";
		return "Train: " + count + " cars, " 
						 + totalLength + " meters, "
						 + totalWeight + " tons, "
						 + " $" + totalValue + " value, "
						 + dangerous;
	}
	
}
