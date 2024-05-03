package woowacourse.movie.fixtures

import woowacourse.movie.model.Movie
import java.time.LocalDate

fun movie(
    id: Int = 3,
    title: String = "해리포터와 불의 잔",
    screenDates: List<LocalDate> = listOf(LocalDate.of(2024, 4, 1), LocalDate.of(2024, 4, 29)),
    runningTime: Int = 162,
    description: String = "해리포터",
    img: Int = 0,
): Movie {
    return Movie(
        id,
        title,
        screenDates,
        runningTime,
        description,
        img,
    )
}
