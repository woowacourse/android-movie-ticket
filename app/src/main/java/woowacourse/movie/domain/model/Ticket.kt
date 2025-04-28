package woowacourse.movie.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.time.LocalDateTime

@Parcelize
data class Ticket(
    val title: String,
    val showTime: LocalDateTime,
    val seat: Seat,
    val price: Int,
) : Parcelable
