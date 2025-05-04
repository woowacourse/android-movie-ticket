package woowacourse.movie.feature.movies.view.adapter

import woowacourse.movie.feature.model.MovieUiModel
import woowacourse.movie.feature.movies.view.adapter.MovieItemViewType.ADVERTISEMENT
import woowacourse.movie.feature.movies.view.adapter.MovieItemViewType.MOVIE

sealed class MovieItem(
    movieViewType: MovieItemViewType,
) {
    val viewType: Int = movieViewType.ordinal
    abstract val id: Int

    data class Movie(
        override val id: Int,
        val value: MovieUiModel,
    ) : MovieItem(MOVIE)

    data class Advertisement(
        override val id: Int,
    ) : MovieItem(ADVERTISEMENT)

    companion object {
        fun from(movies: List<MovieUiModel>): List<MovieItem> {
            var id = 0
            val movieItems = mutableListOf<MovieItem>()

            movies.chunked(3).forEach { movieChunk ->
                movieChunk.forEach { movie -> movieItems.add(Movie(id++, movie)) }
                if (movieChunk.size == 3) movieItems.add(Advertisement(id++))
            }

            return movieItems
        }
    }
}
