package woowacourse.movie.booking.detail

import android.os.Bundle
import woowacourse.movie.model.BookingResult
import woowacourse.movie.model.Movie
import java.time.LocalDate
import java.time.LocalTime

interface BookingDetailContract {
    interface View {
        fun showMovieInfo(movie: Movie)

        fun showBookingResult(result: BookingResult)

        fun showScreeningDates(
            dates: List<LocalDate>,
            selected: LocalDate,
        )

        fun showScreeningTimes(
            times: List<LocalTime>,
            selected: LocalTime,
        )

        fun showBookingResultDialog(result: BookingResult)

        fun showToastErrorAndFinish(message: String)
    }

    interface Presenter {
        fun initializeData(savedInstanceState: Bundle?)

        fun onDateSelected(date: LocalDate)

        fun onTimeSelected(time: LocalTime)

        fun onHeadCountIncreased()

        fun onHeadCountDecreased()

        fun onConfirmReservation()

        fun onSaveState(outState: Bundle)
    }
}
