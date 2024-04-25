package woowacourse.movie.presentation.seat

import android.util.Log
import woowacourse.movie.domain.model.MovieSeats
import woowacourse.movie.presentation.seat.model.SeatSelectType
import woowacourse.movie.domain.repository.SeatRepository

class SeatSelectionPresenter(
    ticketCount: Int,
    private val view: SeatSelectionContract.View,
    private val seatRepository: SeatRepository,
) : SeatSelectionContract.Presenter {
    private val movieSeats = MovieSeats(ticketCount)

    override fun loadSeat() {
        view.showSeat(seatRepository.getSeats())
    }

    override fun selectSeat(
        rowIndex: Int,
        columIndex: Int,
    ) {
        val seat = seatRepository.getSeat(rowIndex, columIndex)
        if (seat.seatName.isEmpty()) return
        Log.d("dslfhjsdlfjlsdfjlsdf",movieSeats.getSeatSelectType(seat).toString())
        when(val movieSelectType = movieSeats.getSeatSelectType(seat)){
            SeatSelectType.ADD -> {
                movieSeats.addSeat(seat)
                view.showSelectedSeat(rowIndex,columIndex,movieSelectType)
            }
            SeatSelectType.REMOVE -> {
                movieSeats.deleteSeat(seat)
                view.showSelectedSeat(rowIndex,columIndex,movieSelectType)
            }
            SeatSelectType.PREVENT -> {}
        }
    }
}
