package woowacourse.movie.view

import androidx.annotation.DrawableRes
import woowacourse.movie.R

object ResourceMapper {
    @DrawableRes
    fun movieIdToPoster(movieId: String): Int =
        when (movieId) {
            "HarryPotter1" -> R.drawable.poster_harry_potter_and_the_philosophers_stone
            else -> R.drawable.poster_harry_potter_and_the_philosophers_stone // 기본 이미지
        }
}
