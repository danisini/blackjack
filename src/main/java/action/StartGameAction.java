package action;

import model.Card;
import model.Deck;
import request.StartRequest;
import response.BaseResponse;;
import responseBuilder.ResponseBuilder;
import service.DeckService;
import service.impl.DeckServiceImpl;
import util.GameState;

import java.util.List;

public class StartGameAction extends BaseAction<BaseResponse, StartRequest> {

    @Override
    public BaseResponse doAction(StartRequest request) {
        GameState state = new GameState();
        state.setBalance(request.getBalance());
        state.setStake(request.getStake());

        DeckService deckService = new DeckServiceImpl(new Deck());

        Card firstPlayerCard = deckService.drawCard();
        Card secondPlayerCard = deckService.drawCard();

        state.getPlayerHand().add(firstPlayerCard);
        state.getPlayerHand().add(secondPlayerCard);

        Card firstDealerCard = deckService.drawCard();
        Card secondDealerCard = deckService.drawCard();

        state.getDealerHand().add(firstDealerCard);
        state.getDealerHand().add(secondDealerCard);
        System.out.println("HERE");
        System.out.println(state);
        ResponseBuilder response = new ResponseBuilder();
        return response.buildResponse(state);
    }
}
