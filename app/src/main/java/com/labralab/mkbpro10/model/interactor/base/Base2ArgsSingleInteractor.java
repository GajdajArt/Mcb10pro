package com.labralab.mkbpro10.model.interactor.base;

import io.reactivex.Single;

@SuppressWarnings("SpellCheckingInspection")
public interface Base2ArgsSingleInteractor<Param1, Param2, Result> {

    Single<Result> getSingle(Param1 params, Param2 param2);
}