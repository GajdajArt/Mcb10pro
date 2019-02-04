package com.labralab.mkbpro10.model.interactor.base;

import io.reactivex.Maybe;

public abstract class Base2ArgsMaybeInteractor<T1, T2, R> {

    public Maybe<R> getMaybe(final T1 t1, final T2 t2) {
        return buildMaybe(t1, t2);
    }

    protected abstract Maybe<R> buildMaybe(final T1 t1, final T2 t2);
}
