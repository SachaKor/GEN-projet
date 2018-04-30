package main.java.server.model.dungeon.entities;

import main.java.server.model.dungeon.Dungeon;

import java.awt.*;

public class Spider extends Enemy {
    public Spider(Point position, Dungeon dungeon) {
        super(position, dungeon);
        symbol = 'S';
    }
}
