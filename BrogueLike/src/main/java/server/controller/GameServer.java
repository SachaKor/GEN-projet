package server.controller;

import client.controller.ClientController;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class GameServer {
    ServerSocket serverSocket;
    public static int nbClients = 0;
    public static final int MAX_CONNEXIONS = 2;
    private int port = 3030;
    private static GameServer server = new GameServer();
    private boolean running;
    private List<ClientWorker> clientWorkers = new CopyOnWriteArrayList<>();

    private GameServer(int port) {
        this.port = port;
    }

    private GameServer() {
    }

    public void startServer() throws IOException {
        serverSocket = new ServerSocket();
        serverSocket.bind(new InetSocketAddress(port));

        Thread serverThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Socket clientSocket = serverSocket.accept();
                    nbClients++;
                    ClientWorker clientWorker = new ClientWorker(clientSocket, getClientHandler(), GameServer.this);
                    clientWorkers.add(clientWorker);
                    Thread clientThread = new Thread(clientWorker);
                    clientThread.start();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });
        serverThread.start();
    }

    private IClientHandler getClientHandler() {
        return new ClientHandler(nbClients);
    }

    public static GameServer getServer() {
        return server;
    }
}