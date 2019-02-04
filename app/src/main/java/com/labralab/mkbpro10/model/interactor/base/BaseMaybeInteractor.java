package com.labralab.mkbpro10.model.interactor.base;

import io.reactivex.Maybe;

@SuppressWarnings("SpellCheckingInspection")
public abstract class BaseMaybeInteractor<Result, Params> {

    protected BaseMaybeInteractor() {
    }

    protected abstract Maybe<Result> buildMaybe(Params params);

    public Maybe<Result> getMaybe(Params params) {
        return buildMaybe(params);
    }
}
