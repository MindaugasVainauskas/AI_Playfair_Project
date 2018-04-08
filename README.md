# AI_Playfair_Project
This is AI module project for deciphering Playfair cipher using simulated annealing algorithm

Mindaugas Vainauskas, 4th year Software Development course, Artificial Intelligence module, GMIT 2018

## Project description

This project is a command line application that processes encrypted message from .txt file that has been encrypted using Playfair cipher. This file must be in same folder as an executable jar file, which runs the application, and 4grams.txt file, which contains the 4 character blocks frequently found in english language and as such, is used as basis to decipher the message.

## Short history of Playfair cipher

The Playfair Cipher is a symmetric polygram substitution cipher invented by the Victorian scientist Sir Charles Wheatstone in 1854 (of Wheatstone Bridge fame). The cipher is named after his colleague Lord Playfair, who popularised and promoted the encryption system. Due to its simplicity, the Playfair Cipher was used at a tactical level by both the British and US forces during WWII and is also notable for its role in the rescue of the crew of PT-109 in the Pacific in 1943.

Polygram substitution is a classical system of encryption in which a group of n plain-text letters is replaced as a unit by n cipher-text letters. In the simplest case, where n = 2, the system is called digraphic and each letter pair is replaced by a cipher digraph. The Playfair Cipher uses digraphs to encrypt and decrypt from a 5x5 matrix constructed from a sequence key of 25 unique letters. Note that the letter J is not included. 

## Simulated Annealing algorithm

Simulated annealing (SA) is an excellent approach for breaking a cipher using a randomly generated key. Unlike conventional Hill Climbing algorithms, that are easily side-tracked by local optima, SA uses randomization to avoid heuristic plateaux and attempt to find a global maxima solution. It uses temperature(temp) andtransitions variables ina nested for loop format to run through entire transition count. Note that the initial value of the variables temp and transitions can have a major impact on the success of the SA algorithm. Both variables control the cooling schedule of SA and should be experimented with for best results

## Application installation

 - Download CipherBreaker_app folder which contains the .java classes in src folder and 4grams.txt file in the root folder;
 - Navigate to src folder in the command line;
 - Compile the code using command: *javac ie/gmit/sw/ai/\*.java*;
 - Run following command to create an executable jar file: *jar –cf playfair.jar ie/gmit/sw/ai/\*.class*;
 
 An Executable .jar file named playfair.jar should now be created in the src folder and with that the installation process is finished.
 
 ## Running the application
 - Add encrypted message in .txt format file to the root folder of the application (same folder that contains 4grams.txt file)
 - Navigate to folder with the jar file created previously in the command line terminal;
 - Run this jar file with comand: *java –cp ./playfair.jar ie.gmit.sw.ai.CipherBreaker*;
 - A menu is shown with 2 options to either enter a file name or exit application;
 - Enter 1 on a keyboard to be able to enter a file name to decipher;
 - Enter file name that you want decrypted. Please note to enter only name itself and no suffixes!;
 
 
 A new Menu is shown where temperature number, amount of transitions and step size are required to be entered. This is to allow different variations of runs to be run on different length ciphered messages.
 - User enters the desired numbers for the above values
 - Initial Parent key is displayed;
 - Length of a 4gram array created from encrypted messages is displayed;
 - Initial score of first pass over encrypted message with initial parent key is displayed;
 - Application runs for varying amount of time depending on amount of transitions required;
 - Once finished, application prints out final key matrix used in a 5x5 table format and a resultant decrypted message text. Decrypted text is also written out into a filew in root folder;
 - Menu to either start entering encrypted message file name or exit application is displayed;
 - User can select to either enter 1 to enter encrypted file name again or enter 2 to exit application;
 - If option 2 has been selected, Good bye message is shown and application exits.
