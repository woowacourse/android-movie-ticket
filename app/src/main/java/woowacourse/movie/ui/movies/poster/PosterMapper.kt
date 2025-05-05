package woowacourse.movie.ui.movies.poster

import woowacourse.movie.R

object PosterMapper {
    fun mapMovieIdToDrawableRes(id: Int): Int? =
        when (id) {
            1 -> R.drawable.match
            2 -> R.drawable.mickey
            3 -> R.drawable.yahdang
            4 -> R.drawable.prime_city
            else -> null
        }

    fun mapMovieIdToMovieTitle(id: Int): String? =
        when (id) {
            1 -> "승부"
            2 -> "미키 17"
            3 -> "야당"
            4 -> "범죄도시"
            else -> null
        }
}
