package byog.Core;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import java.io.*;

public class Game {
    TERenderer ter = new TERenderer();
    /* Feel free to change the width and height. */
    public static final int WIDTH = 80;
    public static final int HEIGHT = 30;

    /**
     * Method used for playing a fresh game. The game should start from the main menu.
     * Not implemented until the second phrase
     */
    public void playWithKeyboard() {
    }

    /**
     * Method used for autograding and testing the game code. The input string will be a series
     * of characters (for example, "n123sswwdasdassadwas", "n123sss:q", "lwww". The game should
     * behave exactly as if the user typed these characters into the game after playing
     * playWithKeyboard. If the string ends in ":q", the same world should be returned as if the
     * string did not end with q. For example "n123sss" and "n123sss:q" should return the same
     * world. However, the behavior is slightly different. After playing with "n123sss:q", the game
     * should save, and thus if we then called playWithInputString with the string "l", we'd expect
     * to get the exact same world back again, since this corresponds to loading the saved game.
     * @param input the input string to feed to your program
     * @return the 2D TETile[][] representing the state of the world
     */
    public TETile[][] playWithInputString(String input) {
        // TODO: Fill out this method to run the game using the input passed in,
        // and return a 2D tile representation of the world that would have been
        // drawn if the same inputs had been given to playWithKeyboard().

        // if input is "l", load the game
        // if input is "N....S", create a new TETile array with the see
        // when use this method, the input can't be s
        TETile[][] finalWorldFrame = new TETile[WIDTH][HEIGHT];
        input = input.toLowerCase();
        int length = input.length();
        if (length > 0 ){
            String start = input.substring(0, 1);
            if (start.equals("l")) {
                return loadGame();
            } else if (start.equals("n") && length >= 3) {
                parseAndExecute(input, finalWorldFrame);
                return finalWorldFrame;
            }
        }
        return finalWorldFrame;
    }

    public void parseAndExecute(String input, TETile[][] finalWorldFrame) {
        int seed = getSeedFromInput(input);
        int length = input.length();
        String commands = getCommandsFromInput(input);
        generateRandomWorld(seed, finalWorldFrame);
        executeCommands(commands);
        String end = input.substring(length - 2, length);
        // generate the world
        if (end.equals(":q")) {
            saveGame(finalWorldFrame);
        }
    }

    public int getSeedFromInput(String input) {
        int end = 1;
        while ( Character.isDigit(input.charAt(end))) {
            end += 1;
        }
        return Integer.parseInt(input.substring(1, end));
    }

    public String getCommandsFromInput(String input) {
        //start is the staring index of the first command
        int start = 1;
        while ( Character.isDigit(input.charAt(start))) {
            start += 1;
        }
        String end = input.substring(input.length() - 2, input.length());
        if (end.equals(":q")) {
            return input.substring(start, input.length() - 2);
        }
        return input.substring(start, input.length());
    }

    public TETile[][] loadGame() {
        File file = new File("save.txt");
        if (! file.exists()) {
            return null;
        } else {
            try {
                FileInputStream f = new FileInputStream(file);
                ObjectInputStream o = new ObjectInputStream(f);
                return  (TETile[][]) o.readObject();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
                return null;
            }
        }
    }

    public void generateRandomWorld(int seed, TETile[][] finalWorldFrame) {
        //takes a seed and a TETile[][], fills the TETile[][] with Tiles
        for (int i = 0; i < WIDTH; i ++) {
            for (int j = 0; j < HEIGHT; j ++) {
                //initialize and set every tile to be nothing
                finalWorldFrame[i][j] = Tileset.NOTHING;
            }
        }
    }


    public void saveGame(TETile[][] finalWorldFrame) {
        File file = new File("save.txt");
        if (! file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            FileOutputStream f = new FileOutputStream(file);
            ObjectOutputStream o = new ObjectOutputStream(f);
            o.writeObject(finalWorldFrame);
            o.close();
            f.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    public void executeCommands(String commands) {

    }

}
