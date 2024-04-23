package woowacourse.movie.model.data.dto

import java.time.LocalDate

data class MovieContent(
    val posterImageId: Int,
    val title: String,
    val screeningDate: LocalDate,
    val runningTime: Int,
    val synopsis: String,
    val id: Long = 0,
)

val nullMovieContent =
    MovieContent(
        0,
        "오류가 발생했습니다.",
        LocalDate.of(1, 1, 1),
        0,
        "",
    )
