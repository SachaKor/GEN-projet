package model.dungeon;

import view.utils.ExtendedAscii;

public class Wall extends Tile implements Unwalkable {
    public Wall () {
        symbol = ExtendedAscii.getAscii(177);
    }
}
