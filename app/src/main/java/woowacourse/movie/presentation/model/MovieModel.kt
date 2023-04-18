package woowacourse.movie.presentation.model

import androidx.annotation.DrawableRes
import woowacourse.movie.TicketCount
import java.time.LocalDate
import java.time.LocalDateTime

data class MovieModel(
    val id: Long,
    val title: String,
    val screeningStartDate: LocalDate,
    val screeningEndDate: LocalDate,
    val runningTime: Int,
    val description: String,
    @DrawableRes val thumbnail: Int?,
    @DrawableRes val poster: Int?,
) {
    fun reserve(dateTime: LocalDateTime, ticketCount: TicketCount): TicketModel =
        toDomainModel().reserve(dateTime, ticketCount).toPresentation()

    fun getScreeningDates(): List<LocalDate> = toDomainModel().getScreeningDates()
}
