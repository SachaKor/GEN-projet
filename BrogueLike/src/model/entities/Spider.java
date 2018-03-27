package model.entities;

import java.awt.Point;
import java.util.Timer;
import java.util.TimerTask;

import model.dungeon.Dungeon;
import model.dungeon.DungeonGraph;

public class Spider extends Enemy {
    public Spider(Point position, Dungeon dungeon) {
        super(position, dungeon);
        symbol = 'S';
    }
}
