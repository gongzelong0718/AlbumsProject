package jotaemepereira.com.upstacktechinical.model

import java.io.Serializable

data class Album(
        val userId: Int,
        val id: Int,
        val title: String
): Serializable

data class Photo(
        val albumId: Int,
        val id: Int,
        val title: String,
        val url: String,
        val thumbnailUrl: String
)