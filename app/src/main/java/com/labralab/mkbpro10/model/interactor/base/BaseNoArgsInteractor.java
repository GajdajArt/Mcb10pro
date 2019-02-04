package com.labralab.mkbpro10.model.interactor.base;

import io.reactivex.Observable;

@SuppressWarnings("SpellCheckingInspection")
public abstract class BaseNoArgsInteractor<Result> {

    protected BaseNoArgsInteractor() {
    }

    protected abstract Observable<Result> buildObservable();

    public Observable<Result> getObservable() {
        return buildObservable();
    }
}
