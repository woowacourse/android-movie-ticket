package woowacourse.movie.view.screening

import androidx.annotation.DrawableRes
import woowacourse.movie.R
import woowacourse.movie.domain.screening.Screening

object Poster {
    @DrawableRes
    fun Screening.posterId(): Int? = posters[id]

    @DrawableRes
    fun posterId(movieId: Int): Int? = posters[movieId]

    private val posters: Map<Int, Int> =
        mapOf(
            0 to R.drawable.poster_harry_potter_and_the_philosophers_stone,
            1 to R.drawable.poster_harry_potter_and_the_chamber_of_secrets,
            2 to R.drawable.poster_harry_potter_and_the_prisoner_of_azkaban,
            3 to R.drawable.poster_harry_potter_and_the_goblet_of_fire,
        )
}
