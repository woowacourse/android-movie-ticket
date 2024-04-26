package woowacourse.movie.feature.reservation

import woowacourse.movie.model.data.dto.Movie
import woowacourse.movie.model.time.ScreeningDate
import woowacourse.movie.model.time.ScreeningTime
import woowacourse.movie.utils.BasePresenter
import woowacourse.movie.utils.ErrorListener
import java.time.LocalDateTime

interface MovieReservationContract {
    interface View : ErrorListener {
        fun initializeReservationView(movie: Movie)

        fun initializeSpinner(
            screeningDates: List<ScreeningDate>,
            screeningTimes: List<ScreeningTime>,
        )

        fun updateReservationCount(reservationCountValue: Int)

        fun moveSeatSelectView(
            screeningDateTime: LocalDateTime,
            reservationCountValue: Int,
        )

        fun updateScreeningTimeSpinner(screeningTimes: List<ScreeningTime>)
    }

    interface Presenter : BasePresenter {
        fun loadMovieData(movieId: Long)

        fun initializeReservationCount()

        fun decreaseReservationCount()

        fun increaseReservationCount()

        fun selectSeat(
            screeningDateValue: String,
            screeningTimeValue: String,
        )

        fun updateReservationCount(reservationCountValue: Int)

        fun selectScreeningDate(screeningDate: ScreeningDate)
    }
}
