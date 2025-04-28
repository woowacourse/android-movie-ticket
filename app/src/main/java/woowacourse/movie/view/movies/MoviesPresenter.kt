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
        view.showMovies(dummyMovie)
    }

    companion object {
        val dummyMovie =
            listOf(
                Movie(
                    R.drawable.harrypotter.toString(),
                    "해리 포터와 마법사의 돌",
                    ScreeningPeriod(
                        LocalDate.of(2025, 4, 1),
                        LocalDate.of(2025, 4, 30),
                    ),
                    RunningTime(152),
                ),
                Movie(
                    R.drawable.harrypotter_2.toString(),
                    "해리 포터와 비밀의 방",
                    ScreeningPeriod(
                        LocalDate.of(2025, 4, 1),
                        LocalDate.of(2025, 4, 30),
                    ),
                    RunningTime(162),
                ),
                Movie(
                    R.drawable.harrypotter_3.toString(),
                    "해리 포터와 아즈카반의 죄수",
                    ScreeningPeriod(
                        LocalDate.of(2025, 5, 1),
                        LocalDate.of(2025, 5, 31),
                    ),
                    RunningTime(141),
                ),
                Movie(
                    R.drawable.harrypotter_4.toString(),
                    "해리 포터와 불의 잔",
                    ScreeningPeriod(
                        LocalDate.of(2025, 6, 1),
                        LocalDate.of(2025, 6, 30),
                    ),
                    RunningTime(157),
                ),
            )
    }
}
