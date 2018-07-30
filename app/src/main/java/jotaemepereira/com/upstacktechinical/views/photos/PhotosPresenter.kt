package jotaemepereira.com.upstacktechinical.views.photos

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import jotaemepereira.com.upstacktechinical.base.BasePresenter
import jotaemepereira.com.upstacktechinical.model.Album
import jotaemepereira.com.upstacktechinical.model.DataManager
import jotaemepereira.com.upstacktechinical.model.Photo
import jotaemepereira.com.upstacktechinical.views.albums.AlbumsView
import javax.inject.Inject

class PhotosPresenter @Inject constructor(val dataManager: DataManager): BasePresenter<PhotosView> {

    private lateinit var view: PhotosView
    private var disposable: Disposable? = null

    fun loadPhotos(albumId: Int) {
        view.showProgress(true)
        disposable = dataManager.getAlbumsPhotos(albumId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { result -> onSuccessGetPhotos(result) },
                        { error -> onError(error) }
                )
    }

    private fun onSuccessGetPhotos(result: List<Photo>) {
        view.showProgress(false)
        view.onPhotosLoaded(result)
    }

    override fun attachView(mvpView: PhotosView) {
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