package action;

import model.Card;
import request.HitRequest;
import response.BaseResponse;
import responseBuilder.ResponseBuilder;
import util.GameState;

import java.util.List;

public class HitAction extends BaseAction<BaseResponse, HitRequest> {
    @Override
    public BaseResponse doAction(HitRequest request) {
        System.out.println("HIT ACTION 14");
        GameState state = request.getState();
        List<Card> playerHand = state.getPlayerHand();
        List<Card> dealerHand = state.getDealerHand();

        Card drawnCard;
        do {
            drawnCard = deckService.drawCard();
        } while (playerHand.contains(drawnCard) || dealerHand.contains(drawnCard));

        state.getPlayerHand().add(drawnCard);

        return new ResponseBuilder().buildResponse(state);
    }
}
