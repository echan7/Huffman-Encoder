///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Main Class File:  FrequencyParser
// File:             MinPriorityQueue.java
// Semester:         CS367 Spring 2016
//
// Author:           JieShenONg
// CS Login:         jieo	
// Lecturer's Name:  Deb Deppler
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ////////////////////
//
// Pair Partner:     Eric Chan 
// Email:            echan7@wisc.edu
// CS Login:         echan7
// Lecturer's Name:  Deb Deppler
//
//////////////////////////// 80 columns wide ///////////////////////////////////

/**
 * This class contains the array of the minimum priority queue along with
 * methods that can be used to modify it.
 *
 * @author Eric Chan
 */
public class MinPriorityQueue implements MinPriorityQueueADT{
	
	// declare an array called huffmanArray that would represent the PriorityQueue
	HuffmanNode[] huffmanArray;
	
	// declare an int variable called numItems that will hold the num of items in queue
	int numItems;
	
	public MinPriorityQueue(){
		huffmanArray = new HuffmanNode[129];
		numItems = 0;
	}
	
	/**
	 * removes the node that is minimum, that is from the root
	 *
	 * @param no parameter
	 * 
	 * @return returns the HuffmanNode that was removed (smallest)
	 */
	@Override
	public HuffmanNode removeMin() throws PriorityQueueEmptyException {
		//check if queue is Empty, if it is, throw exception
		if(isEmpty()){
			throw new PriorityQueueEmptyException();
		}
		
		// declare a variable called returnNode that is used to store the node to be removed
		HuffmanNode returnNode = huffmanArray[1];

		// take the node with the largest frequency and set it as the start of queue
		// set the previous position of the node to null
		huffmanArray[1] = huffmanArray[numItems];
		huffmanArray[numItems] = null;
		
		// reduce numItems since the MIN node is removed
		numItems--;
		
		/* create a variable that store the index of the node used to calibrate
		 * the shape and structure of the PriorityQueue/tree */
		int currIndex = 1;

		// declare a boolean variable done and initialize to false
		// done is used to terminate the loop when calibration is done
		boolean done = false;
		
		/* use a loop that will compare the calibrating node and its children node
		 * and swaps them accordingly each time it is run*/
		while(!done){
			
			/* Since we're using a Binary Tree, we know that the index of the 
			 * left child is twice the index of parent and the index of 
			 * right child is one more than the left node. */
			int leftIndex = currIndex*2;
			int rightIndex = currIndex*2+1;

				/* check if the index of the children nodes are out of bounds
				 * in other words, whether the end of queue is reached
				 * if not, execute if clause */
				if(!(currIndex*2 > numItems) && !(currIndex*2+1 > numItems)){

					/* compare the swapped node with its children and execute clause
					 *  if any children nodes have a higher frequency */
					if(huffmanArray[currIndex].compareTo(huffmanArray[leftIndex]) == 1 || 
							huffmanArray[currIndex].compareTo(huffmanArray[rightIndex]) == 1){
						int lesserNodeIndex = 0;
						
						// find out which children node has the least frequency by comparing both of them.
						// set the lesser node as the node to be swapped
						if(huffmanArray[leftIndex].compareTo(huffmanArray[rightIndex]) == -1){
							lesserNodeIndex = leftIndex;
						}else{
							lesserNodeIndex = rightIndex;
						}
						// Swap the parent node with the node with a smaller frequency
						HuffmanNode temp = huffmanArray[lesserNodeIndex];
						huffmanArray[lesserNodeIndex] = huffmanArray[currIndex];
						huffmanArray[currIndex] = temp;
						currIndex = lesserNodeIndex;
					}else{
						// end loop if children nodes are not supposed to be swapped.
						done = true;
					}
				 
				// if there is a left child but there isn't a right child
				}else if(!(currIndex*2 > numItems) && (currIndex*2+1 > numItems)){
					
					/* compare the parent with the left child  and execute clause 
					if the frequency of parent node is larger than the left child */
					if(huffmanArray[currIndex].compareTo(huffmanArray[leftIndex]) == 1){
						
					// swap if parent's freq is larger than left child 
					HuffmanNode temp = huffmanArray[leftIndex];
					huffmanArray[leftIndex] = huffmanArray[currIndex];
					huffmanArray[currIndex] = temp;
					currIndex = leftIndex;
					}else{
						// terminate node when children are have higher frequencies
						done = true;
					}
				}else{
					/* since the swapped node is now at the end of queue and there 
					 is nothing to compare, terminate loop.*/
					done = true;
				}
		}
		return returnNode;
	}
	
	/**
	 * insert a node into the priority queue, maintaining the shape and structure
	 * of a binary tree, ensuring that the smallest node is at the start of the 
	 * Priority queue
	 *
	 * @param hn HuffmanNode to be added
	 * @throws PriorityQueueFullException if the PriorityQueue is full
	 * @return void
	 */
	@Override
	public void insert(HuffmanNode hn) throws PriorityQueueFullException {
		// if numItems of the array reached its limit, throw exception
		if(numItems == 128){
			throw new PriorityQueueFullException();
		}
		// add the huffmanArray at the end of the "tree"
		huffmanArray[numItems+1] = hn;
		
		// declare var currIndex to store the index of the newly inserted node
		int currIndex = numItems+1;
		
		// declare boolean var done and initialize it to false: used to terminate loop
		boolean done = false;
		
		// increment numItems since we are inserting a new item into the queue
		numItems++;
		
		/* create a while loop that terminates when new node that is inserted is either :
		   1 : reached the root of the tree 
		   2 : bigger than the parent node */
		
		while(currIndex/2 != 0 && !done){
			
			// since the index of the parent of a child is twice the child, calibrate index
			int parentIndex = currIndex/2;
			
			/* if new node is smaller than the parent node, switch the nodes
			  and update the current index*/
			if(huffmanArray[currIndex].compareTo(huffmanArray[parentIndex]) == -1){
				HuffmanNode temp = huffmanArray[parentIndex];
				huffmanArray[parentIndex] = huffmanArray[currIndex];
				huffmanArray[currIndex] = temp;
				
				// update the current index 
				currIndex = parentIndex;
			}else{
				/* if the new node is greater when compared to the parent node,
				 terminate loop*/
				done = true;
			}
		}
		
	}

	/**
	 * allows the caller to check if the Priority Queue is Empty
	 *
	 * @param no parameter
	 * 
	 * @return boolean value. True if queue is empty, False if otherwise.
	 */
	@Override
	public boolean isEmpty() {
		// if there are no items in the queue, queue is empty and return true
		return numItems == 0;
	}

}
