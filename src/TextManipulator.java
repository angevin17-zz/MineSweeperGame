
//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:           Text Manipulator
// Files:           MineSweeper and Config
// Course:          CS 200 Fall Semester 2017
//
// Author:          Nick Fonte
// Email:           nfonte@wisc.edu
// Lecturer's Name: Marc Renualt
// 
//
///////////////////////////// CREDIT OUTSIDE HELP /////////////////////////////
//
// Students who get help from sources other than their partner must fully 
// acknowledge and credit those sources of help here.  Instructors and TAs do 
// not need to be credited here, but tutors, friends, relatives, room mates 
// strangers, etc do.  If you received no outside help from either type of 
// source, then please explicitly indicate NONE.
//
// Persons:         (identify each person and describe their help in detail)
// Online Sources:  (identify each URL and describe their assistance in detail)
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////

import java.util.ArrayList;
import java.util.Scanner;
import java.io.PrintWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

//FIXME class header comment

public class TextManipulator {

	/**
	 * Matches the case of the original string to that of the template as follows.
	 *
	 * 1) If the length of template is the same or longer than the length of the
	 * original, the returned string will match the case of the prefix of the
	 * template up to the length of the original string. For example: template:
	 * XXxxxXxX original: azertYU returns: AZertYu
	 *
	 * 2) If the length of the template is shorter than the length of the original,
	 * the prefix of the returned string up to the length of the template will match
	 * the case of the template. The remaining characters should all be lower case.
	 * For example: template: WxCv original: azertYU returns: AzErtyu
	 *
	 * @param template
	 *            Template of which characters should be which case.
	 * @param original
	 *            String to change the case of, based on the template.
	 * @return A string containing the characters of original with the case matching
	 *         that of template.
	 */
	public static String matchCase(String template, String original) {
		String fixed = "";
		if (template.length() >= original.length()) { 
			for (int i = 0; i < original.length(); i++) {
				if ((template.charAt(i) == ' ')) {
					fixed += Character.toLowerCase(original.charAt(i));
				}
				if (Character.isUpperCase(template.charAt(i))) {
					fixed += Character.toUpperCase(original.charAt(i));
				} else if (Character.isLowerCase(template.charAt(i))) {
																		
					fixed += Character.toLowerCase(original.charAt(i));
					} else if (template.charAt(i) == '\'') {
					fixed += Character.toLowerCase(original.charAt(i));
																		
				}
			}
			return fixed; 
		}
		if (template.length() < original.length()) {
			for (int i = 0; i < original.length(); i++) {
				if (i < template.length()) {
					if (template.charAt(i) == ' ') {
						fixed += Character.toLowerCase(original.charAt(i));
					}
					if (Character.isUpperCase(template.charAt(i))) {
						fixed += Character.toUpperCase(original.charAt(i));
					} else if (Character.isLowerCase(template.charAt(i))) {
						fixed += Character.toLowerCase(original.charAt(i));
					} else if (template.charAt(i) == '\'') {
						fixed += Character.toLowerCase(original.charAt(i));
					}
				} else if (i >= template.length()) {
													
													
					fixed += Character.toLowerCase(original.charAt(i));
				}
			}
			return fixed;
		}
		return fixed;
	}

	/**
	 * Translates a word according to the data in wordList then matches the case.
	 * The parameter wordList contains the mappings for the translation. The data is
	 * organized in an ArrayList containing String arrays of length 2. The first
	 * cell (index 0) contains the word in the original language, called the key,
	 * and the second cell (index 1) contains the translation.
	 *
	 * It is assumed that the items in the wordList are sorted in ascending order
	 * according to the keys in the first cell.
	 *
	 * @param word
	 *            The word to translate.
	 * @param wordList
	 *            An ArrayList containing the translation mappings.
	 * @return The mapping in the wordList with the same case as the original. If no
	 *         match is found in wordList, it returns a string of Config.LINE_CHAR
	 *         of the same length as word.
	 */
	public static String translate(String word, ArrayList<String[]> wordList) {
		String translatedWord = "";
		String lineOfChar = "";
		String newWord = word.toLowerCase();

		if (!Character.isLetter(word.charAt(0))) {
					return word;
		}

		for (int i = 0; i < wordList.size(); i++) {
			for (int j = 0; j < wordList.get(i).length; j++) {
				if (newWord.equals((wordList.get(i))[j])) {
					translatedWord = (wordList.get(i))[j + 1];
				
					break;
				}
			}
			if (!(translatedWord.isEmpty())) {
				break;
			}
		}
		if (translatedWord.isEmpty()) {
			for (int i = 0; i < word.length(); i++) {
				lineOfChar += Config.LINE_CHAR;
			}
			return lineOfChar;
		} else {
			return translatedWord;
		}
	}

