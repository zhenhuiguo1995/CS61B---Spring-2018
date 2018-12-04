package byog.lab5;
import org.junit.Test;
import static org.junit.Assert.*;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;

/**
 * Draws a world consisting of hexagonal regions.
 */
public class HexWorld {
    private static final int WIDTH = 50;
    private static final int HEIGHT = 50;

    //x, y specifies the lower left corner of the hexagon
    // the lower left corner of the coordinate is (0, 0)
    public static void addHexagon(TETile[][] world, Position p, int size) {
        // x stands for the lower left corner of the square which just holds the hexagon
        for (int row = 0; row < size; row ++) {
            smallToBig(row, size, p, world);
        }
        for (int row1 = 0; row1 < size; row1 ++) {
            bigToSmall(row1, size, p, world);
        }
    }

    public static void smallToBig(int row, int size, Position p, TETile[][] world) {
        for (int i = p.x - row; i < p.x - row + size + 2 * row; i ++) {
            world[i][p.y + row] = Tileset.FLOWER;
        }
    }

    public static void bigToSmall(int row, int size, Position p, TETile[][] world) {
        for (int i = p.x - size  + row + 1; i < p.x + 2 * size - 1 - row; i ++) {
            world[i][p.y + row + size] = Tileset.FLOWER;
        }
    }

    public static void fillWithNothing(TETile[][] world) {
        for (int i = 0; i < HEIGHT; i ++) {
            for (int j = 0; j < WIDTH; j ++) {
                world[i][j] = Tileset.NOTHING;
            }
        }
    }

    public static void main(String[] args) {
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);

        TETile[][] randomTiles = new TETile[WIDTH][HEIGHT];
        fillWithNothing(randomTiles);
        //randomTiles[0][25] = Tileset.WALL;
        Position p = new Position(4, 0);
        addHexagon(randomTiles, p, 3);
        ter.renderFrame(randomTiles);
    }
}
