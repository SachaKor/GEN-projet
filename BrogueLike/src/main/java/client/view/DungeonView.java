package client.view;
import java.awt.Point;
import java.io.IOException;

import client.model.Dungeon;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.terminal.TerminalResizeListener;
import client.model.entities.Entity;
import client.model.tiles.Tile;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

public class DungeonView implements TerminalResizeListener {
    private Terminal terminal = new DefaultTerminalFactory().createTerminal();
    private Tile[][] tmpMap;

    static DungeonView dungeonView;

    static {
        try {
            dungeonView = new DungeonView();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public DungeonView() throws IOException {
        terminal.setCursorVisible(false);
        terminal.addResizeListener(this);
    }

    public void showMap(Tile[][] map) throws IOException  {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                terminal.setBackgroundColor(new TextColor.RGB(map[i][j].color.getRed(), map[i][j].color.getGreen(), map[i][j].color.getBlue()));
                terminal.putCharacter(map[i][j].symbol);
                if (i == 0 || i == map.length-1 || j == 0 || j == map[i].length-1) {
                    terminal.putCharacter(map[i][j].symbol);
                } else
                    terminal.putCharacter(' ');
            }
            terminal.setCursorPosition(0, i+1);
        }
        terminal.flush();
        tmpMap = map;
    }

    @Override
    public void onResized(Terminal terminal, TerminalSize terminalSize) {
        try {
            if (tmpMap != null) {
                terminal.clearScreen();
                showMap(tmpMap);
            }
        } catch (IOException e) {
            return;
        }
    }

    public void displayEntity(Entity entity) throws IOException {
        Tile tile = entity.tile();
        terminal.setCursorPosition(entity.position().x*2, entity.position().y);
        terminal.setBackgroundColor(new TextColor.RGB(tile.color.getRed(), tile.color.getGreen(), tile.color.getBlue()));
        terminal.putCharacter(entity.symbol);
        terminal.flush();
    }

    private void displayTile(Point pos) throws IOException {
        Tile tile = tmpMap[pos.y][pos.x];
        terminal.setBackgroundColor(new TextColor.RGB(tile.color.getRed(), tile.color.getGreen(), tile.color.getBlue()));
        terminal.putCharacter(tmpMap[pos.y][pos.x].symbol);

    }

    public void move(Entity entity, Point previousPos) throws IOException {
        terminal.setCursorPosition(previousPos.x * 2, previousPos.y);
        displayTile(new Point(previousPos.x, previousPos.y));
        displayEntity(entity);
    }

    public KeyStroke getInput() throws IOException {
        KeyStroke key = terminal.readInput();
        while(key == null) {
            key = terminal.readInput();
        }
        return key;
    }

    static public DungeonView getDungeonView() {
        return dungeonView;
    }

}
