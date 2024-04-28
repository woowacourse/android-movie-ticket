package woowacourse.movie.ui.reservation

import woowacourse.movie.domain.movie.Movie
import java.time.LocalDate

class ReservationPresenter(private val view: ReservationContract.View) : ReservationContract.Presenter {
    override fun loadMovie() {
        val movie = MOCK_Existing_MOVIE_WITH_ONE_LOCAL_DATE
        view.showMovie(movie)
    }

    override fun onClickedPlusButton(count: Int) {
        if (count < 99) {
            view.updateCount(count + 1)
        } else {
            view.updateCount(count)
        }
    }

    override fun onClickedSubButton(count: Int) {
        if (count > 1) view.updateCount(count - 1)
    }

    override fun onClickedReservation(
        existingMovie: Movie,
        count: Int,
    ) {
    }

    companion object {
        val MOCK_Existing_MOVIE_WITH_ONE_LOCAL_DATE =
            Movie(
                id = 1,
                title = "mockTitle",
                runningTime = 100,
                screenPeriod = listOf(LocalDate.of(2024, 4, 1)),
                description = "mock description",
                imgResId = 0,
            )
        private val MOCK_Existing_MOVIE_WITH_TWO_LOCAL_DATE =
            Movie(
                id = 1,
                title = "mockTitle",
                runningTime = 100,
                screenPeriod =
                    listOf(
                        LocalDate.of(2024, 4, 1),
                        LocalDate.of(2024, 4, 30),
                    ),
                description = "mock description",
                imgResId = 0,
            )
    }
}
