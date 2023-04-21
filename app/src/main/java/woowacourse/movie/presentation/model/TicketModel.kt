package woowacourse.movie.presentation.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.time.LocalDateTime

@Parcelize
data class TicketModel(
    val movieId: Long,
    val bookedDateTime: LocalDateTime,
    val count: Int,
    val paymentMoney: Int,
    val seats: List<String>,
) : Parcelable
