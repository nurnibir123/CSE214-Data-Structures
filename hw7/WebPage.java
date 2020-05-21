/*Nur Nibir
 * 111059470
 * nur.nibir@stonybrook.edu
 * HW #7
 * CSE 214 R08
*/
package cse214;
import java.util.ArrayList;

public class WebPage implements Comparable{
	private String url;
	private int index;
	private int rank = 1;
	ArrayList<Integer> links = new ArrayList();
	public ArrayList<String> keywords;
	
	/**
	 * Getter method for url
	 * @return
	 */
	public String getURL() {
		return this.url;
	}
	
	/**
	 * Setter method for url
	 * @param url
	 */
	public void setURL(String url) {
		this.url = url;
	}
	
	/**
	 * Getter method for index
	 * @return
	 */
	public int getIndex() {
		return this.index;
	}
	
	/**
	 * Setter method for index
	 * @param index
	 */
	public void setIndex(int index) {
		this.index = index;
	}
	
	/**
	 * Getter method for rank
	 * @return
	 */
	public int getRank() {
		return this.rank;
	}
	
	/**
	 * Setter method for rank
	 * @param rank
	 */
	public void setRank(int rank) {
		this.rank = rank;
	}
	
	/**
	 * Getter method for keywords
	 * @return
	 */
	public ArrayList<String> getKeywords(){
		return this.keywords;
	}
	
	/**
	 * Setter method for keywords
	 * @param keywords
	 */
	public void setKeywords(ArrayList<String> keywords) {
		this.keywords = keywords;
	}
	
	/**
	 * Getter method for links
	 * @return
	 */
	public ArrayList getLinks() {
		return this.links;
	}
	
	/**
	 * Setter method for links
	 * @param links
	 */
	public void setLinks(ArrayList links) {
		this.links = links;
	}
	
	/**
	 * Returns a String representation of this WebPage object
	 */
	public String toString() {
		String link = "";
		for(int i = 0; i<links.size() - 1; i++) {
			link += "" + links.get(i) + ", ";
		}
		if(!links.isEmpty())
			link += "" + links.get(links.size() - 1);
		
		String keyword = " ";
		for(int i = 0; i<keywords.size() - 1; i++) {
			keyword += keywords.get(i) + ", ";
		}
		if(!keywords.isEmpty())
			keyword += keywords.get(keywords.size() - 1);
		
		String formatted = String.format("%3d%4s%-19s%2s%5d%6s%19s%3s%23s", index, "|", " " + url, "|", rank, "|", link , "|", keyword);
		return formatted;
	}
	
	/**
	 * Compares WebPage objects by index
	 */
	@Override
	public int compareTo(Object o) {
		WebPage webPage = (WebPage) o;
		if(this.index == webPage.getIndex())
			return 0;
		else if(this.index > webPage.getIndex())
			return 1;
		else 
			return -1;
	}
	
}
