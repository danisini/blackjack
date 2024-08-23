package controller;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import service.DeckService;
import service.GameService;

import java.io.IOException;
import java.io.OutputStream;
import java.util.function.Supplier;

import static util.CommonConstants.*;

public class GameController {
    private final GameService gameService;
    private final DeckService deckService;

    public GameController(GameService gameService, DeckService deckService) {
        this.gameService = gameService;
        this.deckService = deckService;
    }

    public HttpHandler start() {
        return exchange -> handle(exchange, GET, () -> {
            //gameService.startNewGame();
            return "New game started!";
        });
    }

    public HttpHandler hit() {
        return exchange -> handle(exchange, POST, () -> {
            //gameService.playerHit();
            return "Hit!";
        });
    }

    public HttpHandler stand() {
        return exchange -> handle(exchange, POST, () -> {
            //gameService.stand();
            return "standed!";
        });
    }

    private void handle(HttpExchange exchange, String method, Supplier<String> action) throws IOException {
        if (method.equals(exchange.getRequestMethod())) {
            String response = action.get();
            sendResponse(exchange, response, 200);
        } else {
            sendResponse(exchange, "Method Not Allowed", 405);
        }
    }

    private void sendResponse(HttpExchange exchange, String response, int statusCode) throws IOException {
        exchange.sendResponseHeaders(statusCode, response.length());
        try (OutputStream os = exchange.getResponseBody()) {
            os.write(response.getBytes());
        }
    }

}
