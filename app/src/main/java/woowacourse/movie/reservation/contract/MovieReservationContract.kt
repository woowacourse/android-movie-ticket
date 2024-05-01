package woowacourse.movie.reservation.contract

import woowacourse.movie.list.model.Movie
import woowacourse.movie.reservation.model.Count
import woowacourse.movie.reservation.presenter.MovieReservationPresenter
import java.time.LocalDate
import java.time.LocalTime

interface MovieReservationContract {
    interface View {
        val presenter: MovieReservationPresenter

        fun showCurrentResultTicketCountView(info: Int)

        fun setMovieView(info: Movie)

        fun startMovieTicketActivity(info: Count)

        fun showToast(message: String)

        fun showSpinner(
            screeningDates: List<LocalDate>,
            screeningTimes: List<LocalTime>,
        )

        fun setOnSpinnerDateItemSelectedListener(screeningDates: List<LocalDate>)

        fun setOnSpinnerTimeItemSelectedListener(screeningTimes: List<LocalTime>)
    }

    interface Presenter {
        fun setCurrentResultTicketCountInfo()

        fun setPlusButtonClickInfo()

        fun setMinusButtonClickInfo()

        fun setTicketingButtonClickInfo()

        fun storeMovieId(movieId: Long)

        fun setMovieInfo()

        fun setSpinnerInfo()

        fun setSpinnerDateItemInfo()

        fun setSpinnerTimeItemInfo()

        fun storeSelectedTime(selectedTime: LocalTime)
    }
}
