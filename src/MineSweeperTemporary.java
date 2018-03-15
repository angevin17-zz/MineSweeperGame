
//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:           Mine Sweeper
// Files:           MineSweeper and Config
// Course:          CS 200 Fall Semester 2017
//
// Author:          Vinnie Angellotti
// Email:           angellotti@wisc.edu
// Lecturer's Name: Marc Renualt
// Collaborator:    Nicholas Fonte
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

import java.util.Random;
import java.util.Scanner;

/**
 * @author Vinnie Angellotti
 *
 */
public class MineSweeperTemporary {

	/**
	 * This is the main method for Mine Sweeper game! This method contains the
	 * within game and play again loops and calls the various supporting methods.
	 * 
	 * @param args
	 *            (unused)
	 */
	public static void main(String[] args) {
		System.out.println("Welcome to Mine Sweeper!");
		String str = ""; //string that asks user if they want to play again later in code
		Scanner sc = new Scanner(System.in);//creates a variable sc that takes user input
		Random rand = new Random(Config.SEED);//creates a variable rand that can make random numbers
		do {
			String userWidthPrompt = "What width of map would you like (" + Config.MIN_SIZE + " - " + Config.MAX_SIZE
					+ "): ";//tells the user the guidelines for entering the width
			String userHeightPrompt = "What height of map would you like (" + Config.MIN_SIZE + " - " + Config.MAX_SIZE
					+ "): ";//tells the user the guidelines for entering the height
			int userWidth = promptUser(sc, userWidthPrompt, Config.MIN_SIZE, Config.MAX_SIZE);//calls promptUser and gets
					//the user's desired width
			int userHeight = promptUser(sc, userHeightPrompt, Config.MIN_SIZE, Config.MAX_SIZE);//calls promptUser and gets
					//the user's desired height
			boolean[][] mineAtLoc = new boolean[userHeight][userWidth];// array that has info about where bombs are
			int numMines = placeMines(mineAtLoc, rand);//calles placeMines to see how many total mines there are in array
			System.out.println();
			System.out.println("Mines: " + numMines);
			char[][] mines = new char[userHeight][userWidth];//char array that shows where the mines are, if any
			eraseMap(mines);
			printMap(mines);
			int rowSwept = 0;//this will be what row the user wants to sweep for mines
			int colSwept = 0;//this will be what column the user wants to sweep for mines

			do {// this loop will make this whole program keep going until the user hits
				// a mine or wins

				String rowSweep = "row: ";//tells the user to put a row they want swept
				rowSwept = promptUser(sc, rowSweep, 1, userHeight);//calls promptUser and gets a row the user wants swept
				String colSweep = "column: ";//tells the user to put a column they want swept
				colSwept = promptUser(sc, colSweep, 1, userWidth);//calls promptUser and gets a column the user wants swept

				if (mineAtLoc[rowSwept - 1][colSwept - 1] == true) {// there is a mine at the location
					//System.out.println("Sorry you lost");
					showMines(mines, mineAtLoc);
					mines[rowSwept - 1][colSwept - 1] = Config.SWEPT_MINE;
					printMap(mines);
					System.out.println("Sorry, you lost.");
					break;
				} else if (mineAtLoc[rowSwept - 1][colSwept - 1] == false) {// there is not a mine at the location
					
					int minesTouching = sweepLocation(mines, mineAtLoc, rowSwept-1, colSwept-1);
					if(minesTouching== 0) {
						sweepAllNeighbors(mines,mineAtLoc,rowSwept-1, colSwept-1);
					}
					if (allSafeLocationsSwept(mines, mineAtLoc) == true) {
						showMines(mines, mineAtLoc);
						printMap(mines);
						System.out.println("You Win!");
						break;
					}
					System.out.println();
					System.out.println("Mines: " + numMines);
					printMap(mines);

					// char at the location that you guessed is switched to the number of mines that
					// are nearby it and the game continues
				}

			} while (allSafeLocationsSwept(mines, mineAtLoc) == false
					|| mineAtLoc[rowSwept - 1][colSwept - 1] == false);
			System.out.print("Would you like to play again (y/n)? ");
			str = sc.next();// gets input to see if they want to play again
			str = str.toLowerCase();//converts input to lower case so if they enter 'Y' it would be valid 
		} while (str.charAt(0) == 'y');
		System.out.println("Thank you for playing Mine Sweeper!");
	}

