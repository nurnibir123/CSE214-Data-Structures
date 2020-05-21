/*Nur Nibir
 * 111059470
 * nur.nibir@stonybrook.edu
 * HW #7
 * CSE 214 R08
*/
package cse214;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class SearchEngine {
	public static final String PAGES_FILE = "pages.txt";
	public static final String LINKS_FILE = "links.txt";
	private WebGraph web = new WebGraph();
	
	/**
	 * Provide a menu prompt and provide different options for the user
	 * @param args
	 */
	public static void main(String[] args) {
		SearchEngine searchEngine = new SearchEngine();
		
		try {
			searchEngine.web = WebGraph.buildFromFiles(PAGES_FILE, LINKS_FILE);
		} catch (IllegalArgumentException | FileNotFoundException e) {
			e.printStackTrace();
		}
		
		System.out.println("Loading WebGraph data...\n" + "Success!");
		
		String option = "";
		Scanner input = new Scanner(System.in);
		
		
		while(!option.equals("Q")) {
			
			System.out.println("(AP) - Add a new page to the graph.");
			System.out.println("(RP) - Remove a page from the graph.");
			System.out.println("(AL) - Add a link between pages in the graph.");
			System.out.println("(RL) - Remove a link between pages in the graph.");
			System.out.println("(P) - Print the graph.");
			System.out.println("(S) - Search for pages with a keyword.");
			System.out.println("(Q) - Quit.");
			System.out.println();
			System.out.print("Please select an option: ");
			
			option = input.nextLine();
			
			System.out.println();
			
			if(option.equals("AP")) {
				System.out.print("Enter a URL: ");
				String newURL = input.nextLine();
				System.out.print("Enter keywords (space-separated): ");
				String[] newKeywords = input.nextLine().split(" ");
				ArrayList<String> keywords = new ArrayList();
				for(int i = 0; i < newKeywords.length; i++) {
					keywords.add(newKeywords[i]);
				}
				
				searchEngine.web.addPage(newURL, keywords);
				System.out.println();
				System.out.println(newURL + " successuflly added to the WebGraph!");
				System.out.println();
			} 
			
			else if(option.equals("RP")) {
				System.out.print("Enter a URL: ");
				String removedURL = input.nextLine();
				searchEngine.web.removePage(removedURL);
				System.out.println();
				System.out.println(removedURL + " has been removed from the graph!");
				System.out.println();
			}
			
			else if(option.equals("AL")) {
				System.out.print("Enter a source URL: ");
				String sourceURL = input.nextLine();
				System.out.print("Enter a destination URL: ");
				String destinationURL = input.nextLine();
				searchEngine.web.addLink(sourceURL, destinationURL);
				System.out.println();
				System.out.println("Link successfully added from " + sourceURL + " to " + destinationURL);
				System.out.println();
			}
			
			else if(option.equals("RL")){
				System.out.print("Enter a source URL: ");
				String sourceURL = input.nextLine();
				System.out.print("Enter a destination URL: ");
				String destinationURL = input.nextLine();
				searchEngine.web.removeLink(sourceURL, destinationURL);
				System.out.println();
				System.out.println("Link removed from " + sourceURL + " to " + destinationURL);
				System.out.println();
			}
			

			
			else if(option.equals("P")) {
				System.out.println("(I) Sort based on index (ASC)");
				System.out.println("(U) Sort based on URL (ASC)");
				System.out.println("(R) Sort based on rank (DSC)");
				System.out.print("Please select an option: ");
				
				String printOption = input.nextLine();
				
				if(printOption.equals("I")) {
					System.out.println();
					searchEngine.web.printTable("I");
					System.out.println();
				} 
				else if(printOption.equals("U")) {
					System.out.println();
					searchEngine.web.printTable("U");
					System.out.println();
				}
				else if(printOption.equals("R")) {
					System.out.println();
					searchEngine.web.printTable("R");
					System.out.println();
				}
			
			}
			
			else if(option.equals("S")) {
				System.out.print("Search keyword: ");
				String keyword = input.nextLine();
				System.out.println();
				searchEngine.web.searchByKeyWord(keyword);
				System.out.println();
			}
			
			else if(option.equals("Q")) {
				System.out.println("GoodBye");
				System.exit(1);
			}
			
			
		}

	}

}
