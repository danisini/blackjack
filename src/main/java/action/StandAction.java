package action;

import model.Card;
import request.StandRequest;
import response.BaseResponse;
import responseBuilder.impl.ResponseBuilderImpl;
import util.BlackjackUtils;
import util.GameState;

import java.util.List;

import static util.CommonConstants.DEALER_STANDING_POINTS;

public class StandAction extends BaseAction<BaseResponse, StandRequest> {
    @Override
    public BaseResponse doAction(StandRequest request) {
        GameState state = request.getState();
        List<Card> dealerHand = state.getDealerHand();
        List<Card> cardsDealt = state.getCardsDealt();

        while (BlackjackUtils.calculateHandValue(dealerHand) <= DEALER_STANDING_POINTS) {
            Card drawnCard;
            do {
                drawnCard = deckService.drawCard();
            } while (cardsDealt.contains(drawnCard));

            cardsDealt.add(drawnCard);
            dealerHand.add(drawnCard);
        }

        state.setDealerHand(dealerHand);
        state.setCardsDealt(cardsDealt);
        state.setRoundOver(Boolean.TRUE);

        return new ResponseBuilderImpl().buildResponse(state);
    }
}
