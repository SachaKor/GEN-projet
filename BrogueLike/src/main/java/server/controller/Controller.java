package main.java.server.controller;
import com.googlecode.lanterna.input.KeyStroke;
import main.java.server.GameServer;
import main.java.server.model.dungeon.Dungeon;
import model.dungeon.DungeonGraph;
import main.java.server.model.dungeon.entities.Enemy;
import main.java.server.model.dungeon.entities.Hero;
import main.java.server.model.dungeon.entities.PathFinder;
import main.java.server.model.dungeon.entities.Spider;
import model.dungeon.tiles.Unwalkable;
import main.java.client.view.dungeon.DungeonView;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Stack;

public class Controller {
    private Dungeon dungeon;
    private DungeonView dungeonView;
    private Hero hero;
    private LinkedList<Enemy> enemies;
    private GameServer srv;

    public Controller(DungeonView dungeonView) throws IOException {
        srv = new GameServer(3030);
        srv.connect();
        this.dungeonView = dungeonView;
        dungeon = new Dungeon();
        hero = new Hero(new Point(20,4), dungeon);
        initEnemies();
    }

    public void initGame() throws IOException {
        dungeonView.showMap(dungeon.getTiles());
        dungeon.placeEntity(hero);
        dungeonView.displayEntity(hero);
        initEnemies();
        for(Enemy enemy : enemies) {
            dungeon.placeEntity(enemy);
            dungeonView.displayEntity(enemy);
        }
        moveEnemies();
        input();
    }

    public void initEnemies() {
        enemies = new LinkedList<>();
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
                        hero.move(new Point(hero.position().x, hero.position().y + 1));
                        dungeonView.move(hero, previousPos);
                    }
                    break;
                case ArrowLeft:
                    if (!(dungeon.getTile(new Point(hero.position().x-1, hero.position().y)) instanceof Unwalkable)) {
                        Point previousPos = hero.position();
                        hero.move(new Point(hero.position().x - 1, hero.position().y));
                        dungeonView.move(hero, previousPos);
                    }
                    break;
                case ArrowRight:
                    if (!(dungeon.getTile(new Point(hero.position().x+1, hero.position().y)) instanceof Unwalkable)) {
                        Point previousPos = hero.position();
                        hero.move(new Point(hero.position().x + 1, hero.position().y));
                        dungeonView.move(hero, previousPos);
                    }
                    break;
                case ArrowUp:
                    if (!(dungeon.getTile(new Point(hero.position().x, hero.position().y-1)) instanceof Unwalkable)) {
                        Point previousPos = hero.position();
                        hero.move(new Point(hero.position().x, hero.position().y - 1));
                        dungeonView.move(hero, previousPos);
                    }
                    break;

                default:
                    break;
            }
        }
    }

    public void moveEnemies() {
        new Timer(500, w -> {
            for(Enemy e : enemies) {
                PathFinder pathFinder = new PathFinder(new DungeonGraph(dungeon.getTiles()), e.position());
                Stack<Integer> path = pathFinder.pathTo(hero.position().y*Dungeon.DUNGEON_SIZE+hero.position().x);

                if (!path.empty()) {
                    path.pop();
                    if (!path.empty()) {
                        int nextMove = path.pop();
                        Point previousPos = e.position();
                        e.move(new Point(nextMove % Dungeon.DUNGEON_SIZE, nextMove / Dungeon.DUNGEON_SIZE));
                        try {
                            dungeonView.move(e, previousPos);
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                    }

                }
            }
        }).start();
    }

    public static void main(String[] args) throws IOException {
        Controller controller = new Controller(new DungeonView());

        controller.initGame();
    }
}
