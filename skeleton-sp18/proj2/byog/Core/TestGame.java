package byog.Core;
import byog.TileEngine.TETile;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.*;


/**
 * Testing some methods in Game
 */
public class TestGame {
    @Test
    public void testGetSeedFromInput() {
        Game game = new Game();
        String test1 = "n12398awskdejhk";
        int result1 = game.getSeedFromInput(test1);
        String s1 = game.getCommandsFromInput(test1);
        assertEquals(12398, result1);
        assertEquals("awskdejhk", s1);

        String test2 = "n1872827wjjewhhsadsf:q";
        int result2 = game.getSeedFromInput(test2);
        String s2 = game.getCommandsFromInput(test2);
        assertEquals(1872827, result2);
        assertEquals("wjjewhhsadsf", s2);
    }

    @Test
    public void testLoadGame() {
        Game game = new Game();
        File file = new File("save.txt");
        assertEquals(file.exists(), false);
        boolean result;
        try {
            result = file.createNewFile();
            assertEquals(result, true);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(0);
        }
    }
}