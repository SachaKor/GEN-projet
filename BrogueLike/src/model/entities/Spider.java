package model.entities;

import java.awt.Point;

import model.dungeon.Dungeon;

public class Spider extends Enemy {
	public Spider(Point position, Dungeon dungeon) {
		super(position, dungeon);
		symbol = 'S';


	}



	private void mapToGraph() {

	}
}
