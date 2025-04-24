package woowacourse.movie.domain.model

import woowacourse.movie.R
import java.io.Serializable
import java.time.LocalDate

data class Movie(
    val title: String,
    val posterId: Int,
    val releaseDate: ScreeningDate,
    val runningTime: Int,
) : Serializable {
    companion object {
        val DUMMY_MOVIE = Movie(
            "해리 포터와 마법사의 돌",
            R.drawable.harry_potter_one,
            ScreeningDate(
                LocalDate.of(2025, 4, 1),
                LocalDate.of(2025, 4, 30),
            ),
            152,
        )
    }
}
