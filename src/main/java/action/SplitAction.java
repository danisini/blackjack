package action;

import model.Card;
import request.SplitRequest;
import response.BaseResponse;
import responseBuilder.impl.ResponseBuilderImpl;
import util.GameState;

import java.util.ArrayList;
import java.util.List;

import static util.CommonConstants.*;

public class SplitAction extends BaseAction<BaseResponse, SplitRequest> {
    @Override
    public BaseResponse doAction(SplitRequest request) {
        GameState state = request.getState();
        state.setAdditionalStake(request.getAdditionalStake());

        List<Card> playerHand = state.getPlayerHand();
        List<Card> splitHand = new ArrayList<>();
        splitHand.add(playerHand.remove(SECOND));

        state.setPlayerHand(playerHand);
        state.setPlayerSplitHand(splitHand);

        return new ResponseBuilderImpl().buildResponse(state);
    }
}
