package ie.gmit.sw.ai;

public class DecipherRunner {
	
	public static void main(String[] args) {
		//Playfair_Decryptor pfc = new Playfair_Decryptor();
		//FileReaderClass frc = new FileReaderClass("text2.txt");
		
		//K_Mer_Parser kmp = new K_Mer_Parser();
		//String cipherText = null;
				
		//parse text file
		//cipherText = frc.parseTextFile();

		//pfc.setCipherKey("THEQUICKBROWNFXMPDVLAZYGS");
		//send cipher text to playfair decryptor
		//pfc.setEncryptedMessage(cipherText);
		
		SA_Cipher_Breaker sacb = new SA_Cipher_Breaker(500, 10000, 1, "text2.txt");
		System.out.println("Parent key -> "+sacb.getParentKey());	
		sacb.cipherBreakerSA();
		
		//pfc.DecryptCipherText(pfc.createDigrams(cipherText), pfc.getCipherKey());
		//should return deciphered text
		//System.out.println("Deciphered text: "+pfc.getDecryptedMessage());
		
		//kmp.map4GramFile("4grams.txt");
		//System.out.println(kmp.getfGramMap());
	}


}
