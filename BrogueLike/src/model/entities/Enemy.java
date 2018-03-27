package model.entities;

import java.awt.Point;
import java.sql.Time;
import java.util.Timer;
import java.util.TimerTask;

import model.dungeon.Dungeon;
import model.dungeon.DungeonGraph;
import model.dungeon.PathFinder;
import view.utils.Direction;

abstract public class Enemy extends Entity {

	public Enemy (Point position, Dungeon dungeon) {
	    super(position, dungeon);
	}
}
