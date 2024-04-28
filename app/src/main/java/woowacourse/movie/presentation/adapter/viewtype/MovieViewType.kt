package woowacourse.movie.presentation.adapter.viewtype

import woowacourse.movie.domain.Movie

sealed interface MovieViewType {
    data class Screen(
        val movie: Movie,
        val onMovieItemClick: (Long) -> Unit,
    ) : MovieViewType

    data class Ads(
        val adsUrl: String,
    ) : MovieViewType
}
