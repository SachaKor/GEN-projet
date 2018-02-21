package model;

import java.awt.Point;

import view.DungeonView;

public class Spider extends Enemy {
	public Spider(Point position, DungeonView dungeonView) {
		super(position, dungeonView);
		symbol = 'S';
	}
}
