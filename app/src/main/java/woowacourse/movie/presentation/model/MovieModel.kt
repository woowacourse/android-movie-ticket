package woowacourse.movie.presentation.model

import androidx.annotation.DrawableRes
import woowacourse.movie.domain.model.tools.TicketCount
import woowacourse.movie.domain.model.tools.seat.Seats
import woowacourse.movie.presentation.mappers.toDomainModel
import woowacourse.movie.presentation.mappers.toPresentation
import java.time.LocalDate

data class MovieModel(
    val id: Long,
    val title: String,
    val screeningStartDate: LocalDate,
    val screeningEndDate: LocalDate,
    val runningTime: Int,
    val description: String,
    @DrawableRes val thumbnail: Int,
    @DrawableRes val poster: Int,
) {
    fun reserve(reservation: ReservationModel, seats: Seats): TicketModel =
        toDomainModel().reserve(reservation.bookedDateTime, TicketCount(reservation.count), seats)
            .toPresentation()

    fun getScreeningDates(): List<LocalDate> = toDomainModel().getScreeningDates()
}
