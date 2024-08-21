package model;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static util.CommonConstants.*;

public class Deck {
    private final List<Card> cards;

    public Deck() {
        this.cards = new ArrayList<>();
        initializeDeck();
    }

    private void initializeDeck() {

        for (Suit suit : Suit.values()) {
            for (Rank rank : Rank.values()) {

                cards.add(new Card(suit, rank));
            }
        }
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public Card dealCard() {
        if (isDeckEmpty()) {
            throw new IllegalStateException(CANNOT_DEAL_FROM_AN_EMPTY_DECK);
        }

        return cards.remove(0);
    }

    public int getRemainingCards() {
        return cards.size();
    }

    public void resetDeck() {
        cards.clear();
        initializeDeck();
        shuffle();
    }

    private boolean isDeckEmpty() {
        return cards.isEmpty();
    }
}