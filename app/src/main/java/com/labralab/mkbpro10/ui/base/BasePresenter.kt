package com.labralab.mkbpro10.ui.base

import io.reactivex.disposables.Disposable
import java.util.HashMap

open class BasePresenter<V: BaseContract.View>: BaseContract.Presenter<V> {

    protected var view: V? = null

    private val disposables = HashMap<Int, Disposable>()

    override fun bind(view: V) {
        this.view = view
        onBind()
    }

    override fun unbind() {
        disposeAll()
        onUnBind()
        view = null
    }

    open fun onBind() {}

    open fun onUnBind() {}

    protected fun processError(throwable: Throwable) {
        view?.hideProgressDialog()

        throwable.message?.let { view?.showMessage(it) }
    }

    protected fun subscribe(key: Int, disposable: Disposable) {
        val currentDisposable = disposables[key]
        if (currentDisposable != null && !currentDisposable.isDisposed) {
            currentDisposable.dispose()
        }
        disposables[key] = disposable
    }

    protected fun unsubscribe(key: Int) {
        val currentDisposable = disposables[key]
        if (currentDisposable != null && !currentDisposable.isDisposed) {
            currentDisposable.dispose()
        }
    }

    protected fun disposeAll() {
        for (key in disposables.keys) {
            val currentDisposable = disposables[key]
            if (currentDisposable != null && !currentDisposable.isDisposed) {
                currentDisposable.dispose()
            }
        }
    }
}
