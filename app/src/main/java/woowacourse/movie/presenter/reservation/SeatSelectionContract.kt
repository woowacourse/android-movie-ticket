package woowacourse.movie.presenter.reservation

import android.widget.Button
import woowacourse.movie.model.movie.Movie
import woowacourse.movie.model.seats.Grade
import woowacourse.movie.model.seats.Seat
import woowacourse.movie.model.seats.Seats

interface SeatSelectionContract {
    interface Presenter {
        fun loadSeatNumber()

        fun loadMovie(movieId: Int)

        fun updateTotalPrice(
            isSelected: Boolean,
            seat: Seat,
        )

        fun initializeConfirmButton()

        fun restoreReservation(count: Int)

        fun restoreSeats(
            selectedSeats: Seats,
            seatsIndex: List<Int>,
        )

        fun manageSelectedSeats(
            isSelected: Boolean,
            index: Int,
            seat: Seat,
        )
    }

    interface View {
        fun initializeSeatsTable(
            index: Int,
            seat: Seat,
        )

        fun setUpSeatColorByGrade(grade: Grade): Int

        fun Button.showSeatNumber(seat: Seat)

        fun Button.updateReservationInformation(
            index: Int,
            seat: Seat,
        )

        fun updateSeatSelectedState(
            index: Int,
            isSelected: Boolean,
        )

        fun setConfirmButtonEnabled(count: Int)

        fun showMovieTitle(movie: Movie)

        fun showAmount(amount: Int)

        fun launchReservationConfirmDialog(seats: Seats)

        fun navigateToFinished(seats: Seats)

        fun restoreSelectedSeats(selectedSeats: List<Int>)

        fun showErrorToast()
    }
}