	/**
	 * This method prompts the user for a number, verifies that it is between min
	 * and max, inclusive, before returning the number.
	 * 
	 * If the number entered is not between min and max then the user is shown an
	 * error message and given another opportunity to enter a number. If min is 1
	 * and max is 5 the error message is: Expected a number from 1 to 5.
	 * 
	 * If the user enters characters, words or anything other than a valid int then
	 * the user is shown the same message. The entering of characters other than a
	 * valid int is detected using Scanner's methods (hasNextInt) and does not use
	 * exception handling.
	 * 
	 * Do not use constants in this method, only use the min and max passed in
	 * parameters for all comparisons and messages. Do not create an instance of
	 * Scanner in this method, pass the reference to the Scanner in main, to this
	 * method. The entire prompt should be passed in and printed out.
	 *
	 * @param in
	 *            The reference to the instance of Scanner created in main.
	 * @param prompt
	 *            The text prompt that is shown once to the user.
	 * @param min
	 *            The minimum value that the user must enter.
	 * @param max
	 *            The maximum value that the user must enter.
	 * @return The integer that the user entered that is between min and max,
	 *         inclusive.
	 */
	public static int promptUser(Scanner in, String prompt, int min, int max) {
		System.out.print(prompt);
		int userNum = 0;//gets the number the user enters
		while (true) {
			if (!in.hasNextInt()) {
				in.nextLine();
				System.out.println("Expected a number from " + min + " to " + max + ".");
			} else if (in.hasNextInt()) {
				userNum = in.nextInt();// sets number user entered to userNum
				if (userNum < min || userNum > max) {
					System.out.println("Expected a number from " + min + " to " + max + ".");
					in.nextLine();
				} else {

					break;
				}
			}
		}

		in.nextLine();
		return userNum;
	}

