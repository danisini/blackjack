package service;

import com.fasterxml.jackson.databind.ObjectMapper;
import util.GameState;

public class GameStateService {
    private ObjectMapper objectMapper = new ObjectMapper();

    public String serializeGameState(GameState state) throws Exception {
        return objectMapper.writeValueAsString(state);
    }

    public GameState deserializeGameState(String stateAsJson) throws Exception {
        return objectMapper.readValue(stateAsJson, GameState.class);
    }
}
