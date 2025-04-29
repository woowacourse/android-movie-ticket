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
            List(1111) {
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
                    Movie(
                        "월플라워",
                        R.drawable.wall_flower,
                        LocalDate.of(2025, 4, 1),
                        LocalDate.of(2025, 5, 30),
                        129,
                    ),
                    Movie(
                        "줄무늬 파자마를 입은 소년",
                        R.drawable.pajama,
                        LocalDate.of(2025, 4, 11),
                        LocalDate.of(2025, 5, 30),
                        123,
                    ),
                    Movie(
                        "A.I.",
                        R.drawable.movie_ai,
                        LocalDate.of(2025, 4, 21),
                        LocalDate.of(2025, 5, 30),
                        149,
                    ),
                    Movie(
                        "이미테이션 게임",
                        R.drawable.imitation_game,
                        LocalDate.of(2025, 4, 21),
                        LocalDate.of(2025, 5, 30),
                        130,
                    ),
                )
            }.flatten()
    }
}
