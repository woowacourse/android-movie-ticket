package woowacourse.movie.ui.main

import woowacourse.movie.R
import woowacourse.movie.domain.model.Movie
import woowacourse.movie.domain.model.RunningTime
import woowacourse.movie.domain.model.ScreeningDate
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class MainPresenter(private val view: MainContract.View) : MainContract.Presenter {
    override fun showMovies() {
        val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern(DATE_FORMAT)
        val startDate: LocalDate = LocalDate.parse("2025.04.01", formatter)
        val endDate: LocalDate = LocalDate.parse("2025.04.25", formatter)

        val movies =
            List(1000) {
                Movie(
                    "해리포터와 마법사의 돌",
                    ScreeningDate(startDate, endDate),
                    RunningTime(152),
                    R.drawable.harrypotter,
                )
            }
        view.showMovies(movies)
    }

    private companion object {
        const val DATE_FORMAT = "yyyy.MM.dd"
    }
}
