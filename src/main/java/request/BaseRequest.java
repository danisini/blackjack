package request;

import com.fasterxml.jackson.annotation.JsonProperty;
import util.GameState;

public abstract class BaseRequest {
    @JsonProperty("state")
    private GameState state;

    public GameState getState() {
        return state;
    }

    public void setState(GameState state) {
        this.state = state;
    }
}
