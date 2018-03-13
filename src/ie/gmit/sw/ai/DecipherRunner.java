package ie.gmit.sw.ai;

public class DecipherRunner {
	//Declare alphabet of 25 characters used for initial setup of key.
	private  static final String ALPHABET = "ABCDEFGHIKLMNOPQRSTUVWXYZ";
	
	public static void main(String[] args) {
		PlayFairCipher pfc = new PlayFairCipher("THEQUICKBROWNFXMPDVLAZYGS");
		FileReaderClass frc = new FileReaderClass("text2.txt");
		
		SimulatedAnnealing sa = new SimulatedAnnealing();
		String cipherText = null;
				
		cipherText = frc.parseTextFile();

		pfc.setEncryptedMessage(cipherText);
		
		sa.setCipherKey(ALPHABET);
		
		System.out.println("Randomized initial key: -> "+sa.getCipherKey());
		
		//should return deciphered text
		System.out.println("Deciphered text: "+pfc.DecryptCipherText(pfc.createDigrams(cipherText), pfc.getCipherKey()));
	}


}
