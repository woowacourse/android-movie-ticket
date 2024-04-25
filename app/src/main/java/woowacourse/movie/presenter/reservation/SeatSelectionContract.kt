package woowacourse.movie.presenter.reservation

import woowacourse.movie.model.Grade
import woowacourse.movie.model.Movie
import woowacourse.movie.model.Seat
import woowacourse.movie.model.Seats

interface SeatSelectionContract {
    interface Presenter {
        fun loadSeatNumber()

        fun loadMovie(movieId: Int)

        fun updateTotalPrice(
            isSelected: Boolean,
            seat: Seat,
        )

        fun initializeConfirmButton()
    }

    interface View {
        fun showSeatNumber(
            index: Int,
            seat: Seat,
        )

        fun setUpSeatColorByGrade(grade: Grade): Int

        fun updateSeatSelectedState(
            index: Int,
            isSelected: Boolean,
        )

        fun setConfirmButtonEnabled(count: Int)

        fun showMovieTitle(movie: Movie)

        fun showTotalPrice(amount: Int)

        fun launchReservationConfirmDialog(seats: Seats)

        fun navigateToFinished(seats: Seats)
    }
}
