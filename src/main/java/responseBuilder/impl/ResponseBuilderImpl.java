package responseBuilder.impl;

import model.Card;
import response.BaseResponse;
import responseBuilder.ResponseBuilder;
import util.GameState;

import java.util.ArrayList;
import java.util.List;

import static util.BlackjackUtils.*;
import static util.CommonConstants.*;

public  class ResponseBuilderImpl implements ResponseBuilder {
    public BaseResponse buildResponse(GameState state) {
        updateState(state);
        return new BaseResponse(OK, state);
    }

    private void updateState(GameState state) {
        List<Card> playerHand = state.getPlayerHand();
        List<Card> dealerHand = state.getDealerHand();
        List<Card> splitHand = state.getPlayerSplitHand();

        List<String> possibleActions = new ArrayList<>();

        if (canGameContinue(playerHand, dealerHand, state.getRoundOver())) {
            if (isSplitPossible(playerHand))
                possibleActions.add(SPLIT);

            possibleActions.add(DOUBLE);
            possibleActions.add(STAND);
            possibleActions.add(HIT);
        } else {
            possibleActions.add(START);
            Boolean hasFirstHandPlayerWon = hasPlayerWon(playerHand, dealerHand);
            Boolean hasDealerWon = hasDealerWon(playerHand, dealerHand);
            Boolean hasPlayerSpiltWon = hasPlayerWon(splitHand, dealerHand);
            Boolean hasPlayerWon = hasPlayerSpiltWon || hasPlayerSpiltWon;

            state.setHasPlayerWon(hasPlayerWon);
            state.setHasDealerWon(hasDealerWon);
            state.setRoundOver(hasDealerWon || hasPlayerWon);

            if (hasPlayerWon) {
                calculateWinAmount(state, hasFirstHandPlayerWon, hasPlayerSpiltWon);
            } else if (hasDealerWon) {
                calculateLostAmount(state);
            }

            state.setStake(ZERO_STAKE);
            state.setAdditionalStake(ZERO_STAKE);
        }

        state.setPossibleActions(possibleActions);
    }

    private static void calculateLostAmount(GameState state) {
        Double lostAmount = state.getStake() + state.getAdditionalStake();
        if (state.getStakeDoubled()) lostAmount *= TWO;

        state.setBalance(state.getBalance() - lostAmount);
    }

    private static void calculateWinAmount(GameState state, Boolean hasFirstHandPlayerWon, Boolean hasPlayerSpiltWon) {
        Double winAmount = 0.0;
        if (hasFirstHandPlayerWon) winAmount += state.getStake();
        if (hasPlayerSpiltWon) winAmount += state.getAdditionalStake();
        if (state.getStakeDoubled())
            winAmount *= TWO;

        state.setWinAmount(winAmount);
        state.setBalance(state.getBalance() + state.getWinAmount());
    }

    private Boolean isSplitPossible(List<Card> playerHand) {
        return playerHand.size() == TWO && playerHand.get(FIRST).getRank().equals(playerHand.get(SECOND).getRank());
    }

    private Boolean canGameContinue(List<Card> playerHand, List<Card> dealerHand, Boolean isRoundOver) {
        return !isRoundOver && !isParticipantBusted(playerHand) && !isParticipantBusted(dealerHand);
    }

}
