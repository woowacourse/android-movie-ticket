package woowacourse.movie.presentation.model

import androidx.annotation.DrawableRes
import woowacourse.movie.TicketCount
import woowacourse.movie.model.Seat
import woowacourse.movie.presentation.Reservation
import java.time.LocalDate

data class MovieModel(
    val id: Long,
    val title: String,
    val screeningStartDate: LocalDate,
    val screeningEndDate: LocalDate,
    val runningTime: Int,
    val description: String,
    @DrawableRes val thumbnail: Int?,
    @DrawableRes val poster: Int?,
) : MovieItemModel() {
    fun reserve(reservation: Reservation, seats: List<Seat>): TicketModel =
        toDomainModel().reserve(reservation.bookedDateTime, TicketCount(reservation.count), seats)
            .toPresentation()

    fun getScreeningDates(): List<LocalDate> = toDomainModel().getScreeningDates()
}
