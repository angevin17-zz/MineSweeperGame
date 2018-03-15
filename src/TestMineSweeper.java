
/**
 * This file contains testing methods for the MineSweeper project.
 * These methods are intended to serve several objectives:
 * 1) provide an example of a way to incrementally test your code
 * 2) provide example method calls for the MineSweeper methods
 * 3) provide examples of creating, accessing and modifying arrays
 *    
 * Toward these objectives, the expectation is that part of the 
 * grade for the MineSweeper project is to write some tests and
 * write header comments summarizing the tests that have been written. 
 * Specific places are noted with FIXME but add any other comments 
 * you feel would be useful.
 * 
 * Some of the provided comments within this file explain
 * Java code as they are intended to help you learn Java.  However,
 * your comments and comments in professional code, should
 * summarize the purpose of the code, not explain the meaning
 * of the specific Java constructs.
 *    
 */

import java.util.Random;

/**
 * This class contains a few methods for testing methods in the MineSweeper
 * class as they are developed. These methods are all private as they are only
 * intended for use within this class.
 * 
 * @author Jim Williams
 * @author Vincent Angellotti
 * @author Nick Fonte
 *
 */
public class TestMineSweeper {

	/**
	 * This is the main method that runs the various tests. Uncomment the tests when
	 * you are ready for them to run.
	 * 
	 * @param args
	 *            (unused)
	 */
	public static void main(String[] args) {

		// Milestone 1
		// testing the main loop, promptUser and simplePrintMap, since they have
		// a variety of output, is probably easiest using a tool such as diffchecker.com
		// and comparing to the examples provided.
		testEraseMap(); // done

		// Milestone 2
		testPlaceMines();
		testNumNearbyMines();
		testShowMines();
		testAllSafeLocationsSwept();

		// Milestone 3

		testSweepLocation();
		testSweepAllNeighbors();
		// testing printMap, due to printed output is probably easiest using a
		// tool such as diffchecker.com and comparing to the examples provided.
	}

