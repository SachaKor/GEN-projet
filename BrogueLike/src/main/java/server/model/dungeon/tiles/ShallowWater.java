package model.dungeon.tiles;

import main.java.server.model.dungeon.tiles.Tile;

import java.awt.*;

public class ShallowWater extends Tile {
    public ShallowWater () {
        symbol = '~';
        color = new Color(65,105,225);
    }
}