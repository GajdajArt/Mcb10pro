package com.labralab.mkbpro10.model.interactor.base;

import io.reactivex.Completable;

@SuppressWarnings("SpellCheckingInspection")
public abstract class BaseCompletableInteractor<Params> {

    protected BaseCompletableInteractor() {
    }

    protected abstract Completable buildCompletable(Params params);

    public Completable getCompletable(Params params) {
        return buildCompletable(params);
    }
}
