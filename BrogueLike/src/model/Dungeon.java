package model;

import java.awt.*;
import java.io.IOException;
import java.util.LinkedList;

import view.DungeonView;

public class Dungeon {
	private DungeonView dungeonView;
	private LinkedList<Tile> tiles;
	private LinkedList<Entity> entities;
	
	final private int DUNGEON_SIZE = 30;
	
	public Dungeon(DungeonView dungeonView) throws IOException {
		this.dungeonView = dungeonView;
		tiles = new LinkedList<Tile>();
		generateTiles();
		dungeonView.showMap(tiles);
		entities = new LinkedList<Entity>();
	}
	
	public void generateTiles() {
		//Basic squared map 
		for(int i = 0; i < DUNGEON_SIZE; i++) {
			for (int j = 0; j < DUNGEON_SIZE; j++) {
				if (i == 0 || i == DUNGEON_SIZE-1 || j == 0 || j == DUNGEON_SIZE-1) {
					tiles.add(new Wall());
				} else { 
					tiles.add(new Ground());
				}
			}
		}
	}

    public Tile getTile(Point position) {
        return tiles.get((position.x*DUNGEON_SIZE)+position.y);
    }
	
	public void placeEntity(Entity entity) throws IOException {
		entities.add(entity);
		dungeonView.display(entity);
	}
	
	public int size() {
		return DUNGEON_SIZE;
	}
	
	public LinkedList<Entity> getEntities() {
		return entities;
	}
}
