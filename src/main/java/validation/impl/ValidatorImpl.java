package validation.impl;

import request.BaseRequest;
import validation.Validator;

public class ValidatorImpl implements Validator {
    public Boolean hasEnoughBalance(BaseRequest request, Double stake) {
        return request.getState().getBalance() >= stake;
    }
}
