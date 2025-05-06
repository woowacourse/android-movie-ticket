package woowacourse.movie.view.movies

import woowacourse.movie.domain.model.Advertisement
import woowacourse.movie.domain.model.Movie

sealed class MovieListItem {
    class MovieItem(
        val movie: Movie,
    ) : MovieListItem()

    class AdItem(
        val ad: Advertisement,
    ) : MovieListItem()
}
