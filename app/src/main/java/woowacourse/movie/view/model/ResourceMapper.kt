package woowacourse.movie.view.model

import androidx.annotation.DrawableRes
import woowacourse.movie.R
import woowacourse.movie.model.movie.screening.Screening.Companion.HARRY_POTTER_1_MOVIE_ID

object ResourceMapper {
    @DrawableRes
    fun movieIdToPoster(movieId: String): Int =
        when (movieId) {
            HARRY_POTTER_1_MOVIE_ID -> R.drawable.poster_harry_potter_and_the_philosophers_stone
            else -> R.drawable.poster_harry_potter_and_the_philosophers_stone // 기본 이미지
        }
}
