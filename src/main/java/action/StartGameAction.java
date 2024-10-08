package action;

import model.Card;
import request.StartRequest;
import response.BaseResponse;
import responseBuilder.impl.ResponseBuilderImpl;
import util.GameState;

public class StartGameAction extends BaseAction<BaseResponse, StartRequest> {

    @Override
    public BaseResponse doAction(StartRequest request) {
        GameState state = new GameState();
        state.setBalance(request.getBalance());
        state.setStake(request.getStake());

        Card firstPlayerCard = deckService.drawCard();
        Card secondPlayerCard = deckService.drawCard();

        state.getPlayerHand().add(firstPlayerCard);
        state.getPlayerHand().add(secondPlayerCard);

        Card firstDealerCard = deckService.drawCard();
        state.getDealerHand().add(firstDealerCard);

        state.getCardsDealt().add(firstPlayerCard);
        state.getCardsDealt().add(secondPlayerCard);
        state.getCardsDealt().add(firstDealerCard);

        return new ResponseBuilderImpl().buildResponse(state);
    }
}
