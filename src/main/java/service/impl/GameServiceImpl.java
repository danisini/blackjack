package service.impl;

import action.BaseAction;
import action.ResumeGameAction;
import action.StartGameAction;
import request.*;
import response.*;
import service.GameService;
import util.GameState;
import validation.Validator;
import validation.impl.ValidatorImpl;

import static util.CommonConstants.*;

public class GameServiceImpl implements GameService {

    private Validator validator = new ValidatorImpl();
    @Override
    public BaseResponse startNewGame(StartRequest request) {
        validator.hasEnoughBalance(request, request.getStake());
        validator.isActionValid(request, START);

        GameState state = request.getState();
        BaseAction<BaseResponse, StartRequest> action;

        action = !state.getPlayerHand().isEmpty() ?
                new ResumeGameAction() : new StartGameAction();

        return action.doAction(request);
    }

    @Override
    public BaseResponse hit(HitRequest request) {
        validator.isActionValid(request, HIT);

        return null;
    }

    @Override
    public BaseResponse stand(StandRequest request) {
        Validator validator = new ValidatorImpl();
        validator.isActionValid(request, STAND);
        return null;
    }

    @Override
    public BaseResponse doubleStake(DoubleRequest request) {
        validator.isActionValid(request, DOUBLE);
        validator.hasEnoughBalance(request,
                (request.getState().getStake() + request.getState().getAdditionalStake()) * TWO);
        return null;
    }

    @Override
    public BaseResponse split(SplitRequest request) {
        validator.isActionValid(request, SPLIT);
        validator.hasEnoughBalance(request, request.getState().getStake() + request.getAdditionalStake());
        return null;
    }


}
