/*Nur Nibir
 * 111059470
 * nur.nibir@stonybrook.edu
 * HW #3
 * CSE 214 R08
*/
package cse214;

public class Complexity {
	private int nPower;
	private int logPower;
	
	/**
	 * Empty Constructor for a Complexity object
	 */
	public Complexity() {
	}
	
	/**
	 * Getter method for the nPower variable
	 * @return
	 * 	nPower
	 */
	public int getNPower() {
		return this.nPower;
	}
	
	/**
	 * Setter method for the nPower variable
	 * @param nPower
	 */
	public void setNPower(int nPower) {
		this.nPower = nPower;
	}
	
	/**
	 * Getter method for the logPower variable
	 * @return
	 * 	logPower
	 */
	public int getLogPower() {
		return this.logPower;
	}
	
	/**
	 * Setter method for logPower
	 * @param logPower
	 * 	An int value to set the logPower to
	 */
	public void setLogPower(int logPower) {
		this.logPower = logPower;
	}
	
	/**
	 * Returns a String representation of the Complexity object
	 */
	public String toString() {
		return "O(n^" + this.getNPower() + " * log(n)^" + this.getLogPower() + ")" ;
	}
	
}
