/*Nur Nibir
 * 111059470
 * nur.nibir@stonybrook.edu
 * HW #7
 * CSE 214 R08
*/
package cse214;

import java.util.Comparator;

public class URLComparator implements Comparator{
	
	/**
	 * Compares WebPage objects by URL
	 */
	@Override
	public int compare(Object o1, Object o2) {
		
		WebPage webPage1 = (WebPage) o1;
		WebPage webPage2 = (WebPage) o2;
		
		return webPage1.getURL().compareTo(webPage2.getURL());
			
	}
	

}
