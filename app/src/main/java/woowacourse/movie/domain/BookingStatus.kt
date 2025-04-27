package woowacourse.movie.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import woowacourse.movie.domain.seat.BookingSeats
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

@Parcelize
data class BookingStatus(
    val movie: Movie,
    val isBooked: Boolean = true,
    val seat: BookingSeats,
    val bookedTime: LocalDateTime,
) : Parcelable {
    val memberCount: Int
        get() = seat.value

    fun calculateTicketPrices(): Int {
        return seat.calculateTicketPrices()
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

        fun from(
            movie: Movie,
            count: Int,
            bookedDate: LocalDate,
            bookedTime: LocalTime
        ): BookingStatus {
            val bookedDateTime = LocalDateTime.of(bookedDate, bookedTime)
            return BookingStatus(
                movie = movie,
                seat = BookingSeats(count),
                bookedTime = bookedDateTime
            )
        }

        operator fun invoke(
            movie: Movie,
            count: Int,
            bookedDate: LocalDate,
            bookedTime: LocalTime
        ): BookingStatus = from(movie, count, bookedDate, bookedTime)
    }
}
