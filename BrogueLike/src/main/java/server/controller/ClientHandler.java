package server.controller;

import client.utils.JsonObjectMapper;
import protocol.MoveCommandResponse;
import server.model.Dungeon;
import server.model.entities.Hero;
import server.utils.Direction;
import protocol.GameProtocol;
import protocol.MoveProtocol;

import java.awt.*;
import java.io.*;

public class ClientHandler implements IClientHandler {
    private Hero hero;

    public ClientHandler(int id) {
        hero = new Hero(new Point(4+id, 4), id);
    }

    @Override
    public void handleClientConnection(InputStream is, OutputStream os) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        PrintWriter writer = new PrintWriter(new OutputStreamWriter(os));

        String command;
        boolean done = false;
        while (true) {
            MoveCommandResponse message;
            command = reader.readLine();
            switch (command.toUpperCase()) {
                case GameProtocol.HELLO :
                    Dungeon.getDungeon().initHero(hero);
                    writer.println(hero.getId());
                    writer.flush();
                    break;
                case MoveProtocol.LEFT :
                    Dungeon.getDungeon().moveEntity(hero, Direction.LEFT);
                    message = new MoveCommandResponse();
                    message.setId(hero.getId());
                    message.setDirection(client.utils.Direction.LEFT);
                    GameServer.getServer().notifyClients(JsonObjectMapper.toJson(message));
                    break;
                case MoveProtocol.DOWN :
                    Dungeon.getDungeon().moveEntity(hero, Direction.DOWN);
                    message = new MoveCommandResponse();
                    message.setId(hero.getId());
                    message.setDirection(client.utils.Direction.DOWN);
                    GameServer.getServer().notifyClients(JsonObjectMapper.toJson(message));
                    break;
                case MoveProtocol.UP :
                    Dungeon.getDungeon().moveEntity(hero, Direction.UP);
                    message = new MoveCommandResponse();
                    message.setId(hero.getId());
                    message.setDirection(client.utils.Direction.UP);
                    GameServer.getServer().notifyClients(JsonObjectMapper.toJson(message));
                    break;
                case MoveProtocol.RIGHT :
                    Dungeon.getDungeon().moveEntity(hero, Direction.RIGHT);
                    message = new MoveCommandResponse();
                    message.setId(hero.getId());
                    message.setDirection(client.utils.Direction.RIGHT);
                    GameServer.getServer().notifyClients(JsonObjectMapper.toJson(message));
                    break;
                default:
                    break;
            }
        }
    }
}
