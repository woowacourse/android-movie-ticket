package woowacourse.movie.presentation.reservation.booking

import woowacourse.movie.model.HeadCount
import woowacourse.movie.repository.MovieRepository
import java.time.LocalDate

class MovieReservationPresenter(
    private val id: Long,
    private val view: MovieReservationView,
    private val repository: MovieRepository,
    initialCount: Int = DEFAULT_COUNT,
) {
    private var date: LocalDate
    private var count: HeadCount = HeadCount(initialCount)

    init {
        val screenMovie = repository.screenMovieById(id)
        date = screenMovie.screenDateTimes.first().date
        view.showMovieReservation(screenMovie.toMovieReservationUiModel())
        view.updateHeadCount(count.count)
    }

    fun count(): Int = count.count

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

    companion object {
        private const val DEFAULT_COUNT = 1
    }
}