	/**
	 * This initializes the map char array passed in such that all elements have the
	 * Config.UNSWEPT character. Within this method only use the actual size of the
	 * array. Don't assume the size of the array. This method does not print out
	 * anything. This method does not return anything.
	 * 
	 * @param map
	 *            An allocated array. After this method call all elements in the
	 *            array have the same character, Config.UNSWEPT.
	 */
	public static void eraseMap(char[][] map) {
		int row = map.length; 
		int col = map[0].length;
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				map[i][j] = Config.UNSWEPT;

			}

		}

		return;
	}

	/**
	 * This prints out a version of the map without the row and column numbers. A
	 * map with width 4 and height 6 would look like the following: . . . . . . . .
	 * . . . . . . . . . . . . . . . . For each location in the map a space is
	 * printed followed by the character in the map location.
	 * 
	 * @param map
	 *            The map to print out.
	 */
	public static void simplePrintMap(char[][] map) {
		int row = map.length;
		int col = map[0].length;
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				System.out.print(" " + map[i][j]);
			}
			System.out.println();
		}
		return;
	}

	/**
	 * This prints out the map. This shows numbers of the columns and rows on the
	 * top and left side, respectively. map[0][0] is row 1, column 1 when shown to
	 * the user. The first column, last column and every multiple of 5 are shown.
	 * 
	 * To print out a 2 digit number with a leading space if the number is less than
	 * 10, you may use: System.out.printf("%2d", 1);
	 * 
	 * @param map
	 *            The map to print out.
	 */
	public static void printMap(char[][] map) {
		int maxRow = map.length;
		int maxCol = map[0].length;
		int k = 5;
		int l = 5;
		for(int i = 0; i<= maxRow; i++) {
			for(int j = 0; j<= maxCol; j++) {
				if(i==0 && j == 0) {
					System.out.print("  ");
				}
				else if(i==0 && j==1 ) {
					System.out.printf("%2d", 1);
				}
				else if(j==0 && i==1) {
					System.out.printf("%2d", 1);
				}
				else if(!(i%l == 0) && j == 0 && i!=1 && i!=maxRow) {
					System.out.print(" |");
				}
				else if(!(j%k == 0) && i==0 && j!=1 && j!=maxCol ) {
					System.out.print("--");
				}
				else if(j%k==0 && i==0 && j!=0) {
					System.out.printf("%2d",k);
					k+=5;
				}
				else if(i%l==0 && j==0 && i!=0) {
					System.out.printf("%2d",l);
					l+=5;
				}
				else if(i==maxRow && j==0 && !(i%5==0)) {
					System.out.printf("%2d",maxRow);
				}
				else if(j==maxCol && i==0 && !(j%5==0)) {
					System.out.printf("%2d",maxCol);
				}
				else{
					System.out.print(" " + map[i-1][j-1]);
				}
			}
			System.out.println();
		}
		return;
	}

	/**
	 * This method initializes the boolean mines array passed in. A true value for
	 * an element in the mines array means that location has a mine, false means the
	 * location does not have a mine. The MINE_PROBABILITY is used to determine
	 * whether a particular location has a mine. The randGen parameter contains the
	 * reference to the instance of Random created in the main method.
	 * 
	 * Access the elements in the mines array with row then column (e.g.,
	 * mines[row][col]).
	 * 
	 * Access the elements in the array solely using the actual size of the mines
	 * array passed in, do not use constants.
	 * 
	 * A MINE_PROBABILITY of 0.3 indicates that a particular location has a 30%
	 * chance of having a mine. For each location the result of randGen.nextFloat()
	 * < Config.MINE_PROBABILITY determines whether that location has a mine.
	 * 
	 * This method does not print out anything.
	 * 
	 * @param mines
	 *            The array of boolean that tracks the locations of the mines.
	 * @param randGen
	 *            The reference to the instance of the Random number generator
	 *            created in the main method.
	 * @return The number of mines in the mines array.
	 */
	public static int placeMines(boolean[][] mines, Random randGen) {
		int numMines = 0; //initializes variable that will count how many total mines
				//are placed
		for (int i = 0; i < mines.length; i++) {
			for (int j = 0; j < mines[i].length; j++) {
				if (randGen.nextFloat() < Config.MINE_PROBABILITY) {
					mines[i][j] = true;
					if (mines[i][j] == true) {
						numMines += 1;
					}
				}

			}

		}
		return numMines;
	}

	/**
	 * This method returns the number of mines in the 8 neighboring locations. For
	 * locations along an edge of the array, neighboring locations outside of the
	 * mines array do not contain mines. This method does not print out anything.
	 * 
	 * If the row or col arguments are outside the mines array, then return -1. This
	 * method (or any part of this program) should not use exception handling.
	 * 
	 * @param mines
	 *            The array showing where the mines are located.
	 * @param row
	 *            The row, 0-based, of a location.
	 * @param col
	 *            The col, 0-based, of a location.
	 * @return The number of mines in the 8 surrounding locations or -1 if row or
	 *         col are invalid.
	 */
	public static int numNearbyMines(boolean[][] mines, int row, int col) {
		int numOfMinesTouch = 0;
		if (row > mines.length || row < 0 || col > mines[0].length || col < 0) {
			return -1;
		}
		for (int i = row - 1; i <= row + 1; i++) {
			for (int j = col - 1; j <= col + 1; j++) {
				if (i == row  && j == col) {
					continue;
				}
				if (i < 0 || j < 0 || i >= mines.length || j >= mines[i].length)
					continue;
				if (mines[i][j]) {
					numOfMinesTouch++;
				}
			}
		}
		return numOfMinesTouch;
	}

	/**
	 * This updates the map with each unswept mine shown with the Config.HIDDEN_MINE
	 * character. Swept mines will already be mapped and so should not be changed.
	 * This method does not print out anything.
	 * 
	 * @param map
	 *            An array containing the map. On return the map shows unswept
	 *            mines.
	 * @param mines
	 * 
	 *            An array indicating which locations have mines. No changes are
	 *            made to the mines array.
	 */
	public static void showMines(char[][] map, boolean[][] mines) {
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				if (mines[i][j] == true) {
					map[i][j] = Config.HIDDEN_MINE;
				}
			}
		}
		return;
	}

	/**
	 * Returns whether all the safe (non-mine) locations have been swept. In other
	 * words, whether all unswept locations have mines. This method does not print
	 * out anything.
	 * 
	 * @param map
	 *            The map showing touched locations that is unchanged by this
	 *            method.
	 * @param mines
	 *            The mines array that is unchanged by this method.
	 * @return whether all non-mine locations are swept.
	 */
	public static boolean allSafeLocationsSwept(char[][] map, boolean[][] mines) {
		boolean allMinesGone = false;
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				if (mines[i][j] == false) {
					if (map[i][j] == Config.UNSWEPT) {
						return false;
					} else if (map[i][j] != Config.UNSWEPT) {
						allMinesGone = true;
					}
				}
			}
		}
		return allMinesGone;
	}

	/**
	 * This method sweeps the specified row and col. - If the row and col specify a
	 * location outside the map array then return -3 without changing the map. - If
	 * the location has already been swept then return -2 without changing the map.
	 * - If there is a mine in the location then the map for the corresponding
	 * location is updated with Config.SWEPT_MINE and return -1. - If there is not a
	 * mine then the number of nearby mines is determined by calling the
	 * numNearbyMines method. - If there are 1 to 8 nearby mines then the map is
	 * updated with the characters '1'..'8' indicating the number of nearby mines. -
	 * If the location has 0 nearby mines then the map is updated with the
	 * Config.NO_NEARBY_MINE character. - Return the number of nearbyMines.
	 * 
	 * @param map
	 *            The map showing swept locations.
	 * @param mines
	 *            The array showing where the mines are located.
	 * @param row
	 *            The row, 0-based, of a location.
	 * @param col
	 *            The col, 0-based, of a location.
	 * @return the number of nearby mines, -1 if the location is a mine, -2 if the
	 *         location has already been swept, -3 if the location is off the map.
	 */
	public static int sweepLocation(char[][] map, boolean[][] mines, int row, int col) {
		int numMinesNearby = 0;
		if (row >= map.length || row < 0 || col >= map[0].length || col < 0) {// location is off the map
			return -3;
		}
		if (map[row][col] != Config.UNSWEPT) {// location has already been swept
			return -2;
		}
		if (mines[row][col]) {// location is a mine
			map[row ][col] = Config.SWEPT_MINE;
			return -1;
		}
		if (mines[row][col] == false) {// no mine
			numMinesNearby = numNearbyMines(mines, row, col);
			if (numMinesNearby > 0) {
				String tempString = Integer.toString(numMinesNearby);
				char tempChar = tempString.charAt(0);
				map[row][col] = tempChar;
			} else if (numMinesNearby == 0) {// no mine nearby
				map[row][col] = Config.NO_NEARBY_MINE;
			}
		}
		return numMinesNearby;
	}

	/**
	 * This method iterates through all 8 neighboring locations and calls
	 * sweepLocation for each. It does not call sweepLocation for its own location,
	 * just the neighboring locations.
	 * 
	 * @param map
	 *            The map showing touched locations.
	 * @param mines
	 *            The array showing where the mines are located.
	 * @param row
	 *            The row, 0-based, of a location.
	 * @param col
	 *            The col, 0-based, of a location.
	 */
	public static void sweepAllNeighbors(char[][] map, boolean[][] mines, int row, int col) {
		int i = 0;
		int j = 0;
		for (i = row - 1; i <= row + 1; i++) {
			for (j = col - 1; j <= col + 1; j++) {
				if (i == row  && j == col) {
					continue;
				}
				if (i < 0 || j < 0 || i >= mines.length || j >= mines[i].length) {
					continue;
				}
				sweepLocation(map, mines, i, j);
			}
		}
		return; // FIXME
	}
}
