package model.dungeon.tiles;

import main.java.server.model.dungeon.tiles.Tile;

import java.awt.*;

public class DeepWater extends Tile implements Unwalkable {
    public DeepWater () {
        symbol = '~';
        color = new Color(0,13,146);
    }
}