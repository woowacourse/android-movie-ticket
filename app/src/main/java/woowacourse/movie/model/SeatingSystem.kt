package woowacourse.movie.model

class SeatingSystem(
    private var availableSeatCount: Int,
    private val rowSize: Int = 5,
    private val colSize: Int = 4,
) {
    val seats =
        List(rowSize * colSize) { index ->
            val row = index / colSize
            val col = index % colSize
            Seat(row, col)
        }

    private val _selectedSeats: MutableSet<Seat> = mutableSetOf()
    val selectedSeats: Set<Seat>
        get() = _selectedSeats.toSet()

    fun isSelected(index: Int): Boolean = seats[index] in selectedSeats

    fun trySelectSeat(index: Int): Result<Seat> {
        return if (canSelectSeat()) {
            val selected = seats[index]
            _selectedSeats.add(selected)
            Result.success(selected)
        } else {
            Result.failure(Error("최대 선택 가능한 자리 수는 ${availableSeatCount}개 입니다."))
        }
    }

    fun unSelectSeat(index: Int) {
        val selected = seats[index]
        _selectedSeats.remove(selected)
    }

    fun canSelectSeat(): Boolean = availableSeatCount > selectedSeats.size

    fun getTotalPrice(): Int = selectedSeats.sumOf { it.seatGrade.price }

    fun getSelectedSeatsIndex(): List<Int> = selectedSeats.map { (it.row * colSize) + it.col }
}
