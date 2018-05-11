package server.controller;

import protocol.GameProtocol;

import java.io.*;

public class ClientHandler implements IClientHandler {
    private int id;

    public ClientHandler(int id) {
        this.id = id;
    }

    @Override
    public void handleClientConnection(InputStream is, OutputStream os) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        PrintWriter writer = new PrintWriter(new OutputStreamWriter(os));

        String command;
        boolean done = false;
        while (true) {
            command = reader.readLine();
            switch (command.toUpperCase()) {
                case GameProtocol.HELLO :
                    writer.println(id);
                    writer.flush();
                    break;
                default:
                    break;
            }
        }
    }
}
