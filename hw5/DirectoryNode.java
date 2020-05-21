/*Nur Nibir
 * 111059470
 * nur.nibir@stonybrook.edu
 * HW #5
 * CSE 214 R08
*/
package cse214;

public class DirectoryNode {
	private String name;
	private DirectoryNode left;
	private DirectoryNode middle;
	private DirectoryNode right;
	private DirectoryNode parent;
	private boolean isFile;
	private int depth;
	
	/**
	 * Getter method for name variable
	 * @return
	 * 	name
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * Setter method for name
	 * @param name
	 *  String to set name variable to
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Getter method for left DirectoryNode
	 * @return
	 * 	left
	 */
	public DirectoryNode getLeft() {
		return this.left;
	}
	
	/**
	 * Setter method for left DirectoryNode
	 * @param left
	 * 	DirectoryNode to set left to 
	 */
	public void setLeft(DirectoryNode left) {
		this.left = left;
	}
	
	/**
	 * Getter method for middle DirectoryNode
	 * @return
	 * 	middle
	 */
	public DirectoryNode getMiddle() {
		return this.middle;
	}
	
	/**
	 * Setter method for middle DirectoryNode
	 * @param middle
	 * 	DirectoryNode to set middle to
	 */
	public void setMiddle(DirectoryNode middle) {
		this.middle = middle;
	}
	
	/**
	 * Getter method for right DirectoryNode
	 * @return
	 * 	right
	 */
	public DirectoryNode getRight() {
		return this.right;
	}
	
	/**
	 * Setter method for right DirectoryNode
	 * @param right
	 * 	DirectoryNode to set right to
	 */
	public void setRight(DirectoryNode right) {
		this.right = right;
	}
	
	/**
	 * Getter method for parent
	 * @return
	 * 	parent
	 */
	public DirectoryNode getParent() {
		return this.parent;
	}
	
	/**
	 * Setter method for parent 
	 * @param parent
	 * 	DirectoryNode to set parent to
	 */
	public void setParent(DirectoryNode parent) {
		this.parent = parent;
	}
	
	/**
	 * Getter method for isFile
	 * @return
	 * 	If the DirectoryNode is a file or not
	 */
	public boolean isFile() {
		return this.isFile;
	}
	
	/**
	 * Setter method for isFile
	 * @param isFile
	 * 	Boolean to set isFile to
	 */
	public void setIsFile(boolean isFile) {
		this.isFile = isFile;
	}
	
	/**
	 * Getter method for depth
	 * @return
	 * 	depth
	 */
	public int getDepth() {
		return this.depth;
	}
	
	/**
	 * Setter method for depth
	 * @param depth
	 * 	int to set depth to
	 */
	public void setDepth(int depth) {
		this.depth = depth;
	}
	
	/**
	 * Adds newChild to any of the open child positions of this node (left, middle, or right)
	 * @param newChild
	 * @throws NotADirectoryException
	 * @throws FullDirectoryException
	 */
	public void addChild(DirectoryNode newChild) throws NotADirectoryException, FullDirectoryException{
		
		if(left == null)
			left = newChild;
		else if(middle == null)
			middle = newChild;
		else if(right == null)
			right = newChild;
		else if(this.isFile)
			throw new NotADirectoryException();
		else
			throw new FullDirectoryException();
		
	}
	
	/**
	 * Starts at cursor and preorder prints the DirectoryTree
	 */
	public void preorderPrint() {
		if(!this.isFile)
			System.out.println(printSpaces() + "|- " + this.getName());
		else
			System.out.println(printSpaces() + "  -" + this.getName());
		if(this.getLeft() != null)
			this.getLeft().preorderPrint();
		if(this.getMiddle() != null)
			this.getMiddle().preorderPrint();
		if(this.getRight() != null)
			this.getRight().preorderPrint();
	}
	
	/**
	 * Searches through a DirectoryTree and returns the node being searched for
	 * @param name
	 * @param root
	 * @return
	 */
	public DirectoryNode find(String name, DirectoryNode root) {
		if(root != null) {	
			if(root.getName().equals(name))
				return root;
			else {
				DirectoryNode newSearchNode = find(name,root.getLeft());
				if(newSearchNode == null)
					newSearchNode = find(name, root.getMiddle());
					if(newSearchNode == null)
						newSearchNode = find(name, root.getRight());
				return newSearchNode;
			}
			
			} else
				return null;
		}
	
	
	public String printSpaces() {
		String spaces = "";
		for(int i = 0; i < this.getDepth(); i++)
			spaces += " ";
		return spaces;
	}
	
	/**
	 * Sets the depth variable of each DirectoryNode to its depth on the tree
	 * @param root
	 * @param depth
	 */
	public void setDepth(DirectoryNode root, int depth) {
		if(root == null)
			return;
		if(depth == 0)
			root.setDepth(0);
		
		if(root.getLeft() != null) {
			root.getLeft().setDepth(depth + 1);
			setDepth(root.getLeft(), depth + 1);
		}
		
		if(root.getMiddle() != null) {
			root.getMiddle().setDepth(depth + 1);
			setDepth(root.getMiddle(), depth + 1);
		}
			
		if(root.getRight() != null) {
			root.getRight().setDepth(depth + 1);
			setDepth(root.getRight(), depth + 1);
		}
		return;
	}
	

	
	
	
}
