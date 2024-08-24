package controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import request.*;
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
        return createHandler(GET, StartRequest.class, gameService::startNewGame, "Game started!");
    }

    public HttpHandler hit() {
        return createHandler(POST, HitRequest.class, gameService::hit, "Player hit!");
    }

    public HttpHandler stand() {
        return createHandler(POST, StandRequest.class, gameService::stand, "Player stands!");
    }

    public HttpHandler doubleStake() {
        return createHandler(POST, DoubleRequest.class, gameService::doubleStake, "Player doubled");
    }

    public HttpHandler split() {
        return createHandler(POST, SplitRequest.class, gameService::split, "Player split");
    }

    private <T> HttpHandler createHandler(String requestType, Class<T> requestClass, RequestHandler<T> serviceMethod,
                                          String successMessage) {
        return exchange -> handle(exchange, requestType, () -> {
            T request = parseRequest(exchange, requestClass);
            serviceMethod.execute(request);
            return successMessage;
        });
    }

    private <T> T parseRequest(HttpExchange exchange, Class<T> requestClass) {
        try {
            return objectMapper.readValue(exchange.getRequestBody(), requestClass);
        } catch (IOException e) {
            throw new RuntimeException("Failed to parse " + requestClass.getSimpleName(), e);
        }
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
