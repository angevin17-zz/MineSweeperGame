
/**
 * This class contains the constants used in the MineSweeper program. These constants may be changed
 * when testing and so your program should use the constants but not the values.
 * 
 * @author jimw
 *
 */
public class Config {

    /**
     * The minimum and maximum size, inclusive, for the width and height of the map.
     */
    public static final int MIN_SIZE = 3;
    public static final int MAX_SIZE = 20;

    /**
     * Constants with characters showing the status of different locations on the map
     */
    public static final char HIDDEN_MINE = '*'; // at end of game, show a hidden mine
    public static final char SWEPT_MINE = '@'; // at end of game, show swept/exploded mine
    public static final char UNSWEPT = '.'; // before a location is swept, show as this
    // In milestone 3, when a location has 0 neighboring mines then show this character
    public static final char NO_NEARBY_MINE = ' ';

    /**
     * The probability that a mine will be in any particular location (0.2 means 20% chance)
     */
    public static final double MINE_PROBABILITY = 0.2;

    /**
     * The seed to use in your random number generator so that the mines will show up in the same
     * locations. Useful for debugging and use by our testing system.
     */
    public static final int SEED = 456;
}
