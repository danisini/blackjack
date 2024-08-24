package service.impl;

import model.Card;
import model.Deck;
import service.DeckService;

import java.util.Collections;
import java.util.List;

public class DeckServiceImpl implements DeckService {
    private Deck deck;

    public DeckServiceImpl(Deck deck) {
        this.deck = deck;
    }

    @Override
    public void shuffleDeck() {
        deck.shuffle();
    }

    @Override
    public Card drawCard() {
        return deck.dealCard();
    }

    @Override
    public Deck getDeck() {
        return deck;
    }
}
