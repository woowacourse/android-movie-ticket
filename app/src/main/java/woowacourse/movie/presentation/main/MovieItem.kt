package woowacourse.movie.presentation.main

import androidx.annotation.DrawableRes
import woowacourse.movie.presentation.model.MovieModel

sealed class MovieItem {
    data class Ad(@DrawableRes val adImage: Int) : MovieItem()

    data class Movie(val movie: MovieModel) : MovieItem()
}
