/*Nur Nibir
 * 111059470
 * nur.nibir@stonybrook.edu
 * HW #7
 * CSE 214 R08
*/
package cse214;

import java.util.Comparator;

public class RankComparator implements Comparator{

	/**
	 * Compares WebPage objects by rank
	 */
	public int compare(Object o1, Object o2) {
		WebPage webPage1 = (WebPage) o1;
		WebPage webPage2 = (WebPage) o2;
		
		if(webPage1.getRank() == webPage2.getRank())
			return 0;
		else if(webPage1.getRank() > webPage2.getRank())
			return -1;
		else 
			return 1;
	}
	
}
