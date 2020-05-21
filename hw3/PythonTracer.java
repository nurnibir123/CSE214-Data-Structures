/*Nur Nibir
 * 111059470
 * nur.nibir@stonybrook.edu
 * HW #3
 * CSE 214 R08
*/
package cse214;
import java.util.Stack;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;



public class PythonTracer {
	public static final int SPACE_COUNT = 4;
	
	public static void main(String[] args) {
		
		Scanner input = new Scanner(System.in);
		String fileName = "";
	
		while(!fileName.equalsIgnoreCase("quit")) {	
			System.out.println();
			System.out.print("Enter a file path/name or 'quit' to quit: ");
			fileName = input.nextLine();
			
		if(fileName.equalsIgnoreCase("quit")) {
			System.out.println("Program terminating successfully...");
			System.exit(0);
		}
		
		try {
			System.out.println("Overall complexity of " + fileName + ": " + traceFile(fileName).toString());
		
		} catch (FileNotFoundException e) {
			System.out.println();
			System.out.print("File could not be found");
			continue;
			} continue;
		
		}
		


		
		
	}
	
	/**
	 * The traceFile method: the user is updated to the changes being made to the stack via a stack trace. Every time a new block is pushed on to the stack, the block's name, complexity, and highest sub-complexity will be printed to the console.
	 * @param file
	 * 	The file that is being read
	 * @return
	 * 	A complexity object that contains the highestSubComplexity of the blocks of the stack
	 * @throws FileNotFoundException
	 * 	If the file cannot be found
	 */
	public static Complexity traceFile(String file) throws FileNotFoundException{
		Stack<CodeBlock> stack = new Stack<CodeBlock>();
		int size = 0;
		Scanner input = new Scanner(new File(file));
		String line;

		
		while(input.hasNext()) {
			line = input.nextLine();
			String trimmedLine = line.trim();
			if(!trimmedLine.isEmpty() && trimmedLine.charAt(0) != '#') {
				int indents = line.indexOf(line.trim()) / SPACE_COUNT;

//				System.out.println(size + "\t" + indents);
				while(indents < size) {
					if(indents == 0) {
						input.close();
						CodeBlock block = stack.peek();
						System.out.println(block.toString());
					} else {
	
						CodeBlock oldTop = stack.pop();
						size--;
						Complexity totalComplexity = new Complexity();
						totalComplexity.setNPower(oldTop.getBlockComplexity().getNPower() + oldTop.getHighestSubComplexity().getNPower());
						totalComplexity.setLogPower(oldTop.getBlockComplexity().getLogPower() + oldTop.getHighestSubComplexity().getLogPower());
					
						if(totalComplexity.getLogPower() > stack.peek().getHighestSubComplexity().getLogPower()
								|| totalComplexity.getNPower() > stack.peek().getHighestSubComplexity().getNPower()) {
							stack.peek().setHighestSubComplexity(totalComplexity);
						} 
						
						System.out.println("Leaving " + oldTop.blockType + " block, updating complexity: \n" 
								+ "Block Complexity: " + stack.peek().getBlockComplexity().toString() + "\t  "
								+ "Highest SubComplexity: " + stack.peek().getHighestSubComplexity().toString() + "\n");
						
					}
				}
				
				if(line.contains("def ") || line.contains("for ") || line.contains("while ") 
					|| line.contains("if ") || line.contains("elif ") || line.contains("else ")) {
				
					if(line.contains("for ")) {
						CodeBlock newBlock = new CodeBlock();
						Complexity newBlockComplexity = new Complexity();
						newBlock.blockType = BLOCK_TYPES.FOR;
						String[] words = line.trim().split(" ");
						String ending = words[words.length - 1];
						if(ending.equals("log_N:")) {
							newBlockComplexity.setLogPower(1);
							newBlock.setBlockComplexity(newBlockComplexity);
							stack.push(newBlock);
							System.out.println("Entering FOR block: 1." + indents);
							System.out.println(newBlock.toString() + "\n");
							size++;
						}else if(ending.equals("N:")) {
							newBlockComplexity.setNPower(1);
							newBlock.setBlockComplexity(newBlockComplexity);
							stack.push(newBlock);
							System.out.println("Entering FOR block: 1." + indents);
							System.out.println(newBlock.toString() + "\n");
							size++;
						}
						
					}else if(line.contains("while ")) {
						String[] words = line.trim().split(" ");
						String loopVariable = words[1];
						CodeBlock newBlock = new CodeBlock();
						newBlock.blockType = BLOCK_TYPES.WHILE;
						newBlock.setLoopVariable(loopVariable);
						stack.push(newBlock);
						System.out.println("Entering WHILE block: 1." + indents);
						System.out.println(newBlock.toString() + "\n");
						size++;
					}else if(line.contains("def ")){
						CodeBlock newBlock = new CodeBlock();
						newBlock.blockType = BLOCK_TYPES.DEF;
						stack.push(newBlock);
						System.out.println("Entering DEF block: 1." + indents);
						System.out.println(newBlock.toString() + "\n");
						size++;
					}else if(line.contains("if ")) {
						CodeBlock newBlock = new CodeBlock();
						newBlock.blockType = BLOCK_TYPES.IF;
						stack.push(newBlock);
						System.out.println("Entering IF block: 1." + indents);
						System.out.println(newBlock.toString() + "\n");
						size++;
					}else if(line.contains("elif ")) {
						CodeBlock newBlock = new CodeBlock();
						newBlock.blockType = BLOCK_TYPES.ELIF;
						stack.push(newBlock);
						System.out.println("Entering ELIF block: ");
						System.out.println(newBlock.toString() + "\n");
						size++;
					}else if(line.contains("else ")) {
						CodeBlock newBlock = new CodeBlock();
						newBlock.blockType = BLOCK_TYPES.ELSE;
						stack.push(newBlock);
						System.out.println("Entering ELSE block: ");
						System.out.println(newBlock.toString() + "\n");
						size++;
					}
					
				}else if(stack.peek().blockType == BLOCK_TYPES.WHILE) {
					String[] words = line.trim().split(" ");
					
					if(stack.peek().getLoopVariable().equals(words[0])) {
						if(words[1].equals("/=")){
							Complexity whileComplexity = new Complexity();
							whileComplexity.setLogPower(1);
							stack.peek().setBlockComplexity(whileComplexity);
							
						}else if(words[1].equals("-=")) {
							Complexity whileComplexity = new Complexity();
							whileComplexity.setNPower(1);
							stack.peek().setBlockComplexity(whileComplexity);
							
						}
						
					}
				}
			
			}

		}
			
			while(size > 1) {
				CodeBlock oldTop = stack.pop();
				size--;
				Complexity totalComplexity = new Complexity();
				totalComplexity.setNPower(oldTop.getBlockComplexity().getNPower() + oldTop.getHighestSubComplexity().getNPower());
				totalComplexity.setLogPower(oldTop.getBlockComplexity().getLogPower() + oldTop.getHighestSubComplexity().getLogPower());

				
				if(totalComplexity.getLogPower() > stack.peek().getHighestSubComplexity().getLogPower()
						|| totalComplexity.getNPower() > stack.peek().getHighestSubComplexity().getNPower()) {
					stack.peek().setHighestSubComplexity(totalComplexity);
					System.out.println("Leaving " + oldTop.blockType + " block: " + stack.peek().getHighestSubComplexity().toString());
					
				}

			}
			
		
		System.out.println("Leaving " + stack.peek().blockType + " block: updating complexity: \n" + "Block Complexity: " + stack.peek().getBlockComplexity().toString() + "\t  "
				+ "Highest SubComplexity" + stack.peek().getHighestSubComplexity().toString() + "\n");
		return stack.pop().getHighestSubComplexity();
	}
	
}
