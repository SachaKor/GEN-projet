package model.entities;

import java.awt.Point;

import model.dungeon.Dungeon;
import view.dungeon.DungeonView;


public class Hero extends Entity {	
	public Hero(Point position, Dungeon dungeon) {
		super(position, dungeon);
		symbol = 'X';
	}
}
