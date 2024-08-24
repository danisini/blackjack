package action;

import model.Card;
import request.HitRequest;
import response.BaseResponse;
import responseBuilder.ResponseBuilder;
import util.GameState;

import java.util.List;

import static util.CommonConstants.FIRST;

public class HitAction extends BaseAction<BaseResponse, HitRequest> {
    @Override
    public BaseResponse doAction(HitRequest request) {
        GameState state = request.getState();
        List<Card> playerHand = state.getPlayerHand();
        List<Card> dealerHand = state.getDealerHand();
        List<Card> splitHand = state.getPlayerSplitHand();

        Card drawnCard;
        do {
            drawnCard = deckService.drawCard();
        } while (playerHand.contains(drawnCard) || dealerHand.contains(drawnCard) || splitHand.contains(drawnCard));

        if (request.getHandNumber() == FIRST) {
            state.getPlayerHand().add(drawnCard);
        } else {
            state.getPlayerSplitHand().add(drawnCard);
        }

        return new ResponseBuilder().buildResponse(state);
    }
}
