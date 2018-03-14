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
		//90% - swap single letters
		//2% - swap random rows
		// 2% - swap columns
		// 2% - flip all rows
		// 2% flip all columns
		// 2% reverse the whole key
		
		
		int ranIndex = (int)Math.floor(Math.random() * 100);
		
		switch (ranIndex) {
		case 91:
		case 92:
			System.out.println("Swap random rows");
			break;
		case 93:
		case 94:
			System.out.println("Swap columns");
			break;
		case 95:
		case 96:
			System.out.println("Flip all rows");
			break;
		case 97:
		case 98:
			System.out.println("Flip all columns");
			break;
		case 99:
		case 100:
			System.out.println("Reverse the whole key");
			break;

		default:
			swapSingleRandomLetters();
			System.out.println("Swap random letter pair");
			break;
		}
		
	}

	private void swapSingleRandomLetters() {
		// TODO Auto-generated method stub
		
	}

}
