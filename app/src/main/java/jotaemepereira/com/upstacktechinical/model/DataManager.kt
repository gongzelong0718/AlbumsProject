package jotaemepereira.com.upstacktechinical.model

import io.reactivex.Observable
import jotaemepereira.com.upstacktechinical.network.AlbumsAPI
import javax.inject.Inject

open class DataManager @Inject constructor(val api: AlbumsAPI) {

    fun getAlbums(): Observable<List<Album>> {
        return api.getAlbums()
    }

    fun getAlbumsPhotos(albumId: Int): Observable<List<Photo>> {
        return api.getAlbumPhotos(albumId)
    }
}