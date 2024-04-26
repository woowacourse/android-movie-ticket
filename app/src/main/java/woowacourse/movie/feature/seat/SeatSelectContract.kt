package woowacourse.movie.feature.seat

import woowacourse.movie.feature.seat.ui.SeatSelectMovieUiModel
import woowacourse.movie.feature.seat.ui.SeatSelectTableUiModel
import woowacourse.movie.model.seat.SelectedSeats
import woowacourse.movie.utils.BasePresenter
import woowacourse.movie.utils.ErrorListener

interface SeatSelectContract {
    interface View : ErrorListener {
        fun initializeMovieView(movie: SeatSelectMovieUiModel)

        fun initializeSeatTable(seats: List<List<SeatSelectTableUiModel>>)

        fun updateReservationAmount(reservationAmountValue: Int)

        fun showCannotSelectSeat()

        fun selectSeat(
            row: Int,
            col: Int,
            isConfirm: Boolean,
        )

        fun unselectSeat(
            row: Int,
            col: Int,
        )

        fun moveReservationCompleteView(selectedSeats: SelectedSeats)
    }

    interface Presenter : BasePresenter {
        fun loadMovieData(movieId: Long)

        fun initializeSeatTable(
            row: Int,
            col: Int,
        )

        fun selectSeat(
            row: Int,
            col: Int,
        )

        fun confirmSeatSelection()
    }
}
