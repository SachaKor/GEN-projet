package model.entities;

import java.awt.Point;
import java.io.IOException;

import model.dungeon.Dungeon;
import model.dungeon.Tile;

public abstract class Entity {
	public enum Direction {
		LEFT(new Point(-1,0)),
		UP(new Point(0,-1)),
		RIGHT(new Point(1,0)),
		DOWN(new Point(0,1));
		private Point direction;
		
		private Direction(Point direction) {
			this.direction = direction;
		}
		
	}
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
	
	public void move(Direction direction) throws IOException {
	    Point previousPos = position;
		position = new Point(position.x + direction.direction.x, position.y + direction.direction.y);
		tile = dungeon.getTile(position);
	}
	
	public void tile(Tile tile) {
		this.tile = tile;
	}
	
	public Tile tile() {
		return tile;
	}
}
