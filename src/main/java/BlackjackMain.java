
import config.ServerConfig;
import controller.GameController;
import model.Deck;
import service.DeckService;
import service.GameService;
import service.impl.DeckServiceImpl;
import service.impl.GameServiceImpl;

import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BlackjackMain {
    private static final Logger logger = LoggerFactory.getLogger(BlackjackMain.class);

    public static void main(String[] args) {
        try {
            GameService gameService = new GameServiceImpl();
            GameController gameController = new GameController(gameService);

            ServerConfig serverConfig = new ServerConfig(gameController);
            serverConfig.start();
        } catch (IOException e) {
            logger.info("Failed to start the server: {}", e.getMessage());
        }
    }
}
