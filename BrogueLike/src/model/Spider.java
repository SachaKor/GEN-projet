package model;

import java.awt.Point;

import view.DungeonView;

public class Spider extends Enemy {
	public Spider(Point position, DungeonView dungeonView, Dungeon dungeon) {
		super(position, dungeonView, dungeon);
		symbol = 'S';
	}
}
