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
		
		SA_Cipher_Breaker sacb = new SA_Cipher_Breaker(10, 5000, 1, "text2.txt");
		System.out.println("Parent key -> "+sacb.getParentKey());	
		sacb.cipherBreakerSA();
		
		System.out.println(sacb.getDecryptedText());
	}


}
