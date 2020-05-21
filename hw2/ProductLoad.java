/*Nur Nibir
 * 111059470
 * nur.nibir@stonybrook.edu
 * HW #2
 * CSE 214 R08
*/
package cse214;

public class ProductLoad {
	private String name;
	private double weightInTons;
	private double value;
	private boolean isDangerous;
	
	/**
	 * Empty Constructor for a ProductLoad object
	 */
	public ProductLoad() {
	}
	
	/**
	 * Constructor with parameters for ProductLoad object
	 * @param name 
	 * 	Name of ProductLoad
	 * @param weightInTons
	 * 	Weight of ProductLoad in tons
	 * @param value
	 * 	Value of ProductLoad in dollars
	 * @param isDangerous
	 * 	Is the ProductLoad dangerous or not
	 */
	public ProductLoad(String name, double weightInTons, double value, boolean isDangerous) {
		this.name = name;
		this.weightInTons = weightInTons;
		this.value = value;
		this.isDangerous = isDangerous;
	}
	
	/**
	 * Getter method for name variable
	 * @return
	 * 	name variable
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * Setter method for name variable
	 * @param name
	 * 	What to set the name variable to
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Getter method for weight variable
	 * @return
	 * 	weight variable
	 */
	public double getWeight() {
		return this.weightInTons;
	}
	
	/**
	 * Setter method for weight variable
	 * @param weight
	 * 	What to set the weight variable to 
	 */
	public void setWeight(double weight) {
		if(weight >= 0)
			this.weightInTons = weight;
		else
			throw new IllegalArgumentException("Invalid weight");
	}
	
	/**
	 * Getter method for value variable
	 * @return
	 *	value variable
	 */
	public double getValue() {
		return this.value;
	}
	
	/**
	 * Setter method for value variable
	 * @param value
	 *	What to set the value variable to
	 */
	public void setValue(double value) {
		if(value >= 0)
			this.value = value;
		else 
			throw new IllegalArgumentException("Invalid value");
	}
	
	/**
	 * Getter method for isDangerous variable
	 * @return
	 * 	isDangerous variable
	 */
	public boolean isDangerous() {
		return this.isDangerous;
	}
	
	/**
	 * Setter method for isDangerous
	 * @param dangerous
	 * 	What to set the isDangerous variable to
	 */
	public void setIsDangerous(boolean dangerous) {
		this.isDangerous = dangerous;
	}
	
	
}

