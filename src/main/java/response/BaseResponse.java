package response;

import util.GameState;

public class BaseResponse {
    private String statusMessage;
    private GameState state;

    public BaseResponse(String message, GameState state) {
        this.statusMessage = message;
        this.state = state;

    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }

    public GameState getState() {
        return state;
    }

    public void setState(GameState state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "BaseResponse{" +
                "statusMessage='" + statusMessage + '\'' +
                ", state=" + state +
                '}';
    }
}
