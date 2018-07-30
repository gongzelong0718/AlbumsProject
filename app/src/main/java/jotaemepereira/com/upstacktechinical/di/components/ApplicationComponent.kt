package jotaemepereira.com.upstacktechinical.di.components;

import android.app.Application
import android.content.Context
import dagger.Component
import jotaemepereira.com.upstacktechinical.di.ApplicationContext
import jotaemepereira.com.upstacktechinical.di.modules.ApplicationModule
import jotaemepereira.com.upstacktechinical.model.DataManager
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(ApplicationModule::class))
interface ApplicationComponent {

    @ApplicationContext
    fun context(): Context
    fun application(): Application
    fun dataManager(): DataManager
}