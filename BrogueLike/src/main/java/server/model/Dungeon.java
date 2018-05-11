package server.model;

import server.model.entities.Entity;
import server.model.entities.Hero;
import server.model.tiles.Tile;
import server.model.tiles.DeepWater;
import server.model.tiles.Ground;
import server.model.tiles.ShallowWater;
import server.model.tiles.Wall;
import server.utils.Direction;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Dungeon {
    private Tile[][] tiles;
    private Entity[][] entities;
    private Hero hero;

    static private Dungeon dungeon;

    static {
        try {
            dungeon = new Dungeon();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    final public static int DUNGEON_SIZE = 24;

    private Dungeon() throws IOException {
        tiles = new Tile[DUNGEON_SIZE][DUNGEON_SIZE];
        generateDungeon();
        entities = new Entity[DUNGEON_SIZE][DUNGEON_SIZE];
        initHero();
    }

    private synchronized void initHero() throws IOException {
        Hero hero = new Hero(new Point(4, 4));
        this.hero = hero;
        placeEntity(hero);
    }

    public Hero getHero() {
        return hero;
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

    public Entity getEntity(int id) {
        for (Entity[] entLine : entities) {
            for (Entity entity : entLine) {
                if (entity.getId() == id) {
                    return entity;
                }
            }
        }
        return null;
    }

    public void moveEntity(Entity entity, Point newPos) {
        entities[entity.position().y][entity.position().x] = null;
        entities[newPos.y][newPos.x] = entity;
    }

    public void moveEntity(Entity entity, Direction direction) {
        entities[entity.position().y][entity.position().x] = null;
        entities[entity.position().y + direction.y()][entity.position().x + direction.x()] = entity;
    }

    public void placeEntity(Entity entity) {
        entities[entity.position().y][entity.position().x] = entity;
    }

    public Tile[][] getTiles() { return tiles; }

    static public Dungeon getDungeon() {
        return dungeon;
    }
}
