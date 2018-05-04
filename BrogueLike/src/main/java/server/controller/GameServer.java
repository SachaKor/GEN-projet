package server.controller;

public class GameServer implements Runnable {
    public static final int MAX_CONNEXIONS = 2;
    private final int PORT = 5050;
    private static GameServer server;
    private boolean running;

    private GameServer() {

    }

    @Override
    public void run() {

    }
}
