package com.labralab.mkbpro10.model.interactor.base;

import io.reactivex.Observable;

@SuppressWarnings("SpellCheckingInspection")
public abstract class BaseInteractor<Result, Params> {

    protected BaseInteractor() {
    }

    protected abstract Observable<Result> buildObservable(Params params);

    public Observable<Result> getObservable(Params params) {
        return buildObservable(params);
    }
}
