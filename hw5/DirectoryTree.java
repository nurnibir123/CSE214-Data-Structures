/*Nur Nibir
 * 111059470
 * nur.nibir@stonybrook.edu
 * HW #5
 * CSE 214 R08
*/
package cse214;

public class DirectoryTree {
	private DirectoryNode root;
	private DirectoryNode cursor;
	
	public DirectoryTree() {
		this.root = new DirectoryNode();
		root.setName("root");
		this.cursor = root;
		setAllDepth();
	}
	
	/**
	 * Moves the cursor to the root node of the tree.
	 */
	public void resetCursor() {
		this.cursor = this.root;
	}
	
	/**
	 * Moves the cursor to the directory with the name indicated by name.
	 * @param name
	 * @throws NotADirectoryException
	 */
	public void changeDirectory(String name) throws NotADirectoryException{
			
		if(cursor.getLeft() != null && cursor.getLeft().getName().equals(name)) {
			if(!cursor.getLeft().isFile())
				cursor = cursor.getLeft();
			else 
				throw new NotADirectoryException();
		}
		else if(cursor.getMiddle() != null && cursor.getMiddle().getName().equals(name)) {
			if(!cursor.getMiddle().isFile())
				cursor = cursor.getMiddle();
			else 
				throw new NotADirectoryException();
		}
		else if(cursor.getRight() != null && cursor.getRight().getName().equals(name)) {
			if(!cursor.getRight().isFile())
				cursor = cursor.getRight();
			else 
				throw new NotADirectoryException();
		}
		else
			System.out.println("ERROR: No such directory named " + name);
	}
	
	/**
	 * Returns a String containing the path of directory names from the root node of the tree to the cursor, with each name separated by a forward slash "/".
	 * @return
	 * 	
	 */
	public String presentWorkingDirectory() {
		String path = cursor.getName();
		
		for(DirectoryNode tempCursor = cursor.getParent(); tempCursor != null; tempCursor = tempCursor.getParent())
			path =  tempCursor.getName() + "/" + path;
			
		return path;
	}
	
	/**
	 * Returns a String containing a space-separated list of names of all the child directories or files of the cursor.
	 * @return
	 */
	public String listDirectory() {
		String ls = "";
		if(cursor.getLeft() != null)
			ls += cursor.getLeft().getName() + " ";
		if(cursor.getMiddle() != null)
			ls += cursor.getMiddle().getName() + " ";
		if(cursor.getRight() != null)
			ls += cursor.getRight().getName(); 
		
		return ls;
	}
	
	/**
	 * Prints a formatted nested list of names of all the nodes in the directory tree, starting from the cursor.
	 */
	public void printDirectoryTree() {

		cursor.preorderPrint();
		
	}
	
	/**
	 * Creates a directory with the indicated name and adds it to the children of the cursor node.
	 * @param name
	 * @throws IllegalArgumentException
	 * @throws FullDirectoryException
	 */
	public void makeDirectory(String name) throws IllegalArgumentException, FullDirectoryException{
		DirectoryNode newChild = new DirectoryNode();
		newChild.setName(name);
		newChild.setParent(cursor);
		
		try {
			cursor.addChild(newChild);
			setAllDepth();
		} catch (NotADirectoryException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Creates a file with the indicated name and adds it to the children of the cursor node.
	 * @param name
	 * @throws IllegalArgumentException
	 * @throws FullDirectoryException
	 */
	public void makeFile(String name) throws IllegalArgumentException, FullDirectoryException{
		DirectoryNode newFile = new DirectoryNode();
		newFile.setName(name);
		newFile.setIsFile(true);
		newFile.setParent(cursor);
		try {
			cursor.addChild(newFile);
			setAllDepth();
		} catch (NotADirectoryException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Finds the node in the tree with the indicated name and prints the path.
	 * @param name
	 * @return
	 */
	public String find(String name) {
		DirectoryNode found = cursor.find(name, root);
		if(found == null)
			return "Error, directory/file cannot be found";
		
		String path = found.getName();
		
		for(DirectoryNode tempCursor = found.getParent(); tempCursor != null; tempCursor = tempCursor.getParent())
			path = tempCursor.getName()  + "/" + path;
			
		return path;
		
	}

	/**
	 * Moves the cursor up to its parent directory
	 */
	public void moveToParent() {
		if(!cursor.equals(root))
			cursor = cursor.getParent();
		else
			System.out.println("Directory does not exist");
	}
	
	
	public void findPath(String path) {
		String[] splitPath = path.split("/");
		String target = splitPath[splitPath.length-1];
		if(this.find(target).contains(path))
			cursor = cursor.find(target, root);
	}
	
	public void setAllDepth() {
		root.setDepth(this.root, 0);
	}
	
	public String printSpaces() {
		String spaces = "";
		for(int i = 0; i < cursor.getDepth(); i++)
			spaces += " ";
		return spaces;
	}
	
	
	public void move(String src, String dst) {
		DirectoryNode start = cursor.find(src, this.root);
		DirectoryNode dest = cursor.find(dst, this.root);

			
			if(start.getParent().getLeft().getName().equals(start.getName())) {
				start.getParent().setLeft(null);
				start.setParent(null);
			}

			else if(start.getParent().getMiddle().getName().equals(start.getName())) {
				start.getParent().setMiddle(null);
				start.setParent(null);
			}

			else if(start.getParent().getRight().getName().equals(start.getName())) {
				start.getParent().setRight(null);
				start.setParent(null);			
			}

			try {
				dest.addChild(start);
				setAllDepth();
			} catch (NotADirectoryException | FullDirectoryException e) {
				System.out.println("ERROR: The destination directory is full");
			}



	}
	
}
