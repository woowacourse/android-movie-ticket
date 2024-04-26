package woowacourse.movie.model

import woowacourse.movie.model.ticketing.BookingDateTime
import woowacourse.movie.model.ticketing.BookingSeat
import java.lang.IllegalStateException

class BoxOffice(
    val count: Count,
    val bookingDateTime: BookingDateTime,
) {
    val totalPrice: Int
        get() = seats.sumOf { seat -> seat.seatClass.price }

    var seats: List<BookingSeat> = emptyList()
        private set

    val isSubmitAvailable: Boolean
        get() = seats.size == count.value

    fun updateSeats(newSeats: List<BookingSeat>) {
        if (newSeats.size > count.value) throw IllegalStateException(ERROR_OVER_COUNT)
        seats = newSeats
    }

    companion object {
        private const val ERROR_OVER_COUNT = "예약 가능 인원 수를 초과하였습니다."
    }
}
