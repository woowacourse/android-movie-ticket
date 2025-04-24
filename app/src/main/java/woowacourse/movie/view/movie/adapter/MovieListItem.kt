package woowacourse.movie.view.movie.adapter

import woowacourse.movie.view.movie.model.MovieUiModel

sealed class MovieListItem(
    val type: ViewType,
) {
    data class MovieItem(
        val movie: MovieUiModel,
    ) : MovieListItem(ViewType.TYPE_MOVIE)

    class AdItem : MovieListItem(ViewType.TYPE_ADS)

    enum class ViewType {
        TYPE_MOVIE,
        TYPE_ADS,
    }
}
