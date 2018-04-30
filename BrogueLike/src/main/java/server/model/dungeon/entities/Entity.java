package main.java.server.model.dungeon.entities;

import main.java.server.model.dungeon.Dungeon;
import main.java.server.model.dungeon.tiles.Tile;
import model.dungeon.tiles.Unwalkable;

import java.awt.*;

public abstract class Entity implements Unwalkable {
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
        if (dungeon.getEntity(nextPos) == null) {
            dungeon.moveEntity(this, nextPos);
            position = nextPos;
            tile = dungeon.getTile(position);
        }
    }

    public Tile tile() {
        return tile;
    }
}
