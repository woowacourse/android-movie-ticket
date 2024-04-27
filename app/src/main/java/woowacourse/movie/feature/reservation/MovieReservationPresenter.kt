package woowacourse.movie.feature.reservation

import woowacourse.movie.feature.reservation.ui.convertLocalDateTime
import woowacourse.movie.feature.reservation.ui.screeningDateMessage
import woowacourse.movie.feature.reservation.ui.screeningTimeMessage
import woowacourse.movie.feature.reservation.ui.toScreeningDate
import woowacourse.movie.model.data.MovieRepository
import woowacourse.movie.model.reservation.ReservationCount
import woowacourse.movie.model.time.rangeTo

class MovieReservationPresenter(
    private val view: MovieReservationContract.View,
    private val movieRepository: MovieRepository,
) : MovieReservationContract.Presenter {
    private lateinit var reservationCount: ReservationCount

    override fun loadMovieData(movieId: Long) {
        val movie = movieRepository.find(movieId)
        view.initializeReservationView(movie)

        val screeningDates = (movie.startScreeningDate..movie.endScreeningDate).toList()
        view.initializeSpinner(
            screeningDates.screeningDateMessage(),
            screeningDates[0].screeningTimes().screeningTimeMessage(),
        )
    }

    override fun initializeReservationCount() {
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
        val screeningLocalDateTime = convertLocalDateTime(screeningDateValue, screeningTimeValue)
        view.moveSeatSelectView(screeningLocalDateTime, reservationCount.count)
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

    override fun selectScreeningDate(screeningDateMessage: String) {
        val screeningTimes = screeningDateMessage.toScreeningDate().screeningTimes()
        view.updateScreeningTimeSpinner(screeningTimes.screeningTimeMessage())
    }
}
