//When key is unknown this class will be used to try and find it using simulated annealing algorithm.	
package ie.gmit.sw.ai;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class SimulatedAnnealing {
	
	private String cipherKey;
	private char[][] cipherMatrix;

	public char[][] getCipherMatrix() {
		return cipherMatrix;
	}

	public void setCipherMatrix(String cipherKey) {
		char[][] tempMatrix = new char[5][5];
		int run = 0;
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				tempMatrix[i][j] = cipherKey.charAt(run);
				run++;
			}
			
		}
		this.cipherMatrix = tempMatrix;
	}

	public String getCipherKey() {
		return cipherKey;
	}

	//set up randomized initial key
	public void setCipherKey(String alpha) {
		char[] temp = shuffle(alpha.toCharArray());
		this.cipherKey = String.valueOf(temp);
		this.setCipherMatrix(this.cipherKey);
		
		System.out.println("Initial Cipher matrix key");
		printMatrix();
		
	}

	//print out the current cipher matrix to console for feedback and testing
	private void printMatrix() {
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				System.out.print(this.cipherMatrix[i][j]+" ");
			}
			System.out.println();
		}
	}
		
	//shuffle the character array of initial alphabet sequence
	public char[] shuffle(char[] key) {
		 int index;
			 Random random = ThreadLocalRandom.current();
			 for (int i = key.length - 1; i > 0; i--) {
				 index = random.nextInt(i + 1);
				 if (index != i) {
				 key[index] ^= key[i];
				 key[i] ^= key[index];
				 key[index] ^= key[i];
			 }
		 }
			return key;
	}
	
	//Key shuffle function to change up the key as ciphertext is being processed.
	public void shuffleKey() {
		//switch
		//90% - swap single letters - done
		//2% - swap random rows
		// 2% - swap columns
		// 2% - flip all rows
		// 2% flip all columns
		// 2% reverse the whole key		
		
		int ranIndex = (int)Math.floor(Math.random() * 100);
		
		switch (ranIndex) {
		case 91:
		case 92:
			swapRandomRows();
			break;
		case 93:
		case 94:
			swapRandomColumns();
			break;
		case 95:
		case 96:
			flipRows();
			break;
		case 97:
		case 98:
			flipColumns();
			break;
		case 99:
		case 100:
			reverseEntireKey();
			break;

		default:
			//90% of time key will be adjusted by swapping random letter pair in the matrix
			swapSingleRandomLetters();			
			break;
		}
		
	}

	private void swapRandomRows() {
		System.out.println("Swap random rows");
		
	}
	
	private void swapRandomColumns() {
		System.out.println("Swap columns");
		
	}

	private void flipRows() {
		System.out.println("Flip all rows");
		
	}

	private void flipColumns() {
		System.out.println("Flip all columns");
		
	}
	
	private void reverseEntireKey() {
		System.out.println("Reverse the whole key");
		
	}

	//method to swap around randomly selected characters
	private void swapSingleRandomLetters() {
		System.out.println("Swap random letter pair");
		int r1, c1, r2, c2;
		//pick coordinates on the matrix for character swapping as long as they're not same.
		do{
			r1 = (int) Math.floor((Math.random()*5));
			c1 = (int) Math.floor((Math.random()*5));
			r2 = (int) Math.floor((Math.random()*5));
			c2 = (int) Math.floor((Math.random()*5));
			
		}while ((r1 == r2) && (c1 == c2)); 
		
		System.out.println(r1+" "+ c1+" "+r2+" "+c2);
		
		//swap the characters in matrix around
		char temp = cipherMatrix[r1][c1];
		cipherMatrix[r1][c1] = cipherMatrix[r2][c2];
		cipherMatrix[r2][c2] = temp;
		
		printMatrix();
		
	}

}
