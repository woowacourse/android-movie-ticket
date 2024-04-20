package woowacourse.movie.presentation.reservation.booking

import woowacourse.movie.model.HeadCount
import woowacourse.movie.repository.MovieRepository
import java.time.LocalDate

class MovieReservationPresenter(
    private val id: Long,
    private val view: MovieReservationView,
    private val repository: MovieRepository,
) {
    private var count: HeadCount = HeadCount(1)
    private var date: LocalDate

    init {
        val screenMovie = repository.screenMovieById(id)
        date = screenMovie.screenDateTimes.first().date
        view.showMovieReservation(screenMovie.toMovieReservationUiModel())
        view.updateHeadCount(count.count)
    }

    fun plusCount() {
        count = count.increase()
        view.updateHeadCount(count.count)
    }

    fun minusCount() {
        if (count.canDecrease()) {
            count = count.decrease()
            view.updateHeadCount(count.count)
        }
    }

    fun completeReservation() {
        val dateTime = date.atTime(0, 0, 0)
        repository.reserveMovie(id, dateTime = dateTime, count = count).onSuccess {
            view.navigateToReservationResultView(it)
        }
    }
}
