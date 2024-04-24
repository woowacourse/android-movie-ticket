package woowacourse.movie.moviereservation

import woowacourse.movie.model.ScreeningMovie
import woowacourse.movie.repository.MovieRepository
import java.time.LocalDate
import java.time.LocalDateTime

class MovieReservationPresenter(
    private val view: MovieReservationView,
    private val repository: MovieRepository,
) {
    private lateinit var screeningMovie: ScreeningMovie

    fun loadMovieDetail(screenMovieId: Long) {
        screeningMovie = repository.screenMovieById(screenMovieId)
        view.showMovieReservation(screeningMovie.toMovieReservationUiModel())
    }

    fun plusCount(currentCount: HeadCountUiModel) {
        val count = currentCount.toHeadCount()
        view.updateHeadCount(count.increase().toHeadCountUiModel())
    }

    fun minusCount(currentCount: HeadCountUiModel) {
        val count = currentCount.toHeadCount()
        if (count.canDecrease()) {
            view.updateHeadCount(count.decrease().toHeadCountUiModel())
        }
    }

    fun completeReservation(currentCount: HeadCountUiModel) {
        val dateTime = screeningMovie.screenDateTimes.first().date.toDefaultLocalDateTime()
        repository.reserveMovie(
            screeningMovie.id,
            dateTime = dateTime,
            count = currentCount.toHeadCount(),
        ).onSuccess {
            view.navigateToReservationResultView(it)
        }
    }

    companion object {
        private fun LocalDate.toDefaultLocalDateTime(): LocalDateTime = this.atTime(0, 0, 0)
    }
}
