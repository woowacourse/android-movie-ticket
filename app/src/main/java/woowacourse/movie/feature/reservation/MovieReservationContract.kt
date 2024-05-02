package woowacourse.movie.feature.reservation

import woowacourse.movie.model.data.dto.Movie
import woowacourse.movie.utils.BasePresenter
import java.time.LocalDateTime

interface MovieReservationContract {
    interface View : MovieReservationErrorListener {
        fun initializeReservationView(movie: Movie)

        fun initializeSpinner(
            screeningDatesMessage: List<String>,
            screeningTimesMessage: List<String>,
        )

        fun updateReservationCount(reservationCountValue: Int)

        fun moveSeatSelectView(
            screeningDateTime: LocalDateTime,
            reservationCountValue: Int,
        )

        fun updateScreeningTimeSpinner(screeningTimes: List<String>)
    }

    interface Presenter : BasePresenter {
        fun loadMovieData(movieId: Long)

        fun loadReservationCount()

        fun decreaseReservationCount()

        fun increaseReservationCount()

        fun selectSeat(
            screeningDateValue: String,
            screeningTimeValue: String,
        )

        fun updateReservationCount(reservationCountValue: Int)

        fun selectScreeningDate(screeningDateMessage: String)
    }
}
