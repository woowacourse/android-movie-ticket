package woowacourse.movie.presentation.uimodel

import woowacourse.movie.domain.model.Seat

data class SeatsUIModel(
    val seatUIModels: List<SeatUIModel>,
    private val maxSelectableSeats: Int,
) {
    fun toggleSeatSelection(index: Int): SeatSelectionResult {
        val seatUIModel = seatUIModels[index]
        if (seatUIModel.isSelected) {
            seatUIModel.isSelected = false
            return SeatSelectionResult.Success
        } else if (selectedCount() < maxSelectableSeats) {
            seatUIModel.isSelected = true
            if (selectedCount() == maxSelectableSeats) {
                return SeatSelectionResult.MaxCapacityReached
            }
            return SeatSelectionResult.Success
        } else if (selectedCount() == maxSelectableSeats) {
            return SeatSelectionResult.AlreadyMaxCapacityReached
        }
        return SeatSelectionResult.Failure
    }

    private fun selectedCount(): Int = seatUIModels.count { it.isSelected }

    fun selectedSeats(): List<Seat> = seatUIModels.filter { it.isSelected }.map { it.seat }

    fun totalPrice(): Int = selectedSeats().sumOf { it.seatGrade.price }
}
