package client.model.entities;

import client.model.Dungeon;

import java.awt.*;


public class Hero extends Entity {
    private int health;
    private int strength;
    private int gold;

    public Hero(Point position, int id) {
        super(position, id);
        symbol = 'X';
        health = 20;
        strength = 1;
        gold = 0;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }
}
