package ie.gmit.sw.ai;

//This class is the playfair decryptor that runs text against given key and sends it back
//Based on:
//https://github.com/anushshrestha/Cryptography/blob/master/Cryptography/src/main/java/com/silentflutes/cryptography/Playfair/Playfair.java

public class Playfair_Decryptor {
	
	private String cipherKey;	
	
	//hardcoded for now. will pull from file later 
	private String encryptedMessage;
	
	private String decryptedMessage;
	
	//set cipher key to be the selected formatted keystring
	public void setCipherKey(String keyString) {
		this.cipherKey = keyString;
	}
	
	public String getCipherKey() {
		return this.cipherKey;
	}

	public String getEncryptedMessage() {
		return encryptedMessage;
	}

	public void setEncryptedMessage(String encryptedMessage) {
		this.encryptedMessage = encryptedMessage;
	}

	public String getDecryptedMessage() {
		return decryptedMessage;
	}

	public void setDecryptedMessage(String decryptedMessage) {
		this.decryptedMessage = decryptedMessage;
	}

	//create character pairs from ciphertext.
	public String[] createDigrams(String encMessage) {
		String digrams[] = new String[encMessage.length() /2];
		int i = 0;
		
		for (int j = 0; j < encMessage.length()-1; j = j + 2) {
			 digrams[i] = encMessage.substring(j, j + 2);
	         i++;
		}
		
		return digrams;
	}
	//Decryption method to set decrypted message value when done
	public void DecryptCipherText(String[] digrams, String cKey) {
		StringBuilder plainText = new StringBuilder();
		//System.out.println(cKey);
		for (String pair : digrams) {
			int row1 = (cKey.indexOf(pair.charAt(0)) / 5);
            int col1 = (cKey.indexOf(pair.charAt(0)) % 5);
            int row2 = (cKey.indexOf(pair.charAt(1)) / 5);
            int col2 = (cKey.indexOf(pair.charAt(1)) % 5);
            
            char chr1;
            char chr2;
            
            if (col1 == col2) {
                chr1 = cKey.charAt((row1 + 4) % 5 * 5 + col1);
                chr2 = cKey.charAt((row2 + 4) % 5 * 5 + col2);
            } else if (row1 == row2) {
                chr1 = cKey.charAt(row1 * 5 +(col1 +4) % 5);
                chr2 = cKey.charAt(row2 * 5 +(col2 +4) % 5);
            } else {
                chr1 = cKey.charAt(row1 * 5 + col2);
                chr2 = cKey.charAt(row2 * 5 + col1);
            }
            
            plainText.append(Character.toString(chr1) + Character.toString(chr2));
		}
		
		//Set the decrypted text to be assesed
		setDecryptedMessage(plainText.toString());
	}
}
