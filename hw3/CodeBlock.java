/*Nur Nibir
 * 111059470
 * nur.nibir@stonybrook.edu
 * HW #3
 * CSE 214 R08
*/
package cse214;

public class CodeBlock {
	BLOCK_TYPES blockType;
	private String name;
	private Complexity blockComplexity;
	private Complexity highestSubComplexity;
	private String loopVariable;
	
	/**
	 * Constructor for a CodeBlock object that initializes new Complexities
	 */
	public CodeBlock() {
		blockComplexity = new Complexity();
		highestSubComplexity = new Complexity();
	}
	
	/**
	 * 
	 * @return
	 * 	Returns the name of the CodeBlock
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * Setter method for the name variable
	 * @param name
	 * 	A String value to set the name variable to
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Getter method for the blockComplexity object
	 * @return
	 * 	The blockComplexity
	 */
	public Complexity getBlockComplexity() {
		return this.blockComplexity;
	}
	
	/**
	 * Setter method for the blockComplexity object
	 * @param blockComplexity
	 * 	A Complexity object to set the blockComplexity to
	 */
	public void setBlockComplexity(Complexity blockComplexity) {
		this.blockComplexity = blockComplexity;
	}
	
	/**
	 * Getter method for highestSubComplexity variable
	 * @return
	 * 	highestSubComplexity
	 */
	public Complexity getHighestSubComplexity() {
		return this.highestSubComplexity;
	}
	
	/**
	 * Setter method for highestSubComplexity
	 * @param highestSubComplexity
	 * 	Complexity object to set highestSubComplexity to
	 */
	public void setHighestSubComplexity(Complexity highestSubComplexity) {
		this.highestSubComplexity = highestSubComplexity;
	}
	
	/**
	 * Getter method for the loopVariable variable
	 * @return
	 * 	loopVariable
	 */
	public String getLoopVariable() {
		return this.loopVariable;
	}
	
	/**
	 * Setter method for loopVariable variable
	 * @param loopVariable
	 * 	String to set loopVariable to
	 */
	public void setLoopVariable(String loopVariable) {
		this.loopVariable = loopVariable;
	}
	
	/**
	 * Returns a String representation of the codeBlock object
	 */
	public String toString() {
		return "Block Complexity: " + this.blockComplexity.toString() + "\t  Highest SubComplexity: " + this.highestSubComplexity.toString();
	}
	
	
}
