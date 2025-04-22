package woowacourse.movie.view.screening

import androidx.annotation.DrawableRes
import woowacourse.movie.R
import woowacourse.movie.domain.screening.Screening

object Poster {
    @DrawableRes
    fun Screening.posterId(): Int? = posters[id]

    private val posters: Map<Int, Int> =
        mapOf(0 to R.drawable.poster_harry_potter_and_the_philosophers_stone)
}
