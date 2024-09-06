package action;

import model.Card;
import request.DoubleRequest;
import request.StandRequest;
import response.BaseResponse;
import util.GameState;

import java.util.List;

public class DoubleAction extends BaseAction <BaseResponse, DoubleRequest> {
    @Override
    public BaseResponse doAction(DoubleRequest request) {
        GameState state = request.getState();
        state.setStakeDoubled(Boolean.TRUE);

        List<Card> playerHand = state.getPlayerHand();
        List<Card> cardsDealt = state.getCardsDealt();

        Card drawnCard;
        do {
            drawnCard = deckService.drawCard();
        } while (cardsDealt.contains(drawnCard));

        state.getCardsDealt().add(drawnCard);
        state.getPlayerHand().add(drawnCard);

        StandRequest standRequest = new StandRequest();
        standRequest.setState(state);

        return new StandAction().doAction(standRequest);
    }
}