	/**
	 * Converts a string to simplified Pig Latin then matching the case. The rules
	 * for simplified Pig Latin are as follows: 1) If the word begins with a vowel
	 * (a, e, i, o, u, or y), then the string "way" is appended. 2) If the word
	 * begins with a consonant (any letter that is not a vowel as defined above),
	 * then the group of consonants at the beginning of the word are moved to the
	 * end of the string, and then the string "ay" is appended. 3) If the word
	 * begins with any other character, the original string is returned. Note 1: 'y'
	 * is always considered a vowel. Note 2: An apostrophe that is not the first
	 * character of a word is treated as a consonant.
	 *
	 * Some examples: Pig -> Igpay Latin -> Atinlay Scram -> Amscray I'd -> I'dway
	 * you -> youway crypt -> yptcray
	 *
	 * @param word
	 *            The word to covert to simplified Pig Latin.
	 * @return The simplified Pig Latin of the parameter word with matching case.
	 */
	public static String pigLatin(String word) {
		String tempWord = word.toLowerCase();
		String finishedString = "";
		char vowel; 
		String firstPart = "";
		boolean change = true;
		char one = tempWord.charAt(0);
		if (((one == 'a') || (one == 'e') || (one == 'i') || (one == 'o') || (one == 'u') || (one == 'y'))) {
			tempWord = tempWord + "way";
			finishedString = tempWord;
		} else if ((one != 'a') && (one != 'e') && (one != 'i') && (one != 'o') && (one != 'u') && (one != 'y')
				&& ((Character.isLetter(one)) || (one == '\''))) {
			for (int i = 0; i < tempWord.length(); i++) {
				vowel = tempWord.charAt(i);
				if (change) {
					if (((vowel != 'a') && (vowel != 'e') && (vowel != 'i') && (vowel != 'o') && (vowel != 'u')
							&& (vowel != 'y'))) {
						firstPart += vowel;
						continue;
					} else if (((vowel == 'a') || (vowel == 'e') || (vowel == 'i') || (vowel == 'o') || (vowel == 'u')
							|| (vowel == 'y'))) {
						change = false;
					}
				}
				finishedString += vowel;
			}
			finishedString += firstPart;
			finishedString += "ay";			
		} else {
			finishedString = word;
									
			if (finishedString.charAt(0) == '.' || finishedString.charAt(0) == '!') {
				return finishedString;
			}
		}
		finishedString = matchCase(word, finishedString);
		return finishedString;
	}

	/**
	 * Reverses a String, then matches the case. For example: aZErty returns yTReza
	 *
	 * @param word
	 *            The String to reverse.
	 * @return The reverse of word with matching case.
	 */
	public static String reverse(String word) {
		String tempWord = word.toLowerCase();// temp word is the original word lower cased
		String tempString = "";// temporary string that will be returned
		for (int i = word.length() - 1; i >= 0; i--) {
			tempString = tempString + tempWord.charAt(i);// reversing word
		}
		if (tempWord.charAt(0) == '.' || tempWord.charAt(0) == '!') {// if the word is a '.' or '!' return that
			return (word);
		}
		return (matchCase(word, tempString));// make sure the case is right
	}

	/**
	 * Builds a new ArrayList of Strings that contains the items of the ArrayList
	 * passed in, arrL, but in reverse order.
	 *
	 * @param arrL
	 *            The ArrayList of Strings to reverse.
	 * @return A new ArraList of Strings that is the reverse of arrL.
	 */
	public static ArrayList<String> reverse(ArrayList<String> arrL) {
		ArrayList<String> temp = new ArrayList<String>();// temporary arrayList<String>
		String tempString = "";// temporary string that will get added to the above temp

		for (int i = arrL.size() - 1; i >= 0; i--) {

			tempString = arrL.get(i);// switch items in the arrayList
			temp.add(tempString);
		}

		return temp;
	}

