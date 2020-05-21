/*Nur Nibir
 * 111059470
 * nur.nibir@stonybrook.edu
 * HW #6
 * CSE 214 R08
*/
package cse214;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Set;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.sql.Time;

import big.data.DataSource;

public class AuctionTable extends Hashtable<String, Auction> implements Serializable{
	
	/**
	 * Uses the BigData library to construct an AuctionTable from a remote data source.
	 * @param URL
	 * 	URL - String representing the URL for the remote data source.
	 * @return
	 * 	The AuctionTable constructed from the remote data source.
	 */
	public static AuctionTable buildFromURL(String URL)  {
	
	
		AuctionTable auctionTable = new AuctionTable();
		DataSource ds = DataSource.connectXML(URL).load();
		
		String[] idNums = ds.fetchStringArray("listing/auction_info/id_num");
		String[] currentBid = ds.fetchStringArray("listing/auction_info/current_bid");
		String[] sellerName = ds.fetchStringArray("listing/seller_info/seller_name");
		String[] buyerName = ds.fetchStringArray("listing/auction_info/high_bidder/bidder_name");
		String[] timeLeft = ds.fetchStringArray("listing/auction_info/time_left");
		String[] memoryInfo = ds.fetchStringArray("listing/item_info/memory");
		String[] hardDriveInfo = ds.fetchStringArray("listing/item_info/hard_drive");
		String[] cpuInfo = ds.fetchStringArray("listing/item_info/cpu");
		
		for(int i = 0; i<idNums.length; i++) {
			
			double currentBid1 = Double.parseDouble(currentBid[i].substring(1).replaceAll(",", ""));
			String[] timeParsed = timeLeft[i].split(" ");
			int totalTime = 0;
			String description = "";
			String seller = "";
			if(timeParsed.length == 2 && timeParsed[1].equals("days")) {
				totalTime = Integer.parseInt(timeParsed[0]);
				description = memoryInfo[i].replaceAll("\n", "") + " " + hardDriveInfo[i].replaceAll("\n", "") + " " + cpuInfo[i].replaceAll("\n", "");
				seller = sellerName[i].replaceAll("\n", "").replaceAll(" ", "");
			}
			else if(timeParsed.length == 2 && timeParsed[0].equals("hrs")) {
				totalTime = Integer.parseInt(timeParsed[0]);
				description = memoryInfo[i].replaceAll("\n", "") + " " + hardDriveInfo[i].replaceAll("\n", "") + " " + cpuInfo[i].replaceAll("\n", "");
				seller = sellerName[i].replaceAll("\n", "").replaceAll(" ", "");
				
			} else if(timeParsed.length != 2) {
				totalTime = Integer.parseInt(timeParsed[0])*24 + Integer.parseInt(timeParsed[2]);
				description = memoryInfo[i] + " " + hardDriveInfo[i] + " " + cpuInfo[i];
				seller = sellerName[i];
			}
			
			Auction newAuction = new Auction(totalTime, currentBid1, idNums[i], seller, buyerName[i].trim(), description.trim());
			auctionTable.putAuction(idNums[i], newAuction);

		}
		
		return auctionTable;
		
	}
	
	/**
	 * Manually posts an auction, and add it into the table.
	 * @param auctionID
	 * 	the unique key for this object
	 * @param auction
	 * 	The auction to insert into the table with the corresponding auctionID
	 */
	public void putAuction(String auctionID, Auction auction) {
		if(this.containsKey(auctionID))
			throw new IllegalArgumentException("ID already exists");
		else
			this.put(auctionID, auction);
	}
	
	/**
	 * Get the information of an Auction that contains the given ID as key
	 * @param auctionID
	 * 	auctionID - the unique key for this object
	 * @return
	 * 	An Auction object with the given key, null otherwise.
	 */
	public Auction getAuction(String auctionID) {
		return (Auction)this.get(auctionID);
	}
	
	/**
	 * Simulates the passing of time. Decrease the timeRemaining of all Auction objects by the amount specified.
	 * @param numHours
	 * 	numHours - the number of hours to decrease the timeRemaining value by.
	 */
	public void letTimePass(int numHours) {
		if(numHours < 0)
			throw new IllegalArgumentException("Time passed cannot be negative");
		else {
			Set<String> setOfAuctions = this.keySet();

			for(String key : setOfAuctions) {
				if(this.get(key).getTimeRemaining() < numHours)
					this.get(key).decrementTimeRemaining(this.get(key).getTimeRemaining());
				else
					this.get(key).decrementTimeRemaining(numHours);
			}
		}
		
	}
	
	/**
	 * Iterates over all Auction objects in the table and removes them if they are expired (timeRemaining == 0).
	 */
	public void removeExpiredAuctions() {
		Object[] setOfAuctions = this.keySet().toArray();
		for(int i = 0; i<setOfAuctions.length;i++) {
			if(this.get((String)setOfAuctions[i]).getTimeRemaining() == 0)
				this.remove((String)setOfAuctions[i]);
		}
		
	}
		
	

	/**
	 * Prints the AuctionTable in tabular form.
	 */
	public void printTable() {
		System.out.printf("%10s%2s%10s%5s%17s%10s%22s%11s%10s%5s%10s", "Auction ID","|", "Bid", "|", "Seller","|", "Buyer","|", "Time","|", "Item Info");
		System.out.println();
		System.out.println("=============================================================================================================================");
		Set<String> setOfAuctions = this.keySet();
		for(String key : setOfAuctions) {
			System.out.println(this.get(key).toString());
		}
	}

	
	
	
}
