package woowacourse.movie.moviereservation

import android.util.Log
import woowacourse.movie.model.HeadCount
import woowacourse.movie.moviereservation.uimodel.HeadCountUiModel
import woowacourse.movie.repository.MovieRepository
import java.time.LocalDate
import java.time.LocalDateTime

class MovieReservationPresenter(
    private val view: MovieReservationContract.View,
    private val repository: MovieRepository,
) : MovieReservationContract.Presenter {
    override fun loadMovieDetail(screenMovieId: Long) {
        runCatching {
            val screeningMovie = repository.screenMovieById(screenMovieId)
            view.showMovieInfo(screeningMovie.toMovieReservationUiModel())
            view.showScreeningDateTime(screeningMovie.toScreeningDateTimeUiModel())
        }.onFailure {
            Log.d("테스트", "${it.message}")
            view.showScreeningMovieError()
        }
    }

    override fun plusCount(currentCount: HeadCountUiModel) {
        val count = currentCount.toHeadCount()
        view.updateHeadCount(count.increase().toHeadCountUiModel())
    }

    override fun minusCount(currentCount: HeadCountUiModel) {
        val count = currentCount.toHeadCount()
        runCatching {
            count.decrease().toHeadCountUiModel()
        }.onSuccess { updatedCount ->
            view.updateHeadCount(updatedCount)
        }.onFailure {
            view.showCantDecreaseError(HeadCount.MIN_COUNT)
        }
    }

    override fun completeReservation(
        screenMovieId: Long,
        currentCount: HeadCountUiModel,
    ) {
        runCatching {
            repository.reserveMovie(
                screenMovieId,
                dateTime = repository.screenMovieById(screenMovieId).screenDateTimes.first().date.toDefaultLocalDateTime(),
                count = currentCount.toHeadCount(),
            )
        }.onSuccess {
            view.navigateToReservationResultView(it)
        }.onFailure {
            view.showMovieReservationError()
        }
    }

    companion object {
        private fun LocalDate.toDefaultLocalDateTime(): LocalDateTime = this.atTime(0, 0, 0)
    }
}
