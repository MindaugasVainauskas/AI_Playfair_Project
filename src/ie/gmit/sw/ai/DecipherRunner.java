package ie.gmit.sw.ai;

import java.util.Scanner;

import com.sun.glass.ui.Application;

public class DecipherRunner {
	private static DecipheredFileWriter dfw;
	
	public static void main(String[] args) {
		final int SENTINEL = -1;
		int choice = 0;
		String fName = "";
		int tracknumber = 0;
		int temperature = 0;
		int steps = 0;
		int stepSize = 0;
		dfw = new DecipheredFileWriter();

		Scanner input = new Scanner(System.in);
		do {
			mainMenu();
			System.out.println();
			choice = input.nextInt();
			
			switch (choice) {
			case 1:
				System.out.println("Enter name of file to decipher. Don't add extensions!\n");
				fName = input.next();
				fName = "..\\"+fName+".txt";
				
				System.out.println("Enter temperature number");
				temperature = input.nextInt();
				System.out.println("Enter transition count");
				steps = input.nextInt();
				System.out.println("Enter temperature step size");
				stepSize = input.nextInt();
				
				//set up new instance of cipher breaking class with temperature and run count
				SA_Cipher_Breaker sacb = new SA_Cipher_Breaker(temperature, steps, stepSize, fName);
				System.out.println("Initial Parent key -> "+sacb.getParentKey());	
				sacb.cipherBreakerSA();

				System.out.println(sacb.getDecryptedText());
				dfw.WriteDecipheredTextTofile(sacb.getDecryptedText());
				break;
			case 2:
				tracknumber = SENTINEL;
				System.out.println("Good Bye!");
				break;
			default:
				break;
			}
			
		} while (tracknumber != SENTINEL);
		
	}

	
	public static void mainMenu() {
		System.out.println("Please choose of the following options:");
		System.out.println("Press 1 to enter name of file to decrypt (File must be in same folder!)");
		System.out.println("press 2 to exit app");		
	}

}
