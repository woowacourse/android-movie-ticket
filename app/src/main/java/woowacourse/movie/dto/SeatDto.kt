package woowacourse.movie.dto

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import woowacourse.movie.domain.Seat

@Parcelize
data class SeatDto(
    val location: String,
    val price: PriceRuleDto,
    val row: String,
    val column: Int,
) : Parcelable {
    companion object {
        fun fromSeat(seat: Seat): SeatDto {
            return SeatDto(
                seat.location,
                PriceRuleDto.fromPriceRule(seat.price),
                seat.row,
                seat.column,
            )
        }
    }
}
