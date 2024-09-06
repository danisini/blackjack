package responseBuilder;

import response.BaseResponse;
import util.GameState;

public interface ResponseBuilder {
    public BaseResponse buildResponse(GameState state);
}
