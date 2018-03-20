package ie.gmit.sw.ai;

import java.util.HashMap;
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
	
	private KeyShuffler ks;
	private Playfair_Decryptor pfd;
	private FileReaderClass frc;
	private K_Mer_Parser kmp;	
	
	private Map<String, Integer> kMerMap;
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
			String kmers[] = new String[encMessage.length()];
			int i = 0;
			kMerMap = new HashMap<String, Integer>();
			
			//Set up 4-mer shingles for checking later. Will shift them 2 characters at a time with the length of 4.
			// This approach should hopefully give better accuracy than jumping 4 characters at a time but more speed than 1 character shift approach.
			for (int j = 0; j < encMessage.length()-2; j = j + 2) {
				 kmers[i] = encMessage.substring(j, j + 4);
				 
		         if(kMerMap.containsKey(kmers[i])) {
		        	 kMerMap.put(kmers[i], kMerMap.get(kmers[i])+1);
		         }else {
		        	 kMerMap.put(kmers[i], 1);
		         }		         
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
		
		for (String key : kMerMap.keySet()) {
			if (kMerCompareMap.keySet().contains(key)) {
				//calculate probability value and its log and add it to temp fitness score
				double probability = Math.log10((kMerCompareMap.get(key).doubleValue() / kmp.getTotal4GramCount()));
				tempScore += probability;
			}			
		}		
		setKeyFitness(tempScore);
		System.out.println("Total number in 4-gram text file: "+kmp.getTotal4GramCount());
		System.out.println("4-Gram score: -> "+ tempScore);
		
		//simulated annealing nested for loops for temperature and transitions traversal.
		for (int i = temperature; i > 0; i-= stepSize) {
			for (int j = transitions; j > 0; j--) {
				childKey = ks.shuffleKey(parentKey);
				double childFitness = 0;
				double deltaFitness;
				pfd.DecryptCipherText(digrams, childKey); //decrypt text with given key		
				parsetoKMers(pfd.getDecryptedMessage()); //parse decrypted text into 4-mers for checking against 4gram hash map
				//check calculated map for k-mers that exist in original kMer compare map.
				
				for (String key : kMerMap.keySet()) {
					if (kMerCompareMap.keySet().contains(key)) {
						//calculate probability value and its log and add it to temp fitness score
						double probability = Math.log10((kMerCompareMap.get(key).doubleValue() / kmp.getTotal4GramCount()));
						childFitness += probability;
					}			
				}		
				
				deltaFitness = childFitness - getKeyFitness();
				//System.out.println(deltaFitness);
				if(deltaFitness > 0) {
					this.setParentKey(childKey);
				}else if(deltaFitness < 0){
					double p = Math.pow(Math.E,(deltaFitness/temperature));
					System.out.println(p);
					if (p > 0.5) {
						this.setParentKey(childKey);
					}
				}
			}
		}
		
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

	public String getDecryptedText() {
		return pfd.getDecryptedMessage();
	}

	public void setDecryptedText(String decryptedText) {
		this.decryptedText = decryptedText;
	}

}
