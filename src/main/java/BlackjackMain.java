
import config.ConfigLoader;
import config.ServerConfig;
import controller.GameController;
import model.Deck;
import service.DeckService;
import service.GameService;
import service.impl.DeckServiceImpl;
import service.impl.GameServiceImpl;

import java.io.IOException;

public class BlackjackMain {

    //TODO: logger instead of system.err
    public static void main(String[] args) {
        try {
            GameService gameService = new GameServiceImpl();
            DeckService deckService = new DeckServiceImpl(new Deck());
            GameController gameController = new GameController(gameService, deckService);

            ServerConfig serverConfig = new ServerConfig(gameController);
            serverConfig.start();
        } catch (IOException e) {
            System.err.println("Failed to start the server: " + e.getMessage());
        }
    }
}
