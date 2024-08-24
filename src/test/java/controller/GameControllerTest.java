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
        String json = "{ \"state\": { \"hasPlayerWon\": false, \"hasDealerWon\": false, \"isRoundOver\": false, \"isStakeDoubled\": false, \"stake\": 0.0, \"additionalStake\": 0.0, \"winAmount\": 0.0, \"balance\": 100.0, \"playerHand\": [], \"playerSplitHand\": [], \"dealerHand\": [] }, \"balance\": 100.0, \"stake\": 1.0 }";

        ObjectMapper objectMapper = new ObjectMapper();
        StartRequest startRequest = objectMapper.readValue(json, StartRequest.class);

        System.out.println(startRequest.getState().getBalance());
    }


}
