package model;

import java.awt.Point;
import java.io.IOException;

import view.DungeonView;

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
	private DungeonView dungeonView;
	private Point position;
	private Tile tile;
	
	public Entity (Point position, DungeonView dungeonView) {
		this.position = position;
		this.dungeonView = dungeonView;
	}
	
	public Point position() {
		return position;
	}
	
	public void move(Direction direction) throws IOException {
		position = new Point(position.x + direction.direction.x, position.y + direction.direction.y);
		dungeonView.display(this);
	}
	
	public void tile(Tile tile) {
		this.tile = tile;
	}
	
	public Tile tile() {
		return tile;
	}
}
