package woowacourse.movie.view.movies

import woowacourse.movie.R
import woowacourse.movie.domain.model.Movie
import woowacourse.movie.domain.model.RunningTime
import woowacourse.movie.domain.model.ScreeningPeriod
import java.time.LocalDate

class MoviesPresenter(
    private val view: MoviesContract.View,
) : MoviesContract.Presenter {
    override fun loadData() {
        val movies = listOf(dummyMovie)
        view.showMovies(movies)
    }

    companion object {
        val dummyMovie =
            Movie(
                R.drawable.harrypotter.toString(),
                "해리 포터와 마법사의 돌",
                ScreeningPeriod(
                    LocalDate.of(2025, 4, 1),
                    LocalDate.of(2025, 4, 25),
                ),
                RunningTime(152),
            )
    }
}
