package woowacourse.movie.moviereservation

import woowacourse.movie.model.HeadCount
import woowacourse.movie.moviereservation.uimodel.BookingInfoUiModel
import woowacourse.movie.moviereservation.uimodel.HeadCountUiModel
import woowacourse.movie.repository.MovieRepository

class MovieReservationPresenter(
    private val view: MovieReservationContract.View,
    private val repository: MovieRepository,
) : MovieReservationContract.Presenter {
    override fun loadMovieDetail(screenMovieId: Long) {
        runCatching {
            repository.screenMovieById(screenMovieId)
        }.onSuccess { screeningMovie ->
            view.showMovieInfo(screeningMovie.toMovieReservationUiModel())
            view.showDefaultBookingInfo(
                screeningMovie.toScreeningDateTimeUiModel(),
                BookingInfoUiModel(
                    screenMovieId,
                    HeadCount.MIN_COUNT,
                    screeningMovie.startDate,
                    screeningMovie.screenDateTimes.first().times.first(),
                ),
            )
        }.onFailure {
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
}