	/**
	 * The method that displays the main program menu and reads the user's menu
	 * choice. The full menu has the following format where the (assuming that
	 * Config.MISSING_CHAR is '-'):
	 * 
	 * --------------------------------------------------------------------------------
	 * Text Manipulator Program
	 * --------------------------------------------------------------------------------
	 * Current input file: --- Current output file: --- Current dictionary: ---
	 * Current mode: Interleaved Current mods: TPWL
	 * --------------------------------------------------------------------------------
	 * Main menu: 1) Display Output 2) Save Output Manipulations: T)ranslate P)ig
	 * latin W)ord reverse L)ine reverse Configuration: I)nput file. O)utput file.
	 * D)ictionary file. M)ode Toggle. H)ide/show Menu. Q)uit Enter action:
	 *
	 * Notes: - The lines consist of 80 Config.LINE_CHAR characters. - The input
	 * file, output file and dictionary names are 3 Config.LINE_CHAR characters if
	 * appropriate value in files is null, otherwise display the appropriate value
	 * in files. - The Current mode displays "Interleaved" when modeBoth is true and
	 * "Modified" when false. - The Current mods displays (in the following order)
	 * 'T' if translate is toggled, 'P' if Pig Latin is toggled, 'W' if word reverse
	 * is toggled, and 'L' if line reverse is toggled. - The manipulation and
	 * configuration options are preceded by a single tab. - There is no new line
	 * following the final "Enter action: " prompt.
	 *
	 * @param sc
	 *            The reference to the Scanner object for reading input from the
	 *            user.
	 * @param files
	 *            A string array containing the input file name, the output file
	 *            name, and the dictionary file name in that exact order. The
	 *            entries may be null. The length of the array is Config.NUM_FILES.
	 *            The input file name is at index Config.FILE_IN, the output file
	 *            name is at index Config.FILE_OUT, and the dictionary file name is
	 *            at index Config.FILE_DICT.
	 * @param modFlags
	 *            A boolean array where the values are true if the given
	 *            modification is set to be applied.
	 * @param modeBoth
	 *            True if the display command will alternate lines from the modified
	 *            text and the original text.
	 * @param showMenu
	 *            If true the entire menu is shown, otherwise only the "Enter
	 *            Action: " line is shown.
	 * @return The first character of the line inputed by the user.
	 */
	public static char promptMenu(Scanner sc, String[] files, boolean[] modFlags, boolean modeBoth, boolean showMenu) {
		String threeBlank = Config.LINE_CHAR + "" + Config.LINE_CHAR + "" + Config.LINE_CHAR;

		if (showMenu == true) {
			print80();
			System.out.println("Text Manipulator Program");
			print80();
			System.out.print("Current input file: ");
			if (files[Config.FILE_IN] == null) {
				System.out.println(threeBlank);
			} else if (files[Config.FILE_IN] != null) {
				System.out.println(files[Config.FILE_IN]);
			}
			System.out.print("Current output file: ");
			if (files[Config.FILE_OUT] == null) {
				System.out.println(threeBlank);
			} else if (files[Config.FILE_OUT] != null) {
				System.out.println(files[Config.FILE_OUT]);
			}
			System.out.print("Current dictionary: ");
			if (files[Config.FILE_DICT] == null) {
				System.out.println(threeBlank);
			} else if (files[Config.FILE_DICT] != null) {
				System.out.println(files[Config.FILE_DICT]);
			}
			System.out.print("Current mode: ");
			if (modeBoth == true) {
				System.out.println("Interleaved");
			} else if (modeBoth == false) {
				System.out.println("Modified");
			}
			System.out.print("Current mods: ");
			if (modFlags[Config.MOD_TRANS] == true) {
				System.out.print("T");
			}
			if (modFlags[Config.MOD_PIG] == true) {
				System.out.print("P");
			}
			if (modFlags[Config.MOD_REV_WORD] == true) {
				System.out.print("W");
			}
			if (modFlags[Config.MOD_REV_LINE] == true) {
				System.out.print("L");
			}
			System.out.println();
			print80();
			System.out.println("Main menu:");// prints out the main menu
			System.out.println("1) Display Output");
			System.out.println("2) Save Output");
			System.out.println("Manipulations:");
			System.out.println("\tT)ranslate");
			System.out.println("\tP)ig latin");
			System.out.println("\tW)ord reverse");
			System.out.println("\tL)ine reverse");
			System.out.println("Configuration:");
			System.out.println("\tI)nput file.");
			System.out.println("\tO)utput file.");
			System.out.println("\tD)ictionary file.");
			System.out.println("\tM)ode Toggle.");
			System.out.println("\tH)ide/show Menu.");
			System.out.println("Q)uit");
			System.out.print("Enter action: ");
			String userInput = sc.nextLine();
			userInput = userInput.toUpperCase();
			userInput = userInput.trim();

			return userInput.charAt(0);
		} else {
			System.out.print("Enter action: ");
			String userInput = sc.nextLine();
			userInput = userInput.toUpperCase();
			userInput = userInput.trim();
			return userInput.charAt(0);
		}

	}

