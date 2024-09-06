package validation;

import request.BaseRequest;

public interface Validator {
    public Boolean hasEnoughBalance(BaseRequest request, Double stake);
    public Boolean isActionValid(BaseRequest request, String action);

}
