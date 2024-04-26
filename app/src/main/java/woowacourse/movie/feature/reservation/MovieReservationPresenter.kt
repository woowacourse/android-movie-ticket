package woowacourse.movie.feature.reservation

import woowacourse.movie.feature.reservation.ui.screeningDateTime
import woowacourse.movie.model.data.MovieRepository
import woowacourse.movie.model.reservation.ReservationCount
import woowacourse.movie.model.time.ScreeningDate
import woowacourse.movie.model.time.rangeTo

class MovieReservationPresenter(
    private val view: MovieReservationContract.View,
    private val movieRepository: MovieRepository,
) : MovieReservationContract.Presenter {
    private lateinit var reservationCount: ReservationCount

    override fun loadMovieData(movieId: Long) {
        val movie = movieRepository.find(movieId)
        view.setUpReservationView(movie)

        val screeningDates = (movie.startScreeningDate..movie.endScreeningDate).toList()
        view.initializeSpinner(screeningDates, screeningDates[0].screeningTimes())
    }

    override fun setUpReservationCount() {
        reservationCount = ReservationCount()
        view.updateReservationCount(reservationCount.count)
    }

    override fun decreaseReservationCount() {
        reservationCount--
        view.updateReservationCount(reservationCount.count)
    }

    override fun increaseReservationCount() {
        reservationCount++
        view.updateReservationCount(reservationCount.count)
    }

    override fun selectSeat(
        screeningDateValue: String,
        screeningTimeValue: String,
    ) {
        val screeningDateTime = screeningDateTime(screeningDateValue, screeningTimeValue)
        view.moveSeatSelectView(screeningDateTime, reservationCount.count)
    }

    override fun updateReservationCount(reservationCountValue: Int) {
        reservationCount =
            runCatching {
                ReservationCount(reservationCountValue)
            }.getOrElse {
                view.handleError(it)
                return
            }
        view.updateReservationCount(reservationCount.count)
    }

    override fun selectScreeningDate(screeningDate: ScreeningDate) {
        val screeningTimes = screeningDate.screeningTimes()
        view.updateScreeningTimeSpinner(screeningTimes)
    }
}
