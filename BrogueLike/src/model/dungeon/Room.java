package model.dungeon;

import java.util.concurrent.ThreadLocalRandom;

public class Room {
    final int MAX_SIZE = 6;
    final int MIN_SIZE = 3;
    //x1,y1 = top left corner, x2,y2 = bottom right corner
    public int x1, x2, y1, y2;
    public int width, height;

    public Room() {
        width = ThreadLocalRandom.current().nextInt(MIN_SIZE, MAX_SIZE + 1);
        height = ThreadLocalRandom.current().nextInt(MIN_SIZE, MAX_SIZE + 1);
        x1 = ThreadLocalRandom.current().nextInt(1, Dungeon.DUNGEON_SIZE-width);
        y1 = ThreadLocalRandom.current().nextInt(1, Dungeon.DUNGEON_SIZE-height);
        x2 = x1 + width;
        y2 = y1 + height;
    }

    public boolean intersects(Room room) {
        return (x1 <= room.x2 && x2 >= room.x1 && y1 <= room.y2 && room.y2 >= room.y1);
    }
}
