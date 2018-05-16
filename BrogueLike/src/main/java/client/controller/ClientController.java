package client.controller;

import client.model.Dungeon;
import client.model.entities.Entity;
import client.utils.JsonObjectMapper;
import client.view.DungeonView;
import protocol.MoveCommandResponse;
import protocol.MoveProtocol;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class ClientController implements Runnable {
    BufferedReader in;
    PrintWriter out;

    public ClientController(BufferedReader in, PrintWriter out) {
        this.in = in;
        this.out = out;
    }

    private void listen() throws IOException {
        String line;
        while (true) {
            line = in.readLine();
            switch (line) {
                case MoveProtocol.MOVE_RESPONSE :
                    MoveCommandResponse response = JsonObjectMapper.parseJson(in.readLine(), MoveCommandResponse.class);
                    Dungeon dungeon = Dungeon.getDungeon();
                    Entity entity = dungeon.getEntity(response.getId());
                    Point prevPos = entity.position();
                    dungeon.moveEntity(entity, response.getDirection());
                    DungeonView.getDungeonView().move(dungeon.getEntity(response.getId()), prevPos);
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
