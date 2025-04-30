package woowacourse.movie.movie

import woowacourse.movie.domain.Movie

sealed interface MovieListItem {
    data class MovieItem(val movie: Movie) : MovieListItem

    data class AdvertisementItem(val imageId: Int) : MovieListItem
}
