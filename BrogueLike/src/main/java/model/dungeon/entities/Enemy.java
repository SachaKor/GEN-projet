package model.dungeon.entities;

import java.awt.Point;

import model.dungeon.Dungeon;

abstract public class Enemy extends Entity {

	public Enemy (Point position, Dungeon dungeon) {
	    super(position, dungeon);
	}
}
