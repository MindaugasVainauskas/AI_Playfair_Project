//This class is responsible for reading in the text from file to be processed by decryptor.
package ie.gmit.sw.ai;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class FileReaderClass {
	
	private String fileName;
	
	public FileReaderClass(String fName) {
		this.setFileName(fName);
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	//Parse the text from file and return it as a string.
	public String parseTextFile() {
		
		String textString = null;
		try {
			FileReader fr = new FileReader(fileName);
			BufferedReader reader = new BufferedReader(fr);
			StringBuffer sb = new StringBuffer();
			
			String lineText = null;
			
			while ((lineText = reader.readLine()) != null) {
				sb.append(lineText);				
			}
			
			textString = sb.toString();
			
			//Close out buffered reader
			reader.close();
		} catch (FileNotFoundException e) {
			System.out.println("No such File Found!");
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//return resultant string.
		return textString;
	}

}
