package woowacourse.movie.model

import java.time.LocalDate
import java.time.LocalTime

data class Ticket(
    val title: String,
    val headCount: HeadCount,
    val selectedDate: LocalDate,
    val selectedTime: LocalTime,
    val seats: Seats,
) {
    init {
        require(title.isNotBlank()) { ERROR_TITLE_BLANK_MESSAGE }
    }

    val amount: Int
        get() = seats.amount

    fun plusHeadCount(): Ticket = copy(headCount = headCount.plus())

    fun minusHeadCount(): Ticket = copy(headCount = headCount.minus())

    fun updateDate(date: LocalDate): Ticket = copy(selectedDate = date)

    fun updateTime(time: LocalTime): Ticket = copy(selectedTime = time)

    fun isHeadCountValid(): Boolean = headCount.isValid()

    fun toggleSeat(seat: Seat): Ticket = copy(seats = seats.toggle(seat, headCount.value))

    companion object {
        private const val ERROR_TITLE_BLANK_MESSAGE = "예매한 영화 제목은 비어 있을 수 없다"
    }
}
