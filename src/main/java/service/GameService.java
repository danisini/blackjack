package service;

import request.*;
import response.*;

public interface GameService {
    BaseResponse startNewGame(StartRequest request);
    BaseResponse hit(HitRequest request);
    BaseResponse stand(StandRequest request);
    BaseResponse doubleStake(DoubleRequest request);
    BaseResponse split(SplitRequest request);
}
