package server.model.entities;

import server.model.Dungeon;
import server.model.tiles.Tile;
import server.model.tiles.Unwalkable;

import java.awt.*;

public abstract class Entity implements Unwalkable {
    private static int nbEntity = 0;
    private int id;
    public char symbol;
    protected Point position;
    private Tile tile;

    public Entity (Point position, int id) {
        this.id = id;
        this.position = position;
        this.tile = Dungeon.getDungeon().getTile(position);
    }

    public int getId() {
        return id;
    }

    public Point position() {
        return position;
    }

    public void move(Point nextPos) {
        if (Dungeon.getDungeon().getEntity(nextPos) == null) {
            Dungeon.getDungeon().moveEntity(this, nextPos);
            position = nextPos;
            tile = Dungeon.getDungeon().getTile(position);
        }
    }

    public Tile tile() {
        return tile;
    }
}
