package ie.gmit.sw.ai;

//This class looks after writing out deciphered text to the file in same folder

import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class DecipheredFileWriter {
	private  PrintWriter out;
	private String fName = "..\\DecryptedText.txt";
	
	//Method to write out deciphered text to file with given name.
	public void WriteDecipheredTextTofile(String text) {
		try {
			out = new PrintWriter(fName);  //instantiate new print writer
			out.println(text);  //Write out deciphered text to the file 			
			out.close();  //Close the print writer at the end
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
