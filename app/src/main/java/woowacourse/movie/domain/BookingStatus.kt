package woowacourse.movie.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

@Parcelize
data class BookingStatus (
    val movie: Movie,
    val isBooked: Boolean = true,
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
        else throw IllegalStateException(ERROR_ALREADY_BOOKED)
    }

    fun cancel(): BookingStatus {
        return if (isBooked) this.copy(isBooked = false)
        else throw IllegalStateException(ERROR_NOT_BOOKED)
    }

    companion object {
        private const val ERROR_ALREADY_BOOKED = "이미 예매된 상태입니다."
        private const val ERROR_NOT_BOOKED = "예매가 되어있지 않습니다"

        operator fun invoke(movie: Movie, count: Int, bookedDate: LocalDate, bookedTime: LocalTime): BookingStatus {
            val bookedDateTime = LocalDateTime.of(bookedDate, bookedTime)
            return BookingStatus(
                movie = movie,
                _memberCount = MemberCount(count),
                bookedTime = bookedDateTime
            )
        }
    }
}

