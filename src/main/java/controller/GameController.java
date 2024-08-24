package controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import request.*;
import service.GameService;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.function.Supplier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static util.CommonConstants.*;

public class GameController {
    private static final Logger logger = LoggerFactory.getLogger(GameController.class);
    private final GameService gameService;
    private ObjectMapper objectMapper;

    public GameController(GameService gameService) {
        this.gameService = gameService;
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
                logger.info("Couldn't read request body for :{}", e.getMessage());
                throw new RuntimeException(e);
            }
            T request = parseRequest(stringRequest, requestClass);
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
            logger.info("Couldn't parse request: {}", e.getMessage());
            throw new RuntimeException("Failed to parse " + requestClass.getSimpleName(), e);
        }
    }

    private void handle(HttpExchange exchange, String method, Supplier<String> action) throws IOException {
        if (method.equals(exchange.getRequestMethod())) {
            String response = action.get();
            sendResponse(exchange, response, 200);
        } else {
            logger.info("Method not allowed: {}", method);
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
