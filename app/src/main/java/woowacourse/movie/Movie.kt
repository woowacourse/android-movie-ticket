package woowacourse.movie

import androidx.annotation.DrawableRes
import java.time.LocalDate

data class Movie(
    val title: String,
    val startDate: LocalDate,
    val endDate: LocalDate,
    val runningTime: Int,
    @DrawableRes val poster: Int,
)

val movies: List<Movie> =
    listOf(
        Movie(
            title = "해리 포터와 마법사의 돌",
            startDate = LocalDate.of(2025, 4, 1),
            endDate = LocalDate.of(2025, 4, 25),
            runningTime = 152,
            poster = R.drawable.img_poster_harry_potter_and_the_philosophers_stone,
        ),
    )
