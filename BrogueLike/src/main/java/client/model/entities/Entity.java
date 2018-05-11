package client.model.entities;

import client.model.Dungeon;
import client.model.tiles.Tile;
import client.model.tiles.Unwalkable;

import java.awt.*;

public abstract class Entity implements Unwalkable {
    private static int nbEntity = 0;
    private int id;
    public char symbol;
    protected Point position;
    private Tile tile;

    public Entity (Point position) {
        id = nbEntity++;
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
