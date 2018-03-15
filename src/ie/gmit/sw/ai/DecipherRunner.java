package ie.gmit.sw.ai;

public class DecipherRunner {
	//Declare alphabet of 25 characters used for initial setup of key.
	private  static final String ALPHABET = "ABCDEFGHIKLMNOPQRSTUVWXYZ";
	
	public static void main(String[] args) {
		PlayFairCipher pfc = new PlayFairCipher("THEQUICKBROWNFXMPDVLAZYGS");
		FileReaderClass frc = new FileReaderClass("text2.txt");
		
		KeyShuffler ks = new KeyShuffler();
		K_Mer_Parser kmp = new K_Mer_Parser();
		String cipherText = null;
				
		//parse text file
		cipherText = frc.parseTextFile();

		//send cipher text to playfair decrypter
		pfc.setEncryptedMessage(cipherText);
		
		//set up initial random key
		//ks.setCipherKey(ALPHABET);
		//Set up cipher matrix into 5x5 grid
		//ks.shuffleKey();
		//System.out.println("Initial random key -> "+ks.getCipherKey());
		//System.out.println("Child key -> "+ks.getChildCipherKey());
		//should return deciphered text
		//System.out.println("Deciphered text: "+pfc.DecryptCipherText(pfc.createDigrams(cipherText), pfc.getCipherKey()));
		
		kmp.map4GramFile("4grams.txt");
		System.out.println(kmp.getfGramMap());
	}


}
