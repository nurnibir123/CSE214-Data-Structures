/*Nur Nibir
 * 111059470
 * nur.nibir@stonybrook.edu
 * HW #6
 * CSE 214 R08
*/
package cse214;
import java.io.File;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Scanner;

public class AuctionSystem implements Serializable{
	public AuctionTable auctionTable;
	public String username;

	/**
	 * The method should first prompt the user for a username. This should be stored in username The rest of the program will be executed on behalf of this user.
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		AuctionSystem auctionSystem = new AuctionSystem();
		Scanner input = new Scanner(System.in);
		
		File f = new File("auction.obj");
		if(f.exists()) {
			System.out.println("Starting...");
			System.out.println("Loading previous Auction Table..."); 
			System.out.println();
			FileInputStream file = new FileInputStream("auction.obj");
			ObjectInputStream inStream = new ObjectInputStream(file);
			
			try {
				auctionSystem.auctionTable = (AuctionTable)inStream.readObject();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}	else {
			System.out.println("Starting...");
			System.out.println("No previous auction table detected.");
			System.out.println("Creating new table...");
			System.out.println();
		}
		
			
			System.out.print("Please select a username: ");
			String username = input.nextLine();
			String option = "";
	
		
		
		do {
			System.out.println();
			
			System.out.println("(D) - Import Data from URL");
			System.out.println("(A) - Create a New Auction");
			System.out.println("(B) - Bid on an Item");
			System.out.println("(I) - Get Info on Auction");
			System.out.println("(P) - Print All Auctions");
			System.out.println("(R) - Remove Expired Auctions");
			System.out.println("(T) - Let Time Pass");
			System.out.println("(Q) - Quit");
			System.out.print("Please select an option: ");
			
			option = input.nextLine();
			
			System.out.println();
			
			switch(option){
			
			case "D":
				System.out.print("Please enter a URL: ");
				option = input.nextLine();
				auctionSystem.auctionTable = AuctionTable.buildFromURL(option);
				System.out.println("Loading...");
				System.out.println("Auction data loaded successfully!");
				break;
			
			case "A": 
				System.out.println("Creating new Auction as " + username);
				System.out.print("Please enter an Auction ID: ");
				String auctionID = input.nextLine();
				System.out.print("Please enter an Auction time (hours): ");
				int auctionTime = input.nextInt();
				input.nextLine();
				System.out.print("Please enter some Item Info: ");
				String auctionInfo = input.nextLine();
				Auction newAuction = new Auction(auctionID, auctionTime, auctionInfo, username);
				auctionSystem.auctionTable.putAuction(auctionID, newAuction);
				System.out.println("Auction " + newAuction.getAuctionID() + " inserted into table");
				break;

			case "B":
				System.out.print("Please enter an Auction ID: ");
				String auctionID1 = input.nextLine(); 
				Auction bidAuction = auctionSystem.auctionTable.getAuction(auctionID1);
				if(bidAuction.getTimeRemaining() > 0) {
					System.out.println("Auction " + bidAuction.getAuctionID() + " is OPEN\n Current Bid: " + bidAuction.currentBid());
					System.out.println("What would you like to bid?: ");
					double newBid = input.nextDouble();
					input.nextLine();
					auctionSystem.auctionTable.getAuction(auctionID1).newBid(username, newBid);
				} else if(bidAuction.getTimeRemaining() == 0) {
					System.out.println("Auction " + bidAuction.getAuctionID() + " is CLOSED");
					System.out.println("You can no longer bid on this item.");
				}
				break;
				
			case "I":
				System.out.println("Please enter an Auction ID: ");
				String auctionID2 = input.nextLine();
				Auction searchedAuction = auctionSystem.auctionTable.getAuction(auctionID2);
				System.out.println("Auction " + searchedAuction.getAuctionID());
				System.out.println("\tSeller: " + searchedAuction.getSellerName());
				System.out.println("\tBuyer: " + searchedAuction.getBuyerName());
				System.out.println("\tTime: " + searchedAuction.getTimeRemaining() + " hours");
				System.out.println("\tInfo: " + searchedAuction.itemInfo());
				break;
				
			case "P":
				auctionSystem.auctionTable.printTable(); 
				break;
				
			case "R":
				auctionSystem.auctionTable.removeExpiredAuctions();
				System.out.println("Removing expired auctions...");
				System.out.println("All expired auctions removed.");
				break;
			
			case "T":
				System.out.println("How many hours should pass: ");
				int hoursPassed = input.nextInt();
				input.nextLine();
				auctionSystem.auctionTable.letTimePass(hoursPassed);
				System.out.println("Time passing...");
				System.out.println("Auction times updated.");
				break;
				
			case "Q":
				FileOutputStream file1 = new FileOutputStream("auction.obj");
				ObjectOutputStream outStream = new ObjectOutputStream(file1);
				outStream.writeObject(auctionSystem.auctionTable);
				System.out.println("Writing Auction Table to file...");
				System.out.println("Done!");
				System.out.println("Goodbye.");
				System.exit(1);
				break;
			}
			
			
		} while(!option.equals("Q"));

	}

}
