package request;

import util.GameState;

public abstract class BaseRequest {
    private GameState state;

    public GameState getState() {
        return state;
    }

    public void setState(GameState state) {
        this.state = state;
    }
}
