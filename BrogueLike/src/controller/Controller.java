package controller;
import java.awt.Point;
import java.io.IOException;
import java.util.LinkedList;

import com.googlecode.lanterna.input.KeyStroke;

import model.*;
import view.DungeonView;

import model.Entity.Direction;

public class Controller {
	private Dungeon dungeon;
	private DungeonView dungeonView;
	private Hero hero;
	private LinkedList<Enemy> enemies;
	
	public Controller(DungeonView dungeonView) throws IOException {
		this.dungeonView = dungeonView;
		dungeon = new Dungeon(dungeonView);
		hero = new Hero(new Point(1,1), dungeonView, dungeon);
		initEnemies();
	}
	
	public void initGame() throws IOException {
		dungeon.placeEntity(hero);
		initEnemies();
		for(Enemy enemy : enemies) {
			dungeon.placeEntity(enemy);
		}
	}
	
	public void initEnemies() {
		enemies = new LinkedList<Enemy>();
		//TODO Initialize enemies to random positions
		enemies.add(new Spider(new Point(13,20), dungeonView, dungeon));
		enemies.add(new Spider(new Point(22,8), dungeonView, dungeon));
		enemies.add(new Spider(new Point(18,19), dungeonView, dungeon));
	}
	
	public void input() throws IOException {
		//If move, check adjacent tile to see if the move is legal then apply it
		while (true) {
			KeyStroke key = dungeonView.getInput();
			switch (key.getKeyType()) {
			case ArrowDown:
				if (!(dungeon.getTile(new Point(hero.position().x, hero.position().y+1)) instanceof Unwalkable)) {
                    hero.move(Direction.DOWN);
                }
				break;
			case ArrowLeft:
                if (!(dungeon.getTile(new Point(hero.position().x-1, hero.position().y)) instanceof Unwalkable)) {
                    hero.move(Direction.LEFT);
                }
				break;
			case ArrowRight:
                if (!(dungeon.getTile(new Point(hero.position().x+1, hero.position().y)) instanceof Unwalkable)) {
                    hero.move(Direction.RIGHT);
                }
				break;
			case ArrowUp:
                if (!(dungeon.getTile(new Point(hero.position().x, hero.position().y-1)) instanceof Unwalkable)) {
                    hero.move(Direction.UP);
                }
				break;
	
			default:
				break;
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		DungeonView dungeonView = new DungeonView();
        Controller controller = new Controller(dungeonView);
        
        controller.initGame();
        controller.input();
	}
}
