package woowacourse.movie.uimodel

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.time.LocalDateTime

@Parcelize
class SeatPlan(
    val movieId: Int,
    val ticketNum: Int,
    val reservedDateTime: LocalDateTime,
) : Parcelable
