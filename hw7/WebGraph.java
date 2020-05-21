/*Nur Nibir
 * 111059470
 * nur.nibir@stonybrook.edu
 * HW #7
 * CSE 214 R08
*/
package cse214;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class WebGraph {
	public static final int MAX_PAGES = 40;
	private ArrayList<WebPage> pages = new ArrayList();
	private int[][] edges = new int[MAX_PAGES][MAX_PAGES];
	int index;
	
	/**
	 * Constructs a WebGraph object using the indicated files as the source for pages and edges.
	 * @param pagesFile
	 * 	pagesFile - String of the relative path to the file containing the page information.
	 * @param linksFile
	 * 	linksFile - String of the relative path to the file containing the link information.
	 * @return
	 * 	The WebGraph constructed from the text files.
	 * @throws IllegalArgumentException
	 * @throws FileNotFoundException
	 */
	public static WebGraph buildFromFiles(String pagesFile, String linksFile) throws IllegalArgumentException, FileNotFoundException{
		WebGraph webGraph = new WebGraph();		
		Scanner pagesInput = new Scanner(new File(pagesFile));
		Scanner linksInput = new Scanner(new File(linksFile));
		
		while(pagesInput.hasNext()) {
			String[] pagesNextLine = pagesInput.nextLine().trim().split(" ");
	
			ArrayList<String> keywords = new ArrayList();
			
			for(int i = 1; i<pagesNextLine.length; i++) 
				keywords.add(pagesNextLine[i]);
	
			webGraph.addPage(pagesNextLine[0], keywords);
		}
		
		while(linksInput.hasNext()) {
			String[] linksNextLine = linksInput.nextLine().trim().split(" ");
			webGraph.addLink(linksNextLine[0], linksNextLine[1]);
		}
		
		webGraph.updatePageRank();
		
		return webGraph;
	}
	
	/**
	 * Adds a page to the WebGraph.
	 * @param url
	 * 	url - The URL of the webpage (must not already exist in the WebGraph).
	 * @param keywords
	 * 	keywords - The keywords associated with the WebPage.
	 */
	public void addPage(String url, ArrayList<String> keywords) {
		WebPage newPage = new WebPage();
		int exists = 0;
		
		for(int i = 0; i<pages.size(); i++) {
			if(pages.get(i).getURL().equals(url)) {
				exists++;
			}
		}
		
		if(exists > 0) {
			System.out.println();
			System.out.println("Error: " + url + " already exists in the WebGraph. Could not add new WepPage.");
			System.out.println();
		}
		else if(exists == 0) {
			newPage.setURL(url);
			newPage.setKeywords(keywords);
			newPage.setIndex(index);
			this.pages.add(newPage);
			this.index++;
		}
	}
	
	/**
	 * Adds a link from the WebPage with the URL indicated by source to the WebPage with the URL indicated by destination
	 * @param source
	 * 	source - the URL of the page which contains the hyperlink to destination.
	 * @param destination
	 * 	destination - the URL of the page which the hyperlink points to.
	 */
	public void addLink(String source, String destination) {
		WebPage sourcePage = new WebPage();
		WebPage destinationPage = new WebPage();

		for(int i = 0; i<pages.size(); i++) {
			if(pages.get(i).getURL().equals(source)) {
				sourcePage = pages.get(i);
			}else if(pages.get(i).getURL().equals(destination)) {
				destinationPage = pages.get(i);
			}
		}

		edges[sourcePage.getIndex()][destinationPage.getIndex()] = 1;
		pages.get(sourcePage.getIndex()).getLinks().add(destinationPage.getIndex());
		this.updatePageRank();

	}
	
	/**
	 * Removes the WebPage from the graph with the given URL.
	 * @param url
	 * 	url - The URL of the page to remove from the graph.
	 */
	public void removePage(String url) {
		WebPage removedPage = new WebPage();
		
		for(int i = 0; i<pages.size(); i++) {
			if(pages.get(i).getURL().equals(url)) 
				removedPage = pages.get(i);
		}
		
		for(int k = 0; k<pages.size(); k++) {
			for(int j = removedPage.getIndex(); j<pages.size()-1; j++) {
				edges[k][j] = edges[k][j+1];
				edges[j][k] = edges[j+1][k];
			}
		}
			
			
		for(int j = 0; j<pages.size(); j++) {
			for(int w = 0; w<pages.get(j).getLinks().size(); w++) {
				if(pages.get(j).getLinks().contains(removedPage.getIndex()))
					pages.get(j).getLinks().remove(Integer.valueOf(removedPage.getIndex()));
			
			}
		}

		for(int j = 0; j<pages.size(); j++) {
			for(int w = 0; w<pages.get(j).getLinks().size(); w++) {
				
				if( (int)pages.get(j).getLinks().get(w) > removedPage.getIndex() ) {
					int updatedLinks = (int) pages.get(j).getLinks().get(w);
					pages.get(j).getLinks().set(w, updatedLinks - 1);
				}
			}
		}

		pages.remove(removedPage);
		
		for(int i = 0; i<pages.size(); i++)
			pages.get(i).setIndex(i);
		
		this.updatePageRank();
	}
	
	
	/**
	 * Removes the link from WebPage with the URL indicated by source to the WebPage with the URL indicated by destination.
	 * @param source
	 * 	source - The URL of the WebPage to remove the link.
	 * @param destination
	 * 	source - The URL of the link to be removed.
	 */
	public void removeLink(String source, String destination) {
		WebPage sourcePage = new WebPage();
		WebPage destinationPage = new WebPage();
		for(int i = 0; i<pages.size(); i++) {
			if(pages.get(i).getURL().equals(source)) {
				sourcePage = pages.get(i);
			}else if(pages.get(i).getURL().equals(destination)) {
				destinationPage = pages.get(i);
			}

			edges[sourcePage.getIndex()][destinationPage.getIndex()] = 0;
			pages.get(sourcePage.getIndex()).getLinks().remove(Integer.valueOf(destinationPage.getIndex()));
			this.updatePageRank();
		}
	}
	
	/**
	 * Calculates and assigns the PageRank for every page in the WebGraph 
	 */
	public void updatePageRank() {
		int pageRank = 0;
		for(int i = 0; i<pages.size(); i++) {
			for(int j = 0; j<pages.size(); j++) {
				pageRank += edges[j][i];
			}
			pages.get(i).setRank(pageRank);
			pageRank = 0;
		}
		
	}
	
	/**
	 * Prints the WebGraph in tabular form
	 * @param option
	 */
	public void printTable(String option) {
		System.out.printf("%-10s%-19s%-14s%-19s%-15s","Index", "URL", "PageRank", "Links", "Keywords");
		System.out.println();
		System.out.println("-----------------------------------------------------------------------------------------");
		
		if(option.equals("I")) {
			Collections.sort(pages);
			for(int i = 0; i<pages.size(); i++) {
				System.out.println(pages.get(i).toString());
			}
		}
		
		else if(option.equals("U")) {
			Collections.sort(pages, new URLComparator());
			for(int i = 0; i<pages.size(); i++) {
				System.out.println(pages.get(i).toString());
			}
			Collections.sort(pages);
		}
		
		else if(option.equals("R")) {
			Collections.sort(pages, new RankComparator());
			for(int i = 0; i<pages.size(); i++) {
				System.out.println(pages.get(i).toString());
			}
			Collections.sort(pages);
		}
	}
	
	public void searchByKeyWord(String keyword) {
		int rank = 1;
		int exists = 0;
		
		for(int i = 0; i< pages.size(); i++) {
			if(pages.get(i).getKeywords().contains(keyword)) {
				exists++;
			}
		}
		
		if(exists == 0)
			System.out.println("No search results found for the keyword " + keyword);
		
		else if(exists > 0) {

			System.out.printf("%-10s%-15s%-25s", "Rank", "PageRank", "URL");
			System.out.println();
			System.out.println("--------------------------------------------------------");
			Collections.sort(pages, new RankComparator());
			for(int i = 0; i< pages.size(); i++) {
				if(pages.get(i).getKeywords().contains(keyword)) {

					System.out.printf("%3d%4s%7d%7s%15s", rank, "|", pages.get(i).getRank(), "|", pages.get(i).getURL());
					System.out.println();
					rank++;
				}
			}
		}
		
		Collections.sort(pages);
		this.updatePageRank();
	}
	
}
