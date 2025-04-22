package woowacourse.movie.ui.mapper

import woowacourse.movie.R

object PosterMapper {
    fun mapMovieIdToDrawableRes(id: Int): Int? =
        when (id) {
            1 -> R.drawable.match
            2 -> R.drawable.mickey
            else -> null
        }

    fun mapMovieIdToMovieTitle(id: Int): String? =
        when (id) {
            1 -> "승부"
            2 -> "미키 17"
            else -> null
        }
}
