package model.dungeon.tiles;

import view.utils.ExtendedAscii;

import java.awt.*;

public class Wall extends Tile implements Unwalkable {
    public Wall () {
        symbol = ExtendedAscii.getAscii(177);
        color = new Color(41, 56, 61);
    }

}
