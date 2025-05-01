package woowacourse.movie.domain

import androidx.annotation.DrawableRes
import woowacourse.movie.R
import woowacourse.movie.domain.movietime.Date
import java.io.Serializable
import java.time.LocalDate

data class Movie(
    @DrawableRes val image: Int,
    val title: String,
    val date: Date,
    val time: Int,
) : Serializable {
    companion object {
        val dummy: List<Movie> =
            (1..10000).map {
                Movie(
                    R.drawable.harry,
                    "해리 포터$it",
                    Date(LocalDate.of(2025, 4, 1), LocalDate.of(2025, 5, 30)),
                    152,
                )
            }
    }
}
