package ie.gmit.sw.ai;

public class DecipherRunner {
	//Declare alphabet of 25 characters used for initial setup of key.
	private  static final String ALPHABET = "ABCDEFGHIKLMNOPQRSTUVWXYZ";
	
	public static void main(String[] args) {
		PlayFairCipher pfc = new PlayFairCipher("THEQUICKBROWNFXMPDVLAZYGS");
		FileReaderClass frc = new FileReaderClass("text2.txt");
		
		SimulatedAnnealing sa = new SimulatedAnnealing();
		String cipherText = null;
				
		//parse text file
		cipherText = frc.parseTextFile();

		//send cipher text to playfair decrypter
		pfc.setEncryptedMessage(cipherText);
		
		//set up initial random key
		sa.setCipherKey(ALPHABET);
		//Set up cipher matrix into 5x5 grid
		sa.shuffleKey();
		System.out.println("Initial random key -> "+sa.getCipherKey());
		System.out.println("Child key -> "+sa.getChildCipherKey());
		//should return deciphered text
		System.out.println("Deciphered text: "+pfc.DecryptCipherText(pfc.createDigrams(cipherText), pfc.getCipherKey()));
//		System.out.println("Deciphered text: "+pfc.DecryptCipherText(pfc.createDigrams(cipherText), sa.getChildCipherKey()));
	}


}
