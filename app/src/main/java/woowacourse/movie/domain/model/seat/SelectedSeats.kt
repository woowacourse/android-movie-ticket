package woowacourse.movie.domain.model.seat

import java.util.Locale

class SelectedSeats(
    private val headCount: Int,
    private val _seats: MutableSet<Seat> = mutableSetOf(),
) {
    val value: List<Seat>
        get() = _seats.toList()

    fun updateSelection(seat: Seat) {
        if (isSelected(seat)) {
            _seats.remove(seat)
            return
        } else {
            require(_seats.size < headCount) {
                String.format(Locale.getDefault(), SELECT_MESSAGE, headCount)
            }
            _seats.add(seat)
        }
    }

    fun getTotalPrice(): Int = _seats.sumOf { it.grade.price }

    fun isFull(): Boolean = _seats.size == headCount

    fun isSelected(seat: Seat): Boolean = _seats.contains(seat)

    companion object {
        private const val SELECT_MESSAGE = "좌석은 %d개만 선택할 수 있습니다."
    }
}
