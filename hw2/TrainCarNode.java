/*Nur Nibir
 * 111059470
 * nur.nibir@stonybrook.edu
 * HW #2
 * CSE 214 R08
*/
package cse214;

public class TrainCarNode {
	
	private TrainCarNode prev;
	private TrainCarNode next;
	private TrainCar car;
	
	/**
	 * Empty constructor for TrainCarNode object
	 */
	public TrainCarNode() {
	}
	
	/**
	 * Constructor with parameters for TrainCarNode object
	 * @param car
	 * 	What to set the car variable to
	 */
	public TrainCarNode(TrainCar car) {
		this.car = car;
	}
	
	/**
	 * 
	 * @return
	 * 	Returns the previous TrainCarNode object
	 */
	public TrainCarNode getPrev() {
		return this.prev;
	}
	
	/**
	 * Sets the previous TrainCarNode object
	 * @param prev
	 * 	What to set the prev variable to
	 */
	public void setPrev(TrainCarNode prev) {
		this.prev = prev;
	}
	
	/**
	 * 
	 * @return
	 *	Returns the next TrainCarNode object
	 */
	public TrainCarNode getNext() {
		return this.next;
	}
	
	/**
	 * Sets the next TrainCarNode object
	 * @param next
	 * 	What to set the next variable to 
	 */
	public void setNext(TrainCarNode next) {
		this.next = next;
	}
	
	/**
	 * 
	 * @return
	 * 	Returns the train car object
	 */
	public TrainCar getCar() {
		return this.car;
	}
	
	/**
	 * Sets the car variable of the TrainCarNode object
	 * @param car
	 * 	
	 */
	public void setCar(TrainCar car) {
		this.car = car;
	}
	
	
	public String toString() {
		return null;
	}
}

