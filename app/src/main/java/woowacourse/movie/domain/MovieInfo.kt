package woowacourse.movie.domain

import androidx.annotation.DrawableRes

sealed class MovieInfo {
    data class Advertisement(
        @DrawableRes val adImage: Int,
        val url: String,
    ) : MovieInfo()

    data class MovieUnit(
        val movie: Movie,
    ) : MovieInfo()
}
