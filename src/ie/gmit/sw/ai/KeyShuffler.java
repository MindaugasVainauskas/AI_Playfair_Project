//When key is unknown this class will be used to try and find it using simulated annealing algorithm.	
package ie.gmit.sw.ai;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class KeyShuffler {
	
	private String parentCipherKey;
	private String childCipherKey;
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

	public String getParentCipherKey() {
		return parentCipherKey;
	}

	//set up randomized initial key
	public void setParentCipherKey(String alpha) {
		char[] temp = shuffle(alpha.toCharArray());
		this.parentCipherKey = String.valueOf(temp);
		this.setCipherMatrix(this.parentCipherKey);
		
		//System.out.println("Initial Cipher key matrix");
		//printMatrix();
		
	}

	public String getChildCipherKey() {
		return childCipherKey;
	}

	public void setChildCipherKey(String childCipherKey) {
		this.childCipherKey = childCipherKey;
	}

	//print out the current cipher matrix to console for feedback and testing
	public void printMatrix() {
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				System.out.print(this.cipherMatrix[i][j]+" ");
			}
			System.out.println();
		}
	}
	
	public String getIntermediateKey() {
		String s = "";
		
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				s+= cipherMatrix[i][j];
			}
		}
		return s;
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
	public String shuffleKey(String parKey) {		
		//random index number used in switch statement
		int ranIndex = (int)Math.floor(Math.random() * 100);
		this.setCipherMatrix(parKey);
		//switch statement with random 1-100 values used to determine what to do when shuffling the key
		//90% - swap single letters
		//2% - swap random rows
		// 2% - swap columns
		// 2% - flip all rows
		// 2% flip all columns
		// 2% reverse the whole key				
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
		
		this.setChildCipherKey(this.getIntermediateKey());
		
		return this.getChildCipherKey();
	}

	//swap around random rows in the matrix
	private void swapRandomRows() {
		int r1, r2;
		
		do {
			r1 = (int)Math.floor(Math.random()*5);
			r2 = (int)Math.floor(Math.random()*5);
		}while(r1 == r2);
		//swap rows of the cipherMatrix around
		char[] temp = cipherMatrix[r1];
		cipherMatrix[r1] = cipherMatrix[r2];
		cipherMatrix[r2] = temp;
	}
	
	//swap around random columns in the matrix
	private void swapRandomColumns() {		
		int c1, c2;
		
		do {
			c1 = (int)Math.floor(Math.random()*5);
			c2 = (int)Math.floor(Math.random()*5);
		}while(c1 == c2);
		//swap the characters in different columns around
		for (int i = 0; i < 5; i++) {
			char temp = cipherMatrix[i][c1];
			cipherMatrix[i][c1] = cipherMatrix[i][c2];
			cipherMatrix[i][c2] = temp;
		}		
	}

	//flip all rows in the matrix around
	private void flipRows() {		
		//run the for loop for all 5 rows in matrix
		for (int i = 0; i < 5; i++) {
			char[] temp = cipherMatrix[i];
			
			//reverse temporary 1d array
			for(int j = 0; j < temp.length / 2; j++) {
			    char temp2 = temp[j];
			    temp[j] = temp[temp.length - j - 1];
			    temp[temp.length - j - 1] = temp2;
			}
			
			//assign reversed row value back into matrix
			cipherMatrix[i] = temp;			
		}
	}

	//reverse all the columns in the matrix
	private void flipColumns() {
		//outer for for columns
		for (int i = 0; i < 5/2; i++) {
			//inner for loop for rows
			for (int j = 0; j < 5; j++) {
				char temp = cipherMatrix[i][j];
				cipherMatrix[i][j] = cipherMatrix[5 - i - 1][j];
				//assign temp value back into its new spot in array
				cipherMatrix[5 - i - 1][j] = temp;
			}
		}
	}
	
	//reverse entire matrix key
	private void reverseEntireKey() {
		//for entire key reversal I am simply calling row and column reversal one after another once.
		flipColumns();
		flipRows();		
	}

	//method to swap around randomly selected characters
	private void swapSingleRandomLetters() {
		int r1, c1, r2, c2;
		//pick coordinates on the matrix for character swapping as long as they're not same.
		do{
			r1 = (int) Math.floor((Math.random()*5));
			c1 = (int) Math.floor((Math.random()*5));
			r2 = (int) Math.floor((Math.random()*5));
			c2 = (int) Math.floor((Math.random()*5));
			
		}while ((r1 == r2) && (c1 == c2)); 
		
		//swap the characters in matrix around
		char temp = cipherMatrix[r1][c1];
		cipherMatrix[r1][c1] = cipherMatrix[r2][c2];
		cipherMatrix[r2][c2] = temp;
	}

}
