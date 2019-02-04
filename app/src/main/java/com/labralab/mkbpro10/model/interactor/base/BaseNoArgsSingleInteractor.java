package com.labralab.mkbpro10.model.interactor.base;

import io.reactivex.Single;

@SuppressWarnings("SpellCheckingInspection")
public interface BaseNoArgsSingleInteractor<Result> {

    Single<Result> getSingle();
}