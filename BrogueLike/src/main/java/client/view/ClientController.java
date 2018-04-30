package main.java.client.view;

import main.java.server.model.dungeon.tiles.Tile;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

public class ClientController {
    Socket socket;
    ObjectInputStream in;

    public boolean connect(String server, int port) {
        try {
            socket = new Socket(server, port);
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    public Tile[][] getMap() throws IOException, ClassNotFoundException {
        return (Tile[][])in.readObject();
    }

    public
}
