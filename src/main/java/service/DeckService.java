package service;

import model.Card;
import model.Deck;

public interface DeckService {
    void shuffleDeck();
    Card drawCard();
    Deck getDeck();
}
