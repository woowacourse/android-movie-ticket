package woowacourse.movie.dto

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import woowacourse.movie.domain.BookingStatus
import woowacourse.movie.dto.MovieListData.MovieDto
import java.time.LocalDateTime

@Parcelize
data class ReservationDto(
    val movie: MovieDto,
    val memberCount: Int,
    val bookedTime: LocalDateTime,
) : Parcelable {
    companion object {
        fun fromBookingStatus(bookingStatus: BookingStatus): ReservationDto {
            return ReservationDto(
                movie = MovieDto.fromMovie(bookingStatus.movie),
                memberCount = bookingStatus.memberCount.value,
                bookedTime = bookingStatus.bookedTime,
            )
        }
    }
}
