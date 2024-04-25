package io.pyron.server.data.entity

data class Movie(
    val id: Long,
    val thumbnailUrl: String,
    val title: String,
    val description: String,
    val runningTime: Int,
)
