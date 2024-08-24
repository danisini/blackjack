package config;

import com.sun.net.httpserver.HttpServer;
import controller.GameController;
import java.io.IOException;
import java.net.InetSocketAddress;

import static util.CommonConstants.*;

public class ServerConfig {
    private final HttpServer server;

    public ServerConfig(GameController gameController) throws IOException {
        this.server = setupServer(gameController);
    }

    private HttpServer setupServer(GameController gameController) throws IOException {
        int port = Integer.parseInt(ConfigLoader.getProperty(PORT));
        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);

        server.createContext(START, gameController.start());
        server.createContext(HIT, gameController.hit());
        server.createContext(STAND, gameController.stand());
        server.createContext(SPLIT, gameController.split());
        server.createContext(DOUBLE, gameController.split());

        return server;
    }

    public void start() {
        server.start();
    }
}
