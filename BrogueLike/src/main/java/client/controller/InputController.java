package client.controller;

import client.model.Dungeon;
import client.model.entities.Entity;
import client.utils.Direction;
import client.view.DungeonView;
import com.googlecode.lanterna.input.KeyStroke;
import protocol.MoveProtocol;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class InputController implements Runnable {
    BufferedReader in;
    PrintWriter out;

    public InputController(BufferedReader in, PrintWriter out) {
        this.in = in;
        this.out = out;
    }

    public void input() throws IOException {
        while (true) {
            KeyStroke key = DungeonView.getDungeonView().getInput();
            switch (key.getKeyType()) {
                case ArrowDown:
                    move(Dungeon.getDungeon().getHero(), Direction.DOWN);
                    break;
                case ArrowLeft:
                    move(Dungeon.getDungeon().getHero(), Direction.LEFT);
                    break;
                case ArrowRight:
                    move(Dungeon.getDungeon().getHero(), Direction.RIGHT);
                    break;
                case ArrowUp:
                    move(Dungeon.getDungeon().getHero(), Direction.UP);
                    break;

                default:
                    break;
            }
        }
    }

    public void move(Entity entity, Direction direction) throws IOException {
        switch (direction) {
            case UP:
                out.println(MoveProtocol.UP);
                out.flush();
                break;
            case DOWN:
                out.println(MoveProtocol.DOWN);
                out.flush();
                break;
            case LEFT:
                out.println(MoveProtocol.LEFT);
                out.flush();
                break;
            case RIGHT:
                out.println(MoveProtocol.RIGHT);
                out.flush();
                break;
            default:
                break;
        }
    }

    @Override
    public void run() {
        try {
            input();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
