package woowacourse.movie.presentation.uimodel

data class SeatsUIModel(
    val seats: List<SeatUIModel>,
    private val maxSelectableSeats: Int
) {
    fun toggleSeatSelection(index: Int): SeatSelectionResult {
        val seatUIModel = seats[index]
        if (seatUIModel.isSelected) {
            seatUIModel.isSelected = false
            return SeatSelectionResult.Success
        } else if (selectedCount() < maxSelectableSeats) {
            seatUIModel.isSelected = true
            if (selectedCount() == maxSelectableSeats) {
                return SeatSelectionResult.MaxCapacityReached
            }
            return SeatSelectionResult.Success
        }
        return SeatSelectionResult.Failure
    }
    
    private fun selectedCount(): Int = seats.count { it.isSelected }
    
    fun selectedSeats(): List<SeatUIModel> = seats.filter { it.isSelected }
    
    fun totalPrice(): Int = selectedSeats().sumOf { it.seat.seatGrade.price }
}