	/**
	 * Prompts the user for a new file name. The prompt should be: "Enter file name
	 * [curFileName]: ", where curFileName is the value from the parameter of the
	 * same name. There should not be a new line following the prompt.
	 *
	 * @param sc
	 *            The reference to the Scanner object for reading input from the
	 *            user.
	 * @param curFileName
	 *            The current file name.
	 * @return The value input by the user excluding any leading or trailing white
	 *         space. If the input is an empty string, then curFileName is returned.
	 */
	public static String updateFileName(Scanner sc, String curFileName) {
		System.out.print("Enter file name [" + curFileName + "]: ");
		curFileName = sc.nextLine();
		if (curFileName.length() == 0) {
			curFileName = null;
		}
		return curFileName;
	}

	/**
	 * Opens and reads the contents of the dictionary file specified in fileName.
	 * The file is assumed to be a text file encoded in UTF-8. It is assumed that
	 * there is one translation mapping per line. Each line contains a key and its
	 * translation separated by a tab. Note: The dictionary file is assumed to be
	 * sorted by the keys in ascending order.
	 *
	 * For each line in the dictionary file, an entry is added into wordList. The
	 * entry is a String array of length 2, where the value of index 0 is the key
	 * and the value of index 1 is the translation.
	 *
	 * When opening the file, any FileNotFoundException is caught and the error
	 * message "Exception: File 'fileName' not found." followed by a new line is
	 * output, where fileName is the name of the file that the method attempted to
	 * open.
	 *
	 * @param fileName
	 * @param wordList
	 *            Reference to ArrayList to contain the translation mappings.
	 * @throws IOException
	 *             if an I/O error occurs when closing the file.
	 *             FileNotFoundException is caught when opening the file.
	 */
	public static void readDictFile(String fileName, ArrayList<String[]> wordList) throws IOException {
		if (fileName == null) {
			return;
		}
		FileInputStream text = new FileInputStream(fileName);
		Scanner input = null;
		input = new Scanner(text);

		while (input.hasNext()) {
			
			String textDict = input.next();
				String textTrans = input.next();
				String[] temp = new String[2];
				temp[0] = textDict;
				temp[1] = textTrans;
				wordList.add(temp);
		}
		input.close();
	}

