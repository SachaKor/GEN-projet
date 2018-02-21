package controller;
import java.awt.Point;
import java.io.IOException;
import java.util.LinkedList;

import com.googlecode.lanterna.input.KeyStroke;

import view.DungeonView;

import model.Dungeon;
import model.Enemy;
import model.Entity.Direction;
import model.Hero;
import model.Spider;

public class Controller {
	private Dungeon dungeon;
	private DungeonView dungeonView;
	private Hero hero;
	private LinkedList<Enemy> enemies;
	
	public Controller(DungeonView dungeonView) throws IOException {
		this.dungeonView = dungeonView;
		dungeon = new Dungeon(dungeonView);
		hero = new Hero(new Point(1,1), dungeonView);
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
		enemies.add(new Spider(new Point(13,20), dungeonView));
		enemies.add(new Spider(new Point(22,8), dungeonView));
		enemies.add(new Spider(new Point(18,19), dungeonView));
	}
	
	public void input() throws IOException {
		//If move, check adjacent tile to see if the move is legal then apply it
		while (true) {
			KeyStroke key = dungeonView.getInput();
			switch (key.getKeyType()) {
			case ArrowDown:
				hero.move(Direction.DOWN);
				break;
			case ArrowLeft:
				hero.move(Direction.LEFT);
				break;
			case ArrowRight:
				hero.move(Direction.RIGHT);
				break;
			case ArrowUp:
				hero.move(Direction.UP);
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
