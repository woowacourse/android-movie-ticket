package woowacourse.movie.presentation.movies

import androidx.annotation.DrawableRes
import woowacourse.movie.domain.model.movie.Movie

sealed interface MoviesItem {
    data class MovieItem(val movie: Movie) : MoviesItem

    data class AdvertisementItem(
        @DrawableRes val id: Int,
    ) : MoviesItem
}
