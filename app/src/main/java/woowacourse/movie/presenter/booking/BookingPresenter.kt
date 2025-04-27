package woowacourse.movie.presenter.booking

import woowacourse.movie.domain.model.booking.Booking
import woowacourse.movie.domain.model.booking.BookingResult
import woowacourse.movie.domain.model.movie.Movie
import woowacourse.movie.sample.SampleMovies
import woowacourse.movie.ui.model.movie.MovieUiModel
import woowacourse.movie.util.mapper.MovieModelMapper

class BookingPresenter(
    val view: BookingContract.View,
    val movieUiModel: MovieUiModel?,
) : BookingContract.Presenter {
    private val movies = SampleMovies()
    private val bookingMovie: Movie by lazy { MovieModelMapper.toDomain(movieUiModel!!) }
    private val booking: Booking by lazy { Booking(bookingMovie) }
    private val bookingResult: BookingResult by lazy { initBookingResult(booking) }

    init {
        if (movieUiModel == null) {
            view.showErrorMessage(ERROR_NOT_EXIST_MOVIE)
        } else {
            loadMovie(movieUiModel)
        }
    }

    override fun loadMovie(movieUiModel: MovieUiModel) {
        if (isExist(movieUiModel)) {
            view.showMovieInfo(movieUiModel)
        } else {
            view.showErrorMessage(ERROR_NOT_EXIST_MOVIE)
        }
    }

    private fun isExist(movieUiModel: MovieUiModel): Boolean {
        return movies.movieUiModels.any { movie -> movie.id == movieUiModel.id }
    }

    private fun initBookingResult(booking: Booking): BookingResult {
        val nearestDate = booking.screeningPeriods()[0]
        val availBookingTimes = booking.screeningTimes(nearestDate)
        val nearestTime = availBookingTimes[0]
        return BookingResult(
            title = bookingMovie.title,
            headCount = 0,
            selectedDate = nearestDate,
            selectedTime = nearestTime,
        )
    }

    companion object {
        private const val ERROR_NOT_EXIST_MOVIE = "영화 정보가 존재하지 않습니다."
    }
}
