package model.dungeon;

import java.awt.*;
import java.io.IOException;
import java.util.LinkedList;

import model.entities.Entity;

public class Dungeon {
	private Tile[][] tiles;
	private LinkedList<Entity> entities;
	private LinkedList<Room> rooms;
	private DungeonGraph graph;
	
	final public static int DUNGEON_SIZE = 25;
	
	public Dungeon() throws IOException {
		tiles = new Tile[DUNGEON_SIZE][DUNGEON_SIZE];
		rooms = new LinkedList<>();
		generateRooms(1000000);
		generateDungeon();
		entities = new LinkedList<Entity>();
		graph = new DungeonGraph(tiles);
	}

	public void generateRooms(int maxTries) {
	    int tries = 0;
        rooms.add(new Room());
        while(tries++ < maxTries) {
            boolean intersects = false;
            Room newRoom = new Room();
            for (Room room : rooms) {
                if (newRoom.intersects(room)) intersects = true;
            }
            if (!intersects) rooms.add(newRoom);
        }
    }

	public void generateDungeon() {
        for (int i = 0; i < DUNGEON_SIZE; i++) {
            for (int j = 0; j < DUNGEON_SIZE; j++) {
                tiles[i][j] = new Wall();
            }
        }

        for (Room room : rooms) {
            for (int i = room.x1; i < room.x2; i++) {
                for (int j = room.y1; j < room.y2; j++) {
                    tiles[i][j] = new Ground();
                }
            }
        }
    }

    public Tile getTile(Point position) {
        return tiles[position.x][position.y];
    }
	
	public void placeEntity(Entity entity) throws IOException {
		entities.add(entity);
	}
	
	public int size() {
		return DUNGEON_SIZE;
	}
	
	public LinkedList<Entity> getEntities() {
		return entities;
	}
	public Tile[][] getTiles() { return tiles; }
}
