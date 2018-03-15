package ie.gmit.sw.ai;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class K_Mer_Parser {
	
	//declare hashmap for use with the 4gram text file
	private Map<String, Integer> fGramMap;
	
	//name of file containing the 4grams
	private String fileName;
	
	public void map4GramFile(String fName) {
		fGramMap = new HashMap<String, Integer>();
		fileName = fName;
		
		FileReader fr;
		//read 4 gram file from file with given file name.
		try {
			fr = new FileReader(fileName);
			Scanner scanner = new Scanner(fr);
			
			while (scanner.hasNextLine()) {
				//split every line into key value pairs by splitting it by whitespaces
				String[] columns = scanner.nextLine().split("\\s+");

		        fGramMap.put(columns[0],Integer.parseInt(columns[1]));				
			}
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	public Map<String, Integer> getfGramMap() {
		return fGramMap;
	}

	public void setfGramMap(Map<String, Integer> fGramMap) {
		this.fGramMap = fGramMap;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

}
