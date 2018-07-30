package jotaemepereira.com.upstacktechinical.base

interface BasePresenter<in T: BaseView> {

    fun attachView(mvpView: T)
    fun detachView()
    fun onError(throwable: Throwable)
}