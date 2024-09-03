package action;

import model.Card;
import request.HitRequest;
import response.BaseResponse;
import responseBuilder.impl.ResponseBuilderImpl;
import util.GameState;

import java.util.List;

import static util.CommonConstants.FIRST;

public class HitAction extends BaseAction<BaseResponse, HitRequest> {
    @Override
    public BaseResponse doAction(HitRequest request) {
        GameState state = request.getState();
        List<Card> cardsDealt = state.getCardsDealt();

        Card drawnCard;
        do {
            drawnCard = deckService.drawCard();
        } while (cardsDealt.contains(drawnCard));

        if (request.getHandNumber() == FIRST) {
            state.getPlayerHand().add(drawnCard);
        } else {
            state.getPlayerSplitHand().add(drawnCard);
        }
        state.getCardsDealt().add(drawnCard);

        return new ResponseBuilderImpl().buildResponse(state);
    }
}
