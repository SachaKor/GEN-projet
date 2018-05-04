package client.model.entities;

import client.model.Dungeon;

import java.awt.*;

abstract public class Enemy extends Entity {

	public Enemy (Point position, Dungeon dungeon) {
	    super(position, dungeon);
	}
}
