package client.model.entities;

import client.model.Dungeon;
import client.model.tiles.Tile;
import client.model.tiles.Unwalkable;

import java.awt.*;

public abstract class Entity implements Unwalkable {
    private static int nbEntity = 0;
    private int id;
    public char symbol;
    protected Dungeon dungeon;
    protected Point position;
    private Tile tile;

    public Entity (Point position, Dungeon dungeon) {
        id = nbEntity++;
        this.position = position;
        this.dungeon = dungeon;
        this.tile = dungeon.getTile(position);
    }

    public int getId() {
        return id;
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
