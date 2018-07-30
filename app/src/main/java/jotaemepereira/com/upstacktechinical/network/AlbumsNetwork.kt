package jotaemepereira.com.upstacktechinical.network

import jotaemepereira.com.upstacktechinical.BuildConfig
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

class AlbumsNetwork() {
    companion object {
        fun create(): AlbumsAPI {
            val retrofit = Retrofit.Builder()
                    .baseUrl(BuildConfig.API_ENDPOINT)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(MoshiConverterFactory.create())
                    .build()

            return retrofit.create(AlbumsAPI::class.java)
        }
    }
}