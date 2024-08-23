
import config.ConfigLoader;
import config.ServerConfig;
import controller.GameController;
import service.DeckService;
import service.GameService;
import java.io.IOException;

public class BlackjackMain {

    //TODO: logger instead of system.err
    public static void main(String[] args) {
        try {
            ConfigLoader config = new ConfigLoader();
            GameService gameService = new GameService();
            DeckService deckService = new DeckService();
            GameController gameController = new GameController(gameService, deckService);

            ServerConfig serverConfig = new ServerConfig(gameController, config);
            serverConfig.start();
        } catch (IOException e) {
            System.err.println("Failed to start the server: " + e.getMessage());
        }
    }
}
