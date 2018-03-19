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
	
	private Map<String, Integer> kMerMap;
	
	public SA_Cipher_Breaker(int temp, int transits, int stSize, String fName) {
		ks = new KeyShuffler();
		ks.setParentCipherKey(ALPHABET); //set initial parent cipher key from given alphabet
		pfd = new Playfair_Decryptor();
		frc = new FileReaderClass(fName);
		pfd.setEncryptedMessage(frc.parseTextFile()); //parse the initial encrypted text file. Only needed once so I do it in constructor for now.
		
		this.setTemperature(temp);
		this.setTransitions(transits);
		this.setStepSize(stSize);
		
		//set parent key to be the initial random cipher key that key shuffler generates
		this.setParentKey(ks.getParentCipherKey());
	}
	
	//create character g-grams from returned text.
		public String[] parsetoKMers(String encMessage) {
			String kmers[] = new String[encMessage.length() /4];
			int i = 0;
			kMerMap = new HashMap<String, Integer>();
			
			for (int j = 0; j < encMessage.length()-1; j = j + 4) {
				 kmers[i] = encMessage.substring(j, j + 4);
		         
		         if(kMerMap.containsKey(kmers[i])) {
		        	 kMerMap.put(kmers[i], kMerMap.get(kmers[i])+1);
		         }else {
		        	 kMerMap.put(kmers[i], 1);
		         }
		         
		         i++;
			}
			
			return kmers;
		}
	
	public void cipherBreakerSA() {		
		String[] digrams = pfd.createDigrams(pfd.getEncryptedMessage()); //set up digrams from ciphered text
		String tKey = ks.getParentCipherKey(); //get temporary key from parent key(child key in for loops)
		pfd.DecryptCipherText(digrams, tKey); //decrypt text with given key
		//return deciphered(hopefully) text and split into 4grams(k-mers)
		//keyFitness = log(K-Mer sum?)
		
		String[] kMers = parsetoKMers(pfd.getDecryptedMessage());
		//check calculated map for k-mers
		for (String key : kMerMap.keySet()) {
			System.out.println(key + " "+ kMerMap.get(key).intValue());
		}
		//simulated annealing nested for loops for temperature and transitions traversal.
//		for (int i = temperature; i > 0; i-= stepSize) {
//			for (int j = transitions; j > 0; j-= stepSize) {
//				
//			}
//		}
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

}