	/**
	 * Opens and reads the contents of the input file specified in fileName. The
	 * input file is read line by line. Each line is split into words and punction
	 * (excluding the apostrophe) and stored in an ArrayList of Strings. These
	 * ArrayLists representing the line are stored in an ArrayList of ArrayLists of
	 * Strings. Specifically, they are put in the ArrayList fileByLine that is
	 * passed in as a parameter.
	 *
	 * For example, a file containing the following: Lorem ipsum dolor sit amet,
	 * consectetur adipiscing elit. Don'ec elementum tortor in mauris consequat
	 * vulputate.
	 *
	 * Would produce an ArrayList of ArrayLists containing 2 ArrayLists of Strings.
	 * The first ArrayList would contain: "Lorem", "ipsum", "dolor", "sit", "amet",
	 * ",", "consectetur", "adipiscing", "elit", ".", "Don'ec", "elementum",
	 * "tortor", "in", "mauris" The second Arraylist would contain: "consequat",
	 * "vulputate", "."
	 *
	 * Note 1: The text file is assumed to be UTF-8. Note 2: There are no assumption
	 * about the length of the file or the length of the lines. Note 3: All single
	 * quotes (') are assumed to be apostrophes.
	 *
	 * When opening the file, any FileNotFoundException is caught and the error
	 * message "Exception: File 'fileName' not found." followed by a new line is
	 * output, where fileName is the name of the file that the method attempted to
	 * open.
	 *
	 * @param fileName
	 *            The name of the input text file to parse.
	 * @param fileByLine
	 *            Reference to ArrayList to contain the contents of the file line by
	 *            line, where each line is an ArrayList of Strings.
	 * @throws IOException
	 *             if an I/O error occurs when closing the file.
	 *             FileNotFoundException is caught when opening the file.
	 */
	public static void readInputFile(String fileName, ArrayList<ArrayList<String>> fileByLine) throws IOException {
		if (fileName == null) {
			return;
		}
		FileInputStream text = new FileInputStream(fileName);
		Scanner input = null;
		input = new Scanner(text);
		ArrayList<String> Line = new ArrayList<String>();
		Scanner reader = null;

		while (input.hasNextLine()) {
			Line = new ArrayList<String>();
			reader = new Scanner(input.nextLine());

			while (reader.hasNext()) {
				String S = new String();
			S = reader.next();
			String tempW = new String();
				tempW = S;
				for (int i = 0; i < tempW.length(); ++i) {
					if (Character.isLetter(tempW.charAt(i)) == false) {
						tempW = tempW.replace(tempW.charAt(i), ' ');
						tempW = tempW.replaceAll(" ", "");
					}

					else {
						continue;
					}

				}

				Line.add(tempW);

				for (int i = 0; i < S.length(); ++i) {
					if (Character.isLetter(S.charAt(i)) == false) {
						String c = "" + S.charAt(i);
						Line.add(c);

					} else {
				continue;
					}

				}

				
			}

			fileByLine.add(Line);

		}
		input.close();
	}

	/**
	 * Opens and writes (overwrites if the file already exits) the modified contents
	 * of the input file specified contained in modFileByLine to a filecalled
	 * filename. medFileByLine is an ArrayList containing one ArrayList of String
	 * for each output line.
	 *
	 * When producing the output file, each line should be terminated by a new line.
	 * Each non-punctuation should be Moreover, the spacing around the punctuation
	 * should be as follows: - Excluding, double quotes ("), no space between the
	 * preceding string and the punctuation and a space following. - Double quotes
	 * (") will be treated as pairs: - the first double quote will be preceded by a
	 * space and will not have a space following. - the next double quote will not
	 * be preceded by space and will have a space following.
	 *
	 * If modFileByLine is an ArrayList of ArrayLists containt 2 ArrayLists of
	 * Strings, such that: - The first ArrayList contains: "Lorem", "ipsum", "\"",
	 * "dolor", "sit", "\"", "amet", ",", "consectetur", "adipiscing", "elit", ".",
	 * "Don'ec", "elementum", "tortor", "in", "mauris" - The second Arraylist
	 * contains: "consequat", "vulputate", "."
	 *
	 * The output to the file would be: Lorem ipsum "dolor sit" amet consectetur
	 * adipiscing elit. Don'ec elementum tortor in mauris consequat vulputate.
	 *
	 * Note 1: The output to the file is UTF-8.
	 *
	 * When opening the file, any FileNotFoundException is caught and the error
	 * message "Exception: File 'fileName' not found." followed by a new line is
	 * output, where fileName is the name of the file that the method attempted to
	 * open.
	 *
	 * @param fileName
	 *            The name of the input text file to parse.
	 * @param modFileByLine
	 *            Reference to ArrayList to contain the modified contents of the
	 *            file line by line, where each line is an ArrayList of Strings.
	 * @throws IOException
	 *             if an I/O error occurs when closing the file.
	 *             FileNotFoundException is caught when opening the file.
	 */
	public static void saveToFile(String fileName, ArrayList<ArrayList<String>> modFileByLine) throws IOException {
		FileOutputStream fileByteStream = null;
		PrintWriter outFS = null;
		fileByteStream = new FileOutputStream(fileName);
		outFS = new PrintWriter(fileByteStream);
		for (int i = 0; i < modFileByLine.size(); i++) {
			for (int j = 0; j < modFileByLine.get(i).size(); j++) {
				outFS.print(modFileByLine.get(i).get(j) + " ");
			}
			outFS.println();
		}
		outFS.flush();

		fileByteStream.close();

	}

