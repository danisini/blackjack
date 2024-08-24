package action;

import model.Card;
import request.StandRequest;
import response.BaseResponse;
import responseBuilder.ResponseBuilder;
import util.BlackjackUtils;
import util.GameState;

import java.util.List;

import static util.CommonConstants.DEALER_STANDING_POINTS;

public class StandAction extends BaseAction<BaseResponse, StandRequest> {
    @Override
    public BaseResponse doAction(StandRequest request) {
        GameState state = request.getState();
        List<Card> playerHand = state.getPlayerHand();
        List<Card> dealerHand = state.getDealerHand();

        while (BlackjackUtils.calculateHandValue(dealerHand) >= DEALER_STANDING_POINTS) {
            Card drawnCard;
            do {
                drawnCard = deckService.drawCard();
            } while (playerHand.contains(drawnCard) || dealerHand.contains(drawnCard));

            dealerHand.add(drawnCard);
        }
        state.setDealerHand(dealerHand);

        return new ResponseBuilder().buildResponse(state);
    }
}
