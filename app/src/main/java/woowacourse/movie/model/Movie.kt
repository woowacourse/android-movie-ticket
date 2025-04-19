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
                    LocalDate.of(2025, 4, 20),
                    120,
                ),
            )

        val value: Movie =
            Movie(
                "라라랜드",
                R.drawable.lalaland,
                LocalDate.of(2025, 4, 1),
                LocalDate.of(2025, 4, 20),
                120,
            )
    }
}
