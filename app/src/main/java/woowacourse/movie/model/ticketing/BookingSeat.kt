package woowacourse.movie.model.ticketing

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import woowacourse.movie.model.theater.SeatClass

@Parcelize
data class BookingSeat(
    val row: Int,
    val column: Int,
    val seatClass: SeatClass,
) : Parcelable
