package model;

import java.awt.Point;

import view.DungeonView;


public class Hero extends Entity {	
	public Hero(Point position, DungeonView dungeonView) {
		super(position, dungeonView);
		symbol = 'X';
	}
}
