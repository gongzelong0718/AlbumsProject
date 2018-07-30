package jotaemepereira.com.upstacktechinical.views.albums

import jotaemepereira.com.upstacktechinical.base.BaseView
import jotaemepereira.com.upstacktechinical.model.Album

interface AlbumsView: BaseView {
    fun onAlbumsLoaded(albums: List<Album>)
    fun showProgress(showProgress: Boolean)
}