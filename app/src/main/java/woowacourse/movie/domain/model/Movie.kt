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
        val DUMMY_MOVIES =
            listOf(
                Movie(
                    "해리 포터와 마법사의 돌",
                    R.drawable.harry_potter_one,
                    ScreeningDate(
                        LocalDate.of(2025, 4, 1),
                        LocalDate.of(2025, 4, 30),
                    ),
                    152,
                ),
                Movie(
                    "해리 포터와 비밀의 방",
                    R.drawable.harry_potter_two,
                    ScreeningDate(
                        LocalDate.of(2025, 4, 1),
                        LocalDate.of(2025, 4, 28),
                    ),
                    162,
                ),
                Movie(
                    "해리 포터와 아즈카반의 죄수",
                    R.drawable.harry_potter_three,
                    ScreeningDate(
                        LocalDate.of(2025, 5, 1),
                        LocalDate.of(2025, 5, 31),
                    ),
                    141,
                ),
                Movie(
                    "해리 포터와 불의 잔",
                    R.drawable.harry_potter_four,
                    ScreeningDate(
                        LocalDate.of(2025, 6, 1),
                        LocalDate.of(2025, 6, 30),
                    ),
                    157,
                ),
            )
    }
}
