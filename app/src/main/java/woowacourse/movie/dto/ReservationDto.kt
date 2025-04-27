package woowacourse.movie.dto

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import woowacourse.movie.domain.BookingStatus
import woowacourse.movie.domain.PriceRule
import woowacourse.movie.dto.MovieListData.MovieDto
import java.time.LocalDateTime

@Parcelize
data class ReservationDto(
    val movie: MovieDto,
    val isBooked: Boolean,
    val memberCount: Int,
    val bookedTime: LocalDateTime,
    val totalPrice: Int,
) : Parcelable {
    companion object {
        fun fromBookingStatus(bookingStatus: BookingStatus): ReservationDto {
            return ReservationDto(
                movie = MovieDto.fromMovie(bookingStatus.movie),
                isBooked = bookingStatus.isBooked,
                memberCount = bookingStatus.memberCount.value,
                bookedTime = bookingStatus.bookedTime,
                totalPrice = PriceRule.RANK_A.price(bookingStatus.memberCount),
            )
        }
    }
}
