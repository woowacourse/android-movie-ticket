package woowacourse.movie.moviereservation

import woowacourse.movie.model.HeadCount
import woowacourse.movie.repository.MovieRepository
import java.time.LocalDate
import java.time.LocalDateTime

class MovieReservationPresenter(
    private val view: MovieReservationView,
    private val repository: MovieRepository,
) {
    fun loadMovieDetail(screenMovieId: Long) {
        runCatching {
            val screeningMovie = repository.screenMovieById(screenMovieId)
            view.showMovieReservation(screeningMovie.toMovieReservationUiModel())
        }.onFailure {
            view.showScreeningMovieError()
        }
    }

    fun plusCount(currentCount: HeadCountUiModel) {
        val count = currentCount.toHeadCount()
        view.updateHeadCount(count.increase().toHeadCountUiModel())
    }

    fun minusCount(currentCount: HeadCountUiModel) {
        val count = currentCount.toHeadCount()
        runCatching {
            view.updateHeadCount(count.decrease().toHeadCountUiModel())
        }.onFailure {
            view.showCantDecreaseError(HeadCount.MIN_COUNT)
        }
    }

    fun completeReservation(
        screenMovieId: Long,
        currentCount: HeadCountUiModel,
    ) {
        repository.reserveMovie(
            screenMovieId,
            dateTime = repository.screenMovieById(screenMovieId).screenDateTimes.first().date.toDefaultLocalDateTime(),
            count = currentCount.toHeadCount(),
        ).onSuccess {
            view.navigateToReservationResultView(it)
        }
    }

    companion object {
        private fun LocalDate.toDefaultLocalDateTime(): LocalDateTime = this.atTime(0, 0, 0)
    }
}
