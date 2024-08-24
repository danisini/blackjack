package responseBuilder;

import model.Card;
import response.BaseResponse;
import util.BlackjackUtils.*;
import util.GameState;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

import static util.BlackjackUtils.*;
import static util.CommonConstants.*;

public  class ResponseBuilder {
    public BaseResponse buildResponse(GameState state) {
        updateState(state);
        return new BaseResponse("OK", state);
    }

    private void updateState(GameState state) {
        System.out.println("HERE RESP BUILDER 21");
        List<Card> playerHand = state.getPlayerHand();
        List<Card> dealerHand = state.getDealerHand();
        List<String> possibleActions = new ArrayList<>();

        if (canGameContinue(playerHand, dealerHand)) {
            if (isSplitPossible(playerHand))
                possibleActions.add(SPLIT);

            possibleActions.add(DOUBLE);
            possibleActions.add(STAND);
            possibleActions.add(HIT);
        } else {
            possibleActions.add(START);
            Boolean hasPlayerWon = hasPlayerWon(playerHand, dealerHand);
            Boolean hasDealerWon = hasDealerWon(playerHand, dealerHand);

            state.setHasPlayerWon(hasPlayerWon);
            state.setHasDealerWon(hasDealerWon);
            state.setRoundOver(hasDealerWon || hasPlayerWon);

            if (hasPlayerWon) {
                state.setWinAmount(state.getStake() + state.getAdditionalStake());
                if (state.getStakeDoubled())
                    state.setWinAmount(state.getWinAmount() * TWO);

                state.setBalance(state.getBalance() + state.getWinAmount());
            } else if (hasDealerWon) {
                state.setBalance(state.getBalance() - (state.getStake() + state.getAdditionalStake()));
            }

            state.setStake(ZERO_STAKE);
            state.setAdditionalStake(ZERO_STAKE);
        }

        state.setPossibleActions(possibleActions);
    }

    private Boolean isSplitPossible(List<Card> playerHand) {
        return playerHand.size() == TWO && playerHand.get(FIRST).getRank().equals(playerHand.get(SECOND).getRank());
    }

    private Boolean canGameContinue(List<Card> playerHand, List<Card> dealerHand) {
        return !isParticipantBusted(playerHand) && !isParticipantBusted(dealerHand);
    }

}
