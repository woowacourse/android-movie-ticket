package woowacourse.movie.domain.model.movies

import woowacourse.movie.R
import woowacourse.movie.domain.model.ScreeningDate
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
                        Poster(R.drawable.harry_potter_one.toString()),
                        screeningDate,
                        152,
                    )
                },
            )

        fun size() = movies.items.size

        fun get(idx: Int) = movies.items[idx]

        fun getAll() = movies.items
    }
}
