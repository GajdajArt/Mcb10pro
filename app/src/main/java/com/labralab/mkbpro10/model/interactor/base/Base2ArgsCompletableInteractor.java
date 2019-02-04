package com.labralab.mkbpro10.model.interactor.base;

import io.reactivex.Completable;

public interface Base2ArgsCompletableInteractor<Param1, Param2> {

    Completable getCompletable(Param1 params, Param2 param2);
}
