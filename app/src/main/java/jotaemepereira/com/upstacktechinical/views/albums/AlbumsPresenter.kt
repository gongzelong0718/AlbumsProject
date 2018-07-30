package jotaemepereira.com.upstacktechinical.views.albums

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import jotaemepereira.com.upstacktechinical.base.BasePresenter
import jotaemepereira.com.upstacktechinical.model.Album
import jotaemepereira.com.upstacktechinical.model.DataManager
import javax.inject.Inject

class AlbumsPresenter @Inject constructor(val dataManager: DataManager): BasePresenter<AlbumsView> {

    private lateinit var view: AlbumsView
    private var disposable: Disposable? = null

    fun loadAlbums() {
        view.showProgress(true)
        disposable = dataManager.getAlbums()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { result -> onSuccessGetAlbums(result) },
                        { error -> onError(error) }
                )
    }

    private fun onSuccessGetAlbums(result: List<Album>) {
        view.showProgress(false)
        view.onAlbumsLoaded(result)
    }

    override fun attachView(mvpView: AlbumsView) {
        view = mvpView
    }

    override fun detachView() {
        disposable?.dispose()
    }

    override fun onError(throwable: Throwable) {
        view.showProgress(false)
        view.onError(throwable.localizedMessage)
    }
}