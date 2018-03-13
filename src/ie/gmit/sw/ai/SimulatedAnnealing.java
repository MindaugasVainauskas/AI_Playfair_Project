package ie.gmit.sw.ai;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class SimulatedAnnealing {
	//When key is unknown this class will be used to try and find it using
	// simulated annealing algorithm.	
	private String cipherKey;
	// Set up changing function to change up the key using annealing algorithm
	//run the encrypted text using key changing it incrementally until decoded plaintext is present.

	public String getCipherKey() {
		return cipherKey;
	}

	//set up randomized initial key
	public void setCipherKey(String alpha) {
		char[] temp = shuffle(alpha.toCharArray());
		this.cipherKey = String.valueOf(temp);
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

}
