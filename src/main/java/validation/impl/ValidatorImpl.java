package validation.impl;

import request.BaseRequest;
import util.GameState;
import validation.Validator;

import java.util.List;

public class ValidatorImpl implements Validator {
    public Boolean hasEnoughBalance(BaseRequest request, Double stake) {
        return request.getState().getBalance() >= stake;
    }

    public Boolean isActionValid(BaseRequest request, String action) {
        List<String> possibleActions = request.getState().getPossibleActions();
        return possibleActions.contains(action);
    }
}
