package woowacourse.movie.feature.ticket

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import woowacourse.movie.feature.movieSelect.adapter.ScreeningData
import woowacourse.movie.feature.seatSelect.SeatsData
import java.time.LocalDateTime

@Parcelize
data class TicketData(
    val screeningData: ScreeningData,
    val showtime: LocalDateTime,
    val ticketCount: Int,
    val seatsData: SeatsData = SeatsData.Companion.getEmptySeatsData(),
    val totalTicketPrice: Int = 0,
) : Parcelable {
    fun seatsAddedTicketData(seatsData: SeatsData): TicketData = TicketData(this.screeningData, this.showtime, this.ticketCount, seatsData)
}
