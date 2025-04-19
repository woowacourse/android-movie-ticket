package woowacourse.movie.domain.model

import woowacourse.movie.R
import java.io.Serializable
import java.time.LocalDate

class Movie(
    val title: String,
    val poster: String,
    val releaseDate: ScreeningDate,
    val runningTime: String,
) : Serializable {
    companion object {
        val dummy =
            mutableListOf<Movie>().apply {
                repeat(100) {
                    this.add(
                        Movie(
                            "해리 포터와 마법사의 돌 $it",
                            R.drawable.harry_potter_one.toString(),
                            ScreeningDate(
                                LocalDate.of(2025, 4, 1),
                                LocalDate.of(2025, 4, 25),
                            ),
                            "152분",
                        ),
                    )
                }
            }
    }
}
