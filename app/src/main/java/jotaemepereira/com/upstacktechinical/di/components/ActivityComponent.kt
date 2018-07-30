package jotaemepereira.com.upstacktechinical.di.components;

import dagger.Component
import jotaemepereira.com.upstacktechinical.di.PerActivity
import jotaemepereira.com.upstacktechinical.di.modules.ActivityModule
import jotaemepereira.com.upstacktechinical.views.albums.AlbumsActivity
import jotaemepereira.com.upstacktechinical.views.photos.PhotosActivity

@PerActivity
@Component(dependencies = arrayOf(ApplicationComponent::class), modules = arrayOf(ActivityModule::class))
interface ActivityComponent {

    fun inject(albumsActivity: AlbumsActivity)
    fun inject(photosActivity: PhotosActivity)
}