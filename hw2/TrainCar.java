/*Nur Nibir
 * 111059470
 * nur.nibir@stonybrook.edu
 * HW #2
 * CSE 214 R08
*/
package cse214;

public class TrainCar {
	private double length;
	private double weightInTons;
	private ProductLoad productLoad;
	
	/**
	 * Empty Constructor for TrainCar object
	 */
	public TrainCar() {
		this.productLoad = new ProductLoad("Empty", 0, 0, false);
	}
	
	/**
	 * Constructor with parameters for TrainCar object
	 * @param length
	 * 	length of TrainCar object
	 * @param weightInTons
	 * 	weight in tons of TrainCar object
	 */
	public TrainCar(double length,double weightInTons) {
		this.length = length;
		this.weightInTons = weightInTons;
		this.productLoad = new ProductLoad("Empty", 0, 0, false);
	}
	
	/**
	 * Getter method for the length variable
	 * @return
	 * 	length variable
	 */
	public double getLength() {
		return this.length;
	}
	
	/**
	 * Getter method for the weight variable
	 * @return
	 * 	weight variable
	 */
	public double getWeight() {
		return this.weightInTons;
	}
	
	/**
	 * Getter method for the ProductLoad variable
	 * @return
	 * 	ProductLoad variable
	 */
	public ProductLoad getProductLoad() {
		return this.productLoad;
	}
	
	/**
	 * Setter method for ProductLoad variable
	 * @param productLoad
	 * 	What to set the ProductLoad variable to
	 */
	public void setProductLoad(ProductLoad productLoad) {
		this.productLoad = productLoad;
	}
	
	/**
	 * Checks if the ProductLoad is empty 
	 * @return
	 * 	true if the ProductLoad is null, false otherwise
	 */
	public boolean isEmpty() {
		if(this.getProductLoad() == null)
			return true;
		else
			return false;
	}
	
	
}
