package view;
import java.awt.Point;
import java.io.IOException;

import model.Entity;
import model.Ground;
import model.Tile;
import model.Wall;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

public class DungeonView {
	final char WALL = ExtendedAscii.getAscii(177);
	final char GROUND = '.';
	final int SIZE = 30;
	private Terminal terminal = new DefaultTerminalFactory().createTerminal();
	
	public DungeonView() throws IOException {
		terminal.setCursorVisible(false);
		
		//Basic squared map 
		for(int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				if (i == 0 || i == SIZE-1 || j == 0 || j == SIZE-1) {
					terminal.putCharacter(WALL);
					terminal.putCharacter(WALL);
				} else { 
					terminal.putCharacter(GROUND);
					terminal.putCharacter(' ');
				}
			}
			terminal.setCursorPosition(0, i+1);
		}
	}
	
	public void display(Entity entity) throws IOException {
		terminal.putCharacter(entity.tile().symbol);
		terminal.setCursorPosition(entity.position().x*2, entity.position().y);
		terminal.putCharacter(entity.symbol);
		terminal.flush();
	}
	
	public KeyStroke getInput() throws IOException {
		KeyStroke key = terminal.readInput();
		while(key == null) {
			key = terminal.readInput();
        }
		return key;
	}
	
}
