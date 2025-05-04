package woowacourse.movie.domain.model

import java.io.Serializable
import java.time.LocalDate

data class Movie(
    val id: Int,
    val title: String,
    val releaseDate: ScreeningDate,
    val runningTime: Int,
) : Serializable {
    companion object {
        val DUMMY_MOVIES =
            listOf(
                Movie(
                    1,
                    "해리 포터와 마법사의 돌",
                    ScreeningDate(
                        LocalDate.of(2025, 4, 1),
                        LocalDate.of(2025, 4, 30),
                    ),
                    152,
                ),
                Movie(
                    2,
                    "해리 포터와 비밀의 방",
                    ScreeningDate(
                        LocalDate.of(2025, 4, 1),
                        LocalDate.of(2025, 4, 28),
                    ),
                    162,
                ),
                Movie(
                    3,
                    "해리 포터와 아즈카반의 죄수",
                    ScreeningDate(
                        LocalDate.of(2025, 5, 1),
                        LocalDate.of(2025, 5, 31),
                    ),
                    141,
                ),
                Movie(
                    4,
                    "해리 포터와 불의 잔",
                    ScreeningDate(
                        LocalDate.of(2025, 6, 1),
                        LocalDate.of(2025, 6, 30),
                    ),
                    157,
                ),
            )
    }
}
