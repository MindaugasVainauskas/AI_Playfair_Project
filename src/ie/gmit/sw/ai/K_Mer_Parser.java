package ie.gmit.sw.ai;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class K_Mer_Parser {
	
	//declare hashmap for use with the 4gram text file
	private Map<String, Integer> fGramMap;
	private long total4GramCount;
	
	//name of file containing the 4grams
	private String fileName;
	
	public void map4GramFile(String fName) {
		fGramMap = new HashMap<String, Integer>();
		fileName = fName;
		long temp = 0;
		FileReader fr;
		//read 4 gram file from file with given file name.
		try {
			fr = new FileReader(fileName);
			Scanner scanner = new Scanner(fr);
			BufferedReader bfr = new BufferedReader(fr);
			String line;
			while ((line = bfr.readLine()) != null) {
				//split every line into key value pairs by splitting it by white spaces
				String[] columns = line.split(" ");
				
				if (columns.length >= 2) {
					String key = columns[0];
					int value = Integer.parseInt(columns[1]);
					fGramMap.put(key,value);
					//set the sum of available 4-grams so far
					temp += value;
				}		        				
			}
						
			//set the total number of 4-grams from text file. (Could have it hardcoded but where's the fun in that? Also handy if 4-gram file is changed)
			setTotal4GramCount(temp);
			
			scanner.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
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

	public long getTotal4GramCount() {
		return total4GramCount;
	}

	public void setTotal4GramCount(long total4GramCount) {
		this.total4GramCount = total4GramCount;
	}

}
