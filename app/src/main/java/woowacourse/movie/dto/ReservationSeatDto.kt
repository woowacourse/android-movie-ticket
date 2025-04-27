package woowacourse.movie.dto

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ReservationSeatDto(
    val reservationDto: ReservationDto,
    val totalPrice: Int,
    val seats: List<SeatDto>,
) : Parcelable
