package view;
import java.awt.Point;
import java.io.IOException;
import java.security.Signature;
import java.util.LinkedList;

import model.Entity;
import model.Ground;
import model.Tile;
import model.Wall;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import javax.swing.text.Position;

public class DungeonView {
	final int SIZE = 30;
	private Terminal terminal = new DefaultTerminalFactory().createTerminal();
	
	public DungeonView() throws IOException {
		terminal.setCursorVisible(false);
	}

	public void showMap(LinkedList<Tile> map) throws IOException  {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                terminal.putCharacter(map.get((i*SIZE+j)).symbol);
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
