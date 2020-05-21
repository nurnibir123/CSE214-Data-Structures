/*Nur Nibir
 * 111059470
 * nur.nibir@stonybrook.edu
 * HW #5
 * CSE 214 R08
*/
package cse214;
import java.util.Scanner;

public class BashTerminal {
	
	/**
	 * allows a user to interact with a file system implemented by an instance of DirectoryTree using commands
	 * @param args
	 */
	public static void main(String[] args) {
		DirectoryTree tree = new DirectoryTree();
		String command = "";
		
		Scanner input = new Scanner(System.in);
	
	
			
			while(!command.equals("exit")) {
				System.out.print("[111059470@host]: $ ");
				command = input.nextLine();
				String[] split = command.trim().split(" ");
				
				//pwd
				if(split[0].equals("pwd"))
					System.out.println(tree.presentWorkingDirectory()); 
				
				//ls
				else if(split[0].equals("ls") && split.length == 1)
					System.out.println(tree.listDirectory()); 
				
				//ls -R
				else if(split[0].equals("ls") && split[1].equals("-R"))
					tree.printDirectoryTree();
				
				//cd
				else if(split[0].equals("cd") && !split[1].equals("/") && !split[1].startsWith("root") && !split[1].equals("...")) {
		
					try {
						tree.changeDirectory(split[1]);
					} catch (NotADirectoryException e) {
						System.out.println("Cannot change directory into a file.");;
					} 
				}
				
				//cd /
				else if(split[0].equals("cd") && split[1].equals("/"))
					tree.resetCursor(); 
				
				//mkdir
				else if(split[0].equals("mkdir")) {
					try {
						tree.makeDirectory(split[1]);
					} catch (IllegalArgumentException e) {
						e.printStackTrace();
					} catch (FullDirectoryException e) {
						e.printStackTrace();
					} 
				}
			
				else if(split[0].equals("touch")) {
					try {
						tree.makeFile(split[1]);
					} catch (IllegalArgumentException e) {
						e.printStackTrace();
					} catch (FullDirectoryException e) {
						System.out.println("ERROR: Present directory is full.");;
					}
				}
				
				else if(split[0].equals("find")) 
					System.out.println(tree.find(split[1]));
				
				else if(split[0].equals("cd") && split[1].contains("/"))
					tree.findPath(split[1]);
				
				else if(split[0].equals("cd") && split[1].equals(".."))
					tree.moveToParent();
				
				else if(split[0].equals("mv"))
					tree.move(split[1], split[2]);
					
				else if(split[0].equals("exit")) {
					System.out.println("Program terminating normally");
					System.exit(0); 
				}
				 
			}
			
			
			
	
	}
	
}
