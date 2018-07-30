package jotaemepereira.com.upstacktechinical.network

import io.reactivex.Observable
import jotaemepereira.com.upstacktechinical.model.Album
import jotaemepereira.com.upstacktechinical.model.Photo
import retrofit2.http.GET
import retrofit2.http.Path

interface AlbumsAPI {

    @GET("/albums")
    fun getAlbums(): Observable<List<Album>>

    @GET("/albums/{albumId}/photos")
    fun getAlbumPhotos( @Path("albumId") albumId: Int): Observable<List<Photo>>
}