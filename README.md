# AI_Playfair_Project
This is AI module project for deciphering Playfair cipher using simulated annealing algorithm

Mindaugas Vainauskas, 4th year Software Development course, Artificial Intelligence module, GMIT 2018

## Project description

This project is a command line application that processes encrypted message from .txt file that has been encrypted using Playfair cipher. This file must be in same folder as an executable jar file, which runs the application, and 4grams.txt file, which contains the 4 character blocks frequently found in english language and as such, is used as basis to decipher the message.

## Installation

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
