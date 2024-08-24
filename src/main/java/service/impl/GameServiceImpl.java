package service.impl;

import action.BaseAction;
import action.ResumeGameAction;
import action.StartGameAction;
import request.*;
import response.*;
import service.GameService;
import util.GameState;

import static util.CommonConstants.*;

public class GameServiceImpl implements GameService {

    @Override
    public BaseResponse startNewGame(StartRequest request) {
        GameState state = request.getState();
        BaseAction<BaseResponse, StartRequest> action;

        action = !state.getPlayerHand().isEmpty() ?
                new ResumeGameAction() : new StartGameAction();

        return action.doAction(request);
    }

    @Override
    public BaseResponse hit(HitRequest request) {
        return null;
    }

    @Override
    public BaseResponse stand(StandRequest request) {
        return null;
    }

    @Override
    public BaseResponse doubleStake(DoubleRequest request) {
        return null;
    }

    @Override
    public BaseResponse split(SplitRequest request) {
        return null;
    }


}
