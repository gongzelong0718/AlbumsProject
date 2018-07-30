package jotaemepereira.com.upstacktechinical

import android.app.Application
import android.content.Context
import jotaemepereira.com.upstacktechinical.di.components.ApplicationComponent
import jotaemepereira.com.upstacktechinical.di.components.DaggerApplicationComponent
import jotaemepereira.com.upstacktechinical.di.modules.ApplicationModule

class AlbumsApplication: Application() {

    companion object {
        @JvmStatic lateinit var graph: ApplicationComponent
        @JvmStatic
        fun get(context: Context): AlbumsApplication = context.applicationContext as AlbumsApplication
    }

    override fun onCreate() {
        super.onCreate()

        graph = DaggerApplicationComponent
                .builder()
                .applicationModule(ApplicationModule(this))
                .build()
    }

    fun setComponent(component: ApplicationComponent) {
        graph = component
    }
}