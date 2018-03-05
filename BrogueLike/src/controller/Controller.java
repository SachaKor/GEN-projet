package controller;
import java.awt.Point;
import java.io.IOException;
import java.util.LinkedList;

import com.googlecode.lanterna.input.KeyStroke;

import model.dungeon.Dungeon;
import model.dungeon.Unwalkable;
import model.entities.Enemy;
import model.entities.Hero;
import model.entities.Spider;
import view.dungeon.DungeonView;

import model.entities.Entity.Direction;

import javax.swing.text.Position;

public class Controller {
	private Dungeon dungeon;
	private DungeonView dungeonView;
	private Hero hero;
	private LinkedList<Enemy> enemies;
	
	public Controller(DungeonView dungeonView) throws IOException {
		this.dungeonView = dungeonView;
		dungeon = new Dungeon();
		hero = new Hero(new Point(1,1), dungeon);
		initEnemies();
	}
	
	public void initGame() throws IOException {
		dungeonView.showMap(dungeon.getTiles());
		dungeon.placeEntity(hero);
		dungeonView.display(hero);
		initEnemies();
		for(Enemy enemy : enemies) {
			dungeon.placeEntity(enemy);
			dungeonView.display(enemy);
		}

	}
	
	public void initEnemies() {
		enemies = new LinkedList<Enemy>();
		//TODO Initialize enemies to random positions
		enemies.add(new Spider(new Point(13,15), dungeon));
		enemies.add(new Spider(new Point(4,8), dungeon));
		enemies.add(new Spider(new Point(18,18), dungeon));
	}
	
	public void input() throws IOException {
		//If move, check adjacent tile to see if the move is legal then apply it
		while (true) {
			KeyStroke key = dungeonView.getInput();
			switch (key.getKeyType()) {
			case ArrowDown:
				if (!(dungeon.getTile(new Point(hero.position().x, hero.position().y+1)) instanceof Unwalkable)) {
					Point previousPos = hero.position();
					hero.move(Direction.DOWN);
                    dungeonView.move(hero, previousPos);
                }
				break;
			case ArrowLeft:
                if (!(dungeon.getTile(new Point(hero.position().x-1, hero.position().y)) instanceof Unwalkable)) {
					Point previousPos = hero.position();
                	hero.move(Direction.LEFT);
                    dungeonView.move(hero, previousPos);
                }
				break;
			case ArrowRight:
                if (!(dungeon.getTile(new Point(hero.position().x+1, hero.position().y)) instanceof Unwalkable)) {
					Point previousPos = hero.position();
                	hero.move(Direction.RIGHT);
					dungeonView.move(hero, previousPos);
                }
				break;
			case ArrowUp:
                if (!(dungeon.getTile(new Point(hero.position().x, hero.position().y-1)) instanceof Unwalkable)) {
					Point previousPos = hero.position();
                	hero.move(Direction.UP);
					dungeonView.move(hero, previousPos);
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
