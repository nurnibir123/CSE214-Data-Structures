/*Nur Nibir
 * 111059470
 * nur.nibir@stonybrook.edu
 * HW #4
 * CSE 214 R08
*/
package cse214;
import java.util.ArrayList;
import java.util.Scanner;


public class Simulator {
	public static final int MAX_PACKETS = 3;
	public Router dispatcher = new Router();
	public int totalServiceTime;
	public int totalPacketsArrived;
	public Router[] intRouters;
	public int packetsDropped;
	
	/**
	 * Runs the simulator as described in the specs. Calculate and return the average time each packet spends within the network.
	 * @param arrivalProb
	 * @param numIntRouters
	 * @param maxBufferSize
	 * @param minPacketSize
	 * @param maxPacketSize
	 * @param bandwidth
	 * @param duration
	 * @return
	 * 	Return the average amount of time a packet takes to reach the destination
	 */
	public double simulate(double arrivalProb, int numIntRouters, int maxBufferSize, int minPacketSize, int maxPacketSize, int bandwidth, int duration) {
		intRouters = new Router[numIntRouters];
		int packets = 0;
		for(int i = 0; i< numIntRouters; i++) {
			intRouters[i] = new Router();
		}
		Router destination = new Router();
		
		if(duration <= 0 ||arrivalProb < 0.0 || arrivalProb > 1.0 || duration < 0 )
			System.out.println("NO SIMULATION");
		
		for(int currentSecond = 1; currentSecond <= duration; currentSecond++) {
			System.out.println();
			System.out.println("Time: " + currentSecond);
			
			for(int i = 1; i <= MAX_PACKETS; i++) {
				if(Math.random() < arrivalProb) {
					Packet newPacket = new Packet();
					totalPacketsArrived++;
					newPacket.setId(totalPacketsArrived);
					newPacket.setTimeArrive(currentSecond);
					newPacket.setPacketSize(randInt(minPacketSize, maxPacketSize));
					newPacket.setTimeToDest(newPacket.getPacketSize() / 100);
					dispatcher.enqueue(newPacket);
					System.out.println("Packet " + newPacket.getId() + " arrives at dispatcher with size " + newPacket.getPacketSize());
					
				}
			}
				
			for(int j = 0; j <= dispatcher.size + 1; j++) {
				
				int intRouterNum = Router.sendPacketTo(intRouters);
				if(dispatcher.size != 0) {
					if(intRouters[intRouterNum-1].size < maxBufferSize) {
						Packet toIntRouter = dispatcher.dequeue();
					
						intRouters[intRouterNum-1].enqueue(toIntRouter);
				
						System.out.println("Packet " + toIntRouter.getId() + " sent to Router " + intRouterNum);
				} else {
					
					Packet droppedPacket = dispatcher.dequeue();
					packetsDropped++;
					System.out.println("Network is congested. Packet " + droppedPacket.getId() + " is droppped");
					
				}
					
//					for(int m = 0; m < intRouters.length; m ++) {
//						System.out.println("R" + (m+1) + ": " + intRouters[m].toString());
//					}
					
				}
				
			
				
				
				}
			
			
			for(int m = 0; m < intRouters.length; m ++) {
				System.out.println("R" + (m+1) + ": " + intRouters[m].toString());
			}
			
			for(int k = 0; k<numIntRouters; k++) {
				
				if(intRouters[k].peek() != null) {
					int toDestination = intRouters[k].peek().getTimeToDest();
					
					for(int w = 0; w < bandwidth; w++) {
						if(currentSecond != toDestination) {
							if(intRouters[k].peek().getTimeToDest() == 0) {
								Packet sendToDestination = intRouters[k].dequeue();
								totalServiceTime += (currentSecond - sendToDestination.getTimeArrive());
								int serviceTime = (currentSecond - sendToDestination.getTimeArrive());
								packets++;
								System.out.println("Packet " + sendToDestination.getId() + " has successfully reached its destination: +" + serviceTime);
//								intRouters[k].peek().setTimeToDest(toDestination-1);
							}
						}
					
					}
						if(intRouters[k].peek() != null) {
							toDestination = intRouters[k].peek().getTimeToDest();
						intRouters[k].peek().setTimeToDest(toDestination-1);
						}
					
				}
			

				}
			
		}
		
		System.out.println();
		System.out.println("Simulation ending...");
		System.out.println("Total service time: " + totalServiceTime);
		System.out.println("Total packets served: " + packets);
		if(packets > 0)
			System.out.println("Average service time per packet: " + totalServiceTime / packets);
		else
			System.out.println();
		System.out.println("Total packets dropped: " + packetsDropped);
		return totalServiceTime / totalPacketsArrived;
	}
	
	/**
	 * can generate a random number between minVal and maxVal, inclusively. 
	 * @param minVal
	 * @param maxVal
	 * @return
	 * 	Return that randomly generated number
	 */
	private int randInt(int minVal, int maxVal) {
		return (minVal + ((int)(Math.random()*((maxVal - minVal)+1))));
	}
	
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		


		System.out.println("Starting simulator . . .");
		System.out.println("Enter the number of Intermediate routers: ");
		int numIntRouters = input.nextInt();
		System.out.println("Enter the arrival probability of a packet: ");
		double arrivalProb = input.nextDouble();
		System.out.println("Enter maximum buffer size of a router: ");
		int maxBufferSize = input.nextInt();
		System.out.println("Enter minimum size of a packet: ");
		int minPacketSize = input.nextInt();
		System.out.println("Enter maximum size of a packet: ");
		int maxPacketSize = input.nextInt();
		System.out.println("Enter the bandwidth size: ");
		int bandwidth = input.nextInt();
		System.out.println("Enter the simulation duration: ");
		int duration = input.nextInt();
		
		Simulator simulator = new Simulator();
		simulator.simulate(arrivalProb, numIntRouters, maxBufferSize, minPacketSize, maxPacketSize, bandwidth, duration);
		
		

	}
	
}
