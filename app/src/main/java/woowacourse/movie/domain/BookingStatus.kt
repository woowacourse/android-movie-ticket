package woowacourse.movie.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.time.LocalDateTime

@Parcelize
data class BookingStatus(
    val movie: Movie,
    val isBooked: Boolean,
    private val _memberCount: MemberCount,
    val bookedTime: LocalDateTime,
) : Parcelable {
    val memberCount: Int
        get() = _memberCount.value

    fun calculateTicketPrices(): Int {
        return _memberCount.calculateTicketPrices()
    }

    fun book(): BookingStatus {
        return if (!isBooked) this.copy(isBooked = true)
        else throw IllegalStateException(ERR_ALREADY_BOOKED)
    }

    fun cancel(): BookingStatus {
        return if (isBooked) this.copy(isBooked = false)
        else throw IllegalStateException(ERR_NOT_BOOKED)
    }

    companion object {
        private const val ERR_ALREADY_BOOKED = "이미 예매된 상태입니다."
        private const val ERR_NOT_BOOKED = "예매가 되어있지 않습니다"

    }
}

