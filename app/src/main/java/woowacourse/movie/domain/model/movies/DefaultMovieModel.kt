package woowacourse.movie.domain.model.movies

import woowacourse.movie.R
import woowacourse.movie.domain.model.ScreeningDate
import woowacourse.movie.view.movies.MovieListContract
import java.time.LocalDate

class DefaultMovieModel : MovieListContract.MovieModel {
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

    override fun get(index: Int): Movie = movies[index]

    override fun getAll(): Movies = movies

    override fun size(): Int = movies.size()
}