	/**
	 * Prints out the modified file (and possibly interleaved with the original
	 * file) to the screen.
	 *
	 * If modeBoth is false, then the contents of modFileByLine is output line by
	 * line. Each word is output followed by a space except for the last word. Each
	 * line is terminated with a new line character.
	 *
	 * For the non-interleaved mode, consider the following example: modFileByLine
	 * contains 2 ArrayList of Strings: 1: "Où", "est", "la", "bibliothèque", "?"
	 * 2: "Aucune", "idée", "."
	 *
	 * The output would be: Où est la bibliothèque ? Aucune idée .
	 *
	 * Otherwise, modeBoth is true, then the contents of modFileByLine is
	 * interleaved with fileByline. Lines are printed out in order. First, a line
	 * from modFileByLine is output followed by a new line character followed by the
	 * corresponding line in fileByLine. In order to better match up the
	 * corresponding words in the corresponding lines, the short word is appended
	 * with spaces to the length of the longer word. Between each word adjusted for
	 * length is an additional space.
	 *
	 * For the interleaved mode, consider the following example:
	 * 
	 * modFileByLine contains 2 ArrayList of Strings: 1: "Où", "est", "la",
	 * "bibliothèque", "?" 2: "Aucune", "idée", "." fileByLine contains 1
	 * ArrayList of Strings: 1: "Where", "is", "the", "library", "?" 2: "No",
	 * "idea", "."
	 *
	 * The output would be: Où est la bibliothèque ? Where is the library ? Aucune
	 * idée . No idea .
	 * 
	 * @param fileByLine
	 *            An ArrayList of ArrayList of Strings containing the original
	 *            content.
	 * @param modFileByLine
	 *            An ArrayList of ArrayList of Strings containing the modified
	 *            content.
	 * @param modeBoth
	 *            Flag to indicate if the original file should be interleaved.
	 * @throws Exception
	 *             Throws an Exception with the message "Number of lines in modified
	 *             version differs from original." if the size of fileByLine
	 *             differes from modFileByLine.
	 * @throws Exception
	 *             Throws an Exception with the message "Number of words on line i
	 *             in modified version differs from original.", where i should be
	 *             the 0-based line index where the size of the ArrayList at index i
	 *             in fileByLine differes from the ArrayList at index i in
	 *             modFileByLine.
	 */
	public static void display(ArrayList<ArrayList<String>> fileByLine, ArrayList<ArrayList<String>> modFileByLine,
			boolean modeBoth) throws Exception {
		String addSpace = "";
		String modAddSpace = "";
		if (modeBoth == false) {
			for (int i = 0; i < modFileByLine.size(); i++) {
				for (int k = 0; k < modFileByLine.get(i).size(); k++) {
					if (!(k == modFileByLine.get(i).size() - 1)) {
						System.out.print(modFileByLine.get(i).get(k) + " ");
					} else {
						System.out.print(modFileByLine.get(i).get(k));
					}
				}
				System.out.println();
			}
		} else if (modeBoth == true) {// if you want the words and their translations
			for (int i = 0; i < fileByLine.size(); i++) {
				for (int k = 0; k < fileByLine.get(i).size(); k++) {
					while (k < modFileByLine.get(i).size()) {
						if (!(k == fileByLine.get(i).size() - 1)) {
							while (modFileByLine.get(i).get(k).length() < fileByLine.get(i).get(k).length()) {

								modAddSpace = modFileByLine.get(i).get(k) + " ";
								modFileByLine.get(i).set(k, modAddSpace);
							}
							System.out.print(modFileByLine.get(i).get(k) + " ");
						} else {
							System.out.print(modFileByLine.get(i).get(k));
						}
						k++;
					}
					System.out.println();
					k = 0;
					while (k < fileByLine.get(i).size()) {
						if (!(k == fileByLine.get(i).size() - 1)) {
							while (fileByLine.get(i).get(k).length() < modFileByLine.get(i).get(k).length()) {

								addSpace = fileByLine.get(i).get(k) + " ";
								fileByLine.get(i).set(k, addSpace);
							}
							System.out.print(fileByLine.get(i).get(k) + " ");
						} else {
							System.out.print(fileByLine.get(i).get(k));
						}
						k++;
					}
					System.out.println();
				}
				System.out.println();
			}

		}
	}

