package woowacourse.movie.ui.mapper

import woowacourse.movie.R

object PosterMapper {
    fun mapMovieIdToDrawableRes(key: String): Int? =
        when (key) {
            "match" -> R.drawable.match
            "mickey" -> R.drawable.mickey
            else -> null
        }

    fun mapMovieIdToMovieTitle(key: String): String? =
        when (key) {
            "match" -> "승부"
            "mickey" -> "미키 17"
            else -> null
        }
}
