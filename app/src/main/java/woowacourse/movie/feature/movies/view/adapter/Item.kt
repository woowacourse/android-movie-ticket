package woowacourse.movie.feature.movies.view.adapter

import woowacourse.movie.feature.model.MovieUiModel

sealed class Item {
    abstract val id: Int

    data class Movie(
        override val id: Int,
        val value: MovieUiModel,
    ) : Item()

    data class Advertisement(
        override val id: Int,
    ) : Item()

    companion object {
        fun from(movies: List<MovieUiModel>): List<Item> {
            var id = 0
            val items = mutableListOf<Item>()

            movies.chunked(3).forEach { movieChunk ->
                movieChunk.forEach { movie -> items.add(Movie(id++, movie)) }
                if (movieChunk.size == 3) items.add(Advertisement(id++))
            }

            return items
        }
    }
}
