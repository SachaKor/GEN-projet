package main.java.server.model.dungeon.entities;

import main.java.server.model.dungeon.Dungeon;

import java.awt.*;

abstract public class Enemy extends Entity {

	public Enemy (Point position, Dungeon dungeon) {
	    super(position, dungeon);
	}
}
