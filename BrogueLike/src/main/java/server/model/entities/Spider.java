package server.model.entities;

import java.awt.*;

public class Spider extends Enemy {
    public Spider(Point position, int id) {
        super(position, id);
        symbol = 'S';
    }
}
