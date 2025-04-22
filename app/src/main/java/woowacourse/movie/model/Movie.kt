package woowacourse.movie.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import woowacourse.movie.R
import java.time.LocalDate

@Parcelize
data class Movie(
    val title: String,
    val poster: Int,
    val startDate: LocalDate,
    val endDate: LocalDate,
    val runningTime: Int,
) : Parcelable {
    companion object {
        val values: List<Movie> =
            listOf(
                Movie(
                    "라라랜드",
                    R.drawable.lalaland,
                    LocalDate.of(2025, 4, 1),
                    LocalDate.of(2025, 4, 30),
                    123,
                ),
                Movie(
                    "승부",
                    R.drawable.match,
                    LocalDate.of(2025, 4, 11),
                    LocalDate.of(2025, 4, 30),
                    114,
                ),
                Movie(
                    "야당",
                    R.drawable.yadang,
                    LocalDate.of(2025, 4, 21),
                    LocalDate.of(2025, 4, 30),
                    109,
                ),
            )

        val value: Movie =
            Movie(
                "Mock 라라랜드",
                R.drawable.lalaland,
                LocalDate.of(2025, 4, 1),
                LocalDate.of(2025, 4, 30),
                120,
            )
    }
}
