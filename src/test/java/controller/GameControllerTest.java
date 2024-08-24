package controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import request.StartRequest;
import service.DeckService;
import service.GameService;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static org.junit.Assert.assertEquals;

public class GameControllerTest {

    @Test
    public void testDeserialization() throws IOException {
        String json = "{\n" +
                "  \"state\": {\n" +
                "    \"hasPlayerWon\": false,\n" +
                "    \"hasDealerWon\": false,\n" +
                "    \"isRoundOver\": false,\n" +
                "    \"isStakeDoubled\": true,\n" +
                "    \"stake\": 50,\n" +
                "    \"additionalStake\": 50,\n" +
                "    \"winAmount\": 0,\n" +
                "    \"balance\": 900,\n" +
                "    \"playerHand\": [\n" +
                "      {\"suit\": \"Hearts\", \"rank\": \"10\"},\n" +
                "      {\"suit\": \"Spades\", \"rank\": \"7\"}\n" +
                "    ],\n" +
                "    \"playerSplitHand\": [],\n" +
                "    \"dealerHand\": [\n" +
                "      {\"suit\": \"Diamonds\", \"rank\": \"5\"}\n" +
                "    ]\n" +
                "  },\n" +
                "  \"balance\": 100.0,\n" +
                "  \"stake\": 1.0\n" +
                "}";
        ObjectMapper objectMapper = new ObjectMapper();
        StartRequest startRequest = objectMapper.readValue(json, StartRequest.class);

        System.out.println(startRequest.getState().getBalance());
    }


}
