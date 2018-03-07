package ie.gmit.sw.ai;

public class PlayFairCipher {
	
	private String cipherKey;	
	
	//hardcoded for now. will pull from file later 
	private String encryptedMessage = "HFZQLYVEDWNITIQPQDUVHYLGXZHFNYBKPACAZQHFVQIQCUUVYCBXABQZQZURHQDZHBKDMVZQHXRGURLQHTXZQVDFYXZHRGGWHBYEGXNYYEGKYVHFLQDBWDVQIZEAUCAHHPQIBRRVBREZNYYQAHPUQDUVHYZXGNRDEOZWQFKCLZZHXVRDEOFEINQZZKZPKDYDCAMEEQUDBCLDBKPAEDUVYCHFZQQEUMSVPBUMURLQHTXZXZCUHTVTPHMDLDRGMDLDVBHCMGUVYCQVPVDMSZXQCPDIQZLQKDUBEMTCYDDBCQGDFEUKQZVPCYUHKDIABDFVFEETGKIDOZEFURLQUVYCKDPTACYQUCFUPVVBBREZZXDTZPWCMEDILYTHZHADMUDBGQHBKIFEMDEWIZRGVQHTKCNWIEGNHCPLLUDPCOFTQGDPNWBYHCHFQZITQVGKUVYCHFBDQVHVHCHFDIYXHFBRUMLZKDZDFQFHNYLGSAPLQCCAZQHCPCBODITCVBMUHFDIYXHFBRUMLZKDLULIDLIDDLQRKWZQACYQUZBHZBDUBHQZUKUZEDGWTVBXABQZQZBUFEUFFTQVEKZQINAHMEPTDFNYFBIZEXBRRVBREZTCILEVFBEDHUBRWDLYTHFHIZNYCPOVBDLIZQHFQPQDUVHYLGCUNYOKDMPCHTXZPCGCHFDYLQDBLTHPQEKCGKTIQIBRVQHBQNDBRXBZEFRFVUEDQYNYMZCPBDHYLKCUXF";
	
	public PlayFairCipher() {
		setCipherKey("THEQUICKBROWNFXMPDVLAZYGS");
	}	

	//set cipher key to be the selected formatted keystring
	public void setCipherKey(String keyString) {
		this.cipherKey = keyString;
	}
	
	//create character pairs from ciphertext.
	public String[] createDigrams() {
		String digrams[] = new String[encryptedMessage.length() /2];
		int i = 0;
		
		for (int j = 0; j < encryptedMessage.length(); j = j + 2) {
			 digrams[j] = encryptedMessage.substring(j, j + 2);
	         i++;
		}
		
		return digrams;
	}
	
	
	//Decryption method to return plain text
	public String DecryptCipherText(String[] digrams, String cKey) {
		StringBuilder plainText = new StringBuilder();
		
		for (String digram : digrams) {
			 byte row1 = (byte) (cipherKey.indexOf(digram.charAt(0)) / 5);
	            byte col1 = (byte) (cipherKey.indexOf(digram.charAt(0)) % 5);
	            byte row2 = (byte) (cipherKey.indexOf(digram.charAt(1)) / 5);
	            byte col2 = (byte) (cipherKey.indexOf(digram.charAt(1)) % 5);
	            //System.out.println("char 1 " + pair.charAt(0) + "  at " + row1 + "   " + col1);
	            //System.out.println("char 2 " + pair.charAt(1) + "  at " + row2 + "   " + col2);

	            char chr1;
	            char chr2;
	            if (col1 == col2) {
	                chr2 = cipherKey.charAt(((row2 - 1) % 5 * 5 + col2));
	                chr1 = cipherKey.charAt(((row1 - 1) % 5 * 5 + col1));
	            } else if (row1 == row2) {
	                chr1 = cipherKey.charAt(row1 * 5 + ((col1 - 1) % 5));
	                chr2 = cipherKey.charAt(row2 * 5 + ((col2 - 1) % 5));
	            } else {
	                chr1 = cipherKey.charAt(row1 * 5 + col2);
	                chr2 = cipherKey.charAt(row2 * 5 + col1);
	            }
	            plainText.append(Character.toString(chr1) + Character.toString(chr2));
	        }
		return plainText.toString();
	}
	
	public static void main(String[] args) {
		PlayFairCipher pfc = new PlayFairCipher();
		
		
		//should return deciphered text
		//pfc.DecryptCipherText(digrams, cKey)
	}

}
