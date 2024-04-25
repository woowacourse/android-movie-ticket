package woowacourse.movie.presentation.seatSelection

import woowacourse.movie.model.SeatingSystem

class SeatSelectionPresenter(
    private val seatSelectionContractView: SeatSelectionContract.View,
    private val movieId: Int,
) : SeatSelectionContract.Presenter {
    private val seatingSystem = SeatingSystem()

    override fun initializeSeats() {
        seatSelectionContractView.initializeSeats(seatingSystem.seats)
    }

    override fun updateSeatSelection(index: Int) {
        if (seatingSystem.isSelected(index)) {
            seatingSystem.unSelectSeat(index)
            seatSelectionContractView.updateUnSelectedSeatUI(index)
            return
        }
        seatingSystem.selectSeat(index)
        seatSelectionContractView.updateSelectedSeatUI(index)
    }
}
