package woowacourse.movie.domain

import java.io.Serializable
import java.time.LocalDateTime

class Reservation(
    val title: String,
    var count: Int,
    val reservedTime: LocalDateTime,
) : Serializable {
    init {
        require(count >= 1) { INVALID_COUNT }
    }

    fun totalPrice(): Int = TICKET_PRICE * count

    fun addCount() = Reservation(title, ++count, reservedTime)

    fun canMinus(): Boolean = count > 1

    fun minusCount(): Reservation {
        return Reservation(title, --count, reservedTime)
    }

    fun updateReservedTime(time: LocalDateTime) = Reservation(title, count, time)

    companion object {
        private const val TICKET_PRICE = 13000
        private const val INVALID_COUNT = "예약 개수는 1보다 같거나 커야 합니다."
    }
}
