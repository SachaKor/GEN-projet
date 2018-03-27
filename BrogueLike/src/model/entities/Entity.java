package model.entities;

import java.awt.Point;
import java.io.IOException;

import model.dungeon.Dungeon;
import model.dungeon.Tile;

public abstract class Entity {
	public char symbol;
	protected Dungeon dungeon;
	protected Point position;
	private Tile tile;
	
	public Entity (Point position, Dungeon dungeon) {
		this.position = position;
		this.dungeon = dungeon;
		this.tile = dungeon.getTile(position);
	}
	
	public Point position() {
		return position;
	}
	
	public void move(Point nextPos) {
		position = nextPos;
		tile = dungeon.getTile(position);
	}
	
	public Tile tile() {
		return tile;
	}
}
