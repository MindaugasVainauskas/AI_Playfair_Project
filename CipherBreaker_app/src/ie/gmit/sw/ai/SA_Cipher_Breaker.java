package ie.gmit.sw.ai;

import java.util.Map;

public class SA_Cipher_Breaker {
	
	//Alphabet of 25 characters used in cipher breaking process
	private  static final String ALPHABET = "ABCDEFGHIKLMNOPQRSTUVWXYZ";
	
	//declare cipher keys that will be used.
	private String parentKey;
	private String childKey;
	
	//Declare Simulated Annealing specific variables
	private int temperature;
	private int transitions;
	private int stepSize;	
	private double keyFitness;
	
	//Declare classes used by this class
	private KeyShuffler ks;
	private Playfair_Decryptor pfd;
	private FileReaderClass frc;
	private K_Mer_Parser kmp;	
	
	private String[] kmers;
	
	private Map<String, Integer> kMerCompareMap;
	
	private String decryptedText;
	
	public SA_Cipher_Breaker(int temp, int transits, int stSize, String fName) {
		ks = new KeyShuffler();
		ks.setParentCipherKey(ALPHABET); //set initial parent cipher key from given alphabet
		pfd = new Playfair_Decryptor();
		frc = new FileReaderClass(fName);
		pfd.setEncryptedMessage(frc.parseTextFile()); //parse the initial encrypted text file. Only needed once so I do it in constructor for now.
		kmp = new K_Mer_Parser(); //Import 4Gram file
		kmp.map4GramFile("4grams.txt"); //Map the 4gram file to hash map
		kMerCompareMap = kmp.getfGramMap();
		
		this.setTemperature(temp);
		this.setTransitions(transits);
		this.setStepSize(stSize);
		
		//set parent key to be the initial random cipher key that key shuffler generates
		this.setParentKey(ks.getParentCipherKey());
	}
	
	//create character g-grams from returned text.
		public void parsetoKMers(String encMessage) {
			kmers = new String[encMessage.length()-4];
			int i = 0;			
			//Set up 4-mer shingles for checking later. Will shift them 2 characters at a time with the length of 4.
			// This approach should hopefully give better accuracy than jumping 4 characters at a time but more speed than 1 character shift approach.
			for (int j = 0; j < encMessage.length()-4; j++) {
				 kmers[i] = encMessage.substring(j, j + 4);         
		         i++;
			}
		}
	
	public void cipherBreakerSA() {		
		String[] digrams = pfd.createDigrams(pfd.getEncryptedMessage()); //set up digrams from ciphered text
		double tempScore = 0;
		
		parentKey = ks.getParentCipherKey(); //get temporary key from parent key(child key in for loops)
		pfd.DecryptCipherText(digrams, parentKey); //decrypt text with given key		
		parsetoKMers(pfd.getDecryptedMessage()); //parse decrypted text into 4-mers for checking against 4gram hash map
		//check calculated map for k-mers that exist in original kMer compare map.
		
		for (String key : kmers) {
			if (kMerCompareMap.keySet().contains(key)) {
				//calculate probability value and its log and add it to temp fitness score
				double probability = (Math.log10(kMerCompareMap.get(key).doubleValue()) / Math.log10(kmp.getTotal4GramCount()));
				tempScore += probability;
			}			
		}		
		System.out.println("Generated 4-gram array length: "+kmers.length);
		this.setKeyFitness(tempScore);  //Set initial fitness from parent probability score
		System.out.println("Initial Parent key score: -> "+ tempScore);
		
		//simulated annealing nested for loops for temperature and transitions traversal.
		for (int i = temperature; i > 0; i-= stepSize) {
			for (int j = transitions; j > 0; j--) {
				childKey = ks.shuffleKey(parentKey);
				double childFitness = 0;
				double deltaFitness;
				pfd.DecryptCipherText(digrams, childKey); //decrypt text with given key		
				parsetoKMers(pfd.getDecryptedMessage()); //parse decrypted text into 4-mers for checking against 4gram hash map
				
				//Check for the kmers that exist as keys in the 4-gram hashmap
				for (String key : kmers) {
					if (kMerCompareMap.keySet().contains(key)) {
						//calculate probability value and its log and add it to temp fitness score
						double probability = (Math.log10(kMerCompareMap.get(key).doubleValue()) / Math.log10(kmp.getTotal4GramCount()));
						childFitness += probability;
					}			
				}		
				//Calculate delta fitness to see which key is better
				deltaFitness = childFitness - this.getKeyFitness();
				if(deltaFitness > 0) {
					this.setParentKey(childKey);
					this.setKeyFitness(childFitness);
				}else if(deltaFitness < 0){  //Sometimes choose child key even if its worse than parent key to prevent local max situations
					double p = Math.pow(Math.E,(deltaFitness/temperature));
					if (p > 0.5) {
						this.setParentKey(childKey);
						this.setKeyFitness(childFitness);
					}
				}
			}
		}
		
		
		//Print out resultant final matrix of the key when deciphering is done with the runs
		ks.printMatrix();
	}
	
	//getter and setter sets for variables
	public String getParentKey() {
		return parentKey;
	}
	public void setParentKey(String parentKey) {
		this.parentKey = parentKey;
	}
	public String getChildKey() {
		return childKey;
	}
	public void setChildKey(String childKey) {
		this.childKey = childKey;
	}
	public int getTemperature() {
		return temperature;
	}
	public void setTemperature(int temperature) {
		this.temperature = temperature;
	}
	public int getTransitions() {
		return transitions;
	}
	public void setTransitions(int transitions) {
		this.transitions = transitions;
	}
	public int getStepSize() {
		return stepSize;
	}
	public void setStepSize(int stepSize) {
		this.stepSize = stepSize;
	}

	public double getKeyFitness() {
		return keyFitness;
	}

	public void setKeyFitness(double keyFitness) {
		this.keyFitness = keyFitness;
	}

	//return decrypted text. I am simply delegating it to the playfair decryptor class.
	public String getDecryptedText() {
		return pfd.getDecryptedMessage();
	}

}
