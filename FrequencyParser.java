///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Title:            Huffman Coding
// Files:            MinPriorityQueue, Config.java, FileBitStream.java, FileBitWriter.java
//					 Huffman.java, HuffmanDB.java, HuffmanDecoder.java, HuffmanENcoder.java
//					 HuffmanNode.java, HuffmanTree.java, MinPriorityQueue.ADT, PrintFrequency.java
//					 PriorityQueueEmptyException.java,  PriorityQueueFullException.java
//					 TestFrequencyParser.java, TestMinPriorityQueue.java
// Semester:         CS367 Fall 2016
//
// Author:           JieShen Ong
// Email:            jong4@wisc.edu	
// CS Login:         jieo	
// Lecturer's Name:  Deb Depler
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ////////////////////
//
//                   CHECK ASSIGNMENT PAGE TO see IF PAIR-PROGRAMMING IS ALLOWED
//                   If pair programming is allowed:
//                   1. Read PAIR-PROGRAMMING policy (in cs302 policy) 
//                   2. choose a partner wisely
//                   3. REGISTER THE TEAM BEFORE YOU WORK TOGETHER 
//                      a. one partner creates the team
//                      b. the other partner must join the team
//                   4. complete this section for each program file.
//
// Pair Partner:     Eric Chan
// Email:            echan7@wisc.edu
// CS Login:         echan7
// Lecturer's Name:  Deb Depler
//
//////////////////////////// 80 columns wide //////////////////////////////////
import java.io.*;
import java.util.*;

public class FrequencyParser{
	/**
	 * This method takes a text file containing ASCII characters only and
	 * returns an int array of length 128 which counts the occurrences of each
	 * character.  
	 * 
	 * The entry at index i is the count of the character with ASCII
	 * value i 'Start of Heading' (ASCII value 1) and null character (ASCII
	 * value 0) should both have count 0.
	 * 
	 * @param file 
	 *  the name of the file containing the mapping from symbols to codes
	 * 
	 * @return the array of frequencies of each character
	 * 
	 * @throws FileNotFoundException 
	 *  if the file does not exist
	 */
	public static int[] getFrequencies(String file) throws FileNotFoundException{
		Scanner input = new Scanner(new File(file));

		// Initialize an array of size 128 as there are 128 ASCII characters
		int[] asciiArray = new int[128];


		// create a while loop to read all the characters from the text file
		while(input.hasNextLine()){
			// increment new line's count everytime loop is run
			asciiArray[(int)'\n']++;	
			// read line and convert it to a char array 
			String line = input.nextLine();
			char[] charArray = line.toCharArray();

			// loop through the char Array and increment the value according to
			// the char value indexes of char found
			for(int i = 0; i< charArray.length; i++ ){
				int found = charArray[i];
				asciiArray[found]++;
			}
		}

		// return the parsed frequency array
		return asciiArray;
	}
}
