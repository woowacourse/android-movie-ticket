package woowacourse.movie.presentation.uimodel

data class SeatsUIModel(
    val seats: List<SeatUIModel>,
    private val maxSelectableSeats: Int
) {
    fun toggleSeatSelection(index: Int): Boolean {
        val seatUIModel = seats[index]
        if (seatUIModel.isSelected) {
            seatUIModel.isSelected = false
            return true
        } else if (selectedCount() < maxSelectableSeats) {
            seatUIModel.isSelected = true
            return true
        }
        return false
    }
    
    private fun selectedCount(): Int = seats.count { it.isSelected }
    
    fun selectedSeats(): List<SeatUIModel> = seats.filter { it.isSelected }
    
    fun totalPrice(): Int = selectedSeats().sumOf { it.seat.seatGrade.price }
}
