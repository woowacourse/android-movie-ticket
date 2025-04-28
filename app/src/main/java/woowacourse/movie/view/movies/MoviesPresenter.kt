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
                    R.drawable.harrypotter_1.toString(),
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
                Movie(
                    R.drawable.harrypotter_5.toString(),
                    "해리 포터와 불사조 기사단",
                    ScreeningPeriod(
                        LocalDate.of(2025, 6, 1),
                        LocalDate.of(2025, 6, 30),
                    ),
                    RunningTime(157),
                ),
                Movie(
                    R.drawable.harrypotter_6.toString(),
                    "해리 포터와 혼혈 왕자",
                    ScreeningPeriod(
                        LocalDate.of(2025, 6, 1),
                        LocalDate.of(2025, 6, 30),
                    ),
                    RunningTime(153),
                ),
                Movie(
                    R.drawable.harrypotter_7.toString(),
                    "해리 포터와 죽음의 성물 - 1부",
                    ScreeningPeriod(
                        LocalDate.of(2025, 6, 1),
                        LocalDate.of(2025, 6, 30),
                    ),
                    RunningTime(146),
                ),
                Movie(
                    R.drawable.harrypotter_8.toString(),
                    "해리 포터와 죽음의 성물 - 2부",
                    ScreeningPeriod(
                        LocalDate.of(2025, 6, 1),
                        LocalDate.of(2025, 6, 30),
                    ),
                    RunningTime(131),
                ),
            )
    }
}
