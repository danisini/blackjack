package service.impl;

import action.*;
import request.*;
import response.*;
import service.GameService;
import validation.Validator;
import validation.impl.ValidatorImpl;

import static util.CommonConstants.*;

public class GameServiceImpl implements GameService {

    private Validator validator = new ValidatorImpl();
    @Override
    public BaseResponse startNewGame(StartRequest request) {
        validator.hasEnoughBalance(request, request.getStake());
        validator.isActionValid(request, START);

        return new StartGameAction().doAction(request);
    }

    @Override
    public BaseResponse hit(HitRequest request) {
        validator.isActionValid(request, HIT);
        return new HitAction().doAction(request);
    }

    @Override
    public BaseResponse stand(StandRequest request) {
        Validator validator = new ValidatorImpl();
        validator.isActionValid(request, STAND);

        return new StandAction().doAction(request);
    }

    @Override
    public BaseResponse doubleStake(DoubleRequest request) {
        validator.isActionValid(request, DOUBLE);
        validator.hasEnoughBalance(request,
                (request.getState().getStake() + request.getState().getAdditionalStake()) * TWO);

        return new DoubleAction().doAction(request);
    }

    @Override
    public BaseResponse split(SplitRequest request) {
        validator.isActionValid(request, SPLIT);
        validator.hasEnoughBalance(request, request.getState().getStake() + request.getAdditionalStake());

        return new SplitAction().doAction(request);
    }
}
