package server.model;

import server.model.entities.Entity;
import server.model.tiles.Tile;
import server.model.tiles.DeepWater;
import server.model.tiles.Ground;
import server.model.tiles.ShallowWater;
import server.model.tiles.Wall;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;

public class Dungeon {
    private Tile[][] tiles;
    private Entity[][] entities;

    final public static int DUNGEON_SIZE = 24;

    public Dungeon() {
        tiles = new Tile[DUNGEON_SIZE][DUNGEON_SIZE];
        generateDungeon();
        entities = new Entity[DUNGEON_SIZE][DUNGEON_SIZE];
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
                            break;
                        case 's':
                            tiles[i][j] = new ShallowWater();
                            break;
                        case 'd':
                            tiles[i][j] = new DeepWater();
                            break;
                        default:
                            tiles[i][j] = new Ground();
                            break;
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
        return tiles[position.y][position.x];
    }

    public Entity getEntity(Point position) {
        return entities[position.y][position.x];
    }

    public void moveEntity(Entity entity, Point newPos) {
        entities[entity.position().y][entity.position().x] = null;
        entities[newPos.y][newPos.x] = entity;
    }

    public void placeEntity(Entity entity) {
        entities[entity.position().y][entity.position().x] = entity;
    }

    public Tile[][] getTiles() { return tiles; }
}
