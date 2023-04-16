package woowacourse.movie

import androidx.annotation.DrawableRes
import java.io.Serializable
import java.time.LocalDate

data class Movie(
    @DrawableRes val poster: Int,
    val title: String,
    val releaseDate: String,
    val runningTime: String,
    val synopsis: String,
    val startDate: LocalDate,
    val endDate: LocalDate
) : Serializable {
    companion object {
        val nullData = Movie(
            R.drawable.no_image,
            "영화제목",
            "0000.0.0",
            "0분",
            "시놉시스가 없습니다.",
            LocalDate.of(2023, 7, 1),
            LocalDate.of(2023, 7, 1),
        )
    }
}
