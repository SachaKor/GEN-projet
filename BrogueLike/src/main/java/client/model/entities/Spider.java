package client.model.entities;

import client.model.Dungeon;

import java.awt.*;

public class Spider extends Enemy {
    public Spider(Point position, Dungeon dungeon) {
        super(position, dungeon);
        symbol = 'S';
    }
}
