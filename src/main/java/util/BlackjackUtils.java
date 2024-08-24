package util;

import model.Card;
import model.Rank;
import java.util.List;
import java.util.Objects;

import static model.Rank.*;
import static util.CommonConstants.*;

public class BlackjackUtils {
    public static int calculateHandValue(List<Card> hand) {
        int value = 0;
        int numberOfAces = 0;

        for (Card card : hand) {
            Rank rank = card.getRank();
            if (rank.equals(ACE)) {
                numberOfAces++;
            }
            value += rank.getValue();
        }
        System.out.println("CURRENT RANK: " + value);

        while (value > MAX_NUMBER_OF_POINTS && numberOfAces > 0) {
            value -= 10;
            numberOfAces--;
        }

        return value;
    }

    public static boolean isParticipantBusted(List<Card> hand) {
        return calculateHandValue(hand) > MAX_NUMBER_OF_POINTS;
    }

    public static boolean hasPlayerWon(List<Card> playerHand, List<Card> dealerHand) {
        int playerValue = calculateHandValue(playerHand);
        int dealerValue = calculateHandValue(dealerHand);

        if (isParticipantBusted(playerHand)) {
            return false;
        }

        if (isParticipantBusted(dealerHand)) {
            return true;
        }

        if (playerValue > dealerValue) {
            return true;
        }

        if (playerValue == 21 && dealerValue != 21) {
            return true;
        }

        return false;
    }

    public static boolean hasDealerWon(List<Card> playerHand, List<Card> dealerHand) {
        int playerValue = calculateHandValue(playerHand);
        int dealerValue = calculateHandValue(dealerHand);
        return !hasPlayerWon(playerHand, dealerHand) && playerValue != dealerValue;
    }
}
