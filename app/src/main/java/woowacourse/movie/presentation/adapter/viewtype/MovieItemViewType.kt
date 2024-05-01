package woowacourse.movie.presentation.adapter.viewtype

import woowacourse.movie.domain.Movie

sealed class MovieItemViewType(val movieItemId: MovieItemId) {
    data class MovieView(
        val movie: Movie,
    ) : MovieItemViewType(MovieItemId.MOVIE)

    data class AdView(
        val adsUrl: String,
    ) : MovieItemViewType(MovieItemId.AD)
}

enum class MovieItemId(val id: Int) {
    MOVIE(0),
    AD(1),
}
