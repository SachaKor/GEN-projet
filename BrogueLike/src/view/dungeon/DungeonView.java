package view.dungeon;
import java.awt.Point;
import java.io.IOException;
import java.util.LinkedList;

import model.dungeon.Dungeon;
import model.entities.Entity;
import model.dungeon.Tile;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

public class DungeonView {
	private Terminal terminal = new DefaultTerminalFactory().createTerminal();
	
	public DungeonView() throws IOException {
		terminal.setCursorVisible(false);
	}

	public void showMap(Tile[][] map) throws IOException  {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                terminal.putCharacter(map[i][j].symbol);
                terminal.putCharacter(' ');
            }
            terminal.setCursorPosition(0, i+1);
        }
	}
	
	public void display(Entity entity) throws IOException {
		terminal.setCursorPosition(entity.position().x*2, entity.position().y);
		terminal.putCharacter(entity.symbol);
		terminal.flush();
	}

    public void move(Entity entity, Point previousPos) throws IOException {
        terminal.setCursorPosition(previousPos.x*2, previousPos.y);
        terminal.putCharacter(entity.tile().symbol);
        display(entity);
    }
	
	public KeyStroke getInput() throws IOException {
		KeyStroke key = terminal.readInput();
		while(key == null) {
			key = terminal.readInput();
        }
		return key;
	}
	
}
