package server.model.entities;

import server.model.Dungeon;

import java.awt.*;

abstract public class Enemy extends Entity {

	public Enemy (Point position, Dungeon dungeon) {
	    super(position, dungeon);
	}
}
