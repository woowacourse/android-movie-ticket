package woowacourse.movie.selectseat.uimodel

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SeatsUiModel(val seats: List<SeatUiModel>) : Parcelable {
    fun changeState(seat: SeatUiModel): SeatsUiModel {
        val updatedSeats =
            seats.map {
                if (it.row == seat.row && it.col == seat.col) {
                    it.changeState()
                } else {
                    it
                }
            }
        return SeatsUiModel(updatedSeats)
    }

    fun selectedSeats(): List<SeatUiModel> = seats.filter { it.state == SeatState.SELECTED }
}