	/**
	 * Performs the actions specified by the modFlags to the input file stored in
	 * the ArrayList of ArrayLists of Strings fileByLine. This method stores the
	 * modified string in a new ArrayList of ArrayLists of Strings which it returns.
	 *
	 * There are 4 modifications that may be performed. They are to be process in
	 * the following order if indicated in modFlags: 1 - Translation 2 - To Pig
	 * Latin 3 - Reverse the letters in each word 4 - Reverse the words in each line
	 *
	 * @param fileByLine
	 *            The original file stored as an ArrayList of ArrayLists of Strings.
	 * @param dict
	 *            An ArrayList of String arrays of length two. The ArrayList is
	 *            assumed to be sorted in ascending order accordings to the strings
	 *            at index 0. This will be the second argument for the translate
	 *            method.
	 * @param modFlags
	 *            A boolean area of length Config.NUM_MODS that indicates which
	 *            translation to perform by having a value of true in the
	 *            appropriate cell as follows: Index Modification
	 *            ------------------- --------------------------------
	 *            Config.MOD_TRANS Translation Config.MOD_PIG To Pig Latin
	 *            Config.MOD_REV_WORD Reverse the letters in each word
	 *            Config.MOD_REv_LINE Reverse the words in each line
	 * @return An ArrayList of ArrayLists of Strings, where each internal ArrayList
	 *         is a line which corresponds to the data in fileByLine but with the
	 *         transformations applied in the order specified above.
	 */
	public static ArrayList<ArrayList<String>> manipulate(ArrayList<ArrayList<String>> fileByLine,
			ArrayList<String[]> dict, boolean[] modFlags) {
		ArrayList<ArrayList<String>> modFileByLine = new ArrayList<ArrayList<String>>();
		modFileByLine = fileByLine;

		if (modFlags[Config.MOD_TRANS] == true) {
			ArrayList<ArrayList<String>> temp = new ArrayList<ArrayList<String>>();
			for (int i = 0; i < modFileByLine.size(); ++i) {
				ArrayList<String> wordHold = new ArrayList<String>();
				for (int j = 0; j < modFileByLine.get(i).size(); ++j) {

					String word = modFileByLine.get(i).get(j);
					char c = word.charAt(0);
					if (word.length() == 1 && Character.isAlphabetic(c) == false) {
						wordHold.add(word);
						continue;
					}

					else {
						word = translate(word, dict);
						wordHold.add(word);
					}

				}
				temp.add(wordHold);

			}
			modFileByLine = temp;
		}

		if (modFlags[Config.MOD_PIG] == true) {
			ArrayList<ArrayList<String>> temp = new ArrayList<ArrayList<String>>();
			for (int i = 0; i < modFileByLine.size(); ++i) {
				ArrayList<String> wordHold = new ArrayList<String>();
				for (int j = 0; j < modFileByLine.get(i).size(); ++j) {

					String word = modFileByLine.get(i).get(j);
					char c = word.charAt(0);
					if (word.length() == 1 && Character.isAlphabetic(c) == false) {
						wordHold.add(word);
						continue;
					}

					else {
						word = pigLatin(word);
						wordHold.add(word);
					}
				}
				temp.add(wordHold);

			}
			modFileByLine = temp;
		}

		else if (modFlags[Config.MOD_REV_WORD] == true) {
			ArrayList<ArrayList<String>> temp = new ArrayList<ArrayList<String>>();
			for (int i = 0; i < modFileByLine.size(); ++i) {
				ArrayList<String> wordHold = new ArrayList<String>();
				for (int j = 0; j < modFileByLine.get(i).size(); ++j) {

					String word = modFileByLine.get(i).get(j);
					char c = word.charAt(0);
					if (word.length() == 1 && Character.isAlphabetic(c) == false) {
						wordHold.add(word);
						continue;
					}

					else {
						word = reverse(word);
						wordHold.add(word);
					}

				}
				temp.add(wordHold);
			}
			modFileByLine = temp;
		}

		if (modFlags[Config.MOD_REV_LINE] == true) {
			ArrayList<ArrayList<String>> temp = new ArrayList<ArrayList<String>>();
			ArrayList<String> wordHold = new ArrayList<String>();
			for (int i = 0; i < modFileByLine.size(); ++i) {

				wordHold = reverse(modFileByLine.get(i));
				temp.add(wordHold);

			}

			modFileByLine = temp;
		}

		// Leave the method as is for Milestone 1.
		return modFileByLine;

		// For Milestone 2, you will need to build a new ArrayList<ArrayList<String>>
		// that will be
		// returned. Go through the each string in fileByLine and, if the boolean at
		// Config.MOD_TRANS in modFlags is true, then call the translate method, storing
		// the modified
		// strings (otherwise store the original string) line by line as they are
		// organized in fileByLine.

		// For Milestone 3, you will need to build a new ArrayList<ArrayList<String>>
		// that will be
		// returned. For each string in fileByLine, you will need to check the booleans
		// at
		// Config.MOD_TRANS, Config.MOD_PIG, and Config.MOD_REV_WORD in modFlags in that
		// order. For
		// each one that is true, apply the appropriate transformation, storing them as
		// in
		// Milestone 2. After having processed each string, if the boolean at
		// Config.MOD_REV_LINE
		// in modFlags is true, reverse each line in the returning
		// ArrayList<ArrayList<String>>.
	}