	/**
	 * This is intended to run some tests on the eraseMap method. 1. A char array is
	 * made with all values set to a value we don't want it to be
	 * (Config.SWEPT_MINE) 2. Another char array of the same size is made and filled
	 * with what we do want(Config.UNSWEPT) 3. We call the eraseMap method and then
	 * see if the array's have matching values
	 */
	private static void testEraseMap() {
		char[][] map = new char[3][3];// creates sample array
		char[][] fixedMap = new char[3][3];// creates array to be tested
		MineSweeper.eraseMap(map);
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				map[i][j] = Config.SWEPT_MINE;// fills array with values that we don't want it to be
			}
		}
		for (int i = 0; i < fixedMap.length; i++) {
			for (int j = 0; j < fixedMap[0].length; j++) {
				fixedMap[i][j] = Config.UNSWEPT;// fills array with values that we want to be in it
			}
		}

		MineSweeper.eraseMap(map);// tests eraseMap method
		if (map[0][0] == fixedMap[0][0]) {
			System.out.println("eraseMap: passed");
		} else {
			System.out.println("eraseMap: failed");
		}
		if (map[2][2] == fixedMap[2][2]) {
			System.out.println("eraseMap: passed");
		} else {
			System.out.println("eraseMap: failed");
		}
	}

	/*
	 * Calculate the number of elements in array1 with different values from those
	 * in array2
	 */
	private static int getDiffCells(boolean[][] array1, boolean[][] array2) {
		int counter = 0;
		for (int i = 0; i < array1.length; i++) {
			for (int j = 0; j < array1[i].length; j++) {
				if (array1[i][j] != array2[i][j])
					counter++;
			}
		}
		return counter;
	}

	/**
	 * This runs some tests on the placeMines method. 1. Makes a boolean array with
	 * values that we should have in it(random's seeded correctly) 2. Creates a new
	 * boolean array of the same size and calls placeMines 3. If the arrays are the
	 * same then the test passes
	 */
	private static void testPlaceMines() {
		boolean error = false;

		// These expected values were generated with a Random instance set with
		// seed of 123 and MINE_PROBABILITY is 0.2.
		boolean[][] expectedMap = new boolean[][] { { false, false, false, false, false },
				{ false, false, false, false, false }, { false, false, false, true, true },
				{ false, false, false, false, false }, { false, false, true, false, false } };
		int expectedNumMines = 3;

		Random studentRandGen = new Random(123);
		boolean[][] studentMap = new boolean[5][5];
		int studentNumMines = MineSweeper.placeMines(studentMap, studentRandGen);

		if (studentNumMines != expectedNumMines) {
			error = true;
			System.out.println(
					"testPlaceMines 1: expectedNumMines=" + expectedNumMines + " studentNumMines=" + studentNumMines);
		}
		int diffCells = getDiffCells(expectedMap, studentMap);
		if (diffCells != 0) {
			error = true;
			System.out.println("testPlaceMines 2: mine map differs.");
		}

		if (error) {
			System.out.println("testPlaceMines: failed");
		} else {
			System.out.println("testPlaceMines: passed");
		}

	}

	/**
	 * This runs some tests on the numNearbyMines method. 1. Creates an example
	 * boolean array 2. Chooses a value and see how many true values(mines) are next
	 * to it by calling the numNearbyMines method 3. If the correct number of nearby
	 * mines are output then the test passes
	 * 
	 */
	private static void testNumNearbyMines() {
		boolean error = false;

		boolean[][] mines = new boolean[][] { { false, false, true, false, false },
				{ false, false, false, false, false }, { false, true, false, true, true },
				{ false, false, false, false, false }, { false, false, true, false, false } };
		int numNearbyMines = MineSweeper.numNearbyMines(mines, 1, 1);

		if (numNearbyMines != 2) {
			error = true;
			System.out.println("testNumNearbyMines 1: numNearbyMines=" + numNearbyMines + " expected mines=2");
		}

		numNearbyMines = MineSweeper.numNearbyMines(mines, 2, 1);

		if (numNearbyMines != 0) {
			error = true;
			System.out.println("testNumNearbyMines 2: numNearbyMines=" + numNearbyMines + " expected mines=0");
		}

		numNearbyMines = MineSweeper.numNearbyMines(mines, 3, 3);

		if (numNearbyMines != 3) {
			error = true;
			System.out.println("testNumNearbyMines 3: numNearbyMines=" + numNearbyMines + " expected mines=2");
		}

		if (error) {
			System.out.println("testNumNearbyMines: failed");
		} else {
			System.out.println("testNumNearbyMines: passed");
		}
	}

	/**
	 * This runs some tests on the showMines method. 1. We first make a sample
	 * boolean array with random values. 2. Next, we make a map that is the same
	 * size as the boolean array and give some row and columns values of unswept
	 * mines 3. Then we call the method and if there isn't a bomb where we expected
	 * there to be a bomb or if there is a bomb where we expected there not to be a
	 * bomb, the test fails.
	 * 
	 */
	private static void testShowMines() {
		boolean error = false;

		boolean[][] mines = new boolean[][] { { false, false, true, false, false },
				{ false, false, false, false, false }, { false, true, false, false, false },
				{ false, false, false, false, false }, { false, false, true, false, false } };

		char[][] map = new char[mines.length][mines[0].length];
		map[0][2] = Config.UNSWEPT;
		map[2][1] = Config.UNSWEPT;
		map[4][2] = Config.UNSWEPT;

		MineSweeper.showMines(map, mines);
		if (!(map[0][2] == Config.HIDDEN_MINE && map[2][1] == Config.HIDDEN_MINE && map[4][2] == Config.HIDDEN_MINE)) {
			error = true;
			System.out.println("testShowMines 1: a mine not mapped");
		}
		if (map[0][0] == Config.HIDDEN_MINE || map[0][4] == Config.HIDDEN_MINE || map[4][4] == Config.HIDDEN_MINE) {
			error = true;
			System.out.println("testShowMines 2: unexpected showing of mine.");
		}

		if (error) {
			System.out.println("testShowMines: failed");
		} else {
			System.out.println("testShowMines: passed");
		}
	}

	/**
	 * This is intended to run some tests on the allSafeLocationsSwept method. 1. We
	 * first created two arrays(one char and one boolean) with values that were not
	 * all swept 2. We called the allSafeLocationsSwept to make sure that it
	 * returned false 3. Then we made two more arrays with values that were all
	 * swept 4. Then we called allSafeLocationsSwept and if it didn't return true
	 * there was an error
	 */
	private static void testAllSafeLocationsSwept() {
		boolean error = false;
		boolean[][] mines = new boolean[][] { { false, false, true }, { false, false, false }, { false, true, false } };

		char[][] map = new char[][] { { Config.UNSWEPT, '1', '2' }, { '1', '2', '1' }, { '2', '3', '1' } };
		if (MineSweeper.allSafeLocationsSwept(map, mines) == true) {
			error = true;
		}
		boolean[][] newMines = new boolean[][] { { false, false, true }, { false, false, false },
				{ false, true, false } };

		char[][] newMap = new char[][] { { '1', '1', '2' }, { '1', '2', '1' }, { '2', '3', '1' } };
		if (MineSweeper.allSafeLocationsSwept(newMap, newMines) == false) {
			error = true;
		}
		if (error == true) {
			System.out.println("testAllSafeLocationsSwept: failed");
		} else {
			System.out.println("testAllSafeLocationsSwept: passed");
		}
	}

	/**
	 * 1. We first made sample char and boolean arrays 2. Then we tested a row and
	 * col that were out of range of the arrays 3. If there was not a -3 returned
	 * then the method was wrong 4. Then we tested a row and col that were already
	 * swept 5. If there was not a -2 returned then it was wrong 6. Then we tested
	 * with a row and col that had a mine under it 7. If there was not a -1 returned
	 * it was wrong
	 * 
	 */
	private static void testSweepLocation() {
		boolean error = false;
		char[][] map = new char[][] { { Config.SWEPT_MINE, '1', Config.UNSWEPT }, { '1', '2', '1' },
				{ '2', '3', '1' } };
		boolean[][] mines = new boolean[][] { { false, false, true }, { false, false, false }, { false, true, false } };
		int row = 4;
		int col = 4;
		int testRow2 = 0;
		int testCol2 = 0;
		int testRow3 = 0;
		int testCol3 = 2;
		if (MineSweeper.sweepLocation(map, mines, row, col) != -3) {
			error = true;
		}
		if (MineSweeper.sweepLocation(map, mines, testRow2, testCol2) != -2) {
			error = true;
		}
		if (MineSweeper.sweepLocation(map, mines, testRow3, testCol3) != -1) {

			error = true;
		}
		if (error) {
			System.out.println("testSweepLocation: failed");
		} else {
			System.out.println("testSweepLocation: passed");
		}
	}

	/**
	 * This is intended to run some tests on the sweepAllNeighbors method. 1.We
	 * first make char and boolean arrays with the same length 2. The boolean array
	 * has random values to resemble mines and the map array has all unswept mines.
	 * 3. We choose a location where there are no mines nearby 4. If the values
	 * match up to what is intended then it passes
	 */
	private static void testSweepAllNeighbors() {
		boolean error = false;
		char[][] map = new char[][] { { Config.UNSWEPT, Config.UNSWEPT, Config.UNSWEPT },
				{ Config.UNSWEPT, Config.UNSWEPT, Config.UNSWEPT },
				{ Config.UNSWEPT, Config.UNSWEPT, Config.UNSWEPT } };
		boolean[][] mines = new boolean[][] { { false, false, true }, { false, false, false }, { false, true, false } };
		int row = 0;
		int col = 0;
		MineSweeper.sweepAllNeighbors(map, mines, row, col);
		if (map[0][0] != '.') {
			error = true;
		}
		if (map[0][1] != '1') {
			error = true;
		}
		if (error == false) {
			System.out.println("testSweepAllNeighbors: passed");
		} else {
			System.out.println("testSweepAllNeighbors: failed");
		}
	}
}
