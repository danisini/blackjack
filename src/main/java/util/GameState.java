package util;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import model.Deck;
import model.Card;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GameState {
    private Boolean hasPlayerWon;
    private Boolean hasDealerWon;
    private Boolean isRoundOver;
    private Boolean isStakeDoubled;

    private Double stake;
    private Double additionalStake;
    private Double winAmount;
    private Double balance;

    private List<String> possibleActions;
    private List<Card> playerHand;
    private List<Card> playerSplitHand;
    private List<Card> dealerHand;
    private List<Card> cardsDealt;

    public GameState() {
        playerHand = new ArrayList<>();
        playerSplitHand = new ArrayList<>();
        dealerHand = new ArrayList<>();
        cardsDealt = new ArrayList<>();
        hasPlayerWon = false;
        hasDealerWon = false;
        isRoundOver = false;
        isStakeDoubled = false;
        additionalStake = 0.0;
        winAmount = 0.0;

    }

    public List<String> getPossibleActions() {
        return possibleActions;
    }

    public void setPossibleActions(List<String> possibleActions) {
        this.possibleActions = possibleActions;
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

    public Boolean getStakeDoubled() {
        return isStakeDoubled;
    }

    public void setStakeDoubled(Boolean stakeDoubled) {
        isStakeDoubled = stakeDoubled;
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

    public Double getWinAmount() {
        return winAmount;
    }

    public void setWinAmount(Double winAmount) {
        this.winAmount = winAmount;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
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

    public List<Card> getCardsDealt() {
        return cardsDealt;
    }

    public void setCardsDealt(List<Card> cardsDealt) {
        this.cardsDealt = cardsDealt;
    }

    @Override
    public String toString() {
        return "GameState{" +
                ", hasPlayerWon=" + hasPlayerWon +
                ", hasDealerWon=" + hasDealerWon +
                ", isRoundOver=" + isRoundOver +
                ", isStakeDoubled=" + isStakeDoubled +
                ", stake=" + stake +
                ", additionalStake=" + additionalStake +
                ", winAmount=" + winAmount +
                ", balance=" + balance +
                ", playerHand=" + playerHand +
                ", playerSplitHand=" + playerSplitHand +
                ", dealerHand=" + dealerHand +
                ", possibleActions=" + possibleActions +
                ", cardsDealt=" + cardsDealt +
                '}';
    }
}
