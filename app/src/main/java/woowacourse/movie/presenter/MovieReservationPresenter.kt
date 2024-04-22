package woowacourse.movie.presenter

import woowacourse.movie.model.HeadCount
import woowacourse.movie.model.ScreeningMovie
import woowacourse.movie.repository.MovieRepository
import woowacourse.movie.view.MovieReservationView
import java.time.LocalDate
import java.time.LocalDateTime

class MovieReservationPresenter(
    private val view: MovieReservationView,
    private val repository: MovieRepository,
) {
    private var count: HeadCount = HeadCount(DEFAULT_COUNT_VALUE)
    private lateinit var screeningMovie: ScreeningMovie

    init {
        view.updateHeadCount(count.count)
    }

    fun loadMovieDetail(screenMovieId: Long) {
        screeningMovie = repository.screenMovieById(screenMovieId)
        view.showMovieReservation(screeningMovie.toMovieReservationUiModel())
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
        val dateTime = screeningMovie.screenDateTimes.first().date.toDefaultLocalDateTime()
        repository.reserveMovie(screeningMovie.id, dateTime = dateTime, count = count).onSuccess {
            view.navigateToReservationResultView(it)
        }
    }

    companion object {
        private const val DEFAULT_COUNT_VALUE = 1
        private fun LocalDate.toDefaultLocalDateTime(): LocalDateTime = this.atTime(0, 0, 0)
    }
}
