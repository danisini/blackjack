package responseBuilder;

import response.BaseResponse;
import util.GameState;

public  class ResponseBuilder {
    public BaseResponse buildResponse(GameState state) {
        return new BaseResponse("OK", state);
    }
}
