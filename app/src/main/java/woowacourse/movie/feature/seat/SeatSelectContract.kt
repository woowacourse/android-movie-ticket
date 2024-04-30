package woowacourse.movie.feature.seat

import woowacourse.movie.feature.seat.ui.SeatSelectMovieUiModel
import woowacourse.movie.feature.seat.ui.SeatSelectTableUiModel
import woowacourse.movie.model.seat.SelectedSeats
import woowacourse.movie.utils.BasePresenter
import java.time.LocalDateTime

interface SeatSelectContract {
    interface View : SeatSelectErrorListener {
        fun initializeMovieView(movie: SeatSelectMovieUiModel)

        fun loadSeatTable(seats: List<List<SeatSelectTableUiModel>>)

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

        fun moveReservationCompleteView(ticketId: Long)
    }

    interface Presenter : BasePresenter {
        fun loadMovieData(movieId: Long)

        fun initializeSeatTable(
            selectedSeats: SelectedSeats,
            row: Int,
            col: Int,
        )

        fun selectSeat(
            row: Int,
            col: Int,
        )

        fun finishSeatSelection(
            movieId: Long,
            screeningDateTime: LocalDateTime,
        )

        fun updateSelectedSeats(selectedSeats: SelectedSeats)
    }
}
