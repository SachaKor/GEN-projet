package model;

import java.awt.Point;

import view.DungeonView;

abstract public class Enemy extends Entity {
	public Enemy (Point position, DungeonView dungeonView, Dungeon dungeon) {
		super(position, dungeonView, dungeon);
	}
	
}
