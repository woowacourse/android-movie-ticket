package woowacourse.movie.view.movie.adapter

import woowacourse.movie.model.Movie

sealed class MovieItemType {
    data class MovieData(
        val movie: Movie,
    ) : MovieItemType()

    data object Ad : MovieItemType()
}
