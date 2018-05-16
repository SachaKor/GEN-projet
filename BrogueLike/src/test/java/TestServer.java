import java.io.IOException;

import client.controller.ServerLink;
import client.view.DungeonView;
import org.testng.annotations.Test;

import server.controller.GameServer;

public class TestServer {
    @Test
    public void testServer() throws IOException {
        GameServer server = GameServer.getServer();
        server.startServer();

        ServerLink client = new ServerLink();
        client.connect("localhost", 3030);
        ServerLink client2 = new ServerLink();
        client2.connect("localhost", 3030);

        DungeonView dungeonView = DungeonView.getDungeonView();
        dungeonView.getInput();
    }
}
