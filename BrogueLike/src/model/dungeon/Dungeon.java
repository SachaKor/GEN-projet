package model.dungeon;

import java.awt.*;
import java.io.*;
import java.util.LinkedList;

import model.entities.Entity;

public class Dungeon {
    private Tile[][] tiles;
    private LinkedList<Entity> entities;
    private LinkedList<Room> rooms;
    private DungeonGraph graph;

    final public static int DUNGEON_SIZE = 24;

    public Dungeon() {
        tiles = new Tile[DUNGEON_SIZE][DUNGEON_SIZE];
        rooms = new LinkedList<>();
        generateDungeon();
        entities = new LinkedList<>();
        graph = new DungeonGraph(tiles);
    }

    public void generateDungeon() {
        try {
            BufferedReader br = new BufferedReader(new FileReader("./data/dungeon.txt"));
            String line = br.readLine();

            int i = 0;
            while (line != null) {
                for (int j = 0; j < line.length(); j++) {
                    switch(line.charAt(j)) {
                        case 'w':
                            tiles[i][j] = new Wall();
                            break;
                        case 'g':
                            tiles[i][j] = new Ground();
                        default:
                            tiles[i][j] = new Ground();
                    }
                }
                line = br.readLine();
                i++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public Tile getTile(Point position) {
        return tiles[position.x][position.y];
    }

    public void placeEntity(Entity entity) {
        entities.add(entity);
    }

    public Tile[][] getTiles() { return tiles; }
}
