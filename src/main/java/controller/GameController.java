package controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import request.*;
import service.DeckService;
import service.GameService;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.function.Supplier;

import static util.CommonConstants.*;

public class GameController {
    private final GameService gameService;
    private final DeckService deckService;
    private ObjectMapper objectMapper;

    public GameController(GameService gameService, DeckService deckService) {
        this.gameService = gameService;
        this.deckService = deckService;
        objectMapper = new ObjectMapper();
    }

    public HttpHandler start() {
        return createHandler(StartRequest.class, gameService::startNewGame);
    }

    public HttpHandler hit() {
        return createHandler(HitRequest.class, gameService::hit);
    }

    public HttpHandler stand() {
        return createHandler(StandRequest.class, gameService::stand);
    }

    public HttpHandler doubleStake() {
        return createHandler(DoubleRequest.class, gameService::doubleStake);
    }

    public HttpHandler split() {
        return createHandler(SplitRequest.class, gameService::split);
    }

    private <T> HttpHandler createHandler(Class<T> requestClass, RequestHandler<T> serviceMethod) {
        return exchange -> handle(exchange, POST, () -> {
            String stringRequest;
            try {
                stringRequest = readRequestBody(exchange.getRequestBody());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            System.out.println(stringRequest);
            T request = parseRequest(stringRequest, requestClass);

            System.out.println("CONTROLER 58");
            System.out.println(request.toString());
            return String.valueOf(serviceMethod.execute(request));
        });
    }

    private String readRequestBody(InputStream inputStream) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line).append("\n");
            }
            return sb.toString().trim(); // Return as a single string
        }
    }

    private <T> T parseRequest(String request, Class<T> requestClass) {
        try {
            return objectMapper.readValue(request, requestClass);
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
