package jotaemepereira.com.upstacktechinical.views.photos

import jotaemepereira.com.upstacktechinical.base.BaseView
import jotaemepereira.com.upstacktechinical.model.Photo

interface PhotosView: BaseView {
    fun onPhotosLoaded(photos: List<Photo>)
    fun showProgress(showProgress: Boolean)
}