	public static void print80() {
		char line = Config.LINE_CHAR;// makes a char equal to Line Char for ease of not having to tyle Line Char
		String completedLine = "";
		for (int i = 0; i < 80; i++) {
			completedLine += line;// prints 80 of LineChar
		}
		System.out.println(completedLine);
	}

	/**
	 * This is the main method for the TextManipulator program. This method contains
	 * the loop that runs the prompt. It handles the user response and calls the
	 * methods that are necessary in order to perform the actions requested by the
	 * user.
	 *
	 * The initial default behavior of the program is to show the full menu, to be
	 * in the "Modified" mode, to have no modifications selected, and to have no
	 * values for the various file names.
	 *
	 * @param args
	 *            (unused)
	 */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		boolean loop = true;
		String[] text = new String[Config.NUM_FILES];
		boolean[] modFlags = new boolean[Config.NUM_MODS];
		boolean modBoth = false;
		boolean showMenu = true;
		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		ArrayList<ArrayList<String>> modFileByLine = new ArrayList<ArrayList<String>>();
		ArrayList<String[]> directoryList = new ArrayList<String[]>();
		while (loop) {
			char userChar = promptMenu(sc, text, modFlags, modBoth, showMenu);
			if (userChar == 'H') {
				showMenu ^= true;
			} else if (userChar == 'Q') {
				loop = false;
			} else if (userChar == '1') {
				modFileByLine = manipulate(list, directoryList, modFlags);

				print80();
				try {
					display(list, modFileByLine, modBoth);
				} catch (Exception e) {
					e.printStackTrace();
				}

				print80();
			} else if (userChar == '2') {
				try {
					saveToFile(text[Config.FILE_OUT], modFileByLine);
				} catch (IOException e) {
					System.out.println("Exception: File '" + text[Config.FILE_OUT] + "' not found.");
				}

			} else if (userChar == 'T') {
				modFlags[Config.MOD_TRANS] ^= true;

			} else if (userChar == 'P') {
				modFlags[Config.MOD_PIG] ^= true;

			} else if (userChar == 'W') {
				modFlags[Config.MOD_REV_WORD] ^= true;

			} else if (userChar == 'L') {
				modFlags[Config.MOD_REV_LINE] ^= true;

			} else if (userChar == 'I') {
				text[Config.FILE_IN] = updateFileName(sc, text[Config.FILE_IN]);
				try {
					readInputFile(text[Config.FILE_IN], list);
				} catch (IOException e) {
					System.out.println("Exception: File '" + text[Config.FILE_IN] + "' not found.");
				}
				modFileByLine = list;
			} else if (userChar == 'O') {
				text[Config.FILE_OUT] = updateFileName(sc, text[Config.FILE_OUT]);
			} else if (userChar == 'D') {
				text[Config.FILE_DICT] = updateFileName(sc, text[Config.FILE_DICT]);
				try {
					readDictFile(text[Config.FILE_DICT], directoryList);
				} catch (IOException e) {
					System.out.println("Exception: File '" + text[Config.FILE_DICT] + "' not found.");
				}
			} else if (userChar == 'M') {
				modBoth ^= true;
			} else {
				System.out.println("Unknown option.");
			}
		}
	}

}
