package woowacourse.movie.view.reservation.detail

import android.os.Bundle
import woowacourse.movie.domain.Movie
import woowacourse.movie.domain.Ticket
import java.time.LocalDateTime

interface ReservationContract {
    interface Presenter {
        fun fetchData(movie: Movie)

        fun onSaveState(outState: Bundle)

        fun onRestoreState(outState: Bundle)

        fun increasedCount()

        fun decreasedCount()

        fun selectedDate(position: Int)

        fun selectedTime(position: Int)

        fun createTicket(selectedDateTime: LocalDateTime)

        fun resetSelectedTimePosition(position: Int)
    }

    interface View {
        fun showErrorInvalidMovie()

        fun showMovieReservationScreen(movie: Movie)

        fun showCount(count: Int)

        fun showTicket(ticket: Ticket)

        fun setCountButtons()

        fun setReservationButton()

        fun showSpinnerData(
            movie: Movie,
            selectedDatePosition: Int,
        )

        fun setTimeSelection(position: Int)
    }
}
