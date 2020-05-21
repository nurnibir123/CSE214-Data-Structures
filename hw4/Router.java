/*Nur Nibir
 * 111059470
 * nur.nibir@stonybrook.edu
 * HW #4
 * CSE 214 R08
*/
package cse214;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Queue;


public class Router extends LinkedList<Packet>{
	public int size;
	
	/**
	 * Enqueues a packet 
	 * @param p
	 * 	a packet to enqueue
	 */
	public void enqueue(Packet p) {
		add(p);
		size++;
	}
	
	/**
	 * Dequeues a packet 
	 * @return
	 * 	The packet dequeued
	 */
	public Packet dequeue() {
		size--;
		return removeFirst();
	}
	
	/**
	 * Returns the Packet at the front of the list
	 */
	public Packet peek() {
		return peekFirst();
	}
	
	/**
	 * Returns the size of the queue
	 */
	public int size() {
		return this.size;
	}
	
	/**
	 * Returns if the queue is empty or not
	 */
	public boolean isEmpty() {
		return (size == 0);
	}
	
	/**
	 * Returns the string representation of queue
	 */
	public String toString() {
		String string = "{";
		for(int i=0; i<size; i++) 	 
			 string += get(i).toString() + ", ";
		return string + "}";
	}
	
	/**
	 * Setter method for the size of the queue
	 * @param size
	 */
	public void setSize(int size) {
		this.size = size;
	}
	
	/**
	 * Traverses through a routers array and compares the sizes of each router
	 * @param routers
	 * 	An array of routers
	 * @return
	 * 	the index of the router with the most amount of space
	 */
	public static int sendPacketTo(Router[] routers)  {
		int minSize = routers[0].size();
		int index = 0;
	
		for(int i = 0; i<routers.length; i++) {
			if(routers[i].size() < minSize) {
				minSize = routers[i].size();
				index = i;
			}
			
		}
		
		return index + 1;
	}
	
}
