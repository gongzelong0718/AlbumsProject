package jotaemepereira.com.upstacktechinical.di.modules

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import jotaemepereira.com.upstacktechinical.di.ApplicationContext
import jotaemepereira.com.upstacktechinical.network.AlbumsAPI
import jotaemepereira.com.upstacktechinical.network.AlbumsNetwork
import javax.inject.Singleton

@Module
class ApplicationModule(private val application: Application) {

    @Provides
    @Singleton
    internal fun provideRestApi(): AlbumsAPI = AlbumsNetwork.create()

    @Provides
    internal fun provideApplication(): Application = application

    @Provides
    @ApplicationContext
    internal fun provideContext(): Context = application
}