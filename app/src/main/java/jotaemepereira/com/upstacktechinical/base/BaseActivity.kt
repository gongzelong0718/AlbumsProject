package jotaemepereira.com.upstacktechinical.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import jotaemepereira.com.upstacktechinical.AlbumsApplication
import jotaemepereira.com.upstacktechinical.di.components.ActivityComponent
import jotaemepereira.com.upstacktechinical.di.components.DaggerActivityComponent

open class BaseActivity: AppCompatActivity() {

    lateinit var graph: ActivityComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        graph = DaggerActivityComponent
                .builder()
                .applicationComponent(AlbumsApplication.graph)
                .build()
    }

}