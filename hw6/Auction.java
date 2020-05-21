/*Nur Nibir
 * 111059470
 * nur.nibir@stonybrook.edu
 * HW #6
 * CSE 214 R08
*/
package cse214;

import java.io.Serializable;

public class Auction implements Serializable{
	private int timeRemaining;
	private double currentBid;
	private String auctionID;
	private String sellerName;
	private String buyerName;
	private String itemInfo;
	
	/**
	 * Constructor for Auction object
	 * @param timeRemaining
	 * @param currentBid
	 * @param auctionID
	 * @param sellerName
	 * @param buyerName
	 * @param itemInfo
	 */
	public Auction(int timeRemaining, double currentBid, String auctionID, String sellerName, String buyerName, String itemInfo) {
		this.timeRemaining = timeRemaining;
		this.currentBid = currentBid;
		this.auctionID = auctionID;
		this.sellerName = sellerName;
		this.buyerName = buyerName;
		this.itemInfo = itemInfo;
	}
	
	/**
	 * Constructor for Auction object
	 * @param auctionID
	 * @param timeRemaining
	 * @param itemInfo
	 * @param username
	 */
	public Auction(String auctionID, int timeRemaining, String itemInfo, String username) {
		this.auctionID = auctionID;
		this.timeRemaining = timeRemaining;
		this.itemInfo = itemInfo;
		this.sellerName = username;
		this.buyerName = "";
		
	}
	
	/**
	 * Decreases the time remaining for this auction by the specified amount
	 * @param time
	 * 	Time to decrease remainingTime by
	 */
	public void decrementTimeRemaining(int time) {
		this.timeRemaining = this.timeRemaining - time;
	}
	
	/**
	 * Makes a new bid on this auction. If bidAmt is larger than currentBid, then the value of currentBid is replaced by bidAmt and buyerName is is replaced by bidderName.
	 * @param bidderName
	 * @param bidAmt
	 */
	public void newBid(String bidderName, double bidAmt) {

//		if(this.timeRemaining == 0)
//			throw new ClosedAuctionException();

//		else {
		
			if(bidAmt > this.currentBid) {
				this.currentBid = bidAmt;
				this.buyerName = bidderName;
				System.out.println("Bid accepted.");
			}
		}
//	}
	
	/**
	 * Getter method for timeRemaining
	 * @return
	 * 	the timeRemaining variable
	 */
	public int getTimeRemaining() {
		return this.timeRemaining;
	}
	
	/**
	 * Getter method for currentBid
	 * @return
	 * 	the currentBid variable
	 */
	public double currentBid() {
		return this.currentBid;
	}
	
	/**
	 * 
	 * @return
	 * 	the auctionID variable
	 */
	public String getAuctionID() {
		return this.auctionID;
	}
	
	/**
	 * 
	 * @return
	 * 	the sellerName variable
	 */
	public String getSellerName() {
		return this.sellerName;
	}
	
	/**
	 * 
	 * @return
	 * 	the buyerName variable
	 */
	public String getBuyerName() {
		return this.buyerName;
	}
	
	/**
	 * 
	 * @return
	 * 	the itemInfo variable
	 */
	public String itemInfo() {
		return this.itemInfo;
	}

	/**
	 * Returns a string version of this Auction object
	 */
	public String toString() {
		String formatted = String.format("%10s%2s%12.2f%3s%17s%10s%22s%11s%10s%5s%10s", auctionID, "|", currentBid, "|", sellerName,"|", buyerName, "|", timeRemaining + "" + " hours", "|", itemInfo);
		return formatted;
	}
	
}
