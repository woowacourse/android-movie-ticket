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
        val emptyData = Movie(
            R.drawable.no_image,
            "데이터를 불러올 수 없습니다.",
            "0000.0.0",
            "0분",
            "",
            LocalDate.of(2023, 7, 1),
            LocalDate.of(2023, 7, 1),
        )
    }
}
