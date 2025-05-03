package woowacourse.movie.view.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import woowacourse.movie.feature.movieSelect.adapter.ScreeningData
import java.time.LocalDateTime

@Parcelize
data class TicketData(
    val screeningData: ScreeningData,
    val showtime: LocalDateTime,
    val ticketCount: Int,
    val seatsData: SeatsData = SeatsData.getEmptySeatsData(),
) : Parcelable {
    fun seatsAddedTicketData(seatsData: SeatsData): TicketData = TicketData(this.screeningData, this.showtime, this.ticketCount, seatsData)
}
