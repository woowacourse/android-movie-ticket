package woowacourse.movie.view.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.time.LocalDateTime

@Parcelize
data class TicketData(
    val screeningData: ScreeningData,
    val showtime: LocalDateTime,
    val ticketCount: Int,
) : Parcelable
