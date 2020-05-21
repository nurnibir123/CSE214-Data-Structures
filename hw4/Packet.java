/*Nur Nibir
 * 111059470
 * nur.nibir@stonybrook.edu
 * HW #4
 * CSE 214 R08
*/
package cse214;

public class Packet {
	private static int packetCount;
	private int id;
	private int packetSize;
	private int timeArrive;
	private int timeToDest = packetSize / 100;
	
	/**
	 * Getter method for the packetCount
	 * @return
	 * 	packetCount
	 */
	public int getPacketCount() {
		return this.packetCount;
	}
	
	/**
	 * Setter method for packetCount
	 * @param packetCount
	 * 	the int to set packetCount to
	 */
	public void setPacketCount(int packetCount) {
		this.packetCount = packetCount;
	}
	
	/**
	 * Getter method for id
	 * @return
	 * 	id
	 */
	public int getId() {
		return this.id;
	}
	
	/**
	 * Setter method for id
	 * @param id
	 * 	the int to set id to
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * Getter method for packetSize
	 * @return
	 * 	packetSize
	 */
	public int getPacketSize() {
		return this.packetSize;
	}
	
	/**
	 * Setter method for packetSize
	 * @param packetSize
	 * 	the int to set packetSize to
	 */
	public void setPacketSize(int packetSize) {
		this.packetSize = packetSize;
	}
	
	/**
	 * Getter method for timeArrive
	 * @return
	 * 	timeArrive
	 */
	public int getTimeArrive() {
		return this.timeArrive;
	}
	
	/**
	 * Setter method for timeArrive
	 * @param timeArrive
	 * 	the int to set timeArrive to
	 */
	public void setTimeArrive(int timeArrive) {
		this.timeArrive = timeArrive;
	}
	
	/**
	 * Getter method for timeToDest
	 * @return
	 * 	timeToDest
	 */
	public int getTimeToDest() {
		return this.timeToDest;
	}
	
	/**
	 * Setter method for timeToDest
	 * @param timeToDest
	 * 	the int to set timeToDest
	 */
	public void setTimeToDest(int timeToDest) {
		this.timeToDest = timeToDest;
	}
	
	/**
	 * The toString method for a Packet object
	 * 	
	 */
	public String toString() {
		return  "[" + this.id + ", " + this.timeArrive + ", " + this.timeToDest + "]";
	}
	
	
	
}
