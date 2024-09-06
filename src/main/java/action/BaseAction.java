package action;

import model.Deck;
import service.DeckService;
import service.impl.DeckServiceImpl;

public abstract class BaseAction<T,P> {
    DeckService deckService = new DeckServiceImpl(new Deck());
    public abstract T doAction(P request);
}
