package util;

import model.Deck;
import model.Card;

import java.util.List;

public class GameState {
    private Deck deck;

    private Boolean hasPlayerWon;
    private Boolean hasDealerWon;
    private Boolean isRoundOver;
    private Boolean isStakeDoubled;

    private Double stake;
    private Double additionalStake;
    private Double winAmount;

    private List<Card> playerHand;
    private List<Card> playerSplitHand;
    private List<Card> dealerHand;

    public GameState() {

    }
    
    public Deck getDeck() {
        return deck;
    }

    public void setDeck(Deck deck) {
        this.deck = deck;
    }

    public Boolean getHasPlayerWon() {
        return hasPlayerWon;
    }

    public void setHasPlayerWon(Boolean hasPlayerWon) {
        this.hasPlayerWon = hasPlayerWon;
    }

    public Boolean getHasDealerWon() {
        return hasDealerWon;
    }

    public void setHasDealerWon(Boolean hasDealerWon) {
        this.hasDealerWon = hasDealerWon;
    }

    public Boolean getRoundOver() {
        return isRoundOver;
    }

    public void setRoundOver(Boolean roundOver) {
        isRoundOver = roundOver;
    }

    public Double getStake() {
        return stake;
    }

    public void setStake(Double stake) {
        this.stake = stake;
    }

    public Double getAdditionalStake() {
        return additionalStake;
    }

    public void setAdditionalStake(Double additionalStake) {
        this.additionalStake = additionalStake;
    }

    public List<Card> getPlayerHand() {
        return playerHand;
    }

    public void setPlayerHand(List<Card> playerHand) {
        this.playerHand = playerHand;
    }

    public List<Card> getPlayerSplitHand() {
        return playerSplitHand;
    }

    public void setPlayerSplitHand(List<Card> playerSplitHand) {
        this.playerSplitHand = playerSplitHand;
    }

    public List<Card> getDealerHand() {
        return dealerHand;
    }

    public void setDealerHand(List<Card> dealerHand) {
        this.dealerHand = dealerHand;
    }

    public Boolean getStakeDoubled() {
        return isStakeDoubled;
    }

    public void setStakeDoubled(Boolean stakeDoubled) {
        isStakeDoubled = stakeDoubled;
    }

    @Override
    public String toString() {
        return "GameState{" +
                "deck=" + deck +
                ", hasPlayerWon=" + hasPlayerWon +
                ", hasDealerWon=" + hasDealerWon +
                ", isRoundOver=" + isRoundOver +
                ", stake=" + stake +
                ", additionalStake=" + additionalStake +
                ", playerHand=" + playerHand +
                ", playerSplitHand=" + playerSplitHand +
                ", dealerHand=" + dealerHand +
                '}';
    }
}
