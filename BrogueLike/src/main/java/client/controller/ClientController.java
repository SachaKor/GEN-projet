package client.controller;

import client.model.Dungeon;
import client.model.entities.Entity;
import client.utils.Direction;
import client.utils.JsonObjectMapper;
import client.view.DungeonView;
import protocol.MoveCommandResponse;
import protocol.MoveProtocol;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientController implements Runnable {
    Socket clientSocket;
    BufferedReader in;
    PrintWriter out;
    Dungeon dungeon;

    public void connect(String server, int port) throws IOException {
        clientSocket = new Socket(server, port);
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        out = new PrintWriter(clientSocket.getOutputStream());
    }

    public void move(Entity entity, Direction direction) throws IOException {
        switch (direction) {
            case UP:
                out.println(MoveProtocol.UP);
                out.flush();
                break;
            case DOWN:
                out.println(MoveProtocol.DOWN);
                out.flush();
                break;
            case LEFT:
                out.println(MoveProtocol.LEFT);
                out.flush();
                break;
            case RIGHT:
                out.println(MoveProtocol.RIGHT);
                out.flush();
                break;
            default:
                break;
        }
    }

    private void listen() throws IOException {
        String line;
        while (true) {
            line = in.readLine();
            switch (line) {
                case MoveProtocol.MOVE_RESPONSE :
                    MoveCommandResponse response = JsonObjectMapper.parseJson(in.readLine(), MoveCommandResponse.class);
                    Dungeon.getDungeon().moveEntity(Dungeon.getDungeon().getEntity(response.getId()), response.getDirection());
                    DungeonView.getDungeonView().move(Dungeon.getDungeon().getEntity(response.getId()), response.getDirection());
                    break;
            }
        }
    }

    public void run() {
        try {
            listen();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
