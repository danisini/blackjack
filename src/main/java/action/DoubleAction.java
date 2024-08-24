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
        List<Card> dealerHand = state.getDealerHand();

        Card drawnCard;
        do {
            drawnCard = deckService.drawCard();
        } while (playerHand.contains(drawnCard) || dealerHand.contains(drawnCard));

        playerHand.add(drawnCard);
        state.setPlayerHand(playerHand);

        StandRequest standRequest = new StandRequest();
        standRequest.setState(state);

        return new StandAction().doAction(standRequest);
    }
}
