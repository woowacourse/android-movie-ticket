package woowacourse.movie.view.main

import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test
import woowacourse.movie.R
import woowacourse.movie.domain.model.movie.Movie
import woowacourse.movie.domain.model.movie.RunningTime
import woowacourse.movie.domain.model.movie.ScreeningDate
import woowacourse.movie.ui.main.MainContract
import woowacourse.movie.ui.main.MainPresenter
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class MainPresenterTest {
    private val view = mockk<MainContract.View>(relaxed = true)
    private val presenter = MainPresenter(view)
    private val advertisementResources = listOf(R.drawable.baemin, R.drawable.baemina)
    private var mockMovies: List<Movie>

    init {
        val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern(DATE_FORMAT)
        val startDate: LocalDate = LocalDate.parse("2025.04.15", formatter)
        val endDate: LocalDate = LocalDate.parse("2025.05.23", formatter)

        mockMovies =
            List(10) {
                Movie(
                    "해리포터와 마법사의 돌",
                    ScreeningDate(startDate, endDate),
                    RunningTime(152),
                    R.drawable.harrypotter,
                )
            }
    }

    @Test
    fun `초기화한 영화와 광고를 보여준다`() {
        presenter.showMovies()
        verify {
            view.showMovies(
                mockMovies,
                advertisementResources,
            )
        }
    }

    private companion object {
        const val DATE_FORMAT = "yyyy.MM.dd"
    }
}
