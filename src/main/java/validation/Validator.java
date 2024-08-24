package validation;

import request.BaseRequest;

public interface Validator {
    public Boolean hasEnoughBalance(BaseRequest request, Double stake);

}
