package woowacourse.movie.domain.model.booking.result

import java.time.LocalDate
import java.time.LocalTime

data class BookingResult(
    val title: String,
    val headCount: Int,
    val selectedDate: LocalDate,
    val selectedTime: LocalTime,
) {
    init {
        require(title.isNotBlank()) { ERROR_TITLE_BLANK_MESSAGE }
    }

    fun calculateAmount(ticketPrice: TicketPrice): Int {
        return ticketPrice.calculate(headCount)
    }

    fun plusHeadCount(): BookingResult {
        return this.copy(headCount = headCount + 1)
    }

    fun minusHeadCount(): BookingResult {
        return this.copy(headCount = headCount - 1)
    }

    fun updateCount(count : Int) : BookingResult {
        return this.copy(headCount = count)
    }

    fun updateDate(date: LocalDate): BookingResult {
        return this.copy(selectedDate = date)
    }

    fun updateTime(time: LocalTime): BookingResult {
        return this.copy(selectedTime = time)
    }

    fun isHeadCountValid() = headCount > 0

    companion object {
        const val CANCELLATION_LIMIT_MINUTES = 15
        private const val ERROR_TITLE_BLANK_MESSAGE = "예매한 영화 제목은 비어 있을 수 없다"
    }
}
