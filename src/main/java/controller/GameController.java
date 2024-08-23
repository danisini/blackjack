package controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import request.HitRequest;
import service.DeckService;
import service.GameService;

import java.io.IOException;
import java.io.OutputStream;
import java.util.function.Supplier;

import static util.CommonConstants.*;

public class GameController {
    private final GameService gameService;
    private final DeckService deckService;
    private ObjectMapper objectMapper;

    public GameController(GameService gameService, DeckService deckService) {
        this.gameService = gameService;
        this.deckService = deckService;
    }

    public HttpHandler start() {
        return exchange -> handle(exchange, GET, () -> {
            //StartRequest startRequest = objectMapper.readValue(exchange.getRequestBody(), StartRequest.class);
            //gameService.startNewGame();
            return "New game started!";
        });
    }

    public HttpHandler hit() {
        return exchange -> handle(exchange, POST, () -> {
            //HitRequest hitRequest = objectMapper.readValue(exchange.getRequestBody(), HitRequest.class);

            //gameService.playerHit(hitRequest);
            return "Hit!";
        });
    }

    public HttpHandler stand() {
        return exchange -> handle(exchange, POST, () -> {
            //StandRequest standRequest = objectMapper.readValue(exchange.getRequestBody(), StandRequest.class);
            //gameService.stand();
            return "standed!";
        });
    }

    public HttpHandler doubleStake() {
        return exchange -> handle(exchange, POST, () -> {
            //DoubleRequest doubleRequest = objectMapper.readValue(exchange.getRequestBody(), DoubleRequest.class);
            //gameService.doubleRequest();
            return "standed!";
        });
    }

    public HttpHandler split() {
        return exchange -> handle(exchange, POST, () -> {
            //SplitRequest SplitRequest = objectMapper.readValue(exchange.getRequestBody(), SplitRequest.class);
            //gameService.split();
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
