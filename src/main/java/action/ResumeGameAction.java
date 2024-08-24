package action;

import request.StartRequest;
import response.BaseResponse;

public class ResumeGameAction extends BaseAction<BaseResponse, StartRequest> {

    @Override
    public BaseResponse doAction(StartRequest request) {
        request.getState().setStake(request.getStake());
        System.out.println(request);
        return new BaseResponse("Resumed successfully", request.getState());
    }
}
