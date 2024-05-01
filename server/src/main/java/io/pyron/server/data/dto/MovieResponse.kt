package io.pyron.server.data.dto

import io.pyron.server.data.entity.Movie

data class MovieResponse(
    val id: Long,
    val thumbnailUrl: String,
    val title: String,
    val description: String,
    val runningTime: Int,
) {
    companion object {
        fun from(movie: Movie): MovieResponse {
            return MovieResponse(
                id = movie.id,
                thumbnailUrl = movie.thumbnailUrl,
                title = movie.title,
                description = movie.description,
                runningTime = movie.runningTime,
            )
        }
    }
}
