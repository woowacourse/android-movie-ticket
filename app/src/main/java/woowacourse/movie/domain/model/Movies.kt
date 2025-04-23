package woowacourse.movie.domain.model

import woowacourse.movie.R
import java.time.LocalDate

class Movies(
    val items: List<Movie>,
) {
    companion object DUMMY {
        private val movies =
            Movies(
                List(100) {
                    val screeningDate =
                        ScreeningDate(
                            LocalDate.of(2025, 4, 1),
                            LocalDate.of(2025, 4, 25),
                        )

                    Movie(
                        "해리 포터와 마법사의 돌 $it",
                        R.drawable.harry_potter_one.toString(),
                        screeningDate,
                        "152분",
                    )
                },
            )

        fun get(idx: Int) = movies.items[idx]

        fun getAll() = movies.items
    }
}
