package woowacourse.movie.model.data.dto

import androidx.annotation.DrawableRes
import java.time.LocalDate

data class MovieContent(
    @DrawableRes val imageId: Int,
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
